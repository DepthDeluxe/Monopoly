package monopoly;

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
