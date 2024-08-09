package autodeskproj;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import autodeskproj.TestComponents.BaseTest;
import autodeskproj.pageobjects.CalculatorPage;

public class VerifyAC_CEFunctionality extends BaseTest{
CalculatorPage calc;
	
	@BeforeMethod
	public void setUp() throws IOException {
		calc = launchCalculator();
	}

	 @Test(description = "Verify LCD when CE clicked for single digit on screen")
	    public void verifyCEForSingleDigit() {
		 	String exp = "1";
		 	calc.clickDigit(exp.charAt(0));
		 	calc.getClearEntry().click();
	        String result = calc.getLCDDisplay().getText();
	        Assert.assertEquals(result,"0", "CE should be displayed after clicking AC");
	    }
	 
	 @Test(description = "Verify CE toggles to AC when = is clicked")
	 public void verifyCEToACForEquals() {
	     // Navigate to the calculator page
	     calc.goTo();

	     // Click on digit 5
	     calc.clickDigit('5');

	     // Verify if the CE button is visible
	     boolean ceButtonVisible = calc.isCEButtonVisible();
	     Assert.assertTrue(ceButtonVisible, "CE button should be visible initially");

	     // If CE button is visible, proceed with further steps
	     calc.getClearEntry().click();
	     calc.clickDigit('2');
	     calc.clickDigit('+');
	     calc.clickDigit('4');
	     calc.clickDigit('=');

	     // Verify if the AC button is visible after clicking '='
	     boolean isACVisible = calc.getClearMemoryButton().isDisplayed();
	     Assert.assertTrue(isACVisible, "AC button should be visible after '=' is clicked");
	 }
	 @Test(description = "Verify AC button clears all input")
	    public void testACButton() {
	        calc.calculateExp("5×9");
	        calc.getClearMemoryButton().click();
	        String displayText = calc.getLCDDisplay().getText();
	        Assert.assertEquals(displayText, "0", "AC button should clear all input");
	    }
	 
	 @Test(description = "Verify AC button clears all input but keeps last answer")
	    public void testACAns() {
	        calc.calculateExp("5×9");
	        calc.getClearMemoryButton().click();
	        String displayText = calc.getLCDDisplay().getText();
	        Assert.assertEquals(displayText, "0", "AC button should clear all input");
	        
	        String ansText = calc.getLCDAns().getText();
	        Assert.assertEquals(ansText, "Ans = 45", "clear Memeory should show last calculated ans");
	    }
	 
	 @Test(description = "Verify CE button clears last entry")
	    public void testCEButton() {
	        calc.clickDigit('7');
	        calc.clickDigit('5');
	        calc.getClearEntry().click();
	        String displayText = calc.getLCDDisplay().getText();
	        Assert.assertEquals(displayText, "7", "CE button should clear the last entry");
	    }
	 
	 @Test(description = "Verify CE button during operation")
	    public void testCEButtonDuringOperation() {
	        calc.goTo();
	        calc.clickDigit('7');
	        calc.clickDigit('5');
	        calc.getClearEntry().click();
	        calc.clickDigit('3');
	        calc.clickDigit('+');
	        calc.clickDigit('2');
	        calc.clickDigit('0');
	        calc.getClearEntry().click();
	        calc.clickDigit('5');
	        calc.clickDigit('=');
	        String displayText = calc.getLCDDisplay().getText();
	        Assert.assertEquals(displayText, "98", "CE button should clear the last entry during operation");
	        
	       
	    }
	 
	 

	    	
		 	
		 	
		 	
	
	 
	 @AfterMethod
	    public void tearDown() {
	    	driver.close();
	    }

}
