artm	START	0

first	LDA	#3
	LDT	#18
	LDX	#0

loop	LDS	input,X
	JSUB	plnm
	STS	result,X
	
	ADDR	A, X
	COMPR	X, T
	JLT	loop

	J	halt

. potrebno shraniti T in A (S je zaseden z argumentom)
. x^4 + 2x^3 + 3x^2 + 4x + 5
plnm	STA	tmp_a
	STT	tmp_t
	LDT	#0

	LDA	#1
	MULR	S, A
	MULR	S, A
	MULR	S, A
	MULR	S, A
	ADDR	A, T

	LDA	#2
	MULR	S, A
	MULR	S, A
	MULR	S, A
	ADDR	A, T

	LDA	#3
	MULR	S, A
	MULR	S, A
	ADDR	A, T

	LDA	#4
	MULR	S, A
	ADDR	A, T

	LDS	#5
	ADDR	T, S

	LDA	tmp_a
	LDT	tmp_t

	RSUB

halt	J	halt

x	WORD	3

input	WORD	0
	WORD	1
	WORD	2
	WORD	3
	WORD	4
	WORD	5
lastin	EQU	*
len	EQU	lastin-in

result	RESW	6

tmp_a	RESW	1
tmp_t	RESW	1
	END	first
