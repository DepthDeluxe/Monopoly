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
	private JLayeredPane mainPane;
	private JPanel gamePanel;
	private JPanel tempPane;
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
		this.setSize(900, 700); // set size of this window
		
		this.mainPane = new JLayeredPane(); // new layered pane
		this.mainPane.setBorder(empty); // empty border
		this.setContentPane(this.mainPane); // make the layered pane the content pane
		
		this.gamePanel = new JPanel(new BorderLayout()); // the pane holding the main components 
		
		MMenuBar tempBar = new MMenuBar(); // the menu bad
		menuBar = tempBar;
		this.setJMenuBar(menuBar); // add it
		
		MBoardPanel board = new MBoardPanel(numPlayers); // create the board
		this.gamePanel.add(board, BorderLayout.CENTER); // in the center
		this.theBoard = board;
		
		MControlPanel controlT = new MControlPanel();
		this.gamePanel.add(controlT, BorderLayout.WEST);
		this.control = controlT;
		this.control.setSize(100, 550);
		double[] arr = {4000.00, 2000.00, 1000.00, 5000.00};
		this.control.setMoneyVals(arr);
		
		MPropertiesPanel propertyPanel = new MPropertiesPanel();
		this.properties = propertyPanel;
		this.gamePanel.add(propertyPanel, BorderLayout.EAST);
		
		/*MConsoleWindow console = new MConsoleWindow();
		this.consoleWindow = console;
		this.gamePanel.add(console, BorderLayout.SOUTH);*/
		
		this.gamePanel.setBounds(0, 0, 900, 700);
		this.mainPane.add(gamePanel, 0);
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

	/**
	 * @return the tempPane
	 */
	public JPanel getTempPane() {
		return tempPane;
	}

	/**
	 * @param tempPane the tempPane to set
	 */
	public void setTempPane(JPanel tempPane) {
		this.tempPane = tempPane;
	}

	/**
	 * @return the mainPane
	 */
	public JLayeredPane getMainPane() {
		return mainPane;
	}
	

}
