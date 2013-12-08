/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 7, 2013, 9:34:49 PM
 */
package controller;

import java.io.File;
import javax.swing.filechooser.FileFilter;


/**
 * Custom Class XMLFilter that extends from FileFilter. This allows us to attach this object 
 * to a file 
 * @author ajrk001
 *
 */
public class XMLFilter extends FileFilter
{
	/**
	 * Function that overrides the FileFilter accept to return if a file is xml or not
	 * @param f - File object that encapsulates a file path to be checked
	 */
	@Override
	public boolean accept(File f) 
	{
		if(f.isDirectory()) { return true; } // if it is a directory, return true
		
		String extension = stripExtension(f.getName()); // get the extension
		if(!extension.equals("")) // in case no extension could be found
		{
			if(extension.equalsIgnoreCase(".xml")) // if it is equal, true
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Helper function that returns just the 
	 * @param name - the name of the file without the path attached
	 * @return a string representing just the extension
	 */
	private String stripExtension(String name)
	{
		String ext = ""; // default value
		String s = name; // name of file
		int index = s.lastIndexOf('.'); // checking for last . representing extension
		
		if(index != -1 && index < s.length() - 1) // if not not found or greater than length
		{
			ext = s.substring(index); // the extension is just what remains (does include .)
		}
		return ext; // return this
	}

	/**
	 * Function that returns what the description of the FileChooser should read
	 */
	@Override
	public String getDescription() {
		return ".xml (XML Files only)";
	}

}