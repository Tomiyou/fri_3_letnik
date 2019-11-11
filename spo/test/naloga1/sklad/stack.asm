stackProgram    START           0

first           LDA             #0x9AF
                JSUB	        stackinit

                STA             @stackptr
                JSUB            stackpush

                LDA             #0x110

                STA             @stackptr
                JSUB            stackpush
            
                JSUB            stackpop
                LDA             @stackptr

halt	        J     	        halt

tmpPush         RESW            1
stackpush       STA             tmpPush
                LDA	        stackptr
                ADD             #3
                STA	        stackptr
                LDA             tmpPush
                RSUB

tmpPop          RESW            1
stackpop        STA             tmpPop
                LDA	        stackptr
                SUB             #3
                STA	        stackptr
                LDA             tmpPop
                RSUB

tmpInit         RESW            1
stackinit       STA             tmpInit
                LDA	        #stack
                STA	        stackptr
                LDA             tmpInit
                RSUB

stackptr        RESW  	        1
stack           RESW            1024
	        END   	        first