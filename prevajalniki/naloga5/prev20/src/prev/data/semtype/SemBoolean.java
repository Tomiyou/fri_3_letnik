package prev.data.semtype;

import prev.common.logger.*;

/**
 * Type {@code boolean}.
 */
public class SemBoolean extends SemType {
	
	@Override
	public void log(Logger logger) {
		if (logger == null)
			return;
		logger.begElement("semtype");
		logger.addAttribute("type", "BOOLEAN");
		logger.endElement();
	}

}
