package prev.data.semtype;

import java.util.*;

import prev.common.logger.*;

/**
 * Record type.
 */
public class SemRecord extends SemType {

	/** Component types. */
	private final SemType[] compTypes;

	public SemRecord(Collection<SemType> compTypes) {
		this.compTypes = new SemType[compTypes.size()];
		int index = 0;
		for (SemType compType: compTypes)
			this.compTypes[index++] = compType;
	}

	@Override
	public void log(Logger logger) {
		if (logger == null)
			return;
		logger.begElement("semtype");
		logger.addAttribute("type", "RECORD");
		for (SemType compType: compTypes)
			compType.log(logger);
		logger.endElement();
	}

}
