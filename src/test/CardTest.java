package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import monopoly.Card;
import monopoly.Monopoly;
import monopoly.Player;
import monopoly.Board;
import monopoly.tiles.ITile;
import monopoly.tiles.TileType;

public class CardTest {
	
	// members
	private Monopoly theGame;
	private Board theBoard;
	private Player thePlayer;
	private Player[] otherPlayers;
	
	private Card theCard;
	
	// constants used in scripts
	private static final double START_AMOUNT = 1000.0;
	private static final double TRANSACTION_AMOUNT = 200.0;
	private static final int START_POSITION = 0;
	private static final int NEW_POSITION = 3;
	private static final int OTHER_PLAYER_COUNT = 3;
	
	private static final String LAME_DESCRIPTION = "haha hoho hehe";
	
	// scripts
	private static final String PAY_SCRIPT = "pay 200";
	private static final String COLLECT_SCRIPT = "collect 200";
	private static final String PAY_TO_ALL_SCRIPT = "paytoall 200";
	private static final String COLLECT_FROM_ALL_SCRIPT = "collectfromall 200";
	private static final String MOVE_SCRIPT = "move 3";
	private static final String RAILROAD_SCRIPT = "railroad";
	private static final String UTILITY_SCRIPT = "utility";
	
	// error for assertEquals
	private static final double ERROR = 0.01;
	
	@Before
	public void setUp() {
		// create the game and set up the board
		theGame = new Monopoly("Tiles.xml", null, null);
		theBoard = theGame.getBoard();
		
		// init main player
		thePlayer = new Player("Herman", START_AMOUNT, theBoard.getNumTiles());
		theGame.addPlayer(thePlayer);
		
		// set up other players
		otherPlayers = new Player[OTHER_PLAYER_COUNT];
		for(int n = 0; n < OTHER_PLAYER_COUNT; n++) {
			// init the player
			otherPlayers[n] = new Player("dummy", START_AMOUNT, theBoard.getNumTiles());
			
			// add to the game
			theGame.addPlayer(otherPlayers[n]);
		}
	}
	
	@Test
	public void payTest() {
		theCard = new Card(LAME_DESCRIPTION, PAY_SCRIPT);
		
		// run the script
		theCard.runScript(theGame);
		
		// and verify the results
		assertEquals(thePlayer.getMoney(), START_AMOUNT - TRANSACTION_AMOUNT, ERROR);
		assertTrue(thePlayer.getPosition() == START_POSITION);
	}
	
	@Test
	public void collectTest() {
		theCard = new Card(LAME_DESCRIPTION, COLLECT_SCRIPT);
		
		theCard.runScript(theGame);
		
		assertEquals(thePlayer.getMoney(), START_AMOUNT + TRANSACTION_AMOUNT, ERROR);
		assertTrue(thePlayer.getPosition() == START_POSITION);
	}
	
	@Test
	public void payToAllTest() {
		theCard = new Card(LAME_DESCRIPTION, PAY_TO_ALL_SCRIPT);
		
		theCard.runScript(theGame);
		
		// verify it worked for the player
		assertEquals(thePlayer.getMoney(), 
				START_AMOUNT - OTHER_PLAYER_COUNT * TRANSACTION_AMOUNT,
				ERROR
				);
		assertTrue(thePlayer.getPosition() == START_POSITION);
		
		// verify other players got the money
		for (Player op : otherPlayers) {
			assertEquals(op.getMoney(), START_AMOUNT + TRANSACTION_AMOUNT, ERROR);
		}
	}
	
	@Test
	public void collectFromAllTest() {
		theCard = new Card(LAME_DESCRIPTION, COLLECT_FROM_ALL_SCRIPT);
		
		theCard.runScript(theGame);
		
		// verify it worked for the player
		assertEquals(thePlayer.getMoney(), 
				START_AMOUNT + OTHER_PLAYER_COUNT * TRANSACTION_AMOUNT,
				ERROR
				);
		assertTrue(thePlayer.getPosition() == START_POSITION);
				
		// verify other players got the money
		for (Player op : otherPlayers) {
			assertEquals(op.getMoney(), START_AMOUNT - TRANSACTION_AMOUNT, ERROR);
		}
	}
	
	@Test
	public void moveTest() {
		theCard = new Card(LAME_DESCRIPTION, MOVE_SCRIPT);
		
		theCard.runScript(theGame);
		
		// verify correct changes to state
		assertEquals(thePlayer.getMoney(), START_AMOUNT, ERROR);
		assertTrue(thePlayer.getPosition() == NEW_POSITION);
	}
	
	@Test
	public void railroadTest() {
		theCard = new Card(LAME_DESCRIPTION, RAILROAD_SCRIPT);
		
		theCard.runScript(theGame);
		
		// find the nearest railroad
		Integer nearestRailroadPos = null;
		for (int n = START_POSITION; n < theBoard.getNumTiles(); n++) {
			ITile t = theBoard.getTileAt(n);
			
			if (t.getTileType() == TileType.RAILROAD) {
				nearestRailroadPos = n;
			}
		}
		
		// verify correct changes
		assertEquals(thePlayer.getMoney(), START_AMOUNT, ERROR);
		assertTrue(thePlayer.getPosition() == (START_POSITION + nearestRailroadPos));
	}
	
	@Test
	public void utilityTest() {
		theCard = new Card(LAME_DESCRIPTION, UTILITY_SCRIPT);
		
		theCard.runScript(theGame);
		
		// find the nearest railroad
		Integer nearestUtilityPos = null;
		for (int n = START_POSITION; n < theBoard.getNumTiles(); n++) {
			ITile t = theBoard.getTileAt(n);
			
			if (t.getTileType() == TileType.RAILROAD) {
				nearestUtilityPos = n;
			}
		}
		
		// verify correct changes
		assertEquals(thePlayer.getMoney(), START_AMOUNT, ERROR);
		assertTrue(thePlayer.getPosition() == (START_POSITION + nearestUtilityPos));
	}

	@After
	public void tearDown() {
		theCard = null;
		
		otherPlayers = null;
		thePlayer = null;
		theBoard = null;
		theGame = null;
	}
	
}
