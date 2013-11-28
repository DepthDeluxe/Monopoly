package monopoly;

public class Property implements ITile {
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
		this.mortgagedValue = 0.5 * price;
		
		this.owner = null;
		
		this.isMortgaged = false;
	}
	
	//
	// Main Functions
	//
	
	public void setOwner(Player newOwner) {
		this.owner = newOwner;
	}
	
	public boolean chargeRent(Player player) {
		// can't charge rent if property is unowned or mortaged
		if (owner == null || isMortgaged == true) {
			return false;
		}
		
		// transfer the rent from the player that landed on the
		// tile to the owner of the tile
		player.takeMoney(getRent());
		owner.giveMoney(getRent());
		
		return true;
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
		return true;
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
	
	public double getMortgagedValue() {
		return mortgagedValue;
	}
	
	//
	// ITile Implementation
	//
	
	public void landOn(Player p) {
		if (owner == null) {
			// need to figure out how this segment works...
		}
		else {
			// player has to transfer money
			p.takeMoney(getRent());
			owner.giveMoney(getRent());
		}
	}
}
