package monopoly.tiles;

import monopoly.Board;
import monopoly.Player;
import monopoly.MonopolyModelState;

public class GoToJailTile implements ITile {
	//
	// Constructor
	//
	
	public GoToJailTile() {
		
	}
	
	//
	// ITile Implementation
	//
	
	@Override
	public MonopolyModelState landOn(Player p) {
		p.moveTo(Board.JAIL);
		
		return MonopolyModelState.PLAYING;
	}
}
