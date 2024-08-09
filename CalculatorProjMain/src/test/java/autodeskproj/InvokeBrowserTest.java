package autodeskproj;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import autodeskproj.TestComponents.BaseTest;
import autodeskproj.pageobjects.CalculatorPage;

public class InvokeBrowserTest extends BaseTest {
	CalculatorPage calc;
	

    @BeforeTest(description= "loads url" )
    public void verifyCalculatorScreen() throws IOException {
        calc = launchCalculator();
    }
    
    @Test(description = "Verify if calculator screen is loaded")
    public void checkCalculatorScreen() {
    	 boolean screen = calc.isDisplayScreenVisible();
         Assert.assertTrue(screen, "Calculator Screen should be visible");

         
     }

    
    @AfterTest
    public void tearDown() {
    	driver.close();
    }
    
}
