/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 8, 2013, 12:55:15 AM
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import monopoly.Player;
import monopoly.tiles.Property;
import gui.MMainFrame;
import gui.MMortgageDialog;
import gui.MUnmortgageDialog;


/**
 * @author ajrk001
 *
 */
public class MortgageListener implements ActionListener
{
	Player play;
	MMainFrame frame;
	
	public MortgageListener(Player p, MMainFrame f)
	{
		play = p;
		frame = f;
	}
	
	public void actionPerformed(ActionEvent e1)
	{
		openDialog(); // open the dialog box
	}
	
	private void openDialog()
	{
		if(play.getProperties().size() == 0) // if no properties
		{
			JOptionPane.showMessageDialog(null, "You don't have any properties to mortgage!");
			return;
		}
		else if((play.getProperties().size()-play.getMortgagedProperties()) == 0) // all properties are mortgaged
		{
			unmortgage();
		}
		else if(play.getMortgagedProperties() == 0) // if no mortgaged properties, go straight to mortgaged
		{
			mortgage();
		}
		else
		{
			Object[] options = {"Mortgage", "Unmortgage"};
			int option = JOptionPane.showOptionDialog(null, "Do you want to mortgage or unmortgage properties?", "Property Management", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(option == 0)
			{
				mortgage();
			}
			else if(option == 1)
			{
				unmortgage();
			}
		}
	}
	
	private void mortgage()
	{
		MMortgageDialog panel = new MMortgageDialog(frame, play);
		panel.setLocationRelativeTo(frame);
		panel.setVisible(true);
	}
	
	private void unmortgage()
	{
		MUnmortgageDialog panel = new MUnmortgageDialog(frame, play);
		panel.setLocationRelativeTo(frame);
		panel.setVisible(true);
	}
}
