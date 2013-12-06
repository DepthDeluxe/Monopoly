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
	private JButton btnConfirm;
	
	public MSettingsMenu(int hours) 
	{
		this.setTitle("Settings");
		this.setLocation(100, 100);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel optionPanel = new JPanel();
		getContentPane().add(optionPanel);
		optionPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNoPlayers = new JLabel("Number of Players:");
		lblNoPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(lblNoPlayers);
		
		comboPlayers = new JComboBox();
		comboPlayers.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		optionPanel.add(comboPlayers);
		
		JLabel lblAvailableCash = new JLabel("Available hours at start:");
		lblAvailableCash.setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(lblAvailableCash);
		
		textFieldCash = new JTextField();
		textFieldCash.setText(Integer.toString(hours));
		textFieldCash.setHorizontalAlignment(SwingConstants.CENTER);
		optionPanel.add(textFieldCash);
		textFieldCash.setColumns(6);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnConfirm = new JButton("Set Options");
		panel.add(btnConfirm);
		
		this.pack();
		
		final MSettingsMenu menuTwo = this;
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1)
			{
				boolean x = true;
				int hours = 0;
				try
				{
					hours = Integer.parseInt(menuTwo.textFieldCash.getText());
				}
				catch(Exception e2) { x = false; }
				
				if(x)
				{
					MMainMenu menu = new MMainMenu(hours, Integer.parseInt((String) menuTwo.comboPlayers.getModel().getSelectedItem()));
					menu.setVisible(true);
					menuTwo.dispose();
				}
				else { JOptionPane.showMessageDialog(null, "Please enter an integer in the hours field!"); }
			}
		});
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
	 * @return the btnConfirm
	 */
	public JButton getBtnConfirm() {
		return btnConfirm;
	}
}
