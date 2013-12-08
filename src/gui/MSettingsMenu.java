/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 5, 2013, 8:58:51 PM
 */
package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MSettingsMenu extends JFrame
{
	private JTextField textFieldCash;
	private JComboBox comboPlayers;
	private JComboBox comboAI;
	private JButton btnConfirm;
	private JTextField[] playerName;
	private JLabel[] playerNameLbl;

	
	public MSettingsMenu(int hours) 
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Settings");
		this.setLocation(100, 100);
		this.setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		playerName = new JTextField[4];
		playerNameLbl = new JLabel[4];
		
		JPanel optionPanel = new JPanel();
		getContentPane().add(optionPanel);
		optionPanel.setLayout(new GridLayout(7, 2, 0, 0));
		
		JLabel lblNoPlayers = new JLabel("Number of Players:");
		lblNoPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(lblNoPlayers);
		
		comboPlayers = new JComboBox();
		comboPlayers.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4"}));
		comboPlayers.setSelectedIndex(2);
		optionPanel.add(comboPlayers);
		
		JLabel lblAvailableCash = new JLabel("Available hours at start:");
		lblAvailableCash.setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(lblAvailableCash);
		
		textFieldCash = new JTextField();
		textFieldCash.setText(Integer.toString(hours));
		textFieldCash.setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(textFieldCash);
		textFieldCash.setColumns(6);
		
		JLabel lblAiDifficulty = new JLabel("AI Difficulty:");
		lblAiDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(lblAiDifficulty);
		
		comboAI = new JComboBox();
		comboAI.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Medium", "Hard"}));
		optionPanel.add(comboAI);
		
		playerNameLbl[0] = new JLabel("Your Name?");
		playerNameLbl[0].setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(playerNameLbl[0]);
		
		playerName[0] = new JTextField();
		playerName[0].setText("Player One");
		optionPanel.add(playerName[0]);
		playerName[0].setColumns(10);
		
		playerNameLbl[1] = new JLabel("Player Two Name?");
		playerNameLbl[1].setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(playerNameLbl[1]);
		
		playerName[1] = new JTextField();
		playerName[1].setText("Player Two");
		optionPanel.add(playerName[1]);
		playerName[1].setColumns(10);
		
		playerNameLbl[2] = new JLabel("Player Three Name?");
		playerNameLbl[2].setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(playerNameLbl[2]);
		
		playerName[2] = new JTextField();
		playerName[2].setText("Player Three");
		optionPanel.add(playerName[2]);
		playerName[2].setColumns(10);	
		
		playerNameLbl[3] = new JLabel("Player Four Name?");
		playerNameLbl[3].setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(playerNameLbl[3]);
		
		playerName[3] = new JTextField();
		playerName[3].setText("Player Four");
		optionPanel.add(playerName[3]);
		playerName[3].setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnConfirm = new JButton("Set Options");
		panel.add(btnConfirm);
		
		this.pack();
	}
	/**
	 * @return the textFieldCash
	 */
	public JTextField getTextFieldCash() {
		return textFieldCash;
	}

	/**
	 * @return the comboPlayers
	 */
	public JComboBox getComboPlayers() {
		return comboPlayers;
	}

	/**
	 * @return the comboAI
	 */
	public JComboBox getComboAI() {
		return comboAI;
	}

	/**
	 * @return the btnConfirm
	 */
	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	/**
	 * @return the playerName
	 */
	public JTextField[] getPlayerName() {
		return playerName;
	}
}
