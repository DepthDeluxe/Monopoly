package monopoly;

import java.util.LinkedList;

public class Player {
	//
	// Member Variables
	//
	
	private int position;
	private double money;
	
	private LinkedList<Property> properties;
	
	//
	// Constructors
	//
	
	public Player(int startMoney) {
		this.money = startMoney;
		position = 0;
		
		properties = new LinkedList<Property>();
	}
	
	//
	// Main Functions
	//
	
	public void move(int numProperties) {
		this.position += numProperties;
		
		// go back to the beginning
		if (this.position > Board.SIZE) {
			this.position -= Board.SIZE;
		}
	}
	
	public void giveMoney(double amount) {
		this.money += amount;
	}
	
	public boolean takeMoney(double amount) {
		// don't allow player to have negative money
		if (money < amount) {
			return false;
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
	
	//
	// Getters
	//
	
	public double getCurrentMoney() {
		return money;
	}
	
	public boolean owns(Property p) {
		return properties.contains(p);
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
}
