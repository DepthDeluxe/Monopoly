package test.tiles;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import monopoly.tiles.Property;
import monopoly.Player;

public class PropertyTest {

	Property property;
	Player player, otherPlayer;

	// the player constructor constants
	private static final double START_MONEY = 10000.0;
	private static final int BOARD_SIZE = 40;
	
	// other required variables
	private static final String PROPERTY_NAME = "Test Place";
	private static final double PROPERTY_VALUE = 225.0;
	private static final double PROPERTY_RENT = 22.50;
	private static final double PROPERTY_MORTGAGE_VALUE = 100.0;
	private static final int PROPERTY_GROUP = 1;
	
	
	private static final double ERROR = 0.01;
	
	@Before
	public void setUp() {		
		property = new Property(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_RENT,
				PROPERTY_MORTGAGE_VALUE, PROPERTY_GROUP);

		// init the players
		player = new Player("Player", START_MONEY + PROPERTY_VALUE, BOARD_SIZE);		// so player has START_MONEY after buying the property
		otherPlayer = new Player("Other Player", START_MONEY, BOARD_SIZE);
		
		// the player owns the property by default
		player.buyProperty(property);

	}
	
	@Test
	public void rentCalculation() {
		// get the rent without multiple in group
		double singleRent = property.getRent();
		
		// buy the property again and calculate double rent
		player.buyProperty(property);
		double doubleRent = property.getRent();
		
		// buy property again and calculate triple rent
		player.buyProperty(property);
		double tripleRent = property.getRent();
		
		// make sure the rents are the right value
		assertEquals(singleRent, PROPERTY_RENT, ERROR);
		assertEquals(doubleRent, 2 * PROPERTY_RENT, ERROR);
		assertEquals(tripleRent, 4 * PROPERTY_RENT, ERROR);
	}
	
	@Test
	public void rentTransfer() {		
		boolean success = property.chargeRent(otherPlayer);
		
		assertTrue(success);
		assertEquals(player.getMoney(), START_MONEY + property.getRent(), ERROR);
		assertEquals(otherPlayer.getMoney(), START_MONEY - property.getRent(), ERROR);
	}
	
	@Test
	public void mortgage() {
		boolean success = property.mortgage();
		
		// make sure success was thrown, and the player has the mortgage value back
		assertTrue(success);
		assertEquals(player.getMoney(), START_MONEY + property.getMortgagedValue(), ERROR);
	}
	
	@Test
	public void mortgageFailure() {
		// mortgage once and take away the value earned
		property.mortgage();
		player.takeMoney(property.getMortgagedValue());
		
		// and try to again
		boolean success = property.mortgage();
	
		// make sure fail flag was passed an no money was transferred
		assertTrue(!success);
		assertEquals(player.getMoney(), START_MONEY, ERROR);
	}
	
	@Test
	public void mortgagedRent() {
		// mortgage the property and take away the amount earned
		property.mortgage();
		player.takeMoney(property.getMortgagedValue());
		
		boolean success = property.chargeRent(otherPlayer);
		
		// make sure fail flag passed, and no money was transferred
		assertTrue(!success);
		assertEquals(player.getMoney(), START_MONEY, ERROR);
		assertEquals(otherPlayer.getMoney(), START_MONEY, ERROR);
	}
	
	@After
	public void tearDown() {
		property = null;
		player = null;
	}
}
