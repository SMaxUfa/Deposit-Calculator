package myPackage;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class DepositCalculatorTest {

	@Test
	void testGetProfit() {
		Assert.assertEquals("100,00", DepositCalculator.getProfit("1000", "1 год", "10"));
		Assert.assertEquals("200,00", DepositCalculator.getProfit("2000", "1 год", "10"));
		Assert.assertEquals(DepositCalculator.ERROR_MESSAGE, DepositCalculator.getProfit("2000", "qwe", "10"));
		Assert.assertEquals(DepositCalculator.ERROR_MESSAGE, DepositCalculator.getProfit("", "", ""));
	}
}
