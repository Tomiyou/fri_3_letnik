package prev.phase.regall;

import java.util.*;

import prev.data.mem.*;
import prev.common.report.Report;
import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;
import prev.phase.livean.LiveAn;
import prev.Compiler;

/**
 * Register allocation.
 */
public class RegAll extends Phase {

	public int R = 100;
	HashMap<MemTemp, Vozlisce> seznamVozlisc;
	MemTemp framePointer;

	/** Mapping of temporary variables to registers. */
	public final HashMap<MemTemp, Integer> tempToReg = new HashMap<MemTemp, Integer>();

	public RegAll() {
		super("regall");
		R = Compiler.registerCount;
	}

	public void allocate() {
		for (Code code : AsmGen.codes) {
			framePointer = code.frame.FP;

			while (true) {
				seznamVozlisc = new HashMap<>();
				Graph<MemTemp> graf = build(code.instrs, seznamVozlisc);

				Stack<MemTemp> sklad = new Stack<>();
				while (true) {
					simplify(graf, sklad);
					if (!spill(graf, sklad))
						break;
				}

				graf = build(code.instrs, seznamVozlisc);
				MemTemp preliv = select(graf, sklad);

				if (preliv != null) {
					// System.out.println("Preliv: " + preliv);

					code.tempSize += 8;
					long offset = 0 - (code.tempSize + code.frame.locsSize + 16);

					for (int i = 0; i < code.instrs.size(); i++) {
						AsmInstr instr = code.instrs.get(i);

						int tempReplaceIdx = instr.uses().indexOf(preliv);
						if (tempReplaceIdx != -1) {
							MemTemp newTemp = new MemTemp();
							MemTemp tempReplace = new MemTemp();

							if (instr instanceof AsmOPER) {
								// SET instruction
								Vector<MemTemp> setlDefs = new Vector<>();
								setlDefs.add(newTemp);
								AsmOPER setInstr = new AsmOPER("SETL `d0," + offset, null, setlDefs, null);
								code.instrs.add(i++, setInstr);

								// LDO instruction
								Vector<MemTemp> ldoUses = new Vector<>();
								ldoUses.add(framePointer);
								ldoUses.add(newTemp);
								Vector<MemTemp> ldoDefs = new Vector<>();
								ldoDefs.add(tempReplace);
								AsmOPER ldoInstr = new AsmOPER("LDO `d0,`s0,`s1", ldoUses, ldoDefs, null);
								code.instrs.add(i++, ldoInstr);

								// ORIGINAL instruction
								Vector<MemTemp> uses = instr.uses();
								uses.set(tempReplaceIdx, tempReplace);
								AsmOPER newInstr = new AsmOPER(((AsmOPER) instr).instr(), uses, instr.defs(),
										instr.jumps());
								code.instrs.set(i, newInstr);

								instr = newInstr;
							} else {
								throw new Report.Error("Ukaz za popravljanje NI AsmOPER!");
							}
						}

						tempReplaceIdx = instr.defs().indexOf(preliv);
						if (tempReplaceIdx != -1) {
							MemTemp tempReplace = new MemTemp();
							MemTemp newTemp = new MemTemp();

							if (instr instanceof AsmOPER) {
								// ORIGINAL instruction
								Vector<MemTemp> defs = instr.defs();
								defs.set(tempReplaceIdx, tempReplace);
								AsmOPER newInstr = new AsmOPER(((AsmOPER) instr).instr(), instr.uses(), defs,
										instr.jumps());
								code.instrs.set(i++, newInstr);

								// SET instruction
								Vector<MemTemp> setlDefs = new Vector<>();
								setlDefs.add(newTemp);
								AsmOPER setInstr = new AsmOPER("SETL `d0," + offset, null, setlDefs, null);
								code.instrs.add(i++, setInstr);

								// STO instruction
								Vector<MemTemp> stoUses = new Vector<>();
								stoUses.add(tempReplace);
								stoUses.add(framePointer);
								stoUses.add(newTemp);
								AsmOPER stoInstr = new AsmOPER("STO `s0,`s1,`s2", stoUses, null, null);
								code.instrs.add(i, stoInstr);
							} else {
								throw new Report.Error("Ukaz za popravljanje NI AsmOPER");
							}
						}
					}

					LiveAn l = new LiveAn();
					l.analysis();
				} else {
					break;
				}

			}

			for (MemTemp temp : seznamVozlisc.keySet()) {
				tempToReg.put(temp, seznamVozlisc.get(temp).barva);
			}
		}

	}

	private MemTemp select(Graph<MemTemp> graf, Stack<MemTemp> sklad) {

		while (!sklad.empty()) {
			MemTemp t = sklad.pop();
			Vozlisce v = seznamVozlisc.get(t);
			boolean[] barve = new boolean[R];

			// pregledamo a so sosedi že pobarvani
			for (MemTemp sosed : graf.vrniSosede(t)) {
				// frame pointer ima cel svoj register
				if (sosed == framePointer)
					continue;

				Vozlisce _sosed = seznamVozlisc.get(sosed);
				if (_sosed.barva >= 0)
					barve[_sosed.barva] = true;
			}

			// frame pointer ima svoj register == svojo barvo
			if (t == framePointer) {
				v.barva = 253;
			} else {
				for (int i = 0; i < R; i++) {
					// poskušamo najti neuporabljeno barvo
					if (barve[i] == false) {
						v.barva = i;
						break;
					}
				}
			}

			if (v.barva >= 0) {
				v.spill = false;
			} else {
				return t;
			}
		}

		return null;

	}

	private boolean spill(Graph<MemTemp> graf, Stack<MemTemp> sklad) {
		MemTemp temp = graf.vrniNajvecjoStopnjo();

		if (temp == null)
			return false;

		graf.odstraniVozlisce(temp);
		sklad.push(temp);
		seznamVozlisc.get(temp).spill = true;

		return true;
	}

	private Stack<MemTemp> simplify(Graph<MemTemp> graf, Stack<MemTemp> sklad) {
		boolean zanka = true;

		while (zanka) {
			zanka = false;

			// gremo čez cel graf in odstranimo vsa vozlišča s stopnjo < R
			Iterator<MemTemp> it = graf.vrniIterator();
			while (it.hasNext()) {
				MemTemp t = it.next();

				if (graf.vrniSosede(t).size() < R) {
					graf.odstraniVozlisce(t, it);
					sklad.push(t);
					zanka = true;
				}
			}
		}

		return sklad;
	}

	public Graph<MemTemp> build(Vector<AsmInstr> instrs, HashMap<MemTemp, Vozlisce> seznamVozlisc) {
		Graph<MemTemp> graf = new Graph<>();

		for (AsmInstr instr : instrs) {
			// najprej dodamo vse oute
			HashSet<MemTemp> out = instr.out();

			for (MemTemp temp : out) {
				Vozlisce v = seznamVozlisc.get(temp);
				graf.addVertex(temp);

				// če ne obstaja, ga dodamo med seznam vozlišč
				if (v == null) {
					v = new Vozlisce(temp);
					seznamVozlisc.put(temp, v);
				}

				// dodamo v graf to vozlišč in vse njegove povezave
				for (MemTemp tempovSosed : out) {
					if (tempovSosed == temp)
						continue;

					graf.dodajSoseda(temp, tempovSosed);
				}
			}

			// potem dodamo vse defse
			Vector<MemTemp> defs = instr.defs();

			for (MemTemp temp : defs) {
				Vozlisce v = seznamVozlisc.get(temp);
				graf.addVertex(temp);

				// če ne obstaja, ga dodamo med seznam vozlišč
				if (v == null) {
					v = new Vozlisce(temp);
					seznamVozlisc.put(temp, v);
				}

				// dodamo v graf to vozlišč in vse njegove povezave
				for (MemTemp tempovSosed : defs) {
					if (tempovSosed == temp)
						continue;

					graf.dodajSoseda(temp, tempovSosed);
				}
			}
		}

		return graf;
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
				logger.addAttribute("code", instr.toString(tempToReg));
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

class Vozlisce {

	boolean spill;
	int barva;

	public Vozlisce(MemTemp t) {
		spill = false;
		barva = -1;
	}
}