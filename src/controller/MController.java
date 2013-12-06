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
	private MMainFrame theView;
	private Monopoly theGame;
	
	public MController(MMainFrame theView, Monopoly theGame)
	{
		this.theView = theView;
		this.theGame = theGame;
	}
	
	public void setRollDiceFunction() {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		
		theView.getControl().setRollDiceAction(actionListener);
	}
	
	public void setMortgageFunction() {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		
		theView.getControl().setMortgageAction(actionListener);
	}
}
