package prev.phase.imclin;

import java.util.*;

import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.visitor.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.lin.*;
import prev.phase.imcgen.ImcGen;
import prev.phase.memory.*;

public class ChunkGenerator extends AstFullVisitor<Object, Object> {

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
		switch (atomExpr.type()) {
		case STRING:
			MemAbsAccess absAccess = Memory.strings.get(atomExpr);
			ImcLin.addDataChunk(new LinDataChunk(absAccess));
			break;
		default:
			break;
		}
		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		funDecl.expr().accept(this, arg);

		MemFrame frame = Memory.frames.get(funDecl);
		MemLabel entryLabel = new MemLabel();
		MemLabel exitLabel = new MemLabel();
		
		Vector<ImcStmt> canonStmts = new Vector<ImcStmt>();
		canonStmts.add(new ImcLABEL(entryLabel));
		ImcExpr bodyExpr = ImcGen.exprImc.get(funDecl.expr());
		ImcStmt bodyStmt = new ImcMOVE(new ImcTEMP(frame.RV), bodyExpr);
		canonStmts.addAll(bodyStmt.accept(new StmtCanonizer(), null));
		canonStmts.add(new ImcJUMP(exitLabel));
		
		Vector<ImcStmt> linearStmts = linearize (canonStmts);
		ImcLin.addCodeChunk(new LinCodeChunk(frame, linearStmts, entryLabel, exitLabel));
		
		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Object arg) {
		MemAccess access = Memory.accesses.get(varDecl);
		if (access instanceof MemAbsAccess) {
			MemAbsAccess absAccess = (MemAbsAccess) access;
			ImcLin.addDataChunk(new LinDataChunk(absAccess));
		}
		return null;
	}
	
	private Vector<ImcStmt> linearize(Vector<ImcStmt> stmts) {
		HashMap<MemLabel, Integer> labels = new HashMap<>();

		for (int i = 1; i < stmts.size(); i++) {
			if (stmts.get(i) instanceof ImcLABEL) {
				ImcLABEL label = (ImcLABEL) stmts.get(i);

				// preverimo ali ima vsak label spredaj jump
				if (!(stmts.get(i - 1) instanceof ImcJUMP || stmts.get(i - 1) instanceof ImcCJUMP)) {
					stmts.add(i, new ImcJUMP(label.label));
					i++;
				}
				
				labels.put(label.label, i);
			}
		}

		@SuppressWarnings("unchecked")
		Vector<ImcStmt> __stmts = (Vector<ImcStmt>) stmts.clone();
		for (int i = 0; i < __stmts.size(); i++) {
			ImcStmt stmt = __stmts.get(i);
			
			if (stmt instanceof ImcCJUMP) {
				// skopiraj negativno labelo za ta CJUMP brez LABELE, ker dodamo novo
				i++; // skip ImcCJUMP
				
				MemLabel newNegLabel = new MemLabel();
				__stmts.add(i++, new ImcLABEL(newNegLabel));

				ImcCJUMP cjump = (ImcCJUMP) stmt;
				int k = labels.get(cjump.negLabel) + 1; // +1 da skippamo labelo
				cjump.negLabel = newNegLabel; // nastavimo na novo negativno labelo
				
				while (k < stmts.size() && !(stmts.get(k) instanceof ImcLABEL)) {
					__stmts.add(i++, stmts.get(k++));
				}
				
				i -= 1;
			}
		}

		return __stmts;
	}

}
