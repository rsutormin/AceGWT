package edu.ycp.cs.dh.acegwt.client.ace;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;

public class AceHighlightRules {
	protected Map<String, List<Rule>> rules = new LinkedHashMap<String, List<Rule>>();
	protected JavaScriptObject ancestor = null;
	protected boolean doNormalizeRules = false;
	
	public AceHighlightRules() {
		this.ancestor = getJavaScriptAncestor();
	}
	
	protected native JavaScriptObject getJavaScriptAncestor() /*-{
		var rulesName = "ace/mode/text_highlight_rules";
		return $wnd.require(rulesName).TextHighlightRules;
	}-*/;
	
	public AceHighlightRules addRules(String stateName, Rule... rules) {
		List<Rule> ruleList = new ArrayList<Rule>(rules.length);
		for (Rule r : rules)
			ruleList.add(r);
		addRules(stateName, ruleList);
		return this;
	}

	public AceHighlightRules addRules(String stateName, List<Rule> rules) {
		this.rules.put(stateName, rules);
		return this;
	}
	
	public void setDoNormalizeRules(boolean doNormalizeRules) {
		this.doNormalizeRules = doNormalizeRules;
	}
	
	public native JavaScriptObject toJavaScript() /*-{
		var javaRuleMap = this.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules::rules;
		var self = this;
		var highlightRules = function() {
			var jsRules = {};
			var stateList = @java.util.ArrayList::new(Ljava/util/Collection;)(javaRuleMap.@java.util.Map::keySet()());
			var stateCount = stateList.@java.util.List::size()();
			for (var statePos = 0; statePos < stateCount; statePos++) {
				var stateName = stateList.@java.util.List::get(I)(statePos);
				var javaRuleList = javaRuleMap.@java.util.Map::get(Ljava/lang/Object;)(stateName);
				var jsRuleList = [];
				var ruleCount = javaRuleList.@java.util.List::size()();
				for (var rulePos = 0; rulePos < ruleCount; rulePos++) {
					var javaRule = javaRuleList.@java.util.List::get(I)(rulePos);
					var jsRule = {};
					var tokenString = javaRule.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.Rule::tokenString;
					if (tokenString)
						jsRule['token'] = tokenString;
					var tokenMapper = javaRule.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.Rule::tokenMapper;
					if (tokenMapper) {
						var jsMap = tokenMapper.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.KeywordMapper::getMap()();
						var defaultToken = tokenMapper.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.KeywordMapper::defaultToken;
						var ignoreCase = tokenMapper.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.KeywordMapper::ignoreCase;
						var splitChar = tokenMapper.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.KeywordMapper::splitChar;
						var keywordMapper = this.createKeywordMapper(jsMap, defaultToken, ignoreCase, splitChar);
						this.$keywords = keywordMapper;
						jsRule['token'] = keywordMapper;
					}
					var regex = javaRule.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.Rule::regex;
					if (regex)
						jsRule['regex'] = regex;
					var javaNext = javaRule.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.Rule::next;
					if (javaNext) {
						var jsNext = [];
						var nextCount = javaNext.@java.util.List::size()();
						for (var nextPos = 0; nextPos < nextCount; nextPos++)
							jsNext.push(javaNext.@java.util.List::get(I)(nextPos));
						jsRule['token'] = jsNext;
					}
					var defaultToken = javaRule.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.Rule::defaultToken;
					if (defaultToken)
						jsRule['defaultToken'] = defaultToken;
					var caseInsensitive = javaRule.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.Rule::caseInsensitive;
					if (caseInsensitive)
						jsRule['caseInsensitive'] = caseInsensitive.@java.lang.Boolean::booleanValue()();
					var start = javaRule.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.Rule::start;
					if (start)
						jsRule['start'] = start;
					var end = javaRule.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.Rule::end;
					if (end)
						jsRule['end'] = end;
					jsRuleList.push(jsRule);
				}
				jsRules[stateName] = jsRuleList;
			}
    		this.$rules = jsRules;
			if (self.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules::doNormalizeRules)
				this.normalizeRules();
		};
		var ancestorHihglightRules = this.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules::ancestor;
		var oop = $wnd.require("ace/lib/oop");
		oop.inherits(highlightRules, ancestorHihglightRules);
		return highlightRules;
	}-*/;
	
	public static class Rule {
		private String tokenString = null;
		private KeywordMapper tokenMapper = null;
		private String regex = null;
		private String start = null;
		private String end = null;
		private String defaultToken = null;
		private List<String> next = null;
		private Boolean caseInsensitive = null;
		
		public Rule() {
		}

		public Rule(String token, String regex) {
			withToken(token).withRegex(regex);
		}
		
		public Rule(String defaultToken) {
			withDefault(defaultToken);
		}
		
		public Rule(String token, String start, String end) {
			withToken(token).withStartEnd(start, end);
		}
		
		public Rule withToken(String token) {
			this.tokenString = token;
			return this;
		}
		
		public Rule withRegex(String regex) {
			this.regex = regex;
			return this;
		}

		public Rule withToken(KeywordMapper token) {
			this.tokenMapper = token;
			return this;
		}

		public Rule withStartEnd(String start, String end) {
			this.start = start;
			this.end = end;
			return this;
		}

		public Rule withDefault(String defaultToken) {
			this.defaultToken = defaultToken;
			return this;
		}
		
		public Rule withNext(String... next) {
			List<String> list = new ArrayList<String>(next.length);
			for (String n : next)
				list.add(n);
			withNext(list);
			return this;
		}
		
		public Rule withNext(List<String> next) {
			this.next = next;
			return this;
		}

		public Rule withCaseInsensitive(boolean caseInsensitive) {
			this.caseInsensitive = caseInsensitive;
			return this;
		}
		
		private static native void putArrayItem(JavaScriptObject map, String key, String arrayItem) /*-{
			var array = map[key];
			if (!array) {
				array = [];
				map[key] = array;
			}
			array.push(arrayItem);
		}-*/;

		public String getTokenString() {
			return this.tokenString;
		}

		public KeywordMapper getTokenMapper() {
			return tokenMapper;
		}
		
		public String getRegex() {
			return regex;
		}

		public String getDefaultToken() {
			return defaultToken;
		}
		
		public List<String> getNext() {
			return this.next;
		}
		
		public Boolean getCaseInsensitive() {
			return caseInsensitive;
		}
		
		public String getStart() {
			return start;
		}
		
		public String getEnd() {
			return end;
		}
	}
	
	public static enum KeywordClass {
		STORAGE("storage"),						// blue
		KEYWORD("keyword"),						// blue
		CONSTANT("constant"),					// rgb(197, 6, 11)
		CONSTANT_BUILDIN("constant.buildin"),	// rgb(88, 72, 246)
		CONSTANT_LANGUAGE("constant.language"),	// rgb(88, 92, 246)
		CONSTANT_LIBRARY("constant.library"),	// rgb(6, 150, 14)
		INVALID("invalid"),						// rgba(255, 0, 0, 0.1)
		SUPPORT_FUNCTION("support.function"),	// rgb(60, 76, 114)
		SUPPORT_CONSTANT("support.constant"),	// rgb(6, 150, 14)
		SUPPORT_TYPE("support.type"),			// rgb(109, 121, 222)
		SUPPORT_CLASS("support.class"),			// rgb(109, 121, 222)
		KEYWORD_OPERATOR("keyword.operator"),	// rgb(104, 118, 135)
		STRING("string"),						// rgb(3, 106, 7)
		COMMENT("comment"),						// rgb(76, 136, 107)
		COMMENT_DOC("comment.doc"),				// rgb(0, 102, 255)
		COMMENT_DOC_TAG("comment.doc.tag"),		// rgb(128, 159, 191)
		CONSTANT_NUMERIC("constant.numeric"),	// rgb(0, 0, 205)
		VARIABLE("variable");					// rgb(49, 132, 149)
		
		private final String name;

		private KeywordClass(final String name) {
			this.name = name;
		}

		/**
		 * @return the keyword class name (e.g., "constant.language")
		 */
		public String getName() {
			return name;
		}
	}
	
	public static class KeywordMapper {
		private JavaScriptObject map = JavaScriptObject.createObject();
		private String defaultToken = null;
		private boolean ignoreCase = false; 
		private String splitChar = null;
		
		public KeywordMapper(String defaultToken) {
			this.defaultToken = defaultToken;
		}
		
		public String getDefaultToken() {
			return defaultToken;
		}

		public KeywordMapper with(KeywordClass keywordClass, String keywords) {
			return with(keywordClass.getName(), keywords);
		}

		public native KeywordMapper with(String className, String keywords) /*-{
			var map = this.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.KeywordMapper::map;
			map[className] = keywords;
			return this;
		}-*/;
		
		public JavaScriptObject getMap() {
			return map;
		}
		
		public KeywordMapper withIgnoreCase(boolean ignoreCase) {
			this.ignoreCase = ignoreCase;
			return this;
		}
		
		public boolean isIgnoreCase() {
			return this.ignoreCase;
		}
		
		public KeywordMapper withSplitChar(String splitChar) {
			this.splitChar = splitChar;
			return this;
		}
		
		public String getSplitChar() {
			return splitChar;
		}
	}
}
