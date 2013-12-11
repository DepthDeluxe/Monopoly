package monopoly;

import java.util.LinkedList;

import monopoly.tiles.Property;
import monopoly.tiles.TileType;

public class AIPlayer extends Player {
	//
	// Member Variables
	//
	
	private float propBuyRatio;
	private Monopoly theGame;
	
	//
	// Constructors
	//
	
	public AIPlayer(float difficulty, String name, double startMoney, Monopoly game) {
		// init the Player parent class
		super(name, startMoney, game.getBoard().getNumTiles());
		
		this.theGame = game;
		this.propBuyRatio = difficulty;
	}
	
	//
	// Main Functions
	//
	
	/**
	 * Function that mimics what the the controller does
	 */
	public boolean autoMove()
	{
		MonopolyModelState state = theGame.getModelState(); // get the state of the board
		
		switch(state) // handle each of the states
		{
		case BUY_REQUEST: // if it landed on a buyable tile
			boolean buy = handleBuyRequest(theGame.getBoard().getPropertyAt(this.getPosition())); // see if player should buy based on logic
			if(buy) // if yes, buy  
			{ 
				theGame.handleBuyRequest(true); // buy the game 
			}
			break;
			
		case CHANCE: // if it landed on a chance card
			theGame.handleCardPull(TileType.CHANCE);
			break;
			
		case COMMUNITY_CHEST: // if it landed on commchest
			theGame.handleCardPull(TileType.COMMUNITY_CHEST);
			break;
			
		case PLAYING: // if it is not a special tile, just increment player
			theGame.handleIdleState();
			break;
			
		case PLAYER_MOVED:
			return true;
			
		case PLAYER_BANKRUPT:
			boolean handled = handlePlayerBankrupt();
			boolean success = true;
			if(handled) // if they now have enough money
			{
				success = this.payOffDebt();
			}
			else if(!handled)
			{
				goBankrupt();
			}
			
			if(!success) // if it didn't work
			{
				goBankrupt();
			}
			
		default: // should never
			throw new RuntimeException("Invalid MonopolyModelState Received! (" + state + ")"); 
		}
		return false;
	}
	
	// TODO implement handlers that essentially do what the controller does but in the back-end instead
	
	/**
	 * Use AI to determine whether or not this player wants to buy the property
	 * @param p The property that the player has an opportunity to buy
	 * @return boolean that indicates if it wants to buy the property or not
	 */
	private boolean handleBuyRequest(Property p) {
		// calculate 
		double propToMoneyRatio = p.getPrice() / getMoney();
		
		// if the ratio is greater than propBuyRatio, don't buy because it is
		// too expensive!
		if (propToMoneyRatio < propBuyRatio) {
			return true;
		}
		return false;
	}
	
	private boolean handlePlayerBankrupt()
	{ 
		if(!this.canPayOffDebt()) { return false; }
		
		double moneyRaised = 0; // we can mortgage the right amount of properties
		double moneyOwed = super.getAmountOwed();
		LinkedList<Property> playProps = this.getProperties();
		
		int x = 0; // an incrementer to go through the properties and mortgage them
		
		while(moneyRaised < moneyOwed)
		{
			Property prop = playProps.get(x);
			prop.mortgage(); // even if its already mortgaged, fn will just return false and not do anything
			x++;
		}
		
		return true;
	}
	
	//
	// Player Overrides
	//
	
	@Override
	public boolean isAI() {
		return true;
	}
}
