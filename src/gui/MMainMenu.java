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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author ajrk001
 *
 */
public class MMainMenu extends JFrame
{
	private int hours;
	private int players;
	
	private JButton btnNewGame;
	private JButton btnLoadGame;
	private JButton btnSettings;
	
	public MMainMenu(int hour, int player) 
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Reddit Monopoly!");
		this.setLocation(100, 100);
		this.setSize(400, 350);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		hours = hour;
		players = player;
		
		JLabel monopolyImage = new JLabel("");
		monopolyImage.setIcon(new ImageIcon(MMainMenu.class.getResource("/gui/Images/mainMenu.jpg")));
		monopolyImage.setBounds(400, 250, 0, 0);
		getContentPane().add(monopolyImage, BorderLayout.NORTH);
		
		JPanel btnPanel = new JPanel();
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		btnNewGame = new JButton("New Game");
		btnPanel.add(btnNewGame);
		btnLoadGame = new JButton("Load Game");
		btnPanel.add(btnLoadGame);
		btnSettings = new JButton("Settings");
		btnPanel.add(btnSettings);
		
		final MMainMenu menuTwo = this;
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e0)
			{
				MSettingsMenu menu = new MSettingsMenu(hours);
				menu.setVisible(true);
				menuTwo.dispose();
			}
		});
		
		/*btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2)
			{
				MonopolyRunGame game = new MonopolyRunGame(hours, players);
				this.dispose();
			}
		});*/
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the players
	 */
	public int getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(int players) {
		this.players = players;
	}

	/**
	 * @return the btnNewGame
	 */
	public JButton getBtnNewGame() {
		return btnNewGame;
	}

	/**
	 * @return the btnLoadGame
	 */
	public JButton getBtnLoadGame() {
		return btnLoadGame;
	}

	/**
	 * @return the btnSettings
	 */
	public JButton getBtnSettings() {
		return btnSettings;
	}
}
