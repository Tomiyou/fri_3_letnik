artm	START	0

first	LDA	#0

read	TD	#0
	JEQ	read
	RD	#0

write	TD	#1
	JEQ	write
	WD	#1

	J	read

	END	first
