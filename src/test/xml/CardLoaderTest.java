package test.xml;

import static org.junit.Assert.*;
import org.junit.Test;

import monopoly.xml.CardLoader;
import monopoly.Card;
import monopoly.CardDeck;


public class CardLoaderTest {

	private static final String FILENAME = "Chance.xml";
	
	@Test
	public void test() {
		// try to load chance
		Card[] cards = CardLoader.loadFromFile(FILENAME);
		
		// get the first card
		Card firstCard = cards[0];
		
		// makes sure it was read right
		assertTrue(firstCard.getDescription().equals("Your internet broke! Move to the broken internet space. Do not pass go. Do not collect 24 hours."));
		assertTrue(firstCard.getScript().equals("jail"));
	}

}
