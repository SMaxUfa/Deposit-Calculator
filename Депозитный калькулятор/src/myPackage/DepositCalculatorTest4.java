package myPackage; 

import org.junit.Assert; 
import org.junit.jupiter.api.Test; 

class DepositCalculatorTest4 { 

	@Test 
	void testGetProfit() { 
		Assert.assertEquals("120,00", DepositCalculator.getProfit("1000", "1 год", "12")); 
		Assert.assertEquals("440,00", DepositCalculator.getProfit("2000", "2 года", "11")); 
		Assert.assertEquals(DepositCalculator.ERROR_MESSAGE, DepositCalculator.getProfit("", "", "")); 
		Assert.assertEquals(DepositCalculator.ERROR_MESSAGE, DepositCalculator.getProfit("1000", "qwerty", "20")); 
	} 
}