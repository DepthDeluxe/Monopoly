/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 7, 2013, 9:48:14 PM
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ajrk001
 *
 */
public class MenuListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String action = e.getActionCommand(); // button objective
		if(action.equals("NewGame")) // new game
		{
			// do new game stuff
			System.out.println("New Game!");
		}
		else if(action.equals("SaveGame")) // save game
		{
			// do save game stuff
			System.out.println("Save Game!");
		}
		else if(action.equals("LoadGame")) // load game
		{
			// do load game stuff
			System.out.println("Load Game!");
		}
		else if(action.equals("Exit")) // exit
		{
			System.exit(0);
		}
		else if(action.equals("SeeProps")) // if they want to see the properties owned by each player
		{
			// do see stuff
			System.out.println("See properties of all players!");
		}
	}

}
