package monopoly.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

public class XMLIO {
	public static String getChildValue(String nodeName, Element e) {		
		return e.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();
	}
	
	public static Element classMemberToElement(String nodeName, String value, Document doc) {
		// create the element
		Element e = doc.createElement(nodeName);
		
		// set the node value
		e.setNodeValue(nodeName);
		
		return e;
	}
}
