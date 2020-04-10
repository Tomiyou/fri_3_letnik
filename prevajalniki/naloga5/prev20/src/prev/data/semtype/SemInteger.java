package prev.data.semtype;

import prev.common.logger.*;

/**
 * Type {@code integer}.
 */
public class SemInteger extends SemType {

	@Override
	public void log(Logger logger) {
		if (logger == null)
			return;
		logger.begElement("semtype");
		logger.addAttribute("type", "INTEGER");
		logger.endElement();
	}

}
