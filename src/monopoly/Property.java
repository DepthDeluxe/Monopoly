package monopoly;

public class Property {
	//
	// Member Variables
	//
	
	private String name;
	
	private double price;
	private Player owner;
	
	// TODO: add support for mortgages
	
	//
	// Constructors
	//
	
	public Property(String name, double price) {
		this.name = name;
		this.price = price;
		
		this.owner = null;
	}
	
	//
	// Main Functions
	//
	
	public void setOwner(Player newOwner) {
		this.owner = newOwner;
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
		return price / 10;
	}
	
	public boolean isOwned() {
		return (owner != null);
	}
	
	public Player getOwner() {
		return owner;
	}
}
