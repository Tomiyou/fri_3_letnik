fact            START	0
first           JSUB	stackinit
                LDA	#9
                JSUB	factorial

halt            J	halt

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

tmpPush         RESW	1
stackpush       STA	tmpPush
                LDA	stackptr
                ADD	#3
                STA	stackptr
                LDA	tmpPush
                RSUB

tmpPop          RESW	1
stackpop        STA	tmpPop
                LDA	stackptr
                SUB	#3
                STA	stackptr
                LDA	tmpPop
                RSUB

tmpInit         RESW	1
stackinit       STA	tmpInit
                LDA	#stack
                STA	stackptr
                LDA	tmpInit
                RSUB

stackptr        RESW	1
stack           RESW	1024
	        END	first