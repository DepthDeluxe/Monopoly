package monopoly.xml;

import monopoly.tiles.*;

//XML DOM Classes
import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import monopoly.MonopolyModelState;
import monopoly.Player;

class InvalidXMLException extends RuntimeException {
	public InvalidXMLException() { super("The XML is an invalid format!"); }
}

public class TileLoader {
	//
	// Main Functions
	//
	
	public static ITile[] loadFromXML(String xmlFilename) {
		// load the xml document
		Document document = XMLIO.loadDocumentFromFile(xmlFilename);
		
		// get the property nodes and init the property arrays
		Element propertiesElement = (Element)document.getFirstChild();
		NodeList propertyNodes = propertiesElement.getChildNodes();
		LinkedList<ITile> tileList = new LinkedList<ITile>();
		
		// iterate through every property in the document, converting
		for (int n = 0; n < propertyNodes.getLength(); n++) {
			Node currentNode = propertyNodes.item(n);
			
			// typecast as an element only if it is an element
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				ITile t = XMLToTile((Element)currentNode);
				tileList.add(t);
			}
			
			// otherwise, skip over this one
		}
		
		// now convert to an array and return it
		ITile[] outTiles = tileList.toArray(new ITile[0]);
		return outTiles;
	}
	
	public static ITile XMLToTile(Element e) {
		
		// see what element type this is
		String elementType = e.getNodeName();
		
		// get pointer to an ITile
		ITile outTile = null;
        if (elementType.equals("Go")) {
            String collectAmountStr = XMLIO.getChildValue("CollectAmount", e);
            double collectAmount = Double.parseDouble(collectAmountStr);

            outTile = new GoTile(collectAmount);


        } else if (elementType.equals("CardTile")) {
            String type = XMLIO.getChildValue("Type", e);
            if (type.equals("Chance")) {
                outTile = new CardTile(MonopolyModelState.CHANCE);
            } else if (type.equals("Community Chest")) {
                outTile = new CardTile(MonopolyModelState.COMMUNITY_CHEST);
            } else {
                throw new InvalidXMLException();
            }


        } else if (elementType.equals("FreeParking")) {
            String startAmountStr = XMLIO.getChildValue("StartAmount", e);
            double startAmount = Double.parseDouble(startAmountStr);

            outTile = new FreeParking(startAmount);

        } else if (elementType.equals("GoToJailTile")) {// get the destination board location
            String destStr = XMLIO.getChildValue("Destination", e);
            int destination = Integer.parseInt(destStr);

            outTile = new GoToJailTile(destination);

        } else if (elementType.equals("Property")) {// get the name
            String name = XMLIO.getChildValue("Name", e);

            // get the value
            String strValue = XMLIO.getChildValue("Value", e);
            double value = Double.parseDouble(strValue);

            // get the rent
            String strRent = XMLIO.getChildValue("Rent", e);
            double rent = Double.parseDouble(strRent);

            // get mortgage value
            String strMortgage = XMLIO.getChildValue("Mortgage", e);
            double mortgageValue = Double.parseDouble(strMortgage);

            // get the group number
            String strGroup = XMLIO.getChildValue("Group", e);
            int group = Integer.parseInt(strGroup);

            outTile = new Property(name, value, rent, mortgageValue, group);

        } else if (elementType.equals("Railroad")) {
            String strValue;
            String strMortgage;
            String name;
            double mortgageValue;
            double value;// get the name
            name = XMLIO.getChildValue("Name", e);

            // get the value
            strValue = XMLIO.getChildValue("Value", e);
            value = Double.parseDouble(strValue);

            // get the base rent for railroad
            String strRentBase = XMLIO.getChildValue("Rent", e);
            double rentBase = Double.parseDouble(strRentBase);

            // get mortgage value
            strMortgage = XMLIO.getChildValue("Mortgage", e);
            mortgageValue = Double.parseDouble(strMortgage);

            outTile = new Railroad(name, value, rentBase, mortgageValue);

        } else if (elementType.equals("Utility")) {
            String strValue;
            String strMortgage;
            String name;
            double mortgageValue;
            double value;// get the name
            name = XMLIO.getChildValue("Name", e);

            // get the value
            strValue = XMLIO.getChildValue("Value", e);
            value = Double.parseDouble(strValue);

            // get mortgage value
            strMortgage = XMLIO.getChildValue("Mortgage", e);
            mortgageValue = Double.parseDouble(strMortgage);

            outTile = new Utility(name, value, mortgageValue);

        } else if (elementType.equals("Tax")) {// get the rate
            String rateStr = XMLIO.getChildValue("Rate", e);
            double rate = Double.parseDouble(rateStr);

            // get the minimum
            String taxMinStr = XMLIO.getChildValue("Minimum", e);
            double taxMin = Double.parseDouble(taxMinStr);

            // construct the tax tile
            outTile = new TaxTile(rate, taxMin);

        } else {// assign the proper tile type
            final TileType tileType = TileType.parseType(elementType);

            // basic board tile cases.  Implement a basic, do nothing,
            // return PLAYING landOn() function
            outTile = new ITile() {
                @Override
                public MonopolyModelState landOn(Player p) {
                    return MonopolyModelState.PLAYING;
                }

                @Override
                public TileType getTileType() {
                    return tileType;
                }
            };

        }
		
		return outTile;
	}
}
