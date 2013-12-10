package monopoly;

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
	
	public AIPlayer(float propBuyRatio, String name, double startMoney, int boardSize, Monopoly game) {
		// init the Player parent class
		super(name, startMoney, boardSize);
		
		this.theGame = game;
		this.propBuyRatio = propBuyRatio;
	}
	
	//
	// Main Functions
	//
	
	/**
	 * Function that mimics what the the controller falls
	 */
	public void autoMove()
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
			// don't know how to get him to roll again.....
			break;
			
		case PLAYER_BANKRUPT:
			handlePlayerBankrupt();
			
		default: // should never
			throw new RuntimeException("Invalid MonopolyModelState Received! (" + state + ")"); 
		}
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
		int moneyRaised = 0;
		return false;
	}
}
