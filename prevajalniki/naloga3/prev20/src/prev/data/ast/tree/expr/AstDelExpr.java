package prev.data.ast.tree.expr;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.visitor.*;

/**
 * Dellocation expression.
 */
public class AstDelExpr extends AstNode implements AstExpr {

	/** The expression. */
	private AstExpr expr;

	/**
	 * Constructs a dellocation expression.
	 * 
	 * @param location The location.
	 * @param expr     The expression.
	 */
	public AstDelExpr(Location location, AstExpr expr) {
		super(location);
		this.expr = expr;
	}

	/**
	 * Returns the expr.
	 * 
	 * @return The expr.
	 */
	public final AstExpr expr() {
		return expr;
	}

	@Override
	public AstDelExpr clone() {
		AstDelExpr ast = (AstDelExpr) super.clone();
		ast.expr = expr == null ? null : expr.clone();
		return ast;
	}

	@Override
	public <Result, Arg> Result accept(AstVisitor<Result, Arg> visitor, Arg arg) {
		return visitor.visit(this, arg);
	}

}
