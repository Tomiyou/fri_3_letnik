package prev.phase.seman;

import prev.common.logger.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;

/**
 * Logs semantic attributes of an abstract syntax tree.
 * 
 * (Must be used as a subvisitor of {@link prev.phase.abstr.AbsLogger}.)
 */
public class SemLogger extends AstNullVisitor<Object, String> {

	/** The logger the log should be written to. */
	private final Logger logger;

	/**
	 * Construct a new visitor with a logger the log should be written to.
	 * 
	 * @param logger The logger the log should be written to.
	 */
	public SemLogger(Logger logger) {
		this.logger = logger;
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstCallExpr callExpr, String arg) {
		AstDecl decl = SemAn.declaredAt.get(callExpr);
		if (decl != null) {
			logger.begElement("declaredAt");
			logger.addAttribute("idx", Integer.toString(decl.id()));
			logger.addAttribute("location", decl.location().toString());
			logger.endElement();
		}
		if (callExpr.args() != null)
			callExpr.args().accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, String arg) {
		AstDecl decl = SemAn.declaredAt.get(nameExpr);
		if (decl != null) {
			logger.begElement("declaredAt");
			logger.addAttribute("idx", Integer.toString(decl.id()));
			logger.addAttribute("location", decl.location().toString());
			logger.endElement();
		}
		return null;
	}

	// TYPES


	@Override
	public Object visit(AstNameType nameType, String arg) {
		AstDecl decl = SemAn.declaredAt.get(nameType);
		if (decl != null) {
			logger.begElement("declaredAt");
			logger.addAttribute("idx", Integer.toString(decl.id()));
			logger.addAttribute("location", decl.location().toString());
			logger.endElement();
		}
		return null;
	}

}
