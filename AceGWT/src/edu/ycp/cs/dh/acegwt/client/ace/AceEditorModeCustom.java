package edu.ycp.cs.dh.acegwt.client.ace;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Ace editor mode implemented in java. It's experimental feature 
 * yet and it covers only token highlighting.
 */
public class AceEditorModeCustom {
	protected JavaScriptObject ancestor;
	
	/**
	 * Create default text editor mode (no highlighting).
	 */
	public AceEditorModeCustom() {
		this.ancestor = getJavaScriptAncestor();
	}
	
	protected native JavaScriptObject getJavaScriptAncestor() /*-{
		var modeName = "ace/mode/text";
		var textMode = $wnd.require(modeName).Mode;
		return textMode;
	}-*/;

	/**
	 * Give highlight rules. Inheriting class can override this 
	 * method if necessary.
	 * @return object with highlighting rules
	 */
	public AceHighlightRules getHighlightRules() {
		return null;
	}
	
	/**
	 * Give short mode ID (which will be suffix of "ace/mode/...").
	 * @return short mode ID
	 */
	public String getShortModeId() {
		return null;
	}

	/**
	 * Give starting prefix of one line comment.
	 * @return starting prefix of one line comment
	 */
	public String getLineCommentStart() {
		return null;
	}
	
	/**
	 * Create JavaScript object representing editor mode.
	 * @return JavaScript object representing editor mode
	 */
	public native JavaScriptObject toJavaScript() /*-{
		var javaRules = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditorModeCustom::getHighlightRules()();
		var jsRules = javaRules.@edu.ycp.cs.dh.acegwt.client.ace.AceHighlightRules::toJavaScript()();
		var Mode = function() {
    		this.HighlightRules = jsRules;
		};
		var ancestorMode = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditorModeCustom::ancestor;
		var oop = $wnd.require("ace/lib/oop");
		oop.inherits(Mode, ancestorMode);
		var lineCommentStart = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditorModeCustom::getLineCommentStart()();
		var id = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditorModeCustom::getShortModeId();
		(function() {
			if (lineCommentStart)
    			this.lineCommentStart = lineCommentStart;
    		this.$id = "ace/mode/" + id;
		}).call(Mode.prototype);
		return Mode;
	}-*/;
}
