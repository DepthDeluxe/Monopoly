package monopoly;

public class Player {
	//
	// Member Variables
	//
	
	private int position;
	private double money;
	
	//
	// Constructors
	//
	
	public Player(int startMoney) {
		this.money = startMoney;
		position = 0;
	}
	
	//
	// Main Functions
	//
	
	public void move(int numProperties) {
		this.position += numProperties;
		
		// go back to the beginning
		if (this.position > Board.SIZE) {
			this.position -= Board.SIZE;
		}
	}
	
	public void giveMoney(double amount) {
		this.money += amount;
	}
	
	public void takeMoney(double amount) {
		this.money -= amount;
	}

	//
	// Getters
	//
	
	public double getCurrentMoney() {
		return money;
	}
}
