package monopoly;

public class PlayerBankruptException extends Exception {
	//
	// Member Variables
	//
	
	private double moneyRemaining;
	
	//
	// Constructors
	//
	
	public PlayerBankruptException(double moneyRemaining) {
		super();
	}
	
	//
	// Getters
	//
	
	public double getMoneyRemaining() {
		return moneyRemaining;
	}
}
