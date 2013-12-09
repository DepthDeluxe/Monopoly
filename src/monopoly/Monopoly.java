package monopoly;

import java.util.LinkedList;

import monopoly.tiles.*;
import monopoly.xml.TileLoader;
import monopoly.xml.CardLoader;

class NoPlayersException extends RuntimeException {
	private static final long serialVersionUID = -4783179608622400629L;

	public NoPlayersException() { super("There are not enough players added to the game!"); }	
}

public class Monopoly {
	//
	// Member Variables
	//
	
	private LinkedList<Player> players;
	private int curPlayerIndex;
	private Player currentPlayer;
	
	private Dice dice;
	private Board board;
	
	private MonopolyModelState modelState;
	
	//
	// Constructors
	//
	
	public Monopoly(String boardFilename, String chanceFilename, String commChestFilename) {
		// init player stuff
		players = new LinkedList<Player>();
		curPlayerIndex = 0;
		
		// load the dice
		dice = new Dice();
		
		// load the board components from files
		ITile[] boardTiles = TileLoader.loadFromXML(boardFilename);
		
		// only load the chance deck if the file was specified
		CardDeck chanceDeck = null;
		if (chanceFilename != null) {
			chanceDeck = new CardDeck(CardLoader.loadFromFile(chanceFilename));
		}
		
		// only load the com chance deck if the file was specified
		CardDeck commChestDeck = null;
		if (commChestFilename != null) {
			commChestDeck = new CardDeck(CardLoader.loadFromFile(commChestFilename));
		}
		
		// load the board
		board = new Board(boardTiles, chanceDeck, commChestDeck);
		
		modelState = MonopolyModelState.PLAYING;
	}
	
	//
	// Main Functions
	//
	
	public boolean nextMove() {
		if (players.size() < 2) {
			throw new NoPlayersException();
		}
		
		// if the model is in PLAYER_MOVED state that means the player already
		// rolled the diced and moved before but was moved by a chance card.
		// Don't do anything if the model is in a different state.
		switch (modelState) {
		case PLAYING:
			// roll the dice and cycle the player
			dice.roll();
			
			// get the current player and calculate distance
			int distance = dice.getFirstValue() + dice.getSecondValue();
			
			// no longer in jail if they rolled doubles
			if (dice.getFirstValue() == dice.getSecondValue()) {
				currentPlayer.setInJail(false);
			}
			
			// player will not move if they are in jail
			currentPlayer.move(distance);
			
		// this gets run when game is in PLAYING or PLAYER_MOVED state
		case PLAYER_MOVED:
			
			// run the landOn of the current tile
			ITile tile = board.getTileAt(currentPlayer.getPosition());
			modelState = tile.landOn(currentPlayer);
			
			break;
			
		// in other cases, don't do anything
		default:
		}
		

		// return true if there isn't any other action required by
		// the controller
		return (modelState == MonopolyModelState.PLAYING);
	}
	
	public boolean handleBuyRequest(boolean buying) {
		if (modelState != MonopolyModelState.BUY_REQUEST) {
			return false;
		}
		
		boolean success = true;
		if (buying) {
			Property propertyToBuy = board.getPropertyAt(currentPlayer.getPosition());
			success = currentPlayer.buyProperty(propertyToBuy);
		}
		
		// reset the model state and increment the player
		modelState = MonopolyModelState.PLAYING;
		incrementPlayer();
		
		return success;
	}
	
	public boolean handleCardPull(TileType cardType) {
		// get the pulled card
		Card pulledCard;
		if (cardType == TileType.CHANCE) {
			pulledCard = board.getChanceDeck().getTopCard();
		}
		else if (cardType == TileType.COMMUNITY_CHEST) {
			pulledCard = board.getCommunityChestDeck().getTopCard();
		}
		else {
			throw new RuntimeException("Error: Invalid card type!");
		}
		
		// run the script on the card
		modelState = pulledCard.runScript(this);
		
		board.getChanceDeck().nextCard(); // increment the card deck
		
		// only increment player if the model state is PLAYING
		if (modelState == MonopolyModelState.PLAYING) {
			incrementPlayer();		
			return true;
		}
		
		// return false if the game is not done
		return false;
	}
	
	public boolean handleIdleState() {
		// make sure the modelState is playing
		modelState = MonopolyModelState.PLAYING;
		
		// and increment the player
		incrementPlayer();
		
		return true;
	}
	
	public void addPlayer(Player p) {
		// add the player to the list of current players
		players.add(p);
		
		// if this is the first player, they are the current player
		if (players.size() == 1) {
			currentPlayer = p;
		}
	}
	
	//
	// Getters
	//
	
	public Player[] getPlayers() {
		return players.toArray(new Player[0]);
	}
	
	public int getCurrentPlayerIndex() {
		return curPlayerIndex;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Dice getDice() {
		return dice;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public MonopolyModelState getModelState() {
		return modelState;
	}
	
	//
	// Private Functions
	//
	
	private void incrementPlayer() {		
		// increment the current player
		curPlayerIndex++;
		if (curPlayerIndex == players.size()) {
			curPlayerIndex = 0;
		}
		
		// assign the value of the current player
		currentPlayer = players.get(curPlayerIndex);
	}
}
