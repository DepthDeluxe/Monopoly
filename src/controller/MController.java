/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 5, 2013, 8:00:47 PM
 */

package controller;

import monopoly.*;
import monopoly.tiles.Property;
import monopoly.tiles.TileType;

import gui.MMainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

/**
 * @author ajrk001
 *
 */
public class MController 
{
	//
	// Member Variables
	//
	
	private MMainFrame theView;
	private Monopoly theGame;
	
	private int numHours;
	private int numPlayers;
	private String[] playerNames;
	
	//
	// Constructors
	//
	
	public MController(MMainFrame theView, Monopoly theGame, int hours, int players, String[] names)
	{
		this.theView = theView;
		this.theGame = theGame;
		this.numHours = hours;
		this.numPlayers = players;
		playerNames = names;
		for(int x = 0; x < numPlayers; x++)
		{
			this.theGame.addPlayer(new Player(playerNames[x], numHours, theGame.getBoard()));
		}
		setRollDiceFunction();
		setMortgageFunction();
		
		loadViewFromModel();
	}
	
	//
	// Main Functions
	//
	
	private void setRollDiceFunction() {
		RollDiceAction actionListener = new RollDiceAction(theView, theGame);
		
		theView.getControl().setRollDiceAction(actionListener);
	}
	
	private void setMortgageFunction() {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		
		theView.getControl().setMortgageAction(actionListener);
	}
	
	//
	// Private Functions
	//
	
	public void loadViewFromModel() {
		JTextArea[] props = theView.getTheBoard().getPropertyLabels(); // get all the property labels
		Board theBoard = theGame.getBoard(); // get the board itself
		int x = 0; // need two seperate incrementers to go trhough the props array
		for(int y = 0; y < theBoard.getNumTiles(); y++) // parse through the board and tiles 
		{
			Property prop = theBoard.getPropertyAt(y); // gets property or null if not property
			if(prop != null && prop.getTileType() == TileType.PROPERTY) // check if not null, and double check not a railroad or utility
			{
				props[x].setText(prop.getName()); // set text to name
				x++; // incrememnt props incrementer
			}
		}
	}
}
