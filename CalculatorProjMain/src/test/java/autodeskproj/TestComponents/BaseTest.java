package autodeskproj.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import autodeskproj.pageobjects.CalculatorPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public CalculatorPage calc;
	
	public WebDriver initializeDriver()throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Testing\\CalculatorProj\\src\\main\\java\\autodeskproj\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		}
		else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } 
		else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } 
		else {
            System.out.println("Invalid browser name specified.");
            System.exit(1);
        }
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().window().maximize();
		return driver;
	}
	
	public CalculatorPage launchCalculator() throws IOException{
		driver = initializeDriver();
		calc = new CalculatorPage(driver);
		calc.goTo();
		return calc;
	}

}
