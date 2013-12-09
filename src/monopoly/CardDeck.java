package monopoly;

public class CardDeck {
	private Card[] cards;
	
	private Card topCard;
	private int  curCardIndex;
	
	public CardDeck(Card[] cards) {
		this.cards = cards;
		
		topCard = cards[0];
		curCardIndex = 0;
	}
	
	public Card getTopCard() {
		return topCard;
	}
	
	public void nextCard() {
		// increment the card counter.  If it reached the end,
		// reset to zero
		curCardIndex++;
		if (curCardIndex == cards.length) {
			curCardIndex = 0;
		}
		topCard = cards[curCardIndex];
	}
}
