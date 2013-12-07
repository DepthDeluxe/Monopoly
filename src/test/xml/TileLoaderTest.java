package test.xml;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import monopoly.MonopolyModelState;
import monopoly.xml.TileLoader;
import monopoly.tiles.*;

public class TileLoaderTest {
	//
	// Member Variables
	//
	
	ITile[] tiles;
	
	private static final double ERROR = 0.01;
	
	@Before
	public void setUp() {
		tiles = TileLoader.loadFromXML("Original-Tiles.xml");
	}
	
	//
	// JUnit Tests
	//
	
	@Test
	public void propertyTest() {		
		// test the first property
		ITile secondTile = tiles[1];
		Property property = (Property)secondTile;
		
		// make sure it has good values
		assertTrue(property.getName().equals("Mediterranean Avenue"));
		assertEquals(property.getPrice(), 60.0, ERROR);
	}
	
	@Test
	public void cardTileTest() {
		// get the first card tile
		ITile thirdTile = tiles[2];
		CardTile cardTile = (CardTile)thirdTile;
		
		// make sure it has good values
		assertTrue(cardTile.landOn(null) == MonopolyModelState.COMMUNITY_CHEST);
	}
	
	@Test
	public void railroadTest() {
		// get the first railroad tile
		ITile sixthTile = tiles[5];
		Railroad railroad = (Railroad)sixthTile;
		
		// make sure it has good values
		assertTrue(railroad.getName().equals("Reading Railroad"));
	}
	
	@Test
	public void utilityTest() {
		ITile utilityTile = tiles[12];
		Utility utility = (Utility)utilityTile;
		
		// make sure it has good values
		assertTrue(utility.getName().equals("Electric Company"));
	}

	@After
	public void tearDown() {
		tiles = null;
	}
}
