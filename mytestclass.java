package mytestpack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class mytestclass {

	WebDriver driver;
	JavascriptExecutor jse;

	public void invokeBrowser() {

		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\huangti1\\selenium\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window(); // maximize the window
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // synchronize the lines of code + page --
																				// wait for the page to load
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			driver.get("https://www.amazon.ca/");
//			search();
//			changeLanguage();
			navigateCommands();
			closeTab();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void search() {

		try {
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys("book"); // this is found with the inspector tool
//			Thread.sleep(3000); // CODE will sleep for 3 seconds
//			driver.findElement(By.cssSelector(".nav-input")).click();
			driver.findElement(By.className("nav-input")).click();

			jse = (JavascriptExecutor) driver; // casting
			jse.executeScript("scroll(0, 400)");

			driver.findElement(By.partialLinkText("Ages 6-8")).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeLanguage() {

		try {
			WebElement element = driver.findElement(By.xpath("//div[@id='nav-tools']/a[@id='icp-nav-flyout']"));
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();			// hover
			
			driver.findElement(By.partialLinkText("Français")).click();		// change language to french
			
//			Thread.sleep(2000);
//			
//			action.moveToElement(element).perform();			// hover
//			driver.findElement(By.partialLinkText("English")).click();		// change language to english

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

		mytestclass myObj = new mytestclass();
		myObj.invokeBrowser();
	}

}
