package monopoly;

public class Property {
	//
	// Member Variables
	//
	
	private String name;
	
	private double price;
	private Player owner;
	
	private boolean isMortgaged;
	private double mortgagedValue;
	
	//
	// Constructors
	//
	
	public Property(String name, double price) {
		this.name = name;
		this.price = price;
		
		this.owner = null;
		
		this.isMortgaged = false;
	}
	
	//
	// Main Functions
	//
	
	public void setOwner(Player newOwner) {
		this.owner = newOwner;
	}
	
	public boolean mortgage() {
		// can't mortgage an unowned house,
		// can't mortgage an already mortgaged house
		if (owner == null || isMortgaged == true) {
			return false;
		}
		
		// give the owner the mortgaged value
		owner.giveMoney(mortgagedValue);
		
		isMortgaged = true;
	}
	
	public boolean unmortgage() {
		// can't unmortgage an unowned house,
		// can't unmortgage an already unmortgaged house
		if (owner == null || isMortgaged == false) {
			return false;
		}
		
		// try to take the mortgaged value + 10%
		boolean success = owner.takeMoney(1.1 * mortgagedValue);
		
		if (success) {
			isMortgaged = false;
		}
		
		// returns true if the house was unmortgaged
		return !isMortgaged;
	}
	
	//
	// Getters
	//
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getRent() {
		// rent is nothing when the house is mortgaged
		if (isMortgaged) {
			return 0;
		}
		else {
			return price / 10;
		}
	}
	
	public boolean isOwned() {
		return (owner != null);
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public boolean isMortgaged() {
		return isMortgaged;
	}
}
