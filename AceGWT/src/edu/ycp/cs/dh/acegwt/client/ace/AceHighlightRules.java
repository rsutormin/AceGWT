package edu.ycp.cs.dh.acegwt.client.ace;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Ace editor highlighting rules.
 */
public class AceHighlightRules {
	protected Map<String, List<Rule>> rules = new LinkedHashMap<String, List<Rule>>();
	protected JavaScriptObject ancestor = null;
	protected boolean doNormalizeRules = false;
	
	/**
	 * Create new object defining highlighting rules. 
	 */
	public AceHighlightRules() {
		this.ancestor = getJavaScriptAncestor();
	}
	
	protected native JavaScriptObject getJavaScriptAncestor() /*-{
		var rulesName = "ace/mode/text_highlight_rules";
		return $wnd.require(rulesName).TextHighlightRules;
	}-*/;
	
	/**
	 * Add highlighting rules. 
	 * @param stateName name of upstream state (use "start" if you don't know how to choose it)
	 * @param rules array of highlighting rules
	 * @return reference to this rules object (for method chaining)
	 */
	public AceHighlightRules addRules(String stateName, Rule... rules) {
		List<Rule> ruleList = new ArrayList<Rule>(rules.length);
		for (Rule r : rules)
			ruleList.add(r);
		addRules(stateName, ruleList);
		return this;
	}

	/**
	 * Add highlighting rules. 
	 * @param stateName name of upstream state (use "start" if you don't know how to choose it)
	 * @param rules list of highlighting rules
	 * @return reference to this rules object (for method chaining)
	 */
	public AceHighlightRules addRules(String stateName, List<Rule> rules) {
		this.rules.put(stateName, rules);
		return this;
	}
	
	/**
	 * Define should rules be normalized or not. If you're not sure then it's better 
	 * to do it rather than not.
	 * @param doNormalizeRules
	 */
	public void setDoNormalizeRules(boolean doNormalizeRules) {
		this.doNormalizeRules = doNormalizeRules;
	}
	
	/**
	 * Create JavaScript object representing these highlighting rules.
	 * @return JavaScript object representing these highlighting rules
	 */
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
	
	/**
	 * Highlighting rule.
	 */
	public static class Rule {
		private String tokenString = null;
		private KeywordMapper tokenMapper = null;
		private String regex = null;
		private String start = null;
		private String end = null;
		private String defaultToken = null;
		private List<String> next = null;
		private Boolean caseInsensitive = null;

		/**
		 * Create rule without defined properties.
		 */
		public Rule() {
		}

		/**
		 * Create rule with token type and regular expression.
		 * @param token token type (see {@link KeywordClass} for common tokens) 
		 * @param regex regular expression searching token in text 
		 */
		public Rule(String token, String regex) {
			withToken(token).withRegex(regex);
		}
		
		/**
		 * Create rule with default token.
		 * @param defaultToken token name (see {@link KeywordClass} for common tokens)
		 */
		public Rule(String defaultToken) {
			withDefault(defaultToken);
		}
		
		/**
		 * Create rule with token type and starting and ending regular expressions.
		 * @param token token type (see {@link KeywordClass} for common tokens)
		 * @param start
		 * @param end
		 */
		public Rule(String token, String start, String end) {
			withToken(token).withStartEnd(start, end);
		}
		
		/**
		 * Set token type.
		 * @param token token type (see {@link KeywordClass} for common tokens)
		 * @return reference to this rule (for method chaining)
		 */
		public Rule withToken(String token) {
			this.tokenString = token;
			return this;
		}
		
		/**
		 * Set regular expression.
		 * @param regex regular expression searching token in text 
		 * @return reference to this rule (for method chaining)
		 */
		public Rule withRegex(String regex) {
			this.regex = regex;
			return this;
		}

		/**
		 * Set token as keyword mapper.
		 * @param token keyword mapper (see {@link KeywordMapper})
		 * @return reference to this rule (for method chaining)
		 */
		public Rule withToken(KeywordMapper token) {
			this.tokenMapper = token;
			return this;
		}

		/**
		 * Set starting and ending regular expressions.
		 * @param start starting regular expression
		 * @param end ending regular expression
		 * @return reference to this rule (for method chaining)
		 */
		public Rule withStartEnd(String start, String end) {
			this.start = start;
			this.end = end;
			return this;
		}

		/**
		 * Set default token.
		 * @param defaultToken default token (see {@link KeywordClass})
		 * @return reference to this rule (for method chaining)
		 */
		public Rule withDefault(String defaultToken) {
			this.defaultToken = defaultToken;
			return this;
		}
		
		/**
		 * Define next states.
		 * @param next array of next states
		 * @return reference to this rule (for method chaining)
		 */
		public Rule withNext(String... next) {
			List<String> list = new ArrayList<String>(next.length);
			for (String n : next)
				list.add(n);
			withNext(list);
			return this;
		}
		
		/**
		 * Define next states.
		 * @param next list of next states
		 * @return reference to this rule (for method chaining)
		 */
		public Rule withNext(List<String> next) {
			this.next = next;
			return this;
		}

		/**
		 * Define whether or not token is case insensitive. Default value is false.
		 * @param caseInsensitive whether or not token is case insensitive
		 * @return reference to this rule (for method chaining)
		 */
		public Rule withCaseInsensitive(Boolean caseInsensitive) {
			this.caseInsensitive = caseInsensitive;
			return this;
		}
		
		/**
		 * Defined token type.
		 * @return token type
		 */
		public String getTokenString() {
			return this.tokenString;
		}

		/**
		 * Defined token mapper.
		 * @return token mapper
		 */
		public KeywordMapper getTokenMapper() {
			return tokenMapper;
		}
		
		/**
		 * Defined regular expression searching token in text.
		 * @return regular expression searching token in text
		 */
		public String getRegex() {
			return regex;
		}

		/**
		 * Defined default token
		 * @return default token
		 */
		public String getDefaultToken() {
			return defaultToken;
		}
		
		/**
		 * Defined list of next states.
		 * @return list of next states
		 */
		public List<String> getNext() {
			return this.next;
		}
		
		/**
		 * Defined case insensitive flag.
		 * @return case insensitive flag
		 */
		public Boolean getCaseInsensitive() {
			return caseInsensitive;
		}
		
		/**
		 * Defined starting regular expression.
		 * @return starting regular expression
		 */
		public String getStart() {
			return start;
		}
		
		/**
		 * Defined ending regular expression.
		 * @return ending regular expression
		 */
		public String getEnd() {
			return end;
		}
	}
	
	/**
	 * Enumeration of keyword classes defining token colors (see {@link KeywordMapper}).
	 */
	public static enum KeywordClass {			// colors in default theme
		STORAGE("storage"),						// blue
		KEYWORD("keyword"),						// blue
		KEYWORD_OPERATOR("keyword.operator"),	// rgb(104, 118, 135)
		CONSTANT("constant"),					// rgb(197, 6, 11)
		CONSTANT_BUILDIN("constant.buildin"),	// rgb(88, 72, 246)
		CONSTANT_LANGUAGE("constant.language"),	// rgb(88, 92, 246)
		CONSTANT_LIBRARY("constant.library"),	// rgb(6, 150, 14)
		CONSTANT_NUMERIC("constant.numeric"),	// rgb(0, 0, 205)
		INVALID("invalid"),						// rgba(255, 0, 0, 0.1)
		SUPPORT_FUNCTION("support.function"),	// rgb(60, 76, 114)
		SUPPORT_CONSTANT("support.constant"),	// rgb(6, 150, 14)
		SUPPORT_TYPE("support.type"),			// rgb(109, 121, 222)
		SUPPORT_CLASS("support.class"),			// rgb(109, 121, 222)
		STRING("string"),						// rgb(3, 106, 7)
		COMMENT("comment"),						// rgb(76, 136, 107)
		COMMENT_DOC("comment.doc"),				// rgb(0, 102, 255)
		COMMENT_DOC_TAG("comment.doc.tag"),		// rgb(128, 159, 191)
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

	/**
	 * Mapping from keyword class (see {@link KeywordClass}) to concatenated list of 
	 * keywords. Default separator is "|" (pipe).
	 */
	public static class KeywordMapper {
		private JavaScriptObject map = JavaScriptObject.createObject();
		private String defaultToken = null;
		private boolean ignoreCase = false; 
		private String splitChar = null;
		
		/**
		 * Create mapper with defined default token.
		 * @param defaultToken default token
		 */
		public KeywordMapper(String defaultToken) {
			this.defaultToken = defaultToken;
		}
		
		/**
		 * Defined default token.
		 * @return default token
		 */
		public String getDefaultToken() {
			return defaultToken;
		}

		/**
		 * Add key-value pair.
		 * @param keywordClass keyword class
		 * @param keywords concatenated list of keywords (default separator is "|")
		 * @return reference to this mapper (for method chaining)
		 */
		public KeywordMapper with(KeywordClass keywordClass, String keywords) {
			return with(keywordClass.getName(), keywords);
		}

		/**
		 * Add key-value pair.
		 * @param keywordClass keyword class name
		 * @param keywords concatenated list of keywords (default separator is "|")
		 * @return reference to this mapper (for method chaining)
		 */
		public native KeywordMapper with(String className, String keywords) /*-{
			var map = this.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules.KeywordMapper::map;
			map[className] = keywords;
			return this;
		}-*/;

		/**
		 * Give mapper as JavaScript object.
		 * @return mapper as JavaScript object
		 */
		public JavaScriptObject getMap() {
			return map;
		}
		
		/**
		 * Define ignore case flag.
		 * @param ignoreCase ignore case flag
		 * @return reference to this mapper (for method chaining)
		 */
		public KeywordMapper withIgnoreCase(boolean ignoreCase) {
			this.ignoreCase = ignoreCase;
			return this;
		}
		
		/**
		 * Give ignore case flag.
		 * @return ignore case flag
		 */
		public boolean isIgnoreCase() {
			return this.ignoreCase;
		}
		
		/**
		 * Define split character (default value is "|").
		 * @param splitChar split character
		 * @return reference to this mapper (for method chaining)
		 */
		public KeywordMapper withSplitChar(String splitChar) {
			this.splitChar = splitChar;
			return this;
		}
		
		/**
		 * Give split character.
		 * @return split character
		 */
		public String getSplitChar() {
			return splitChar;
		}
	}
}
