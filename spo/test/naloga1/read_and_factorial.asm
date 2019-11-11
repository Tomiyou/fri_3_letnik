tolong		START	0
first		JSUB	stackinit
		JSUB	readnum
		JSUB	echonum

halt		J	halt

. izracunaj faktorielo stevilo
factorial	COMP	#2
		JLT	endcondition

		. shranimo L in A na sklad
		STL	@stackptr
		JSUB	stackpush
		STA	@stackptr
		JSUB	stackpush

		. zmanjsamo A za 1
		SUB	#1
		JSUB	factorial

		. obnovimo L in A iz sklada, s tem da A direktno mnozimo
		JSUB	stackpop
		MUL	@stackptr
		JSUB	stackpop
		LDL	@stackptr

		RSUB

endcondition	LDA	#1
		RSUB

. get mod 10 of number
modT		RESW	1
mod10		STT	modT
		RMO	A, T
		DIV	#10
		MUL	#10
		SUBR	A, T
		RMO	T, A
		LDT	modT
		RSUB

. echonum - desetiški izpis števila podanega v registru A
readCount	RESW	1
echonum		STL	@stackptr
		JSUB	stackpush
		STT	@stackptr
		JSUB	stackpush
		STX	@stackptr
		JSUB	stackpush

		LDX	#0

. stevilo % 10 -> stack, stevilo // 10, repeat
split		RMO	A, T
		JSUB	mod10

		STA	@stackptr
		JSUB	stackpush
		. X+1, vseeno s cim primerjamo
		TIX	0

		RMO	T, A
		DIV	#10
		COMP	#0
		JGT	split

		STX	readCount
		LDX	#0
. sedaj iz stack jemljemo v obratnem vrstnem redu
print		JSUB	stackpop
		LDA	@stackptr
		ADD	#0x30
		JSUB	echoch

		TIX	readCount
		JLT	print

		JSUB	stackpop
		LDX	@stackptr
		JSUB	stackpop
		LDT	@stackptr
		JSUB	stackpop
		LDL	@stackptr

		RSUB

. echoch - izpis znaka podanega v registru A
echoch		TD	#1
		JEQ	echoch
		WD	#1
		RSUB

. echonl - izpis znaka za skok v novo vrstico
echonl		STL	@stackptr
		JSUB	stackpush
		STA	@stackptr
		JSUB	stackpush

		LDA	#10
		JSUB	echoch

		JSUB	stackpop
		LDA	@stackptr
		JSUB	stackpop
		LDL	@stackptr
		RSUB

. preberi desetisko stevilo iz napravo
readnum		STL	@stackptr
		JSUB	stackpush
		STT	@stackptr
		JSUB	stackpush
		STS	@stackptr
		JSUB	stackpush
		LDA	#0
		LDT	#0
		LDS	#10

read		TD	#0
		JEQ	read
		RD	#0
		COMP	#0xA . \n
		JEQ	ret

		. odstejemo 0x30 da dobimo samo stevilko
		SUB	#0x30
		. pomnozimo do sedaj prebrano stevilo z 10 in pristejemo prebrano stevilko
		MULR	S, T
		ADDR	A, T
		J	read

ret		RMO	T, A
		JSUB	stackpop
		LDS	@stackptr
		JSUB	stackpop
		LDT	@stackptr
		JSUB	stackpop
		LDL	@stackptr
		RSUB

........................ STACK ........................
tmpPush		RESW	1
stackpush	STA	tmpPush
		LDA	stackptr
		ADD	#3
		STA	stackptr
		LDA	tmpPush
		RSUB

tmpPop		RESW	1
stackpop	STA	tmpPop
		LDA	stackptr
		SUB	#3
		STA	stackptr
		LDA	tmpPop
		RSUB

tmpInit		RESW	1
stackinit	STA	tmpInit
		LDA	#stack
		STA	stackptr
		LDA	tmpInit
		RSUB

stackptr	RESW	1
stack		RESW	1024
		END	first