package monopoly;

import java.util.Collections;
import java.util.LinkedList;

public class CardDeck {
	private LinkedList<Card> cards;
	
	private Card topCard;
	private int  curCardIndex;
	
	public CardDeck(Card[] card) 
	{
		cards = new LinkedList<Card>();
		
		for(int x = 0; x < card.length; x++)
		{
			cards.add(card[x]);
		}
		
		Collections.shuffle(cards);
		topCard = cards.get(0);
		curCardIndex = 0;
	}
	
	public Card getTopCard() {
		return topCard;
	}
	
	public void nextCard() {
		// increment the card counter.  If it reached the end,
		// reset to zero
		curCardIndex++;
		if (curCardIndex == cards.size()) {
			curCardIndex = 0;
		}
		topCard = cards.get(curCardIndex);
	}
}
