package monopoly;

import java.util.ArrayList;

public class Monopoly {
	//
	// Member Variables
	//
	
	private ArrayList<Player> players;
	private int curPlayerIndex;
	private Player currentPlayer;
	
	private Board board;
	
	private MonopolyModelState modelState;
	
	//
	// Constructors
	//
	
	public Monopoly() {
		// init player stuff
		players = new ArrayList<Player>();
		curPlayerIndex = 0;
		
		board = new Board();
		
		// game starts off playing
		modelState = MonopolyModelState.PLAYING;
	}
	
	public void nextMove() {
		if (modelState != MonopolyModelState.PLAYING) {
			return;
		}
		
		// roll the dice
		Dice.roll();
		int distance = Dice.getFirstValue() + Dice.getSecondValue();
		
		// set the new index
		currentPlayer = players.get(curPlayerIndex);
		currentPlayer.move(distance);
		
		// run the landOn
		ITile tile = board.getPropertyAt(currentPlayer.getPosition());
		modelState = tile.landOn(currentPlayer);
		
		// increment the current player
		curPlayerIndex++;
		if (curPlayerIndex == players.size()) {
			curPlayerIndex = 0;
		}
	}
	
	public boolean answerBuyRequest(boolean buying) {
		if (modelState != MonopolyModelState.BUY_REQUEST) {
			return false;
		}
		
		boolean success = true;
		if (buying) {
			Property propertyToBuy = board.getPropertyAt(currentPlayer.getPosition());
			success = currentPlayer.buyProperty(propertyToBuy);
		}
		
		return success;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public MonopolyModelState getModelState() {
		return modelState;
	}
}
