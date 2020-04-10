package prev.data.ast.tree.expr;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;

/**
 * Allocation expression.
 */
public class AstNewExpr extends AstNode implements AstExpr {

	/** The type. */
	private AstType type;

	/**
	 * Constructs an allocation expression.
	 * 
	 * @param location The location.
	 * @param type     The type.
	 */
	public AstNewExpr(Location location, AstType type) {
		super(location);
		this.type = type;
	}

	/**
	 * Returns the type.
	 * 
	 * @return The type.
	 */
	public final AstType type() {
		return type;
	}

	@Override
	public AstNewExpr clone() {
		AstNewExpr ast = (AstNewExpr) super.clone();
		ast.type = type == null ? null : type.clone();
		return ast;
	}

	@Override
	public <Result, Arg> Result accept(AstVisitor<Result, Arg> visitor, Arg arg) {
		return visitor.visit(this, arg);
	}

}
