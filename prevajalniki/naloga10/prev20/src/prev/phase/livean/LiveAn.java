package prev.phase.livean;

import prev.data.mem.*;

import java.util.*;

import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;

/**
 * Liveness analysis.
 */
public class LiveAn extends Phase {

	public LiveAn() {
		super("livean");
	}

	public void analysis() {
		HashMap<MemLabel, AsmInstr> labels = new HashMap<>();

		for (Code code : AsmGen.codes) {
			for (int i = 0; i < code.instrs.size(); i++) {
				AsmInstr instr = code.instrs.get(i);
				if (instr instanceof AsmLABEL) {
					labels.put(((AsmLABEL) instr).label, code.instrs.get(i + 1));
				}
			}
		}

		for (Code code : AsmGen.codes) {
			boolean zanka = true;

			while (zanka) {
				zanka = false;

				for (int i = 0; i < code.instrs.size(); i++) {
					AsmInstr instr = code.instrs.get(i);

					HashSet<MemTemp> oldIn = instr.in();
					HashSet<MemTemp> oldOut = instr.out();
					
					HashSet<MemTemp> in = new HashSet<MemTemp>(instr.uses());
					HashSet<MemTemp> out = instr.out();

					out.removeAll(instr.defs()); // out(n) minus def(n)
					in.addAll(out); // use(n) union [ out(n) minus def(n) ]
					instr.addInTemps(in);
					out.clear();

					Vector<AsmInstr> nexts = new Vector<>();

					// ali ima ukaz skok
					if (instr.jumps() != null) {
						// dodamo vse ukaze, ki so po jump labelah
						for (MemLabel jmpLabel : instr.jumps()) {
							AsmInstr instrAfterJump = labels.get(jmpLabel);
							if (instrAfterJump != null) { // lahko je jump na labelo, ki še ni v kodi (epilog)
								nexts.add(instrAfterJump);
							}
						}

						// ce uses != null, potem je to conditional jump, naslednik je tudi naslednji ukaz
						if (instr.uses() != null && code.instrs.size() > i + 1) {
							nexts.add(code.instrs.get(i + 1));
						}
					} else if (code.instrs.size() > i + 1) {
						// ni skok, naslednik je samo naslednji ukaz
						nexts.add(code.instrs.get(i + 1));
					}

					// out(n) = union_{ n' = naslednik n-ja } [ in(n') ]
					for (AsmInstr nextIn : nexts) {
						out.addAll(nextIn.in());
					}

					instr.addOutTemp(out);

					// zanka je true, dokler se usi nehajo spremenijat
					if (!(in.containsAll(oldIn) && oldIn.containsAll(in) && out.containsAll(oldOut) && oldOut.containsAll(out))) {
						zanka = true;
					}
					
				}
			}
			
		}

	}

	public void log() {
		if (logger == null)
			return;
		for (Code code : AsmGen.codes) {
			logger.begElement("code");
			logger.addAttribute("entrylabel", code.entryLabel.name);
			logger.addAttribute("exitlabel", code.exitLabel.name);
			logger.addAttribute("tempsize", Long.toString(code.tempSize));
			code.frame.log(logger);
			logger.begElement("instructions");
			for (AsmInstr instr : code.instrs) {
				logger.begElement("instruction");
				logger.addAttribute("code", instr.toString());
				logger.begElement("temps");
				logger.addAttribute("name", "use");
				for (MemTemp temp : instr.uses()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "def");
				for (MemTemp temp : instr.defs()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "in");
				for (MemTemp temp : instr.in()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "out");
				for (MemTemp temp : instr.out()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.endElement();
			}
			logger.endElement();
			logger.endElement();
		}
	}

}
