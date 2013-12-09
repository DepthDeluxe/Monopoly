package monopoly.tiles;

import monopoly.Player;
import monopoly.MonopolyModelState;

public class Property implements ITile {
	//
	// Member Variables
	//
	
	private String name;
	private double price;
	private double rent;
	private int group;
	
	private Player owner;
	
	private boolean isMortgaged;
	private double mortgagedValue;
	
	// The ITile tile type
	private static final TileType TILE_TYPE = TileType.PROPERTY;
	
	//
	// Constructors
	//
	
	public Property(String name, double price, double rent, double mortgagedValue, int group) {
		this.name = name;
		this.price = price;
		this.rent = rent;
		this.mortgagedValue = mortgagedValue;
		this.group = group;
		
		this.owner = null;
		
		this.isMortgaged = false;
	}
	
	//
	// Main Functions
	//
	
	public void setOwner(Player newOwner) {
		this.owner = newOwner;
		
		// reset mortgaged status
		isMortgaged = false;
	}
	
	public boolean chargeRent(Player player) {
		// can't charge rent if property is unowned or mortaged
		if (owner == null || isMortgaged == true) {
			return false;
		}
		
		// transfer the rent from the player that landed on the
		// tile to the owner of the tile
		player.takeMoney(getRent());
		owner.giveMoney(getRent());
		
		return true;
	}
	
	public boolean mortgage() {
		// can't mortgage an unowned house,
		// can't mortgage an already mortgaged house
		if (owner == null || isMortgaged == true) {
			return false;
		}
		
		// give the owner the mortgaged value
		owner.giveMoney(mortgagedValue);
		owner.changeMortgagedProperties(1);
		
		isMortgaged = true;
		return true;
	}
	
	public boolean unmortgage() {
		// can't unmortgage an unowned house,
		// can't unmortgage an already unmortgaged house
		if (owner == null || isMortgaged == false) {
			return false;
		}
		
		// try to take the mortgaged value + 10%
		boolean success = owner.takeMoney(1.1 * mortgagedValue);
		
		if (success) {
			isMortgaged = false;
			owner.changeMortgagedProperties(-1);
		}
		
		// returns true if the house was unmortgaged
		return !isMortgaged;
	}
	
	//
	// Getters
	//
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getGroup() {
		return group;
	}
	
	public double getRent() {
		// if there is no owner, rent price is simple
		if (owner == null) {
			return rent;
		}
		
		// find number of properties that owner has in each group
		int numPropertiesInGroup = owner.getNumPropertiesInGroup(group);
		
		// charge different rent based on the number of properties owned in that group
		switch (numPropertiesInGroup) {
		case 1:
			return rent;
		
		case 2:
			return 2 * rent;
			
		// 3 or more properties in same group
		default:
			return 4 * rent;
		}
	}
	
	public boolean isOwned() {
		return (owner != null);
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public boolean isMortgaged() {
		return isMortgaged;
	}
	
	public double getMortgagedValue() {
		return mortgagedValue;
	}
	
	//
	// ITile Implementation
	//
	
	@Override
	public MonopolyModelState landOn(Player p) {
		// if the property is unowned, put game in BUY_REQUEST state
		if (owner == null) {
			return MonopolyModelState.BUY_REQUEST;
		}
		
		// if player landed on owned property, they owe rent
		else {
			// get the amount to transfer between
			double amountToTransfer = getRent();
			
			// try to take the money from the player who landed
			boolean success = p.takeMoney(amountToTransfer);
			if (!success) {
				// if not enough money, transfer rest of money from player and give to owner
				double moneyRemaining = p.getMoney();
				p.takeMoney(moneyRemaining);
				p.giveMoney(moneyRemaining);
				
				// let controller know a player has lost
				return MonopolyModelState.PLAYER_OUT_OF_MONEY;
			}
			
			// give the money to the owner
			owner.giveMoney(amountToTransfer);
			
			// continue playing as usual if transfer worked
			return MonopolyModelState.PLAYING;
		}
	}
	
	@Override
	public TileType getTileType() {
		return TILE_TYPE;
	}
}
