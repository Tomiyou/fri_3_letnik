parser grammar PrevParser;

@header {

	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.*;
	import prev.data.ast.tree.*;

}

options {
	tokenVocab = PrevLexer;
}

source
	returns[AstTrees<AstDecl> ast]: (decl)+ { Vector<AstDecl> ds = new Vector<AstDecl>(); //naredim vektor
	  ds.add($decl.ast);				// vanga zmečem deklaracije
	  $ast = new AstTrees<AstDecl>(ds); }; // bla

decl
	returns[AstDecl ast]:
	typeDeclaration {$ast = $typeDeclaration.ast}
	| variableDeclaration {$ast = $variableDeclaration.ast}
	| functionDeclaration {$ast = $functionDeclaration.ast};
typeDeclaration
	returns[AstTypeDecl ast]:
	KEYWORD_TYP IDENTIFIER SYMBOL_EQUALS type {$ast = $type.ast};
variableDeclaration
	returns[AstVarDecl ast]:
	KEYWORD_VAR IDENTIFIER SYMBOL_COLON type {$ast = $type.ast};
functionDeclaration
	returns[AstFunDecl ast]:
	KEYWORD_FUN IDENTIFIER SYMBOL_LBR (typeRepeat)? SYMBOL_RBR SYMBOL_COLON type SYMBOL_EQUALS expr;

type
	returns[AstType ast]:
	atomicType {$ast = $atomicType.ast}
	| namedType {$ast = $namedType.ast}
	| arrayType {$ast = $arrayType.ast}
	| pointerType {$ast = $pointerType.ast}
	| recordType {$ast = $recordType.ast}
	| enclosedType {$ast = $enclosedType.ast};
atomicType returns[AstAtomType ast]:
	KEYWORD_VOID arrayType
	| KEYWORD_CHAR arrayType
	| KEYWORD_INT arrayType
	| KEYWORD_BOOL arrayType;
namedType returns[AstNameType ast]: IDENTIFIER arrayType;
pointerType returns[AstPtrType ast]: SYMBOL_PWR type arrayType;
recordType returns[AstRecType ast]: SYMBOL_LCBR typeRepeat SYMBOL_RCBR arrayType;
enclosedType returns[AstType ast]: SYMBOL_LBR type SYMBOL_RBR arrayType;
arrayType returns[AstArrType ast]: SYMBOL_LSBR expr SYMBOL_RSBR arrayType |;

typeRepeat:
	IDENTIFIER SYMBOL_COLON type (
		SYMBOL_COMMA IDENTIFIER SYMBOL_COLON type
	)*;

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
postOps:
	SYMBOL_LSBR expr SYMBOL_RSBR
	| SYMBOL_PWR
	| SYMBOL_DOT IDENTIFIER;
constant: VOID | BOOLEAN | INTEGER | CHAR | STRING | POINTER;

expr:
	expr KEYWORD_WHERE SYMBOL_LCBR (decl)+ SYMBOL_RCBR
	| disjunctionExpression;

disjunctionExpression:
	conjunctionExpression (SYMBOL_OR conjunctionExpression)*;
conjunctionExpression:
	relationalExpression (SYMBOL_AND relationalExpression)*;
relationalExpression:
	additiveExpression (relOps additiveExpression)*;
additiveExpression:
	multiplicativeExpression (addOps multiplicativeExpression)*;
multiplicativeExpression:
	prefixExpression (mulOps prefixExpression)*;
prefixExpression: (prefOps)* postfixExpression;
postfixExpression: atomicExpression (postOps)*;

atomicExpression:
	constant
	| variableAccess
	| compoundExpression
	| typecastExpression
	| enclosedExpression;

constantExpression: constant;
variableAccess: IDENTIFIER functionCall;
functionCall: (
		SYMBOL_LBR (expr (SYMBOL_COMMA expr)*)? SYMBOL_RBR
	)?;
compoundExpression:
	SYMBOL_LCBR (stmt SYMBOL_SEMIC)+ SYMBOL_RCBR;
typecastExpression: (SYMBOL_COLON type)?;
enclosedExpression:
	SYMBOL_LBR expr typecastExpression SYMBOL_RBR;

stmt:
	expressionStatement
	| assignmentStatement
	| conditionalStatement
	| loopStatement;
expressionStatement: expr;
assignmentStatement: expr SYMBOL_EQUALS expr;
conditionalStatement:
	KEYWORD_IF expr KEYWORD_THEN stmt KEYWORD_ELSE stmt;
loopStatement: KEYWORD_WHILE expr KEYWORD_DO stmt;