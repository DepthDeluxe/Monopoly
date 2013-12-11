package monopoly.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLIO {
	public static String getChildValue(String nodeName, Element e) {		
		return e.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();
	}
	
	public static Element classMemberToElement(String nodeName, String value, Document doc) {
		// create the element
		Element e = doc.createElement(nodeName);
		
		// set the internal text content
		e.setTextContent(value);
		
		return e;
	}
	
	public static Document loadDocumentFromFile(String filename) {
		// open the file
		File xmlFile = new File(filename);
		
		// init the builder factory
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		// init the dBuilder
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e) {
			// throw stack trace because we have to catch the exception -_-
			e.printStackTrace();
			return null;
		}
		
		// parse the document with the builder, if there are any errors,
		// print the stack trace and return null
		Document document;
		try {
			document = dBuilder.parse(xmlFile);
		}
		catch (SAXException e) {
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return document;
	}
}
