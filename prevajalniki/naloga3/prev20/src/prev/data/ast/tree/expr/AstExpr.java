package prev.data.ast.tree.expr;

import prev.data.ast.tree.*;

/**
 * Abstract expression.
 */
public interface AstExpr extends AstTree {

	@Override
	public AstExpr clone();

}
