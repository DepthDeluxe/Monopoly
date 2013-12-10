/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 7, 2013, 9:48:14 PM
 */
package controller;

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

import javax.swing.JOptionPane;

import monopoly.Monopoly;
import monopoly.Player;

/**
 * @author ajrk001
 *
 */
public class MenuListener implements ActionListener
{
	Player[] play; // the player array that gets passed as needed
	MMainFrame frame; // need to attach the frame to jdialogs
	Random rand; // to get a random int for subreddits
	
	private final String reddit = "http://www.reddit.com/r/";
	private final String[] urls = {"gaming", "IAmA", "explainlikeimfive",
			"pcmasterrace", "FoodPorn", "fffffffuuuuuuuuuuuu", "Technology",
			"Programming", "AmazingTechnology", "circlejerk", };
	
	public MenuListener(Monopoly game, MMainFrame gm)
	{
		play = game.getPlayers();
		frame = gm;
		rand = new Random();
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
			MPlayerProperties panel = new MPlayerProperties(play, frame);
			panel.setLocationRelativeTo(frame);
			panel.setVisible(true);
		}
		else if(action.equals("Browse")) // open a random subreddit in default browser
		{
			String url = reddit + urls[rand.nextInt(urls.length)];
	        openBrowser(url);
		}
		else if(action.equals("Manual")) // open the manual in default viewer
		{
			openFile("userManual.pdf");
		}
		else if(action.equals("About")) // open custom jdialog with about settings
		{
			
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
}
