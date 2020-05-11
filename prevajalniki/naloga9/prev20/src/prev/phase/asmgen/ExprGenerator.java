package prev.phase.asmgen;

import java.util.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.visitor.*;
import prev.data.asm.*;

/**
 * Machine code generator for expressions.
 */
public class ExprGenerator implements ImcVisitor<MemTemp, Vector<AsmInstr>> {

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
		defs.add(temp);

		long val = constant.value;
		if (val < 0)
			val = -val;
		boolean negative = val < 0 ? true : false;

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
				instructions.add(new AsmOPER("DIV `d0,`s0,`s1", uses, defs, null));
				instructions.add(new AsmOPER("GET `d0,rR", null, defs, null));
				return temp;

			default: {
				Vector<MemTemp> cmpDefs = new Vector<>();
				cmpDefs.add(new MemTemp());

				instructions.add(new AsmOPER("CMP `d0,`s0,`s1", uses, cmpDefs, null));

				switch (binOp.oper) {
					case EQU:
						instructions.add(new AsmOPER("ZSZ `d0,`s0,1", cmpDefs, defs, null));
						break;
					case NEQ:
						instructions.add(new AsmOPER("ZSNZ `d0,`s0,1", cmpDefs, defs, null));
						break;

					case LTH:
						instructions.add(new AsmOPER("ZSN `d0,`s0,1", cmpDefs, defs, null));
						break;
					case GEQ:
						instructions.add(new AsmOPER("ZSNN `d0,`s0,1", cmpDefs, defs, null));
						break;

					case GTH:
						instructions.add(new AsmOPER("ZSP `d0,`s0,1", cmpDefs, defs, null));
						break;
					case LEQ:
						instructions.add(new AsmOPER("ZSNP `d0,`s0,1", cmpDefs, defs, null));
						break;

					default:
						System.out.println("[ExprGen]" + binOp.oper + " does not exist");
				}

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

		int offset = call.args().size() * 8;

		// Iterate backwards, SL already in args
		ListIterator<ImcExpr> argIter = call.args().listIterator(call.args().size());
		while (argIter.hasPrevious()) {
			ImcExpr expr = argIter.previous();
			offset -= 8;

			MemTemp argTemp = expr.accept(this, instructions);

			Vector<MemTemp> uses = new Vector<>();
			uses.add(argTemp);
			instructions.add(new AsmOPER("STO `s0,$254," + offset, uses, null, null));
		}

		instructions.add(new AsmOPER("PUSHJ $16," + call.label.name, null, null, jumps));

		// Return value
		Vector<MemTemp> defs = new Vector<MemTemp>();
		defs.add(temp);
		instructions.add(new AsmOPER("LDO `d0,$254,0", null, defs, null));

		return temp;
	}

	@Override
	public MemTemp visit(ImcUNOP unOp, Vector<AsmInstr> instructions) {
		Vector<MemTemp> defs = new Vector<>();
		Vector<MemTemp> uses = new Vector<>();

		defs.add(temp);
		uses.add(unOp.subExpr.accept(this, instructions));

		switch (unOp.oper) {
			case NEG:
				instructions.add(new AsmOPER("NEG `d0,`s0", uses, defs, null));
				break;
			case NOT:
				instructions.add(new AsmOPER("SUB `d0,`s0,1", uses, defs, null));
				instructions.add(new AsmOPER("NEG `d0,`s0", defs, defs, null));
				return temp;
		}

		return temp;
	}

	@Override
	public MemTemp visit(ImcNAME name, Vector<AsmInstr> instructions) {
		Vector<MemTemp> uses = new Vector<>();
		Vector<MemTemp> defs = new Vector<>();

		uses.add(new MemTemp());
		defs.add(temp);

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

		defs.add(temp);
		uses.add(mem.addr.accept(this, instructions));

		instructions.add(new AsmOPER("LDO `d0,`s0,0", uses, defs, null));
		return temp;
	}

}
