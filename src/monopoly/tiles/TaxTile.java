package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

import monopoly.PlayerBankruptException;

public class TaxTile implements ITile {

	//
	// Member Variables
	//
	
	private double taxRate;
	private double taxMinimum;
	
	FreeParking freeParking;
	
	//
	// Constructors
	//
	
	public TaxTile(double taxRate, double taxMinimumPct) {
		this.taxRate = taxRate / 100;		// save the decimal value and not the percentage!
		this.taxMinimum = taxMinimum;
		
		// free parking has to be assigned to after the game has
		// been initialized
		freeParking = null;
	}
	
	//
	// Main Functions
	//
	
	public void setFreeParking(FreeParking fp) {
		this.freeParking = fp;
	}
	
	//
	// Getters
	//
	
	public double getTaxRate() {
		return taxRate;
	}
	
	public double taxMinimum() {
		return taxMinimum;
	}
	
	//
	// ITile Implementation
	//
	
	@Override
	public MonopolyModelState landOn(Player p) {
		// calculate the amount the player owes, 
		// if less than minimum, charge the minimum
		double amountOwed = taxRate * p.getMoney();
		
		if (amountOwed < taxMinimum) {
			amountOwed = taxMinimum;
		}
		
		// take the money
		try {
			p.takeMoney(amountOwed);
		}
		// if he can't pay...
		catch (PlayerBankruptException e) {
			// pay free parking what player can if it exists...
			if (freeParking != null) {
				freeParking.addToPot(e.getAmountPaid());
			}
			
			// let controller know that the government screwed him
			return MonopolyModelState.PLAYER_BANKRUPT;
		}
			
		// add the money into free parking if it has been saved
		if (freeParking != null) {
			freeParking.addToPot(amountOwed);
		}
		
		return MonopolyModelState.PLAYING;
	}

	@Override
	public TileType getTileType() {
		return TileType.TAX;
	}

}
