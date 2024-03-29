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
		for (int i = 1; i < stmts.size(); i++) {
			if (stmts.get(i) instanceof ImcLABEL) {
				ImcLABEL label = (ImcLABEL) stmts.get(i);

				// preverimo ali ima vsak label spredaj jump
				ImcStmt prevImc = stmts.get(i - 1);
				if (!(prevImc instanceof ImcJUMP || prevImc instanceof ImcCJUMP))
					stmts.add(i++, new ImcJUMP(label.label));
			}
		}

		Vector<ImcStmt> __stmts = new Vector<ImcStmt>(stmts);
		for (int i = 0; i < __stmts.size(); i++) {
			ImcStmt stmt = __stmts.get(i);
			
			if (stmt instanceof ImcCJUMP) {
				// skopiraj negativno labelo za ta CJUMP brez LABELE, ker dodamo novo
				i++; // skip ImcCJUMP
				
				ImcCJUMP cjump = (ImcCJUMP) stmt;
				MemLabel newNegLabel = new MemLabel();
				__stmts.add(i++, new ImcLABEL(newNegLabel));
				__stmts.add(i, new ImcJUMP(cjump.negLabel));
				cjump.negLabel = newNegLabel; // nastavimo na novo negativno labelo
			}
		}

		return __stmts;
	}

}
