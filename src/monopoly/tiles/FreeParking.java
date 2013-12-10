package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

public class FreeParking implements ITile {
	//
	// Member Variables
	//
	
	private double moneyInPot;
	private double nextPotStart;
	
	private static final TileType TILE_TYPE = TileType.FREE_PARKING;
	
	//
	// Constructors
	//
	
	public FreeParking(double potStartAmount) {
		this.moneyInPot = potStartAmount;
		this.nextPotStart = 2 * this.moneyInPot;	// amount in pot doubles every time
	}
	
	//
	// Main Methods
	//
	
	public void addToPot(double amount) {
		moneyInPot += amount;
	}
	
	public double getMoneyInPot() {
		return moneyInPot;
	}
	
	//
	// ITile implementation
	//
	
	@Override
	public MonopolyModelState landOn(Player p) {
		// give player the money that is in the pot
		p.giveMoney(moneyInPot);
		
		// fill the pot with the next starting amount,
		// double the starting amount for next time
		moneyInPot = nextPotStart;
		nextPotStart *= 2;
		
		return MonopolyModelState.PLAYING;
	}
	
	@Override
	public TileType getTileType() {
		return TILE_TYPE;
	}
}
