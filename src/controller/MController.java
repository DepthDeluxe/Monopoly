/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 5, 2013, 8:00:47 PM
 */

package controller;

import monopoly.*;

import gui.MMainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	//
	// Constructors
	//
	
	public MController(MMainFrame theView, Monopoly theGame)
	{
		this.theView = theView;
		this.theGame = theGame;
		this.theGame.addPlayer(new Player("Player 1", 150, theGame.getBoard()));
		this.theGame.addPlayer(new Player("Player 2", 150, theGame.getBoard()));
		this.theGame.addPlayer(new Player("Player 3", 150, theGame.getBoard()));
		this.theGame.addPlayer(new Player("Player 4", 150, theGame.getBoard()));
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
		
	}
}
