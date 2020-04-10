package prev.phase.seman;

import prev.phase.*;
import prev.data.ast.attribute.*;
import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;


/**
 * Semantic analysis.
 */
public class SemAn extends Phase {

	// === STATIC ===

	/** Maps names to declarations. */
	public static final AstAttribute<AstName, AstDecl> declaredAt = new AstAttribute<AstName, AstDecl>(0);
	
	// ==============

	/**
	 * Phase construction.
	 */
	public SemAn() {
		super("seman");
	}

}
