parser grammar PrevParser;

@header {

	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.lexan.*;

}

options {
	tokenVocab = PrevLexer;
}

source: ( decl)+;

decl:
	typeDeclaration
	| variableDeclaration
	| functionDeclaration;
typeDeclaration: KEYWORD_TYP IDENTIFIER SYMBOL_EQUALS type;
variableDeclaration: KEYWORD_VAR IDENTIFIER SYMBOL_COLON type;
functionDeclaration:
	KEYWORD_FUN IDENTIFIER SYMBOL_LBR funcArgRepeat SYMBOL_RBR SYMBOL_COLON type SYMBOL_EQUALS expr;
funcArgRepeat:
	(
		firstArg = IDENTIFIER SYMBOL_COLON firstType = type (
			SYMBOL_COMMA args = IDENTIFIER SYMBOL_COLON typp = type
		)*
	)?;

// -------------------------------------------------------------------------------------------
// ------------------------------------------ TYPE -------------------------------------------
// -------------------------------------------------------------------------------------------
type:
	rek = type SYMBOL_LSBR expr SYMBOL_RSBR
	| atomicType
	| namedType
	| pointerType
	| recordType
	| enclosedType;

atomicType:
	KEYWORD_VOID
	| KEYWORD_CHAR
	| KEYWORD_INT
	| KEYWORD_BOOL;

namedType: IDENTIFIER;

pointerType: SYMBOL_PWR type;

recordType: SYMBOL_LCBR recTypeRepeat SYMBOL_RCBR;
recTypeRepeat:
	firstArg = IDENTIFIER SYMBOL_COLON firstType = type (
		SYMBOL_COMMA args = IDENTIFIER SYMBOL_COLON typp = type
	)*;
enclosedType: SYMBOL_LBR type SYMBOL_RBR;

// -------------------------------------------------------------------------------------------
// --------------------------------------- EXPRESSION ----------------------------------------
// -------------------------------------------------------------------------------------------
relOps:
	SYMBOL_EQ
	| SYMBOL_NEQ
	| SYMBOL_LT
	| SYMBOL_GT
	| SYMBOL_LEQ
	| SYMBOL_GEQ;
addOps: SYMBOL_PLUS | SYMBOL_MIN;
mulOps: SYMBOL_STAR | SYMBOL_SLASH | SYMBOL_MOD;
prefOps:
	SYMBOL_EXCL
	| SYMBOL_PLUS
	| SYMBOL_MIN
	| SYMBOL_PWR
	| KEYWORD_NEW
	| KEYWORD_DEL;

expr:
	eeee = expr KEYWORD_WHERE SYMBOL_LCBR (decl)+ SYMBOL_RCBR
	| disjunctionExpression;

disjunctionExpression:
	first = conjunctionExpression (
		SYMBOL_OR second = conjunctionExpression
	)*;

conjunctionExpression:
	first = relationalExpression (
		SYMBOL_AND second = relationalExpression
	)*;

relationalExpression:
	first = additiveExpression (
		relOps second = additiveExpression
	)*;

additiveExpression:
	first = multiplicativeExpression (
		addOps second = multiplicativeExpression
	)*;

multiplicativeExpression:
	first = prefixExpression (mulOps second = prefixExpression)*;

prefixExpression: (prefOps)* postfixExpression;

postfixExpression:
	atomicExpression (
		SYMBOL_LSBR expr SYMBOL_RSBR
		| SYMBOL_PWR
		| SYMBOL_DOT IDENTIFIER
	)*;

atomicExpression:
	constantExpression
	| variableAccess
	| compoundExpression
	| enclosedExpression;

constantExpression: constant;
constant: VOID | BOOLEAN | INTEGER | CHAR | STRING | POINTER;

variableAccess: IDENTIFIER functionCall;
functionCall: (
		SYMBOL_LBR (
			firstExpr = expr (SYMBOL_COMMA exprs = expr)*
		)? SYMBOL_RBR
	)?;

compoundExpression:
	SYMBOL_LCBR (stmt SYMBOL_SEMIC)+ SYMBOL_RCBR;

enclosedExpression:
	SYMBOL_LBR expr typecastExpression SYMBOL_RBR;
typecastExpression: ( SYMBOL_COLON type)?;

stmt:
	expressionStatement
	| assignmentStatement
	| conditionalStatement
	| loopStatement;

expressionStatement: expr;

assignmentStatement: dst = expr SYMBOL_EQUALS src = expr;

conditionalStatement:
	KEYWORD_IF expr KEYWORD_THEN thn = stmt KEYWORD_ELSE els = stmt;

loopStatement: KEYWORD_WHILE expr KEYWORD_DO stmt;