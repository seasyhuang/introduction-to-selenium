package sandboxPKG;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class sandbox {

	static WebDriver driver;
	JavascriptExecutor jse;
	
//	String autoName = "Automated Test Name 4";
//	String autoDesc = "Automated Test Description";
	
	Wait<WebDriver> waitLoad = new FluentWait<WebDriver>(driver)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(1, TimeUnit.SECONDS)
            .ignoring(NoSuchElementException.class);

	public void invokeBrowser(HashMap<String, String> data) {

		try {
			
//			driver.get("https://sandbox.esignlive.com/");
//			driver.get((String) data.get("environment"));
			
//			firstTest(data);
//			secondTest(data);		
//			thirdTest(data);
			signTransaction(data);
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void firstTest(HashMap<String, String> data) {

		try {
			
			System.out.println("##### CURRENTLY RUNNING FIRST TEST: #####");
			
			driver.get((String) data.get("environment"));
			login(data);
			newTransaction();
			fillTransaction(data);
			applyTemplate();
			createTransaction();
			openSettings();
			enableNotarization();
			firstCheck(data);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void secondTest(HashMap<String, String> data) {

		try {
			
			System.out.println("\n##### CURRENTLY RUNNING SECOND TEST: #####\n");
			
			driver.get((String) data.get("environment"));
			login(data);
			newTransaction();
			fillTransaction(data);
			createTransaction();
			addFirstSigner(data);
			addAdditionalSigners(data);
			reorderSigners();
			isSigningOrderEnabled(data);
			openSettings();
			setExpiryDate();
			uploadDocumentPDF(data);
			nextToDesigner();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void thirdTest(HashMap<String, String> data) {

		try {

			System.out.println("\n##### CURRENTLY RUNNING THIRD TEST: #####\n");
			
			driver.get((String) data.get("environment"));
			login(data);
			newTransaction();
			fillTransaction(data);
			createTransaction();
			addFirstSigner(data);
			uploadDocumentPDF(data);
			nextToDesigner();
			dragSignature();
			dragFields(data);
//			saveLayout(data);
//			sendToSign();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void signTransaction(HashMap<String, String> data) {

		try {

			System.out.println("\n##### CURRENTLY SIGNING TRANSACTION: #####\n");
			
			driver.get((String) data.get("mail"));
			loginMail(data);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void login(HashMap<String, String> data) {

		try {
			
//			String username = "tingyu.huang@esignlive.com";
//			String password = "Silanis1";
				
			WebElement emailField = driver.findElement(By.cssSelector("input[name='email']"));
			emailField.click();
			slowKeys(data.get("username"), emailField);
			
			WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
			slowKeys(data.get("password"), passwordField);
			passwordField.sendKeys(Keys.ENTER);
			
//			driver.findElement(By.cssSelector("input[name='email']")).click();
//			driver.findElement(By.cssSelector("input[name='email']")).sendKeys(username); 			// types user name into the email field
			
//			driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);		// types password into the password field
//			driver.findElement(By.cssSelector("input[name='password']")).sendKeys(Keys.ENTER);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loginMail(HashMap<String, String> data) {

		try {
				
			WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
			emailField.click();
			slowKeys(data.get("emailUsername"), emailField);
			
			driver.findElement(By.id("identifierNext")).click();
			
//			WebElement passwordField = driver.findElement(By.id("identifierNext"));
//			slowKeys(data.get("emailPassword"), passwordField);
//			passwordField.sendKeys(Keys.ENTER);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newTransaction() {

		try {
			
			WebElement ntButton = driver.findElement(By.cssSelector("button[class='button responsive-button new-transaction-button']"));
			waitLoad.until(ExpectedConditions.visibilityOf(ntButton));
			ntButton.click();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillTransaction(HashMap<String, String> data) {

		try {
			
			WebElement transactionTitle = driver.findElement(By.id("transactionTitle"));
			transactionTitle.click();
			slowKeys(data.get("autoName"), transactionTitle);
//			transactionTitle.sendKeys(autoName); // enters transaction name
			
			WebElement transactionDescription = driver.findElement(By.id("description"));
			transactionDescription.click();
			slowKeys(data.get("autoDesc"), transactionDescription);
//			transactionDescription.sendKeys(autoDesc); // enters transaction description

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void applyTemplate() {

		try {

			String template = "div[class='Select is-searchable']";					// version 2 update
			driver.findElement(By.cssSelector(template)).click();
			
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

	public void firstCheck(HashMap<String, String> data) {			
		// checks if the inputed name and description is the same as the current value for name and description

		try {
			Thread.sleep(1000);

			String selector = "div[class='transaction-title']";							// get to the parent 
			WebElement element = driver.findElement(By.cssSelector(selector));
			element.click();
			
			selector = "div[class='transaction-title'] > div:nth-child(1) > input";		// get to the input tag
			element = driver.findElement(By.cssSelector(selector));
			
			String name = element.getAttribute("value");								// get the value of the input tag
			System.out.println("Current name is: " + name);

			if (name.equals(data.get("autoName"))) {
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

			if (desc.equals(data.get("autoDesc"))) {
				System.out.println("Transaction description was changed successfully to " + desc);
			}

			System.out.println("--------------------------------------------------");

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
	
	
	public void addFirstSigner(HashMap<String, String> data) {
		
		try {
			
//			String firstname1 = "seasyside";
			
			Thread.sleep(2000);
			
			WebElement firstName = driver.findElement(By.name("firstName"));
			firstName.click();
			firstName.clear();
			slowKeys(data.get("firstname1"), firstName);
//		    firstName.sendKeys(inputFN);
		    
		    Thread.sleep(1500);
		    driver.findElement(By.cssSelector("div.name.text-truncate > span")).click();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addAdditionalSigners(HashMap<String, String> data) {
		
		try {
			
			Thread.sleep(1500);
			
//		    ######### SECOND RECIPIENT #########
			
			String add = "button[class='button text-button medium-icon link-button add-button']";
			driver.findElement(By.cssSelector(add)).click();		// click add button
			
			WebElement firstName2 = driver.findElement(By.xpath("(//input[@name='firstName'])[2]"));
			firstName2.click();
			firstName2.clear();
			slowKeys(data.get("firstname2"), firstName2);
//			firstName2.sendKeys("ccrecipient1");
//		    Thread.sleep(1500);
			
		    String clickDropdown = "ul[role='listbox'][aria-hidden='false']";
		    driver.findElement(By.cssSelector(clickDropdown)).click();
		    
//		    ######### THIRD RECIPIENT #########
		    Thread.sleep(1500);
		    
			driver.findElement(By.cssSelector(add)).click();		// click add button
			WebElement firstName3 = driver.findElement(By.xpath("(//input[@name='firstName'])[3]"));
			firstName3.click();
			firstName3.clear();
			slowKeys(data.get("firstname3"), firstName3);
//			firstName3.sendKeys("ccrecipient2");
//		    Thread.sleep(1500);
		    
		    driver.findElement(By.cssSelector(clickDropdown)).click();

//		    ######### FOURTH RECIPIENT #########
		    Thread.sleep(1500);
		    
			driver.findElement(By.cssSelector(add)).click();		// click add button
			WebElement firstName4 = driver.findElement(By.xpath("(//input[@name='firstName'])[4]"));
			firstName4.click();
			firstName4.clear();
			slowKeys(data.get("firstname4"), firstName4);
//			firstName4.sendKeys("rez");
//		    Thread.sleep(1500); 
			
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
//					.moveToElement(secondSpot, 0, -10)
					.pause(1000)
					.moveToElement(secondSpot, 0, -1)
					.pause(200)
					.release()
		        	.build();

			dragAndDrop.perform();
			
			dragAndDrop = builder.clickAndHold(dragHandle4)
//					.moveToElement(thirdSpot, 0, -10)
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
	
	public void isSigningOrderEnabled(HashMap<String, String> data) {
		
		try {
			Thread.sleep(1000);

			String soButton = "section[class='transaction-section transaction-signers'] > div[class='row'] > div:nth-child(1) > div:nth-child(1) > div > div:nth-child(3) > div:nth-child(2)";
			WebElement element = driver.findElement(By.cssSelector(soButton));			

			String value = element.getCssValue("background-color");
			System.out.println("Current value is: " + value);

//			String onColor = "rgb(131, 163, 50)";
//			String oC2 = "rgba(131, 163, 50, 1)";
//			String offColor = "rgb(245, 245, 245)";
//			String offColor2 = "rgba(245, 245, 245, 1)";

			if (value.equals(data.get("onColor")) || value.equals(data.get("onColor2"))) {
				System.out.println("Signing order was successfully enabled.");
			}

			else if (value.equals(data.get("offColor")) || value.equals(data.get("offColor2"))) {
				System.out.println("Signing order is currently disabled.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setExpiryDate() {

		try {
			
			String date = "div[class='date-field']";
			driver.findElement(By.cssSelector(date)).click();

			Date today = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd");				// this gives the date of the month 
			int tmrw = Integer.parseInt(ft.format(today)) + 1;				// tomorrow's date (of the month)						
			
			String xtmrw = "//span[contains(text(), \"" + tmrw + "\")]/..";	// get parent
//			System.out.println(xtmrw);
//			System.out.println("//span[contains(text(), \"22\")]");
			
//			xtmrw = "//span[contains(text(), \"23\")]/..";
			
			driver.findElement(By.xpath(xtmrw)).click();					// click parent		
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void uploadDocumentPDF(HashMap<String, String> data) {

		try {

			jse = (JavascriptExecutor) driver; // scroll
			jse.executeScript("scroll(0, -400)");
			
			String input = "div[class='file-upload'] + form > input[type='file']";
			driver.findElement(By.cssSelector(input)).sendKeys(data.get("docPath"));
			

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
	
	public void dragSignature() {

		String signature = "figure[class='spot-source text-truncate']";
		
		waitLoad.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(signature)));
		
		WebElement sigBlock = driver.findElement(By.cssSelector("figure[data-block-type='signature']"));
//		sigBlock.click();
		
		Actions builder = new Actions(driver);
		
		Action dragAndDrop = builder.clickAndHold(sigBlock)
				.pause(500)
				.moveByOffset(600, 0)
				.pause(500)
				.release()
	        	.build();

		dragAndDrop.perform();
		
		//////////////////////////////////////////////////////////////////////////////////////		
		
		sigBlock = driver.findElement(By.cssSelector("figure[data-block-type='initials']"));
		dragAndDrop = builder.clickAndHold(sigBlock)
				.pause(500)
				.moveByOffset(600, 20)
				.pause(500)
				.release()
	        	.build();
		dragAndDrop.perform();
		
		sigBlock = driver.findElement(By.cssSelector("figure[data-block-type='signingDate']"));
		dragAndDrop = builder.clickAndHold(sigBlock)
				.pause(500)
				.moveByOffset(600, 40)
				.pause(500)
				.release()
	        	.build();
		dragAndDrop.perform();
		
		sigBlock = driver.findElement(By.cssSelector("figure[data-block-type='signerName']"));
		dragAndDrop = builder.clickAndHold(sigBlock)
				.pause(500)
				.moveByOffset(600, 60)
				.pause(500)
				.release()
	        	.build();
		dragAndDrop.perform();
		
		sigBlock = driver.findElement(By.cssSelector("figure[data-block-type='signerTitle']"));
		dragAndDrop = builder.clickAndHold(sigBlock)
				.pause(500)
				.moveByOffset(600, 80)
				.pause(500)
				.release()
	        	.build();
		dragAndDrop.perform();
		
		sigBlock = driver.findElement(By.cssSelector("figure[data-block-type='signerCompany']"));
		dragAndDrop = builder.clickAndHold(sigBlock)
				.pause(500)
				.moveByOffset(600, 100)
				.pause(500)
				.release()
	        	.build();
		dragAndDrop.perform();
		
		
		//////////////////////////////////////////////////////////////////////////////////////	

	}
	
	public void dragFields(HashMap<String, String> data) {

		try {
			Thread.sleep(500);
			
			jse = (JavascriptExecutor) driver; // casting
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector("figure[data-block-type='label']")));
			
			WebElement field = driver.findElement(By.cssSelector("figure[data-block-type='label']"));
			
			Actions builder = new Actions(driver);
			
			Action dragAndDrop = builder.clickAndHold(field)
					.pause(500)
					.moveByOffset(1000, -200)
					.pause(500)
					.release()
			    	.build();

			dragAndDrop.perform();
			
			field = driver.findElement(By.cssSelector("figure[data-block-type='list']"));
			dragAndDrop = builder.clickAndHold(field)
					.pause(500)
					.moveByOffset(1000, -78)
					.pause(500)
					.release()
			    	.build();

			dragAndDrop.perform();
			
			fillListField(data);
			
			field = driver.findElement(By.cssSelector("figure[data-block-type='radio']"));
			field.click();	Thread.sleep(500);	
			field.click();	Thread.sleep(500);
			field.click();	Thread.sleep(500);
			field.click();	Thread.sleep(500);
			
			field = driver.findElement(By.cssSelector("figure[data-block-type='checkbox']"));
			field.click();	Thread.sleep(500);
			field.click();	Thread.sleep(500);	
			
			field = driver.findElement(By.cssSelector("figure[data-block-type='textarea']"));
			field.click();	Thread.sleep(500);
			
			field = driver.findElement(By.cssSelector("figure[data-block-type='textfield']"));
			field.click();
			
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector("section[class='signers-list']")));
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 
	
	public void fillListField(HashMap<String, String> data) {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement mostRecentField = driver.findElement(By.xpath("(//figure[@data-type='input'])[last()]"));
		WebElement viewSettings = driver.findElement(By.cssSelector("div[class='grid-layout designer-layout has-selection']"));
		mostRecentField.click();
		
		waitLoad.until(ExpectedConditions.visibilityOf(viewSettings));
		
		String settings = "(//figure[@data-type='input'])[last()]//div[@class='settings']";
		driver.findElement(By.xpath(settings)).click();
		
		String popup = "div[class='modal']";
		waitLoad.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(popup)));
		
		WebElement listName = driver.findElement(By.cssSelector("input[name=name]"));
		listName.click();
		listName.clear();
		slowKeys(data.get("listName"), listName);
		
		WebElement listList = driver.findElement(By.cssSelector("textarea[name=list]"));
		listList.click();
		listList.clear();
		slowKeys(data.get("listList1"), listList);
		listList.sendKeys(Keys.ENTER);
		slowKeys(data.get("listList2"), listList);
		listList.sendKeys(Keys.ENTER);
		slowKeys(data.get("listList3"), listList);
		
		driver.findElement(By.cssSelector("div[class=\"bottom-bar\"] > div")).click();		// click the save button
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveLayout(HashMap<String, String> data) {

		try {
			
			String saveLayout = "div[class='layout-actions-bar align-right'] button[class='icon-button'] span[class='icon-file-upload']";
			WebElement save = driver.findElement(By.cssSelector(saveLayout));
			
//			Actions hover = new Actions(driver);
//			hover.moveToElement(save);
//			String tooltip = "div[class='rc-tooltip rc-tooltip-placement-bottom']";
//			waitLoad.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tooltip)));
			save.click();
			
			String popup = "div[class='modal']";
			waitLoad.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(popup)));
			
			WebElement layoutName = driver.findElement(By.id("layoutTitle"));
			layoutName.click();
			layoutName.clear();
			slowKeys(data.get("layoutName"), layoutName);
			
			WebElement layoutDesc = driver.findElement(By.id("layoutDescription"));
			layoutDesc.click();
			layoutDesc.clear();
			slowKeys(data.get("layoutDesc"), layoutDesc);
			
			driver.findElement(By.cssSelector("div[class=\"bottom-bar\"] > div")).click();		// click the save button to save layout
			
			// checks if the layout already exists. if the element is present => isPresent will be 1
			Boolean isPresent = driver.findElements(By.xpath("(//div[@class=\"modal\"])[2]")).size() > 0;
			if (isPresent == true) {		// this overwrites the layout no matter what 
				driver.findElement(By.cssSelector("div[class=modal-actions] > div")).click();
			}	
			
			//TODO
//			WebElement snackbar = driver.findElement(By.cssSelector("div[class=\"snackbar\"]"));
//			waitLoad.until(ExpectedConditions.invisibilityOf(snackbar));
			
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public void sendToSign() {

		try {
			
			String click = "div[class=\"send-button-container\"]";
			driver.findElement(By.cssSelector(click)).click();

			String popup = "div[class='modal']";													// waits for the confirm pop up
			waitLoad.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(popup)));
			
			driver.findElement(By.cssSelector("div[class=\"bottom-bar\"] > div")).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

// ########### (MORE) HELPER FUNCTIONS ###########
//
//			   /\ /|
//			   \ V /
//			   | '')
//			   /  |
//			  /  \ \    -Felix Lee-
//			*(__\_\-
//

	public static void slowKeys(String text, WebElement element) {
		String[] array = text.split("");

		for (int i = 0; i < array.length; i++) {
//			System.out.println(array[i]);
			element.sendKeys(array[i]);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
		
	public static HashMap<String, String> convertExcelData(XSSFSheet sheet) {
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		int maxNumPairs = sheet.getPhysicalNumberOfRows();
		System.out.println(maxNumPairs);
		
		for (int i = 1; i < maxNumPairs; i++) {
			String key = sheet.getRow(i).getCell(0).getStringCellValue();	
			String value = sheet.getRow(i).getCell(1).getStringCellValue();		
	
			data.put(key, value);
		}
		
		return data;
	}

	public static void main(String[] args) throws Exception {
		
		File src = new File("P:\\Documents\\Excel_Data\\Selenium_Data.xlsx");		// apache POI
		FileInputStream fis = new FileInputStream(src);								// pass src into fis 
		XSSFWorkbook wb = new XSSFWorkbook(fis);									// this line loads the workbook (XML SpreadSheet Format)
		XSSFSheet sheet1 = wb.getSheetAt(0);										// accesses sheet 1 on excel

		// store all data in an array
		HashMap<String, String> data = new HashMap<String, String>();
		data = convertExcelData(sheet1);
		
//		System.out.println(data.toString());
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\huangti1\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window(); // maximize the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 	// synchronize the lines of code + page --
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);	// wait for the page to load
		
		sandbox myObj = new sandbox();
		
		myObj.invokeBrowser(data);		
		
		wb.close();
		fis.close();
	}

}
