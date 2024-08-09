package autodeskproj;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import autodeskproj.TestComponents.BaseTest;
import autodeskproj.pageobjects.CalculatorPage;
import java.io.IOException;


//This file verifies if all the keys of calculator are operational

public class VerifyCalculatorKeys extends BaseTest{
	CalculatorPage calc;
	
	@BeforeMethod
	public void setUp() throws IOException {
		calc = launchCalculator();
	}

	@Test(description = "Verifies if AC button visible")
	public void verifyDefaultACModeOnLCD() {
		boolean isACVisible = calc.getClearMemoryButton().isDisplayed();
		Assert.assertTrue(isACVisible, "AC button should be visible by default");
		
	}
	
	
	 @Test(description = "Verifies if CE button visible")
	    public void verifyCEOnClickingAC() {
	        calc.getClearMemoryButton().click();
	        boolean isCEVisible = calc.isCEButtonVisible();
	        Assert.assertTrue(isCEVisible, "CE should be displayed after clicking AC");
	    }
	@Test(description = "Verifies all keys are functional")
	public void verifyAllDigitsAndOPLCD() {
		  String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "−", "×","."};

	        for (String digit : digits) {
	            calc.clickDigit(digit.charAt(0));
	            String displayText = calc.getLCDDisplay().getText();
	            if(digit.equals("−")) {
	            	Assert.assertTrue(displayText.contains("-"), "LCD should display the digit " + digit);
	            }
	            else {
	            Assert.assertTrue(displayText.contains(digit), "LCD should display the digit " + digit);
	            }
	            calc.getClearEntry().click();
	        }
	    
	}
	
	
	
	
	
	@AfterMethod
    public void tearDown() {
    	driver.close();
    }

}
