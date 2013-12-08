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
	
	private static String MAIN_TEXT = "Roll Dice!";
	private static String PASS_TEXT = "Pass";
	
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
	
	private void handleChance(Card c) {
		System.out.println("Landed on Chance");
		System.out.println("Description: " + c.getDescription());
		System.out.println();
	}
	
	private void handleCommChest(Card c) {
		System.out.println("Landed on Community Chest");
		System.out.println("Description: " + c.getDescription());
		System.out.println();
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
		// get the current player's position
		Player cur = theGame.getCurrentPlayer();
		int pos = cur.getPosition();
		
		// update the panel with tile that player is currently landed on
		ITile prop = theBoardModel.getTileAt(pos);
		theMainFrame.getProperties().updateProperty(prop);
	}
	
	private void setPlayerPosViewFromModel() {
		// get player information
		int curPlayerIndex = theGame.getCurrentPlayerIndex();
		Player currentPlayer = theGame.getCurrentPlayer();
		int currentPlayerPos = currentPlayer.getPosition();
		
		// set the player's position to where they are on the model
		theBoardView.moveCharacter(curPlayerIndex, currentPlayerPos);
	}
	
	/**
	 * Changes the "roll dice" button's ActionListener to handle
	 * this basic case (basically don't roll dice, change text
	 * back to roll dice, and then set back the original ActionListener	
	 */
	private void enablePassButton() {
		// get the original button
		JButton theButton = theMainFrame.getControl().getBtnRollDice();
		
		// save reference to this class again
		final ActionListener oldListener = this;
		
		// create the new action which manages the button when
		// a player has the opportuniy to buy a property
		ActionListener passListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// let the back-end know that the user doesn't want to buy the
				// property
				theGame.handleBuyRequest(false);
				
				// get the button
				JButton theButton = (JButton)e.getSource();
				
				// remove action listeners
				for (ActionListener al : theButton.getActionListeners()) {
					theButton.removeActionListener(al);
				}
				
				// add the original action listener back
				theButton.addActionListener(oldListener);

				// set the button text back to the start
				theButton.setText(MAIN_TEXT);
			}
		};
		
		// remove all action listeners
		for (ActionListener al : theButton.getActionListeners()) {
			theButton.removeActionListener(al);
		}

		// add the anonymous action listener
		theButton.addActionListener(passListener);
		
		// set the text
		theButton.setText(PASS_TEXT);
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
		
		// update the view
		setPlayerPosViewFromModel();		
		updatePropertyPanel();
		
		// run the right function depending on the state of the model
		MonopolyModelState state = theGame.getModelState();
		switch(state) {
		case BUY_REQUEST:
			// turns this button into pass mode
			enablePassButton();
			break;
			
		case CHANCE:
			// handle the situation graphically
			handleChance(theBoardModel.getChanceDeck().getTopCard());
			
			// update the model
			theGame.handleChancePull();
			break;
			
		case COMMUNITY_CHEST:
			// handle the situation graphically
			handleCommChest(theBoardModel.getCommunityChestDeck().getTopCard());
			
			// update the model
			theGame.handleCommChestPull();
			break;
		
		// PLAYING state
		default:
			break;
		}
		
		// update the view after the model has changed stuff
		setPlayerPosViewFromModel();		
		updatePropertyPanel();
	}
}
