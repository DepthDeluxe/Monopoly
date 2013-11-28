package monopoly;

public class Utility extends Property {
	//
	// Member Variables
	//
	
	//
	// Static Variables
	//
	
	private static final double UTILITY_VALUE = 200;
	
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
		Dice.roll();
		
		double diceSum = Dice.getFirstValue() + Dice.getSecondValue();
		
		// if player owns both utilities, the rent is 10 times the dice roll,
		// if only one, 4 times the dice roll
		if (getOwner().getNumUtilities() == 2) {
			return 10.0 * diceSum;
		}
		else {
			return 4.0 * diceSum;
		}
	}
}
