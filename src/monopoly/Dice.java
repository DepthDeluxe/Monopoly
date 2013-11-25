package monopoly;

import java.util.Random;

public class Dice {
	//
	// Static Members
	//
	
	private static int firstValue, secondValue;
	private static final Random rand = new Random();
	
	private static final int NUM_FACES = 6;
	
	//
	// Main Functions
	//
	
	public static void roll() {
		// get random values, add one becuase nextInt is
		// zero inclusive
		firstValue = (rand.nextInt(NUM_FACES) + 1);
		secondValue = (rand.nextInt(NUM_FACES) + 1);
	}
	
	public static int getFirstValue() {
		return firstValue;
	}
	
	public static int getSecondValue() {
		return secondValue;
	}
}
