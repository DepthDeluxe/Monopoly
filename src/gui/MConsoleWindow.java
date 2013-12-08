/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 7, 2013, 3:06:03 PM
 */
package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author ajrk001
 *
 */
public class MConsoleWindow extends JPanel
{
	JTextArea consoleWindow; // the window that has to be updated
	private final String endLine = "\n";
	
	public MConsoleWindow()
	{
		this.setLayout(new GridLayout(1, 1, 0, 0));
		
		consoleWindow = new JTextArea(5, 5);
		JScrollPane scroll = new JScrollPane(consoleWindow);
		consoleWindow.setWrapStyleWord(true);
		consoleWindow.setEditable(false);
		this.add(scroll);
	}
	
	/**
	 * a function that will add a new line to 
	 * @param line
	 */
	public void printLine(String line)
	{
		consoleWindow.append(line + endLine);
	}
}
