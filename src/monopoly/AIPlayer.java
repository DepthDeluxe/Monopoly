package monopoly;

import monopoly.tiles.Property;

public class AIPlayer extends Player {
	//
	// Member Variables
	//
	
	private float propBuyRatio;
	
	//
	// Constructors
	//
	
	public AIPlayer(float propBuyRatio, String name, double startMoney, int boardSize) {
		// init the Player parent class
		super(name, startMoney, boardSize);
		
		this.propBuyRatio = propBuyRatio;
	}
	
	//
	// Main Functions
	//
	
	// TODO implement handlers that essentially do what the controller does but in the back-end instead
	
	/**
	 * Use AI to determine whether or not this player wants to buy the property
	 * @param p The property that the player has an opportunity to buy
	 */
	public void handleBuyRequest(Property p) {
		// calculate 
		double propToMoneyRatio = p.getPrice() / getMoney();
		
		// if the ratio is greater than propBuyRatio, don't buy because it is
		// too expensive!
		if (propToMoneyRatio < propBuyRatio) {
			buyProperty(p);
		}
	}
}
