package monopoly;

public class Player {
	//
	// Member Variables
	//
	
	private int position;
	private double time;
	
	//
	// Constructors
	//
	
	public Player(int startTime) {
		position = 0;
		
		this.time = startTime;
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
	
	public void transferTime(double amount) {
		this.time += amount;
	}

	//
	// Getters
	//
	
	public double getCurrentTime() {
		return time;
	}
}
