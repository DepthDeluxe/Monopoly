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

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import monopoly.Player;
import gui.MMainFrame;
import gui.MMortgageDialog;


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
		if(play.getProperties().size() == 0)
		{
			JOptionPane.showMessageDialog(null, "You don't have any properties!");
			return;
		}
		
		MMortgageDialog panel = new MMortgageDialog(frame, play);
		panel.setLocationRelativeTo(frame);
		panel.setVisible(true);
	}
}
