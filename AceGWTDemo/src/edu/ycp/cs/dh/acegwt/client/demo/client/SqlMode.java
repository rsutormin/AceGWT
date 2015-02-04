package edu.ycp.cs.dh.acegwt.client.demo.client;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditorModeCustom;
import edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules;

public class SqlMode extends AceEditorModeCustom {

	@Override
	public String getShortModeId() {
		return "sql2";
	}

	@Override
	public AceHighlightRules getHighlightRules() {
		return new SqlHighlightRules();
	}

	@Override
	public String getLineCommentStart() {
		return "--";
	}
	
	public static class SqlHighlightRules extends AceHighlightRules {
		public SqlHighlightRules() {
		    String keywords = "" +
		    		"select|insert|update|delete|from|where|and|or|group|by|order|"+
		    		"limit|offset|having|as|case|when|else|end|type|left|right|" +
		            "join|on|outer|desc|asc|union";
		    String builtinConstants = "true|false|null";
		    String builtinFunctions = "count|min|max|avg|sum|rank|now|coalesce";
		    KeywordMapper keywordMapper = new KeywordMapper("identifier")
		    			.with(KeywordClass.SUPPORT_FUNCTION, builtinFunctions)
		    			.with(KeywordClass.KEYWORD, keywords)
		    			.with(KeywordClass.CONSTANT_LANGUAGE, builtinConstants)
		    			.withIgnoreCase(true);
			addRules("start", 
					new Rule("comment", "--.*$"),
					new Rule("comment", "/\\*", "\\*/"),
					new Rule("string", "\".*?\""), 
					new Rule("string", "'.*?'"),
					new Rule("constant.numeric", 
							"[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"),
					new Rule().withToken(keywordMapper).withRegex("[a-zA-Z_$][a-zA-Z0-9_$]*\\b"),
					new Rule("keyword.operator", 
							"\\+|\\-|\\/|\\/\\/|%|<@>|@>|<@|&|\\^|~|<|>|<=|=>|==|!=|<>|="),
					new Rule("paren.lparen", "[\\(]"),
					new Rule("paren.rparen", "[\\)]"),
					new Rule("text", "\\s+")
	        );
			setDoNormalizeRules(true);
		}
	}
}
