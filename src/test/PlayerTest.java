package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import monopoly.Player;
import monopoly.tiles.Property;
import monopoly.tiles.Railroad;

public class PlayerTest {
	private Player player;
	private Property property;
	private Railroad railroad;
	
	private static final int START_MONEY = 3200;
	private static final String PROPERTY_NAME = "Test Property";
	private static final double PROPERTY_VALUE = 350.0;
	private static final String RAILROAD_NAME = "Test Railroad";
	
	private static final double ERROR = 0.01;
	
	@Before
	public void setUp() {
		// construct everything with specified test values
		player = new Player(START_MONEY);
		property = new Property(PROPERTY_NAME, PROPERTY_VALUE);
		railroad = new Railroad(RAILROAD_NAME);
	}
	
	@Test
	public void constructorTest() {
		assertEquals(player.getCurrentMoney(), START_MONEY, ERROR);
	}
	
	@Test
	public void takeMoney() {
		double toTake = 320.0;
		double originalMoney = player.getCurrentMoney();
		
		boolean success = player.takeMoney(toTake);
		
		// make sure the transfer was a success and the proper amount was moved
		assertTrue(success);
		assertEquals(originalMoney - toTake, player.getCurrentMoney(), ERROR);
	}
	
	@Test
	public void takeMoneyFail() {
		// take $1000 more than he has
		double toTake = player.getCurrentMoney() + 1000;
		
		boolean success = player.takeMoney(toTake);
		
		// make sure that there wasn't a success
		assertTrue(!success);
	}
	
	@Test
	public void giveMoney() {
		double toGive = 500.0;
		double originalMoney = player.getCurrentMoney();
		
		player.giveMoney(toGive);
		
		assertEquals(originalMoney + toGive, player.getCurrentMoney(), ERROR);
	}
	
	@Test
	public void buyProperty() {
		double propPrice = property.getPrice();
		double originalMoney = player.getCurrentMoney();
		
		boolean success = player.buyProperty(property);
		
		// make sure success flags is true, the property value was subtracted from
		// player's money, and the property owner is now the player
		assertTrue(success);
		assertEquals(originalMoney - propPrice, player.getCurrentMoney(), ERROR);
		assertTrue(property.getOwner() == player);
	}
	
	@Test
	public void buyPropertyMoneyFail() {
		// make sure there is not enough money left
		player.takeMoney(START_MONEY - 1);
		
		double originalMoney = player.getCurrentMoney();
		
		boolean success = player.buyProperty(property);
		
		// make sure failure flag was sent and player actually does not
		// own the property
		assertTrue(!success);
		assertTrue(!player.owns(property));
		assertEquals(originalMoney, player.getCurrentMoney(), ERROR);
	}
	
	@Test
	public void buyPropertyOwnerFail() {
		double originalMoney = player.getCurrentMoney();
		
		// let the other player buy the property
		Player otherPlayer = new Player(100000);
		otherPlayer.buyProperty(property);
		
		// now main player try to buy
		boolean success = player.buyProperty(property);
		
		assertTrue(!success);
		assertTrue(!player.owns(property));
		assertTrue(otherPlayer.owns(property));
		assertTrue(property.getOwner() == otherPlayer);
		assertEquals(originalMoney, player.getCurrentMoney(), ERROR);
	}
	
	@Test
	public void railroadCount() {
		// nothing should be owned in the beginning
		assertTrue(player.getNumRailroads() == 0);
		
		// buy the railroad
		player.buyProperty(railroad);
		
		// now should be owned
		assertTrue(player.getNumRailroads() == 1);
	}
	
	@After
	public void tearDown() {
		// nullify everything
		player = null;
		property = null;
		railroad = null;
	}
}
