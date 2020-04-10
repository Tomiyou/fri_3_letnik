package prev.data.ast.tree.expr;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.visitor.*;

/**
 * Pointer access expression.
 */
public class AstPtrExpr extends AstNode implements AstExpr {

	/** The pointer. */
	private AstExpr ptr;

	/**
	 * Constructs a pointer access expression.
	 * 
	 * @param location The location.
	 * @param ptr      The pointer.
	 * @param comp     The component.
	 */
	public AstPtrExpr(Location location, AstExpr ptr) {
		super(location);
		this.ptr = ptr;
	}

	/**
	 * Returns the pointer.
	 * 
	 * @return The pointer.
	 */
	public final AstExpr ptr() {
		return ptr;
	}

	@Override
	public AstPtrExpr clone() {
		AstPtrExpr ast = (AstPtrExpr) super.clone();
		ast.ptr = ptr == null ? null : ptr.clone();
		return ast;
	}

	@Override
	public <Result, Arg> Result accept(AstVisitor<Result, Arg> visitor, Arg arg) {
		return visitor.visit(this, arg);
	}

}
