package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import monopoly.*;
import monopoly.tiles.ITile;
import gui.MMainFrame;
import gui.MBoardPanel;

public class RollDiceAction implements ActionListener {
	
	//
	// Member Variables
	//
	
	MMainFrame	theMainFrame;
	Monopoly	theGame;
	
	MBoardPanel	theBoardView;
	Board		theBoardModel;
	
	//
	// Constructors
	//
	
	public RollDiceAction(MMainFrame theMainFrame, Monopoly theGame) {
		this.theMainFrame = theMainFrame;
		this.theGame = theGame;
		
		// save the board components
		theBoardView = theMainFrame.getTheBoard();
		theBoardModel = theGame.getBoard();
	}
	
	/** Handles a buy request graphically
	 * @return TRUE if the user wants to buy
	 */
	private boolean handleBuyRequest() {
		return false;
	}
	
	private void handleChance() {
		
	}
	
	private void handleCommChest() {
		
	}
	
	
	private void updatePropertyPanel()
	{
		Player cur = theGame.getCurrentPlayer();
		int pos = cur.getPosition();
		ITile prop = theBoardModel.getTileAt(pos);
		theMainFrame.getProperties().updateProperty(prop);
	}
	//
	// ActionListener Implementation
	//
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// run the next move on the model
		theGame.nextMove();
		
		// update the dice
		Dice d = theGame.getDice();
		theBoardView.rollDice(d);
		
		// update the current player position
		Player currentPlayer = theGame.getCurrentPlayer();
		theBoardView.moveCharacter(theGame.getCurrentPlayerIndex(), currentPlayer.getPosition());
		
		// run the right function depending on the state of the model
		switch(theGame.getModelState()) {
		case BUY_REQUEST:
			boolean userResponse = handleBuyRequest();
			
			theGame.handleBuyRequest(userResponse);
			break;
			
		case CHANCE:
			handleChance();
			break;
			
		case COMMUNITY_CHEST:
			handleCommChest();
			break;
		
		// PLAYING state
		default:
			break;
		}
	}
}
