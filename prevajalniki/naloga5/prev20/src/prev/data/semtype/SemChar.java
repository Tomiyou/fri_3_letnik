package prev.data.semtype;

import prev.common.logger.*;

/**
 * Type {@code char}.
 */
public class SemChar extends SemType {
	
	@Override
	public void log(Logger logger) {
		if (logger == null)
			return;
		logger.begElement("semtype");
		logger.addAttribute("type", "CHAR");
		logger.endElement();
	}

}
