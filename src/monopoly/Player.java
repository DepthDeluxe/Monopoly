package monopoly;

import java.util.LinkedList;

import monopoly.tiles.Property;
import monopoly.tiles.TileType;
import monopoly.xml.ISerializable;
import monopoly.xml.XMLIO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Player implements ISerializable {
	//
	// Member Variables
	//
	
	private String name;
	private double money;

	// position management
	private int position;
	private int numTimesPassedGo;
	private boolean inJail;

	private int boardSize;
	private LinkedList<Property> properties;
	private int mortgagedProperties;
	
	// for debt management
	private Player creditor;
	private double amountOwed;
	private boolean isBankrupt;
	
	//
	// Constructors
	//
	
	public Player(String name, double startMoney, int boardSize) {
		this.name = name;
		this.money = startMoney;
		this.boardSize = boardSize;
		
		position = 0;
		numTimesPassedGo = 0;
		mortgagedProperties = 0;
		inJail = false;
		
		properties = new LinkedList<Property>();
	}
	
	//
	// Main Functions
	//
	
	public void move(int numProperties) {		
		// don't move if in jail
		if (inJail) {
			return;
		}
		
		this.position += numProperties;
		
		// a full loop completed, increment numTimesPassedGo
		if (this.position > boardSize) {
			this.position -= boardSize;
			
			numTimesPassedGo++;
		}
	}
	
	public void moveTo(int destination) {
		int oldPos = this.position;
		this.position = destination;
		
		// increment the times player passed GO if his new position
		if (oldPos > this.position) {
			numTimesPassedGo++;
		}
	}
	
	public void giveMoney(double amount) {
		this.money += amount;
	}
	
	public boolean takeMoney(double amount) throws PlayerBankruptException {
		// if player out of money, throw exception returning
		// the amount of remaining money the player has
		if (money < amount) {
			double playerMoney = money;
			money = 0;
			
			// set the amount owed
			amountOwed = amount - playerMoney;
			
			// pass out the amount remaining
			throw new PlayerBankruptException(playerMoney);
		}
		
		// subtract the money and return true
		this.money -= amount;
		return true;
	}

	public boolean buyProperty(Property p) {
		// can't buy if not enough money
		double price = p.getPrice();
		if (money < price) {
			return false;
		}
		// can't buy if property already owned
		if (p.getOwner() != null) {
			return false;
		}
		
		// subtract to price and add to owner list
		money -= price;
		properties.add(p);
		p.setOwner(this);		// set the new owner of property
		return true;
	}
	
	public void goBankrupt() {
		// iterate through every owned property and set owner to null
		for (Property p : properties) {
			// on setting new owner, the property state will be reset
			p.setOwner(null);
		}
		
		// clear the list of properties
		properties.clear();
		
		// set isBankrupt to true
		isBankrupt = true;
	}
	
	public boolean canPayOffDebt()
	{
		double moneyRaised = 0;
		double moneyOwed = this.getAmountOwed(); // the amount they've got to pay
		// increment through their properties to see if they can mortgaged enough such that they can
		// pay back debt
		for(int x = 0; x < properties.size(); x++)
		{
			Property prop = properties.get(x);
			if(!prop.isMortgaged()) // if not already mortgaged
			{
				moneyRaised += prop.getMortgagedValue();
			}
		}
		
		if(moneyRaised < moneyOwed) { return false; } // if they can't pay it back just return false
		
		return true;
	}
	
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}
	
	//
	// Getters
	//
	
	public String getName() {
		return name;
	}
	
	public double getMoney() {
		return money;
	}
	
	public int getPosition() {
		return position;
	}
	
	public int getNumTimesPassedGo() {
		return numTimesPassedGo;
	}
	
	public boolean owns(Property p) {
		return properties.contains(p);
	}
	
	public int getNumPropertiesInGroup(int group) {
		// start off at zero
		int numInGroup = 0;
		
		// go through all owned properties, incrementing if that
		// owned property is a member of the specified group
		for (Property p: properties) {
			if (group == p.getGroup()) {
				numInGroup++;
			}
		}
		
		return numInGroup;
	}
	
	public int getNumRailroads() {
		// count how many properties are railroads
		int numRailroads = 0;
		for (Property p : properties) {
			if (p.getTileType() == TileType.RAILROAD) {
				numRailroads++;
			}
		}
		
		return numRailroads;
	}
	
	public int getNumUtilities() {
		// count how many properties are utilities
		int numUtilities = 0;
		for (Property p : properties) {
			if (p.getTileType() == TileType.UTILITY) {
				numUtilities++;
			}
		}
		
		return numUtilities;
	}
	
	public boolean isInJail() {
		return inJail;
	}
	
	public LinkedList<Property> getProperties()
	{
		return this.properties;
	}
	
	public int getMortgagedProperties()
	{
		return this.mortgagedProperties;
	}
	
	public boolean payOffDebt() {		
		// don't allow to pay off debt until they have the right money
		if (amountOwed > money) {
			return false;
		}
		
		// pay off the money
		money -= amountOwed;
		
		// give to the creditor if there is one, player can owe money
		// to the house
		if (creditor != null) {			
			creditor.giveMoney(amountOwed);
		}
		
		// zero out amount owed an erase the creditor
		amountOwed = 0;
		creditor = null;
		
		return true;
	}
	
	public boolean isBankrupt() {
		return isBankrupt;
	}
	
	public boolean isAI() {
		return false;
	}
	
	//
	// Setters
	//
	
	public void changeMortgagedProperties(int x)
	{
		this.mortgagedProperties += x;
	}
	
	/**
	 * Sets the player that this player owes money to.
	 * @param p	The player that this player owes money to
	 */
	public void setCreditor(Player p) {
		// don't do anything if this player doesn't owe anything
		if (amountOwed == 0) {
			return;
		}
		
		this.creditor = p;
	}

	/**
	 * @return the amountOwed
	 */
	public double getAmountOwed() {
		return amountOwed;
	}
	
	//
	// ISerializable Implementation
	//
	
	@Override
	public Element serialize(Document doc) {
		// the root element
		Element playerElement = doc.createElement("Player");
		
		// name
		Element nameElement = XMLIO.classMemberToElement("Name", name, doc);
		playerElement.appendChild(nameElement);
		
		// money
		Element moneyElement = XMLIO.classMemberToElement("Money", Double.toString(money), doc);
		playerElement.appendChild(moneyElement);
		
		// position
		Element positionElement = XMLIO.classMemberToElement(
				"Position", Integer.toString(position), doc);
		playerElement.appendChild(positionElement);
		
		// inJail
		Element inJailElement = XMLIO.classMemberToElement(
				"InJail", Boolean.toString(inJail), doc);
		playerElement.appendChild(inJailElement);
		
		// Properties, save the name for each property owned
		Element propertiesElement = doc.createElement("Properties");
		for (Property p : properties) {
			// create the property element
			Element propElement = doc.createElement("Property");
			
			// set name of property
			Element propNameElement = XMLIO.classMemberToElement("Name", p.getName(), doc);
			propElement.appendChild(propNameElement);
			
			// set isMortgaged
			Element isMortgagedElement = XMLIO.classMemberToElement(
					"IsMortgaged", Boolean.toString(p.isMortgaged()), doc);
			propElement.appendChild(isMortgagedElement);
			
			// add the property element to the properties element
			propertiesElement.appendChild(propElement);
		}
		playerElement.appendChild(propertiesElement);
		
		// mortgaged properties count
		Element mortgagedPropertiesElement = XMLIO.classMemberToElement(
				"MortagedProperties", Integer.toString(mortgagedProperties), doc);
		playerElement.appendChild(mortgagedPropertiesElement);
		
		// only save creditor if there is one
		if (creditor != null) {
			Element creditorElement = XMLIO.classMemberToElement("Creditor", creditor.getName(), doc);
			playerElement.appendChild(creditorElement);
		}
		
		// amount owed
		Element amountOwedElement = XMLIO.classMemberToElement(
				"AmountOwed", Double.toString(amountOwed), doc);
		playerElement.appendChild(amountOwedElement);
		
		// is bankrupt
		Element isBankruptElement = XMLIO.classMemberToElement(
				"IsBankrupt", Boolean.toString(isBankrupt), doc);
		playerElement.appendChild(isBankruptElement);
		
		return playerElement;
	}
	
	@Override
	public void deSerialize(Element rootNode, Object outsideParam) {
		// the outside param is the board
		Board theBoard = (Board)outsideParam;
		
		// load all of the elements
		//
		
		String tempStr;
		
		// name
		name = XMLIO.getChildValue("Name", rootNode);
		
		// money
		tempStr = XMLIO.getChildValue("Money", rootNode);
		money = Double.parseDouble(tempStr);
		
		// position
		tempStr = XMLIO.getChildValue("Position", rootNode);
		position = Integer.parseInt(tempStr);
		
		// inJail
		tempStr = XMLIO.getChildValue("InJail", rootNode);
		inJail = Boolean.parseBoolean(tempStr);
		
		// properties
		Element propertiesElement = (Element)rootNode.getElementsByTagName("Properties").item(0);
		NodeList propertyNodes = propertiesElement.getElementsByTagName("Property");
		for (int n = 0; n < propertyNodes.getLength(); n++) {
			Element curPropElement = (Element)propertyNodes.item(n);
			
			// get the property name and see if it is mortgaged
			String propertyName = XMLIO.getChildValue("Name", curPropElement);
			String isMortgagedStr = XMLIO.getChildValue("IsMortgaged", curPropElement);
			boolean isMortgaged = Boolean.parseBoolean(isMortgagedStr);
			
			// get the property, updated it internally, and then add
			// to the list of owned properties
			Property property = theBoard.getPropertyByName(propertyName);
			property.xmlExternalUpdate(this, isMortgaged);
			
			properties.add(property);
		}
		
		// mortgaged properties count
		tempStr = XMLIO.getChildValue("MortgagedProperties", rootNode);
		mortgagedProperties = Integer.parseInt(tempStr);
		
		// amount owed
		tempStr = XMLIO.getChildValue("AmountOwed", rootNode);
		amountOwed = Double.parseDouble(tempStr);
		
		// isBankrupt
		tempStr = XMLIO.getChildValue("IsBankrupt", rootNode);
		isBankrupt = Boolean.parseBoolean(tempStr);
	}
}
