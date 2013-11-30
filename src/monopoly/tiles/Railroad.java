package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

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
		Player owner = getOwner();
		int rrCount = 0;
		
		// price is $25 times 2 raised to the number of railroads owned
		return 25.0 * Math.pow(2, (owner.getNumRailroads()-1));
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
