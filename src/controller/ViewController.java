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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	
	public ViewController()
	{
		menu = new MMainMenu(150, 4);
		runMainMenu();
	}
	
	public void runMainMenu()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					menu.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		} );
		
		menu.getBtnSettings().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1)
			{
				MSettingsMenu menuT = new MSettingsMenu(menu.getHours());
				menuT.setVisible(true);
				menu.setVisible(false);
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
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MSettingsMenu frame = new MSettingsMenu(menu.getHours());
					settings = frame;
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		} );
		
		settings.getBtnConfirm().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1)
			{
				settingConfirmAction();
			}
		});
	}
	
	private void settingConfirmAction()
	{
		boolean x = true;
		int hours = 0;
		try
		{
			hours = Integer.parseInt(settings.getTextFieldCash().getText());
		}
		catch(Exception e2) { x = false; }
		
		if(x)
		{
			int players = Integer.parseInt( (String) settings.getComboPlayers().getModel().getSelectedItem());
			menu.setHours(hours);
			menu.setPlayers(players);
			settings.setVisible(false);
			menu.setVisible(true);
		}				
		else { JOptionPane.showMessageDialog(null, "Please enter an integer in the hours field!"); }
	}
}
