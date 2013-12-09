package test.tiles;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import monopoly.Player;
import monopoly.PlayerBankruptException;
import monopoly.tiles.TaxTile;
import monopoly.tiles.FreeParking;

public class TaxTileTest {
	private TaxTile taxTile;
	
	private Player thePlayer;
	private FreeParking freeParking;
	
	private static final double START_AMOUNT = 200;
	private static final double TAX_RATE = 0.1;
	private static final double TAX_MINIMUM = 10;
	
	private static final double ERROR = 0.1;
	
	@Before
	public void setUp() {
		taxTile = new TaxTile(TAX_RATE, TAX_MINIMUM);
		
		thePlayer = new Player("Herman", START_AMOUNT, 10);
		freeParking = new FreeParking(START_AMOUNT);
	}
	
	@Test
	public void taxRate() {
		// have player land on tile
		taxTile.landOn(thePlayer);
		
		// check to see that it took the money properly
		assertEquals(thePlayer.getMoney(), (1 - TAX_RATE) * START_AMOUNT, ERROR);
	}
	
	@Test
	public void taxMinimum() throws PlayerBankruptException {
		// have player have only $50 remaining
		thePlayer.takeMoney(0.75 * START_AMOUNT);
		
		// land on the tile
		taxTile.landOn(thePlayer);
		
		// check to see that it took the right money
		assertEquals(thePlayer.getMoney(), (0.25 * START_AMOUNT) - TAX_MINIMUM, ERROR);
	}
	
	@Test
	public void landOnFP() {
		// bind the fp to tax tile
		taxTile.setFreeParking(freeParking);
		
		// land on the tile
		taxTile.landOn(thePlayer);
		
		// check to see that the proper money was transferred
		assertEquals(thePlayer.getMoney(), (1 - TAX_RATE) * START_AMOUNT, ERROR);
		assertEquals(freeParking.getMoneyInPot(), (1 + TAX_RATE) * START_AMOUNT, ERROR);
	}
	
	@After
	public void tearDown() {
		taxTile = null;
		
		thePlayer = null;
		freeParking = null;
	}
}
