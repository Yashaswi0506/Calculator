package autodeskproj;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import autodeskproj.TestComponents.BaseTest;
import autodeskproj.pageobjects.CalculatorPage;

public class VerifyLargeInputs extends BaseTest {

	CalculatorPage calc;
	
	@BeforeMethod
	public void setUp() throws IOException {
		calc = launchCalculator();
	}
	
	@Test(description = "Verify large calculation")
	public void verifyLargeOperation() {
		String exp = "77777777777777+77777777777777";
		String result = calc.calculateExp(exp);
		
		
		Assert.assertEquals(result, "1.5555556e+14", "The max length of result is 1.5555556e+14");
	}

	@Test(description = "Verify total output length doesn't exceed 13")
	public void maximumLength() {
		String exp = "77777777777777+77777777777777";
		String result = calc.calculateExp(exp);
		int len = result.length();
		
		Assert.assertTrue(len<=13, "The max length of result is 13");
	}
	
	@Test(description = "Verify total output length for negative doesn't exceed 14")
	public void minimumNegLength() {
		String exp = "−77777777777777+−77777777777777";
		String result = calc.calculateExp(exp);
		int len = result.length();
		
		
		Assert.assertTrue(len <= 14, "The max length of result is 14");
	}
	
	@Test(description = "Verify total output length after decimal doesn't exceed 11")
	public void maximumLengthAfterDecimal() {
		String exp = "77777777777777+77777777777777";
		String result = calc.calculateExp(exp);
		int len = 0;
		if(result.contains(".")) {
			String[] parts = result.split("\\.");
            if (parts.length > 1) {
                len = parts[1].length();
            }
			
		}
		
		Assert.assertTrue(len <=11, "The max length of result after decimal is 11");
	}
	
	
	@AfterMethod
    public void tearDown() {
    	driver.close();
    }

}
