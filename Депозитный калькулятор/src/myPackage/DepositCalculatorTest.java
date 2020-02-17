package myPackage;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class DepositCalculatorTest {

	@Test
	void testGetProfit() {
		Assert.assertEquals("100,00", DepositCalculator.getProfit("1000", "1 ���", "10"));
		Assert.assertEquals("200,00", DepositCalculator.getProfit("2000", "1 ���", "10"));
	}
}
