package monopoly;

import java.util.LinkedList;

import monopoly.tiles.Property;
import monopoly.tiles.Railroad;
import monopoly.tiles.Utility;

public class Player {
	//
	// Member Variables
	//
	
	private String name;
	private double money;

	private int position;
	private boolean inJail;

	private int boardSize;
	private LinkedList<Property> properties;
	private int mortgagedProperties;
	
	// for debt management
	private Player creditor;
	private double amountOwed;
	
	//
	// Static Variables
	//
	
	public static final double GO_MONEY = 200;
	
	//
	// Constructors
	//
	
	public Player(String name, double startMoney, int boardSize) {
		this.name = name;
		this.money = startMoney;
		this.boardSize = boardSize;
		
		position = 0;
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
		
		// go back to the beginning, and collect money for
		// passing go
		if (this.position > boardSize) {
			this.position -= boardSize;
			
			giveMoney(GO_MONEY);
		}
	}
	
	public void moveTo(int destination) {
		int oldPos = this.position;
		this.position = destination;
		
		// give GO_MONEY if player passed go (i.e. destination smaller than start)
		if (oldPos > this.position) {
			giveMoney(GO_MONEY);
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
		// iterate through the properties, seeing which ones you
		// can typecast to Railroad.  If it can't, it will throw
		// a ClassCastException
		int numRailroads = 0;
		for (Property p : properties) {
			try {
				Railroad r = (Railroad)p;
				numRailroads++;
			}
			catch (ClassCastException e) {
				// don't do anything, this is not a railroad
			}
		}
		
		return numRailroads;
	}
	
	public int getNumUtilities() {
		// iterate through the properties, seeing which ones you
		// can typecast to Utility.  If it can't, it will throw
		// a ClassCastException
		int numUtilities = 0;
		for (Property p : properties) {
			try {
				Utility u = (Utility)p;
				numUtilities++;
			}
			catch (ClassCastException e) {
				// don't do anything, this is not a utility
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
}
