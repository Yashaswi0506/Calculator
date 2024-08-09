package autodeskproj;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import autodeskproj.TestComponents.BaseTest;
import autodeskproj.pageobjects.CalculatorPage;

public class VerifyPedmasRule extends BaseTest {
CalculatorPage calc;
	
	@BeforeMethod
	public void setUp() throws IOException {
		calc = launchCalculator();
	}

	//@Test(description = "check addition and subtraction sequenece")
	public void verifyAdditionSubtraction() {
	    String expression1 = "10+5−3";
	    String expected1 = "12";
	    Assert.assertEquals(calc.calculateExp(expression1), expected1, "Result should be 12");
	}
	
	//@Test(description = "check subtraction and addition sequenece")
	public void verifySubtractionAddition() {
	    String expression = "−3+10+5";
	    String expected = "12";
	    Assert.assertEquals(calc.calculateExp(expression), expected, "Result should be 12");
	}

	//@Test(description = "check multiplication and divison sequence ")
	public void verifyMultiplicationDivison() {
	    String expression = "6×3÷2";
	    String expected = "9";
	    Assert.assertEquals(calc.calculateExp(expression), expected, "Result should be 9");
	}
	//@Test(description = "check multiplication and divison sequence ")
	public void verifyDivisonMultiplication() {
	    String expression = "2÷3×6";
	    String expected = "4";
	    Assert.assertEquals(calc.calculateExp(expression), expected, "Result should be 4");
	}

	//@Test(description = "Verify Combination of Operations ")
	public void verifyCombination() {
	    String expression = "12+3×2−4÷2";
	    String expected = "16";
	    Assert.assertEquals(calc.calculateExp(expression), expected, "Result should be 16");
	}

	//@Test(description = "Verify large Operations ")
	public void verifyLargeOperations() {

	    String expression = "10×5−8÷2+7×2−3";
	    String expected = "57";
	    Assert.assertEquals(calc.calculateExp(expression), expected, "Result should be 57");
	}
	
	@AfterMethod
    public void tearDown() {
    	driver.close();
    }

	
}



