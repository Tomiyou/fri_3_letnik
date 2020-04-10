parser grammar PrevParser;

@header {

	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.lexan.*;

	import prev.data.ast.tree.*;
	import prev.data.ast.tree.decl.*;
	import prev.data.ast.tree.expr.*;
	import prev.data.ast.tree.stmt.*;
	import prev.data.ast.tree.type.*;
	import prev.data.ast.attribute.*;
	import prev.data.ast.visitor.*;

}

options {
	tokenVocab = PrevLexer;
}

source
	returns[AstTrees<AstDecl> ast, Vector<AstDecl> decls]
	@init {$decls = new Vector<AstDecl>();}
	@after {$ast = new AstTrees<AstDecl>($decls);}:
	(
		decl {
		$decls.add($decl.ast);
	}
	)+;

decl
	returns[AstDecl ast]:
	typeDeclaration {$ast = $typeDeclaration.ast; }
	| variableDeclaration {$ast = $variableDeclaration.ast; }
	| functionDeclaration {$ast = $functionDeclaration.ast; };
typeDeclaration
	returns[AstTypeDecl ast]:
	KEYWORD_TYP IDENTIFIER SYMBOL_EQUALS type {
		$ast = new AstTypeDecl(new Location((LexAn.PrevToken)$KEYWORD_TYP, $type.ast), $IDENTIFIER.getText(), $type.ast);
	};
variableDeclaration
	returns[AstVarDecl ast]:
	KEYWORD_VAR IDENTIFIER SYMBOL_COLON type {
		$ast = new AstVarDecl(new Location((LexAn.PrevToken)$KEYWORD_VAR, $type.ast), $IDENTIFIER.getText(), $type.ast);
	};
functionDeclaration
	returns[AstFunDecl ast]:
	KEYWORD_FUN IDENTIFIER SYMBOL_LBR funcArgRepeat SYMBOL_RBR SYMBOL_COLON type SYMBOL_EQUALS expr
		{
		$ast = new AstFunDecl(new Location((LexAn.PrevToken)$KEYWORD_FUN, $expr.ast),
			$IDENTIFIER.getText(), $funcArgRepeat.ast, $type.ast, $expr.ast);
	};
funcArgRepeat
	returns[AstTrees<AstParDecl> ast, Vector<AstParDecl> pairs]
	@init {$pairs = new Vector<AstParDecl>();}
	@after {$ast = new AstTrees<AstParDecl>($pairs);}:
	(
		firstArg = IDENTIFIER SYMBOL_COLON firstType = type {
				$pairs.add(new AstParDecl(
					new Location((LexAn.PrevToken)$firstArg, $firstType.ast), $firstArg.getText(), $firstType.ast));
			} (
			SYMBOL_COMMA args = IDENTIFIER SYMBOL_COLON typp = type {
				$pairs.add(new AstParDecl(new Location((LexAn.PrevToken)$SYMBOL_COMMA, $typp.ast), $args.getText(), $typp.ast));
			}
		)*
	)?;

// -------------------------------------------------------------------------------------------
// ------------------------------------------ TYPE -------------------------------------------
// -------------------------------------------------------------------------------------------
type
	returns[AstType ast]:
	rek = type SYMBOL_LSBR expr SYMBOL_RSBR {
			$ast = new AstArrType(new Location($rek.ast, (LexAn.PrevToken)$SYMBOL_RSBR), $rek.ast, $expr.ast); 
		}
	| atomicType {$ast = $atomicType.ast; }
	| namedType {$ast = $namedType.ast; }
	| pointerType {$ast = $pointerType.ast; }
	| recordType {$ast = $recordType.ast; }
	| enclosedType {$ast = $enclosedType.ast; };

atomicType
	returns[AstAtomType ast]:
	KEYWORD_VOID {$ast = new AstAtomType(
		new Location((LexAn.PrevToken)$KEYWORD_VOID, (LexAn.PrevToken)$KEYWORD_VOID), AstAtomType.Type.VOID); 
		}
	| KEYWORD_CHAR {$ast = new AstAtomType(
		new Location((LexAn.PrevToken)$KEYWORD_CHAR, (LexAn.PrevToken)$KEYWORD_CHAR), AstAtomType.Type.CHAR); 
		}
	| KEYWORD_INT {$ast = new AstAtomType(
		new Location((LexAn.PrevToken)$KEYWORD_INT, (LexAn.PrevToken)$KEYWORD_INT), AstAtomType.Type.INTEGER); 
		}
	| KEYWORD_BOOL {$ast = new AstAtomType(
		new Location((LexAn.PrevToken)$KEYWORD_BOOL, (LexAn.PrevToken)$KEYWORD_BOOL), AstAtomType.Type.BOOLEAN); 
		};

namedType
	returns[AstNameType ast]:
	IDENTIFIER {
		$ast = new AstNameType(new Location((LexAn.PrevToken)$IDENTIFIER, (LexAn.PrevToken)$IDENTIFIER), $IDENTIFIER.getText());
	};

pointerType
	returns[AstPtrType ast]:
	SYMBOL_PWR type {
		$ast = new AstPtrType(new Location((LexAn.PrevToken)$SYMBOL_PWR, $type.ast), $type.ast);
	};

recordType
	returns[AstRecType ast]:
	SYMBOL_LCBR recTypeRepeat SYMBOL_RCBR {
		$ast = new AstRecType(new Location((LexAn.PrevToken)$SYMBOL_LCBR, (LexAn.PrevToken)$SYMBOL_RCBR), $recTypeRepeat.ast);
	};
recTypeRepeat
	returns[AstTrees<AstCompDecl> ast, Vector<AstCompDecl> pairs]
	@init {$pairs = new Vector<AstCompDecl>();}
	@after {$ast = new AstTrees<AstCompDecl>($pairs);}:
	firstArg = IDENTIFIER SYMBOL_COLON firstType = type {
			$pairs.add(new AstCompDecl(
				new Location((LexAn.PrevToken)$firstArg, $firstType.ast), $firstArg.getText(), $firstType.ast));
		} (
		SYMBOL_COMMA args = IDENTIFIER SYMBOL_COLON typp = type {
				$pairs.add(new AstCompDecl(new Location((LexAn.PrevToken)$SYMBOL_COMMA, $typp.ast), $args.getText(), $typp.ast));
			}
	)*;
enclosedType
	returns[AstType ast]:
	SYMBOL_LBR type SYMBOL_RBR { $ast = $type.ast; };

// -------------------------------------------------------------------------------------------
// --------------------------------------- EXPRESSION ----------------------------------------
// -------------------------------------------------------------------------------------------
relOps
	returns[AstBinExpr.Oper oper]:
	SYMBOL_EQ {$oper = AstBinExpr.Oper.EQU; }
	| SYMBOL_NEQ {$oper = AstBinExpr.Oper.NEQ; }
	| SYMBOL_LT {$oper = AstBinExpr.Oper.LTH; }
	| SYMBOL_GT {$oper = AstBinExpr.Oper.GTH; }
	| SYMBOL_LEQ {$oper = AstBinExpr.Oper.LEQ; }
	| SYMBOL_GEQ {$oper = AstBinExpr.Oper.GEQ; };
addOps
	returns[AstBinExpr.Oper oper]:
	SYMBOL_PLUS {$oper = AstBinExpr.Oper.ADD; }
	| SYMBOL_MIN {$oper = AstBinExpr.Oper.SUB; };
mulOps
	returns[AstBinExpr.Oper oper]:
	SYMBOL_STAR {$oper = AstBinExpr.Oper.MUL; }
	| SYMBOL_SLASH {$oper = AstBinExpr.Oper.DIV; }
	| SYMBOL_MOD {$oper = AstBinExpr.Oper.MOD; };
prefOps
	returns[AstPfxExpr.Oper oper, LexAn.PrevToken end]:
	SYMBOL_EXCL {$oper = AstPfxExpr.Oper.NOT; $end = (LexAn.PrevToken)$SYMBOL_EXCL; }
	| SYMBOL_PLUS {$oper = AstPfxExpr.Oper.ADD; $end = (LexAn.PrevToken)$SYMBOL_PLUS; }
	| SYMBOL_MIN {$oper = AstPfxExpr.Oper.SUB; $end = (LexAn.PrevToken)$SYMBOL_MIN; }
	| SYMBOL_PWR {$oper = AstPfxExpr.Oper.PTR; $end = (LexAn.PrevToken)$SYMBOL_PWR; }
	| KEYWORD_NEW {$oper = AstPfxExpr.Oper.NEW; $end = (LexAn.PrevToken)$KEYWORD_NEW; }
	| KEYWORD_DEL {$oper = AstPfxExpr.Oper.DEL; $end = (LexAn.PrevToken)$KEYWORD_DEL; };

expr
	returns[AstExpr ast, Vector<AstDecl> decls]
	@init {$decls = new Vector<AstDecl>();}:
	eeee = expr KEYWORD_WHERE SYMBOL_LCBR (
		decl {
		$decls.add($decl.ast);
	}
	)+ SYMBOL_RCBR {
		$ast = new AstWhereExpr(new Location($eeee.ast, (LexAn.PrevToken)$SYMBOL_RCBR), $eeee.ast, new AstTrees<AstDecl>($decls)); 
		}
	| disjunctionExpression {$ast = $disjunctionExpression.ast; };

disjunctionExpression
	returns[AstExpr ast]:
	first = conjunctionExpression {$ast = $first.ast; } (
		SYMBOL_OR second = conjunctionExpression {
			$ast = new AstBinExpr(new Location($ast, $conjunctionExpression.ast), AstBinExpr.Oper.OR, $ast, $second.ast);
			}
	)*;

conjunctionExpression
	returns[AstExpr ast]:
	first = relationalExpression {$ast = $first.ast; } (
		SYMBOL_AND second = relationalExpression {
			$ast = new AstBinExpr(new Location($ast, $relationalExpression.ast), AstBinExpr.Oper.AND, $ast, $second.ast);
			}
	)*;

relationalExpression
	returns[AstExpr ast]:
	first = additiveExpression {$ast = $first.ast; } (
		relOps second = additiveExpression {
			$ast = new AstBinExpr(new Location($ast, $additiveExpression.ast), $relOps.oper, $ast, $second.ast);
			}
	)*;

additiveExpression
	returns[AstExpr ast]:
	first = multiplicativeExpression {$ast = $first.ast; } (
		addOps second = multiplicativeExpression {
			$ast = new AstBinExpr(new Location($ast, $multiplicativeExpression.ast), $addOps.oper, $ast, $second.ast);
			}
	)*;

multiplicativeExpression
	returns[AstExpr ast]:
	first = prefixExpression {$ast = $first.ast; } (
		mulOps second = prefixExpression {
			$ast = new AstBinExpr(new Location($ast, $prefixExpression.ast), $mulOps.oper, $ast, $second.ast);
			}
	)*;

prefixExpression
	returns[AstExpr ast, Vector<AstPfxExpr.Oper> ops, Vector<LexAn.PrevToken> ends]
	@init {$ops = new Vector<AstPfxExpr.Oper>();$ends = new Vector<LexAn.PrevToken>();}:
	(prefOps {$ops.add($prefOps.oper);$ends.add($prefOps.end);})* postfixExpression {
			$ast = $postfixExpression.ast;

			Collections.reverse($ops);
			Collections.reverse($ends);

			for (int i = 0; i < $ops.size(); i++) {
				$ast = new AstPfxExpr(new Location($ends.get(i), $ast), $ops.get(i), $ast);
			}
		};

postfixExpression
	returns[AstExpr ast]:
	atomicExpression {$ast = $atomicExpression.ast; } (
		SYMBOL_LSBR expr SYMBOL_RSBR {
				$ast = new AstArrExpr(new Location($ast, (LexAn.PrevToken)$SYMBOL_RSBR), $ast, $expr.ast); 
			}
		| SYMBOL_PWR {$ast = new AstSfxExpr(new Location($ast, (LexAn.PrevToken)$SYMBOL_PWR), AstSfxExpr.Oper.PTR, $ast); 
			}
		| SYMBOL_DOT IDENTIFIER {
				LexAn.PrevToken loc = (LexAn.PrevToken)$IDENTIFIER;
				$ast = new AstRecExpr(new Location($ast, loc),
					$ast, new AstNameExpr(new Location(loc, loc), $IDENTIFIER.getText())); 
			}
	)*;

atomicExpression
	returns[AstExpr ast]:
	constantExpression {$ast = $constantExpression.ast; }
	| variableAccess {$ast = $variableAccess.ast; }
	| compoundExpression {$ast = $compoundExpression.ast; }
	| enclosedExpression {$ast = $enclosedExpression.ast; };

constantExpression
	returns[AstAtomExpr ast]:
	constant {$ast = new AstAtomExpr(new Location($constant.end, $constant.end), $constant.typp, $constant.val); 
		};
constant
	returns[AstAtomExpr.Type typp, String val, LexAn.PrevToken end]:
	VOID {$typp = AstAtomExpr.Type.VOID; $val = $VOID.getText(); $end = (LexAn.PrevToken)$VOID; }
	| BOOLEAN {$typp = AstAtomExpr.Type.BOOLEAN; $val = $BOOLEAN.getText(); $end = (LexAn.PrevToken)$BOOLEAN; 
		}
	| INTEGER {$typp = AstAtomExpr.Type.INTEGER; $val = $INTEGER.getText(); $end = (LexAn.PrevToken)$INTEGER; 
		}
	| CHAR {$typp = AstAtomExpr.Type.CHAR; $val = $CHAR.getText(); $end = (LexAn.PrevToken)$CHAR; }
	| STRING {$typp = AstAtomExpr.Type.STRING; $val = $STRING.getText(); $end = (LexAn.PrevToken)$STRING; 
		}
	| POINTER {$typp = AstAtomExpr.Type.POINTER; $val = $POINTER.getText(); $end = (LexAn.PrevToken)$POINTER; 
		};

variableAccess
	returns[AstNameExpr ast]:
	IDENTIFIER functionCall {
		if ($functionCall.args == null) {
			LexAn.PrevToken loc = (LexAn.PrevToken)$IDENTIFIER;
			$ast = new AstNameExpr(new Location(loc,loc), $IDENTIFIER.getText());
		} else {
			$ast = new AstCallExpr(
				new Location((LexAn.PrevToken)$IDENTIFIER,$functionCall.end), $IDENTIFIER.getText(), $functionCall.args);
		}
	};
functionCall
	returns[AstTrees<AstExpr> args, Vector<AstExpr> vec, LexAn.PrevToken end]
	@init {$vec = new Vector<AstExpr>();}: (
		SYMBOL_LBR (
			firstExpr = expr (
				SYMBOL_COMMA exprs = expr {$vec.add($exprs.ast);}
			)* {$vec.add($firstExpr.ast);}
		)? SYMBOL_RBR {$args = new AstTrees<AstExpr>($vec); $end = (LexAn.PrevToken)$SYMBOL_RBR; }
	)?;

compoundExpression
	returns[AstStmtExpr ast, Vector<AstStmt> stmts]
	@init {$stmts = new Vector<AstStmt>();}:
	SYMBOL_LCBR (stmt SYMBOL_SEMIC {$stmts.add($stmt.ast);})+ SYMBOL_RCBR {
		$ast = new AstStmtExpr(new Location((LexAn.PrevToken)$SYMBOL_LCBR,(LexAn.PrevToken)$SYMBOL_RCBR),
			new AstTrees<AstStmt>($stmts)
	); };

enclosedExpression
	returns[AstExpr ast]:
	SYMBOL_LBR expr typecastExpression SYMBOL_RBR {
		if ($typecastExpression.typp == null) {
			$ast = $expr.ast;
		} else {
			$ast = new AstCastExpr(
				new Location((LexAn.PrevToken)$SYMBOL_LBR,(LexAn.PrevToken)$SYMBOL_RBR), $expr.ast, $typecastExpression.typp
			);
		}
	};
typecastExpression
	returns[AstType typp]: (
		SYMBOL_COLON type {$typp = $type.ast;}
	)?;

stmt
	returns[AstStmt ast]:
	expressionStatement {$ast = $expressionStatement.ast; }
	| assignmentStatement {$ast = $assignmentStatement.ast; }
	| conditionalStatement {$ast = $conditionalStatement.ast; }
	| loopStatement {$ast = $loopStatement.ast; };

expressionStatement
	returns[AstExprStmt ast]:
	expr {$ast = new AstExprStmt(new Location($expr.ast, $expr.ast), $expr.ast); };

assignmentStatement
	returns[AstAssignStmt ast]:
	dst = expr SYMBOL_EQUALS src = expr {$ast = new AstAssignStmt(new Location($dst.ast, $src.ast), $dst.ast, $src.ast); 
		};

conditionalStatement
	returns[AstIfStmt ast]:
	KEYWORD_IF expr KEYWORD_THEN thn = stmt KEYWORD_ELSE els = stmt {
		$ast = new AstIfStmt(new Location((LexAn.PrevToken)$KEYWORD_IF, $els.ast), $expr.ast, $thn.ast, $els.ast); 
	};

loopStatement
	returns[AstWhileStmt ast]:
	KEYWORD_WHILE expr KEYWORD_DO stmt {
		$ast = new AstWhileStmt(new Location((LexAn.PrevToken)$KEYWORD_WHILE, $stmt.ast), $expr.ast, $stmt.ast);
	};