package monopoly;

public class PlayerBankruptException extends Exception {
	//
	// Member Variables
	//
	
	private double amountPaid;
	
	//
	// Constructors
	//
	
	public PlayerBankruptException(double moneyRemaining) {
		super();
	}
	
	//
	// Getters
	//
	
	public double getAmountPaid() {
		return amountPaid;
	}
}
