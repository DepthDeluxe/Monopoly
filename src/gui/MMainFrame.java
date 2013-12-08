/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Nov 15, 2013, 10:13:41 AM
 */
package gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import monopoly.Player;
import monopoly.tiles.Property;

/**
 * @author ajrk001
 * Class that will represent the main frame of the board. 
 */
public class MMainFrame extends JFrame
{
	private MBoardPanel theBoard;
	private MControlPanel control;
	private MPropertiesPanel properties;
	private MMenuBar menuBar;
	private MConsoleWindow consoleWindow;
	Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	
	/**
	 * Constructor for the main frame, will initalize the frame and set attributes
	 */
	public MMainFrame(int numPlayers)
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set to close on exit
		this.setTitle("Monopoly"); // set fram title
		this.setLocation(100, 100); // set location
		this.setResizable(false); // not resizable to keep components in good shape
		this.setSize(900, 800); // set size of this window
		
		
		MMenuBar tempBar = new MMenuBar(); // the menu bad
		menuBar = tempBar;
		this.setJMenuBar(menuBar); // add it
		
		MBoardPanel board = new MBoardPanel(numPlayers); // create the board
		this.getContentPane().add(board, BorderLayout.CENTER); // in the center
		this.theBoard = board;
		
		MControlPanel controlT = new MControlPanel();
		this.getContentPane().add(controlT, BorderLayout.WEST);
		this.control = controlT;
		this.control.setSize(100, 450);
		double[] arr = {150.00, 150.00, 150.00, 150.00};
		this.control.setMoneyVals(arr);
		
		MPropertiesPanel propertyPanel = new MPropertiesPanel();
		this.properties = propertyPanel;
		this.getContentPane().add(propertyPanel, BorderLayout.EAST);
		
		/*MConsoleWindow console = new MConsoleWindow();
		this.consoleWindow = console;
		this.gamePanel.add(console, BorderLayout.SOUTH);*/
	}
	
	/**
	 * Function to get the main board panel
	 * @return MBoardPanel instance of the board
	 */
	public MBoardPanel getTheBoard() {
		return theBoard;
	}

	/**
	 * Function to get the control panel
	 * @return MControlPanel instance of the panel
	 */
	public MControlPanel getControl() {
		return control;
	}

	/**
	 * Function to get the properties panel
	 * @return MPropertiesPanel instance of the panel
	 */
	public MPropertiesPanel getProperties() {
		return properties;
	}

	/**
	 * @return the menuBar
	 */
	public MMenuBar getMMenuBar() {
		return menuBar;
	}

	/**
	 * @return the consoleWindow
	 */
	public MConsoleWindow getConsoleWindow() {
		return consoleWindow;
	}
}
