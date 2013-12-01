package monopoly.tiles;

import monopoly.Board;
import monopoly.Player;
import monopoly.MonopolyModelState;

public class GoToJailTile implements ITile {
	// the ITile tile type
	private static final TileType TILE_TYPE = TileType.GO_TO_JAIL;
	
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
	
	@Override
	public TileType getTileType() {
		return TILE_TYPE;
	}
}
