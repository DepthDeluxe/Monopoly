package monopoly;

import monopoly.tiles.TileType;

class InvalidCardScriptException extends RuntimeException {
	private static final long serialVersionUID = 2482137175816724626L;

	public InvalidCardScriptException() { super("This CardScript is invalid!"); }
}

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
		this.description = description;
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
	
	public MonopolyModelState runScript(Monopoly theGame) {
		// get board and current player references
		Board theBoard = theGame.getBoard();
		Player currentPlayer = theGame.getCurrentPlayer();
		
		// get the arguments, if it has not length, there is a problem w/the script
		String[] args = cardScript.split(" ");
		if (args.length == 0) {
			throw new InvalidCardScriptException();
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
			
			// try to remove the money
			try {
				currentPlayer.takeMoney(amount);
			}
			catch (PlayerBankruptException e) {
				// put however much player could pay into the pot
				theBoard.getFreeParking().addToPot(e.getAmountPaid());
				
				return MonopolyModelState.PLAYER_BANKRUPT;
			}
			
			theBoard.getFreeParking().addToPot(amount);
			
			break;
			
		case "collectfromall":
			// get amount to pay
			amount = Double.parseDouble(args[1]);
			
			// give to all players
			for (Player p : theGame.getPlayers()) {
				try {
					p.takeMoney(amount);
				}
				catch (PlayerBankruptException e) {
					throw new RuntimeException("Not sure how to handle other player bankrupt while trying to pay a community chest guy...");
				}
				currentPlayer.giveMoney(amount);
			}
			
			break;
			
		case "paytoall":
			// get amount to pay
			amount = Double.parseDouble(args[1]);
			
			// give to all players
			for (Player p : theGame.getPlayers()) {
				try {
					currentPlayer.takeMoney(amount);
				}
				// if player can't pay the money owed, he will only owe money
				// to the first person he can't pay (whatevs..)
				catch (PlayerBankruptException e) {
					// pay the person the amount he has
					p.giveMoney(e.getAmountPaid());
					
					// add this guy as the creditor
					currentPlayer.setCreditor(p);
					
					// return the PLAYER_BANKRUPT state
					return MonopolyModelState.PLAYER_BANKRUPT;
				}
				p.giveMoney(amount);
			}
			
			break;
			
		case "move":
			// get distance to move
			int distance = Integer.parseInt(args[1]);
			
			currentPlayer.move(distance);
			
			return MonopolyModelState.PLAYER_MOVED;
			
		case "utility":
			// get the nearest utility location
			int startLocation = currentPlayer.getPosition();
			int nearestUtilLocation = getNearest(TileType.UTILITY, startLocation, theBoard);
			
			// if the startLocation is after the util location, give money for passing go
			if (startLocation > nearestUtilLocation) {
				double amountOwed = theBoard.getGoTile().getGoMoney();
				
				// give player the money
				currentPlayer.giveMoney(amountOwed);
			}
			
			// and move there
			currentPlayer.moveTo(nearestUtilLocation);
			
			return MonopolyModelState.PLAYER_MOVED;
			
		case "railroad":
			// get the nearest railroad location
			startLocation = currentPlayer.getPosition();
			int nearestRailroadLocation = getNearest(TileType.RAILROAD, startLocation, theBoard);
			
			// and move there
			currentPlayer.moveTo(nearestRailroadLocation);
			
			// if the startLocation is after the util location, give money for passing go
			if (startLocation > nearestRailroadLocation) {
				double amountOwed = theBoard.getGoTile().getGoMoney();
				
				// give player the money
				currentPlayer.giveMoney(amountOwed);
			}
			
			return MonopolyModelState.PLAYER_MOVED;
			
		case "jail":
			// just move the player, don't collect money
			currentPlayer.moveTo(theBoard.getJailLocation());
			
			return MonopolyModelState.PLAYER_MOVED;
		
		// if anything else shows up, the script was invalid!
		default:
			throw new InvalidCardScriptException();
		}
		
		return MonopolyModelState.PLAYING;
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
