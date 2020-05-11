package prev.phase.asmgen;

import java.util.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.imc.visitor.*;
import prev.data.mem.*;
import prev.data.asm.*;

public class StmtGenerator implements ImcVisitor<Vector<AsmInstr>, Object> {
	
	@Override
	public Vector<AsmInstr> visit(ImcLABEL label, Object visArg) {
		Vector<AsmInstr> instructions = new Vector<AsmInstr>();
		instructions.add(new AsmLABEL(label.label));
		return instructions;
	}

	@Override
	public Vector<AsmInstr> visit(ImcCJUMP cjump, Object visArg) {
		Vector<AsmInstr> instructions = new Vector<AsmInstr>();

		// dodamo jump na pozitivno labelo
		Vector<MemLabel> jumps = new Vector<>();
		MemLabel posJUmp = cjump.posLabel;
		jumps.add(posJUmp);
		
		Vector<MemTemp> uses = new Vector<>();
		// uporabimo vrednost pogoja (vrednost začasne spremenljivke), 
		uses.add(cjump.cond.accept(new ExprGenerator(), instructions));
		// Branch Not Zero (1 == pogoj drži)
		instructions.add(new AsmOPER("BNZ `s0," + posJUmp.name, uses,  null, jumps));
		
		return instructions;
	}
	
	@Override
	public Vector<AsmInstr> visit(ImcESTMT eStmt, Object visArg) {
		Vector<AsmInstr> instructions = new Vector<AsmInstr>();
		eStmt.expr.accept(new ExprGenerator(), instructions);
		return instructions;
	}
	
	@Override
	public Vector<AsmInstr> visit(ImcMOVE move, Object visArg) {
		Vector<AsmInstr> instructions = new Vector<AsmInstr>();
		Vector<MemTemp> uses = new Vector<>();
		Vector<MemTemp> defs = new Vector<>();
		
		if (move.dst instanceof ImcMEM) {
			// shranimo nekaj v RAM
			ImcMEM memDst = (ImcMEM) move.dst;
			// vrednost v začasni spremenljivki s0, zapišemo na naslov, ki je v začasni spremenljivki s1
			uses.add(move.src.accept(new ExprGenerator(), instructions));
			uses.add(memDst.addr.accept(new ExprGenerator(), instructions));
			instructions.add(new AsmOPER("STO `s0,`s1,0", uses, null, null));
		} else {
			if (move.dst instanceof ImcTEMP && move.src instanceof ImcTEMP) {
				// vrednost ene začasne spremenljivke prepišemo v drugo začasno spremenljivko
				defs.add(move.dst.accept(new ExprGenerator(), instructions));
				uses.add(move.src.accept(new ExprGenerator(), instructions));
				instructions.add(new AsmMOVE("SET `d0,`s0", uses, defs));
			} else {
				// acceptamo move destination in začasno spremenljivko, ki jo dobimo, damo kot lokacijo,
				// kamor naj move source zapiše vrednost
				MemTemp temp = move.dst.accept(new ExprGenerator(), instructions);
				uses.add(move.src.accept(new ExprGenerator(temp), instructions));
			}
		}
		
		return instructions;
	}
	
	public Vector<AsmInstr> visit(ImcJUMP jump, Object visArg) {
		Vector<AsmInstr> instructions = new Vector<AsmInstr>();
		Vector<MemLabel> jumps = new Vector<>();

		jumps.add(jump.label);
		instructions.add(new AsmOPER("JMP " + jump.label.name, null,  null, jumps));

		return instructions;
	}

}
