package test;

import static org.junit.Assert.*;

import org.junit.Test;

import monopoly.Dice;

public class DiceTest {

	@Test
	public void test() {
		Dice dice = new Dice();
		
		int numTests = 10000;
		for (int n = 0; n < numTests; n++) {
			dice.roll();
			int firstVal = dice.getFirstValue();
			int secondVal = dice.getSecondValue();
			
			// make sure they are good values
			assertTrue(firstVal > 0 && firstVal < 7);
			assertTrue(secondVal > 0 && secondVal < 7);
		}
	}

}
