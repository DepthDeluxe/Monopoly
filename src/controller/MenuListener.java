/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 7, 2013, 9:48:14 PM
 */
package controller;

import gui.AboutDialog;
import gui.MMainFrame;
import gui.MPlayerProperties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import monopoly.Monopoly;
import monopoly.Player;
import monopoly.xml.MonopolyIO;

/**
 * @author ajrk001
 *
 */
public class MenuListener implements ActionListener
{
	Player[] play; // the player array that gets passed as needed
	MMainFrame frame; // need to attach the frame to jdialogs
	Monopoly theGame;
	
	public MenuListener(Monopoly game, MMainFrame gm)
	{
		play = game.getPlayers();
		frame = gm;
		theGame = game;
	}
	
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
			runSaveGame();
		}
		else if(action.equals("LoadGame")) // load game
		{
			runLoadGame();
		}
		else if(action.equals("Exit")) // exit
		{
			System.exit(0);
		}
		else if(action.equals("SeeProps")) // if they want to see the properties owned by each player
		{
			MPlayerProperties panel = new MPlayerProperties(play, frame);
			panel.setLocationRelativeTo(frame);
			panel.setVisible(true);
		}
		else if(action.equals("Browse")) // open a random subreddit in default browser
		{
	        openBrowser("http://www.reddit/r/random");
		}
		else if(action.equals("Manual")) // open the manual in default viewer
		{
			openFile("UserManual.pdf");
		}
		else if(action.equals("About")) // open custom jdialog with about settings
		{
			AboutDialog dialog = new AboutDialog(frame);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
		}
	}
	
	private void openFile(String filePath)
	{
		File file = new File(filePath);
		if(file.toString().endsWith(".pdf"))
		{
			try 
			{
				Desktop.getDesktop().open(file);
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "An error occured, and a window will not be launched");				
			}
		}
	}
	
	private void openBrowser(String url)
	{
		try 
		{
			Desktop.getDesktop().browse(new URI(url));
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "An error occured, and a browser window will not be launched");
		}
	}
	
	private void runLoadGame()
	{
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false); // user cannot pick all file option
		fileChooser.setFileFilter(new XMLFilter()); // uses custom class to exclude all non-xml files
		fileChooser.setSelectedFile(new File("saveFile.xml")); // what the default file name should be
		
		int returnVal = fileChooser.showOpenDialog(frame); // attaches filechooser to the menu pane
		
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
				JOptionPane.showMessageDialog(frame, "That is not a valid file that can be loaded from");
			}
		}
	}
	
	private void runSaveGame()
	{
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false); // user cannot pick all file option
		fileChooser.setFileFilter(new XMLFilter()); // uses custom class to exclude all non-xml files
		fileChooser.setSelectedFile(new File("saveFile.xml")); // what the default file name should be
		
		int returnVal = fileChooser.showSaveDialog(frame); // attaches filechooser to the menu pane
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile(); //  get the selected path
			String filePath = file.toString(); // need the string file path to pass to save class
			String ext = filePath.substring(filePath.lastIndexOf(".")); //  the extension itself
			
			if(ext.equalsIgnoreCase(".xml")) // is it an xml?
			{
				MonopolyIO.saveGame(filePath, theGame); // save game
			}
			else // otherwise tell user
			{
				JOptionPane.showMessageDialog(frame, "That is not a valid file that can be saved to");
			}
		}
	}
}
