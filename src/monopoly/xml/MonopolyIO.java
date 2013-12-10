package monopoly.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import monopoly.Monopoly;

public class MonopolyIO {
	public static void saveGame(String filename, Monopoly theGame) {
		// init the document builders
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}
		
		// create the document
		Document doc = dBuilder.newDocument();
		
		// now save the game
		Element monopolyElement = theGame.serialize(doc);
		
		//
		// write the content to xml
		//
		
		// init the transformer
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return;
		}
		
		// parse the document into a DOMSource object
		DOMSource domSource = new DOMSource(doc);
		
		// open up the transformation stream
		StreamResult streamResult = new StreamResult(new File(filename));
		
		// try to transform and if it doesn't work, that sucks
		try {
			transformer.transform(domSource, streamResult);
		} catch (TransformerException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static Monopoly loadGame(String filename) {
		throw new RuntimeException("Loading not implemented!");
	}
}
