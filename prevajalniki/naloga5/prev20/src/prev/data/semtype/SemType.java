package prev.data.semtype;

import prev.common.logger.*;

/**
 * An abstract class for representing types.
 */
public abstract class SemType implements Loggable {

	/**
	 * Returns the actual type (not a synonym).
	 * 
	 * @return The actual type (not a synonym).
	 */
	public SemType actualType() {
		return this;
	}

}
