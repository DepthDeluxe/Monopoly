package monopoly.xml;

//XML DOM Classes
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import monopoly.Property;

public class TileLoader {
	//
	// Main Functions
	//
	
	public static Property[] loadPropertiesFromFile(String xmlFilename) {
		// open the file
		File xmlFile = new File(xmlFilename);
		
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
		
		Node firstChildNode = document.getFirstChild();
		
		
		// get the property nodes and init the property arrays
		Element propertiesElement = (Element)document.getFirstChild();
		NodeList propertyNodes = propertiesElement.getChildNodes();
		LinkedList<Property> propertyList = new LinkedList<Property>();
		
		// iterate through every property in the document, converting
		for (int n = 0; n < propertyNodes.getLength(); n++) {
			Node currentNode = propertyNodes.item(n);
			
			// typecast as an element only if it is an element
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				Property p = XMLToProperty((Element)currentNode);
				propertyList.add(p);
			}
			
			// otherwise, skip over this one
		}
		
		// now convert to an array and return it
		Property[] properties = propertyList.toArray(new Property[propertyList.size()]);
		return properties;
	}
	
	public static Property XMLToProperty(Element propertyNode) {
		// get the desired information from the XML
		String name = getChildValue("Name", propertyNode);
		String valueStr = getChildValue("Value", propertyNode);
		
		// convert to numbers
		double value = Double.parseDouble(valueStr);
		
		// format the object and then return it
		Property property = new Property(name, value);
		return property;
	}
	
	public static String getChildValue(String nodeName, Element e) {
		return e.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();
	}
}
