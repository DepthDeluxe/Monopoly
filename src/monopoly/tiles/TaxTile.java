package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

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
	
	public TaxTile(double taxRate, double taxMinimum) {
		this.taxRate = taxRate;
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
		p.takeMoney(amountOwed);
		
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
