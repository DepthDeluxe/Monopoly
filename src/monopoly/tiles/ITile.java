package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

public interface ITile {
	MonopolyModelState landOn(Player p);
}
