// Generated from synan/PrevParser.g4 by ANTLR 4.8


	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.lexan.*;
	import prev.phase.lexan.LexAn.*;

	import prev.data.ast.tree.*;
	import prev.data.ast.tree.decl.*;
	import prev.data.ast.tree.expr.*;
	import prev.data.ast.tree.stmt.*;
	import prev.data.ast.tree.type.*;
	import prev.data.ast.attribute.*;
	import prev.data.ast.visitor.*;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VOID=1, BOOLEAN=2, KEYWORD_BOOL=3, KEYWORD_CHAR=4, KEYWORD_DEL=5, KEYWORD_DO=6, 
		KEYWORD_ELSE=7, KEYWORD_FUN=8, KEYWORD_IF=9, KEYWORD_INT=10, KEYWORD_NEW=11, 
		KEYWORD_THEN=12, KEYWORD_TYP=13, KEYWORD_VAR=14, KEYWORD_VOID=15, KEYWORD_WHERE=16, 
		KEYWORD_WHILE=17, INTEGER=18, CHAR=19, STRING=20, POINTER=21, SYMBOL_LBR=22, 
		SYMBOL_RBR=23, SYMBOL_LCBR=24, SYMBOL_RCBR=25, SYMBOL_LSBR=26, SYMBOL_RSBR=27, 
		SYMBOL_DOT=28, SYMBOL_COMMA=29, SYMBOL_COLON=30, SYMBOL_SEMIC=31, SYMBOL_AND=32, 
		SYMBOL_OR=33, SYMBOL_EXCL=34, SYMBOL_EQ=35, SYMBOL_NEQ=36, SYMBOL_LT=37, 
		SYMBOL_GT=38, SYMBOL_LEQ=39, SYMBOL_GEQ=40, SYMBOL_STAR=41, SYMBOL_SLASH=42, 
		SYMBOL_MOD=43, SYMBOL_PLUS=44, SYMBOL_MIN=45, SYMBOL_PWR=46, SYMBOL_EQUALS=47, 
		IDENTIFIER=48, COMMENT=49, WHITESPACE=50, ERROR=51;
	public static final int
		RULE_source = 0, RULE_decl = 1, RULE_typeDeclaration = 2, RULE_variableDeclaration = 3, 
		RULE_functionDeclaration = 4, RULE_funcArgRepeat = 5, RULE_type = 6, RULE_atomicType = 7, 
		RULE_namedType = 8, RULE_pointerType = 9, RULE_recordType = 10, RULE_recTypeRepeat = 11, 
		RULE_enclosedType = 12, RULE_relOps = 13, RULE_addOps = 14, RULE_mulOps = 15, 
		RULE_prefOps = 16, RULE_expr = 17, RULE_disjunctionExpression = 18, RULE_conjunctionExpression = 19, 
		RULE_relationalExpression = 20, RULE_additiveExpression = 21, RULE_multiplicativeExpression = 22, 
		RULE_prefixExpression = 23, RULE_postfixExpression = 24, RULE_atomicExpression = 25, 
		RULE_constantExpression = 26, RULE_variableAccess = 27, RULE_functionCall = 28, 
		RULE_compoundExpression = 29, RULE_enclosedExpression = 30, RULE_typecastExpression = 31, 
		RULE_stmt = 32, RULE_expressionStatement = 33, RULE_assignmentStatement = 34, 
		RULE_conditionalStatement = 35, RULE_loopStatement = 36;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "decl", "typeDeclaration", "variableDeclaration", "functionDeclaration", 
			"funcArgRepeat", "type", "atomicType", "namedType", "pointerType", "recordType", 
			"recTypeRepeat", "enclosedType", "relOps", "addOps", "mulOps", "prefOps", 
			"expr", "disjunctionExpression", "conjunctionExpression", "relationalExpression", 
			"additiveExpression", "multiplicativeExpression", "prefixExpression", 
			"postfixExpression", "atomicExpression", "constantExpression", "variableAccess", 
			"functionCall", "compoundExpression", "enclosedExpression", "typecastExpression", 
			"stmt", "expressionStatement", "assignmentStatement", "conditionalStatement", 
			"loopStatement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'none'", null, "'boolean'", "'char'", "'del'", "'do'", "'else'", 
			"'fun'", "'if'", "'integer'", "'new'", "'then'", "'typ'", "'var'", "'void'", 
			"'where'", "'while'", null, null, null, "'nil'", "'('", "')'", "'{'", 
			"'}'", "'['", "']'", "'.'", "','", "':'", "';'", "'&'", "'|'", "'!'", 
			"'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'*'", "'/'", "'%'", "'+'", 
			"'-'", "'^'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VOID", "BOOLEAN", "KEYWORD_BOOL", "KEYWORD_CHAR", "KEYWORD_DEL", 
			"KEYWORD_DO", "KEYWORD_ELSE", "KEYWORD_FUN", "KEYWORD_IF", "KEYWORD_INT", 
			"KEYWORD_NEW", "KEYWORD_THEN", "KEYWORD_TYP", "KEYWORD_VAR", "KEYWORD_VOID", 
			"KEYWORD_WHERE", "KEYWORD_WHILE", "INTEGER", "CHAR", "STRING", "POINTER", 
			"SYMBOL_LBR", "SYMBOL_RBR", "SYMBOL_LCBR", "SYMBOL_RCBR", "SYMBOL_LSBR", 
			"SYMBOL_RSBR", "SYMBOL_DOT", "SYMBOL_COMMA", "SYMBOL_COLON", "SYMBOL_SEMIC", 
			"SYMBOL_AND", "SYMBOL_OR", "SYMBOL_EXCL", "SYMBOL_EQ", "SYMBOL_NEQ", 
			"SYMBOL_LT", "SYMBOL_GT", "SYMBOL_LEQ", "SYMBOL_GEQ", "SYMBOL_STAR", 
			"SYMBOL_SLASH", "SYMBOL_MOD", "SYMBOL_PLUS", "SYMBOL_MIN", "SYMBOL_PWR", 
			"SYMBOL_EQUALS", "IDENTIFIER", "COMMENT", "WHITESPACE", "ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PrevParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrevParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SourceContext extends ParserRuleContext {
		public AstTrees<AstDecl> ast;
		public Vector<AstDecl> decls;
		public DeclContext decl;
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		((SourceContext)_localctx).decls =  new Vector<AstDecl>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74);
				((SourceContext)_localctx).decl = decl();

						_localctx.decls.add(((SourceContext)_localctx).decl.ast);
					
				}
				}
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KEYWORD_FUN) | (1L << KEYWORD_TYP) | (1L << KEYWORD_VAR))) != 0) );
			}
			_ctx.stop = _input.LT(-1);
			((SourceContext)_localctx).ast =  new AstTrees<AstDecl>(_localctx.decls);
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public AstDecl ast;
		public TypeDeclarationContext typeDeclaration;
		public VariableDeclarationContext variableDeclaration;
		public FunctionDeclarationContext functionDeclaration;
		public TypeDeclarationContext typeDeclaration() {
			return getRuleContext(TypeDeclarationContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			setState(90);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEYWORD_TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				((DeclContext)_localctx).typeDeclaration = typeDeclaration();
				((DeclContext)_localctx).ast =  ((DeclContext)_localctx).typeDeclaration.ast; 
				}
				break;
			case KEYWORD_VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				((DeclContext)_localctx).variableDeclaration = variableDeclaration();
				((DeclContext)_localctx).ast =  ((DeclContext)_localctx).variableDeclaration.ast; 
				}
				break;
			case KEYWORD_FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
				((DeclContext)_localctx).functionDeclaration = functionDeclaration();
				((DeclContext)_localctx).ast =  ((DeclContext)_localctx).functionDeclaration.ast; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDeclarationContext extends ParserRuleContext {
		public AstTypeDecl ast;
		public Token KEYWORD_TYP;
		public Token IDENTIFIER;
		public TypeContext type;
		public TerminalNode KEYWORD_TYP() { return getToken(PrevParser.KEYWORD_TYP, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode SYMBOL_EQUALS() { return getToken(PrevParser.SYMBOL_EQUALS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_typeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			((TypeDeclarationContext)_localctx).KEYWORD_TYP = match(KEYWORD_TYP);
			setState(93);
			((TypeDeclarationContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(94);
			match(SYMBOL_EQUALS);
			setState(95);
			((TypeDeclarationContext)_localctx).type = type(0);

					((TypeDeclarationContext)_localctx).ast =  new AstTypeDecl(new Location((PrevToken)((TypeDeclarationContext)_localctx).KEYWORD_TYP, ((TypeDeclarationContext)_localctx).type.ast), ((TypeDeclarationContext)_localctx).IDENTIFIER.getText(), ((TypeDeclarationContext)_localctx).type.ast);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public AstVarDecl ast;
		public Token KEYWORD_VAR;
		public Token IDENTIFIER;
		public TypeContext type;
		public TerminalNode KEYWORD_VAR() { return getToken(PrevParser.KEYWORD_VAR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode SYMBOL_COLON() { return getToken(PrevParser.SYMBOL_COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			((VariableDeclarationContext)_localctx).KEYWORD_VAR = match(KEYWORD_VAR);
			setState(99);
			((VariableDeclarationContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(100);
			match(SYMBOL_COLON);
			setState(101);
			((VariableDeclarationContext)_localctx).type = type(0);

					((VariableDeclarationContext)_localctx).ast =  new AstVarDecl(new Location((PrevToken)((VariableDeclarationContext)_localctx).KEYWORD_VAR, ((VariableDeclarationContext)_localctx).type.ast), ((VariableDeclarationContext)_localctx).IDENTIFIER.getText(), ((VariableDeclarationContext)_localctx).type.ast);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationContext extends ParserRuleContext {
		public AstFunDecl ast;
		public Token KEYWORD_FUN;
		public Token IDENTIFIER;
		public FuncArgRepeatContext funcArgRepeat;
		public TypeContext type;
		public ExprContext expr;
		public TerminalNode KEYWORD_FUN() { return getToken(PrevParser.KEYWORD_FUN, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode SYMBOL_LBR() { return getToken(PrevParser.SYMBOL_LBR, 0); }
		public FuncArgRepeatContext funcArgRepeat() {
			return getRuleContext(FuncArgRepeatContext.class,0);
		}
		public TerminalNode SYMBOL_RBR() { return getToken(PrevParser.SYMBOL_RBR, 0); }
		public TerminalNode SYMBOL_COLON() { return getToken(PrevParser.SYMBOL_COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SYMBOL_EQUALS() { return getToken(PrevParser.SYMBOL_EQUALS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functionDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			((FunctionDeclarationContext)_localctx).KEYWORD_FUN = match(KEYWORD_FUN);
			setState(105);
			((FunctionDeclarationContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(106);
			match(SYMBOL_LBR);
			setState(107);
			((FunctionDeclarationContext)_localctx).funcArgRepeat = funcArgRepeat();
			setState(108);
			match(SYMBOL_RBR);
			setState(109);
			match(SYMBOL_COLON);
			setState(110);
			((FunctionDeclarationContext)_localctx).type = type(0);
			setState(111);
			match(SYMBOL_EQUALS);
			setState(112);
			((FunctionDeclarationContext)_localctx).expr = expr(0);

					((FunctionDeclarationContext)_localctx).ast =  new AstFunDecl(new Location((PrevToken)((FunctionDeclarationContext)_localctx).KEYWORD_FUN, ((FunctionDeclarationContext)_localctx).expr.ast),
						((FunctionDeclarationContext)_localctx).IDENTIFIER.getText(), ((FunctionDeclarationContext)_localctx).funcArgRepeat.ast, ((FunctionDeclarationContext)_localctx).type.ast, ((FunctionDeclarationContext)_localctx).expr.ast);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncArgRepeatContext extends ParserRuleContext {
		public AstTrees<AstParDecl> ast;
		public Vector<AstParDecl> pairs;
		public Token firstArg;
		public TypeContext firstType;
		public Token SYMBOL_COMMA;
		public Token args;
		public TypeContext typp;
		public List<TerminalNode> SYMBOL_COLON() { return getTokens(PrevParser.SYMBOL_COLON); }
		public TerminalNode SYMBOL_COLON(int i) {
			return getToken(PrevParser.SYMBOL_COLON, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> SYMBOL_COMMA() { return getTokens(PrevParser.SYMBOL_COMMA); }
		public TerminalNode SYMBOL_COMMA(int i) {
			return getToken(PrevParser.SYMBOL_COMMA, i);
		}
		public FuncArgRepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcArgRepeat; }
	}

	public final FuncArgRepeatContext funcArgRepeat() throws RecognitionException {
		FuncArgRepeatContext _localctx = new FuncArgRepeatContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_funcArgRepeat);
		((FuncArgRepeatContext)_localctx).pairs =  new Vector<AstParDecl>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(115);
				((FuncArgRepeatContext)_localctx).firstArg = match(IDENTIFIER);
				setState(116);
				match(SYMBOL_COLON);
				setState(117);
				((FuncArgRepeatContext)_localctx).firstType = type(0);

								_localctx.pairs.add(new AstParDecl(
									new Location((PrevToken)((FuncArgRepeatContext)_localctx).firstArg, ((FuncArgRepeatContext)_localctx).firstType.ast), ((FuncArgRepeatContext)_localctx).firstArg.getText(), ((FuncArgRepeatContext)_localctx).firstType.ast));
							
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SYMBOL_COMMA) {
					{
					{
					setState(119);
					((FuncArgRepeatContext)_localctx).SYMBOL_COMMA = match(SYMBOL_COMMA);
					setState(120);
					((FuncArgRepeatContext)_localctx).args = match(IDENTIFIER);
					setState(121);
					match(SYMBOL_COLON);
					setState(122);
					((FuncArgRepeatContext)_localctx).typp = type(0);

									_localctx.pairs.add(new AstParDecl(new Location((PrevToken)((FuncArgRepeatContext)_localctx).SYMBOL_COMMA, ((FuncArgRepeatContext)_localctx).typp.ast), ((FuncArgRepeatContext)_localctx).args.getText(), ((FuncArgRepeatContext)_localctx).typp.ast));
								
					}
					}
					setState(129);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
			_ctx.stop = _input.LT(-1);
			((FuncArgRepeatContext)_localctx).ast =  new AstTrees<AstParDecl>(_localctx.pairs);
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public AstType ast;
		public TypeContext rek;
		public AtomicTypeContext atomicType;
		public NamedTypeContext namedType;
		public PointerTypeContext pointerType;
		public RecordTypeContext recordType;
		public EnclosedTypeContext enclosedType;
		public ExprContext expr;
		public Token SYMBOL_RSBR;
		public AtomicTypeContext atomicType() {
			return getRuleContext(AtomicTypeContext.class,0);
		}
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class,0);
		}
		public RecordTypeContext recordType() {
			return getRuleContext(RecordTypeContext.class,0);
		}
		public EnclosedTypeContext enclosedType() {
			return getRuleContext(EnclosedTypeContext.class,0);
		}
		public TerminalNode SYMBOL_LSBR() { return getToken(PrevParser.SYMBOL_LSBR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SYMBOL_RSBR() { return getToken(PrevParser.SYMBOL_RSBR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEYWORD_BOOL:
			case KEYWORD_CHAR:
			case KEYWORD_INT:
			case KEYWORD_VOID:
				{
				setState(133);
				((TypeContext)_localctx).atomicType = atomicType();
				((TypeContext)_localctx).ast =  ((TypeContext)_localctx).atomicType.ast; 
				}
				break;
			case IDENTIFIER:
				{
				setState(136);
				((TypeContext)_localctx).namedType = namedType();
				((TypeContext)_localctx).ast =  ((TypeContext)_localctx).namedType.ast; 
				}
				break;
			case SYMBOL_PWR:
				{
				setState(139);
				((TypeContext)_localctx).pointerType = pointerType();
				((TypeContext)_localctx).ast =  ((TypeContext)_localctx).pointerType.ast; 
				}
				break;
			case SYMBOL_LCBR:
				{
				setState(142);
				((TypeContext)_localctx).recordType = recordType();
				((TypeContext)_localctx).ast =  ((TypeContext)_localctx).recordType.ast; 
				}
				break;
			case SYMBOL_LBR:
				{
				setState(145);
				((TypeContext)_localctx).enclosedType = enclosedType();
				((TypeContext)_localctx).ast =  ((TypeContext)_localctx).enclosedType.ast; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(158);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					_localctx.rek = _prevctx;
					_localctx.rek = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(150);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(151);
					match(SYMBOL_LSBR);
					setState(152);
					((TypeContext)_localctx).expr = expr(0);
					setState(153);
					((TypeContext)_localctx).SYMBOL_RSBR = match(SYMBOL_RSBR);

					          			((TypeContext)_localctx).ast =  new AstArrType(new Location(((TypeContext)_localctx).rek.ast, (PrevToken)((TypeContext)_localctx).SYMBOL_RSBR), ((TypeContext)_localctx).rek.ast, ((TypeContext)_localctx).expr.ast); 
					          		
					}
					} 
				}
				setState(160);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomicTypeContext extends ParserRuleContext {
		public AstAtomType ast;
		public Token KEYWORD_VOID;
		public Token KEYWORD_CHAR;
		public Token KEYWORD_INT;
		public Token KEYWORD_BOOL;
		public TerminalNode KEYWORD_VOID() { return getToken(PrevParser.KEYWORD_VOID, 0); }
		public TerminalNode KEYWORD_CHAR() { return getToken(PrevParser.KEYWORD_CHAR, 0); }
		public TerminalNode KEYWORD_INT() { return getToken(PrevParser.KEYWORD_INT, 0); }
		public TerminalNode KEYWORD_BOOL() { return getToken(PrevParser.KEYWORD_BOOL, 0); }
		public AtomicTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomicType; }
	}

	public final AtomicTypeContext atomicType() throws RecognitionException {
		AtomicTypeContext _localctx = new AtomicTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_atomicType);
		try {
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEYWORD_VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				((AtomicTypeContext)_localctx).KEYWORD_VOID = match(KEYWORD_VOID);
				((AtomicTypeContext)_localctx).ast =  new AstAtomType(
						new Location((PrevToken)((AtomicTypeContext)_localctx).KEYWORD_VOID, (PrevToken)((AtomicTypeContext)_localctx).KEYWORD_VOID), AstAtomType.Type.VOID); 
						
				}
				break;
			case KEYWORD_CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				((AtomicTypeContext)_localctx).KEYWORD_CHAR = match(KEYWORD_CHAR);
				((AtomicTypeContext)_localctx).ast =  new AstAtomType(
						new Location((PrevToken)((AtomicTypeContext)_localctx).KEYWORD_CHAR, (PrevToken)((AtomicTypeContext)_localctx).KEYWORD_CHAR), AstAtomType.Type.CHAR); 
						
				}
				break;
			case KEYWORD_INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(165);
				((AtomicTypeContext)_localctx).KEYWORD_INT = match(KEYWORD_INT);
				((AtomicTypeContext)_localctx).ast =  new AstAtomType(
						new Location((PrevToken)((AtomicTypeContext)_localctx).KEYWORD_INT, (PrevToken)((AtomicTypeContext)_localctx).KEYWORD_INT), AstAtomType.Type.INTEGER); 
						
				}
				break;
			case KEYWORD_BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(167);
				((AtomicTypeContext)_localctx).KEYWORD_BOOL = match(KEYWORD_BOOL);
				((AtomicTypeContext)_localctx).ast =  new AstAtomType(
						new Location((PrevToken)((AtomicTypeContext)_localctx).KEYWORD_BOOL, (PrevToken)((AtomicTypeContext)_localctx).KEYWORD_BOOL), AstAtomType.Type.BOOLEAN); 
						
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamedTypeContext extends ParserRuleContext {
		public AstNameType ast;
		public Token IDENTIFIER;
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public NamedTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedType; }
	}

	public final NamedTypeContext namedType() throws RecognitionException {
		NamedTypeContext _localctx = new NamedTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_namedType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			((NamedTypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);

					((NamedTypeContext)_localctx).ast =  new AstNameType(new Location((PrevToken)((NamedTypeContext)_localctx).IDENTIFIER, (PrevToken)((NamedTypeContext)_localctx).IDENTIFIER), ((NamedTypeContext)_localctx).IDENTIFIER.getText());
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointerTypeContext extends ParserRuleContext {
		public AstPtrType ast;
		public Token SYMBOL_PWR;
		public TypeContext type;
		public TerminalNode SYMBOL_PWR() { return getToken(PrevParser.SYMBOL_PWR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public PointerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerType; }
	}

	public final PointerTypeContext pointerType() throws RecognitionException {
		PointerTypeContext _localctx = new PointerTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			((PointerTypeContext)_localctx).SYMBOL_PWR = match(SYMBOL_PWR);
			setState(175);
			((PointerTypeContext)_localctx).type = type(0);

					((PointerTypeContext)_localctx).ast =  new AstPtrType(new Location((PrevToken)((PointerTypeContext)_localctx).SYMBOL_PWR, ((PointerTypeContext)_localctx).type.ast), ((PointerTypeContext)_localctx).type.ast);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecordTypeContext extends ParserRuleContext {
		public AstRecType ast;
		public Token SYMBOL_LCBR;
		public RecTypeRepeatContext recTypeRepeat;
		public Token SYMBOL_RCBR;
		public TerminalNode SYMBOL_LCBR() { return getToken(PrevParser.SYMBOL_LCBR, 0); }
		public RecTypeRepeatContext recTypeRepeat() {
			return getRuleContext(RecTypeRepeatContext.class,0);
		}
		public TerminalNode SYMBOL_RCBR() { return getToken(PrevParser.SYMBOL_RCBR, 0); }
		public RecordTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordType; }
	}

	public final RecordTypeContext recordType() throws RecognitionException {
		RecordTypeContext _localctx = new RecordTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_recordType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			((RecordTypeContext)_localctx).SYMBOL_LCBR = match(SYMBOL_LCBR);
			setState(179);
			((RecordTypeContext)_localctx).recTypeRepeat = recTypeRepeat();
			setState(180);
			((RecordTypeContext)_localctx).SYMBOL_RCBR = match(SYMBOL_RCBR);

					((RecordTypeContext)_localctx).ast =  new AstRecType(new Location((PrevToken)((RecordTypeContext)_localctx).SYMBOL_LCBR, (PrevToken)((RecordTypeContext)_localctx).SYMBOL_RCBR), ((RecordTypeContext)_localctx).recTypeRepeat.ast);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecTypeRepeatContext extends ParserRuleContext {
		public AstTrees<AstCompDecl> ast;
		public Vector<AstCompDecl> pairs;
		public Token firstArg;
		public TypeContext firstType;
		public Token SYMBOL_COMMA;
		public Token args;
		public TypeContext typp;
		public List<TerminalNode> SYMBOL_COLON() { return getTokens(PrevParser.SYMBOL_COLON); }
		public TerminalNode SYMBOL_COLON(int i) {
			return getToken(PrevParser.SYMBOL_COLON, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> SYMBOL_COMMA() { return getTokens(PrevParser.SYMBOL_COMMA); }
		public TerminalNode SYMBOL_COMMA(int i) {
			return getToken(PrevParser.SYMBOL_COMMA, i);
		}
		public RecTypeRepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recTypeRepeat; }
	}

	public final RecTypeRepeatContext recTypeRepeat() throws RecognitionException {
		RecTypeRepeatContext _localctx = new RecTypeRepeatContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_recTypeRepeat);
		((RecTypeRepeatContext)_localctx).pairs =  new Vector<AstCompDecl>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			((RecTypeRepeatContext)_localctx).firstArg = match(IDENTIFIER);
			setState(184);
			match(SYMBOL_COLON);
			setState(185);
			((RecTypeRepeatContext)_localctx).firstType = type(0);

						_localctx.pairs.add(new AstCompDecl(
							new Location((PrevToken)((RecTypeRepeatContext)_localctx).firstArg, ((RecTypeRepeatContext)_localctx).firstType.ast), ((RecTypeRepeatContext)_localctx).firstArg.getText(), ((RecTypeRepeatContext)_localctx).firstType.ast));
					
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SYMBOL_COMMA) {
				{
				{
				setState(187);
				((RecTypeRepeatContext)_localctx).SYMBOL_COMMA = match(SYMBOL_COMMA);
				setState(188);
				((RecTypeRepeatContext)_localctx).args = match(IDENTIFIER);
				setState(189);
				match(SYMBOL_COLON);
				setState(190);
				((RecTypeRepeatContext)_localctx).typp = type(0);

								_localctx.pairs.add(new AstCompDecl(new Location((PrevToken)((RecTypeRepeatContext)_localctx).SYMBOL_COMMA, ((RecTypeRepeatContext)_localctx).typp.ast), ((RecTypeRepeatContext)_localctx).args.getText(), ((RecTypeRepeatContext)_localctx).typp.ast));
							
				}
				}
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);
			((RecTypeRepeatContext)_localctx).ast =  new AstTrees<AstCompDecl>(_localctx.pairs);
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnclosedTypeContext extends ParserRuleContext {
		public AstType ast;
		public TypeContext type;
		public TerminalNode SYMBOL_LBR() { return getToken(PrevParser.SYMBOL_LBR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SYMBOL_RBR() { return getToken(PrevParser.SYMBOL_RBR, 0); }
		public EnclosedTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enclosedType; }
	}

	public final EnclosedTypeContext enclosedType() throws RecognitionException {
		EnclosedTypeContext _localctx = new EnclosedTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_enclosedType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(SYMBOL_LBR);
			setState(199);
			((EnclosedTypeContext)_localctx).type = type(0);
			setState(200);
			match(SYMBOL_RBR);
			 ((EnclosedTypeContext)_localctx).ast =  ((EnclosedTypeContext)_localctx).type.ast; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelOpsContext extends ParserRuleContext {
		public AstBinExpr.Oper oper;
		public TerminalNode SYMBOL_EQ() { return getToken(PrevParser.SYMBOL_EQ, 0); }
		public TerminalNode SYMBOL_NEQ() { return getToken(PrevParser.SYMBOL_NEQ, 0); }
		public TerminalNode SYMBOL_LT() { return getToken(PrevParser.SYMBOL_LT, 0); }
		public TerminalNode SYMBOL_GT() { return getToken(PrevParser.SYMBOL_GT, 0); }
		public TerminalNode SYMBOL_LEQ() { return getToken(PrevParser.SYMBOL_LEQ, 0); }
		public TerminalNode SYMBOL_GEQ() { return getToken(PrevParser.SYMBOL_GEQ, 0); }
		public RelOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relOps; }
	}

	public final RelOpsContext relOps() throws RecognitionException {
		RelOpsContext _localctx = new RelOpsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_relOps);
		try {
			setState(215);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_EQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(203);
				match(SYMBOL_EQ);
				((RelOpsContext)_localctx).oper =  AstBinExpr.Oper.EQU; 
				}
				break;
			case SYMBOL_NEQ:
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				match(SYMBOL_NEQ);
				((RelOpsContext)_localctx).oper =  AstBinExpr.Oper.NEQ; 
				}
				break;
			case SYMBOL_LT:
				enterOuterAlt(_localctx, 3);
				{
				setState(207);
				match(SYMBOL_LT);
				((RelOpsContext)_localctx).oper =  AstBinExpr.Oper.LTH; 
				}
				break;
			case SYMBOL_GT:
				enterOuterAlt(_localctx, 4);
				{
				setState(209);
				match(SYMBOL_GT);
				((RelOpsContext)_localctx).oper =  AstBinExpr.Oper.GTH; 
				}
				break;
			case SYMBOL_LEQ:
				enterOuterAlt(_localctx, 5);
				{
				setState(211);
				match(SYMBOL_LEQ);
				((RelOpsContext)_localctx).oper =  AstBinExpr.Oper.LEQ; 
				}
				break;
			case SYMBOL_GEQ:
				enterOuterAlt(_localctx, 6);
				{
				setState(213);
				match(SYMBOL_GEQ);
				((RelOpsContext)_localctx).oper =  AstBinExpr.Oper.GEQ; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddOpsContext extends ParserRuleContext {
		public AstBinExpr.Oper oper;
		public TerminalNode SYMBOL_PLUS() { return getToken(PrevParser.SYMBOL_PLUS, 0); }
		public TerminalNode SYMBOL_MIN() { return getToken(PrevParser.SYMBOL_MIN, 0); }
		public AddOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addOps; }
	}

	public final AddOpsContext addOps() throws RecognitionException {
		AddOpsContext _localctx = new AddOpsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_addOps);
		try {
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				match(SYMBOL_PLUS);
				((AddOpsContext)_localctx).oper =  AstBinExpr.Oper.ADD; 
				}
				break;
			case SYMBOL_MIN:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				match(SYMBOL_MIN);
				((AddOpsContext)_localctx).oper =  AstBinExpr.Oper.SUB; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulOpsContext extends ParserRuleContext {
		public AstBinExpr.Oper oper;
		public TerminalNode SYMBOL_STAR() { return getToken(PrevParser.SYMBOL_STAR, 0); }
		public TerminalNode SYMBOL_SLASH() { return getToken(PrevParser.SYMBOL_SLASH, 0); }
		public TerminalNode SYMBOL_MOD() { return getToken(PrevParser.SYMBOL_MOD, 0); }
		public MulOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulOps; }
	}

	public final MulOpsContext mulOps() throws RecognitionException {
		MulOpsContext _localctx = new MulOpsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_mulOps);
		try {
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				match(SYMBOL_STAR);
				((MulOpsContext)_localctx).oper =  AstBinExpr.Oper.MUL; 
				}
				break;
			case SYMBOL_SLASH:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				match(SYMBOL_SLASH);
				((MulOpsContext)_localctx).oper =  AstBinExpr.Oper.DIV; 
				}
				break;
			case SYMBOL_MOD:
				enterOuterAlt(_localctx, 3);
				{
				setState(227);
				match(SYMBOL_MOD);
				((MulOpsContext)_localctx).oper =  AstBinExpr.Oper.MOD; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrefOpsContext extends ParserRuleContext {
		public AstPfxExpr.Oper oper;
		public PrevToken end;
		public Token SYMBOL_EXCL;
		public Token SYMBOL_PLUS;
		public Token SYMBOL_MIN;
		public Token SYMBOL_PWR;
		public Token KEYWORD_NEW;
		public Token KEYWORD_DEL;
		public TerminalNode SYMBOL_EXCL() { return getToken(PrevParser.SYMBOL_EXCL, 0); }
		public TerminalNode SYMBOL_PLUS() { return getToken(PrevParser.SYMBOL_PLUS, 0); }
		public TerminalNode SYMBOL_MIN() { return getToken(PrevParser.SYMBOL_MIN, 0); }
		public TerminalNode SYMBOL_PWR() { return getToken(PrevParser.SYMBOL_PWR, 0); }
		public TerminalNode KEYWORD_NEW() { return getToken(PrevParser.KEYWORD_NEW, 0); }
		public TerminalNode KEYWORD_DEL() { return getToken(PrevParser.KEYWORD_DEL, 0); }
		public PrefOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefOps; }
	}

	public final PrefOpsContext prefOps() throws RecognitionException {
		PrefOpsContext _localctx = new PrefOpsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_prefOps);
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_EXCL:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				((PrefOpsContext)_localctx).SYMBOL_EXCL = match(SYMBOL_EXCL);
				((PrefOpsContext)_localctx).oper =  AstPfxExpr.Oper.NOT; ((PrefOpsContext)_localctx).end =  (PrevToken)((PrefOpsContext)_localctx).SYMBOL_EXCL; 
				}
				break;
			case SYMBOL_PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				((PrefOpsContext)_localctx).SYMBOL_PLUS = match(SYMBOL_PLUS);
				((PrefOpsContext)_localctx).oper =  AstPfxExpr.Oper.ADD; ((PrefOpsContext)_localctx).end =  (PrevToken)((PrefOpsContext)_localctx).SYMBOL_PLUS; 
				}
				break;
			case SYMBOL_MIN:
				enterOuterAlt(_localctx, 3);
				{
				setState(235);
				((PrefOpsContext)_localctx).SYMBOL_MIN = match(SYMBOL_MIN);
				((PrefOpsContext)_localctx).oper =  AstPfxExpr.Oper.SUB; ((PrefOpsContext)_localctx).end =  (PrevToken)((PrefOpsContext)_localctx).SYMBOL_MIN; 
				}
				break;
			case SYMBOL_PWR:
				enterOuterAlt(_localctx, 4);
				{
				setState(237);
				((PrefOpsContext)_localctx).SYMBOL_PWR = match(SYMBOL_PWR);
				((PrefOpsContext)_localctx).oper =  AstPfxExpr.Oper.PTR; ((PrefOpsContext)_localctx).end =  (PrevToken)((PrefOpsContext)_localctx).SYMBOL_PWR; 
				}
				break;
			case KEYWORD_NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(239);
				((PrefOpsContext)_localctx).KEYWORD_NEW = match(KEYWORD_NEW);
				((PrefOpsContext)_localctx).oper =  AstPfxExpr.Oper.NEW; ((PrefOpsContext)_localctx).end =  (PrevToken)((PrefOpsContext)_localctx).KEYWORD_NEW; 
				}
				break;
			case KEYWORD_DEL:
				enterOuterAlt(_localctx, 6);
				{
				setState(241);
				((PrefOpsContext)_localctx).KEYWORD_DEL = match(KEYWORD_DEL);
				((PrefOpsContext)_localctx).oper =  AstPfxExpr.Oper.DEL; ((PrefOpsContext)_localctx).end =  (PrevToken)((PrefOpsContext)_localctx).KEYWORD_DEL; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public AstExpr ast;
		public Vector<AstDecl> decls;
		public ExprContext eeee;
		public DisjunctionExpressionContext disjunctionExpression;
		public DeclContext decl;
		public Token SYMBOL_RCBR;
		public DisjunctionExpressionContext disjunctionExpression() {
			return getRuleContext(DisjunctionExpressionContext.class,0);
		}
		public TerminalNode KEYWORD_WHERE() { return getToken(PrevParser.KEYWORD_WHERE, 0); }
		public TerminalNode SYMBOL_LCBR() { return getToken(PrevParser.SYMBOL_LCBR, 0); }
		public TerminalNode SYMBOL_RCBR() { return getToken(PrevParser.SYMBOL_RCBR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(246);
			((ExprContext)_localctx).disjunctionExpression = disjunctionExpression();
			((ExprContext)_localctx).ast =  ((ExprContext)_localctx).disjunctionExpression.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(265);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					_localctx.eeee = _prevctx;
					_localctx.eeee = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(249);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(250);
					match(KEYWORD_WHERE);
					setState(251);
					match(SYMBOL_LCBR);
					((ExprContext)_localctx).decls =  new Vector<AstDecl>();
					setState(256); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(253);
						((ExprContext)_localctx).decl = decl();
						 _localctx.decls.add(((ExprContext)_localctx).decl.ast); 
						}
						}
						setState(258); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KEYWORD_FUN) | (1L << KEYWORD_TYP) | (1L << KEYWORD_VAR))) != 0) );
					setState(260);
					((ExprContext)_localctx).SYMBOL_RCBR = match(SYMBOL_RCBR);

					          		((ExprContext)_localctx).ast =  new AstWhereExpr(new Location(((ExprContext)_localctx).eeee.ast, (PrevToken)((ExprContext)_localctx).SYMBOL_RCBR), ((ExprContext)_localctx).eeee.ast, new AstTrees<AstDecl>(_localctx.decls)); 
					          		
					}
					} 
				}
				setState(267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DisjunctionExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public ConjunctionExpressionContext first;
		public ConjunctionExpressionContext conjunctionExpression;
		public ConjunctionExpressionContext second;
		public List<ConjunctionExpressionContext> conjunctionExpression() {
			return getRuleContexts(ConjunctionExpressionContext.class);
		}
		public ConjunctionExpressionContext conjunctionExpression(int i) {
			return getRuleContext(ConjunctionExpressionContext.class,i);
		}
		public List<TerminalNode> SYMBOL_OR() { return getTokens(PrevParser.SYMBOL_OR); }
		public TerminalNode SYMBOL_OR(int i) {
			return getToken(PrevParser.SYMBOL_OR, i);
		}
		public DisjunctionExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunctionExpression; }
	}

	public final DisjunctionExpressionContext disjunctionExpression() throws RecognitionException {
		DisjunctionExpressionContext _localctx = new DisjunctionExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_disjunctionExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			((DisjunctionExpressionContext)_localctx).first = ((DisjunctionExpressionContext)_localctx).conjunctionExpression = conjunctionExpression();
			((DisjunctionExpressionContext)_localctx).ast =  ((DisjunctionExpressionContext)_localctx).first.ast; 
			setState(276);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(270);
					match(SYMBOL_OR);
					setState(271);
					((DisjunctionExpressionContext)_localctx).second = ((DisjunctionExpressionContext)_localctx).conjunctionExpression = conjunctionExpression();

								((DisjunctionExpressionContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.ast, ((DisjunctionExpressionContext)_localctx).conjunctionExpression.ast), AstBinExpr.Oper.OR, _localctx.ast, ((DisjunctionExpressionContext)_localctx).second.ast);
								
					}
					} 
				}
				setState(278);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConjunctionExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public RelationalExpressionContext first;
		public RelationalExpressionContext relationalExpression;
		public RelationalExpressionContext second;
		public List<RelationalExpressionContext> relationalExpression() {
			return getRuleContexts(RelationalExpressionContext.class);
		}
		public RelationalExpressionContext relationalExpression(int i) {
			return getRuleContext(RelationalExpressionContext.class,i);
		}
		public List<TerminalNode> SYMBOL_AND() { return getTokens(PrevParser.SYMBOL_AND); }
		public TerminalNode SYMBOL_AND(int i) {
			return getToken(PrevParser.SYMBOL_AND, i);
		}
		public ConjunctionExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunctionExpression; }
	}

	public final ConjunctionExpressionContext conjunctionExpression() throws RecognitionException {
		ConjunctionExpressionContext _localctx = new ConjunctionExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_conjunctionExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			((ConjunctionExpressionContext)_localctx).first = ((ConjunctionExpressionContext)_localctx).relationalExpression = relationalExpression();
			((ConjunctionExpressionContext)_localctx).ast =  ((ConjunctionExpressionContext)_localctx).first.ast; 
			setState(287);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(281);
					match(SYMBOL_AND);
					setState(282);
					((ConjunctionExpressionContext)_localctx).second = ((ConjunctionExpressionContext)_localctx).relationalExpression = relationalExpression();

								((ConjunctionExpressionContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.ast, ((ConjunctionExpressionContext)_localctx).relationalExpression.ast), AstBinExpr.Oper.AND, _localctx.ast, ((ConjunctionExpressionContext)_localctx).second.ast);
							
					}
					} 
				}
				setState(289);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public AdditiveExpressionContext first;
		public AdditiveExpressionContext additiveExpression;
		public RelOpsContext relOps;
		public AdditiveExpressionContext second;
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<RelOpsContext> relOps() {
			return getRuleContexts(RelOpsContext.class);
		}
		public RelOpsContext relOps(int i) {
			return getRuleContext(RelOpsContext.class,i);
		}
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_relationalExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			((RelationalExpressionContext)_localctx).first = ((RelationalExpressionContext)_localctx).additiveExpression = additiveExpression();
			((RelationalExpressionContext)_localctx).ast =  ((RelationalExpressionContext)_localctx).first.ast; 
			setState(298);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(292);
					((RelationalExpressionContext)_localctx).relOps = relOps();
					setState(293);
					((RelationalExpressionContext)_localctx).second = ((RelationalExpressionContext)_localctx).additiveExpression = additiveExpression();

								((RelationalExpressionContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.ast, ((RelationalExpressionContext)_localctx).additiveExpression.ast), ((RelationalExpressionContext)_localctx).relOps.oper, _localctx.ast, ((RelationalExpressionContext)_localctx).second.ast);
							
					}
					} 
				}
				setState(300);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public MultiplicativeExpressionContext first;
		public MultiplicativeExpressionContext multiplicativeExpression;
		public AddOpsContext addOps;
		public MultiplicativeExpressionContext second;
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<AddOpsContext> addOps() {
			return getRuleContexts(AddOpsContext.class);
		}
		public AddOpsContext addOps(int i) {
			return getRuleContext(AddOpsContext.class,i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_additiveExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			((AdditiveExpressionContext)_localctx).first = ((AdditiveExpressionContext)_localctx).multiplicativeExpression = multiplicativeExpression();
			((AdditiveExpressionContext)_localctx).ast =  ((AdditiveExpressionContext)_localctx).first.ast; 
			setState(309);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(303);
					((AdditiveExpressionContext)_localctx).addOps = addOps();
					setState(304);
					((AdditiveExpressionContext)_localctx).second = ((AdditiveExpressionContext)_localctx).multiplicativeExpression = multiplicativeExpression();

								((AdditiveExpressionContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.ast, ((AdditiveExpressionContext)_localctx).multiplicativeExpression.ast), ((AdditiveExpressionContext)_localctx).addOps.oper, _localctx.ast, ((AdditiveExpressionContext)_localctx).second.ast);
							
					}
					} 
				}
				setState(311);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public PrefixExpressionContext first;
		public PrefixExpressionContext prefixExpression;
		public MulOpsContext mulOps;
		public PrefixExpressionContext second;
		public List<PrefixExpressionContext> prefixExpression() {
			return getRuleContexts(PrefixExpressionContext.class);
		}
		public PrefixExpressionContext prefixExpression(int i) {
			return getRuleContext(PrefixExpressionContext.class,i);
		}
		public List<MulOpsContext> mulOps() {
			return getRuleContexts(MulOpsContext.class);
		}
		public MulOpsContext mulOps(int i) {
			return getRuleContext(MulOpsContext.class,i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_multiplicativeExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			((MultiplicativeExpressionContext)_localctx).first = ((MultiplicativeExpressionContext)_localctx).prefixExpression = prefixExpression();
			((MultiplicativeExpressionContext)_localctx).ast =  ((MultiplicativeExpressionContext)_localctx).first.ast; 
			setState(320);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(314);
					((MultiplicativeExpressionContext)_localctx).mulOps = mulOps();
					setState(315);
					((MultiplicativeExpressionContext)_localctx).second = ((MultiplicativeExpressionContext)_localctx).prefixExpression = prefixExpression();

								((MultiplicativeExpressionContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.ast, ((MultiplicativeExpressionContext)_localctx).prefixExpression.ast), ((MultiplicativeExpressionContext)_localctx).mulOps.oper, _localctx.ast, ((MultiplicativeExpressionContext)_localctx).second.ast);
							
					}
					} 
				}
				setState(322);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrefixExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public Vector<AstPfxExpr.Oper> ops;
		public Vector<PrevToken> ends;
		public PrefOpsContext prefOps;
		public PostfixExpressionContext postfixExpression;
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public List<PrefOpsContext> prefOps() {
			return getRuleContexts(PrefOpsContext.class);
		}
		public PrefOpsContext prefOps(int i) {
			return getRuleContext(PrefOpsContext.class,i);
		}
		public PrefixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixExpression; }
	}

	public final PrefixExpressionContext prefixExpression() throws RecognitionException {
		PrefixExpressionContext _localctx = new PrefixExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_prefixExpression);
		((PrefixExpressionContext)_localctx).ops =  new Vector<AstPfxExpr.Oper>();((PrefixExpressionContext)_localctx).ends =  new Vector<PrevToken>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KEYWORD_DEL) | (1L << KEYWORD_NEW) | (1L << SYMBOL_EXCL) | (1L << SYMBOL_PLUS) | (1L << SYMBOL_MIN) | (1L << SYMBOL_PWR))) != 0)) {
				{
				{
				setState(323);
				((PrefixExpressionContext)_localctx).prefOps = prefOps();
				_localctx.ops.add(((PrefixExpressionContext)_localctx).prefOps.oper);_localctx.ends.add(((PrefixExpressionContext)_localctx).prefOps.end);
				}
				}
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(331);
			((PrefixExpressionContext)_localctx).postfixExpression = postfixExpression();

					((PrefixExpressionContext)_localctx).ast =  ((PrefixExpressionContext)_localctx).postfixExpression.ast;

					Collections.reverse(_localctx.ops);
					Collections.reverse(_localctx.ends);

					for (int i = 0; i < _localctx.ops.size(); i++) {
						((PrefixExpressionContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.ends.get(i), _localctx.ast), _localctx.ops.get(i), _localctx.ast);
					}
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostfixExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public AtomicExpressionContext atomicExpression;
		public ExprContext expr;
		public Token SYMBOL_RSBR;
		public Token SYMBOL_PWR;
		public Token IDENTIFIER;
		public AtomicExpressionContext atomicExpression() {
			return getRuleContext(AtomicExpressionContext.class,0);
		}
		public List<TerminalNode> SYMBOL_LSBR() { return getTokens(PrevParser.SYMBOL_LSBR); }
		public TerminalNode SYMBOL_LSBR(int i) {
			return getToken(PrevParser.SYMBOL_LSBR, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> SYMBOL_RSBR() { return getTokens(PrevParser.SYMBOL_RSBR); }
		public TerminalNode SYMBOL_RSBR(int i) {
			return getToken(PrevParser.SYMBOL_RSBR, i);
		}
		public List<TerminalNode> SYMBOL_PWR() { return getTokens(PrevParser.SYMBOL_PWR); }
		public TerminalNode SYMBOL_PWR(int i) {
			return getToken(PrevParser.SYMBOL_PWR, i);
		}
		public List<TerminalNode> SYMBOL_DOT() { return getTokens(PrevParser.SYMBOL_DOT); }
		public TerminalNode SYMBOL_DOT(int i) {
			return getToken(PrevParser.SYMBOL_DOT, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpression; }
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_postfixExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			((PostfixExpressionContext)_localctx).atomicExpression = atomicExpression();
			((PostfixExpressionContext)_localctx).ast =  ((PostfixExpressionContext)_localctx).atomicExpression.ast; 
			setState(348);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(346);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case SYMBOL_LSBR:
						{
						setState(336);
						match(SYMBOL_LSBR);
						setState(337);
						((PostfixExpressionContext)_localctx).expr = expr(0);
						setState(338);
						((PostfixExpressionContext)_localctx).SYMBOL_RSBR = match(SYMBOL_RSBR);

										((PostfixExpressionContext)_localctx).ast =  new AstArrExpr(new Location(_localctx.ast, (PrevToken)((PostfixExpressionContext)_localctx).SYMBOL_RSBR), _localctx.ast, ((PostfixExpressionContext)_localctx).expr.ast); 
									
						}
						break;
					case SYMBOL_PWR:
						{
						setState(341);
						((PostfixExpressionContext)_localctx).SYMBOL_PWR = match(SYMBOL_PWR);
						((PostfixExpressionContext)_localctx).ast =  new AstSfxExpr(new Location(_localctx.ast, (PrevToken)((PostfixExpressionContext)_localctx).SYMBOL_PWR), AstSfxExpr.Oper.PTR, _localctx.ast); 
									
						}
						break;
					case SYMBOL_DOT:
						{
						setState(343);
						match(SYMBOL_DOT);
						setState(344);
						((PostfixExpressionContext)_localctx).IDENTIFIER = match(IDENTIFIER);

										PrevToken loc = (PrevToken)((PostfixExpressionContext)_localctx).IDENTIFIER;
										((PostfixExpressionContext)_localctx).ast =  new AstRecExpr(new Location(_localctx.ast, loc),
											_localctx.ast, new AstNameExpr(new Location(loc, loc), ((PostfixExpressionContext)_localctx).IDENTIFIER.getText())); 
									
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(350);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomicExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public ConstantExpressionContext constantExpression;
		public VariableAccessContext variableAccess;
		public CompoundExpressionContext compoundExpression;
		public EnclosedExpressionContext enclosedExpression;
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public VariableAccessContext variableAccess() {
			return getRuleContext(VariableAccessContext.class,0);
		}
		public CompoundExpressionContext compoundExpression() {
			return getRuleContext(CompoundExpressionContext.class,0);
		}
		public EnclosedExpressionContext enclosedExpression() {
			return getRuleContext(EnclosedExpressionContext.class,0);
		}
		public AtomicExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomicExpression; }
	}

	public final AtomicExpressionContext atomicExpression() throws RecognitionException {
		AtomicExpressionContext _localctx = new AtomicExpressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_atomicExpression);
		try {
			setState(363);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
			case BOOLEAN:
			case INTEGER:
			case CHAR:
			case STRING:
			case POINTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(351);
				((AtomicExpressionContext)_localctx).constantExpression = constantExpression();
				((AtomicExpressionContext)_localctx).ast =  ((AtomicExpressionContext)_localctx).constantExpression.ast; 
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(354);
				((AtomicExpressionContext)_localctx).variableAccess = variableAccess();
				((AtomicExpressionContext)_localctx).ast =  ((AtomicExpressionContext)_localctx).variableAccess.ast; 
				}
				break;
			case SYMBOL_LCBR:
				enterOuterAlt(_localctx, 3);
				{
				setState(357);
				((AtomicExpressionContext)_localctx).compoundExpression = compoundExpression();
				((AtomicExpressionContext)_localctx).ast =  ((AtomicExpressionContext)_localctx).compoundExpression.ast; 
				}
				break;
			case SYMBOL_LBR:
				enterOuterAlt(_localctx, 4);
				{
				setState(360);
				((AtomicExpressionContext)_localctx).enclosedExpression = enclosedExpression();
				((AtomicExpressionContext)_localctx).ast =  ((AtomicExpressionContext)_localctx).enclosedExpression.ast; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantExpressionContext extends ParserRuleContext {
		public AstAtomExpr ast;
		public Token VOID;
		public Token BOOLEAN;
		public Token INTEGER;
		public Token CHAR;
		public Token STRING;
		public Token POINTER;
		public TerminalNode VOID() { return getToken(PrevParser.VOID, 0); }
		public TerminalNode BOOLEAN() { return getToken(PrevParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(PrevParser.INTEGER, 0); }
		public TerminalNode CHAR() { return getToken(PrevParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(PrevParser.STRING, 0); }
		public TerminalNode POINTER() { return getToken(PrevParser.POINTER, 0); }
		public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantExpression; }
	}

	public final ConstantExpressionContext constantExpression() throws RecognitionException {
		ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constantExpression);
		try {
			setState(377);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(365);
				((ConstantExpressionContext)_localctx).VOID = match(VOID);
				 PrevToken loc = (PrevToken)((ConstantExpressionContext)_localctx).VOID;
						((ConstantExpressionContext)_localctx).ast =  new AstAtomExpr(new Location(loc, loc), AstAtomExpr.Type.VOID, ((ConstantExpressionContext)_localctx).VOID.getText()); 
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(367);
				((ConstantExpressionContext)_localctx).BOOLEAN = match(BOOLEAN);
				 PrevToken loc = (PrevToken)((ConstantExpressionContext)_localctx).BOOLEAN;
						((ConstantExpressionContext)_localctx).ast =  new AstAtomExpr(new Location(loc, loc), AstAtomExpr.Type.BOOLEAN, ((ConstantExpressionContext)_localctx).BOOLEAN.getText()); 
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 3);
				{
				setState(369);
				((ConstantExpressionContext)_localctx).INTEGER = match(INTEGER);
				 PrevToken loc = (PrevToken)((ConstantExpressionContext)_localctx).INTEGER;
						((ConstantExpressionContext)_localctx).ast =  new AstAtomExpr(new Location(loc, loc), AstAtomExpr.Type.INTEGER, ((ConstantExpressionContext)_localctx).INTEGER.getText()); 
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(371);
				((ConstantExpressionContext)_localctx).CHAR = match(CHAR);
				 PrevToken loc = (PrevToken)((ConstantExpressionContext)_localctx).CHAR;
						((ConstantExpressionContext)_localctx).ast =  new AstAtomExpr(new Location(loc, loc), AstAtomExpr.Type.CHAR, ((ConstantExpressionContext)_localctx).CHAR.getText()); 
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(373);
				((ConstantExpressionContext)_localctx).STRING = match(STRING);
				 PrevToken loc = (PrevToken)((ConstantExpressionContext)_localctx).STRING;
						((ConstantExpressionContext)_localctx).ast =  new AstAtomExpr(new Location(loc, loc), AstAtomExpr.Type.STRING, ((ConstantExpressionContext)_localctx).STRING.getText()); 
				}
				break;
			case POINTER:
				enterOuterAlt(_localctx, 6);
				{
				setState(375);
				((ConstantExpressionContext)_localctx).POINTER = match(POINTER);
				 PrevToken loc = (PrevToken)((ConstantExpressionContext)_localctx).POINTER;
						((ConstantExpressionContext)_localctx).ast =  new AstAtomExpr(new Location(loc, loc), AstAtomExpr.Type.POINTER, ((ConstantExpressionContext)_localctx).POINTER.getText()); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableAccessContext extends ParserRuleContext {
		public AstNameExpr ast;
		public Token IDENTIFIER;
		public FunctionCallContext functionCall;
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public VariableAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableAccess; }
	}

	public final VariableAccessContext variableAccess() throws RecognitionException {
		VariableAccessContext _localctx = new VariableAccessContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_variableAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			((VariableAccessContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(380);
			((VariableAccessContext)_localctx).functionCall = functionCall();

					if (((VariableAccessContext)_localctx).functionCall.args == null) {
						PrevToken loc = (PrevToken)((VariableAccessContext)_localctx).IDENTIFIER;
						((VariableAccessContext)_localctx).ast =  new AstNameExpr(new Location(loc,loc), ((VariableAccessContext)_localctx).IDENTIFIER.getText());
					} else {
						((VariableAccessContext)_localctx).ast =  new AstCallExpr(
							new Location((PrevToken)((VariableAccessContext)_localctx).IDENTIFIER,((VariableAccessContext)_localctx).functionCall.end), ((VariableAccessContext)_localctx).IDENTIFIER.getText(), ((VariableAccessContext)_localctx).functionCall.args);
					}
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public AstTrees<AstExpr> args;
		public Vector<AstExpr> vec;
		public PrevToken end;
		public ExprContext firstExpr;
		public ExprContext exprs;
		public Token SYMBOL_RBR;
		public TerminalNode SYMBOL_LBR() { return getToken(PrevParser.SYMBOL_LBR, 0); }
		public TerminalNode SYMBOL_RBR() { return getToken(PrevParser.SYMBOL_RBR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> SYMBOL_COMMA() { return getTokens(PrevParser.SYMBOL_COMMA); }
		public TerminalNode SYMBOL_COMMA(int i) {
			return getToken(PrevParser.SYMBOL_COMMA, i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_functionCall);
		((FunctionCallContext)_localctx).vec =  new Vector<AstExpr>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(383);
				match(SYMBOL_LBR);
				setState(395);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOLEAN) | (1L << KEYWORD_DEL) | (1L << KEYWORD_NEW) | (1L << INTEGER) | (1L << CHAR) | (1L << STRING) | (1L << POINTER) | (1L << SYMBOL_LBR) | (1L << SYMBOL_LCBR) | (1L << SYMBOL_EXCL) | (1L << SYMBOL_PLUS) | (1L << SYMBOL_MIN) | (1L << SYMBOL_PWR) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(384);
					((FunctionCallContext)_localctx).firstExpr = expr(0);
					_localctx.vec.add(((FunctionCallContext)_localctx).firstExpr.ast);
					setState(392);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SYMBOL_COMMA) {
						{
						{
						setState(386);
						match(SYMBOL_COMMA);
						setState(387);
						((FunctionCallContext)_localctx).exprs = expr(0);
						_localctx.vec.add(((FunctionCallContext)_localctx).exprs.ast);
						}
						}
						setState(394);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(397);
				((FunctionCallContext)_localctx).SYMBOL_RBR = match(SYMBOL_RBR);
				((FunctionCallContext)_localctx).args =  new AstTrees<AstExpr>(_localctx.vec); ((FunctionCallContext)_localctx).end =  (PrevToken)((FunctionCallContext)_localctx).SYMBOL_RBR; 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompoundExpressionContext extends ParserRuleContext {
		public AstStmtExpr ast;
		public Vector<AstStmt> stmts;
		public Token SYMBOL_LCBR;
		public StmtContext stmt;
		public Token SYMBOL_RCBR;
		public TerminalNode SYMBOL_LCBR() { return getToken(PrevParser.SYMBOL_LCBR, 0); }
		public TerminalNode SYMBOL_RCBR() { return getToken(PrevParser.SYMBOL_RCBR, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<TerminalNode> SYMBOL_SEMIC() { return getTokens(PrevParser.SYMBOL_SEMIC); }
		public TerminalNode SYMBOL_SEMIC(int i) {
			return getToken(PrevParser.SYMBOL_SEMIC, i);
		}
		public CompoundExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundExpression; }
	}

	public final CompoundExpressionContext compoundExpression() throws RecognitionException {
		CompoundExpressionContext _localctx = new CompoundExpressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_compoundExpression);
		((CompoundExpressionContext)_localctx).stmts =  new Vector<AstStmt>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			((CompoundExpressionContext)_localctx).SYMBOL_LCBR = match(SYMBOL_LCBR);
			setState(406); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(402);
				((CompoundExpressionContext)_localctx).stmt = stmt();
				setState(403);
				match(SYMBOL_SEMIC);
				_localctx.stmts.add(((CompoundExpressionContext)_localctx).stmt.ast);
				}
				}
				setState(408); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOLEAN) | (1L << KEYWORD_DEL) | (1L << KEYWORD_IF) | (1L << KEYWORD_NEW) | (1L << KEYWORD_WHILE) | (1L << INTEGER) | (1L << CHAR) | (1L << STRING) | (1L << POINTER) | (1L << SYMBOL_LBR) | (1L << SYMBOL_LCBR) | (1L << SYMBOL_EXCL) | (1L << SYMBOL_PLUS) | (1L << SYMBOL_MIN) | (1L << SYMBOL_PWR) | (1L << IDENTIFIER))) != 0) );
			setState(410);
			((CompoundExpressionContext)_localctx).SYMBOL_RCBR = match(SYMBOL_RCBR);

					((CompoundExpressionContext)_localctx).ast =  new AstStmtExpr(new Location((PrevToken)((CompoundExpressionContext)_localctx).SYMBOL_LCBR,(PrevToken)((CompoundExpressionContext)_localctx).SYMBOL_RCBR),
						new AstTrees<AstStmt>(_localctx.stmts)
				); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnclosedExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public Token SYMBOL_LBR;
		public ExprContext expr;
		public TypecastExpressionContext typecastExpression;
		public Token SYMBOL_RBR;
		public TerminalNode SYMBOL_LBR() { return getToken(PrevParser.SYMBOL_LBR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TypecastExpressionContext typecastExpression() {
			return getRuleContext(TypecastExpressionContext.class,0);
		}
		public TerminalNode SYMBOL_RBR() { return getToken(PrevParser.SYMBOL_RBR, 0); }
		public EnclosedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enclosedExpression; }
	}

	public final EnclosedExpressionContext enclosedExpression() throws RecognitionException {
		EnclosedExpressionContext _localctx = new EnclosedExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_enclosedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			((EnclosedExpressionContext)_localctx).SYMBOL_LBR = match(SYMBOL_LBR);
			setState(414);
			((EnclosedExpressionContext)_localctx).expr = expr(0);
			setState(415);
			((EnclosedExpressionContext)_localctx).typecastExpression = typecastExpression();
			setState(416);
			((EnclosedExpressionContext)_localctx).SYMBOL_RBR = match(SYMBOL_RBR);

					if (((EnclosedExpressionContext)_localctx).typecastExpression.typp == null) {
						((EnclosedExpressionContext)_localctx).ast =  ((EnclosedExpressionContext)_localctx).expr.ast;
					} else {
						((EnclosedExpressionContext)_localctx).ast =  new AstCastExpr(
							new Location((PrevToken)((EnclosedExpressionContext)_localctx).SYMBOL_LBR,(PrevToken)((EnclosedExpressionContext)_localctx).SYMBOL_RBR), ((EnclosedExpressionContext)_localctx).expr.ast, ((EnclosedExpressionContext)_localctx).typecastExpression.typp
						);
					}
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypecastExpressionContext extends ParserRuleContext {
		public AstType typp;
		public TypeContext type;
		public TerminalNode SYMBOL_COLON() { return getToken(PrevParser.SYMBOL_COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypecastExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typecastExpression; }
	}

	public final TypecastExpressionContext typecastExpression() throws RecognitionException {
		TypecastExpressionContext _localctx = new TypecastExpressionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_typecastExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SYMBOL_COLON) {
				{
				setState(419);
				match(SYMBOL_COLON);
				setState(420);
				((TypecastExpressionContext)_localctx).type = type(0);
				((TypecastExpressionContext)_localctx).typp =  ((TypecastExpressionContext)_localctx).type.ast;
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public AstStmt ast;
		public ExpressionStatementContext expressionStatement;
		public AssignmentStatementContext assignmentStatement;
		public ConditionalStatementContext conditionalStatement;
		public LoopStatementContext loopStatement;
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public ConditionalStatementContext conditionalStatement() {
			return getRuleContext(ConditionalStatementContext.class,0);
		}
		public LoopStatementContext loopStatement() {
			return getRuleContext(LoopStatementContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_stmt);
		try {
			setState(437);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(425);
				((StmtContext)_localctx).expressionStatement = expressionStatement();
				((StmtContext)_localctx).ast =  ((StmtContext)_localctx).expressionStatement.ast; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(428);
				((StmtContext)_localctx).assignmentStatement = assignmentStatement();
				((StmtContext)_localctx).ast =  ((StmtContext)_localctx).assignmentStatement.ast; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(431);
				((StmtContext)_localctx).conditionalStatement = conditionalStatement();
				((StmtContext)_localctx).ast =  ((StmtContext)_localctx).conditionalStatement.ast; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(434);
				((StmtContext)_localctx).loopStatement = loopStatement();
				((StmtContext)_localctx).ast =  ((StmtContext)_localctx).loopStatement.ast; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public AstExprStmt ast;
		public ExprContext expr;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			((ExpressionStatementContext)_localctx).expr = expr(0);
			((ExpressionStatementContext)_localctx).ast =  new AstExprStmt(new Location(((ExpressionStatementContext)_localctx).expr.ast, ((ExpressionStatementContext)_localctx).expr.ast), ((ExpressionStatementContext)_localctx).expr.ast); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentStatementContext extends ParserRuleContext {
		public AstAssignStmt ast;
		public ExprContext dst;
		public ExprContext src;
		public TerminalNode SYMBOL_EQUALS() { return getToken(PrevParser.SYMBOL_EQUALS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			((AssignmentStatementContext)_localctx).dst = expr(0);
			setState(443);
			match(SYMBOL_EQUALS);
			setState(444);
			((AssignmentStatementContext)_localctx).src = expr(0);
			((AssignmentStatementContext)_localctx).ast =  new AstAssignStmt(new Location(((AssignmentStatementContext)_localctx).dst.ast, ((AssignmentStatementContext)_localctx).src.ast), ((AssignmentStatementContext)_localctx).dst.ast, ((AssignmentStatementContext)_localctx).src.ast); 
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalStatementContext extends ParserRuleContext {
		public AstIfStmt ast;
		public Token KEYWORD_IF;
		public ExprContext expr;
		public StmtContext thn;
		public StmtContext els;
		public TerminalNode KEYWORD_IF() { return getToken(PrevParser.KEYWORD_IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode KEYWORD_THEN() { return getToken(PrevParser.KEYWORD_THEN, 0); }
		public TerminalNode KEYWORD_ELSE() { return getToken(PrevParser.KEYWORD_ELSE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public ConditionalStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalStatement; }
	}

	public final ConditionalStatementContext conditionalStatement() throws RecognitionException {
		ConditionalStatementContext _localctx = new ConditionalStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_conditionalStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			((ConditionalStatementContext)_localctx).KEYWORD_IF = match(KEYWORD_IF);
			setState(448);
			((ConditionalStatementContext)_localctx).expr = expr(0);
			setState(449);
			match(KEYWORD_THEN);
			setState(450);
			((ConditionalStatementContext)_localctx).thn = stmt();
			setState(451);
			match(KEYWORD_ELSE);
			setState(452);
			((ConditionalStatementContext)_localctx).els = stmt();

					((ConditionalStatementContext)_localctx).ast =  new AstIfStmt(new Location((PrevToken)((ConditionalStatementContext)_localctx).KEYWORD_IF, ((ConditionalStatementContext)_localctx).els.ast), ((ConditionalStatementContext)_localctx).expr.ast, ((ConditionalStatementContext)_localctx).thn.ast, ((ConditionalStatementContext)_localctx).els.ast); 
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopStatementContext extends ParserRuleContext {
		public AstWhileStmt ast;
		public Token KEYWORD_WHILE;
		public ExprContext expr;
		public StmtContext stmt;
		public TerminalNode KEYWORD_WHILE() { return getToken(PrevParser.KEYWORD_WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode KEYWORD_DO() { return getToken(PrevParser.KEYWORD_DO, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public LoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopStatement; }
	}

	public final LoopStatementContext loopStatement() throws RecognitionException {
		LoopStatementContext _localctx = new LoopStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_loopStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			((LoopStatementContext)_localctx).KEYWORD_WHILE = match(KEYWORD_WHILE);
			setState(456);
			((LoopStatementContext)_localctx).expr = expr(0);
			setState(457);
			match(KEYWORD_DO);
			setState(458);
			((LoopStatementContext)_localctx).stmt = stmt();

					((LoopStatementContext)_localctx).ast =  new AstWhileStmt(new Location((PrevToken)((LoopStatementContext)_localctx).KEYWORD_WHILE, ((LoopStatementContext)_localctx).stmt.ast), ((LoopStatementContext)_localctx).expr.ast, ((LoopStatementContext)_localctx).stmt.ast);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 17:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\65\u01d0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\6\2P\n\2\r\2\16\2Q\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3]\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u0080\n\7\f\7\16\7\u0083\13\7\5"+
		"\7\u0085\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\5\b\u0097\n\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u009f\n\b\f\b\16\b\u00a2"+
		"\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00ac\n\t\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\7\r\u00c4\n\r\f\r\16\r\u00c7\13\r\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00da\n\17"+
		"\3\20\3\20\3\20\3\20\5\20\u00e0\n\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u00e8\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u00f6\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\6\23\u0103\n\23\r\23\16\23\u0104\3\23\3\23\3\23\7\23\u010a\n\23\f\23"+
		"\16\23\u010d\13\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u0115\n\24\f\24"+
		"\16\24\u0118\13\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0120\n\25\f\25"+
		"\16\25\u0123\13\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u012b\n\26\f\26"+
		"\16\26\u012e\13\26\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u0136\n\27\f\27"+
		"\16\27\u0139\13\27\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u0141\n\30\f\30"+
		"\16\30\u0144\13\30\3\31\3\31\3\31\7\31\u0149\n\31\f\31\16\31\u014c\13"+
		"\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\7\32\u015d\n\32\f\32\16\32\u0160\13\32\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u016e\n\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u017c\n\34\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u0189\n\36\f\36\16"+
		"\36\u018c\13\36\5\36\u018e\n\36\3\36\3\36\5\36\u0192\n\36\3\37\3\37\3"+
		"\37\3\37\3\37\6\37\u0199\n\37\r\37\16\37\u019a\3\37\3\37\3\37\3 \3 \3"+
		" \3 \3 \3 \3!\3!\3!\3!\5!\u01aa\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\5\"\u01b8\n\"\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3"+
		"%\3%\3&\3&\3&\3&\3&\3&\3&\2\4\16$\'\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2\2\2\u01e0\2O\3\2\2\2\4\\\3\2\2\2"+
		"\6^\3\2\2\2\bd\3\2\2\2\nj\3\2\2\2\f\u0084\3\2\2\2\16\u0096\3\2\2\2\20"+
		"\u00ab\3\2\2\2\22\u00ad\3\2\2\2\24\u00b0\3\2\2\2\26\u00b4\3\2\2\2\30\u00b9"+
		"\3\2\2\2\32\u00c8\3\2\2\2\34\u00d9\3\2\2\2\36\u00df\3\2\2\2 \u00e7\3\2"+
		"\2\2\"\u00f5\3\2\2\2$\u00f7\3\2\2\2&\u010e\3\2\2\2(\u0119\3\2\2\2*\u0124"+
		"\3\2\2\2,\u012f\3\2\2\2.\u013a\3\2\2\2\60\u014a\3\2\2\2\62\u0150\3\2\2"+
		"\2\64\u016d\3\2\2\2\66\u017b\3\2\2\28\u017d\3\2\2\2:\u0191\3\2\2\2<\u0193"+
		"\3\2\2\2>\u019f\3\2\2\2@\u01a9\3\2\2\2B\u01b7\3\2\2\2D\u01b9\3\2\2\2F"+
		"\u01bc\3\2\2\2H\u01c1\3\2\2\2J\u01c9\3\2\2\2LM\5\4\3\2MN\b\2\1\2NP\3\2"+
		"\2\2OL\3\2\2\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2\2R\3\3\2\2\2ST\5\6\4\2TU\b"+
		"\3\1\2U]\3\2\2\2VW\5\b\5\2WX\b\3\1\2X]\3\2\2\2YZ\5\n\6\2Z[\b\3\1\2[]\3"+
		"\2\2\2\\S\3\2\2\2\\V\3\2\2\2\\Y\3\2\2\2]\5\3\2\2\2^_\7\17\2\2_`\7\62\2"+
		"\2`a\7\61\2\2ab\5\16\b\2bc\b\4\1\2c\7\3\2\2\2de\7\20\2\2ef\7\62\2\2fg"+
		"\7 \2\2gh\5\16\b\2hi\b\5\1\2i\t\3\2\2\2jk\7\n\2\2kl\7\62\2\2lm\7\30\2"+
		"\2mn\5\f\7\2no\7\31\2\2op\7 \2\2pq\5\16\b\2qr\7\61\2\2rs\5$\23\2st\b\6"+
		"\1\2t\13\3\2\2\2uv\7\62\2\2vw\7 \2\2wx\5\16\b\2x\u0081\b\7\1\2yz\7\37"+
		"\2\2z{\7\62\2\2{|\7 \2\2|}\5\16\b\2}~\b\7\1\2~\u0080\3\2\2\2\177y\3\2"+
		"\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0085"+
		"\3\2\2\2\u0083\u0081\3\2\2\2\u0084u\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\r\3\2\2\2\u0086\u0087\b\b\1\2\u0087\u0088\5\20\t\2\u0088\u0089\b\b\1"+
		"\2\u0089\u0097\3\2\2\2\u008a\u008b\5\22\n\2\u008b\u008c\b\b\1\2\u008c"+
		"\u0097\3\2\2\2\u008d\u008e\5\24\13\2\u008e\u008f\b\b\1\2\u008f\u0097\3"+
		"\2\2\2\u0090\u0091\5\26\f\2\u0091\u0092\b\b\1\2\u0092\u0097\3\2\2\2\u0093"+
		"\u0094\5\32\16\2\u0094\u0095\b\b\1\2\u0095\u0097\3\2\2\2\u0096\u0086\3"+
		"\2\2\2\u0096\u008a\3\2\2\2\u0096\u008d\3\2\2\2\u0096\u0090\3\2\2\2\u0096"+
		"\u0093\3\2\2\2\u0097\u00a0\3\2\2\2\u0098\u0099\f\b\2\2\u0099\u009a\7\34"+
		"\2\2\u009a\u009b\5$\23\2\u009b\u009c\7\35\2\2\u009c\u009d\b\b\1\2\u009d"+
		"\u009f\3\2\2\2\u009e\u0098\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2"+
		"\2\2\u00a0\u00a1\3\2\2\2\u00a1\17\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4"+
		"\7\21\2\2\u00a4\u00ac\b\t\1\2\u00a5\u00a6\7\6\2\2\u00a6\u00ac\b\t\1\2"+
		"\u00a7\u00a8\7\f\2\2\u00a8\u00ac\b\t\1\2\u00a9\u00aa\7\5\2\2\u00aa\u00ac"+
		"\b\t\1\2\u00ab\u00a3\3\2\2\2\u00ab\u00a5\3\2\2\2\u00ab\u00a7\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\21\3\2\2\2\u00ad\u00ae\7\62\2\2\u00ae\u00af\b\n\1"+
		"\2\u00af\23\3\2\2\2\u00b0\u00b1\7\60\2\2\u00b1\u00b2\5\16\b\2\u00b2\u00b3"+
		"\b\13\1\2\u00b3\25\3\2\2\2\u00b4\u00b5\7\32\2\2\u00b5\u00b6\5\30\r\2\u00b6"+
		"\u00b7\7\33\2\2\u00b7\u00b8\b\f\1\2\u00b8\27\3\2\2\2\u00b9\u00ba\7\62"+
		"\2\2\u00ba\u00bb\7 \2\2\u00bb\u00bc\5\16\b\2\u00bc\u00c5\b\r\1\2\u00bd"+
		"\u00be\7\37\2\2\u00be\u00bf\7\62\2\2\u00bf\u00c0\7 \2\2\u00c0\u00c1\5"+
		"\16\b\2\u00c1\u00c2\b\r\1\2\u00c2\u00c4\3\2\2\2\u00c3\u00bd\3\2\2\2\u00c4"+
		"\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\31\3\2\2"+
		"\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\7\30\2\2\u00c9\u00ca\5\16\b\2\u00ca"+
		"\u00cb\7\31\2\2\u00cb\u00cc\b\16\1\2\u00cc\33\3\2\2\2\u00cd\u00ce\7%\2"+
		"\2\u00ce\u00da\b\17\1\2\u00cf\u00d0\7&\2\2\u00d0\u00da\b\17\1\2\u00d1"+
		"\u00d2\7\'\2\2\u00d2\u00da\b\17\1\2\u00d3\u00d4\7(\2\2\u00d4\u00da\b\17"+
		"\1\2\u00d5\u00d6\7)\2\2\u00d6\u00da\b\17\1\2\u00d7\u00d8\7*\2\2\u00d8"+
		"\u00da\b\17\1\2\u00d9\u00cd\3\2\2\2\u00d9\u00cf\3\2\2\2\u00d9\u00d1\3"+
		"\2\2\2\u00d9\u00d3\3\2\2\2\u00d9\u00d5\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da"+
		"\35\3\2\2\2\u00db\u00dc\7.\2\2\u00dc\u00e0\b\20\1\2\u00dd\u00de\7/\2\2"+
		"\u00de\u00e0\b\20\1\2\u00df\u00db\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\37"+
		"\3\2\2\2\u00e1\u00e2\7+\2\2\u00e2\u00e8\b\21\1\2\u00e3\u00e4\7,\2\2\u00e4"+
		"\u00e8\b\21\1\2\u00e5\u00e6\7-\2\2\u00e6\u00e8\b\21\1\2\u00e7\u00e1\3"+
		"\2\2\2\u00e7\u00e3\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8!\3\2\2\2\u00e9\u00ea"+
		"\7$\2\2\u00ea\u00f6\b\22\1\2\u00eb\u00ec\7.\2\2\u00ec\u00f6\b\22\1\2\u00ed"+
		"\u00ee\7/\2\2\u00ee\u00f6\b\22\1\2\u00ef\u00f0\7\60\2\2\u00f0\u00f6\b"+
		"\22\1\2\u00f1\u00f2\7\r\2\2\u00f2\u00f6\b\22\1\2\u00f3\u00f4\7\7\2\2\u00f4"+
		"\u00f6\b\22\1\2\u00f5\u00e9\3\2\2\2\u00f5\u00eb\3\2\2\2\u00f5\u00ed\3"+
		"\2\2\2\u00f5\u00ef\3\2\2\2\u00f5\u00f1\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6"+
		"#\3\2\2\2\u00f7\u00f8\b\23\1\2\u00f8\u00f9\5&\24\2\u00f9\u00fa\b\23\1"+
		"\2\u00fa\u010b\3\2\2\2\u00fb\u00fc\f\4\2\2\u00fc\u00fd\7\22\2\2\u00fd"+
		"\u00fe\7\32\2\2\u00fe\u0102\b\23\1\2\u00ff\u0100\5\4\3\2\u0100\u0101\b"+
		"\23\1\2\u0101\u0103\3\2\2\2\u0102\u00ff\3\2\2\2\u0103\u0104\3\2\2\2\u0104"+
		"\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\7\33"+
		"\2\2\u0107\u0108\b\23\1\2\u0108\u010a\3\2\2\2\u0109\u00fb\3\2\2\2\u010a"+
		"\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c%\3\2\2\2"+
		"\u010d\u010b\3\2\2\2\u010e\u010f\5(\25\2\u010f\u0116\b\24\1\2\u0110\u0111"+
		"\7#\2\2\u0111\u0112\5(\25\2\u0112\u0113\b\24\1\2\u0113\u0115\3\2\2\2\u0114"+
		"\u0110\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2"+
		"\2\2\u0117\'\3\2\2\2\u0118\u0116\3\2\2\2\u0119\u011a\5*\26\2\u011a\u0121"+
		"\b\25\1\2\u011b\u011c\7\"\2\2\u011c\u011d\5*\26\2\u011d\u011e\b\25\1\2"+
		"\u011e\u0120\3\2\2\2\u011f\u011b\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f"+
		"\3\2\2\2\u0121\u0122\3\2\2\2\u0122)\3\2\2\2\u0123\u0121\3\2\2\2\u0124"+
		"\u0125\5,\27\2\u0125\u012c\b\26\1\2\u0126\u0127\5\34\17\2\u0127\u0128"+
		"\5,\27\2\u0128\u0129\b\26\1\2\u0129\u012b\3\2\2\2\u012a\u0126\3\2\2\2"+
		"\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d+\3"+
		"\2\2\2\u012e\u012c\3\2\2\2\u012f\u0130\5.\30\2\u0130\u0137\b\27\1\2\u0131"+
		"\u0132\5\36\20\2\u0132\u0133\5.\30\2\u0133\u0134\b\27\1\2\u0134\u0136"+
		"\3\2\2\2\u0135\u0131\3\2\2\2\u0136\u0139\3\2\2\2\u0137\u0135\3\2\2\2\u0137"+
		"\u0138\3\2\2\2\u0138-\3\2\2\2\u0139\u0137\3\2\2\2\u013a\u013b\5\60\31"+
		"\2\u013b\u0142\b\30\1\2\u013c\u013d\5 \21\2\u013d\u013e\5\60\31\2\u013e"+
		"\u013f\b\30\1\2\u013f\u0141\3\2\2\2\u0140\u013c\3\2\2\2\u0141\u0144\3"+
		"\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143/\3\2\2\2\u0144\u0142"+
		"\3\2\2\2\u0145\u0146\5\"\22\2\u0146\u0147\b\31\1\2\u0147\u0149\3\2\2\2"+
		"\u0148\u0145\3\2\2\2\u0149\u014c\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b"+
		"\3\2\2\2\u014b\u014d\3\2\2\2\u014c\u014a\3\2\2\2\u014d\u014e\5\62\32\2"+
		"\u014e\u014f\b\31\1\2\u014f\61\3\2\2\2\u0150\u0151\5\64\33\2\u0151\u015e"+
		"\b\32\1\2\u0152\u0153\7\34\2\2\u0153\u0154\5$\23\2\u0154\u0155\7\35\2"+
		"\2\u0155\u0156\b\32\1\2\u0156\u015d\3\2\2\2\u0157\u0158\7\60\2\2\u0158"+
		"\u015d\b\32\1\2\u0159\u015a\7\36\2\2\u015a\u015b\7\62\2\2\u015b\u015d"+
		"\b\32\1\2\u015c\u0152\3\2\2\2\u015c\u0157\3\2\2\2\u015c\u0159\3\2\2\2"+
		"\u015d\u0160\3\2\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f\63"+
		"\3\2\2\2\u0160\u015e\3\2\2\2\u0161\u0162\5\66\34\2\u0162\u0163\b\33\1"+
		"\2\u0163\u016e\3\2\2\2\u0164\u0165\58\35\2\u0165\u0166\b\33\1\2\u0166"+
		"\u016e\3\2\2\2\u0167\u0168\5<\37\2\u0168\u0169\b\33\1\2\u0169\u016e\3"+
		"\2\2\2\u016a\u016b\5> \2\u016b\u016c\b\33\1\2\u016c\u016e\3\2\2\2\u016d"+
		"\u0161\3\2\2\2\u016d\u0164\3\2\2\2\u016d\u0167\3\2\2\2\u016d\u016a\3\2"+
		"\2\2\u016e\65\3\2\2\2\u016f\u0170\7\3\2\2\u0170\u017c\b\34\1\2\u0171\u0172"+
		"\7\4\2\2\u0172\u017c\b\34\1\2\u0173\u0174\7\24\2\2\u0174\u017c\b\34\1"+
		"\2\u0175\u0176\7\25\2\2\u0176\u017c\b\34\1\2\u0177\u0178\7\26\2\2\u0178"+
		"\u017c\b\34\1\2\u0179\u017a\7\27\2\2\u017a\u017c\b\34\1\2\u017b\u016f"+
		"\3\2\2\2\u017b\u0171\3\2\2\2\u017b\u0173\3\2\2\2\u017b\u0175\3\2\2\2\u017b"+
		"\u0177\3\2\2\2\u017b\u0179\3\2\2\2\u017c\67\3\2\2\2\u017d\u017e\7\62\2"+
		"\2\u017e\u017f\5:\36\2\u017f\u0180\b\35\1\2\u01809\3\2\2\2\u0181\u018d"+
		"\7\30\2\2\u0182\u0183\5$\23\2\u0183\u018a\b\36\1\2\u0184\u0185\7\37\2"+
		"\2\u0185\u0186\5$\23\2\u0186\u0187\b\36\1\2\u0187\u0189\3\2\2\2\u0188"+
		"\u0184\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2\2\2\u018a\u018b\3\2"+
		"\2\2\u018b\u018e\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u0182\3\2\2\2\u018d"+
		"\u018e\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0190\7\31\2\2\u0190\u0192\b"+
		"\36\1\2\u0191\u0181\3\2\2\2\u0191\u0192\3\2\2\2\u0192;\3\2\2\2\u0193\u0198"+
		"\7\32\2\2\u0194\u0195\5B\"\2\u0195\u0196\7!\2\2\u0196\u0197\b\37\1\2\u0197"+
		"\u0199\3\2\2\2\u0198\u0194\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u0198\3\2"+
		"\2\2\u019a\u019b\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019d\7\33\2\2\u019d"+
		"\u019e\b\37\1\2\u019e=\3\2\2\2\u019f\u01a0\7\30\2\2\u01a0\u01a1\5$\23"+
		"\2\u01a1\u01a2\5@!\2\u01a2\u01a3\7\31\2\2\u01a3\u01a4\b \1\2\u01a4?\3"+
		"\2\2\2\u01a5\u01a6\7 \2\2\u01a6\u01a7\5\16\b\2\u01a7\u01a8\b!\1\2\u01a8"+
		"\u01aa\3\2\2\2\u01a9\u01a5\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aaA\3\2\2\2"+
		"\u01ab\u01ac\5D#\2\u01ac\u01ad\b\"\1\2\u01ad\u01b8\3\2\2\2\u01ae\u01af"+
		"\5F$\2\u01af\u01b0\b\"\1\2\u01b0\u01b8\3\2\2\2\u01b1\u01b2\5H%\2\u01b2"+
		"\u01b3\b\"\1\2\u01b3\u01b8\3\2\2\2\u01b4\u01b5\5J&\2\u01b5\u01b6\b\"\1"+
		"\2\u01b6\u01b8\3\2\2\2\u01b7\u01ab\3\2\2\2\u01b7\u01ae\3\2\2\2\u01b7\u01b1"+
		"\3\2\2\2\u01b7\u01b4\3\2\2\2\u01b8C\3\2\2\2\u01b9\u01ba\5$\23\2\u01ba"+
		"\u01bb\b#\1\2\u01bbE\3\2\2\2\u01bc\u01bd\5$\23\2\u01bd\u01be\7\61\2\2"+
		"\u01be\u01bf\5$\23\2\u01bf\u01c0\b$\1\2\u01c0G\3\2\2\2\u01c1\u01c2\7\13"+
		"\2\2\u01c2\u01c3\5$\23\2\u01c3\u01c4\7\16\2\2\u01c4\u01c5\5B\"\2\u01c5"+
		"\u01c6\7\t\2\2\u01c6\u01c7\5B\"\2\u01c7\u01c8\b%\1\2\u01c8I\3\2\2\2\u01c9"+
		"\u01ca\7\23\2\2\u01ca\u01cb\5$\23\2\u01cb\u01cc\7\b\2\2\u01cc\u01cd\5"+
		"B\"\2\u01cd\u01ce\b&\1\2\u01ceK\3\2\2\2 Q\\\u0081\u0084\u0096\u00a0\u00ab"+
		"\u00c5\u00d9\u00df\u00e7\u00f5\u0104\u010b\u0116\u0121\u012c\u0137\u0142"+
		"\u014a\u015c\u015e\u016d\u017b\u018a\u018d\u0191\u019a\u01a9\u01b7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}