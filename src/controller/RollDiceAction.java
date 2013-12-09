package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import monopoly.*;
import monopoly.tiles.ITile;
import monopoly.tiles.Property;
import monopoly.tiles.TileType;
import gui.MCardDialog;
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
	private static String PASS_TEXT = "End Turn";
	
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
	
	//
	// State Handling Functions
	//
	
	/**
	 * Handles a player landing on chance in GUI
	 * @param c - The card drawn
	 */
	private void handleCardPull(CardDeck deckToPull, TileType deckType) {
		Card c = deckToPull.getTopCard();
		
		String deckName;
		if (deckType == TileType.CHANCE) {
			deckName = "Chance";
		}
		else if (deckType == TileType.COMMUNITY_CHEST) {
			deckName = "Community Chest";
		}
		else {
			throw new RuntimeException("Invalid TileType specified!");
		}
		
		// dump the card to the console
		System.out.println("Landed on " + deckName);
		System.out.println("Description: " + c.getDescription());
		System.out.println();
		
		// handle the card dialog
		MCardDialog cardDiag = new MCardDialog(c, theMainFrame, deckType);
		cardDiag.setLocationRelativeTo(theMainFrame);
		cardDiag.setVisible(true);
	}
	
	/**
	 * Handles a player going bankrupt.  Will open them mortgage
	 * panel and force him to mortgage all his properties.
	 */
	private void handlePlayerBankrupt() {
		throw new RuntimeException("handlePlayerBankrupt() is not implemented!");
	}
	
	//
	// View Updating Functions
	//
	
	/**
	 * Meta-function which calls all the other functions in this class
	 * that update the view.  This will be called multiple times in the
	 * main controller
	 */
	private void updateView() {
		// update the dice
		Dice d = theGame.getDice();
		theBoardView.rollDice(d);
		
		// update the view
		setPlayerPosViewFromModel();		
		updatePropertyPanel();
		
		// update the control panel
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
	
	/**
	 * Updates the current player's position from their
	 * location in the model
	 */
	private void setPlayerPosViewFromModel() {
		// get player information
		int curPlayerIndex = theGame.getCurrentPlayerIndex();
		Player currentPlayer = theGame.getCurrentPlayer();
		int currentPlayerPos = currentPlayer.getPosition();
		
		// set the player's position to where they are on the model
		theBoardView.moveCharacter(curPlayerIndex, currentPlayerPos);
	}
	
	//
	// Pass Button Change Handler
	//
	
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
	
	/**
	 * Handles the "Roll Dice" button which progresses the game.  This function
	 * will run until either a buy request has been posted or the game is in
	 * the PLAYING state.  This is to allow cards to move players around the
	 * board and buy properties they are moved to...
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// always run nextMove() no matter which state the game is in,
		// it won't do anything if there is nothing to do
		theGame.nextMove();
		
		// update the view after the model is updated
		updateView();
		
		// have to work on this
		if (theGame.getModelState() == MonopolyModelState.BUY_REQUEST){
			theMainFrame.getProperties().changeBuyState(true);
			ITile p = theGame.getBoard().getTileAt(theGame.getCurrentPlayer().getPosition());
			theMainFrame.getProperties().updateProperty(p);
		}
		
		// run the right function depending on the state of the model
		MonopolyModelState state = theGame.getModelState();
		switch(state) {
		case BUY_REQUEST:
			theMainFrame.getProperties().getBtnBuy().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1){
					if (e1.getSource() == theMainFrame.getProperties().getBtnBuy()){
						theGame.handleBuyRequest(true);
						//theMainFrame.getProperties().changeBuyState();
					}
				}
			});
			
			// turns this button into pass mode
			enablePassButton();
			
			// function needs to return to get out of the infinite
			// repeat loop because the game is not meant to continue
			// until the BUY_REQUEST has been answered...
			return;
			
		case CHANCE:
			// handle the situation graphically
			handleCardPull(theBoardModel.getChanceDeck(), TileType.CHANCE);
			
			// update the model
			theGame.handleCardPull(TileType.CHANCE);
			break;
			
		case COMMUNITY_CHEST:
			// handle the situation graphically
			handleCardPull(theBoardModel.getCommunityChestDeck(), TileType.COMMUNITY_CHEST);
			
			// update the model
			theGame.handleCardPull(TileType.COMMUNITY_CHEST);
			break;
			
		case PLAYING:
			// handle a normal playing state
			theGame.handleIdleState();
			break;
			
		case PLAYER_MOVED:
			// run this actionEvent again!
			actionPerformed(e);
			
			break;
			
		case PLAYER_BANKRUPT:
			handlePlayerBankrupt();
			
			break;
			
		// this should never happen
		default:
			throw new RuntimeException("Invalid MonopolyModelState Received! (" + state + ")");
		}
		
		// update the view after the model has changed
		updateView();
	}
}
