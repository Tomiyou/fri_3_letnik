package prev.data.semtype;

import java.util.*;

import prev.common.logger.*;

/**
 * Record type.
 */
public class SemRecord extends SemType {

	/** Component types. */
	private final SemType[] compTypes;

	/** The size. */
	private final long size;

	public SemRecord(Collection<SemType> compTypes) {
		this.compTypes = new SemType[compTypes.size()];
		int index = 0;
		long size = 0;
		for (SemType compType: compTypes) {
			this.compTypes[index++] = compType;
			size += compType.size();
		}
		this.size = size;
	}
	
	@Override
	public long size() {
		return size;
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
