package user.instabeat.me.dashboard;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;
import user.instabeat.me.pagesMainFunctions.Utils;

public class ProfilePageSettings extends PagesMainFunctions{

	public ProfilePageSettings(WebDriver driver) {
		super(driver);
		createRandomNumbers(100000, 99999999);
	}

	public void typeOldPassword() {
		OldPasswordField.sendKeys(userPassword);
	}

	public void typeNewPassword() {
		NewUserProfilePasswordField.sendKeys(userPassword);
	}

	public void typeConfirmNewPassword() {
		ConfirmNewUserProfilePasswordField.sendKeys(userPassword);
	}

	public void clickOnChangePasswordButton() {
		PasswordUpdateButton.click();
	}

	public void changeSettingsRandomly() {
		List<WebElement> a = driver.findElements(By.xpath("//*[@class ='col-md-9 col-xs-9 custom-inputs']//span[not(self::span[@id | @role])]"));
		for (WebElement b : a){
			b = a.get(new Random().nextInt(a.size()));
			b.click();
			Utils.Log.info("Chosen value: " + b.getText());
		}
		WebElement element = Utils.getRandomFromList(driver.findElement(By.xpath("//select[@name = 'pool_distance_metric']")).findElements(By.tagName("option")));
		element.click();
		Utils.Log.info("Chosen value: " + element.getText());
	}
	
	public void changeAllUnits() {
		List<WebElement> inactiveUnits = driver.findElements(By.cssSelector(".toggle input:not(:checked) + span"));
		List<WebElement> activeUnits  = driver.findElements(By.cssSelector(".toggle input:checked + span"));

		for (WebElement listOfActive : activeUnits){
			System.out.println(listOfActive.getText());
		}

		for (WebElement list : inactiveUnits){
			list.click();
			System.out.println(list.getText());
		}
	}
	
	public void changeHeightUnit(){
		Utils.Log.info("|Height is changed to: " + NotActiveHeightUnit.getText());
		waitUntillElementWillBeVisible(NotActiveHeightUnit, 5);
		NotActiveHeightUnit.click();			
	}
	
	public void changeWeightUnit() {
		Utils.Log.info("|Weight is changed to: " + NotActiveWeightUnit.getText());
		waitUntillElementWillBeVisible(NotActiveWeightUnit, 5);
		NotActiveWeightUnit.click();
	}
	
	public void changeDefaultActivity() {
		NotActiveDefaultActivity.click();
	}
	
	public void changeDefaultPoolLength() {
		
	}
	
	public void changeDefaultPoolLengthMetric() {
		
	}
	
	public void changeDistanceUnit() {
		NotActiveDefaultActivity.click();
	}
	
	public void clickOnMetricUpdate() {
		MetricUpdateButton.click();		
	}

	public void clickOnEraseDataButton() {
		SessionsEraseButton.click();
	}

	public void checkIfEraseWindowOpened() {
		String text = "Are you sure you want to erase your data? Erased data cannot be recovered!";
		Assert.assertEquals(text, EraseTextWindow.getText());
	}

	public void confirmEraseAllSessions() {
		OkButtonForDelete.click();
	}

	public void clickOnCancelButton() {
		CancelButton.click();
	}

	public HomePage clickOnHomeTab() {
		HomeTab.click();
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public ProfilePage clickOnProfilePageLink () {
		ProfileLinkOnSettingsPage.click();
		return PageFactory.initElements(driver, ProfilePage.class);
	}

	public void clickOnDeleteAccountButton() {
		AccountDeleteButton.click();
	}

	public void checkIfDeleteWindowOpened() {
		String text = "Are you sure you want to delete your account? Deleted accounts cannot be recovered!";
		Assert.assertEquals(text, EraseTextWindow.getText());
	}
	
	public void checkIfPasswordSucChanged(){
		waitUntillElementWillBeVisible(greenNotification, 5);
		
		System.out.println(getTheTextFromElementUsingJS("tooltipspan"));
		System.out.println(greenNotification.getText());
		
		Assert.assertTrue(verificationOfElementsOnPages(greenNotification), "Green notification box is not displayed after Profile update");
		Assert.assertEquals(greenNotification.getText(), parameters.SMchangedPassword);
	}

	public void confirmDeleteAccout() {
		OkButtonForDelete.click();
	}

	public void FaceBookConnect() {

		String parent = driver.getWindowHandle();

		if (ConnectToFBButton.getAttribute("value").contains("Click to unlink")){
			ConnectToFBButton.click();		
		} 
		ConnectToFBButton.click();

		for (String child : driver.getWindowHandles()) {		
			driver.switchTo().window(child);
		}	

		if(driver.getTitle().equals("Facebook")){	
			System.out.println(driver.getTitle());
			FBEmailField.sendKeys(FBUser);
			FBPasswordField.sendKeys(userPassword);
			FBLoginButton.click();
		}
		
		if(driver.getTitle().equals("Log in with Facebook")){
			boolean switcher = driver.getTitle().equals("Log in with Facebook");
			while(switcher){
				FBOKButton.click();	
//				Utils.delay(2000);
				try{
					switcher = driver.getTitle().equals("Log in with Facebook");
				}catch(NoSuchWindowException e) {
					switcher = false;
				}
			}
		}
		driver.switchTo().window(parent);
	}

	public void checkErrorMessage() {
		Assert.assertEquals(parameters.EMuserNotFound, ResetPassErrorMessages.getText());
	}

	public void passwordsFieldsValidation(){
		List<WebElement> fields = Arrays.asList(OldPasswordField, NewUserProfilePasswordField, ConfirmNewUserProfilePasswordField);
		List<String> errors = Arrays.asList(parameters.EMnewPasswordRequiered, parameters.EMnewPasswordRequiered, parameters.EMconfirmRequired);

		for(int i = 0; i<fields.size(); i++){
			do {	
				fields.get(i).sendKeys(randomNumbers);	
				PasswordUpdateButton.click();
				//				Utils.waitPage();
			}
			while(DashboardErrorMessages.getText() == errors.get(i));
		}
	}

	public void passwordsFieldsValidationForDifferentCases(){

		clearField(OldPasswordField);
		do {
			PasswordUpdateButton.click();
		}while(DashboardErrorMessages.getText() == parameters.EMoldPasswordRequired);

		do{
			OldPasswordField.sendKeys(userPassword);
			clearField(NewUserProfilePasswordField);
			PasswordUpdateButton.click();
		}while (DashboardErrorMessages.getText() == parameters.EMconfirmRequired);

		do{
			NewUserProfilePasswordField.sendKeys(userPassword);
			clearField(ConfirmNewUserProfilePasswordField);
			PasswordUpdateButton.click();
		}while(DashboardErrorMessages.getText() == parameters.EMconfirmRequired);
	}

	public void checkIfFBConnectionSuc(){
		Assert.assertTrue(ConnectToFBButton.getAttribute("value").contains("Click to unlink"));
	}

	public void TwitterConnect() {
		if (ConnectToTwitterButton.getAttribute("value").contains("Click to unlink")){
			ConnectToTwitterButton.click();
		}
		ConnectToTwitterButton.click();
		
		if(driver.getPageSource().contains("Sign up for Twitter")){
			TwitterEmailField.sendKeys(twitterUser);
			TwitterPasswordField.sendKeys(twitterUserPassword);
			TwitterAuthorizeButton.click();	
		}
		
		if(!driver.getPageSource().contains("Sign up for Twitter")){
			TwitterAuthorizeButton.click();
		}
	}
	
	public void confirmTwitterConnection(){
		
	}

	public void checkIfTwitterConnectionSuc() {
		Assert.assertTrue(ConnectToTwitterButton.getAttribute("value").contains("Click to unlink"));
	}

	public static String textOnSettings; 
	public String getDefaultActivityFromSettingsPage(){
		textOnSettings = ActiveDefaultActivity.getText();
		Utils.Log.info("The activity is in " + ActiveDefaultActivity.getText() + " on Settings page");
		return textOnSettings.toLowerCase() ;
	}
	
	public void compareIfTheActivityIsProperAfterSessionDownload() {
		
			if (HomePage.textOnHome.equals("Open water")){
				waitUntillElementWillBeVisible(ActiveDefaultActivity, 5);
				Assert.assertTrue(ActiveDefaultActivity.getText().contains("Open Water"));
			} else if (HomePage.textOnHome.equals(" yd pool") || HomePage.textOnHome.equals(" m pool")){
				waitUntillElementWillBeVisible(ActiveDefaultActivity, 5);
				Assert.assertTrue(ActiveDefaultActivity.getText().contains("Pool"));
			}else {
				Utils.Log.info("|Something went wrong! Go check it out!");
		}
	}

	public void checkTheMessageAboutUpdate() {
		checkTheGreenNotificationMessage(parameters.SMprofileSettingsUpdate);
	}
	
}
