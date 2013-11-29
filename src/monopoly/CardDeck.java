package monopoly;

public class CardDeck {
	Card[] cards;
	int curCardIndex;
	
	public CardDeck(Card[] cards) {
		this.cards = cards;
		
		curCardIndex = 0;
	}
	
	public Card nextCard() {
		Card currentCard = cards[curCardIndex];
		
		// increment the card counter.  If it reached the end,
		// reset to zero
		curCardIndex++;
		if (curCardIndex == cards.length) {
			curCardIndex = 0;
		}
		
		return currentCard;
	}
}
