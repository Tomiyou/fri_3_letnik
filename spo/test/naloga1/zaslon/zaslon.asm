zaslon          START   0

screen          WORD    0xB800
scrcols         WORD    80
scrrows         WORD    25
scrlen          WORD    1998

first           JSUB    stackinit
                LDA	#69
                JSUB	scrfill
                JSUB	scrclear
halt            J	halt

scrclear        STA     @stackptr
                JSUB    stackpush
                LDA     #32
                J	fill

scrfill         STA     @stackptr
                JSUB    stackpush

address         RESW    1
fill            STX     @stackptr
                JSUB    stackpush
                LDX     #screen
                STX     address
                LDX	#0

loopfill        STA     @stackptr
                JSUB    stackpush

                LDA     screen
                ADDR	X, A
                STA     address

                JSUB    stackpop
                LDA     @stackptr

                STCH	@address
                TIX	scrlen
                JLT     loopfill

                JSUB    stackpop
                LDX     @stackptr

                JSUB    stackpop
                LDA     @stackptr
                
                RSUB

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

                END     first
