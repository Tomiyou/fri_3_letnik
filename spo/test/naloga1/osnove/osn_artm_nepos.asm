artm	START	0

first	LDA	x
	ADD	y
	STA	sum

	LDA	x
	SUB	y
	STA	diff

	LDA	x
	MUL	y
	STA	prod

	LDA	x
	DIV	y
	STA	quot

. S = x
. T = y
	MUL	y
	STA	mod
	LDA	x
	SUB	mod
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
