// Generated from /home/tomi/random/fri_3_letnik/prevajalniki/naloga3/prev20/src/prev/phase/synan/PrevParser.g4 by ANTLR 4.7.1


	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.*;
	import prev.data.ast.tree.*;


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
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

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
		RULE_functionDeclaration = 4, RULE_type = 5, RULE_atomicType = 6, RULE_namedType = 7, 
		RULE_pointerType = 8, RULE_recordType = 9, RULE_enclosedType = 10, RULE_arrayType = 11, 
		RULE_typeRepeat = 12, RULE_relOps = 13, RULE_addOps = 14, RULE_mulOps = 15, 
		RULE_prefOps = 16, RULE_postOps = 17, RULE_constant = 18, RULE_expr = 19, 
		RULE_disjunctionExpression = 20, RULE_conjunctionExpression = 21, RULE_relationalExpression = 22, 
		RULE_additiveExpression = 23, RULE_multiplicativeExpression = 24, RULE_prefixExpression = 25, 
		RULE_postfixExpression = 26, RULE_atomicExpression = 27, RULE_constantExpression = 28, 
		RULE_variableAccess = 29, RULE_functionCall = 30, RULE_compoundExpression = 31, 
		RULE_typecastExpression = 32, RULE_enclosedExpression = 33, RULE_stmt = 34, 
		RULE_expressionStatement = 35, RULE_assignmentStatement = 36, RULE_conditionalStatement = 37, 
		RULE_loopStatement = 38;
	public static final String[] ruleNames = {
		"source", "decl", "typeDeclaration", "variableDeclaration", "functionDeclaration", 
		"type", "atomicType", "namedType", "pointerType", "recordType", "enclosedType", 
		"arrayType", "typeRepeat", "relOps", "addOps", "mulOps", "prefOps", "postOps", 
		"constant", "expr", "disjunctionExpression", "conjunctionExpression", 
		"relationalExpression", "additiveExpression", "multiplicativeExpression", 
		"prefixExpression", "postfixExpression", "atomicExpression", "constantExpression", 
		"variableAccess", "functionCall", "compoundExpression", "typecastExpression", 
		"enclosedExpression", "stmt", "expressionStatement", "assignmentStatement", 
		"conditionalStatement", "loopStatement"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'none'", null, "'boolean'", "'char'", "'del'", "'do'", "'else'", 
		"'fun'", "'if'", "'integer'", "'new'", "'then'", "'typ'", "'var'", "'void'", 
		"'where'", "'while'", null, null, null, "'nil'", "'('", "')'", "'{'", 
		"'}'", "'['", "']'", "'.'", "','", "':'", "';'", "'&'", "'|'", "'!'", 
		"'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'*'", "'/'", "'%'", "'+'", 
		"'-'", "'^'", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VOID", "BOOLEAN", "KEYWORD_BOOL", "KEYWORD_CHAR", "KEYWORD_DEL", 
		"KEYWORD_DO", "KEYWORD_ELSE", "KEYWORD_FUN", "KEYWORD_IF", "KEYWORD_INT", 
		"KEYWORD_NEW", "KEYWORD_THEN", "KEYWORD_TYP", "KEYWORD_VAR", "KEYWORD_VOID", 
		"KEYWORD_WHERE", "KEYWORD_WHILE", "INTEGER", "CHAR", "STRING", "POINTER", 
		"SYMBOL_LBR", "SYMBOL_RBR", "SYMBOL_LCBR", "SYMBOL_RCBR", "SYMBOL_LSBR", 
		"SYMBOL_RSBR", "SYMBOL_DOT", "SYMBOL_COMMA", "SYMBOL_COLON", "SYMBOL_SEMIC", 
		"SYMBOL_AND", "SYMBOL_OR", "SYMBOL_EXCL", "SYMBOL_EQ", "SYMBOL_NEQ", "SYMBOL_LT", 
		"SYMBOL_GT", "SYMBOL_LEQ", "SYMBOL_GEQ", "SYMBOL_STAR", "SYMBOL_SLASH", 
		"SYMBOL_MOD", "SYMBOL_PLUS", "SYMBOL_MIN", "SYMBOL_PWR", "SYMBOL_EQUALS", 
		"IDENTIFIER", "COMMENT", "WHITESPACE", "ERROR"
	};
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(78);
				((SourceContext)_localctx).decl = decl();
				}
				}
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KEYWORD_FUN) | (1L << KEYWORD_TYP) | (1L << KEYWORD_VAR))) != 0) );
			 Vector<AstDecl> ds = new Vector<AstDecl>(); //naredim vektor
				  ds.add(((SourceContext)_localctx).decl.ast);				// vanga zmečem deklaracije
				  ((SourceContext)_localctx).ast =  new AstTrees<AstDecl>(ds); 
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
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEYWORD_TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				((DeclContext)_localctx).typeDeclaration = typeDeclaration();
				_localctx.ast = ((DeclContext)_localctx).typeDeclaration.ast
				}
				break;
			case KEYWORD_VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				((DeclContext)_localctx).variableDeclaration = variableDeclaration();
				_localctx.ast = ((DeclContext)_localctx).variableDeclaration.ast
				}
				break;
			case KEYWORD_FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
				((DeclContext)_localctx).functionDeclaration = functionDeclaration();
				_localctx.ast = ((DeclContext)_localctx).functionDeclaration.ast
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
			setState(96);
			match(KEYWORD_TYP);
			setState(97);
			match(IDENTIFIER);
			setState(98);
			match(SYMBOL_EQUALS);
			setState(99);
			((TypeDeclarationContext)_localctx).type = type();
			_localctx.ast = ((TypeDeclarationContext)_localctx).type.ast
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
			setState(102);
			match(KEYWORD_VAR);
			setState(103);
			match(IDENTIFIER);
			setState(104);
			match(SYMBOL_COLON);
			setState(105);
			((VariableDeclarationContext)_localctx).type = type();
			_localctx.ast = ((VariableDeclarationContext)_localctx).type.ast
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
		public TerminalNode KEYWORD_FUN() { return getToken(PrevParser.KEYWORD_FUN, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode SYMBOL_LBR() { return getToken(PrevParser.SYMBOL_LBR, 0); }
		public TerminalNode SYMBOL_RBR() { return getToken(PrevParser.SYMBOL_RBR, 0); }
		public TerminalNode SYMBOL_COLON() { return getToken(PrevParser.SYMBOL_COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SYMBOL_EQUALS() { return getToken(PrevParser.SYMBOL_EQUALS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TypeRepeatContext typeRepeat() {
			return getRuleContext(TypeRepeatContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(KEYWORD_FUN);
			setState(109);
			match(IDENTIFIER);
			setState(110);
			match(SYMBOL_LBR);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(111);
				typeRepeat();
				}
			}

			setState(114);
			match(SYMBOL_RBR);
			setState(115);
			match(SYMBOL_COLON);
			setState(116);
			type();
			setState(117);
			match(SYMBOL_EQUALS);
			setState(118);
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

	public static class TypeContext extends ParserRuleContext {
		public AstType ast;
		public AtomicTypeContext atomicType;
		public NamedTypeContext namedType;
		public ArrayTypeContext arrayType;
		public PointerTypeContext pointerType;
		public RecordTypeContext recordType;
		public EnclosedTypeContext enclosedType;
		public AtomicTypeContext atomicType() {
			return getRuleContext(AtomicTypeContext.class,0);
		}
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
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
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				((TypeContext)_localctx).atomicType = atomicType();
				_localctx.ast = ((TypeContext)_localctx).atomicType.ast
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				((TypeContext)_localctx).namedType = namedType();
				_localctx.ast = ((TypeContext)_localctx).namedType.ast
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				((TypeContext)_localctx).arrayType = arrayType();
				_localctx.ast = ((TypeContext)_localctx).arrayType.ast
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(129);
				((TypeContext)_localctx).pointerType = pointerType();
				_localctx.ast = ((TypeContext)_localctx).pointerType.ast
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(132);
				((TypeContext)_localctx).recordType = recordType();
				_localctx.ast = ((TypeContext)_localctx).recordType.ast
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(135);
				((TypeContext)_localctx).enclosedType = enclosedType();
				_localctx.ast = ((TypeContext)_localctx).enclosedType.ast
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

	public static class AtomicTypeContext extends ParserRuleContext {
		public AstAtomType ast;
		public TerminalNode KEYWORD_VOID() { return getToken(PrevParser.KEYWORD_VOID, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
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
		enterRule(_localctx, 12, RULE_atomicType);
		try {
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEYWORD_VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				match(KEYWORD_VOID);
				setState(141);
				arrayType();
				}
				break;
			case KEYWORD_CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				match(KEYWORD_CHAR);
				setState(143);
				arrayType();
				}
				break;
			case KEYWORD_INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(144);
				match(KEYWORD_INT);
				setState(145);
				arrayType();
				}
				break;
			case KEYWORD_BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(146);
				match(KEYWORD_BOOL);
				setState(147);
				arrayType();
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
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public NamedTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedType; }
	}

	public final NamedTypeContext namedType() throws RecognitionException {
		NamedTypeContext _localctx = new NamedTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_namedType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(IDENTIFIER);
			setState(151);
			arrayType();
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
		public TerminalNode SYMBOL_PWR() { return getToken(PrevParser.SYMBOL_PWR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public PointerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerType; }
	}

	public final PointerTypeContext pointerType() throws RecognitionException {
		PointerTypeContext _localctx = new PointerTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_pointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(SYMBOL_PWR);
			setState(154);
			type();
			setState(155);
			arrayType();
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
		public TerminalNode SYMBOL_LCBR() { return getToken(PrevParser.SYMBOL_LCBR, 0); }
		public TypeRepeatContext typeRepeat() {
			return getRuleContext(TypeRepeatContext.class,0);
		}
		public TerminalNode SYMBOL_RCBR() { return getToken(PrevParser.SYMBOL_RCBR, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public RecordTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordType; }
	}

	public final RecordTypeContext recordType() throws RecognitionException {
		RecordTypeContext _localctx = new RecordTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_recordType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(SYMBOL_LCBR);
			setState(158);
			typeRepeat();
			setState(159);
			match(SYMBOL_RCBR);
			setState(160);
			arrayType();
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
		public AstType ast;
		public TerminalNode SYMBOL_LBR() { return getToken(PrevParser.SYMBOL_LBR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SYMBOL_RBR() { return getToken(PrevParser.SYMBOL_RBR, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public EnclosedTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enclosedType; }
	}

	public final EnclosedTypeContext enclosedType() throws RecognitionException {
		EnclosedTypeContext _localctx = new EnclosedTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_enclosedType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(SYMBOL_LBR);
			setState(163);
			type();
			setState(164);
			match(SYMBOL_RBR);
			setState(165);
			arrayType();
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

	public static class ArrayTypeContext extends ParserRuleContext {
		public AstArrType ast;
		public TerminalNode SYMBOL_LSBR() { return getToken(PrevParser.SYMBOL_LSBR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SYMBOL_RSBR() { return getToken(PrevParser.SYMBOL_RSBR, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_arrayType);
		try {
			setState(173);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				match(SYMBOL_LSBR);
				setState(168);
				expr(0);
				setState(169);
				match(SYMBOL_RSBR);
				setState(170);
				arrayType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class TypeRepeatContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public List<TerminalNode> SYMBOL_COLON() { return getTokens(PrevParser.SYMBOL_COLON); }
		public TerminalNode SYMBOL_COLON(int i) {
			return getToken(PrevParser.SYMBOL_COLON, i);
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
		public TypeRepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeRepeat; }
	}

	public final TypeRepeatContext typeRepeat() throws RecognitionException {
		TypeRepeatContext _localctx = new TypeRepeatContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_typeRepeat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(IDENTIFIER);
			setState(176);
			match(SYMBOL_COLON);
			setState(177);
			type();
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SYMBOL_COMMA) {
				{
				{
				setState(178);
				match(SYMBOL_COMMA);
				setState(179);
				match(IDENTIFIER);
				setState(180);
				match(SYMBOL_COLON);
				setState(181);
				type();
				}
				}
				setState(186);
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
			setState(187);
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
			setState(189);
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
			setState(191);
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
			setState(193);
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

	public static class PostOpsContext extends ParserRuleContext {
		public TerminalNode SYMBOL_LSBR() { return getToken(PrevParser.SYMBOL_LSBR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SYMBOL_RSBR() { return getToken(PrevParser.SYMBOL_RSBR, 0); }
		public TerminalNode SYMBOL_PWR() { return getToken(PrevParser.SYMBOL_PWR, 0); }
		public TerminalNode SYMBOL_DOT() { return getToken(PrevParser.SYMBOL_DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public PostOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postOps; }
	}

	public final PostOpsContext postOps() throws RecognitionException {
		PostOpsContext _localctx = new PostOpsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_postOps);
		try {
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_LSBR:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				match(SYMBOL_LSBR);
				setState(196);
				expr(0);
				setState(197);
				match(SYMBOL_RSBR);
				}
				break;
			case SYMBOL_PWR:
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				match(SYMBOL_PWR);
				}
				break;
			case SYMBOL_DOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(200);
				match(SYMBOL_DOT);
				setState(201);
				match(IDENTIFIER);
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
		enterRule(_localctx, 36, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
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

	public static class ExprContext extends ParserRuleContext {
		public DisjunctionExpressionContext disjunctionExpression() {
			return getRuleContext(DisjunctionExpressionContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode KEYWORD_WHERE() { return getToken(PrevParser.KEYWORD_WHERE, 0); }
		public TerminalNode SYMBOL_LCBR() { return getToken(PrevParser.SYMBOL_LCBR, 0); }
		public TerminalNode SYMBOL_RCBR() { return getToken(PrevParser.SYMBOL_RCBR, 0); }
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
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(207);
			disjunctionExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(221);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(209);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(210);
					match(KEYWORD_WHERE);
					setState(211);
					match(SYMBOL_LCBR);
					setState(213); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(212);
						decl();
						}
						}
						setState(215); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KEYWORD_FUN) | (1L << KEYWORD_TYP) | (1L << KEYWORD_VAR))) != 0) );
					setState(217);
					match(SYMBOL_RCBR);
					}
					} 
				}
				setState(223);
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
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DisjunctionExpressionContext extends ParserRuleContext {
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
		enterRule(_localctx, 40, RULE_disjunctionExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			conjunctionExpression();
			setState(229);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(225);
					match(SYMBOL_OR);
					setState(226);
					conjunctionExpression();
					}
					} 
				}
				setState(231);
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

	public static class ConjunctionExpressionContext extends ParserRuleContext {
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
		enterRule(_localctx, 42, RULE_conjunctionExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			relationalExpression();
			setState(237);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(233);
					match(SYMBOL_AND);
					setState(234);
					relationalExpression();
					}
					} 
				}
				setState(239);
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

	public static class RelationalExpressionContext extends ParserRuleContext {
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
		enterRule(_localctx, 44, RULE_relationalExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			additiveExpression();
			setState(246);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(241);
					relOps();
					setState(242);
					additiveExpression();
					}
					} 
				}
				setState(248);
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

	public static class AdditiveExpressionContext extends ParserRuleContext {
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
		enterRule(_localctx, 46, RULE_additiveExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			multiplicativeExpression();
			setState(255);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(250);
					addOps();
					setState(251);
					multiplicativeExpression();
					}
					} 
				}
				setState(257);
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

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
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
		enterRule(_localctx, 48, RULE_multiplicativeExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			prefixExpression();
			setState(264);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(259);
					mulOps();
					setState(260);
					prefixExpression();
					}
					} 
				}
				setState(266);
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
		enterRule(_localctx, 50, RULE_prefixExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(267);
					prefOps();
					}
					} 
				}
				setState(272);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(273);
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
		public List<PostOpsContext> postOps() {
			return getRuleContexts(PostOpsContext.class);
		}
		public PostOpsContext postOps(int i) {
			return getRuleContext(PostOpsContext.class,i);
		}
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpression; }
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_postfixExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			atomicExpression();
			setState(279);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(276);
					postOps();
					}
					} 
				}
				setState(281);
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
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VariableAccessContext variableAccess() {
			return getRuleContext(VariableAccessContext.class,0);
		}
		public CompoundExpressionContext compoundExpression() {
			return getRuleContext(CompoundExpressionContext.class,0);
		}
		public TypecastExpressionContext typecastExpression() {
			return getRuleContext(TypecastExpressionContext.class,0);
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
		enterRule(_localctx, 54, RULE_atomicExpression);
		try {
			setState(287);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				variableAccess();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(284);
				compoundExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(285);
				typecastExpression();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(286);
				enclosedExpression();
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
		enterRule(_localctx, 56, RULE_constantExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
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
		enterRule(_localctx, 58, RULE_variableAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(IDENTIFIER);
			setState(292);
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
		enterRule(_localctx, 60, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(294);
				match(SYMBOL_LBR);
				setState(303);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(295);
					expr(0);
					setState(300);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SYMBOL_COMMA) {
						{
						{
						setState(296);
						match(SYMBOL_COMMA);
						setState(297);
						expr(0);
						}
						}
						setState(302);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
				setState(305);
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
		enterRule(_localctx, 62, RULE_compoundExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(SYMBOL_LCBR);
			setState(312); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(309);
					stmt();
					setState(310);
					match(SYMBOL_SEMIC);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(314); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(316);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(318);
				match(SYMBOL_COLON);
				setState(319);
				type();
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
		enterRule(_localctx, 66, RULE_enclosedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			match(SYMBOL_LBR);
			setState(323);
			expr(0);
			setState(324);
			typecastExpression();
			setState(325);
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
		enterRule(_localctx, 68, RULE_stmt);
		try {
			setState(331);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(327);
				expressionStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(328);
				assignmentStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(329);
				conditionalStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(330);
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
		enterRule(_localctx, 70, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
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
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SYMBOL_EQUALS() { return getToken(PrevParser.SYMBOL_EQUALS, 0); }
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			expr(0);
			setState(336);
			match(SYMBOL_EQUALS);
			setState(337);
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

	public static class ConditionalStatementContext extends ParserRuleContext {
		public TerminalNode KEYWORD_IF() { return getToken(PrevParser.KEYWORD_IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode KEYWORD_THEN() { return getToken(PrevParser.KEYWORD_THEN, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode KEYWORD_ELSE() { return getToken(PrevParser.KEYWORD_ELSE, 0); }
		public ConditionalStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalStatement; }
	}

	public final ConditionalStatementContext conditionalStatement() throws RecognitionException {
		ConditionalStatementContext _localctx = new ConditionalStatementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_conditionalStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			match(KEYWORD_IF);
			setState(340);
			expr(0);
			setState(341);
			match(KEYWORD_THEN);
			setState(342);
			stmt();
			setState(343);
			match(KEYWORD_ELSE);
			setState(344);
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
		enterRule(_localctx, 76, RULE_loopStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(KEYWORD_WHILE);
			setState(347);
			expr(0);
			setState(348);
			match(KEYWORD_DO);
			setState(349);
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
		case 19:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\65\u0162\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\6\2R\n\2\r\2\16"+
		"\2S\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3a\n\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6s\n\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\5\7\u008d\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0097"+
		"\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00b0\n\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\7\16\u00b9\n\16\f\16\16\16\u00bc\13\16\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00cd\n\23"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\6\25\u00d8\n\25\r\25\16"+
		"\25\u00d9\3\25\3\25\7\25\u00de\n\25\f\25\16\25\u00e1\13\25\3\26\3\26\3"+
		"\26\7\26\u00e6\n\26\f\26\16\26\u00e9\13\26\3\27\3\27\3\27\7\27\u00ee\n"+
		"\27\f\27\16\27\u00f1\13\27\3\30\3\30\3\30\3\30\7\30\u00f7\n\30\f\30\16"+
		"\30\u00fa\13\30\3\31\3\31\3\31\3\31\7\31\u0100\n\31\f\31\16\31\u0103\13"+
		"\31\3\32\3\32\3\32\3\32\7\32\u0109\n\32\f\32\16\32\u010c\13\32\3\33\7"+
		"\33\u010f\n\33\f\33\16\33\u0112\13\33\3\33\3\33\3\34\3\34\7\34\u0118\n"+
		"\34\f\34\16\34\u011b\13\34\3\35\3\35\3\35\3\35\3\35\5\35\u0122\n\35\3"+
		"\36\3\36\3\37\3\37\3\37\3 \3 \3 \3 \7 \u012d\n \f \16 \u0130\13 \5 \u0132"+
		"\n \3 \5 \u0135\n \3!\3!\3!\3!\6!\u013b\n!\r!\16!\u013c\3!\3!\3\"\3\""+
		"\5\"\u0143\n\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\5$\u014e\n$\3%\3%\3&\3&\3&\3"+
		"&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\2\3()\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLN\2\7\3\2%*\3\2."+
		"/\3\2+-\6\2\7\7\r\r$$.\60\4\2\3\4\24\27\2\u015f\2Q\3\2\2\2\4`\3\2\2\2"+
		"\6b\3\2\2\2\bh\3\2\2\2\nn\3\2\2\2\f\u008c\3\2\2\2\16\u0096\3\2\2\2\20"+
		"\u0098\3\2\2\2\22\u009b\3\2\2\2\24\u009f\3\2\2\2\26\u00a4\3\2\2\2\30\u00af"+
		"\3\2\2\2\32\u00b1\3\2\2\2\34\u00bd\3\2\2\2\36\u00bf\3\2\2\2 \u00c1\3\2"+
		"\2\2\"\u00c3\3\2\2\2$\u00cc\3\2\2\2&\u00ce\3\2\2\2(\u00d0\3\2\2\2*\u00e2"+
		"\3\2\2\2,\u00ea\3\2\2\2.\u00f2\3\2\2\2\60\u00fb\3\2\2\2\62\u0104\3\2\2"+
		"\2\64\u0110\3\2\2\2\66\u0115\3\2\2\28\u0121\3\2\2\2:\u0123\3\2\2\2<\u0125"+
		"\3\2\2\2>\u0134\3\2\2\2@\u0136\3\2\2\2B\u0142\3\2\2\2D\u0144\3\2\2\2F"+
		"\u014d\3\2\2\2H\u014f\3\2\2\2J\u0151\3\2\2\2L\u0155\3\2\2\2N\u015c\3\2"+
		"\2\2PR\5\4\3\2QP\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\b\2"+
		"\1\2V\3\3\2\2\2WX\5\6\4\2XY\b\3\1\2Ya\3\2\2\2Z[\5\b\5\2[\\\b\3\1\2\\a"+
		"\3\2\2\2]^\5\n\6\2^_\b\3\1\2_a\3\2\2\2`W\3\2\2\2`Z\3\2\2\2`]\3\2\2\2a"+
		"\5\3\2\2\2bc\7\17\2\2cd\7\62\2\2de\7\61\2\2ef\5\f\7\2fg\b\4\1\2g\7\3\2"+
		"\2\2hi\7\20\2\2ij\7\62\2\2jk\7 \2\2kl\5\f\7\2lm\b\5\1\2m\t\3\2\2\2no\7"+
		"\n\2\2op\7\62\2\2pr\7\30\2\2qs\5\32\16\2rq\3\2\2\2rs\3\2\2\2st\3\2\2\2"+
		"tu\7\31\2\2uv\7 \2\2vw\5\f\7\2wx\7\61\2\2xy\5(\25\2y\13\3\2\2\2z{\5\16"+
		"\b\2{|\b\7\1\2|\u008d\3\2\2\2}~\5\20\t\2~\177\b\7\1\2\177\u008d\3\2\2"+
		"\2\u0080\u0081\5\30\r\2\u0081\u0082\b\7\1\2\u0082\u008d\3\2\2\2\u0083"+
		"\u0084\5\22\n\2\u0084\u0085\b\7\1\2\u0085\u008d\3\2\2\2\u0086\u0087\5"+
		"\24\13\2\u0087\u0088\b\7\1\2\u0088\u008d\3\2\2\2\u0089\u008a\5\26\f\2"+
		"\u008a\u008b\b\7\1\2\u008b\u008d\3\2\2\2\u008cz\3\2\2\2\u008c}\3\2\2\2"+
		"\u008c\u0080\3\2\2\2\u008c\u0083\3\2\2\2\u008c\u0086\3\2\2\2\u008c\u0089"+
		"\3\2\2\2\u008d\r\3\2\2\2\u008e\u008f\7\21\2\2\u008f\u0097\5\30\r\2\u0090"+
		"\u0091\7\6\2\2\u0091\u0097\5\30\r\2\u0092\u0093\7\f\2\2\u0093\u0097\5"+
		"\30\r\2\u0094\u0095\7\5\2\2\u0095\u0097\5\30\r\2\u0096\u008e\3\2\2\2\u0096"+
		"\u0090\3\2\2\2\u0096\u0092\3\2\2\2\u0096\u0094\3\2\2\2\u0097\17\3\2\2"+
		"\2\u0098\u0099\7\62\2\2\u0099\u009a\5\30\r\2\u009a\21\3\2\2\2\u009b\u009c"+
		"\7\60\2\2\u009c\u009d\5\f\7\2\u009d\u009e\5\30\r\2\u009e\23\3\2\2\2\u009f"+
		"\u00a0\7\32\2\2\u00a0\u00a1\5\32\16\2\u00a1\u00a2\7\33\2\2\u00a2\u00a3"+
		"\5\30\r\2\u00a3\25\3\2\2\2\u00a4\u00a5\7\30\2\2\u00a5\u00a6\5\f\7\2\u00a6"+
		"\u00a7\7\31\2\2\u00a7\u00a8\5\30\r\2\u00a8\27\3\2\2\2\u00a9\u00aa\7\34"+
		"\2\2\u00aa\u00ab\5(\25\2\u00ab\u00ac\7\35\2\2\u00ac\u00ad\5\30\r\2\u00ad"+
		"\u00b0\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af\u00a9\3\2\2\2\u00af\u00ae\3\2"+
		"\2\2\u00b0\31\3\2\2\2\u00b1\u00b2\7\62\2\2\u00b2\u00b3\7 \2\2\u00b3\u00ba"+
		"\5\f\7\2\u00b4\u00b5\7\37\2\2\u00b5\u00b6\7\62\2\2\u00b6\u00b7\7 \2\2"+
		"\u00b7\u00b9\5\f\7\2\u00b8\u00b4\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8"+
		"\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\33\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd"+
		"\u00be\t\2\2\2\u00be\35\3\2\2\2\u00bf\u00c0\t\3\2\2\u00c0\37\3\2\2\2\u00c1"+
		"\u00c2\t\4\2\2\u00c2!\3\2\2\2\u00c3\u00c4\t\5\2\2\u00c4#\3\2\2\2\u00c5"+
		"\u00c6\7\34\2\2\u00c6\u00c7\5(\25\2\u00c7\u00c8\7\35\2\2\u00c8\u00cd\3"+
		"\2\2\2\u00c9\u00cd\7\60\2\2\u00ca\u00cb\7\36\2\2\u00cb\u00cd\7\62\2\2"+
		"\u00cc\u00c5\3\2\2\2\u00cc\u00c9\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd%\3"+
		"\2\2\2\u00ce\u00cf\t\6\2\2\u00cf\'\3\2\2\2\u00d0\u00d1\b\25\1\2\u00d1"+
		"\u00d2\5*\26\2\u00d2\u00df\3\2\2\2\u00d3\u00d4\f\4\2\2\u00d4\u00d5\7\22"+
		"\2\2\u00d5\u00d7\7\32\2\2\u00d6\u00d8\5\4\3\2\u00d7\u00d6\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\3\2"+
		"\2\2\u00db\u00dc\7\33\2\2\u00dc\u00de\3\2\2\2\u00dd\u00d3\3\2\2\2\u00de"+
		"\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0)\3\2\2\2"+
		"\u00e1\u00df\3\2\2\2\u00e2\u00e7\5,\27\2\u00e3\u00e4\7#\2\2\u00e4\u00e6"+
		"\5,\27\2\u00e5\u00e3\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7"+
		"\u00e8\3\2\2\2\u00e8+\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00ef\5.\30\2"+
		"\u00eb\u00ec\7\"\2\2\u00ec\u00ee\5.\30\2\u00ed\u00eb\3\2\2\2\u00ee\u00f1"+
		"\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0-\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f2\u00f8\5\60\31\2\u00f3\u00f4\5\34\17\2\u00f4\u00f5"+
		"\5\60\31\2\u00f5\u00f7\3\2\2\2\u00f6\u00f3\3\2\2\2\u00f7\u00fa\3\2\2\2"+
		"\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9/\3\2\2\2\u00fa\u00f8\3"+
		"\2\2\2\u00fb\u0101\5\62\32\2\u00fc\u00fd\5\36\20\2\u00fd\u00fe\5\62\32"+
		"\2\u00fe\u0100\3\2\2\2\u00ff\u00fc\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff"+
		"\3\2\2\2\u0101\u0102\3\2\2\2\u0102\61\3\2\2\2\u0103\u0101\3\2\2\2\u0104"+
		"\u010a\5\64\33\2\u0105\u0106\5 \21\2\u0106\u0107\5\64\33\2\u0107\u0109"+
		"\3\2\2\2\u0108\u0105\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b\63\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010f\5\"\22"+
		"\2\u010e\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111"+
		"\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0114\5\66\34\2"+
		"\u0114\65\3\2\2\2\u0115\u0119\58\35\2\u0116\u0118\5$\23\2\u0117\u0116"+
		"\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a"+
		"\67\3\2\2\2\u011b\u0119\3\2\2\2\u011c\u0122\5&\24\2\u011d\u0122\5<\37"+
		"\2\u011e\u0122\5@!\2\u011f\u0122\5B\"\2\u0120\u0122\5D#\2\u0121\u011c"+
		"\3\2\2\2\u0121\u011d\3\2\2\2\u0121\u011e\3\2\2\2\u0121\u011f\3\2\2\2\u0121"+
		"\u0120\3\2\2\2\u01229\3\2\2\2\u0123\u0124\5&\24\2\u0124;\3\2\2\2\u0125"+
		"\u0126\7\62\2\2\u0126\u0127\5> \2\u0127=\3\2\2\2\u0128\u0131\7\30\2\2"+
		"\u0129\u012e\5(\25\2\u012a\u012b\7\37\2\2\u012b\u012d\5(\25\2\u012c\u012a"+
		"\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0131\u0129\3\2\2\2\u0131\u0132\3\2"+
		"\2\2\u0132\u0133\3\2\2\2\u0133\u0135\7\31\2\2\u0134\u0128\3\2\2\2\u0134"+
		"\u0135\3\2\2\2\u0135?\3\2\2\2\u0136\u013a\7\32\2\2\u0137\u0138\5F$\2\u0138"+
		"\u0139\7!\2\2\u0139\u013b\3\2\2\2\u013a\u0137\3\2\2\2\u013b\u013c\3\2"+
		"\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\3\2\2\2\u013e"+
		"\u013f\7\33\2\2\u013fA\3\2\2\2\u0140\u0141\7 \2\2\u0141\u0143\5\f\7\2"+
		"\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143C\3\2\2\2\u0144\u0145\7"+
		"\30\2\2\u0145\u0146\5(\25\2\u0146\u0147\5B\"\2\u0147\u0148\7\31\2\2\u0148"+
		"E\3\2\2\2\u0149\u014e\5H%\2\u014a\u014e\5J&\2\u014b\u014e\5L\'\2\u014c"+
		"\u014e\5N(\2\u014d\u0149\3\2\2\2\u014d\u014a\3\2\2\2\u014d\u014b\3\2\2"+
		"\2\u014d\u014c\3\2\2\2\u014eG\3\2\2\2\u014f\u0150\5(\25\2\u0150I\3\2\2"+
		"\2\u0151\u0152\5(\25\2\u0152\u0153\7\61\2\2\u0153\u0154\5(\25\2\u0154"+
		"K\3\2\2\2\u0155\u0156\7\13\2\2\u0156\u0157\5(\25\2\u0157\u0158\7\16\2"+
		"\2\u0158\u0159\5F$\2\u0159\u015a\7\t\2\2\u015a\u015b\5F$\2\u015bM\3\2"+
		"\2\2\u015c\u015d\7\23\2\2\u015d\u015e\5(\25\2\u015e\u015f\7\b\2\2\u015f"+
		"\u0160\5F$\2\u0160O\3\2\2\2\32S`r\u008c\u0096\u00af\u00ba\u00cc\u00d9"+
		"\u00df\u00e7\u00ef\u00f8\u0101\u010a\u0110\u0119\u0121\u012e\u0131\u0134"+
		"\u013c\u0142\u014d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}