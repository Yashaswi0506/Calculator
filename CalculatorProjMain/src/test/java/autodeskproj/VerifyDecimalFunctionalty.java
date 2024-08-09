package autodeskproj;

import java.io.IOException;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import autodeskproj.TestComponents.BaseTest;
import autodeskproj.pageobjects.CalculatorPage;

public class VerifyDecimalFunctionalty extends BaseTest {
	
CalculatorPage calc;
	
	@BeforeMethod
	public void setUp() throws IOException {
		calc = launchCalculator();
	}
	
	@Test(description = "Verify .0 is treated as 0 in result")
	public void zeroAsDecimal() {
		String exp = ".0";
		String result = calc.calculateExp(exp);
		
		
		Assert.assertEquals(result,"0" ,"The max length of result after decimal is 11");
	}
	
	@Test(description = "Verify just decimal followed by = is an error")
	public void decEquals() throws InterruptedException {
		calc.clickDigit('.');
		Thread.sleep(100);
		calc.clickDigit('=');
		String result = calc.getLCDDisplay().getText();
		
		
		Assert.assertEquals(result,"Error" ,"Decimal followed by = is an error");
	}
	
	@Test(description = "Verify decimal followed number will append 0 in the start .5 = 0.5")
	public void verifyDecimalNumber() throws InterruptedException {
		calc.clickDigit('.');
		Thread.sleep(100);
		calc.clickDigit('5');
		Thread.sleep(100);
		calc.clickDigit('=');
		Thread.sleep(100);
		String result = calc.getLCDDisplay().getText();
		
		
		Assert.assertEquals(result,"0.5" ,".5 should be equal to 0.5");
	}
	
	
	
	
	 @Test(description = "Mixed Operations with Decimals")
	    public void testMixedOperationsWithDecimals() {
	        String exp = "2.5+3.1−1.6×2÷1.2";
	        
	        Assert.assertEquals(calc.calculateExp(exp), "2.93333333333", "Result should be 2.93333333333");
	    }
	 
	 @Test(description = "Rounding Behavior of Decimals with ")
	    public void testRoundingBehavior() {
	        String exp = "10÷6";
	        String result = calc.calculateExp(exp); 
	        int roundedLastDigit = 0;

	        if (result.contains(".")) {
	            String[] parts = result.split("\\.");
	            if (parts.length > 1) {
	                int len = parts[1].length();
	                if (len >= 2) {
	                    // Get the second last character and round it
	                    char secondLastChar = parts[1].charAt(len - 2);
	                    int secondLastDigit = Character.getNumericValue(secondLastChar);
	                    // Add 1 to this digit to round it
	                    if(secondLastDigit >= 5) {
	                    roundedLastDigit = secondLastDigit + 1;
	                    }
	                    else {
	                    	roundedLastDigit = secondLastDigit;
	                    }
	                }
	            }
	        }

	        Assert.assertEquals(roundedLastDigit, 7, "With rounding, the second last digit after the decimal should be 7");
	    }
	 
	 @Test(description = "Verfy Small Decimals expression")
	    public void testSmallDecimalsExp() {
	        String exp = "0.0000001×10000000";
	        String result = calc.calculateExp(exp);
	        Assert.assertEquals(result, "1", "Result should be 1");
	    }
	 
	 @Test(description = "Addition of Very Small Decimals")
	    public void testAdditionOfSmallDecimalsEdgeCase() {
	        String exp = "0.000001+0.000002";
	        String result = calc.calculateExp(exp);
	        Assert.assertEquals(result, "0.000003", "Result should be 0.000003");
	    }

	    @Test(description = "Subtraction Resulting in Negative Decimal")
	    public void testSubtractionToNegativeDecimalEdgeCase() {
	        String exp = "2.5−3.1";
	        String result = calc.calculateExp(exp);
	        Assert.assertEquals(result, "-0.6", "Result should be -0.6");
	    }
	
	
	@AfterMethod
    public void tearDown() {
    	driver.close();
    }

}


