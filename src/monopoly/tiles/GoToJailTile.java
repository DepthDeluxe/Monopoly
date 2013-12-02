package monopoly.tiles;

import monopoly.Player;
import monopoly.MonopolyModelState;

public class GoToJailTile implements ITile {
	//
	// Member Variables
	//
	
	private int destination;
	
	// the ITile tile type
	private static final TileType TILE_TYPE = TileType.GO_TO_JAIL;
	
	//
	// Constructors
	//
	
	public GoToJailTile(int destination) {
		this.destination = destination;
	}
	
	//
	// Getters
	//
	
	public int getDestination() {
		return destination;
	}
	
	//
	// ITile Implementation
	//
	
	@Override
	public MonopolyModelState landOn(Player p) {
		// move the player to the destination
		p.moveTo(destination);
		
		return MonopolyModelState.PLAYING;
	}
	
	@Override
	public TileType getTileType() {
		return TILE_TYPE;
	}
}
