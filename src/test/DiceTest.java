package test;

import static org.junit.Assert.*;

import org.junit.Test;

import monopoly.Dice;

public class DiceTest {

	@Test
	public void test() {
		int numTests = 10000;
		for (int n = 0; n < numTests; n++) {
			Dice.roll();
			int firstVal = Dice.getFirstValue();
			int secondVal = Dice.getSecondValue();
			
			// make sure they are good values
			assertTrue(firstVal > 0 && firstVal < 7);
			assertTrue(secondVal > 0 && secondVal < 7);
		}
	}

}
