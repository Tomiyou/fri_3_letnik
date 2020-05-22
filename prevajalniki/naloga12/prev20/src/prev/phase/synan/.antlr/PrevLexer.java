// Generated from /home/tomi/random/fri_3_letnik/prevajalniki/naloga9/prev20/src/prev/phase/synan/PrevLexer.g4 by ANTLR 4.7.1

	package prev.phase.lexan;
	import prev.common.report.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"VOID", "BOOLEAN", "KEYWORD_BOOL", "KEYWORD_CHAR", "KEYWORD_DEL", "KEYWORD_DO", 
		"KEYWORD_ELSE", "KEYWORD_FUN", "KEYWORD_IF", "KEYWORD_INT", "KEYWORD_NEW", 
		"KEYWORD_THEN", "KEYWORD_TYP", "KEYWORD_VAR", "KEYWORD_VOID", "KEYWORD_WHERE", 
		"KEYWORD_WHILE", "INTEGER", "CHAR", "STRING", "POINTER", "SYMBOL_LBR", 
		"SYMBOL_RBR", "SYMBOL_LCBR", "SYMBOL_RCBR", "SYMBOL_LSBR", "SYMBOL_RSBR", 
		"SYMBOL_DOT", "SYMBOL_COMMA", "SYMBOL_COLON", "SYMBOL_SEMIC", "SYMBOL_AND", 
		"SYMBOL_OR", "SYMBOL_EXCL", "SYMBOL_EQ", "SYMBOL_NEQ", "SYMBOL_LT", "SYMBOL_GT", 
		"SYMBOL_LEQ", "SYMBOL_GEQ", "SYMBOL_STAR", "SYMBOL_SLASH", "SYMBOL_MOD", 
		"SYMBOL_PLUS", "SYMBOL_MIN", "SYMBOL_PWR", "SYMBOL_EQUALS", "IDENTIFIER", 
		"COMMENT", "WHITESPACE", "ERROR"
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
		public LexAn.PrevToken nextToken() {
			return (LexAn.PrevToken) super.nextToken();
		}


	public PrevLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PrevLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 50:
			ERROR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ERROR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			if (true) throw new Report.Error("Error at " + this.getLine() + ":" + this.getCharPositionInLine());
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\65\u0134\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3x\n\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\6\23\u00c5\n\23"+
		"\r\23\16\23\u00c6\3\24\3\24\3\24\3\24\5\24\u00cd\n\24\3\24\3\24\3\25\3"+
		"\25\3\25\3\25\7\25\u00d5\n\25\f\25\16\25\u00d8\13\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3"+
		"\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3$\3"+
		"%\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3)\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3."+
		"\3/\3/\3\60\3\60\3\61\3\61\7\61\u011a\n\61\f\61\16\61\u011d\13\61\3\62"+
		"\3\62\7\62\u0121\n\62\f\62\16\62\u0124\13\62\3\62\5\62\u0127\n\62\3\62"+
		"\5\62\u012a\n\62\3\62\3\62\3\63\3\63\3\63\3\63\3\64\3\64\3\64\2\2\65\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37="+
		" ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65\3\2\t\3\2\62"+
		";\3\2\"\u0080\5\2C\\aac|\6\2\62;C\\aac|\4\2\f\f\17\17\3\3\f\f\f\2\13\17"+
		"\"\"\u0087\u0087\u00a2\u00a2\u1682\u1682\u2002\u200c\u202a\u202b\u2031"+
		"\u2031\u2061\u2061\u3002\u3002\2\u013b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2"+
		"\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2"+
		"[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3"+
		"\2\2\2\3i\3\2\2\2\5w\3\2\2\2\7y\3\2\2\2\t\u0081\3\2\2\2\13\u0086\3\2\2"+
		"\2\r\u008a\3\2\2\2\17\u008d\3\2\2\2\21\u0092\3\2\2\2\23\u0096\3\2\2\2"+
		"\25\u0099\3\2\2\2\27\u00a1\3\2\2\2\31\u00a5\3\2\2\2\33\u00aa\3\2\2\2\35"+
		"\u00ae\3\2\2\2\37\u00b2\3\2\2\2!\u00b7\3\2\2\2#\u00bd\3\2\2\2%\u00c4\3"+
		"\2\2\2\'\u00c8\3\2\2\2)\u00d0\3\2\2\2+\u00db\3\2\2\2-\u00df\3\2\2\2/\u00e1"+
		"\3\2\2\2\61\u00e3\3\2\2\2\63\u00e5\3\2\2\2\65\u00e7\3\2\2\2\67\u00e9\3"+
		"\2\2\29\u00eb\3\2\2\2;\u00ed\3\2\2\2=\u00ef\3\2\2\2?\u00f1\3\2\2\2A\u00f3"+
		"\3\2\2\2C\u00f5\3\2\2\2E\u00f7\3\2\2\2G\u00f9\3\2\2\2I\u00fc\3\2\2\2K"+
		"\u00ff\3\2\2\2M\u0101\3\2\2\2O\u0103\3\2\2\2Q\u0106\3\2\2\2S\u0109\3\2"+
		"\2\2U\u010b\3\2\2\2W\u010d\3\2\2\2Y\u010f\3\2\2\2[\u0111\3\2\2\2]\u0113"+
		"\3\2\2\2_\u0115\3\2\2\2a\u0117\3\2\2\2c\u011e\3\2\2\2e\u012d\3\2\2\2g"+
		"\u0131\3\2\2\2ij\7p\2\2jk\7q\2\2kl\7p\2\2lm\7g\2\2m\4\3\2\2\2no\7v\2\2"+
		"op\7t\2\2pq\7w\2\2qx\7g\2\2rs\7h\2\2st\7c\2\2tu\7n\2\2uv\7u\2\2vx\7g\2"+
		"\2wn\3\2\2\2wr\3\2\2\2x\6\3\2\2\2yz\7d\2\2z{\7q\2\2{|\7q\2\2|}\7n\2\2"+
		"}~\7g\2\2~\177\7c\2\2\177\u0080\7p\2\2\u0080\b\3\2\2\2\u0081\u0082\7e"+
		"\2\2\u0082\u0083\7j\2\2\u0083\u0084\7c\2\2\u0084\u0085\7t\2\2\u0085\n"+
		"\3\2\2\2\u0086\u0087\7f\2\2\u0087\u0088\7g\2\2\u0088\u0089\7n\2\2\u0089"+
		"\f\3\2\2\2\u008a\u008b\7f\2\2\u008b\u008c\7q\2\2\u008c\16\3\2\2\2\u008d"+
		"\u008e\7g\2\2\u008e\u008f\7n\2\2\u008f\u0090\7u\2\2\u0090\u0091\7g\2\2"+
		"\u0091\20\3\2\2\2\u0092\u0093\7h\2\2\u0093\u0094\7w\2\2\u0094\u0095\7"+
		"p\2\2\u0095\22\3\2\2\2\u0096\u0097\7k\2\2\u0097\u0098\7h\2\2\u0098\24"+
		"\3\2\2\2\u0099\u009a\7k\2\2\u009a\u009b\7p\2\2\u009b\u009c\7v\2\2\u009c"+
		"\u009d\7g\2\2\u009d\u009e\7i\2\2\u009e\u009f\7g\2\2\u009f\u00a0\7t\2\2"+
		"\u00a0\26\3\2\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7"+
		"y\2\2\u00a4\30\3\2\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7j\2\2\u00a7\u00a8"+
		"\7g\2\2\u00a8\u00a9\7p\2\2\u00a9\32\3\2\2\2\u00aa\u00ab\7v\2\2\u00ab\u00ac"+
		"\7{\2\2\u00ac\u00ad\7r\2\2\u00ad\34\3\2\2\2\u00ae\u00af\7x\2\2\u00af\u00b0"+
		"\7c\2\2\u00b0\u00b1\7t\2\2\u00b1\36\3\2\2\2\u00b2\u00b3\7x\2\2\u00b3\u00b4"+
		"\7q\2\2\u00b4\u00b5\7k\2\2\u00b5\u00b6\7f\2\2\u00b6 \3\2\2\2\u00b7\u00b8"+
		"\7y\2\2\u00b8\u00b9\7j\2\2\u00b9\u00ba\7g\2\2\u00ba\u00bb\7t\2\2\u00bb"+
		"\u00bc\7g\2\2\u00bc\"\3\2\2\2\u00bd\u00be\7y\2\2\u00be\u00bf\7j\2\2\u00bf"+
		"\u00c0\7k\2\2\u00c0\u00c1\7n\2\2\u00c1\u00c2\7g\2\2\u00c2$\3\2\2\2\u00c3"+
		"\u00c5\t\2\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c4\3\2"+
		"\2\2\u00c6\u00c7\3\2\2\2\u00c7&\3\2\2\2\u00c8\u00cc\7)\2\2\u00c9\u00ca"+
		"\7^\2\2\u00ca\u00cd\7)\2\2\u00cb\u00cd\t\3\2\2\u00cc\u00c9\3\2\2\2\u00cc"+
		"\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\7)\2\2\u00cf(\3\2\2\2\u00d0"+
		"\u00d6\7$\2\2\u00d1\u00d2\7^\2\2\u00d2\u00d5\7$\2\2\u00d3\u00d5\t\3\2"+
		"\2\u00d4\u00d1\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4"+
		"\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9"+
		"\u00da\7$\2\2\u00da*\3\2\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7k\2\2\u00dd"+
		"\u00de\7n\2\2\u00de,\3\2\2\2\u00df\u00e0\7*\2\2\u00e0.\3\2\2\2\u00e1\u00e2"+
		"\7+\2\2\u00e2\60\3\2\2\2\u00e3\u00e4\7}\2\2\u00e4\62\3\2\2\2\u00e5\u00e6"+
		"\7\177\2\2\u00e6\64\3\2\2\2\u00e7\u00e8\7]\2\2\u00e8\66\3\2\2\2\u00e9"+
		"\u00ea\7_\2\2\u00ea8\3\2\2\2\u00eb\u00ec\7\60\2\2\u00ec:\3\2\2\2\u00ed"+
		"\u00ee\7.\2\2\u00ee<\3\2\2\2\u00ef\u00f0\7<\2\2\u00f0>\3\2\2\2\u00f1\u00f2"+
		"\7=\2\2\u00f2@\3\2\2\2\u00f3\u00f4\7(\2\2\u00f4B\3\2\2\2\u00f5\u00f6\7"+
		"~\2\2\u00f6D\3\2\2\2\u00f7\u00f8\7#\2\2\u00f8F\3\2\2\2\u00f9\u00fa\7?"+
		"\2\2\u00fa\u00fb\7?\2\2\u00fbH\3\2\2\2\u00fc\u00fd\7#\2\2\u00fd\u00fe"+
		"\7?\2\2\u00feJ\3\2\2\2\u00ff\u0100\7>\2\2\u0100L\3\2\2\2\u0101\u0102\7"+
		"@\2\2\u0102N\3\2\2\2\u0103\u0104\7>\2\2\u0104\u0105\7?\2\2\u0105P\3\2"+
		"\2\2\u0106\u0107\7@\2\2\u0107\u0108\7?\2\2\u0108R\3\2\2\2\u0109\u010a"+
		"\7,\2\2\u010aT\3\2\2\2\u010b\u010c\7\61\2\2\u010cV\3\2\2\2\u010d\u010e"+
		"\7\'\2\2\u010eX\3\2\2\2\u010f\u0110\7-\2\2\u0110Z\3\2\2\2\u0111\u0112"+
		"\7/\2\2\u0112\\\3\2\2\2\u0113\u0114\7`\2\2\u0114^\3\2\2\2\u0115\u0116"+
		"\7?\2\2\u0116`\3\2\2\2\u0117\u011b\t\4\2\2\u0118\u011a\t\5\2\2\u0119\u0118"+
		"\3\2\2\2\u011a\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c"+
		"b\3\2\2\2\u011d\u011b\3\2\2\2\u011e\u0122\7%\2\2\u011f\u0121\n\6\2\2\u0120"+
		"\u011f\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2"+
		"\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0127\7\17\2\2\u0126"+
		"\u0125\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0129\3\2\2\2\u0128\u012a\t\7"+
		"\2\2\u0129\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\b\62\2\2\u012c"+
		"d\3\2\2\2\u012d\u012e\t\b\2\2\u012e\u012f\3\2\2\2\u012f\u0130\b\63\2\2"+
		"\u0130f\3\2\2\2\u0131\u0132\13\2\2\2\u0132\u0133\b\64\3\2\u0133h\3\2\2"+
		"\2\f\2w\u00c6\u00cc\u00d4\u00d6\u011b\u0122\u0126\u0129\4\b\2\2\3\64\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}