package monopoly;

import monopoly.tiles.Railroad;
import monopoly.tiles.Utility;

public class Card {
	//
	// Member Variables
	//
	
	String description;
	String cardScript;
	
	//
	// Constructor
	//
	
	public Card(String description, String cardScript) {
		this.cardScript = cardScript;
	}
	
	//
	// Getters
	//
	
	public String getDescription() {
		return description;
	}
	
	public String getScript() {
		return cardScript;
	}
	
	public static void runScript(Monopoly theGame, String theScript) {
		// get board and current player references
		Board theBoard = theGame.getBoard();
		Player currentPlayer = theGame.getCurrentPlayer();
		
		// split up the script into lines and iterate through them
		String[] lines = theScript.split("\n");
		for (String line : lines) {
			// get the arguments
			String[] args = line.split(" ");
			if (args.length == 0) {
				continue;
			}
			
			// interpret the command
			String command = args[0];
			switch (command) {
			case "collect":
				// get the amount to pay
				double amount = Double.parseDouble(args[1]);
				
				// and give money to the player
				currentPlayer.giveMoney(amount);
				break;
				
			case "pay":
				// get the amount to pay
				amount = Double.parseDouble(args[1]);
				
				// and transfer the money from the player to Free Parking
				currentPlayer.takeMoney(amount);
				theBoard.getFreeParking().addToPot(amount);
				
				break;
				
			case "collectfromall":
				// get amount to pay
				amount = Double.parseDouble(args[1]);
				
				// give to all players
				for (Player p : theGame.getPlayers()) {
					p.takeMoney(amount);
					currentPlayer.giveMoney(amount);
				}
				
				break;
				
			case "paytoall":
				// get amount to pay
				amount = Double.parseDouble(args[1]);
				
				// give to all players
				for (Player p : theGame.getPlayers()) {
					currentPlayer.takeMoney(amount);
					p.giveMoney(amount);
				}
				
				break;
				
			case "move":
				// get distance to move
				int distance = Integer.parseInt(args[1]);
				
				currentPlayer.move(distance);
				
				break;
				
			case "utility":
				// find the nearest utility AHEAD of the player
				int startLocation = currentPlayer.getPosition();
				
				for (int n = startLocation; n != (startLocation-1); n++) {
					// go back to 0 if board over
					if (n == theBoard.getNumTiles()) {
						n = 0;
					}
					
					// try to cast as utility, if it works, then move the player
					try {
						Utility castedU = (Utility)theBoard.getTileAt(n);
						currentPlayer.moveTo(n);
					}
					// if it doesn't work, don't do anything
					catch (ClassCastException e) {
						
					}
				}
				
				break;
				
			case "railroad":
				// find the nearest railroad AHEAD of the player
				startLocation = currentPlayer.getPosition();
				
				for (int n = startLocation; n != (startLocation-1); n++) {
					// go back to 0 if board over
					if (n == theBoard.getNumTiles()) {
						n = 0;
					}
					
					// try to cast as railroad, if it works, then move the player
					try {
						Railroad castedR = (Railroad)theBoard.getTileAt(n);
						currentPlayer.moveTo(n);
					}
					catch (ClassCastException e) {
						// if it doesn't work, don't do anything						
					}
				}
				
				break;
				
			case "jail":
				
				startLocation = currentPlayer.getPosition();
				currentPlayer.moveTo(theBoard.getJailLocation());
				
				// don't get $200 for passing go...
				if (startLocation > currentPlayer.getPosition()) {
					currentPlayer.takeMoney(Player.GO_MONEY);
				}
				
				break;
				
			}
			
		}
	}
}
