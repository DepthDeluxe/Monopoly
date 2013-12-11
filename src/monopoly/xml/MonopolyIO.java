package monopoly.xml;

import java.io.File;
import java.io.IOException;

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
import org.xml.sax.SAXException;

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
		doc.appendChild(monopolyElement);
		
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
	
	public static Monopoly loadGame(String filename, Monopoly theGame) {
		// if game null, this doesn't work
		if (theGame == null) {
			throw new RuntimeException("You need to give loadGame() a reference to a Monopoly object!");
		}
		
		// deSerialize the game from the document
		
		// load the document
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
		
		Element rootElement = (Element)document.getFirstChild();
		
		// now deserialize
		theGame.deSerialize(rootElement, null);
		
		// return a reference to the game
		return theGame;
	}
}
