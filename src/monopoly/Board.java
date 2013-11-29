package monopoly;

public class Board {
	//
	// Member Variables
	//
	
	private ITile[] tiles;
	
	// Static Variables
	public static final int SIZE = 40;
	
	//
	// Constructors
	//
	
	public Board() {
		// init the property list
		tiles = new ITile[SIZE];
	}
	
	//
	// Setters
	//
	
	public void setTile(ITile t, int index) {
		tiles[index] = t;
	}
	
	//
	// Getters
	//
	
	public ITile getTileAt(int index) {
		return tiles[index];
	}
	
	public Property getPropertyAt(int index) {
		// get the ITile
		ITile desiredTile = tiles[index];
		
		// try to typecast and if it resulted in exception,
		// return null
		Property outProperty;
		try {
			outProperty = (Property)desiredTile;
		}
		catch (ClassCastException e) {
			return null;
		}
		
		return outProperty;
	}
}
