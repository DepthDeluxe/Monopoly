package monopoly;

import java.util.ArrayList;

public class Monopoly {
	//
	// Member Variables
	//
	
	private ArrayList<Player> players;
	private int currentPlayer;
	
	private Board board;
	
	//
	// Constructors
	//
	
	public Monopoly() {
		// init player stuff
		players = new ArrayList<Player>();
		currentPlayer = 0;
		
		board = new Board();
	}
}
