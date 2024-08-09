package autodeskproj;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import autodeskproj.TestComponents.BaseTest;
import autodeskproj.pageobjects.CalculatorPage;

//This files contains all the basic operation of calculator, operations with 0 and negative operations

public class VerifyOperations extends BaseTest {
CalculatorPage calc;
	
	@BeforeMethod
	public void setUp() throws IOException {
		calc = launchCalculator();
	}

	@Test(description = "Add two numbers")
	public void addTwoNumbers() {
		String exp = "2+3";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "5", "Addition of 2+3 should be equal to 5");
	}
	
	@Test(description = "Subtract two numbers")
	public void subtractTwoNumbers() {
		String exp = "5−2";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "3", "5-2 should be equal to 3");
	}
	
	
	@Test(description = "Multiply two numbers")
	public void multiplyTwoNumbers() {
		String exp = "5×2";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "10", "5*2 should be equal to 10");
	}
	
	@Test(description = "Divide two numbers")
	public void divideTwoNumbers() {
		String exp = "10÷2";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "5", "10/5 should be equal to 5");
	}
	
	@Test(description = "add two zeros")
	public void addZeroToZero() {
		String exp = "0+0";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "0", "0+0 should be equal to 5");
	}
	@Test(description = "subtract 0 from 0")
	public void subZeroToZero() {
		String exp = "0−0";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "0", "0-0 should be equal to 0");
	}
	@Test(description = "Multiply 0 by 0")
	public void mulZeroToZero() {
		String exp = "0×0";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "0", "0*0 should be equal to 0");
	}
	@Test(description = "0 Divide by 0")
	public void divZeroToZero() {
		String exp = "0÷0";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "Error", "0/0 is an error");
	}
	
	@Test(description = "Divide by 0")
	public void divByZero() {
		String exp = "10÷0";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "Infinity", "Any number divide by 0 is infinity");
	}
	
	
	
	@Test(description = "Add two negative numbers")
	public void negAdd() {
		String exp = "−10+−10";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "-20", "-10 +(-10) should be equal to -20");
	}
	@Test(description = "Subtract two negative numbers")
	public void negSub() {
		String exp = "−10−10";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "-20", "-10-10 should be equal to -20");
	}
	
	@Test(description = "Multiply two negative numbers")
	public void negMul() {
		String exp = "−10×−10";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "100", "-10*-10 should be equal to 100");
	}
	@Test(description = "Divide by negative number")
	public void negDiv() {
		String exp = "10÷−10";
		String result = calc.calculateExp(exp);
		
		Assert.assertEquals(result, "-1", "10/-10 should be equal to -1");
	}
	
	
	
	
	@AfterMethod
    public void tearDown() {
    	driver.close();
    }

}



