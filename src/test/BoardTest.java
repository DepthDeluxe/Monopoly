package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import monopoly.*;
import monopoly.tiles.*;
import monopoly.xml.TileLoader;

public class BoardTest {
	//
	// Member Variables
	//
	
	private final ITile[] tiles = TileLoader.loadFromXML("Tiles.xml");
	private Board theBoard;
	
	//
	// Set Up Function
	//
	
	@Before
	public void setUp() {		
		theBoard = new Board(tiles, null, null);
	}
	
	//
	// JUnit Tests
	//
	
	@Test
	public void numTiles() {
		assertTrue(tiles.length == theBoard.getNumTiles());
	}
	
	@Test
	public void getTile() {
		int pickedTile = 3;
		
		ITile arrayTile = tiles[pickedTile];
		ITile boardTile = theBoard.getTileAt(pickedTile);
		
		assertTrue(arrayTile.equals(boardTile));
	}
	
	@Test
	public void getProperty() {
		int propertyTile = 3;
		int nonPropertyTile = 4;
		
		// get the tile from two different locations
		Property arrayProperty = (Property)tiles[propertyTile];
		Property boardProperty = theBoard.getPropertyAt(propertyTile);
		
		// should be null
		Property nonProperty = theBoard.getPropertyAt(nonPropertyTile);
		
		// make sure that the following things are true
		assertTrue(arrayProperty.equals(boardProperty));
		assertTrue(nonProperty == null);
	}
	
	@Test
	public void findFreeParking() {
		// first find free parking in tiles list
		FreeParking arrayFP = null;
		for (int n = 0; n < tiles.length; n++) {
			ITile currentTile = tiles[n];
			if (currentTile.getTileType() == TileType.FREE_PARKING) {
				arrayFP = (FreeParking)currentTile;
			}
		}
		
		// get free parking from the board
		FreeParking boardFP = theBoard.getFreeParking();
		
		// verify that they are equal
		assertTrue(arrayFP.equals(boardFP));
	}
	
	@Test
	public void getJailLocation() {
		int jailLocation = -1;
		
		// first find by looking through tiles list
		for (int n = 0; n < tiles.length; n++) {
			ITile currentTile = tiles[n];
					
			if (currentTile.getTileType() == TileType.JAIL) {
				jailLocation = n;
				break;
			}
		}
		
		// make sure they are equal
		assertTrue(jailLocation == theBoard.getJailLocation());
	}
	
	//
	// Tear Down Function
	//
	
	@After
	public void tearDown() {
		theBoard = null;
	}
}
