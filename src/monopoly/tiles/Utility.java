package monopoly.tiles;

import monopoly.Player;
import monopoly.Dice;

public class Utility extends Property {	
	//
	// Static Variables
	//
	
	private static final double UTILITY_VALUE = 200.0;

	private static final TileType TILE_TYPE = TileType.UTILITY;
	
	//
	// Constructors
	//
	
	public Utility(String name) {
		super(name, UTILITY_VALUE);
	}
	
	//
	// Property Overrides
	//
	
	@Override
	public double getRent() {
		Dice dice = new Dice();
		
		double diceSum = dice.getSum();
		if(getOwner() == null)
		{
			return 4.0 * diceSum;
		}
		// if player owns both utilities, the rent is 10 times the dice roll,
		// if only one, 4 times the dice roll
		if (getOwner().getNumUtilities() == 2) {
			return 10.0 * diceSum;
		}
		else {
			return 4.0 * diceSum;
		}
	}
	
	@Override
	public TileType getTileType() {
		return TILE_TYPE;
	}
}
