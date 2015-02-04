package edu.ycp.cs.dh.acegwt.client.ace;

import com.google.gwt.core.client.JavaScriptObject;

public class AceEditorModeCustom {
	protected JavaScriptObject ancestor;
	
	public AceEditorModeCustom() {
		this.ancestor = getJavaScriptAncestor();
	}
	
	protected native JavaScriptObject getJavaScriptAncestor() /*-{
		var modeName = "ace/mode/text";
		var textMode = $wnd.require(modeName).Mode;
		return textMode;
	}-*/;

	public AceHighlightRules getHighlightRules() {
		return null;
	}
	
	public String getShortModeId() {
		return null;
	}

	public String getLineCommentStart() {
		return null;
	}
	
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
