package user.instabeat.me.pagesMainFunctions;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.testng.Assert;

import user.instabeat.me.driver.CreateDriver;

public class PagesMainFunctions extends PagesWebElements{

	Random random = new Random();
	protected ParametersManager parameters;
	protected String userEmail;
	protected String userPassword;
	protected String stagingUrl;
	protected String FBUser;
	protected String twitterUser;
	protected String twitterUserPassword;
	private static final String SCREENSHOT_FOLDER = "images/screenshots";
	private static final String SCREENSHOT_FORMAT = ".png";
	private static String fileName;
	
	public PagesMainFunctions(WebDriver driver) {	
		super(driver);
		Utils.logFile();
		parameters = new ParametersManager();
		parameters.getPropertyFields();
		userEmail = PropertiesXml.loadProperty("test.user");
		userPassword = PropertiesXml.loadProperty("test.userPassword");
		stagingUrl = PropertiesXml.loadProperty("staging.url");
		FBUser = PropertiesXml.loadProperty("FBUser");
		twitterUser = PropertiesXml.loadProperty("twitterUser");
		twitterUserPassword = PropertiesXml.loadProperty("twitterUserPassword");
	}


	public String randomUser;// = "testusergl"+random.nextInt()+"@ukr.net";
	public String randomValues; // ="Name"+RandomStringUtils.randomAlphabetic(5);
	public String randomNumbers;

	public void createRandomUser() {
		randomUser = "fortestgl+" + random.nextInt(Integer.MAX_VALUE)
				+ "@gmail.com";
	}

	public void createRandomValues(int numberOfLetters) {
		randomValues = "Name"
				+ RandomStringUtils.randomAlphabetic(numberOfLetters);
	}

	public void createRandomNumbers(int minValue, int maxValue) {
		int min = minValue;
		int max = maxValue;
		randomNumbers = random.nextInt(max - min) + min + "";
	}
	
	public void chooseRandomValuesFromDropDownList(WebElement listField){
		
		Random random = new Random();
		List<WebElement> options = new Select(listField).getOptions();

		WebElement element = options.get(random.nextInt(options.size()));

		element.click();

		waitPage();
	}

	public void values(WebElement element, String values) {
		element.sendKeys(values);
	}

	public void click(By by) {
		driver.findElement(by).click();
	}

	public boolean verifyPageContent(String data) {
		waitUntillElementWillBeVisible(HomeTab, 5);
		Assert.assertTrue(driver.getPageSource().contains(data), "Expected content " + data + " was not found");
		return driver.getPageSource().contains(data);
	}

	public void logout() {
		LogoutLink.click();
		waitUntillElementWillBeVisible(LoginButton, 5);
	}

	public void GoToIMAPServer() throws Exception {
		driver.navigate().to("http://" + ImapGmail.GetConfirmationLink());
	}

	public void LoginApp(String value) throws Exception {
		JSONObject response = ApplicationLogin.AppLogin(value);
		String username = response.getString("user");
		String usertoken = response.getString("token");
		driver.get(stagingUrl + "appconfirm?user=" + username
				+ "&token=" + usertoken + "&status=PARTIAL");
		/*driver.get("http://user.instabeat.me/user/appconfirm?user=" + username
				+ "&token=" + usertoken + "&status=PARTIAL");*/
	}

	public void sendSessionFromDevice() throws Exception{
		PostSessions.postSession();
	}

	public void GetAllLinksOnPage() {

		List<WebElement> linkElement = driver.findElements(By.tagName("a"));
		String [] elements = new String [linkElement.size()]; 
		int i = 0;

		for (WebElement e : linkElement) {
			elements[i] = e.getAttribute("href");
			i++;
		}

		for (int a = 0; a<elements.length; a++){
			driver.navigate().to(elements[a]);
			Utils.Log.info("|Got to page " + "~" + driver.getTitle() + "~" + " by link " + elements[a]);
//			driver.navigate().back();
		}
		Utils.Log.info("|An amount of links on the page are: "+linkElement.size());
		Utils.Log.info("|An amount of passed links are: "+elements.length);
		driver.navigate().to(stagingUrl);
	}

	public void linksTest() {

		List<WebElement> linkElement = driver.findElements(By.tagName("a"));
		String[] linkText = new String[linkElement.size()];
		int i = 0;

		for (WebElement e : linkElement) {
			linkText[i] = e.getText();
			i++;
		}

		for (String t : linkText) {

			driver.navigate().to(t);
			driver.findElement(By.linkText(t)).click();
			if (driver.getTitle().equals("Login")) {

				System.out.println("\"" + t + "\"" + "--------->is out");
			} else {
				System.out.println("\"" + t + "\"" + "--------->>is working");
			}
			driver.navigate().back();
		}
	}
	public boolean verificationOfElementsOnPages(WebElement element) {
		try {
			element.isDisplayed();
			Utils.Log.info("|Element " + element + " is PRESENT!!!");
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void typeValuesForValidation(String error, List<String> values, WebElement field, WebElement button, WebElement errorElement){

		for (int i = 0; i < values.size(); ++i) {
			clearField(field);
			field.sendKeys(values.get(i));
			getTheValueFromFieldUsingJS(field, "id");
			button.click();
			waitUntillMessageAppears(errorElement, error, 10);
			Assert.assertEquals(error, errorElement.getText(), "Expected " + "\'" + error + "\'"  + " error is not equals to Actual " + "\'" + errorElement.getText() + "\'" + "error");
			field.clear();
		}
	}

	public boolean isCongratsMessagePresent(String text, WebElement message){
		verificationOfElementsOnPages(message);
		return verifyPageContent(text);
	}

	public static String stripNonDigits(final CharSequence input){

		final StringBuilder sb = new StringBuilder(input.length());
		for(int i = 0; i < input.length(); i++){
			final char c = input.charAt(i);
			if(c > 47 && c < 58){
				sb.append(c);
			}
		}
		return sb.toString();
	}
		
	public String getTheValueFromFields(WebElement field) {		
		return field.getAttribute("value");
	}
	
	public String getTheValueFromFieldUsingJS(WebElement field, String attributeOfField) {
		if ((!(field.getAttribute("id").isEmpty())) && (field.getAttribute("id").length()>0)){
			
			String valueFromField = (String) ((JavascriptExecutor) driver ).executeScript("return $('input').filter(':visible').filter('#"+field.getAttribute(attributeOfField)+"').val()", field); 
			Utils.Log.info("The value from " + "\"" + field.getAttribute(attributeOfField) + "\"" + " field is: " + valueFromField);
			return valueFromField;
		}else {
			return "The field " + field + " is empty OR check the field id";
		}	
	}
	

	public String getTheTextFromElementUsingJS(String attribute){
		return (String)((JavascriptExecutor) driver).executeScript("return document.getElementsByClassName(arguments[0])[0].innerHTML;", attribute);
	}
	
	
	public void clearField(WebElement field){
		field.clear();
	}
	
	public static void waitPage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void delay(final long amount) {
		try {
			Thread.sleep(amount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebElement getRandomFromList(List<WebElement> list){
		int size = list.size();
		int item = new Random().nextInt(size); 
		int i = 0;
		for(WebElement obj : list)
		{
			if (i == item)
				return obj;
			i = i + 1;

		}
		return null;
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	public boolean isElementPresent(WebElement element) {
        try {
        	element.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	
	public void WaitForTextPresent(WebElement webelement, String text) throws InterruptedException
    {
        int waitRetryDelayMs = 100;
        int timeOut = 500; 
        boolean first = true; 

        for (int milliSecond = 0; ; milliSecond += waitRetryDelayMs)
        {
            if (milliSecond > timeOut * 1000)
            {
                System.out.println("Timeout: Text '" + text + "' is not found ");
                break;
            }

            if (webelement.getText().contains(text))
            {
                if (!first) System.out.println("Text is found: '" + text + "'");
                break;
            }

            if (first) System.out.println("Waiting for text is present: '" + text + "'");

            first = false;
            Thread.sleep(waitRetryDelayMs);
        }
    }
	
	
	public void threadWait (int seconds){
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public WebElement waitUntillElementWillBeVisible(WebElement element, int seconds){
		return (new WebDriverWait(driver, seconds * 1000)).until(ExpectedConditions.visibilityOf(element));
	}
	
	public boolean waitUntillMessageAppears(WebElement element, String message, int seconds){
		return (new WebDriverWait(driver, seconds * 1000)).until(ExpectedConditions.textToBePresentInElement(element, message));
	}
	
		
	public boolean pictureExist(String picture) throws FindFailed{
			
		LoadImageButton.click();
		Utils.Log.info("|Loading image: " + picture);
		
		Screen screen = new Screen();
		
		ImagePath.add("D:\\Java\\Projects\\webAutomation\\images\\");
		String pathForScreenshot = "D:\\Java\\Projects\\webAutomation\\images\\screenshots\\";
		
		screen.type("type.png", "D:\\Java\\Projects\\webAutomation\\images\\" + picture);
		screen.click("open.png");
		
		waitUntillElementWillBeVisible(LoadingGIFImage, 8);
		Utils.Log.info("|Image is loading...");
           		
		waitUntillElementWillBeVisible(LoadedImage, 8);
		Utils.Log.info("|Image is loaded");
		
		Match findMatches = screen.exists(pathForScreenshot + takeScreenshot());
		
		if(findMatches != null) {
			Utils.Log.info("|Found matches: " + findMatches);
			Utils.Log.info("|The score of matches is: " + findMatches.getScore());
			return true;
		}else{
			Utils.Log.info("|Matches didn't find. Check the image you loaded or the screenshot that was made");
			return false;
		}
	}
	
	/*public boolean checkForImage2(String image){
		
		Screen screen = new Screen(0);
		ImagePath.setBundlePath("D:\\Java\\Projects\\webAutomation\\images\\");
		Match m = screen.exists(image);
		
		System.out.println("Loading is PASSED");
		 if(m != null)
         {
            	System.out.println("YES !!!!!!!!!! " + m);
                 return true;
           }
          else
          {
        	  System.out.println("NO !!!!! " + m);
        	 
             return false;
          }
	}
*/
/*	public void checkForImage(String imageLocation) throws IOException, FindFailed, InterruptedException{
		
		String screen = "D:\\Java\\Projects\\webAutomation\\images\\screenshots\\" + takeScreenshot();
		System.out.println("Screenshot file name: " + screen);
		
		ImagePath.setBundlePath("D:\\Java\\Projects\\webAutomation\\images\\");
			
			Pattern avatar = new Pattern(imageLocation);
			
			Finder f = new Finder(screen);
			
			System.out.println(f.find(avatar));
			System.out.println(f.hasNext());
			
			f.find(avatar);
			
			while (f.hasNext()){
				System.out.println("Found: " + f.next());
				if (f.hasNext()){
					break;
				}
			}
			f.hasNext();
			System.out.println(f.hasNext() + "=-=-=-=-=-=-=-=-=-=-");
			f.destroy();	
	}
	*/
	
	public String takeScreenshot(){
		//creating screenshot folder for test
				String folder = SCREENSHOT_FOLDER + "/";
				File dir = new File(folder);
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				try {
					// Pause because sometimes webdriver takes previous page screenshot
					Thread.sleep(3000);
					// Taking webDriver screenshot
					File screenFile = ((TakesScreenshot) CreateDriver.getSetDriver()).getScreenshotAs(OutputType.FILE);
					// Setting screenshot file name 'testMethodName_01_12_14_14_11_09.png'
					SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
					fileName = formater.format(Calendar.getInstance().getTime()) + SCREENSHOT_FORMAT;
					// Put screen file to appropriate folder
					FileUtils.copyFile(screenFile, new File(dir.getAbsoluteFile() + "/" + fileName));
					// Get cannonical screenshot path on disc and link it to reporter output with html code
					// <a href="E:\web.framework/target/screenshots/signInDisapearing/signInDisapearing_30_11_2014_02_48_34.png" 
					// target="_blank"><br><img 
					// src="file:///E:\web.framework/target/screenshots/signInDisapearing/signInDisapearing_30_11_2014_02_48_34.png" 
					// width="600" height="338" alt=""><br></a>
					File directory = new File(".");
					String cannonicalScreenshotsPath = directory.getCanonicalPath();
				} catch (WebDriverException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return fileName;
			}
	
	public void makeScreenShot (String name) throws IOException{
		File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShotFile, new File("D:\\" + name + ".png"));
	}
	
	public void checkTheGreenNotificationMessage(String greenMessage) {
		waitUntillMessageAppears(greenNotification, greenMessage, 5);
		Assert.assertTrue(verificationOfElementsOnPages(greenNotification), "Green notification box is not displayed after update");
		Assert.assertEquals(greenNotification.getText(), greenMessage, "The green notification is not equal to expected one: ");
	}
}
