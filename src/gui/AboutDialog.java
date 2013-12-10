/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 9, 2013, 8:03:43 PM
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

/**
 * @author ajrk001
 *
 */
public class AboutDialog extends JDialog implements ActionListener
{
	public AboutDialog(MMainFrame frame)
	{
		super(frame, true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JPanel lblPanel = new JPanel();
		lblPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblTitle = new JLabel("Monopoly: Reddit Edition");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanel.add(lblTitle);
		
		JLabel lblDevs = new JLabel("Developed by Bonsai Commando Squad Studios");
		lblDevs.setHorizontalAlignment(SwingConstants.CENTER);
		lblDevs.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPanel.add(lblDevs);
		
		JLabel lblSpecialHelp = new JLabel("With Special Help from Gaben");
		lblSpecialHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpecialHelp.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPanel.add(lblSpecialHelp);
		
		JPanel imagePanel = new JPanel();
		JLabel label = new JLabel("");
		imagePanel.add(label);
		label.setIcon(new ImageIcon(AboutDialog.class.getResource("/gui/Images/gabenAboutMenu.jpg")));
		imagePanel.setPreferredSize(new Dimension(label.getIcon().getIconWidth(), label.getIcon().getIconHeight()));
		
		JPanel btnPane = new JPanel();
		JButton btnBack = new JButton("Gaben approves of this messages");
		btnBack.addActionListener(this);
		btnPane.add(btnBack);
		
		this.add(lblPanel, BorderLayout.NORTH);
		this.add(imagePanel, BorderLayout.CENTER);
		this.add(btnPane, BorderLayout.SOUTH);
		
		this.pack();
	}
		
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.dispose();	
	}

}
