package myPackage;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class DepositCalculatorTest3 {

	@Test
	void testGetProfit() {
		Assert.assertEquals("343,00", DepositCalculator.getProfit("2450", "2 года", "7"));
		Assert.assertEquals("259,00", DepositCalculator.getProfit("3700", "1 год", "7"));
		Assert.assertEquals(DepositCalculator.ERROR_MESSAGE, DepositCalculator.getProfit("jkl", "2 года", "7"));
		Assert.assertEquals(DepositCalculator.ERROR_MESSAGE, DepositCalculator.getProfit("1500", "1 год", "#$%"));
	}
}