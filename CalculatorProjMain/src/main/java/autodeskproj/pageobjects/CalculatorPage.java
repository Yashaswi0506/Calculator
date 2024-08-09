package autodeskproj.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Components;

public class CalculatorPage extends Components{
	WebDriver driver;
	public CalculatorPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	 @FindBy(xpath = "//div[@class ='card-section']/div[1]")
	WebElement calculatorScreen;
	 
	 @FindBy(xpath = "//span[@id ='cwos']")
	 WebElement lcdDisplay;
	
	 @FindBy(xpath = "//div[(text() = 'AC')]")
	 WebElement clearMemoryButton;
	 
	 @FindBy(xpath = "//div[(text() = 'CE')]")
	 WebElement clearEntry;
	 
	 @FindBy(xpath = "//div[(text() = '0')]")
	 WebElement zeroButton;
	 
	 @FindBy(xpath = "//div[(text() = '1')]")
	 WebElement oneButton;
	 
	 @FindBy(xpath = "//div[(text() = '2')]")
	 WebElement twoButton;
	 
	 @FindBy(xpath = "//div[(text() = '3')]")
	 WebElement threeButton;
	 
	 @FindBy(xpath = "//div[(text() = '4')]")
	 WebElement fourButton;
	 
	 @FindBy(xpath = "//div[(text() = '5')]")
	 WebElement fiveButton;
	 
	 @FindBy(xpath = "//div[(text() = '6')]")
	 WebElement sixButton;
	 
	 @FindBy(xpath = "//div[(text() = '7')]")
	 WebElement sevenButton;
	 
	 @FindBy(xpath = "//div[(text() = '8')]")
	 WebElement eightButton;
	 
	 @FindBy(xpath = "//div[(text() = '9')]")
	 WebElement nineButton;
	 
	 @FindBy(xpath = "//div[(text() = '+')]")
	 WebElement additionOp;
	 
	 @FindBy(xpath = "//div[(text() = '−')]")
	 WebElement subtractionOp;
	 
	 @FindBy(xpath = "//div[(text() = '×')]")
	 WebElement multiplicationOp;
	 
	 @FindBy(xpath = "//div[(text() = '÷')]")
	 WebElement divisionOp;
	 
	 @FindBy(xpath = "//div[(text() = '=')]")
	 WebElement equalOp;
	 
	 @FindBy(xpath = "//div[(text() = '.')]")
	 WebElement decimalOp;
	 
	 @FindBy(xpath = "//span[@class='vUGUtc']")
	 WebElement ansLCDDisplay;
	 
	 
	 
	 
	 By displayScreen = By.xpath("//div[@class ='card-section']/div[1]");
	 By clearEntryButton = By.xpath("//div[(text() = 'CE')]");
	 By clearMemoryButtonWait = By.xpath("//div[(text() = 'AC')]");
	 String exp = "";
	 By lcdDisplayAns = By.xpath("//span[@class='vUGUtc']");
	 
	 StringBuilder modifiedExp = new StringBuilder();
	 

	 public void goTo() {
	        driver.get("https://www.google.com/search?q=calculator");
	    }

	    public boolean isDisplayScreenVisible() {
	        waitForElementToAppear(displayScreen);
	        return calculatorScreen.isDisplayed();
	    }
	    
	    public boolean isCEButtonVisible() {
	        waitForElementToAppear(clearEntryButton);
	        return clearEntry.isDisplayed();
	    }
	    
	    
	    
	    public WebElement getClearMemoryButton() {
	    	waitForElementToAppear(clearMemoryButtonWait);
	        return clearMemoryButton;
	    }
	    
	    public WebElement getClearEntry() {
	    	waitForElementToAppear(clearEntryButton);
	        return clearEntry;
	    }
	    
	    public WebElement getLCDAns() {
	    	waitForElementToAppear(lcdDisplayAns);
	    	return ansLCDDisplay;
	    }
	    
	    public void clickDigit(char digit) {
	        switch (digit) {
	            case '0':
	                zeroButton.click();
	                break;
	            case '1':
	                oneButton.click();
	                break;
	            case '2':
	                twoButton.click();
	                break;
	            case '3':
	                threeButton.click();
	                break;
	            case '4':
	                fourButton.click();
	                break;
	            case '5':
	                fiveButton.click();
	                break;
	            case '6':
	                sixButton.click();
	                break;
	            case '7':
	                sevenButton.click();
	                break;
	            case '8':
	                eightButton.click();
	                break;
	            case '9':
	                nineButton.click();
	                break;
	            case '×':
	            	multiplicationOp.click();
	            	break;
	            case '+':
	            	additionOp.click();
	            	break;
	            case '−':
	            	subtractionOp.click();
	            	break;
	            case '÷':
	            	divisionOp.click();
	            	break;
	            case '.':
	            	decimalOp.click();
	            	break;
	            case '=':
	            	equalOp.click();
	            	break;
	            default:
	                throw new IllegalArgumentException("Invalid digit: " + digit);
	        }
	    }
	    //returns elements from display screen
	    public WebElement getLCDDisplay() {
	    	return lcdDisplay;
	    }
	    
	    
	    public String calculateExp(String exp) {
	        String expWithEquals = exp + "=";
	        modifiedExp = new StringBuilder(expWithEquals);
	        String op = "+÷×";
	        
	        
	        for (int i = 0; i < exp.length(); i++) {
	        	
	        	
	            clickDigit(exp.charAt(i));
	           
	            if (i < exp.length() - 1 && exp.charAt(i) == '+' && exp.charAt(i + 1) == '−') {
	            	
	                modifiedExp.deleteCharAt(i);
	                modifiedExp.setCharAt(i, '-');
	                System.out.println("Chars are"+modifiedExp.charAt(i+1));
	            }
	            else if(i<exp.length()-1 && op.contains(String.valueOf(exp.charAt(i))) && op.contains(String.valueOf(exp.charAt(i+1)))) {
	            	modifiedExp.deleteCharAt(i);
	            }
	            else if(i<exp.length()-1 && exp.charAt(i) == '−' && (op.contains(String.valueOf(exp.charAt(i+1)))||exp.charAt(i+1) == '−' )) {
	            	modifiedExp.deleteCharAt(i);
	            }
	        }
	        
	        equalOp.click();
	        modifiedExp = new StringBuilder(modifiedExp.toString().replace('−', '-'));
	        By lcdDisplayAns = By.xpath("//span[normalize-space(translate(text(), ' ', ''))='" + modifiedExp.toString() + "']");
	        waitForElementToAppear(lcdDisplayAns);
	        return getLCDDisplay().getText();
	        }
	    
	    public String getExp(String exp) {
	    	calculateExp(exp);
	    	return modifiedExp.toString();
	    }
	        
	        
	    }




	 
	 
	


