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

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import monopoly.Monopoly;

/**
 * @author ajrk001
 *
 */
public class ViewController 
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
	
	public ViewController()
	{
		numHours = 150;
		numPlayers = 4;
		playerNames = new String[4];
		menu = new MMainMenu();
		runMainMenu();
	}
	
	public void runMainMenu()
	{
		menu.setVisible(true);
		
		menu.getBtnSettings().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1)
			{
				runSettingsMenu();
			}
		});
		
		menu.getBtnNewGame().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2)
			{
				MMainFrame frame = new MMainFrame();
				game = frame;
				frame.setVisible(true);
				menu.setVisible(false);
				
				// create the model
				theGame = new Monopoly("Original-Tiles.xml", null, null);
				
				// create the controller
				theController = new MController(frame, theGame);
			}
		});
		
	}
	
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
				settings.revalidate();
			}
		});
	}
	
	private void setEdit(JTextField field, boolean bool)
	{
		if(bool)
		{
			field.setEditable(true);
			field.setEnabled(true);
		}
		else
		{
			field.setEditable(false);
			field.setEnabled(false);
		}
	}
	
	private void settingConfirmAction()
	{
		boolean x = true;
		int hoursTemp = 0;
		try
		{
			hoursTemp = Integer.parseInt(settings.getTextFieldCash().getText());
		}
		catch(Exception e2) { x = false; }
		
		if(x)
		{
			int players = Integer.parseInt( (String) settings.getComboPlayers().getModel().getSelectedItem());
			numHours = hoursTemp;
			numPlayers = players;
			JTextField[] fields = settings.getPlayerName();
			for(int d = 0; d < 4; d++) { playerNames[d] = fields[d].getText(); }
			settings.setVisible(false);
			menu.setVisible(true);
		}				
		else { JOptionPane.showMessageDialog(null, "Please enter an integer in the hours field!"); }
	}
}