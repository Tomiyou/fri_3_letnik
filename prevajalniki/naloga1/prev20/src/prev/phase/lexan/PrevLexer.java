// Generated from lexan/PrevLexer.g4 by ANTLR 4.8

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
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VOID=1, BOOLEAN=2, KEYWORD_BOOL=3, KEYWORD_CHAR=4, KEYWORD_DEL=5, KEYWORD_DO=6, 
		KEYWORD_ELSE=7, KEYWORD_FUN=8, KEYWORD_IF=9, KEYWORD_INT=10, KEYWORD_NEW=11, 
		KEYWORD_THEN=12, KEYWORD_TYP=13, KEYWORD_VAR=14, KEYWORD_VOID=15, KEYWORD_WHERE=16, 
		KEYWORD_WHILE=17, SYMBOL=18, INTEGER=19, CHAR=20, STRING=21, POINTER=22, 
		IDENTIFIER=23, COMMENT=24, WHITESPACE=25, ERROR=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"VOID", "BOOLEAN", "KEYWORD_BOOL", "KEYWORD_CHAR", "KEYWORD_DEL", "KEYWORD_DO", 
			"KEYWORD_ELSE", "KEYWORD_FUN", "KEYWORD_IF", "KEYWORD_INT", "KEYWORD_NEW", 
			"KEYWORD_THEN", "KEYWORD_TYP", "KEYWORD_VAR", "KEYWORD_VOID", "KEYWORD_WHERE", 
			"KEYWORD_WHILE", "SYMBOL", "INTEGER", "CHAR", "STRING", "POINTER", "IDENTIFIER", 
			"COMMENT", "WHITESPACE", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'none'", null, "'boolean'", "'char'", "'del'", "'do'", "'else'", 
			"'fun'", "'if'", "'integer'", "'new'", "'then'", "'typ'", "'var'", "'void'", 
			"'where'", "'while'", null, null, null, null, "'nil'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VOID", "BOOLEAN", "KEYWORD_BOOL", "KEYWORD_CHAR", "KEYWORD_DEL", 
			"KEYWORD_DO", "KEYWORD_ELSE", "KEYWORD_FUN", "KEYWORD_IF", "KEYWORD_INT", 
			"KEYWORD_NEW", "KEYWORD_THEN", "KEYWORD_TYP", "KEYWORD_VAR", "KEYWORD_VOID", 
			"KEYWORD_WHERE", "KEYWORD_WHILE", "SYMBOL", "INTEGER", "CHAR", "STRING", 
			"POINTER", "IDENTIFIER", "COMMENT", "WHITESPACE", "ERROR"
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
		case 25:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\34\u00d5\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3F\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u009b\n\23\3\24\6\24"+
		"\u009e\n\24\r\24\16\24\u009f\3\25\3\25\3\25\3\25\5\25\u00a6\n\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\7\26\u00ae\n\26\f\26\16\26\u00b1\13\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\30\3\30\7\30\u00bb\n\30\f\30\16\30\u00be\13"+
		"\30\3\31\3\31\7\31\u00c2\n\31\f\31\16\31\u00c5\13\31\3\31\5\31\u00c8\n"+
		"\31\3\31\5\31\u00cb\n\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33"+
		"\2\2\34\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\3\2\n"+
		"\t\2##\'(*\61<@]]_`}\177\3\2\62;\3\2\"\u0080\5\2C\\aac|\6\2\62;C\\aac"+
		"|\4\2\f\f\17\17\3\3\f\f\f\2\13\17\"\"\u0087\u0087\u00a2\u00a2\u1682\u1682"+
		"\u2002\u200c\u202a\u202b\u2031\u2031\u2061\u2061\u3002\u3002\2\u00e0\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2\5E\3\2\2\2\7G\3\2"+
		"\2\2\tO\3\2\2\2\13T\3\2\2\2\rX\3\2\2\2\17[\3\2\2\2\21`\3\2\2\2\23d\3\2"+
		"\2\2\25g\3\2\2\2\27o\3\2\2\2\31s\3\2\2\2\33x\3\2\2\2\35|\3\2\2\2\37\u0080"+
		"\3\2\2\2!\u0085\3\2\2\2#\u008b\3\2\2\2%\u009a\3\2\2\2\'\u009d\3\2\2\2"+
		")\u00a1\3\2\2\2+\u00a9\3\2\2\2-\u00b4\3\2\2\2/\u00b8\3\2\2\2\61\u00bf"+
		"\3\2\2\2\63\u00ce\3\2\2\2\65\u00d2\3\2\2\2\678\7p\2\289\7q\2\29:\7p\2"+
		"\2:;\7g\2\2;\4\3\2\2\2<=\7v\2\2=>\7t\2\2>?\7w\2\2?F\7g\2\2@A\7h\2\2AB"+
		"\7c\2\2BC\7n\2\2CD\7u\2\2DF\7g\2\2E<\3\2\2\2E@\3\2\2\2F\6\3\2\2\2GH\7"+
		"d\2\2HI\7q\2\2IJ\7q\2\2JK\7n\2\2KL\7g\2\2LM\7c\2\2MN\7p\2\2N\b\3\2\2\2"+
		"OP\7e\2\2PQ\7j\2\2QR\7c\2\2RS\7t\2\2S\n\3\2\2\2TU\7f\2\2UV\7g\2\2VW\7"+
		"n\2\2W\f\3\2\2\2XY\7f\2\2YZ\7q\2\2Z\16\3\2\2\2[\\\7g\2\2\\]\7n\2\2]^\7"+
		"u\2\2^_\7g\2\2_\20\3\2\2\2`a\7h\2\2ab\7w\2\2bc\7p\2\2c\22\3\2\2\2de\7"+
		"k\2\2ef\7h\2\2f\24\3\2\2\2gh\7k\2\2hi\7p\2\2ij\7v\2\2jk\7g\2\2kl\7i\2"+
		"\2lm\7g\2\2mn\7t\2\2n\26\3\2\2\2op\7p\2\2pq\7g\2\2qr\7y\2\2r\30\3\2\2"+
		"\2st\7v\2\2tu\7j\2\2uv\7g\2\2vw\7p\2\2w\32\3\2\2\2xy\7v\2\2yz\7{\2\2z"+
		"{\7r\2\2{\34\3\2\2\2|}\7x\2\2}~\7c\2\2~\177\7t\2\2\177\36\3\2\2\2\u0080"+
		"\u0081\7x\2\2\u0081\u0082\7q\2\2\u0082\u0083\7k\2\2\u0083\u0084\7f\2\2"+
		"\u0084 \3\2\2\2\u0085\u0086\7y\2\2\u0086\u0087\7j\2\2\u0087\u0088\7g\2"+
		"\2\u0088\u0089\7t\2\2\u0089\u008a\7g\2\2\u008a\"\3\2\2\2\u008b\u008c\7"+
		"y\2\2\u008c\u008d\7j\2\2\u008d\u008e\7k\2\2\u008e\u008f\7n\2\2\u008f\u0090"+
		"\7g\2\2\u0090$\3\2\2\2\u0091\u0092\7?\2\2\u0092\u009b\7?\2\2\u0093\u0094"+
		"\7#\2\2\u0094\u009b\7?\2\2\u0095\u0096\7>\2\2\u0096\u009b\7?\2\2\u0097"+
		"\u0098\7@\2\2\u0098\u009b\7?\2\2\u0099\u009b\t\2\2\2\u009a\u0091\3\2\2"+
		"\2\u009a\u0093\3\2\2\2\u009a\u0095\3\2\2\2\u009a\u0097\3\2\2\2\u009a\u0099"+
		"\3\2\2\2\u009b&\3\2\2\2\u009c\u009e\t\3\2\2\u009d\u009c\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0(\3\2\2\2"+
		"\u00a1\u00a5\7)\2\2\u00a2\u00a3\7^\2\2\u00a3\u00a6\7)\2\2\u00a4\u00a6"+
		"\t\4\2\2\u00a5\u00a2\3\2\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00a8\7)\2\2\u00a8*\3\2\2\2\u00a9\u00af\7$\2\2\u00aa\u00ab\7^\2\2\u00ab"+
		"\u00ae\7$\2\2\u00ac\u00ae\t\4\2\2\u00ad\u00aa\3\2\2\2\u00ad\u00ac\3\2"+
		"\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\7$\2\2\u00b3,\3\2\2\2\u00b4"+
		"\u00b5\7p\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7n\2\2\u00b7.\3\2\2\2\u00b8"+
		"\u00bc\t\5\2\2\u00b9\u00bb\t\6\2\2\u00ba\u00b9\3\2\2\2\u00bb\u00be\3\2"+
		"\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\60\3\2\2\2\u00be\u00bc"+
		"\3\2\2\2\u00bf\u00c3\7%\2\2\u00c0\u00c2\n\7\2\2\u00c1\u00c0\3\2\2\2\u00c2"+
		"\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c7\3\2"+
		"\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c8\7\17\2\2\u00c7\u00c6\3\2\2\2\u00c7"+
		"\u00c8\3\2\2\2\u00c8\u00ca\3\2\2\2\u00c9\u00cb\t\b\2\2\u00ca\u00c9\3\2"+
		"\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\b\31\2\2\u00cd\62\3\2\2\2\u00ce\u00cf"+
		"\t\t\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\b\32\2\2\u00d1\64\3\2\2\2\u00d2"+
		"\u00d3\13\2\2\2\u00d3\u00d4\b\33\3\2\u00d4\66\3\2\2\2\r\2E\u009a\u009f"+
		"\u00a5\u00ad\u00af\u00bc\u00c3\u00c7\u00ca\4\b\2\2\3\33\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}