package prev.data.asm;

import java.util.*;
import prev.data.mem.*;

/**
 * A general assembly operation.
 * 
 * {@code instr} contains the text of the instruction. If a temporary variable
 * is given as a variable, it is replaced by `s0, `s1, etc (if it is a source)
 * or `d0, `d1, etc (if it is a destination). Temporary variables that act as
 * sources must be included in {@code uses}, temporary variables that act as
 * destinations must be included in {@code defs}.
 * 
 * For instance:
 * 
 * ADD T1,T2,T3 => "ADD `d0,`s0,`s1" & uses={T2,T3} & defs={T1}
 * SETL T1,3 => "SETL `d0,3" & uses={} & defs={T1}
 * SETML T1,3 => "SETL `d0,3" & uses={T1} & defs={T1}
 * 
 * @author sliva
 *
 */
public class AsmOPER extends AsmInstr {

	/** The string representation of the instruction. */
	private final String instr;

	/** The list of temporaries used by this instruction. */
	private final Vector<MemTemp> uses;

	/** The list of temporaries defined by this instruction. */
	private final Vector<MemTemp> defs;

	/** The list of labels this instruction can jump to. */
	private final Vector<MemLabel> jumps;

	/**
	 * Constructs a new assembly instruction.
	 * 
	 * @param instr The string representation of the instruction.
	 * @param uses  The list of temporaries used by this instruction.
	 * @param defs  The list of temporaries defined by this instruction.
	 * @param jumps The list of labels this instruction can jump to.
	 */
	public AsmOPER(String instr, Vector<MemTemp> uses, Vector<MemTemp> defs, Vector<MemLabel> jumps) {
		this.instr = instr;
		this.uses = uses == null ? new Vector<MemTemp>() : uses;
		this.defs = defs == null ? new Vector<MemTemp>() : defs;
		this.jumps = jumps == null ? new Vector<MemLabel>() : jumps;
	}

	@Override
	public Vector<MemTemp> uses() {
		return new Vector<MemTemp>(uses);
	}

	@Override
	public Vector<MemTemp> defs() {
		return new Vector<MemTemp>(defs);
	}

	@Override
	public Vector<MemLabel> jumps() {
		return new Vector<MemLabel>(jumps);
	}

	@Override
	public String toString() {
		String instruction = this.instr;
		for (int i = 0; i < uses.size(); i++)
			instruction = instruction.replace("`s" + i, "T" + uses.get(i).temp);
		for (int i = 0; i < defs.size(); i++)
			instruction = instruction.replace("`d" + i, "T" + defs.get(i).temp);
		return instruction;
	}

	@Override
	public String toString(HashMap<MemTemp, Integer> regs) {
		String instruction = this.instr;
		for (int i = 0; i < uses.size(); i++)
			instruction = instruction.replace("`s" + i, "$" + regs.get(uses.get(i)));
		for (int i = 0; i < defs.size(); i++)
			instruction = instruction.replace("`d" + i, "$" + regs.get(defs.get(i)));
		return instruction;
	}

}
