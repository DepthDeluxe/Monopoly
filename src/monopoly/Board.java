package monopoly;

public class Board {
	//
	// Member Variables
	//
	
	private Property[] properties;
	
	// Static Variables
	public static final int SIZE = 40;
	
	//
	// Constructors
	//
	
	public Board() {
		// init the property list
		properties = new Property[SIZE];
	}
	
	//
	// Getters
	//
	
	public Property getPropertyAt(int index) {
		return properties[index];
	}
}
