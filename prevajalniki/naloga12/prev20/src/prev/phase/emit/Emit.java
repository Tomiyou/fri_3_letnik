package prev.phase.emit;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import prev.data.asm.*;
import prev.data.lin.LinDataChunk;
import prev.phase.*;
import prev.phase.asmgen.*;
import prev.phase.imclin.ImcLin;
import prev.phase.regall.RegAll;
import prev.Compiler;

/**
 * Register allocation.
 */
public class Emit extends Phase {

	StringBuilder _assembly;

	public Emit() {
		super("regall");
	}

	public void appendLine(String line) {
		_assembly.append(line);
		_assembly.append('\n');
	}

	public void append(String line) {
		_assembly.append(line);
	}

	public void emit() {
		_assembly = new StringBuilder("");

		// nastavi globalne registre
		appendLine("\tLOC\t#100"); // tko je v primeru za en simpl assembly
		appendLine("\tGREG\t@"); // SP = $254
		appendLine("D254\tOCTA\t0");
		appendLine("\tGREG\t@"); // FP = $253
		appendLine("D253\tOCTA\t0");
		appendLine("\tGREG\t@"); // HP = $252
		appendLine("D252\tOCTA\t0");

		// IO buffers
		appendLine("\tGREG\t@");
		appendLine("charPrint	BYTE	0,0");

		appendLine("inCount	IS	#1");
		appendLine("InArgs	OCTA	charRead,inCount");
		appendLine("charRead	BYTE	0");

		// emit statične spremenljivke in stringe
		for (LinDataChunk chunk : ImcLin.dataChunks()) {
			if (chunk.init == null) {
				// non-string
				long size = chunk.size - 8;
				String instr = chunk.label.name + "\tOCTA\t0";

				for (; size > 0; size -= 8) {
					instr += ",0";
				}

				appendLine(instr);
			} else {
				// string
				// Greeting BYTE "Hello World!",0 -- direkt iz refcard
				appendLine(chunk.label.name + "\tOCTA\t" + chunk.init + ",0");
			}
		}

		appendLine("Main\tSET $0,#FFFF");
		appendLine("\tSETH $252,#3000"); // HP
		appendLine("\tSETH $254,#3FFF"); // SP
		appendLine("\tINCMH $254,#FFFF");
		appendLine("\tINCML $254,#FFFF");
		appendLine("\tINCL $254,#FFFF");

		// jump na main rutino
		appendLine("\tPUSHJ $0,_main"); // ne pushamo nobenega registra

		// halt po izhodu main rutine
		appendLine("\tTRAP 0,Halt,0");

		// emit prolog + jedro funkcije + epilog
		for (Code code : AsmGen.codes) {
			String fnName = code.frame.label.name;
			if (fnName.equals("_putChar") || fnName.equals("_getChar") || fnName.equals("_exit"))
				continue;

			appendLine("\n\n% fun: " + code.frame.label.name);
			appendLine("% - Prologue");

			appendLine(code.frame.label.name + "\tSET $0,$254");
			appendLine("\tSET $1," + (code.frame.locsSize + 8));
			appendLine("\tSUB $0,$0,$1"); // SP - locsSize
			appendLine("\tSTO $253,$0,0");

			appendLine("\tSUB $0,$0," + 8);
			appendLine("\tGET $1,rJ");
			appendLine("\tSTO $1,$0,0");

			appendLine("\tSET $253,$254");
			appendLine("\tSET $0," + (code.frame.size + code.tempSize));
			appendLine("\tSUB $254,$254,$0");

			appendLine("\tJMP " + code.entryLabel.name);
			appendLine("% - Main");

			for (AsmInstr instr : code.instrs) {
				if (instr instanceof AsmLABEL) {
					append(((AsmLABEL) instr).label.name);
				} else {
					appendLine("\t" + instr.toString(RegAll.tempToReg));
				}
			}

			appendLine("% - Epilogue");
			appendLine(code.exitLabel.name + "\tSTO $0,$253,0"); // set RV

			appendLine("\tSET $0,$253"); // $0 = FP
			appendLine("\tSET $1," + (code.frame.locsSize + 8)); // $1 = odmik za OLD FP
			appendLine("\tSUB $0,$0,$1"); // $0 = FP - omdik = OLD FP address
			appendLine("\tLDO $1,$0,0"); // $1 = OLD FP
			appendLine("\tSET $254,$253"); // SP = FP
			appendLine("\tSET $253,$1"); // FP = OLD FP

			// Restore rJ
			appendLine("\tSUB $0,$0," + 8); // OLD FP address - 8 = RA address
			appendLine("\tLDO $1,$0,0"); // $1 = RA address
			appendLine("\tPUT rJ,$1");

			appendLine("\tPOP");

			appendLine("\n");
		}

		// putChar
		appendLine("_putChar	LDO	$0,$254,8"); // nalozimo arg 1 v reg $0
		appendLine("	LDA	$255,charPrint"); // nalozimo naslov labele v reg $255
		appendLine("	STB $0,$255,0"); // zapisemo arg 1 na labelo
		appendLine("	TRAP	0,Fputs,StdOut");
		appendLine("	POP");

		// getChar
		appendLine("_getChar	SET $0,$254");
		appendLine("	SET $1,8");
		appendLine("	SUB $0,$0,$1");
		appendLine("	STO $253,$0,0");
		appendLine("	SUB $0,$0,8");
		appendLine("	GET $1,rJ");
		appendLine("	STO $1,$0,0");
		appendLine("	SET $253,$254");
		appendLine("	SET $0,16");
		appendLine("	SUB $254,$254,$0");
		appendLine("	LDA	$255,InArgs");
		appendLine("	TRAP	0,Fread,StdIn");
		appendLine("	LDA	$255,charRead");
		appendLine("	LDB	$0,$255,0");
		appendLine("	STO $0,$253,0");
		appendLine("	SET $0,$253");
		appendLine("	SET $1,8");
		appendLine("	SUB $0,$0,$1");
		appendLine("	LDO $1,$0,0");
		appendLine("	SET $254,$253");
		appendLine("	SET $253,$1");
		appendLine("	SUB $0,$0,8");
		appendLine("	LDO $1,$0,0");
		appendLine("	PUT rJ,$1");
		appendLine("	POP");

		// exit
		appendLine("_exit\tTRAP 0,Halt,0");

		// new
		appendLine("_new	LDO	$0,$254,0"); // nalozimo velikost malloca iz SP
		appendLine("	STO	$252,$254,0"); // na SP zapišemo vrednost HP
		appendLine("	ADD	$252,$252,$0"); // seštejemo HP in malloc size
		appendLine("	POP");

		// del
		appendLine("_del	POP");

		try {
			String filename = Compiler.cmdLineArgValue("--src-file-name");
			if (filename.contains("."))
				filename = filename.substring(0, filename.lastIndexOf('.'));
			PrintWriter writer = new PrintWriter(filename + ".mms", "UTF-8");
			writer.write(_assembly.toString());
			writer.flush();
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
