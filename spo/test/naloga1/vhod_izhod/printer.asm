artm	START	0

. echoch - izpis znaka podanega v registru A
echoch	TD	#1
	JEQ	echoch
	WD	#1
	RSUB

. echonl - izpis znaka za skok v novo vrstico
nlA	RESW	1
nlL	RESW	1
echonl	STA	nlA
	STL	nlL
	LDA	#10
	JSUB	echoch
	LDA	nlA
	LDL	nlL
	RSUB

. echostr - izpis niza, ki se nahaja na naslovu podanem v registru A
ecstrA	RESW	1
ecstrX	RESW	1
ecstrL	RESW	1
echostr	STL	ecstrL
	STX	ecstrX
	STA	ecstrA

	STA	strptr
	LDX	#0

loop	LDCH	@strptr
	COMP	#0
	JEQ	endloop
	JSUB	echoch

	LDA	strptr
	ADD	#1
	STA	strptr

	J	loop
	
endloop	JSUB	echonl

	LDL	ecstrL
	LDX	ecstrX
	LDA	ecstrA
	RSUB


modT	RESW	1
mod10	STT	modT
	RMO	A, T
	DIV	#10
	MUL	#10
	SUBR	A, T
	RMO	T, A
	LDT	modT
	RSUB
. echonum - desetiški izpis števila podanega v registru A
prntT	RESW	1
prntX	RESW	1
prntL	RESW	1
echonum	STT	prntT
	STX	prntX
	STL	prntL
	LDX	#0

split	RMO	A, T
	JSUB	mod10
	STCH	numbers,X
	TIX	#8

	RMO	T, A
	DIV	#10
	COMP	#0
	JGT	split

print	RMO	X, A
	SUB	#1
	RMO	A, X
	LDCH	numbers,X
	ADD	#48
	JSUB	echoch
	
	RMO	X, A
	COMP	#0
	JGT	print

	LDT	prntT
	LDX	prntX
	LDL	prntL
	RSUB


first	LDA	#0xAFE
	JSUB	echonum

halt	J	halt

tmpA	RESW	1
tmpB	RESW	1
strptr	RESW	1
string	BYTE	100
	BYTE	97
	BYTE	110
	BYTE	101
	BYTE	115
	BYTE	32
	BYTE	106
	BYTE	101
	BYTE	32
	BYTE	108
	BYTE	101
	BYTE	112
	BYTE	32
	BYTE	100
	BYTE	97
	BYTE	110
	BYTE	0
numbers	RESB	8
	END	first