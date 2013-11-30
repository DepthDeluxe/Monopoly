package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

enum CardType {
	CHANCE, COMMUNITY_CHEST;
}

public class CardTile implements ITile {
	//
	// Member Variables
	//
	
	CardType cardType;
	
	//
	// Constructors
	//
	
	public CardTile(CardType cardType) {
		this.cardType = cardType;
	}
	
	//
	// ITile Implementation
	//

	@Override
	public MonopolyModelState landOn(Player p) {
		// send appropriate flag to the controller to let it
		// know that a chance or community chest card must be drawn
		if (cardType == CardType.CHANCE) {
			return MonopolyModelState.CHANCE;
		}
		else {
			return MonopolyModelState.COMMUNITY_CHEST;
		}
	}
}
