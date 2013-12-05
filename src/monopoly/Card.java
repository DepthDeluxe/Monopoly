package monopoly;

import monopoly.tiles.Railroad;
import monopoly.tiles.TileType;
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
	
	public void runScript(Monopoly theGame) {
		// get board and current player references
		Board theBoard = theGame.getBoard();
		Player currentPlayer = theGame.getCurrentPlayer();
		
		// split up the script into lines and iterate through them
		String[] lines = cardScript.split("\n");
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
				// get the nearest utility location
				int startLocation = currentPlayer.getPosition();
				int nearestUtilLocation = getNearest(TileType.UTILITY, startLocation, theBoard);
				
				// and move there
				currentPlayer.moveTo(nearestUtilLocation);
				
				break;
				
			case "railroad":
				// get the nearest railroad location
				startLocation = currentPlayer.getPosition();
				int nearestRailroadLocation = getNearest(TileType.RAILROAD, startLocation, theBoard);
				
				// and move there
				currentPlayer.moveTo(nearestRailroadLocation);
				
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
	
	private int getNearest(TileType tileType, int startLocation, Board theBoard) {
		int nearestLocation = -1;
		
		for (int n = startLocation+1; n < theBoard.getNumTiles(); n++) {					
			// if tile the right type, then move the player to that position
			if (theBoard.getTileAt(n).getTileType() == TileType.UTILITY) {
				nearestLocation = n;
				break;
			}
		}
		
		// continue the search at the beginning of the board if new utility not found
		if (nearestLocation == -1) {
			for (int n = 0; n < startLocation; n++) {
				// if the property is the utility, then move the player to that position
				if (theBoard.getTileAt(n).getTileType() == TileType.UTILITY) {
					nearestLocation = n;
					break;
				}
			}
		}
		
		return nearestLocation;
	}
}
