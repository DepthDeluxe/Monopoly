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
	Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	
	/**
	 * Constructor for the main frame, will initalize the frame and set attributes
	 */
	public MMainFrame()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Monopoly");
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		((JComponent) getContentPane()).setBorder(empty);
		this.setSize(1000, 700);
		
		MBoardPanel board = new MBoardPanel();
		this.getContentPane().add(board, BorderLayout.CENTER);
		this.theBoard = board;
		
		MControlPanel controlT = new MControlPanel();
		this.getContentPane().add(controlT, BorderLayout.WEST);
		this.control = controlT;
		this.control.setSize(100, 550);
		double[] arr = {4000.00, 2000.00, 1000.00, 5000.00};
		this.control.setMoneyVals(arr);
		
		Property p = new Property("Testing", 5000.00);
		p.setOwner(new Player("nase", 1000, null));
		MPropertiesPanel propertyPanel = new MPropertiesPanel(p);
		this.properties = propertyPanel;
		this.getContentPane().add(propertyPanel, BorderLayout.EAST);
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

}
