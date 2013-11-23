package monopoly;

public class Property {
	//
	// Member Variables
	//
	
	private String name;
	
	private double price;
	private Player owner;
	
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
	
	//
	// Getters
	//
	
	public double getPrice() {
		return price;
	}
	
	public boolean isOwned() {
		return (owner != null);
	}
	
	public Player getOwner() {
		return owner;
	}
}
