/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 5, 2013, 8:00:47 PM
 */

package monopoly;

import gui.MMainFrame;

/**
 * @author ajrk001
 *
 */
public class MController 
{
	private MMainFrame theView;
	private Board theBoard;
	
	public MController(MMainFrame frame)
	{
		this.theView = frame;
	}
}
