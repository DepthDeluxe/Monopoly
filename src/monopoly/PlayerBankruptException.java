package monopoly;

public class PlayerBankruptException extends Exception {
	//
	// Member Variables
	//
	
	private double amountPaid;
	
	// serial UID
	private static final long serialVersionUID = -2930641786820770912L;
	
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
