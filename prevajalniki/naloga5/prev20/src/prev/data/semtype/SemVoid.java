package prev.data.semtype;

import prev.common.logger.*;

/**
 * Type {@code void}.
 */
public class SemVoid extends SemType {
	
	@Override
	public void log(Logger logger) {
		if (logger == null)
			return;
		logger.begElement("semtype");
		logger.addAttribute("type", "VOID");
		logger.endElement();
	}

}
