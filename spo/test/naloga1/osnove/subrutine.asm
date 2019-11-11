artm	START	0

first	LDA	#713
	LDT	#338

	LDS	x
	JSUB	plnm
	
	STS	result
	J	halt

. potrebno shraniti T in A (S je zaseden z argumentom)
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
result	WORD	0
tmp_a	RESW	1
tmp_t	RESW	1
	END	first
