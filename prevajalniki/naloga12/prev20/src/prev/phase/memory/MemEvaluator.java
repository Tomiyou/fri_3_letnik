package prev.phase.memory;

import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;
import prev.data.semtype.*;
import prev.data.mem.*;
import prev.phase.seman.*;

/**
 * Computing memory layout: frames and accesses.
 */
public class MemEvaluator extends AstFullVisitor<Object, MemEvaluator.Context> {

	/**
	 * The context {@link MemEvaluator} uses while computing function frames and
	 * variable accesses.
	 */
	protected abstract class Context {
	}

	/**
	 * Functional context, i.e., used when traversing function and building a new
	 * frame, parameter acceses and variable acceses.
	 */
	private class FunContext extends Context {
		public int depth = 0;
		public long locsSize = 0;
		public long argsSize = 0;
		public long parsSize = new SemPointer(new SemVoid()).size();
	}

	/**
	 * Record context, i.e., used when traversing record definition and computing
	 * record component acceses.
	 */
	private class RecContext extends Context {
		public long compsSize = 0;
	}

	// DECLARATIONS

	@Override
	public Object visit(AstCompDecl compDecl, Context ctx) {
		if (compDecl.type() != null)
			compDecl.type().accept(this, ctx);

		long size = SemAn.isType.get(compDecl.type()).actualType().size();
		RecContext _ctx = (RecContext) ctx;
		MemAccess acc = new MemRelAccess(size, _ctx.compsSize, 0);
		_ctx.compsSize += size;

		return Memory.accesses.put(compDecl, acc);
	}

	@Override
	public Object visit(AstFunDecl funDecl, Context ctx) {
		int depth = ctx == null ? 0 : ((FunContext) ctx).depth + 1;
		FunContext newCtx = new FunContext();
		newCtx.depth = depth;

		if (funDecl.pars() != null)
			funDecl.pars().accept(this, newCtx);
		if (funDecl.type() != null)
			funDecl.type().accept(this, newCtx);
		if (funDecl.expr() != null)
			funDecl.expr().accept(this, newCtx);

		MemLabel label = ctx == null ? new MemLabel(funDecl.name()) : new MemLabel();
		Memory.frames.put(funDecl, new MemFrame(label, depth, newCtx.locsSize, newCtx.argsSize));

		return null;
	}

	@Override
	public Object visit(AstParDecl parDecl, Context ctx) {
		if (parDecl.type() != null)
			parDecl.type().accept(this, ctx);

		FunContext _ctx = (FunContext) ctx;
		long size = SemAn.isType.get(parDecl.type()).actualType().size();
		Memory.accesses.put(parDecl, new MemRelAccess(size, _ctx.parsSize, _ctx.depth));
		_ctx.parsSize += size;
		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Context ctx) {
		if (varDecl.type() != null)
			varDecl.type().accept(this, ctx);

		long size = SemAn.isType.get(varDecl.type()).actualType().size();
		MemAccess acc;
		if (ctx == null) {
			acc = new MemAbsAccess(size, new MemLabel(varDecl.name()));
		} else {
			FunContext _ctx = (FunContext) ctx;
			_ctx.locsSize += size;
			acc = new MemRelAccess(size, -_ctx.locsSize, _ctx.depth);
		}

		return Memory.accesses.put(varDecl, acc);
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstAtomExpr atomExpr, Context ctx) {
		if (atomExpr.type() == AstAtomExpr.Type.STRING) {
			long size = atomExpr.value().length() * 8;
			// long size = SemAn.ofType.get(atomExpr).actualType();
			Memory.strings.put(atomExpr, new MemAbsAccess(size, new MemLabel(), atomExpr.value()));
		}
		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Context ctx) {
		if (callExpr.args() != null)
			callExpr.args().accept(this, ctx);

		long parsSize = 8;
		for (AstExpr expr : callExpr.args()) {
			SemType typ = SemAn.ofType.get(expr).actualType();
			parsSize += typ.size();
		}

		FunContext _ctx = (FunContext) ctx;
		if (_ctx.argsSize < parsSize)
			_ctx.argsSize = parsSize;

		long retSize = SemAn.ofType.get(callExpr).actualType().size();
		if (_ctx.argsSize < retSize)
			_ctx.argsSize = retSize;

		return null;
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Context ctx) {
		switch (pfxExpr.oper()) {
			case ADD:
			case SUB:
			case NOT:
			case PTR:
			break;
			case DEL:
			case NEW:
				FunContext _ctx = (FunContext) ctx;
				if (_ctx.argsSize < 8)
					_ctx.argsSize = 8;
				break;
		}

		return null;
	}

	// TYPES

	@Override
	public Object visit(AstRecType recType, Context ctx) {
		RecContext newCtx = new RecContext();

		if (recType.comps() != null)
			recType.comps().accept(this, newCtx);
		return null;
	}

}
