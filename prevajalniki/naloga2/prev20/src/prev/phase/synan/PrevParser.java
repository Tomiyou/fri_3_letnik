// Generated from synan/PrevParser.g4 by ANTLR 4.8


	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.lexan.*;


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
		RULE_constantExpression = 26, RULE_constant = 27, RULE_variableAccess = 28, 
		RULE_functionCall = 29, RULE_compoundExpression = 30, RULE_enclosedExpression = 31, 
		RULE_typecastExpression = 32, RULE_stmt = 33, RULE_expressionStatement = 34, 
		RULE_assignmentStatement = 35, RULE_conditionalStatement = 36, RULE_loopStatement = 37;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "decl", "typeDeclaration", "variableDeclaration", "functionDeclaration", 
			"funcArgRepeat", "type", "atomicType", "namedType", "pointerType", "recordType", 
			"recTypeRepeat", "enclosedType", "relOps", "addOps", "mulOps", "prefOps", 
			"expr", "disjunctionExpression", "conjunctionExpression", "relationalExpression", 
			"additiveExpression", "multiplicativeExpression", "prefixExpression", 
			"postfixExpression", "atomicExpression", "constantExpression", "constant", 
			"variableAccess", "functionCall", "compoundExpression", "enclosedExpression", 
			"typecastExpression", "stmt", "expressionStatement", "assignmentStatement", 
			"conditionalStatement", "loopStatement"
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
				setState(76);
				decl();
				}
				}
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KEYWORD_FUN) | (1L << KEYWORD_TYP) | (1L << KEYWORD_VAR))) != 0) );
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

	public static class DeclContext extends ParserRuleContext {
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
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEYWORD_TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				typeDeclaration();
				}
				break;
			case KEYWORD_VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				variableDeclaration();
				}
				break;
			case KEYWORD_FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				functionDeclaration();
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
			setState(86);
			match(KEYWORD_TYP);
			setState(87);
			match(IDENTIFIER);
			setState(88);
			match(SYMBOL_EQUALS);
			setState(89);
			type(0);
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
			setState(91);
			match(KEYWORD_VAR);
			setState(92);
			match(IDENTIFIER);
			setState(93);
			match(SYMBOL_COLON);
			setState(94);
			type(0);
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
			setState(96);
			match(KEYWORD_FUN);
			setState(97);
			match(IDENTIFIER);
			setState(98);
			match(SYMBOL_LBR);
			setState(99);
			funcArgRepeat();
			setState(100);
			match(SYMBOL_RBR);
			setState(101);
			match(SYMBOL_COLON);
			setState(102);
			type(0);
			setState(103);
			match(SYMBOL_EQUALS);
			setState(104);
			expr(0);
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
		public Token firstArg;
		public TypeContext firstType;
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(106);
				((FuncArgRepeatContext)_localctx).firstArg = match(IDENTIFIER);
				setState(107);
				match(SYMBOL_COLON);
				setState(108);
				((FuncArgRepeatContext)_localctx).firstType = type(0);
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SYMBOL_COMMA) {
					{
					{
					setState(109);
					match(SYMBOL_COMMA);
					setState(110);
					((FuncArgRepeatContext)_localctx).args = match(IDENTIFIER);
					setState(111);
					match(SYMBOL_COLON);
					setState(112);
					((FuncArgRepeatContext)_localctx).typp = type(0);
					}
					}
					setState(117);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext rek;
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
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEYWORD_BOOL:
			case KEYWORD_CHAR:
			case KEYWORD_INT:
			case KEYWORD_VOID:
				{
				setState(121);
				atomicType();
				}
				break;
			case IDENTIFIER:
				{
				setState(122);
				namedType();
				}
				break;
			case SYMBOL_PWR:
				{
				setState(123);
				pointerType();
				}
				break;
			case SYMBOL_LCBR:
				{
				setState(124);
				recordType();
				}
				break;
			case SYMBOL_LBR:
				{
				setState(125);
				enclosedType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(135);
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
					setState(128);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(129);
					match(SYMBOL_LSBR);
					setState(130);
					expr(0);
					setState(131);
					match(SYMBOL_RSBR);
					}
					} 
				}
				setState(137);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KEYWORD_BOOL) | (1L << KEYWORD_CHAR) | (1L << KEYWORD_INT) | (1L << KEYWORD_VOID))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class NamedTypeContext extends ParserRuleContext {
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
			setState(140);
			match(IDENTIFIER);
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
			setState(142);
			match(SYMBOL_PWR);
			setState(143);
			type(0);
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
			setState(145);
			match(SYMBOL_LCBR);
			setState(146);
			recTypeRepeat();
			setState(147);
			match(SYMBOL_RCBR);
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
		public Token firstArg;
		public TypeContext firstType;
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			((RecTypeRepeatContext)_localctx).firstArg = match(IDENTIFIER);
			setState(150);
			match(SYMBOL_COLON);
			setState(151);
			((RecTypeRepeatContext)_localctx).firstType = type(0);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SYMBOL_COMMA) {
				{
				{
				setState(152);
				match(SYMBOL_COMMA);
				setState(153);
				((RecTypeRepeatContext)_localctx).args = match(IDENTIFIER);
				setState(154);
				match(SYMBOL_COLON);
				setState(155);
				((RecTypeRepeatContext)_localctx).typp = type(0);
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class EnclosedTypeContext extends ParserRuleContext {
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
			setState(161);
			match(SYMBOL_LBR);
			setState(162);
			type(0);
			setState(163);
			match(SYMBOL_RBR);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SYMBOL_EQ) | (1L << SYMBOL_NEQ) | (1L << SYMBOL_LT) | (1L << SYMBOL_GT) | (1L << SYMBOL_LEQ) | (1L << SYMBOL_GEQ))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class AddOpsContext extends ParserRuleContext {
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_la = _input.LA(1);
			if ( !(_la==SYMBOL_PLUS || _la==SYMBOL_MIN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class MulOpsContext extends ParserRuleContext {
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SYMBOL_STAR) | (1L << SYMBOL_SLASH) | (1L << SYMBOL_MOD))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class PrefOpsContext extends ParserRuleContext {
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KEYWORD_DEL) | (1L << KEYWORD_NEW) | (1L << SYMBOL_EXCL) | (1L << SYMBOL_PLUS) | (1L << SYMBOL_MIN) | (1L << SYMBOL_PWR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext eeee;
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
			setState(174);
			disjunctionExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(188);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
					setState(176);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(177);
					match(KEYWORD_WHERE);
					setState(178);
					match(SYMBOL_LCBR);
					setState(180); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(179);
						decl();
						}
						}
						setState(182); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KEYWORD_FUN) | (1L << KEYWORD_TYP) | (1L << KEYWORD_VAR))) != 0) );
					setState(184);
					match(SYMBOL_RCBR);
					}
					} 
				}
				setState(190);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
		public ConjunctionExpressionContext first;
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
			setState(191);
			((DisjunctionExpressionContext)_localctx).first = conjunctionExpression();
			setState(196);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(192);
					match(SYMBOL_OR);
					setState(193);
					((DisjunctionExpressionContext)_localctx).second = conjunctionExpression();
					}
					} 
				}
				setState(198);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
		public RelationalExpressionContext first;
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
			setState(199);
			((ConjunctionExpressionContext)_localctx).first = relationalExpression();
			setState(204);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(200);
					match(SYMBOL_AND);
					setState(201);
					((ConjunctionExpressionContext)_localctx).second = relationalExpression();
					}
					} 
				}
				setState(206);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		public AdditiveExpressionContext first;
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
			setState(207);
			((RelationalExpressionContext)_localctx).first = additiveExpression();
			setState(213);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(208);
					relOps();
					setState(209);
					((RelationalExpressionContext)_localctx).second = additiveExpression();
					}
					} 
				}
				setState(215);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		public MultiplicativeExpressionContext first;
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
			setState(216);
			((AdditiveExpressionContext)_localctx).first = multiplicativeExpression();
			setState(222);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(217);
					addOps();
					setState(218);
					((AdditiveExpressionContext)_localctx).second = multiplicativeExpression();
					}
					} 
				}
				setState(224);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
		public PrefixExpressionContext first;
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
			setState(225);
			((MultiplicativeExpressionContext)_localctx).first = prefixExpression();
			setState(231);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(226);
					mulOps();
					setState(227);
					((MultiplicativeExpressionContext)_localctx).second = prefixExpression();
					}
					} 
				}
				setState(233);
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
			exitRule();
		}
		return _localctx;
	}

	public static class PrefixExpressionContext extends ParserRuleContext {
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KEYWORD_DEL) | (1L << KEYWORD_NEW) | (1L << SYMBOL_EXCL) | (1L << SYMBOL_PLUS) | (1L << SYMBOL_MIN) | (1L << SYMBOL_PWR))) != 0)) {
				{
				{
				setState(234);
				prefOps();
				}
				}
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(240);
			postfixExpression();
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
			setState(242);
			atomicExpression();
			setState(252);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(250);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case SYMBOL_LSBR:
						{
						setState(243);
						match(SYMBOL_LSBR);
						setState(244);
						expr(0);
						setState(245);
						match(SYMBOL_RSBR);
						}
						break;
					case SYMBOL_PWR:
						{
						setState(247);
						match(SYMBOL_PWR);
						}
						break;
					case SYMBOL_DOT:
						{
						setState(248);
						match(SYMBOL_DOT);
						setState(249);
						match(IDENTIFIER);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(254);
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

	public static class AtomicExpressionContext extends ParserRuleContext {
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
			setState(259);
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
				setState(255);
				constantExpression();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				variableAccess();
				}
				break;
			case SYMBOL_LCBR:
				enterOuterAlt(_localctx, 3);
				{
				setState(257);
				compoundExpression();
				}
				break;
			case SYMBOL_LBR:
				enterOuterAlt(_localctx, 4);
				{
				setState(258);
				enclosedExpression();
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
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantExpression; }
	}

	public final ConstantExpressionContext constantExpression() throws RecognitionException {
		ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constantExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			constant();
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

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(PrevParser.VOID, 0); }
		public TerminalNode BOOLEAN() { return getToken(PrevParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(PrevParser.INTEGER, 0); }
		public TerminalNode CHAR() { return getToken(PrevParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(PrevParser.STRING, 0); }
		public TerminalNode POINTER() { return getToken(PrevParser.POINTER, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOLEAN) | (1L << INTEGER) | (1L << CHAR) | (1L << STRING) | (1L << POINTER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class VariableAccessContext extends ParserRuleContext {
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
		enterRule(_localctx, 56, RULE_variableAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(IDENTIFIER);
			setState(266);
			functionCall();
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
		public ExprContext firstExpr;
		public ExprContext exprs;
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
		enterRule(_localctx, 58, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(268);
				match(SYMBOL_LBR);
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOLEAN) | (1L << KEYWORD_DEL) | (1L << KEYWORD_NEW) | (1L << INTEGER) | (1L << CHAR) | (1L << STRING) | (1L << POINTER) | (1L << SYMBOL_LBR) | (1L << SYMBOL_LCBR) | (1L << SYMBOL_EXCL) | (1L << SYMBOL_PLUS) | (1L << SYMBOL_MIN) | (1L << SYMBOL_PWR) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(269);
					((FunctionCallContext)_localctx).firstExpr = expr(0);
					setState(274);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SYMBOL_COMMA) {
						{
						{
						setState(270);
						match(SYMBOL_COMMA);
						setState(271);
						((FunctionCallContext)_localctx).exprs = expr(0);
						}
						}
						setState(276);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(279);
				match(SYMBOL_RBR);
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
		enterRule(_localctx, 60, RULE_compoundExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(SYMBOL_LCBR);
			setState(286); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(283);
				stmt();
				setState(284);
				match(SYMBOL_SEMIC);
				}
				}
				setState(288); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOLEAN) | (1L << KEYWORD_DEL) | (1L << KEYWORD_IF) | (1L << KEYWORD_NEW) | (1L << KEYWORD_WHILE) | (1L << INTEGER) | (1L << CHAR) | (1L << STRING) | (1L << POINTER) | (1L << SYMBOL_LBR) | (1L << SYMBOL_LCBR) | (1L << SYMBOL_EXCL) | (1L << SYMBOL_PLUS) | (1L << SYMBOL_MIN) | (1L << SYMBOL_PWR) | (1L << IDENTIFIER))) != 0) );
			setState(290);
			match(SYMBOL_RCBR);
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
		enterRule(_localctx, 62, RULE_enclosedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(SYMBOL_LBR);
			setState(293);
			expr(0);
			setState(294);
			typecastExpression();
			setState(295);
			match(SYMBOL_RBR);
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
		enterRule(_localctx, 64, RULE_typecastExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SYMBOL_COLON) {
				{
				setState(297);
				match(SYMBOL_COLON);
				setState(298);
				type(0);
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
		enterRule(_localctx, 66, RULE_stmt);
		try {
			setState(305);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(301);
				expressionStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(302);
				assignmentStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(303);
				conditionalStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(304);
				loopStatement();
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
		enterRule(_localctx, 68, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			expr(0);
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
		enterRule(_localctx, 70, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			((AssignmentStatementContext)_localctx).dst = expr(0);
			setState(310);
			match(SYMBOL_EQUALS);
			setState(311);
			((AssignmentStatementContext)_localctx).src = expr(0);
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
		enterRule(_localctx, 72, RULE_conditionalStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			match(KEYWORD_IF);
			setState(314);
			expr(0);
			setState(315);
			match(KEYWORD_THEN);
			setState(316);
			((ConditionalStatementContext)_localctx).thn = stmt();
			setState(317);
			match(KEYWORD_ELSE);
			setState(318);
			((ConditionalStatementContext)_localctx).els = stmt();
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
		enterRule(_localctx, 74, RULE_loopStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(KEYWORD_WHILE);
			setState(321);
			expr(0);
			setState(322);
			match(KEYWORD_DO);
			setState(323);
			stmt();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\65\u0148\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\6\2P\n\2\r\2\16\2Q\3\3"+
		"\3\3\3\3\5\3W\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7t\n\7\f\7"+
		"\16\7w\13\7\5\7y\n\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0081\n\b\3\b\3\b\3\b"+
		"\3\b\3\b\7\b\u0088\n\b\f\b\16\b\u008b\13\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u009f\n\r\f\r\16\r"+
		"\u00a2\13\r\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\6\23\u00b7\n\23\r\23\16\23\u00b8\3"+
		"\23\3\23\7\23\u00bd\n\23\f\23\16\23\u00c0\13\23\3\24\3\24\3\24\7\24\u00c5"+
		"\n\24\f\24\16\24\u00c8\13\24\3\25\3\25\3\25\7\25\u00cd\n\25\f\25\16\25"+
		"\u00d0\13\25\3\26\3\26\3\26\3\26\7\26\u00d6\n\26\f\26\16\26\u00d9\13\26"+
		"\3\27\3\27\3\27\3\27\7\27\u00df\n\27\f\27\16\27\u00e2\13\27\3\30\3\30"+
		"\3\30\3\30\7\30\u00e8\n\30\f\30\16\30\u00eb\13\30\3\31\7\31\u00ee\n\31"+
		"\f\31\16\31\u00f1\13\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\7\32\u00fd\n\32\f\32\16\32\u0100\13\32\3\33\3\33\3\33\3\33\5\33\u0106"+
		"\n\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\7\37\u0113"+
		"\n\37\f\37\16\37\u0116\13\37\5\37\u0118\n\37\3\37\5\37\u011b\n\37\3 \3"+
		" \3 \3 \6 \u0121\n \r \16 \u0122\3 \3 \3!\3!\3!\3!\3!\3\"\3\"\5\"\u012e"+
		"\n\"\3#\3#\3#\3#\5#\u0134\n#\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\2\4\16$(\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJL\2\b\5\2\5\6\f\f\21\21\3\2%*\3\2./\3\2"+
		"+-\6\2\7\7\r\r$$.\60\4\2\3\4\24\27\2\u0142\2O\3\2\2\2\4V\3\2\2\2\6X\3"+
		"\2\2\2\b]\3\2\2\2\nb\3\2\2\2\fx\3\2\2\2\16\u0080\3\2\2\2\20\u008c\3\2"+
		"\2\2\22\u008e\3\2\2\2\24\u0090\3\2\2\2\26\u0093\3\2\2\2\30\u0097\3\2\2"+
		"\2\32\u00a3\3\2\2\2\34\u00a7\3\2\2\2\36\u00a9\3\2\2\2 \u00ab\3\2\2\2\""+
		"\u00ad\3\2\2\2$\u00af\3\2\2\2&\u00c1\3\2\2\2(\u00c9\3\2\2\2*\u00d1\3\2"+
		"\2\2,\u00da\3\2\2\2.\u00e3\3\2\2\2\60\u00ef\3\2\2\2\62\u00f4\3\2\2\2\64"+
		"\u0105\3\2\2\2\66\u0107\3\2\2\28\u0109\3\2\2\2:\u010b\3\2\2\2<\u011a\3"+
		"\2\2\2>\u011c\3\2\2\2@\u0126\3\2\2\2B\u012d\3\2\2\2D\u0133\3\2\2\2F\u0135"+
		"\3\2\2\2H\u0137\3\2\2\2J\u013b\3\2\2\2L\u0142\3\2\2\2NP\5\4\3\2ON\3\2"+
		"\2\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2\2R\3\3\2\2\2SW\5\6\4\2TW\5\b\5\2UW\5"+
		"\n\6\2VS\3\2\2\2VT\3\2\2\2VU\3\2\2\2W\5\3\2\2\2XY\7\17\2\2YZ\7\62\2\2"+
		"Z[\7\61\2\2[\\\5\16\b\2\\\7\3\2\2\2]^\7\20\2\2^_\7\62\2\2_`\7 \2\2`a\5"+
		"\16\b\2a\t\3\2\2\2bc\7\n\2\2cd\7\62\2\2de\7\30\2\2ef\5\f\7\2fg\7\31\2"+
		"\2gh\7 \2\2hi\5\16\b\2ij\7\61\2\2jk\5$\23\2k\13\3\2\2\2lm\7\62\2\2mn\7"+
		" \2\2nu\5\16\b\2op\7\37\2\2pq\7\62\2\2qr\7 \2\2rt\5\16\b\2so\3\2\2\2t"+
		"w\3\2\2\2us\3\2\2\2uv\3\2\2\2vy\3\2\2\2wu\3\2\2\2xl\3\2\2\2xy\3\2\2\2"+
		"y\r\3\2\2\2z{\b\b\1\2{\u0081\5\20\t\2|\u0081\5\22\n\2}\u0081\5\24\13\2"+
		"~\u0081\5\26\f\2\177\u0081\5\32\16\2\u0080z\3\2\2\2\u0080|\3\2\2\2\u0080"+
		"}\3\2\2\2\u0080~\3\2\2\2\u0080\177\3\2\2\2\u0081\u0089\3\2\2\2\u0082\u0083"+
		"\f\b\2\2\u0083\u0084\7\34\2\2\u0084\u0085\5$\23\2\u0085\u0086\7\35\2\2"+
		"\u0086\u0088\3\2\2\2\u0087\u0082\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\17\3\2\2\2\u008b\u0089\3\2\2\2\u008c"+
		"\u008d\t\2\2\2\u008d\21\3\2\2\2\u008e\u008f\7\62\2\2\u008f\23\3\2\2\2"+
		"\u0090\u0091\7\60\2\2\u0091\u0092\5\16\b\2\u0092\25\3\2\2\2\u0093\u0094"+
		"\7\32\2\2\u0094\u0095\5\30\r\2\u0095\u0096\7\33\2\2\u0096\27\3\2\2\2\u0097"+
		"\u0098\7\62\2\2\u0098\u0099\7 \2\2\u0099\u00a0\5\16\b\2\u009a\u009b\7"+
		"\37\2\2\u009b\u009c\7\62\2\2\u009c\u009d\7 \2\2\u009d\u009f\5\16\b\2\u009e"+
		"\u009a\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\31\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\7\30\2\2\u00a4\u00a5"+
		"\5\16\b\2\u00a5\u00a6\7\31\2\2\u00a6\33\3\2\2\2\u00a7\u00a8\t\3\2\2\u00a8"+
		"\35\3\2\2\2\u00a9\u00aa\t\4\2\2\u00aa\37\3\2\2\2\u00ab\u00ac\t\5\2\2\u00ac"+
		"!\3\2\2\2\u00ad\u00ae\t\6\2\2\u00ae#\3\2\2\2\u00af\u00b0\b\23\1\2\u00b0"+
		"\u00b1\5&\24\2\u00b1\u00be\3\2\2\2\u00b2\u00b3\f\4\2\2\u00b3\u00b4\7\22"+
		"\2\2\u00b4\u00b6\7\32\2\2\u00b5\u00b7\5\4\3\2\u00b6\u00b5\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2"+
		"\2\2\u00ba\u00bb\7\33\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00b2\3\2\2\2\u00bd"+
		"\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf%\3\2\2\2"+
		"\u00c0\u00be\3\2\2\2\u00c1\u00c6\5(\25\2\u00c2\u00c3\7#\2\2\u00c3\u00c5"+
		"\5(\25\2\u00c4\u00c2\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\'\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ce\5*\26\2"+
		"\u00ca\u00cb\7\"\2\2\u00cb\u00cd\5*\26\2\u00cc\u00ca\3\2\2\2\u00cd\u00d0"+
		"\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf)\3\2\2\2\u00d0"+
		"\u00ce\3\2\2\2\u00d1\u00d7\5,\27\2\u00d2\u00d3\5\34\17\2\u00d3\u00d4\5"+
		",\27\2\u00d4\u00d6\3\2\2\2\u00d5\u00d2\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8+\3\2\2\2\u00d9\u00d7\3\2\2\2"+
		"\u00da\u00e0\5.\30\2\u00db\u00dc\5\36\20\2\u00dc\u00dd\5.\30\2\u00dd\u00df"+
		"\3\2\2\2\u00de\u00db\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1-\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e9\5\60\31"+
		"\2\u00e4\u00e5\5 \21\2\u00e5\u00e6\5\60\31\2\u00e6\u00e8\3\2\2\2\u00e7"+
		"\u00e4\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2"+
		"\2\2\u00ea/\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ee\5\"\22\2\u00ed\u00ec"+
		"\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		"\u00f2\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f3\5\62\32\2\u00f3\61\3\2"+
		"\2\2\u00f4\u00fe\5\64\33\2\u00f5\u00f6\7\34\2\2\u00f6\u00f7\5$\23\2\u00f7"+
		"\u00f8\7\35\2\2\u00f8\u00fd\3\2\2\2\u00f9\u00fd\7\60\2\2\u00fa\u00fb\7"+
		"\36\2\2\u00fb\u00fd\7\62\2\2\u00fc\u00f5\3\2\2\2\u00fc\u00f9\3\2\2\2\u00fc"+
		"\u00fa\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2"+
		"\2\2\u00ff\63\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0106\5\66\34\2\u0102"+
		"\u0106\5:\36\2\u0103\u0106\5> \2\u0104\u0106\5@!\2\u0105\u0101\3\2\2\2"+
		"\u0105\u0102\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0104\3\2\2\2\u0106\65"+
		"\3\2\2\2\u0107\u0108\58\35\2\u0108\67\3\2\2\2\u0109\u010a\t\7\2\2\u010a"+
		"9\3\2\2\2\u010b\u010c\7\62\2\2\u010c\u010d\5<\37\2\u010d;\3\2\2\2\u010e"+
		"\u0117\7\30\2\2\u010f\u0114\5$\23\2\u0110\u0111\7\37\2\2\u0111\u0113\5"+
		"$\23\2\u0112\u0110\3\2\2\2\u0113\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0117\u010f\3\2"+
		"\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b\7\31\2\2\u011a"+
		"\u010e\3\2\2\2\u011a\u011b\3\2\2\2\u011b=\3\2\2\2\u011c\u0120\7\32\2\2"+
		"\u011d\u011e\5D#\2\u011e\u011f\7!\2\2\u011f\u0121\3\2\2\2\u0120\u011d"+
		"\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123"+
		"\u0124\3\2\2\2\u0124\u0125\7\33\2\2\u0125?\3\2\2\2\u0126\u0127\7\30\2"+
		"\2\u0127\u0128\5$\23\2\u0128\u0129\5B\"\2\u0129\u012a\7\31\2\2\u012aA"+
		"\3\2\2\2\u012b\u012c\7 \2\2\u012c\u012e\5\16\b\2\u012d\u012b\3\2\2\2\u012d"+
		"\u012e\3\2\2\2\u012eC\3\2\2\2\u012f\u0134\5F$\2\u0130\u0134\5H%\2\u0131"+
		"\u0134\5J&\2\u0132\u0134\5L\'\2\u0133\u012f\3\2\2\2\u0133\u0130\3\2\2"+
		"\2\u0133\u0131\3\2\2\2\u0133\u0132\3\2\2\2\u0134E\3\2\2\2\u0135\u0136"+
		"\5$\23\2\u0136G\3\2\2\2\u0137\u0138\5$\23\2\u0138\u0139\7\61\2\2\u0139"+
		"\u013a\5$\23\2\u013aI\3\2\2\2\u013b\u013c\7\13\2\2\u013c\u013d\5$\23\2"+
		"\u013d\u013e\7\16\2\2\u013e\u013f\5D#\2\u013f\u0140\7\t\2\2\u0140\u0141"+
		"\5D#\2\u0141K\3\2\2\2\u0142\u0143\7\23\2\2\u0143\u0144\5$\23\2\u0144\u0145"+
		"\7\b\2\2\u0145\u0146\5D#\2\u0146M\3\2\2\2\32QVux\u0080\u0089\u00a0\u00b8"+
		"\u00be\u00c6\u00ce\u00d7\u00e0\u00e9\u00ef\u00fc\u00fe\u0105\u0114\u0117"+
		"\u011a\u0122\u012d\u0133";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}