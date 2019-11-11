artm	START	0

first	LDS	x
	LDT	y

	RMO	S, A
	ADDR	T, A
	STA	sum

	RMO	S, A
	. A <- A - T
	SUBR	T, A
	STA	diff

	RMO	S, A
	. A <- A * T
	MULR	T, A
	STA	prod

	RMO	S, A
	. A <- A / T
	DIVR	T, A
	STA	quot

. S = x
. T = y
	MULR	T, A
	RMO	A, T
	RMO	S, A
	SUBR	T, A
	STA	mod
	
. 8 % 3 = 2
. 8 / 3 = 2.6667
. 8 - (8/3) * 3 = 2

halt	J	halt

x	WORD	8
y	WORD	3
sum	RESW	1
diff	RESW	1
prod	RESW	1
quot	RESW	1
mod	RESW	1
	END	first
