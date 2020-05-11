lexer grammar PrevLexer;

@header {
	package prev.phase.lexan;
	import prev.common.report.*;
}

@members {
	@Override
	public LexAn.PrevToken nextToken() {
		return (LexAn.PrevToken) super.nextToken();
	}
}

VOID : 'none' ;
BOOLEAN : 'true' | 'false' ;
KEYWORD_BOOL : 'boolean' ;
KEYWORD_CHAR : 'char' ;
KEYWORD_DEL : 'del' ;
KEYWORD_DO : 'do' ;
KEYWORD_ELSE : 'else' ;
KEYWORD_FUN : 'fun' ;
KEYWORD_IF : 'if' ;
KEYWORD_INT : 'integer' ;
KEYWORD_NEW : 'new' ;
KEYWORD_THEN : 'then' ;
KEYWORD_TYP : 'typ' ;
KEYWORD_VAR : 'var' ;
KEYWORD_VOID : 'void' ;
KEYWORD_WHERE : 'where' ;
KEYWORD_WHILE : 'while' ;

INTEGER : [0-9]+ ;
CHAR : '\'' ('\u005C\u0027' | [ -~]) '\'' ;
STRING : '"' ('\u005C\u0022' | [ -~])* '"' ;
POINTER : 'nil' ;

SYMBOL_LBR : '(' ;
SYMBOL_RBR : ')' ;
SYMBOL_LCBR : '{' ;
SYMBOL_RCBR : '}' ;
SYMBOL_LSBR : '[' ;
SYMBOL_RSBR : ']' ;
SYMBOL_DOT : '.' ;
SYMBOL_COMMA : ',' ;
SYMBOL_COLON : ':' ;
SYMBOL_SEMIC : ';' ;
SYMBOL_AND : '&' ;
SYMBOL_OR : '|' ;
SYMBOL_EXCL : '!' ;
SYMBOL_EQ : '==' ;
SYMBOL_NEQ : '!=' ;
SYMBOL_LT : '<' ;
SYMBOL_GT : '>' ;
SYMBOL_LEQ : '<=' ;
SYMBOL_GEQ : '>=' ;
SYMBOL_STAR : '*' ;
SYMBOL_SLASH : '/' ;
SYMBOL_MOD : '%' ;
SYMBOL_PLUS : '+' ;
SYMBOL_MIN : '-' ;
SYMBOL_PWR : '^' ;
SYMBOL_EQUALS : '=' ;

IDENTIFIER : [_a-zA-Z] [_a-zA-Z0-9]* ;
COMMENT : '#' ~[\r\n]* '\r'? ('\n' | EOF) -> skip ;
WHITESPACE : [\p{White_Space}] -> skip;

ERROR : . {if (true) throw new Report.Error("Error at " + this.getLine() + ":" + this.getCharPositionInLine());} ;