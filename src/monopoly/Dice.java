package monopoly;

import java.util.Random;

public class Dice {
	//
	// Static Members
	//
	
	private int firstValue, secondValue;
	private Random rand;
	
	private static final int NUM_FACES = 6;
	
	//
	// Constructors
	//
	
	public Dice() {
		rand = new Random();
		
		// populate firstValue and secondValue
		roll();
	}
	
	//
	// Main Functions
	//
	
	public void roll() {
		// get random values, add one becuase nextInt is
		// zero inclusive
		firstValue = (rand.nextInt(NUM_FACES) + 1);
		secondValue = (rand.nextInt(NUM_FACES) + 1);
	}
	
	//
	// Getters
	//
	
	public int getFirstValue() {
		return firstValue;
	}
	
	public int getSecondValue() {
		return secondValue;
	}
	
	public int getSum() {
		return firstValue + secondValue;
	}
}
