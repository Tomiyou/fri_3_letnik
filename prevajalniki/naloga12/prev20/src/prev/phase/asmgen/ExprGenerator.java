package prev.phase.asmgen;

import java.util.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.visitor.*;
import prev.data.asm.*;
import prev.Compiler;
import prev.common.report.Report;

/**
 * Machine code generator for expressions.
 */
public class ExprGenerator implements ImcVisitor<MemTemp, Vector<AsmInstr>> {

	// v začasno spremenljivko temp, se zapisuje izhod teh ukazov
	MemTemp temp = null;

	public ExprGenerator() {
		this(new MemTemp());
	}

	public ExprGenerator(MemTemp t) {
		this.temp = t;
	}

	@Override
	public MemTemp visit(ImcCONST constant, Vector<AsmInstr> instructions) {
		Vector<MemTemp> defs = new Vector<>();
		// rezultat zapišemo v temp začasno spremenljivko
		defs.add(temp);


		// če je konstanta negativna, jo rabimo na koncu posebej negirat
		long val = constant.value;
		boolean negative = false;
		if (val < 0) {
			negative = true;
			val = -val;
		}

		long low = val & ((1 << 16) - 1);
		val >>= 16;
		long midLow = val & ((1 << 16) - 1);
		val >>= 16;
		long midHigh = val & ((1 << 16) - 1);
		val >>= 16;
		long high = val & ((1 << 16) - 1);
		val >>= 16;

		instructions.add(new AsmOPER("SETL `d0," + low, null, defs, null));
		if (midLow != 0)
			instructions.add(new AsmOPER("INCML `s0," + midLow, defs, defs, null));
		if (midHigh != 0)
			instructions.add(new AsmOPER("INCMH `s0," + midHigh, defs, defs, null));
		if (high != 0)
			instructions.add(new AsmOPER("INCH `s0," + high, defs, defs, null));

		if (negative)
			instructions.add(new AsmOPER("NEG  `d0,`s0", defs, defs, null));

		return temp;
	}

	@Override
	public MemTemp visit(ImcBINOP binOp, Vector<AsmInstr> instructions) {
		Vector<MemTemp> defs = new Vector<>();
		Vector<MemTemp> uses = new Vector<>();

		// uporabljamo levi in desni izraz, pišemo v temp
		defs.add(temp);
		uses.add(binOp.fstExpr.accept(this, instructions));
		uses.add(binOp.sndExpr.accept(this, instructions));

		String instr = "";
		switch (binOp.oper) {
			case ADD:
				instr = "ADD";
				break;
			case SUB:
				instr = "SUB";
				break;
			case MUL:
				instr = "MUL";
				break;
			case DIV:
				instr = "DIV";
				break;
			case AND:
				instr = "AND";
				break;
			case OR:
				instr = "OR";
				break;
			case MOD:
				// rR je register za remainder pri deljenju
				instructions.add(new AsmOPER("DIV `d0,`s0,`s1", uses, defs, null));
				instructions.add(new AsmOPER("GET `d0,rR", null, defs, null));
				return temp;

			default: {
				// imamo primerjalni ukaz
				// v cmpDefs zapišemo izhod CMP ukaza
				Vector<MemTemp> cmpDefs = new Vector<>();
				cmpDefs.add(new MemTemp());
				instructions.add(new AsmOPER("CMP `d0,`s0,`s1", uses, cmpDefs, null));

				switch (binOp.oper) {
					case EQU:
						instr = "ZSZ";
						break;
					case NEQ:
						instr = "ZSNZ";
						break;

					case LTH:
						instr = "ZSN";
						break;
					case GEQ:
						instr = "ZSNN";
						break;

					case GTH:
						instr = "ZSP";
						break;
					default: // LEQ
						instr = "ZSNP";
						break;
				}

				// cmpDefs je sedaj uses, defs so pa dejanski defs
				instructions.add(new AsmOPER(instr + " `d0,`s0,1", cmpDefs, defs, null));
				return temp;
			}
		}

		instructions.add(new AsmOPER(instr + " `d0,`s0,`s1", uses, defs, null));
		return temp;
	}

	@Override
	public MemTemp visit(ImcCALL call, Vector<AsmInstr> instructions) {
		Vector<MemLabel> jumps = new Vector<>();
		jumps.add(call.label);

		// offset postaja manjši, po stacku se približujemo SP
		int offset = call.args().size() * 8;

		// damo argumente na stack za novo funkcijo
		// Iterate backwards, SL že v args
		ListIterator<ImcExpr> argIter = call.args().listIterator(call.args().size());
		while (argIter.hasPrevious()) {
			ImcExpr expr = argIter.previous();
			offset -= 8;

			// izračunamo vrednost argumenta
			MemTemp argTemp = expr.accept(this, instructions);

			// shranimo vrednost na stack
			Vector<MemTemp> uses = new Vector<>();
			uses.add(argTemp);
			instructions.add(new AsmOPER("STO `s0,$254," + offset, uses, null, null));
		}

		instructions.add(new AsmOPER("PUSHJ $" + Compiler.registerCount + "," + call.label.name, null, null, jumps));

		// naložimo RV (ki je izhod tega ImcCALL izraza)
		Vector<MemTemp> defs = new Vector<MemTemp>();
		defs.add(temp);
		instructions.add(new AsmOPER("LDO `d0,$254,0", null, defs, null));

		return temp;
	}

	@Override
	public MemTemp visit(ImcUNOP unOp, Vector<AsmInstr> instructions) {
		Vector<MemTemp> defs = new Vector<>();
		Vector<MemTemp> uses = new Vector<>();

		// rabimo podizraz za izvedbo operacije nad njem in temp za izhod operacije
		defs.add(temp);
		uses.add(unOp.subExpr.accept(this, instructions));

		switch (unOp.oper) {
			case NEG:
				instructions.add(new AsmOPER("NEG `d0,`s0", uses, defs, null));
				break;
			case NOT:
				// true = 1 -> 1 - 1 = 0, NEG 0 = 0 = false
				// false = 0 -> 0 - 1 = -1, NEG -1 = 1 = true
				instructions.add(new AsmOPER("SUB `d0,`s0,1", uses, defs, null));
				instructions.add(new AsmOPER("NEG `d0,`s0", defs, defs, null));
				return temp;
		}

		return temp;
	}

	@Override
	public MemTemp visit(ImcNAME name, Vector<AsmInstr> instructions) {
		Vector<MemTemp> defs = new Vector<>();
		defs.add(temp);

		// iz pomnilnika naložimo vrednost spremenljivke (label -> LDA)
		instructions.add(new AsmOPER("LDA `d0," + name.label.name, null, defs, null));
		return temp;
	}

	@Override
	public MemTemp visit(ImcTEMP temp, Vector<AsmInstr> instructions) {
		return temp.temp;
	}

	@Override
	public MemTemp visit(ImcMEM mem, Vector<AsmInstr> instructions) {
		Vector<MemTemp> defs = new Vector<>();
		Vector<MemTemp> uses = new Vector<>();

		// rabimo naslov iz katerega nalagamo in temp kamor to zapišemo
		defs.add(temp);
		uses.add(mem.addr.accept(this, instructions));

		// load immediate iz pomnilnika (nek address -> LDO)
		instructions.add(new AsmOPER("LDO `d0,`s0,0", uses, defs, null));
		return temp;
	}

}
