package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

public class FreeParking implements ITile {
	//
	// Member Variables
	//
	
	private double moneyInPot;
	private double nextPotStart;
	
	public static final double POT_START_AMOUNT = 150.0;
	
	private static final TileType TILE_TYPE = TileType.FREE_PARKING;
	
	//
	// Constructors
	//
	
	public FreeParking() {
		this(POT_START_AMOUNT);
	}
	
	public FreeParking(double amountToStart) {
		this.moneyInPot = amountToStart;
		this.nextPotStart = 2 * this.moneyInPot;	// amount in pot doubles every time
	}
	
	//
	// Main Methods
	//
	
	public void addToPot(double amount) {
		moneyInPot += amount;
	}
	
	//
	// ITile implementation
	//
	
	@Override
	public MonopolyModelState landOn(Player p) {
		// give player the money that is in the pot
		p.giveMoney(moneyInPot);
		
		// fill the pot with some money, 
		moneyInPot = nextPotStart;
		nextPotStart *= 2;
		
		return MonopolyModelState.PLAYING;
	}
	
	@Override
	public TileType getTileType() {
		return TILE_TYPE;
	}
}
