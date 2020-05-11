package prev.phase.seman;

import prev.data.ast.visitor.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.common.report.*;

/**
 * Address resolver.
 * 
 * The address resolver finds out which expressions denote lvalues and leaves
 * the information in {@link SemAn#isAddr}.
 */
public class AddrResolver extends AstFullVisitor<Object, Integer> {

	// GENERAL PURPOSE
	Report.Error Error(String msg, Locatable loc) {
		return new Report.Error("Error (" + loc.location() + "): " + msg);
	}

	@Override
	public Object visit(AstArrExpr arrExpr, Integer arg) {
		if (arrExpr.arr() != null)
			arrExpr.arr().accept(this, arg);
		if (arrExpr.idx() != null)
			arrExpr.idx().accept(this, arg);

		if (SemAn.isAddr.get(arrExpr.arr())) {
			SemAn.isAddr.put(arrExpr, true);
		} else {
			throw Error("Array access not addressable.", arrExpr);
		}
			
		return null;
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Integer arg) {
		SemAn.isAddr.put(atomExpr, false);
		return null;
	}

	@Override
	public Object visit(AstBinExpr binExpr, Integer arg) {
		SemAn.isAddr.put(binExpr, false);
		if (binExpr.fstExpr() != null)
			binExpr.fstExpr().accept(this, arg);
		if (binExpr.sndExpr() != null)
			binExpr.sndExpr().accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Integer arg) {
		SemAn.isAddr.put(callExpr, false);
		if (callExpr.args() != null)
			callExpr.args().accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstCastExpr castExpr, Integer arg) {
		SemAn.isAddr.put(castExpr, false);
		if (castExpr.expr() != null)
			castExpr.expr().accept(this, arg);
		if (castExpr.type() != null)
			castExpr.type().accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Integer arg) {
		AstMemDecl varDecl = (AstMemDecl) SemAn.declaredAt.get(nameExpr);
		if (varDecl != null) {
			// reference to a variable
			SemAn.isAddr.put(nameExpr, true);
		} else {
			
			// reference to a field
		}
		
		return null;
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Integer arg) {
		SemAn.isAddr.put(pfxExpr, false);
		if (pfxExpr.expr() != null)
			pfxExpr.expr().accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstRecExpr recExpr, Integer arg) {
		if (recExpr.rec() != null)
			recExpr.rec().accept(this, arg);
		if (recExpr.comp() != null)
			recExpr.comp().accept(this, arg);

		if (SemAn.isAddr.get(recExpr.rec())) {
			SemAn.isAddr.put(recExpr, true);
		} else {
			throw Error("Record not addressable.", recExpr);
		}

		return null;
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Integer arg) {
		if (sfxExpr.expr() != null)
			sfxExpr.expr().accept(this, arg);

		// no need to check if isType is instanceof SemPointer, already checked by type resolver
		SemAn.isAddr.put(sfxExpr, true);
		return null;
	}

	@Override
	public Object visit(AstStmtExpr stmtExpr, Integer arg) {
		SemAn.isAddr.put(stmtExpr, false);
		if (stmtExpr.stmts() != null)
			stmtExpr.stmts().accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstWhereExpr whereExpr, Integer arg) {
		SemAn.isAddr.put(whereExpr, false);
		if (whereExpr.expr() != null)
			whereExpr.expr().accept(this, arg);
		if (whereExpr.decls() != null)
			whereExpr.decls().accept(this, arg);
		return null;
	}

}