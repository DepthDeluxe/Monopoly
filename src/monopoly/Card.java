package monopoly;

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
			
			String command = args[0];
			switch (command) {
			case "collect":
				double amount = Double.parseDouble(args[1]);
				currentPlayer.giveMoney(amount);
				break;
			case "pay":
				amount = Double.parseDouble(args[1]);
				currentPlayer.takeMoney(amount);
				break;
			case "collectfromall":
				amount = Double.parseDouble(args[1]);
				
				for (Player p : theGame.getPlayers()) {
					p.takeMoney(amount);
					currentPlayer.giveMoney(amount);
				}
				
				break;
			case "paytoall":
				amount = Double.parseDouble(args[1]);
				
				for (Player p : theGame.getPlayers()) {
					currentPlayer.takeMoney(amount);
					p.giveMoney(amount);
				}
				
				break;
			case "move":
				int distance = Integer.parseInt(args[1]);
				
				currentPlayer.move(distance);
				
				break;
			case "utility":
				// find the nearest utility AHEAD of the player
				int startLocation = currentPlayer.getPosition();
				
				for (int n = startLocation; n != (startLocation-1); n++) {
					// go back to 0 if board over
					if (n == Board.SIZE) {
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
					if (n == Board.SIZE) {
						n = 0;
					}
					
					// try to cast as railroad, if it works, then move the player
					try {
						Railroad castedR = (Railroad)theBoard.getTileAt(n);
						currentPlayer.moveTo(n);
					}
					// if it doesn't work, don't do anything
					catch (ClassCastException e) {
						
					}
				}
				
				break;
			case "jail":
				
				startLocation = currentPlayer.getPosition();
				currentPlayer.moveTo(Board.JAIL);
				
				// don't get $200 for passing go...
				if (startLocation > currentPlayer.getPosition()) {
					currentPlayer.takeMoney(Player.GO_MONEY);
				}
				
				break;
			}
			
		}
	}
}
