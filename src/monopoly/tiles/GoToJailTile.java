package monopoly.tiles;

import monopoly.Board;
import monopoly.Player;
import monopoly.MonopolyModelState;

public class GoToJailTile implements ITile {
	//
	// Member Variables
	//
	
	private Board theBoard;
	
	// the ITile tile type
	private static final TileType TILE_TYPE = TileType.GO_TO_JAIL;
	
	//
	// Constructors
	//
	
	public GoToJailTile() {
		theBoard = null;
	}
	
	public GoToJailTile(Board theBoard) {
		this.theBoard = theBoard;
	}
	
	//
	// Setters
	//
	
	public void setBoard(Board newBoard) {
		this.theBoard = newBoard;
	}
	
	//
	// ITile Implementation
	//
	
	@Override
	public MonopolyModelState landOn(Player p) {
		if (theBoard == null) {
			p.moveTo(Board.DEFAULT_JAIL_LOCATION);
		}
		else {
			p.moveTo(theBoard.getJailLocation());			
		}
		return MonopolyModelState.PLAYING;
	}
	
	@Override
	public TileType getTileType() {
		return TILE_TYPE;
	}
}
