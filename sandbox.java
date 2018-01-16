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
	String autoName = "Automated Test Name";
	String autoDesc = "Automated Test Description";

	public void invokeBrowser() {

		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\huangti1\\selenium\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window(); // maximize the window
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // synchronize the lines of code + page --
																				// wait for the page to load
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			driver.get("https://sandbox.esignlive.com/");
			
			login();
			newTransaction();
			fillTransaction();
			applyTemplate();
			createTransaction();
			openSettings();
			enableNotarization();
			
			check();
			
			// closeTab();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login() {

		try {

			driver.findElement(By.cssSelector("input[name='email']")).click();
			driver.findElement(By.cssSelector("input[name='email']")).sendKeys("tingyu.huang@esignlive.com"); // types
																												// username
																												// into
																												// the
																												// email
																												// field

			driver.findElement(By.id("mui-id-1")).sendKeys("Silanis1"); // types password into the password field
			driver.findElement(By.id("mui-id-1")).sendKeys(Keys.RETURN); // enter

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newTransaction() {

		try {

			driver.findElement(By.cssSelector(
					"#main-wrapper > div > div.app-wrapper > div.fullwidth-layout.dashboard-layout > div > section > div > div > button > div > div"))
					.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillTransaction() {

		try {
			driver.findElement(By.id("transactionTitle")).click();
			driver.findElement(By.id("transactionTitle")).sendKeys(autoName); // enters transaction name

			driver.findElement(By.id("description")).click();
			driver.findElement(By.id("description")).sendKeys(autoDesc); // enters transaction
																								// description

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openSettings() {
		
		
		try {
			
			Thread.sleep(2000);
			String settings = "#main-wrapper > div > div.app-wrapper > div.grid-layout.transaction-edit-layout > div > div > div.row > div:nth-child(4) > section > div > div:nth-child(1) > button > div > span:nth-child(1)";
			driver.findElement(By.cssSelector(settings)).click();
			
			jse = (JavascriptExecutor) driver; // casting
			jse.executeScript("scroll(0, 400)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enableNotarization() {
		
		
//		String notarization = "body > div:nth-child(12) > div > div:nth-child(2) > div > div > div > div > section > form > div.row.transaction-advanced-settings > div:nth-child(2) > div > div > div:nth-child(2) > div:nth-child(3) > span > div > div > div:nth-child(3)";
		driver.findElement(By.id("notarization")).click();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void applyTemplate() {

		try {

			String template = "body > div:nth-child(12) > div > div:nth-child(2) > div > div > div > div > section > form > div.Select.is-searchable";
			driver.findElement(By.cssSelector(template)).click();
			// driver.findElement(By.cssSelector(template)).clear();

			template = "body > div:nth-child(12) > div > div:nth-child(2) > div > div > div > div > section > form > div.Select.is-searchable.is-open.is-focused > div.Select-menu-outer > div > div";
			driver.findElement(By.cssSelector(template)).click();
			// driver.findElement(By.cssSelector(template)).sendKeys(Keys.ENTER);

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

	public void changeLanguage() {

		try {
			WebElement element = driver.findElement(By.xpath("//div[@id='nav-tools']/a[@id='icp-nav-flyout']"));
			Actions action = new Actions(driver);
			action.moveToElement(element).perform(); // hover

			driver.findElement(By.partialLinkText("Français")).click(); // change language to french

			// Thread.sleep(2000);
			//
			// action.moveToElement(element).perform(); // hover
			// driver.findElement(By.partialLinkText("English")).click(); // change language
			// to english

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
	
	public void check() {		
		
		try {
			Thread.sleep(1000);
			
			String selector = "#main-wrapper > div > div.app-wrapper > div.grid-layout.transaction-edit-layout > div > div > div.row > div:nth-child(4) > section > section > div > div > div.transaction-title > div";
			WebElement element = driver.findElement(By.cssSelector(selector));
			element.click();
			element = driver.findElement(By.id("mui-id-5"));
			String name = element.getAttribute("value");
			System.out.println("Current name is: " + name);
			
			if (name.equals(autoName)) {
				System.out.println("Transaction name was changed successfully to " + name);
			}

			System.out.println("--------------------------------------------------");
			
			selector = "#main-wrapper > div > div.app-wrapper > div.grid-layout.transaction-edit-layout > div > div > div.row > div:nth-child(4) > section > section > div > div > div.description-field > div > div:nth-child(2)";
			element = driver.findElement(By.cssSelector(selector));
			element.click();
			element = driver.findElement(By.id("mui-id-6"));
			String desc = element.getAttribute("value");
			System.out.println("Current description is: " + desc);
			
			if (desc.equals(autoDesc)) {
				System.out.println("Transaction description was changed successfully to " + desc);
			}
			
			System.out.println("--------------------------------------------------");
			
			String xpathExpression = "//*[@id=\"main-wrapper\"]/div/div[2]/div[2]/div/div/div[1]/div[3]/section/div/div[2]/div/div/div[2]/div[3]/span/div/div/div[2]/div[1]";
			element = driver.findElement(By.xpath(xpathExpression));
			
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

	public void closeTab() {
		driver.close();
	}

	public static void main(String[] args) {

		sandbox myObj = new sandbox();
		myObj.invokeBrowser();
	}

}
