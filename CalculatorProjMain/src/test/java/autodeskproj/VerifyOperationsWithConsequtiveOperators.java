package autodeskproj;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import autodeskproj.TestComponents.BaseTest;
import autodeskproj.pageobjects.CalculatorPage;

//this file tests whether the expression appends or overrides operator next to an operator
public class VerifyOperationsWithConsequtiveOperators extends BaseTest{
	
CalculatorPage calc;
	
	@BeforeMethod
	public void setUp() throws IOException {
		calc = launchCalculator();
	}
	
	
	
	@Test(description = "Verify operator + follwed by any operator will overwrite + in the expression")
	public void addAndMul() {
		String exp = "5+×10";
		String result = calc.getExp(exp);
		Assert.assertEquals(result, "5×10=", "-Expression should overwrite +");
	}
	
	
	
	@Test(description = "Verify operator + follwed by + will overwrite + in the expression")
	public void addAndAdd() {
		String exp = "5++10";
		String result = calc.getExp(exp);
		Assert.assertEquals(result, "5+10=", "Expression should overwrite one +");
	}
	
	@Test(description = "Verify operator - follwed by any operator will overwrite - in the expression")
	public void subAndAdd() {
		String exp = "5−+10";
		String result = calc.getExp(exp);
		Assert.assertEquals(result, "5+10=", "Expression should overwrite -");
	}
	
	@Test(description = "Verify operator - or + follwed by - will overwrite first  - in the expression")
	public void subAndSub() {
		String exp = "5−−10";
		String result = calc.getExp(exp);
		Assert.assertEquals(result, "5-10=", "Expression should overwrite one -");
	}
	
	@Test(description = "Verify operator * and / operator  follwed by  will - append - to the expression")
	public void mulAndSub() {
		String exp = "5×−10";
		String result = calc.getExp(exp);
		Assert.assertEquals(result, "5×-10=", "Expression should append -");
	}
	
	
	
	
	
	
	
	

	@Test(description = "Verify operator / operator  follwed by  + / or  * will  overwrite / operator")
	public void divideFollowedByOperator() {
		String exp = "2÷+3";
		String result = calc.getExp(exp);
		
		Assert.assertEquals(result, "2+3=", "Expression will overwrite /");
	}
	
	
	
	
	
	@AfterMethod
    public void tearDown() {
    	driver.close();
    }

}





