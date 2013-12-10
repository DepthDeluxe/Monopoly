package monopoly.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

public class XMLIO {
	public static Element classMemberToElement(String nodeName, String value, Document doc) {
		// create the element
		Element e = doc.createElement(nodeName);
		
		// set the node value
		e.setNodeValue(nodeName);
		
		return e;
	}
}
