package mytestpack;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class sandbox {

	WebDriver driver;
	JavascriptExecutor jse;
	String autoName = "Automated Test Name 4";
	String autoDesc = "Automated Test Description";

	public void invokeBrowser() {

		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\huangti1\\selenium\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window(); // maximize the window
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 	// synchronize the lines of code + page --
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);	// wait for the page to load

			driver.get("https://sandbox.esignlive.com/");
			
//			System.out.println("##### CURRENTLY RUNNING FIRST TEST: #####");
//			login();
//			newTransaction();
//			fillTransaction();
//			applyTemplate();
//			createTransaction();
//			openSettings();
//			enableNotarization();
//			firstCheck();

			// closeTab();
//			
			System.out.println("\n##### CURRENTLY RUNNING SECOND TEST: #####\n");
			login();
			newTransaction();
			fillTransaction();
			createTransaction();
			addFirstSigner();
			addAdditionalSigners();
			enableSigningOrder();
			reorderSigners();
			isSigningOrderEnabled();
			uploadDocumentPDF();
			nextToDesigner();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login() {

		try {
			
			String username = "tingyu.huang@esignlive.com";
			String password = "Silanis1";
			
			driver.findElement(By.cssSelector("input[name='email']")).click();
			driver.findElement(By.cssSelector("input[name='email']")).sendKeys(username); 			// types user name into the email field
			
			driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);		// types password into the password field
			driver.findElement(By.cssSelector("input[name='password']")).sendKeys(Keys.ENTER);
			driver.findElement(By.id("mui-id-1")).sendKeys("Silanis1"); // types password into the password field
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newTransaction() {

		try {
			
			//these both work
//			driver.findElement(By.cssSelector("button.button.responsive-button.new-transaction-button")).click();
			driver.findElement(By.cssSelector("button[class='button responsive-button new-transaction-button']")).click();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillTransaction() {

		try {
			
			driver.findElement(By.id("transactionTitle")).click();
			driver.findElement(By.id("transactionTitle")).sendKeys(autoName); // enters transaction name

			driver.findElement(By.id("description")).click();
			driver.findElement(By.id("description")).sendKeys(autoDesc); // enters transaction description

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void applyTemplate() {

		try {

//			String template = "body > div:nth-child(12) > div > div:nth-child(2) > div > div > div > div > section > form > div.Select.is-searchable";
			String template = "div[class='Select is-searchable']";					// version 2 update
			driver.findElement(By.cssSelector(template)).click();

//			template = "body > div:nth-child(12) > div > div:nth-child(2) > div > div > div > div > section > form > div.Select is-searchable is-open is-focused > div.Select-menu-outer > div > div";
			template = "div[class='Select-option is-focused']";						// version 2 update
			driver.findElement(By.cssSelector(template)).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTransaction() {

		try {

			driver.findElement(By.id("btnCreate")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openSettings() {

		try {

			Thread.sleep(2000);
			
			jse = (JavascriptExecutor) driver; // casting
			jse.executeScript("scroll(0, 400)");
			
//			String settings = "#main-wrapper > div > div.app-wrapper > div.grid-layout.transaction-edit-layout > div > div > div.row > div:nth-child(3) > section > div > div > div > ul > li > div > ul > li.item-field.firstname > div > div.react-typeahead-container.input-error > div > input.react-typeahead-input.react-typeahead-usertext";
			String settings = "button[class='button text-button medium-icon link-button show-advanced-settings-button']";
			driver.findElement(By.cssSelector(settings)).click();

			jse.executeScript("scroll(0, 400)");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enableNotarization() {

		try {
			
			driver.findElement(By.id("notarization")).click();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void firstCheck() {			
		// checks if the inputed name and description is the same as the current value for name and description

		try {
			Thread.sleep(1000);

//			String selector = "#main-wrapper > div > div.app-wrapper > div.grid-layout.transaction-edit-layout > div > div > div.row > div:nth-child(4) > section > section > div > div > div.transaction-title > div";
			String selector = "div[class='transaction-title']";							// get to the parent 
			WebElement element = driver.findElement(By.cssSelector(selector));
			element.click();
			
			selector = "div[class='transaction-title'] > div:nth-child(1) > input";		// get to the input tag
			element = driver.findElement(By.cssSelector(selector));
			
			String name = element.getAttribute("value");								// get the value of the input tag
			System.out.println("Current name is: " + name);

			if (name.equals(autoName)) {
				System.out.println("Transaction name was changed successfully to " + name);
			}

			System.out.println("--------------------------------------------------");

//			selector = "#main-wrapper > div > div.app-wrapper > div.grid-layout.transaction-edit-layout > div > div > div.row > div:nth-child(4) > section > section > div > div > div.description-field > div > div:nth-child(2)";
			selector = "div[class='description-field']";
			element = driver.findElement(By.cssSelector(selector));
			element.click();
			
			selector = "div[class='description-field'] > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)";
			element = driver.findElement(By.cssSelector(selector));
			
			String desc = element.getAttribute("value");
			System.out.println("Current description is: " + desc);

			if (desc.equals(autoDesc)) {
				System.out.println("Transaction description was changed successfully to " + desc);
			}

			System.out.println("--------------------------------------------------");

//			String xpathExpression = "//*[@id=\"main-wrapper\"]/div/div[2]/div[2]/div/div/div[1]/div[3]/section/div/div[2]/div/div/div[2]/div[3]/span/div/div/div[2]/div[1]";
//			element = driver.findElement(By.xpath(xpathExpression));
			
			selector = "input[id='notarization'] + div > div:nth-child(3) > div";		// access through the sibling 
			element = driver.findElement(By.cssSelector(selector));			

			String value = element.getCssValue("background-color");
			System.out.println("Current colour is: " + value);

			String onColor = "rgba(131, 163, 50, 0.5)";
			String offColor = "rgba(0, 0, 0, 0.54)";

			if (value.equals(onColor)) {
				System.out.println("Notarization was changed successfully enabled.");
			}

			else if (value.equals(offColor)) {
				System.out.println("Notarization is currently disabled.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void addFirstSigner() {
		// will always add seasysideacc@gmail.com as the first signer
		
		try {
			
			String inputFN = "seasyside";
			
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			Thread.sleep(2000);
			driver.findElement(By.name("firstName")).click();
		    driver.findElement(By.name("firstName")).clear();
		    driver.findElement(By.name("firstName")).sendKeys(inputFN);
		    
		    Thread.sleep(1500);
		    driver.findElement(By.cssSelector("div.name.text-truncate > span")).click();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addAdditionalSigners() {
		// will always add seasysideacc@gmail.com as the first signer
		
		try {
			
			Thread.sleep(1500);
			
//		    ######### SECOND RECIPIENT #########
			
			String add = "button[class='button text-button medium-icon link-button add-button']";
			driver.findElement(By.cssSelector(add)).click();		// click add button
			
			driver.findElement(By.xpath("(//input[@name='firstName'])[2]")).click();
		    driver.findElement(By.xpath("(//input[@name='firstName'])[2]")).clear();
		    driver.findElement(By.xpath("(//input[@name='firstName'])[2]")).sendKeys("ccrecipient1");
		    
		    Thread.sleep(1500);
		    String clickDropdown = "ul[role='listbox'][aria-hidden='false']";
		    driver.findElement(By.cssSelector(clickDropdown)).click();
		    
//		    ######### THIRD RECIPIENT #########
		    Thread.sleep(1500);
		    
			driver.findElement(By.cssSelector(add)).click();		// click add button
			driver.findElement(By.xpath("(//input[@name='firstName'])[3]")).click();
		    driver.findElement(By.xpath("(//input[@name='firstName'])[3]")).clear();
		    driver.findElement(By.xpath("(//input[@name='firstName'])[3]")).sendKeys("ccrecipient2");
		    
		    Thread.sleep(1500);
		    driver.findElement(By.cssSelector(clickDropdown)).click();

//		    ######### FOURTH RECIPIENT #########
		    Thread.sleep(1500);
		    
			driver.findElement(By.cssSelector(add)).click();		// click add button
			driver.findElement(By.xpath("(//input[@name='firstName'])[4]")).click();
		    driver.findElement(By.xpath("(//input[@name='firstName'])[4]")).clear();
		    driver.findElement(By.xpath("(//input[@name='firstName'])[4]")).sendKeys("rez");
		    
		    Thread.sleep(1500); 
		    driver.findElement(By.cssSelector(clickDropdown)).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enableSigningOrder() {

		try {

			String signingOrder = "section[class='transaction-section transaction-signers'] > div > div > div > input";
			driver.findElement(By.cssSelector(signingOrder)).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reorderSigners() {
		
		try {
			
//			enableSigningOrder();
			Thread.sleep(1000);
			
			// moves Recipient 4 to Recipient 1 location
			Actions builder = new Actions(driver);
			
			WebElement dragHandle4 = driver.findElement(By.cssSelector("div[class='signers'] > ul[class='list list-decorated list-hover list-draggable signers-list'] > li:nth-child(4) > div[class='signer-item'] > div[class='drag-handle']"));
			WebElement firstSpot = driver.findElement(By.cssSelector("div[class='signers'] > ul[class='list list-decorated list-hover list-draggable signers-list'] > li:nth-child(1)"));
			WebElement secondSpot = driver.findElement(By.cssSelector("div[class='signers'] > ul[class='list list-decorated list-hover list-draggable signers-list'] > li:nth-child(2)"));
			WebElement thirdSpot = driver.findElement(By.cssSelector("div[class='signers'] > ul[class='list list-decorated list-hover list-draggable signers-list'] > li:nth-child(3)"));
			
			Action dragAndDrop = builder.clickAndHold(dragHandle4)
					.pause(1000)
	//				.moveToElement(firstSpot, -5, -20)
	//				.pause(2000)
					.moveToElement(firstSpot, 0, 20)
					.pause(1000)
					.moveToElement(firstSpot, 0, 0)
	//				.dragAndDrop(dragHandle4, firstSpot)
					.pause(200)
					.release()
		        	.build();

			dragAndDrop.perform();
			
			dragAndDrop = builder.clickAndHold(dragHandle4)
					.moveToElement(secondSpot, 0, -10)
					.pause(1000)
					.moveToElement(secondSpot, 0, -1)
					.pause(200)
					.release()
		        	.build();

			dragAndDrop.perform();
			
			dragAndDrop = builder.clickAndHold(dragHandle4)
					.moveToElement(thirdSpot, 0, -10)
					.pause(1000)
					.moveToElement(thirdSpot, 0, -1)
					.pause(200)
					.release()
		        	.build();

			dragAndDrop.perform();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void isSigningOrderEnabled() {
		
		try {
			Thread.sleep(1000);

			String soButton = "section[class='transaction-section transaction-signers'] > div[class='row'] > div:nth-child(1) > div:nth-child(1) > div > div:nth-child(3) > div:nth-child(2)";
			WebElement element = driver.findElement(By.cssSelector(soButton));			

			String value = element.getCssValue("background-color");
			System.out.println("Current value is: " + value);

			String onColor = "rgb(131, 163, 50)";
			String oC2 = "rgba(131, 163, 50, 1)";
			String offColor = "rgb(245, 245, 245)";
			String offColor2 = "rgba(245, 245, 245, 1)";

			if (value.equals(onColor) || value.equals(oC2)) {
				System.out.println("Signing order was successfully enabled.");
			}

			else if (value.equals(offColor) || value.equals(offColor2)) {
				System.out.println("Signing order is currently disabled.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void uploadDocumentPDF() {

		try {

			jse = (JavascriptExecutor) driver; // scroll
			jse.executeScript("scroll(0, -400)");
			
			String input = "div[class='file-upload'] + form > input[type='file']";
			String path = "C:\\Users\\huangti1\\Downloads\\Math323-00-SyllabusEvaluation.pdf";
			driver.findElement(By.cssSelector(input)).sendKeys(path);
			

			Thread.sleep(1500);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void nextToDesigner() {

		try {
			
			String next = "button[class='button responsive-button design-transaction-button']";
			driver.findElement(By.cssSelector(next)).click();
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	} 

	public void navigateCommands() {

		try {
			driver.navigate().to("http://www.google.com");
			driver.findElement(By.id("lst-ib")).sendKeys("kittens");
			driver.findElement(By.id("lst-ib")).sendKeys(Keys.RETURN);
			Thread.sleep(2000);
			driver.navigate().back();
			Thread.sleep(2000);
			driver.navigate().forward();
			Thread.sleep(2000);
			driver.navigate().refresh();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void closeTab() {
		driver.close();
	}

	public static void main(String[] args) {

		sandbox myObj = new sandbox();
		myObj.invokeBrowser();
	}

}
