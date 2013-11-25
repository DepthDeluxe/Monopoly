package monopoly;

public class Railroad extends Property {
	//
	// Member Variables
	//
	
	//
	// Static Variables
	//
	
	private static double rrPrice = 200;
	
	//
	// Constructors
	//
	
	public Railroad(String name) {
		super(name, rrPrice);
	}
	
	//
	// Property Overrides
	//
	
	@Override
	public double getRent() {
		// check to see if any other properties player owns is a railroad
		Player owner;
		int rrCount = 0;
		
		throw new RuntimeException("Not Implemented");
		
		//return (25) * rrCount;
	}
	
	//
	// Static Functions
	//
	
	public double getRailroadPrice() {
		return rrPrice;
	}
	
	public void setRailroadPrice(double newPrice) {
		rrPrice = newPrice;
	}
}
