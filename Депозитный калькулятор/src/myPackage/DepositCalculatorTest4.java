package myPackage; 

import org.junit.Assert; 
import org.junit.jupiter.api.Test; 

class DepositCalculatorTest4 { 

	@Test 
	void testGetProfit() { 
		Assert.assertEquals("125,00", DepositCalculator.getProfit("5000", "3 мес€ца", "10")); 
		Assert.assertEquals("79,97", DepositCalculator.getProfit("8000", "1 мес€ц", "12")); 
		Assert.assertEquals(DepositCalculator.ERROR_MESSAGE, DepositCalculator.getProfit("230", "test", "50")); 
		Assert.assertEquals(DepositCalculator.ERROR_MESSAGE, DepositCalculator.getProfit("30", " ", "10")); 
	} 
}