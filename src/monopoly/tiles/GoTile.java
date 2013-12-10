package monopoly.tiles;

import monopoly.MonopolyModelState;
import monopoly.Player;

public class GoTile implements ITile {
	//
	// Member Variables
	//
	
	private double goMoney;
	
	//
	// Constructors
	//
	
	public GoTile(double goMoney) {
		this.goMoney = goMoney;
	}
	
	//
	// Getters
	//
	
	public double getGoMoney() {
		return goMoney;
	}
	
	//
	// ITile Implementation
	//
	
	@Override
	public MonopolyModelState landOn(Player p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TileType getTileType() {
		// TODO Auto-generated method stub
		return null;
	}

}
