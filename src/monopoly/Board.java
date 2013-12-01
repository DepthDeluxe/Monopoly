package monopoly;

import monopoly.tiles.*;

public class Board {
	//
	// Member Variables
	//
	
	// main components
	private ITile[] tiles;
	private CardDeck chanceDeck;
	private CardDeck communityChestDeck;
	
	// special tiles
	private FreeParking freeParking;
	private int jailLocation;
	
	public static final int SIZE = 40;
	public static final int JAIL = 20;
	
	//
	// Constructors
	//
	
	public Board(ITile[] tiles, CardDeck chanceDeck, CardDeck communityChestDeck) {
		this.tiles = tiles;
		this.chanceDeck = chanceDeck;
		this.communityChestDeck = communityChestDeck;
		
		// search for FreeParking tile 
		freeParking = null;
		for (ITile t : tiles) {
			try {
				freeParking = (FreeParking)t;
				break;
			}
			catch (ClassCastException e) {
				// don't do anything
			}
		}
	}
	
	//
	// Getters
	//
	
	public int getNumTiles() {
		return tiles.length;
	}
	
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
	
	public CardDeck getChanceDeck() {
		return chanceDeck;
	}
	
	public CardDeck getCommunityChestDeck() {
		return communityChestDeck;
	}
	
	public FreeParking getFreeParking() {
		return freeParking;
	}
}
