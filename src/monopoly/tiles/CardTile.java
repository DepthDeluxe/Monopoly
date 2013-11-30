package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

public class CardTile implements ITile {
	//
	// Member Variables
	//
	
	MonopolyModelState cardType;
	
	//
	// Constructors
	//
	
	public CardTile(MonopolyModelState cardType) {
		this.cardType = cardType;
	}
	
	//
	// ITile Implementation
	//

	@Override
	public MonopolyModelState landOn(Player p) {
		// send appropriate flag to the controller to let it
		// know that a chance or community chest card must be drawn
		return cardType;
	}
}
