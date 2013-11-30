package test.tiles;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import monopoly.tiles.Property;
import monopoly.Player;

public class PropertyTest {

	Property property;
	Player player;
	
	private static final String PROPERTY_NAME = "Test Place";
	private static final double PROPERTY_VALUE = 225.0;
	
	private static final double START_MONEY = 10000.0;
	
	private static final double ERROR = 0.01;
	
	@Before
	public void setUp() {
		property = new Property(PROPERTY_NAME, PROPERTY_VALUE);
		player = new Player(START_MONEY + PROPERTY_VALUE);			// after purchase, player will have START_MONEY
		
		// the player owns the property by default
		player.buyProperty(property);
	}
	
	@Test
	public void testRent() {
		Player otherPlayer = new Player(START_MONEY);
		
		boolean success = property.chargeRent(otherPlayer);
		
		assertTrue(success);
		assertEquals(player.getCurrentMoney(), START_MONEY + property.getRent(), ERROR);
		assertEquals(otherPlayer.getCurrentMoney(), START_MONEY - property.getRent(), ERROR);
	}
	
	@Test
	public void testMortgage() {
		boolean success = property.mortgage();
		
		// make sure success was thrown, and the player has the mortgage value back
		assertTrue(success);
		assertEquals(player.getCurrentMoney(), START_MONEY + property.getMortgagedValue(), ERROR);
	}
	
	@Test
	public void testMortgageFailure() {
		// mortgage once and take away the value earned
		property.mortgage();
		player.takeMoney(property.getMortgagedValue());
		
		// and try to again
		boolean success = property.mortgage();
	
		// make sure fail flag was passed an no money was transferred
		assertTrue(!success);
		assertEquals(player.getCurrentMoney(), START_MONEY, ERROR);
	}
	
	@Test
	public void testMortgagedRent() {
		// mortgage the property and take away the amount earned
		property.mortgage();
		player.takeMoney(property.getMortgagedValue());
		
		Player otherPlayer = new Player(START_MONEY);
		
		boolean success = property.chargeRent(otherPlayer);
		
		// make sure fail flag passed, and no money was transferred
		assertTrue(!success);
		assertEquals(player.getCurrentMoney(), START_MONEY, ERROR);
		assertEquals(otherPlayer.getCurrentMoney(), START_MONEY, ERROR);
	}
	
	@After
	public void tearDown() {
		property = null;
		player = null;
	}
}
