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

import controller.MenuListener;

/**
 * @author ajrk001
 *
 */
public class MMenuBar extends JMenuBar
{
	private JMenu fileMenu, gameMenu;
	private JMenuItem[] fileOptions;
	private JMenuItem[] gameOptions;
	
	public MMenuBar()
	{
		fileMenu = new JMenu("File"); // create the main menu holder
		this.add(fileMenu);
		
		fileOptions = new JMenuItem[4]; // array of options for file
		
		fileOptions[0] = new JMenuItem("New Game");
		fileOptions[0].setActionCommand("NewGame");
		fileMenu.add(fileOptions[0]);
		fileMenu.addSeparator(); // seperate new game from the other options
		
		fileOptions[1] = new JMenuItem("Save Game");
		fileOptions[1].setActionCommand("SaveGame");
		fileOptions[2] = new JMenuItem("Load Game");
		fileOptions[2].setActionCommand("LoadGame");
		fileMenu.add(fileOptions[1]);
		fileMenu.add(fileOptions[2]);
		fileMenu.addSeparator();
		
		fileOptions[3] = new JMenuItem("Exit");
		fileOptions[3].setActionCommand("Exit");
		fileMenu.add(fileOptions[3]);
		
		gameMenu = new JMenu("Game Options");
		this.add(gameMenu);
		
		gameOptions = new JMenuItem[1];
		gameOptions[0] = new JMenuItem("See Player Properties");
		gameOptions[0].setActionCommand("SeeProps");
		gameMenu.add(gameOptions[0]);
	}

	public void setActionListener(MenuListener listen)
	{
		for(int x = 0; x < fileOptions.length; x++)
		{
			fileOptions[x].addActionListener(listen);
		}
		for(int x = 0; x < gameOptions.length; x++)
		{
			gameOptions[x].addActionListener(listen);
		}
	}
}
