package monopoly;

public class PlayerBankruptException extends Exception {
	//
	// Member Variables
	//
	
	private double amountPaid;
	
	//
	// Constructors
	//
	
	public PlayerBankruptException(double amountPaid) {		
		super();
		
		this.amountPaid = amountPaid;
	}
	
	//
	// Getters
	//
	
	public double getAmountPaid() {
		return amountPaid;
	}
}
