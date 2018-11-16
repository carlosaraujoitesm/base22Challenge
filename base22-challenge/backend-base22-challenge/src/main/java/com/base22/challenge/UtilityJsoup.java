package com.base22.challenge;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Attribute;


public class UtilityJsoup {

    /*Jsoup is for cleaning up html, you can .remove() nodes and elements all.  
	 However,it doesn’t have a bulk remove for attributes.

     Thus, added and option to Jsoup tool box, for keeping/removing the specific attributes in elements e.g
			--KeepID,
			--DeleteInLineStyle,
			--DeleteAll;
	*/

	public static enum Options {
		KeepID,
        DeleteInLineStyle,
		DeleteAll;
	}

	public static void removeAttrs(Element element, Options options) {
		
		for (Attribute attribute : element.attributes()) {
            
			if (options == Options.DeleteInLineStyle && attribute.getKey().equals("style")) {
            
				element.removeAttr(attribute.getKey());
            }
		}
	}
}