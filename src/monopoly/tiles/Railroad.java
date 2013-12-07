package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

public class Railroad extends Property {
	//
	// Member Variables
	//
	
	double rentBase;
	
	//
	// Static Variables
	//
	
	private static final TileType TILE_TYPE = TileType.RAILROAD;
	public static final int GROUP = -1;
	
	//
	// Constructors
	//
	
	public Railroad(String name, double price, double rentBase, double mortgageValue) {
		super(name, price, 0, mortgageValue, GROUP);
		
		this.rentBase = rentBase;
	}
	
	//
	// Property Overrides
	//
	
	@Override
	public double getRent() {
		// check to see if any other properties player owns is a railroad
		Player owner = getOwner();
		
		// if there is no owner..
		if(getOwner() == null) { return rentBase; }
		
		// price is rentBase times 2 raised to the number of railroads owned
		return (rentBase / 2) * Math.pow(2, (owner.getNumRailroads()-1));
	}
	
	@Override
	public TileType getTileType() {
		return TILE_TYPE;
	}
}
