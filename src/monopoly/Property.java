package monopoly;

public class Property {
	//
	// Member Variables
	//
	
	private double price;
	Player owner;
	
	//
	// Constructors
	//
	
	public Property(double price) {
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
	
	public Player getOwner() {
		return owner;
	}
}
