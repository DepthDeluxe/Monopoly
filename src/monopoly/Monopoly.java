package monopoly;

import java.util.LinkedList;

import monopoly.tiles.*;
import monopoly.xml.TileLoader;
import monopoly.xml.CardLoader;
import monopoly.xml.XMLIO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import monopoly.xml.ISerializable;

class NoPlayersException extends RuntimeException {
	private static final long serialVersionUID = -4783179608622400629L;

	public NoPlayersException() { super("There are not enough players added to the game!"); }	
}

public class Monopoly implements ISerializable {
	//
	// Member Variables
	//
	
	private LinkedList<Player> players;
	private int curPlayerIndex;
	private Player currentPlayer;
	
	private Dice dice;
	private Board board;
	
	private MonopolyModelState modelState;
	
	//
	// Constructors
	//
	
	public Monopoly(String boardFilename, String chanceFilename, String commChestFilename) {
		// init player stuff
		players = new LinkedList<Player>();
		curPlayerIndex = 0;
		
		// load the dice
		dice = new Dice();
		
		// load the board components from files
		ITile[] boardTiles = TileLoader.loadFromXML(boardFilename);
		
		// only load the chance deck if the file was specified
		CardDeck chanceDeck = null;
		if (chanceFilename != null) {
			chanceDeck = new CardDeck(CardLoader.loadFromFile(chanceFilename));
		}
		
		// only load the com chance deck if the file was specified
		CardDeck commChestDeck = null;
		if (commChestFilename != null) {
			commChestDeck = new CardDeck(CardLoader.loadFromFile(commChestFilename));
		}
		
		// load the board
		board = new Board(boardTiles, chanceDeck, commChestDeck);
		
		modelState = MonopolyModelState.PLAYING;
	}
	
	//
	// Main Functions
	//
	
	public boolean nextMove() {
		if (players.size() < 2) {
			throw new NoPlayersException();
		}
		
		// since currentPlayer changes after the handle functions called, there
		// needs to be another reference to the current player
		Player playerThisRun = currentPlayer;
		
		if(playerThisRun.isBankrupt())
		{
			incrementPlayer();
			return true;
		}
		
		int originalNumGoTimes = playerThisRun.getNumTimesPassedGo();
		
		// if the model is in PLAYER_MOVED state that means the player already
		// rolled the diced and moved before but was moved by a chance card.
		// Don't do anything if the model is in a different state.
		switch (modelState) {
		case PLAYING:
			// roll the dice and cycle the player
			dice.roll();
			
			// get the current player and calculate distance
			int distance = dice.getFirstValue() + dice.getSecondValue();
			
			// no longer in jail if they rolled doubles
			if (dice.getFirstValue() == dice.getSecondValue()) {
				currentPlayer.setInJail(false);
			}
			
			// player will not move if they are in jail
			currentPlayer.move(distance);
			
		// this gets run when game is in PLAYING or PLAYER_MOVED state
		case PLAYER_MOVED:
			
			// run the landOn of the current tile
			ITile tile = board.getTileAt(currentPlayer.getPosition());
			modelState = tile.landOn(currentPlayer);
			
			break;
			
		// in other cases, don't do anything
		default:
		}
		
		// calculate the amount owed to the player and transfer it to him
		int timesPassedGoDiff = playerThisRun.getNumTimesPassedGo() - originalNumGoTimes;
		double moneyOwedToPlayer = board.getGoTile().getGoMoney() * timesPassedGoDiff;
		playerThisRun.giveMoney(moneyOwedToPlayer);
		
		// return true if there isn't any other action required by
		// the controller
		return (modelState == MonopolyModelState.PLAYING);
	}
	
	public boolean handleBuyRequest(boolean buying) {
		if (modelState != MonopolyModelState.BUY_REQUEST) {
			return false;
		}
		
		boolean success = true;
		if (buying) {
			Property propertyToBuy = board.getPropertyAt(currentPlayer.getPosition());
			success = currentPlayer.buyProperty(propertyToBuy);
		}
		
		// reset the model state and increment the player
		modelState = MonopolyModelState.PLAYING;
		incrementPlayer();
		
		return success;
	}
	
	public boolean handleCardPull(TileType cardType) {
		// get the card deck
		CardDeck theDeck;
		if (cardType == TileType.CHANCE) {
			theDeck = board.getChanceDeck();
		}
		else if (cardType == TileType.COMMUNITY_CHEST) {
			theDeck = board.getCommunityChestDeck();
		}
		else {
			throw new RuntimeException("Error: Invalid card type!");
		}
		
		// get the pulled card from the selected deck
		Card pulledCard = theDeck.getTopCard();
		
		// run the script on the card
		modelState = pulledCard.runScript(this);
		
		// increment the card deck
		board.getChanceDeck().nextCard();
		
		// only increment player if the model state is PLAYING
		if (modelState == MonopolyModelState.PLAYING) {
			incrementPlayer();		
			return true;
		}
		
		// return false if the game is not done
		return false;
	}
	
	public boolean handleIdleState() {
		// make sure the modelState is playing
		modelState = MonopolyModelState.PLAYING;
		
		// and increment the player
		incrementPlayer();
		
		return true;
	}
	
	public void addPlayer(Player p) {
		// add the player to the list of current players
		players.add(p);
		
		// if this is the first player, they are the current player
		if (players.size() == 1) {
			currentPlayer = p;
		}
	}
	
	//
	// Getters
	//
	
	public Player[] getPlayers() {
		return players.toArray(new Player[0]);
	}
	
	public int getCurrentPlayerIndex() {
		return curPlayerIndex;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Dice getDice() {
		return dice;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public MonopolyModelState getModelState() {
		return modelState;
	}
	
	//
	// Private Functions
	//
	
	private void incrementPlayer() {		
		// increment the current player
		curPlayerIndex++;
		if (curPlayerIndex == players.size()) {
			curPlayerIndex = 0;
		}
		
		// assign the value of the current player
		currentPlayer = players.get(curPlayerIndex);
	}
	
	//
	// ISerializable Implementation
	//
	
	@Override
	public Element serialize(Document doc) {
		// create the root element
		Element monopolyElement = doc.createElement("Monopoly");
		
		// players
		Element playersElement = doc.createElement("Players");
		for (Player p : players) {
			// serialize and append the child
			Element pe = p.serialize(doc);
			playersElement.appendChild(pe);
		}
		monopolyElement.appendChild(playersElement);
		
		// current player index
		Element curPlayerIndexElement = XMLIO.classMemberToElement(
				"CurPlayerIndex", Integer.toString(curPlayerIndex), doc);
		monopolyElement.appendChild(curPlayerIndexElement);
		
		// the board
		Element boardElement = board.serialize(doc);
		monopolyElement.appendChild(boardElement);
		
		// the model state
		Element mmsElement = XMLIO.classMemberToElement(
				"MonopolyModelState", modelState.toString(), doc);
		monopolyElement.appendChild(mmsElement);
		
		return monopolyElement;
	}
	
	@Override
	public void deSerialize(Element rootNode, Object outsideParam) {
		// load players
		players.clear();
		Element playersElement = (Element)rootNode.getElementsByTagName("Players").item(0);
		NodeList playerNodes = playersElement.getElementsByTagName("Player");
		for (int n = 0; n < playerNodes.getLength(); n++) {
			// get the next player element
			Element playerElement = (Element)playerNodes.item(n);
			
			// create a player and deserialize him with the board
			Player p = new Player(null, 0, board.getNumTiles());
			p.deSerialize(playerElement, board);
			
			// add him to the players list
			players.add(p);
		}
		
		String tempStr;
		
		// curPlayerIndex
		tempStr = XMLIO.getChildValue("CurPlayerIndex", rootNode);
		curPlayerIndex = Integer.parseInt(tempStr);
		
		// currentPlayer - from curPlayerIndex
		currentPlayer = players.get(curPlayerIndex);
		
		// no dice (lol i'm punny)
		
		// the model state
		tempStr = XMLIO.getChildValue("ModelState", rootNode);
		MonopolyModelState.valueOf(tempStr);
	}
}
