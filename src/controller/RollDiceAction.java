package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import monopoly.*;
import monopoly.tiles.ITile;
import monopoly.tiles.Property;
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
	
	private void handleChance() {
		System.out.println("Landed on chance");
	}
	
	private void handleCommChest() {
		System.out.println("Landed on community chest");
	}
	
	/**
	 * Updates the control panel with the new amounts of money
	 */
	private void updateControlPanel()
	{
		Player[] players = theGame.getPlayers(); // get all the players
		double[] money = new double[4]; 
		for(int x = 0; x < 4; x++)
		{
			money[x] = players[x].getMoney(); // get their money
		}
		theMainFrame.getControl().setMoneyVals(money); // pass to the function
	}
	
	/**
	 * updates the property panel to the current position on 
	 * the board
	 */
	private void updatePropertyPanel()
	{
		Player cur = theGame.getCurrentPlayer(); // get the current player
		int pos = cur.getPosition(); // get their position
		ITile prop = theBoardModel.getTileAt(pos); // get the tile
		theMainFrame.getProperties().updateProperty(prop); // update panel
	}
	//
	// ActionListener Implementation
	//
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// run the next move on the model
		String action = theMainFrame.getControl().getBtnRollDice().getText();
		if(action.equals("Roll Dice!"))
		{
			theGame.nextMove();
			
			// update the dice
			Dice d = theGame.getDice();
			theBoardView.rollDice(d);
			
			// update the current player position
			Player currentPlayer = theGame.getCurrentPlayer();
			theBoardView.moveCharacter(theGame.getCurrentPlayerIndex(), currentPlayer.getPosition());
			
			updatePropertyPanel();
			
			// run the right function depending on the state of the model
			MonopolyModelState state = theGame.getModelState();
			switch(state) {
			case BUY_REQUEST:
				theMainFrame.getControl().getBtnRollDice().setText("Pass");
				break;
				
			case CHANCE:
				// handle the situation graphically
				handleChance();
				
				// update the model
				theGame.handleChancePull();
				break;
				
			case COMMUNITY_CHEST:
				// handle the situation graphically
				handleCommChest();
				
				// update the model
				theGame.handleCommChestPull();
				break;
			
			// PLAYING state
			default:
				break;
			}
		}
		else
		{
			theGame.handleBuyRequest(false);
			theMainFrame.getControl().getBtnRollDice().setText("Roll Dice!");
		}
		updateControlPanel();
	}
}
