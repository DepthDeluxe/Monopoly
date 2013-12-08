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

import monopoly.Card;

public class CardLoader {
	public static Card[] loadFromFile(String xmlFilename) {
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
		
		Element cardsElement = (Element)document.getFirstChild();
		NodeList cardNodes = cardsElement.getChildNodes();
		LinkedList<Card> cards = new LinkedList<Card>();
		
		for (int n = 0; n < cardNodes.getLength(); n++) {
			Node currentNode = cardNodes.item(n);
			
			// typecast only if it is an element
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				// load the card
				Card c = XMLToCard((Element)currentNode);
				
				// and add to the list
				cards.add(c);
			}
		}
		
		// return an array of the cards
		Card[] cardArray = new Card[cards.size()];
		return cards.toArray(cardArray);
	}
	
	public static Card XMLToCard(Element cardElement) {		
		String description = TileLoader.getChildValue("Description", cardElement);
		String script = TileLoader.getChildValue("Script", cardElement);
		
		return new Card(description, script);
	}
}
