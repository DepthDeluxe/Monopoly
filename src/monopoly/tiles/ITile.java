package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

public interface ITile {
	// normal use landOn function
	MonopolyModelState landOn(Player p);
	
	// returns the type of tile
	TileType getTileType();	
}
