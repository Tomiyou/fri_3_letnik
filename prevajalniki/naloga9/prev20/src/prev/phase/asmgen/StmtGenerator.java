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
		Vector<AsmInstr> rets = new Vector<AsmInstr>();
		rets.add(new AsmLABEL(label.label));
		return rets;
	}

	@Override
	public Vector<AsmInstr> visit(ImcCJUMP cjump, Object visArg) {
		Vector<AsmInstr> rets = new Vector<AsmInstr>();
		MemLabel posJUmp = cjump.posLabel;
		Vector<MemLabel> jumps = new Vector<>();
		jumps.add(posJUmp);
		
		Vector<MemTemp> uses = new Vector<>();
		uses.add(cjump.cond.accept(new ExprGenerator(), rets));
		rets.add(new AsmOPER("BNZ `s0," + posJUmp.name, uses,  null, jumps));
		
		return rets;
	}
	
	@Override
	public Vector<AsmInstr> visit(ImcESTMT eStmt, Object visArg) {
		Vector<AsmInstr> rets = new Vector<AsmInstr>();
		eStmt.expr.accept(new ExprGenerator(), rets);
		return rets;
	}
	
	@Override
	public Vector<AsmInstr> visit(ImcMOVE move, Object visArg) {
		Vector<AsmInstr> rets = new Vector<AsmInstr>();
		Vector<MemTemp> uses = new Vector<>();
		Vector<MemTemp> defs = new Vector<>();
		
		ImcExpr leftExpr = move.dst;
		if (leftExpr instanceof ImcMEM) {
			ImcMEM memDst = (ImcMEM) move.dst;
			uses.add(move.src.accept(new ExprGenerator(), rets));
			uses.add(memDst.addr.accept(new ExprGenerator(), rets));
			rets.add(new AsmOPER("STO `s0,`s1,0", uses, null, null));
		} else {
			if (move.dst instanceof ImcTEMP && move.src instanceof ImcTEMP) {
				defs.add(move.dst.accept(new ExprGenerator(), rets));
				uses.add(move.src.accept(new ExprGenerator(), rets));
				rets.add(new AsmMOVE("SET `d0,`s0", uses, defs));
			} else {
				MemTemp temp = move.dst.accept(new ExprGenerator(), rets);
				uses.add(move.src.accept(new ExprGenerator(temp), rets));
			}
		}
		
		return rets;
	}
	
	public Vector<AsmInstr> visit(ImcJUMP jump, Object visArg) {
		Vector<AsmInstr> rets = new Vector<AsmInstr>();
		Vector<MemLabel> jumps = new Vector<>();
		jumps.add(jump.label);	
		rets.add(new AsmOPER("JMP " + jump.label.name, null,  null, jumps));
		return rets;
	}

}
