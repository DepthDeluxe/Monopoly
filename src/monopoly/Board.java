package monopoly;

public class Board {
	//
	// Member Variables
	//
	
	private ITile[] tiles;
	private CardDeck chanceDeck;
	private CardDeck communityChestDeck;
	
	// Static Variables
	public static final int SIZE = 40;
	public static final int JAIL = 20;
	
	//
	// Constructors
	//
	
	public Board(ITile[] tiles, CardDeck chanceDeck, CardDeck communityChestDeck) {
		this.tiles = tiles;
		this.chanceDeck = chanceDeck;
		this.communityChestDeck = communityChestDeck;
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
