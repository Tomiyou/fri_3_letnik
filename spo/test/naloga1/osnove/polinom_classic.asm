artm	START	0

first	LDS	x

	LDA	#1
	MULR	S, A
	MULR	S, A
	MULR	S, A
	MULR	S, A
	ADD	result
	STA	result

	LDA	#2
	MULR	S, A
	MULR	S, A
	MULR	S, A
	ADD	result
	STA	result

	LDA	#3
	MULR	S, A
	MULR	S, A
	ADD	result
	STA	result

	LDA	#4
	MULR	S, A
	ADD	result
	STA	result

	LDA	#5
	ADD	result
	STA	result

halt	J	halt

x	WORD	3
result	WORD	0
	END	first
