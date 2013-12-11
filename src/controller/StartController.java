/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 5, 2013, 10:35:09 PM
 */
package controller;

import gui.MMainFrame;
import gui.MMainMenu;
import gui.MSettingsMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import monopoly.Monopoly;
import monopoly.xml.MonopolyIO;

/**
 * @author ajrk001
 *
 */
public class StartController 
{
	//
	// Member Variables 
	//
	
	// View
	MMainMenu menu;
	MSettingsMenu settings;
	MMainFrame game;
	
	// Model
	Monopoly theGame;
	
	// Controller
	MController theController;
	
	// data to be passed from settings
	int numHours;
	int numPlayers;
	String[] playerNames;
	String difficulty;
	
	public StartController()
	{
		numHours = 150; // default values for variables
		numPlayers = 4;
		playerNames = new String[] {"Player One", "Player Two", "Player Three", "Player Four"};
		difficulty = "Easy";
		menu = new MMainMenu();
		runMainMenu(); // create the start menu
	}
	
	/**
	 * Function that will create a new main menu object, and add action listeners
	 * to the appropriate buttons.
	 */
	public void runMainMenu()
	{
		menu.setVisible(true);
		// add action listeners to all three buttons
		menu.getBtnSettings().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1)
			{
				runSettingsMenu(); 
			}
		});
		
		menu.getBtnNewGame().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2)
			{
				MMainFrame frame = new MMainFrame(numPlayers); // open view
				game = frame;
				
				// set the names of the players
				game.getControl().setNames(playerNames);
				
				frame.setVisible(true);
				menu.setVisible(false);
				
				// instantiate the model from the different tiles
				theGame = new Monopoly("Tiles.xml", "Chance.xml", "CommunityChest.xml");
				
				// create the controller
				theController = new MController(frame, theGame, numHours, numPlayers, playerNames, difficulty);
			}
		});
		
		menu.getBtnLoadGame().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e3)
			{
				runLoadGame(); // special function needed to get some components
			}
		});
	}
	
	/**
	 * Function to create a new Settings menu, and add action listeners to the resepctive components
	 */
	public void runSettingsMenu()
	{
		MSettingsMenu frame = new MSettingsMenu(numHours);
		settings = frame;
		frame.setVisible(true);
		menu.setVisible(false);

		settings.getBtnConfirm().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1)
			{
				settingConfirmAction();
			}
		});
		
		settings.getComboPlayers().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2)
			{
				int selec = Integer.parseInt((String) settings.getComboPlayers().getSelectedItem()); // get the selected number of palyers
				JTextField[] fields = settings.getPlayerName();
				if(selec == 2)
				{
					setEdit(fields[2], false);
					setEdit(fields[3], false);
				}
				else if(selec == 3)
				{
					setEdit(fields[2], true);
					setEdit(fields[3], false);
				}
				else if(selec == 4)
				{
					setEdit(fields[2], true);
					setEdit(fields[3], true);
				}
				settings.revalidate(); // make sure the frame is up to date
			}
		});
	}
	
	/**
	 * Function to help set if a JTextField should be editable/enabled or not
	 * @param field - the field to set values for
	 * @param bool - what should the values be
	 */
	private void setEdit(JTextField field, boolean bool)
	{
			field.setEditable(bool);
			field.setEnabled(bool);
	}
	
	/** 
	 * Function that will handle the functionality for clicking the button on the settings frame
	 */
	private void settingConfirmAction()
	{
		boolean x = true;
		int hoursTemp = 0;
		try
		{
			hoursTemp = Integer.parseInt(settings.getTextFieldCash().getText()); // make sure hours is entered as an int
		}
		catch(Exception e2) { x = false; }
		
		if(x) // if hours == type int
		{
			int players = Integer.parseInt( (String) settings.getComboPlayers().getModel().getSelectedItem()); // get the chosen item
			numHours = hoursTemp; // set the val of the member vars to the vals you get from the frame
			numPlayers = players;
			JTextField[] fields = settings.getPlayerName();
			difficulty = (String) settings.getComboAI().getModel().getSelectedItem();
			for(int d = 0; d < 4; d++) { playerNames[d] = fields[d].getText(); }
			settings.setVisible(false); // change the frame
			menu.setVisible(true);
		}				
		else { JOptionPane.showMessageDialog(null, "Please enter an integer in the hours field!"); } // make sure they enter a int
	}
	
	/**
	 * function that will handle the picking and loading of a new game for the main menu
	 */
	private void runLoadGame()
	{
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false); // user cannot pick all file option
		fileChooser.setFileFilter(new XMLFilter()); // uses custom class to exclude all non-xml files
		fileChooser.setSelectedFile(new File("saveFile.xml")); // what the default file name should be
		
		int returnVal = fileChooser.showOpenDialog(menu); // attaches filechooser to the menu pane
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile(); //  get the selected path
			String filePath = file.toString(); // need the string file path to pass to save class
			String ext = filePath.substring(filePath.lastIndexOf(".")); //  the extension itself
			
			if(ext.equalsIgnoreCase(".xml")) // is it an xml?
			{
				MonopolyIO.loadGame(filePath); // load game
			}
			else // otherwise tell user
			{
				JOptionPane.showMessageDialog(menu, "That is not a valid file that can be loaded from");
			}
		}
	}
}

