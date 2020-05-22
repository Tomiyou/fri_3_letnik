package prev.phase.imcgen;

import java.util.*;

import prev.common.report.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.visitor.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.mem.*;
import prev.data.semtype.*;
import prev.phase.memory.Memory;
import prev.phase.seman.SemAn;

public class ExprGenerator extends AstFullVisitor<Object, Stack<MemFrame>> {

	Report.Error Error(String msg, Locatable loc) {
		return new Report.Error("Error (" + loc.location() + "): " + msg);
	}
	
	// EXPRESSIONS

	@Override
	public Object visit(AstArrExpr arrExpr, Stack<MemFrame> frames) {
		if (arrExpr.arr() != null)
			arrExpr.arr().accept(this, frames);
		if (arrExpr.idx() != null)
			arrExpr.idx().accept(this, frames);

		// a:integer[10] a[j]
		// MEM(BINOP(ADD, addr(a), val(j) * sizeof(integer)))

		ImcExpr arr = ImcGen.exprImc.get(arrExpr.arr());
		ImcExpr idx = ImcGen.exprImc.get(arrExpr.idx());

		if (arr instanceof ImcMEM) {
			arr = ((ImcMEM) arr).addr;
		} else {
			throw new Report.Error("Array access doesn't work on non mem types.");
		}

		return ImcGen.exprImc.put(arrExpr,
				new ImcMEM(new ImcBINOP(ImcBINOP.Oper.ADD, arr, new ImcBINOP(ImcBINOP.Oper.MUL, idx, new ImcCONST(8)))));
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Stack<MemFrame> frames) {
		ImcExpr value;
		switch (atomExpr.type()) {
			case VOID:
				value = new ImcCONST(0);
				break;
			case CHAR:
				value = new ImcCONST((long) atomExpr.value().charAt(1));
				break;
			case INTEGER:
				value = new ImcCONST(Long.parseLong(atomExpr.value()));
				break;
			case BOOLEAN:
				if (atomExpr.value().equals("true"))
					value = new ImcCONST(1);
				else
					value = new ImcCONST(0);
				break;
			case POINTER:
				value = new ImcCONST(0);
				break;
			default: // STRING
				MemAbsAccess acc = Memory.strings.get(atomExpr);
				value = new ImcNAME(acc.label);
				break;
		}

		return ImcGen.exprImc.put(atomExpr, value);
	}

	@Override
	public Object visit(AstBinExpr binExpr, Stack<MemFrame> frames) {
		if (binExpr.fstExpr() != null)
			binExpr.fstExpr().accept(this, frames);
		if (binExpr.sndExpr() != null)
			binExpr.sndExpr().accept(this, frames);

		ImcBINOP.Oper op;
		switch (binExpr.oper()) {
			case OR:
				op = ImcBINOP.Oper.OR;
				break;
			case AND:
				op = ImcBINOP.Oper.AND;
				break;
			case EQU:
				op = ImcBINOP.Oper.EQU;
				break;
			case NEQ:
				op = ImcBINOP.Oper.NEQ;
				break;
			case LTH:
				op = ImcBINOP.Oper.LTH;
				break;
			case GTH:
				op = ImcBINOP.Oper.GTH;
				break;
			case LEQ:
				op = ImcBINOP.Oper.LEQ;
				break;
			case GEQ:
				op = ImcBINOP.Oper.GEQ;
				break;
			case ADD:
				op = ImcBINOP.Oper.ADD;
				break;
			case SUB:
				op = ImcBINOP.Oper.SUB;
				break;
			case MUL:
				op = ImcBINOP.Oper.MUL;
				break;
			case DIV:
				op = ImcBINOP.Oper.DIV;
				break;
			default: // MOD
				op = ImcBINOP.Oper.MOD;
				break;
		}

		return ImcGen.exprImc.put(binExpr,
				new ImcBINOP(op, ImcGen.exprImc.get(binExpr.fstExpr()), ImcGen.exprImc.get(binExpr.sndExpr())));
	}

	@Override
	public Object visit(AstCallExpr callExpr, Stack<MemFrame> frames) {
		if (callExpr.args() != null)
			callExpr.args().accept(this, frames);

		AstFunDecl funDecl = (AstFunDecl) SemAn.declaredAt.get(callExpr);
		MemFrame callFrame = Memory.frames.get(funDecl);
		MemFrame currentFrame = frames.peek();

		ImcExpr SL = new ImcTEMP(currentFrame.FP);
		int declDepth = callFrame.depth, currentDepth = currentFrame.depth;
		while (currentDepth >= declDepth) {
			SL = new ImcMEM(SL);
			currentDepth--;
		}

		Vector<ImcExpr> args = new Vector<>();
		args.add(SL);
		for (AstExpr expr : callExpr.args())
			args.add(ImcGen.exprImc.get(expr));

		long offset = 8;
		Vector<Long> offsets = new Vector<>();
		offsets.add(0L);
		for (AstExpr expr : callExpr.args()) {
			offsets.add(offset);
			offset += SemAn.ofType.get(expr).actualType().size();
		}

		return ImcGen.exprImc.put(callExpr, new ImcCALL(Memory.frames.get(funDecl).label, offsets, args));
	}

	@Override
	public Object visit(AstCastExpr castExpr, Stack<MemFrame> frames) {
		if (castExpr.expr() != null)
			castExpr.expr().accept(this, frames);
		if (castExpr.type() != null)
			castExpr.type().accept(this, frames);

		ImcExpr expr = ImcGen.exprImc.get(castExpr.expr());
		if (SemAn.isType.get(castExpr.type()).actualType() instanceof SemChar) {
			expr = new ImcBINOP(ImcBINOP.Oper.MOD, expr, new ImcCONST(256));
		}

		return ImcGen.exprImc.put(castExpr, expr);
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Stack<MemFrame> frames) {
		AstMemDecl decl = (AstMemDecl) SemAn.declaredAt.get(nameExpr);
		if (decl == null)
			return null;

		MemAccess acc = Memory.accesses.get(decl);

		ImcExpr val;
		if (acc instanceof MemAbsAccess) {
			// globalna spremenljivka: MEM(NAME(Label(_i))) ... dostop do globalne
			// spremenljikve i
			val = new ImcMEM(new ImcNAME(((MemAbsAccess) acc).label));
		} else {
			// MEM(BINOP(ADD, TEMP(FP) , odmik))
			// MEM(BINOP(ADD, MEM(TEMP(FP)), odmik))
			// veš a si na nivoju prave funkcije, če je depth isti kt od funkcije
			MemRelAccess _acc = (MemRelAccess) acc;

			// skačeš po stacku navzgor, za vsak nivo med _acc.depth in frames.peek.depth k
			// je DRUGAČEN je 1 MEM
			ImcExpr baseAddr = new ImcTEMP(frames.peek().FP);
			int declDepth = _acc.depth, currentDepth = frames.peek().depth;
			while (currentDepth > declDepth) {
				baseAddr = new ImcMEM(baseAddr);
				currentDepth--;
			}

			val = new ImcMEM(new ImcBINOP(ImcBINOP.Oper.ADD, baseAddr, new ImcCONST(_acc.offset)));
		}

		return ImcGen.exprImc.put(nameExpr, val);
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Stack<MemFrame> frames) {
		if (pfxExpr.expr() != null)
			pfxExpr.expr().accept(this, frames);

		ImcExpr imc = ImcGen.exprImc.get(pfxExpr.expr());

		switch (pfxExpr.oper()) {
			case ADD:
				break;
			case SUB:
				imc = new ImcUNOP(ImcUNOP.Oper.NEG, imc);
				break;
			case NOT:
				imc = new ImcUNOP(ImcUNOP.Oper.NOT, imc);
				break;
			case DEL:
				Vector<Long> offsets = new Vector<>();
				offsets.add(0L);
				Vector<ImcExpr> args = new Vector<>();
				args.add(imc);
				imc = new ImcCALL(new MemLabel("del"), offsets, args);
				break;
			case NEW:
				offsets = new Vector<>();
				offsets.add(0L);
				args = new Vector<>();
				args.add(imc);
				imc = new ImcCALL(new MemLabel("new"), offsets, args);
				break;
			case PTR:
				if (imc instanceof ImcMEM)
					imc = ((ImcMEM) imc).addr;
				else
					throw Error("Pointer dereferencing doesn't work on non mem types.", pfxExpr);
				break;
		}

		return ImcGen.exprImc.put(pfxExpr, imc);
	}

	@Override
	public Object visit(AstRecExpr recExpr, Stack<MemFrame> frames) {
		if (recExpr.rec() != null)
			recExpr.rec().accept(this, frames);
		if (recExpr.comp() != null)
			recExpr.comp().accept(this, frames);

		ImcExpr rec = ImcGen.exprImc.get(recExpr.rec());
		long offset = ((MemRelAccess) Memory.accesses.get(SemAn.bindCompDecl.get(recExpr))).offset;

		if (rec instanceof ImcMEM) {
			rec = ((ImcMEM) rec).addr;
		} else {
			throw new Report.Error("Compound access doesn't work on non mem types.");
		}

		return ImcGen.exprImc.put(recExpr, new ImcMEM(new ImcBINOP(ImcBINOP.Oper.ADD, rec, new ImcCONST(offset))));
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Stack<MemFrame> frames) {
		if (sfxExpr.expr() != null)
			sfxExpr.expr().accept(this, frames);

		return ImcGen.exprImc.put(sfxExpr, new ImcMEM(ImcGen.exprImc.get(sfxExpr.expr())));
	}

	@Override
	public Object visit(AstStmtExpr stmtExpr, Stack<MemFrame> frames) {
		if (stmtExpr.stmts() != null)
			stmtExpr.stmts().accept(this, frames);

		Vector<ImcStmt> stmts = new Vector<ImcStmt>();
		for (AstStmt stmt : stmtExpr.stmts())
			stmts.add(ImcGen.stmtImc.get(stmt));
		
		// ce je zadn ESTMT (ni recimo assign)
		if (stmts.get(stmts.size() - 1) instanceof ImcESTMT) {
			ImcExpr lastExpr = ((ImcESTMT) stmts.remove(stmts.size() - 1)).expr;
			
			if (stmts.size() > 0)
				return ImcGen.exprImc.put(stmtExpr, new ImcSEXPR(new ImcSTMTS(stmts), lastExpr));

			return ImcGen.exprImc.put(stmtExpr, lastExpr);
		}
		
		return ImcGen.exprImc.put(stmtExpr, new ImcSEXPR(new ImcSTMTS(stmts), new ImcCONST(0) ));
	}

	@Override
	public Object visit(AstWhereExpr whereExpr, Stack<MemFrame> frames) {
		if (whereExpr.expr() != null)
			whereExpr.expr().accept(this, frames);
		whereExpr.decls().accept(new CodeGenerator(), frames);
		
		return ImcGen.exprImc.put(whereExpr, ImcGen.exprImc.get(whereExpr.expr()) );
	}

	// STATEMENTS

	@Override
	public Object visit(AstAssignStmt assignStmt, Stack<MemFrame> frames) {
		if (assignStmt.dst() != null)
			assignStmt.dst().accept(this, frames);
		if (assignStmt.src() != null)
			assignStmt.src().accept(this, frames);

		return ImcGen.stmtImc.put(assignStmt,
				new ImcMOVE(ImcGen.exprImc.get(assignStmt.dst()), ImcGen.exprImc.get(assignStmt.src())));
	}

	@Override
	public Object visit(AstCompoundStmt compoundStmt, Stack<MemFrame> frames) {
		if (compoundStmt.stmts() != null)
			compoundStmt.stmts().accept(this, frames);

		Vector<ImcStmt> stmts = new Vector<ImcStmt>();
		for (AstStmt stmt : compoundStmt.stmts())
			stmts.add(ImcGen.stmtImc.get(stmt));
		return ImcGen.stmtImc.put(compoundStmt, new ImcSTMTS(stmts));
	}

	@Override
	public Object visit(AstExprStmt exprStmt, Stack<MemFrame> frames) {
		if (exprStmt.expr() != null)
			exprStmt.expr().accept(this, frames);
			
		return ImcGen.stmtImc.put(exprStmt, new ImcESTMT( ImcGen.exprImc.get(exprStmt.expr()) ));
	}

	@Override
	public Object visit(AstIfStmt ifStmt, Stack<MemFrame> frames) {
		if (ifStmt.cond() != null)
			ifStmt.cond().accept(this, frames);
		if (ifStmt.thenStmt() != null)
			ifStmt.thenStmt().accept(this, frames);
		if (ifStmt.elseStmt() != null)
			ifStmt.elseStmt().accept(this, frames);

		// construct if stmt
		ImcLABEL _true = new ImcLABEL(new MemLabel());
		ImcLABEL _false = new ImcLABEL(new MemLabel());
		ImcLABEL _end = new ImcLABEL(new MemLabel());

		Vector<ImcStmt> stmts = new Vector<ImcStmt>();
		stmts.add(new ImcCJUMP(ImcGen.exprImc.get(ifStmt.cond()), _true.label, _false.label));
		stmts.add(_true);
		stmts.add(ImcGen.stmtImc.get(ifStmt.thenStmt()));
		stmts.add(new ImcJUMP(_end.label));
		stmts.add(_false);
		stmts.add(ImcGen.stmtImc.get(ifStmt.elseStmt()));
		stmts.add(_end);

		return ImcGen.stmtImc.put(ifStmt, new ImcSTMTS(stmts));
	}

	@Override
	public Object visit(AstWhileStmt whileStmt, Stack<MemFrame> frames) {
		if (whileStmt.cond() != null)
			whileStmt.cond().accept(this, frames);
		if (whileStmt.bodyStmt() != null)
			whileStmt.bodyStmt().accept(this, frames);

		// construct while stmt
		ImcLABEL _start = new ImcLABEL(new MemLabel());
		ImcLABEL _body = new ImcLABEL(new MemLabel());
		ImcLABEL _end = new ImcLABEL(new MemLabel());

		Vector<ImcStmt> stmts = new Vector<ImcStmt>();
		stmts.add(_start);
		stmts.add(new ImcCJUMP(ImcGen.exprImc.get(whileStmt.cond()), _body.label, _end.label));
		stmts.add(_body);
		stmts.add(ImcGen.stmtImc.get(whileStmt.bodyStmt()));
		stmts.add(new ImcJUMP(_start.label));
		stmts.add(_end);

		return ImcGen.stmtImc.put(whileStmt, new ImcSTMTS(stmts));
	}

}
