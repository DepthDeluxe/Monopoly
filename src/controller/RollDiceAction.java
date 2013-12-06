package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import monopoly.*;
import monopoly.tiles.ITile;
import gui.MMainFrame;

public class RollDiceAction implements ActionListener {
	
	//
	// Member Variables
	//
	
	MMainFrame	theMainFrame;
	Monopoly	theGame;
	
	Board		theBoardModel;
	
	//
	// Constructors
	//
	
	public RollDiceAction(MMainFrame theMainFrame, Monopoly theGame) {
		this.theMainFrame = theMainFrame;
		this.theGame = theGame;
		
		// save the board
		theBoardModel = theGame.getBoard();
	}
	
	private void handleBuyRequest(boolean isBuying) {
		
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
	}
}
