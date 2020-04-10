package prev.data.ast.tree.stmt;

import prev.data.ast.tree.*;

/**
 * Abstract statement.
 */
public interface AstStmt extends AstTree {

	@Override
	public AstStmt clone();

}
