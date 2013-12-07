package monopoly;

import java.util.ArrayList;

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
			// if the tile is of type FREE_PARKING, then save it away
			if (t.getTileType() == TileType.FREE_PARKING) {
				freeParking = (FreeParking)t;
				break;
			}
		}
		
		// find the jail location
		jailLocation = 0;
		for (int n = 0; n < tiles.length; n++) {
			ITile t = tiles[n];
			
			if (t.getTileType() == TileType.JAIL) {
				jailLocation = n;
				break;
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
	
	public Property[] getPropertiesInGroup(int group) {
		ArrayList<Property> pInGroup = new ArrayList<Property>();
		
		for (ITile t : tiles) {
			// filter out non-properties
			if (t.getTileType() != TileType.PROPERTY) {
				break;
			}
			
			// typecast to a property
			Property p = (Property)t;
			
			// if the groups match, add it
			if (group == p.getGroup()) {
				pInGroup.add(p);
			}
		}
		
		// return the properties
		return pInGroup.toArray(new Property[0]);
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
	
	public int getJailLocation() {
		return jailLocation;
	}
}
