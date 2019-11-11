artm	START	0

first	LDX	#0

loop	LDA	input,X
	JSUB	write
	TIX	#len
	JLT	loop

halt	J	halt

write	TD	#0xAA
	JEQ	write
	WD	#0xAA
	RSUB
	
input	BYTE	0
	BYTE	0
	BYTE	83
	BYTE	73
	BYTE	67
	BYTE	47
	BYTE	88
	BYTE	69
	BYTE	10
lastin	EQU	*
len	EQU	lastin-input

	END	first