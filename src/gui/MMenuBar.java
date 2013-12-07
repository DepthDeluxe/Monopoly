/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 7, 2013, 2:50:10 PM
 */
package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author ajrk001
 *
 */
public class MMenuBar extends JMenuBar
{
	JMenu fileMenu, gameMenu;
	JMenuItem[] fileOptions;
	JMenuItem[] gameOptions;
	
	public MMenuBar()
	{
		fileMenu = new JMenu("File"); // create the main menu holder
		this.add(fileMenu);
		
		fileOptions = new JMenuItem[3]; // array of options for file
		
		fileOptions[0] = new JMenuItem("New Game");
		fileMenu.add(fileOptions[0]);
		fileMenu.addSeparator(); // seperate new game from the other options
		
		fileOptions[1] = new JMenuItem("Save Game");
		fileOptions[2] = new JMenuItem("Load Game");
		fileMenu.add(fileOptions[1]);
		fileMenu.add(fileOptions[2]);
		
		gameMenu = new JMenu("Game Options");
		this.add(gameMenu);
		
		gameOptions = new JMenuItem[1];
		gameOptions[0] = new JMenuItem("See Player Properties");
		gameMenu.add(gameOptions[0]);
	}
}
