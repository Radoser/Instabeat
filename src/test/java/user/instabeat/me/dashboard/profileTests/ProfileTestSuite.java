package user.instabeat.me.dashboard.profileTests;

import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.Test;

import user.instabeat.me.configTests.TestConfiguration;
import user.instabeat.me.dashboard.HomePage;
import user.instabeat.me.dashboard.ProfilePage;
import user.instabeat.me.dashboard.ProfilePageSettings;
import user.instabeat.me.pages.LoginPage;
import user.instabeat.me.pagesMainFunctions.Utils;

public class ProfileTestSuite extends TestConfiguration{
	
	@Test(groups = {"Sanity"}, priority = 6)
	public void UserCanUpdateProfile(){
		
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.typeUserEmail();
		onLoginPage.typeUserPassword();

		HomePage onHomePage = onLoginPage.LoginButton();

		Utils.Log.info("|Check if user logged in");
		onLoginPage.isUserLoggedIn();
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		
		Utils.Log.info("|Updating user data...");
		onProfilePage.updateFirstNameField();		
		onProfilePage.updateLastNameField();
		onProfilePage.updateHeightfield();
		onProfilePage.updateWeightField();
		onProfilePage.updateBirthdateField();
		onProfilePage.updateCountryField();
		onProfilePage.changeFitnessLevel();
		
		onProfilePage.clickOnUpdateButton();
		
		Utils.Log.info("|Check if updating is successfull");
		onProfilePage.checkMessageAboutUpdate(); 
		
		onProfilePage.isUserTitleNameEqualsUserName();
		
		Utils.Log.info("|Logging out...");
		onProfilePage.logout();
	}
	
	@Test(groups = {"Sanity"}, priority = 6)
	public void userCanChangeProfilePicture() throws IOException, InterruptedException, FindFailed{
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.typeUserEmail();
		onLoginPage.typeUserPassword();

		HomePage onHomePage = onLoginPage.LoginButton();

		Utils.Log.info("|Check if user logged in");
		onLoginPage.isUserLoggedIn();
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		
		Utils.Log.info("|Uploading new user's profile picture");
		onProfilePage.updateUserPicture();
		Utils.Log.info("|Picture is successfully loaded and checked");
		
		Utils.Log.info("|Logging out...");
		onProfilePage.logout();		
	}
	
	@Test(groups = {"Sanity"}, priority = 16)
	public void checkIfHeightIsProperAfterChanges() throws Exception {
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();
		
		Utils.Log.info("|Check if user logged in");
		onLoginPage.isUserLoggedIn();
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		Utils.Log.info("|Getting value from the Height field...");
		
		onProfilePage.getValuesFromHeightField();
		
		Utils.Log.info("|Change Height metric");
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		onProfilePageSettings.changeHeightUnit();
		onProfilePageSettings.clickOnMetricUpdate();
		
		Utils.Log.info("|Check if the Height metric is proper in the Profile page after changes");
		onHomePage.clickOnProfileTab();
		onProfilePage.clickOnProfilePageSettings();
		
		onProfilePageSettings.changeHeightUnit();
		onProfilePageSettings.clickOnMetricUpdate();
		
		onHomePage.clickOnProfileTab();
		onProfilePage.checkChangesInHeightField();
		Utils.Log.info("|The Height metric was proper changed (is the same)");
		
		Utils.Log.info("|Logging out...");
		onHomePage.logout();
	}
	
	@Test(groups = {"Sanity"}, priority = 17, enabled = false) //TO DO need to fix this test 					 
	public void checkIfHeightIsProperConverted() {
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();
		
		Utils.Log.info("|Check if user logged in");
		onLoginPage.isUserLoggedIn();
		onHomePage.isHomePagePresent();
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		Utils.Log.info("|Getting value from the Height field...");
		
//		onProfilePage.getValuesFromHeightField();
		onProfilePage.test();
		
		Utils.Log.info("|Change Height metric");
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		onProfilePageSettings.changeHeightUnit();
		onProfilePageSettings.clickOnMetricUpdate();
		
		Utils.Log.info("|Check if the Height metric is proper converted in the Profile page after changes");
	
		driver.navigate().refresh();
		onHomePage.clickOnProfileTab();
//		onProfilePage.getValuesFromHeightField();
		onProfilePage.test();
		onProfilePage.checkConvertedValues();
		Utils.Log.info("|The Height value was proper converted");
		
		Utils.Log.info("|Logging out...");
		onHomePage.logout();
	}
	
	@Test(groups = {"Sanity"}, priority = 18)
	public void checkIfWeightIsProperAfterChanges() throws Exception{
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();
		
		Utils.Log.info("|Check if user logged in");
		Assert.assertTrue(onHomePage.isHomePagePresent());
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		Utils.Log.info("|Getting value from the Weight field...");
		
		onProfilePage.getTheValueFromWeightField();
	
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		Utils.Log.info("|Change Weight metric");
		onProfilePageSettings.changeWeightUnit();
		onProfilePageSettings.clickOnMetricUpdate();
		onProfilePageSettings.checkTheMessageAboutUpdate();
		
		Utils.Log.info("|Check if the Weight metric is proper in the Profile page");
		
		onProfilePageSettings.changeWeightUnit();
		onProfilePageSettings.clickOnMetricUpdate();
		onProfilePageSettings.checkTheMessageAboutUpdate();
		onHomePage.clickOnProfileTab();

		onProfilePage.checkChangesInWeightField();
		Utils.Log.info("|The Weight metric was proper changed (is the same)");
		
		Utils.Log.info("|Logging out...");
		onHomePage.logout();
	}
	
	@Test(groups = {"Sanity"}, priority = 19) //NEED to add test: check the weight and height after click on Update BUT only after developer fix it
	public void checkIfWeightIsProperConverted() {
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();
		
		Utils.Log.info("|Check if user logged in");
		Assert.assertTrue(onHomePage.isHomePagePresent());
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		Utils.Log.info("|Getting value from the Weight field...");
		
		onProfilePage.getTheValueFromWeightField();
	
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		Utils.Log.info("|Change Weight metric");
		onProfilePageSettings.changeWeightUnit();
		onProfilePageSettings.clickOnMetricUpdate();
		onProfilePageSettings.checkTheMessageAboutUpdate();
		
		Utils.Log.info("|Check if the Weight metric is proper converted in the Profile page after changes");
		
		onHomePage.clickOnProfileTab();
		onProfilePage.getTheValueFromWeightField();
		onProfilePage.checkChangesInWeightAfterConvert();
		
		Utils.Log.info("|The Weight value was proper converted");
		
		Utils.Log.info("|Logging out...");
		onHomePage.logout();
	}
	
	@Test(priority = 4)
	public void ProfileValidation() throws InterruptedException{
				
		LoginPage onLoginPage = new LoginPage(driver);
		
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		HomePage onHomePage = onLoginPage.LoginButton();
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		
		Utils.Log.info("|Checking all fields...");
		
		onProfilePage.firstNameValidation();
		onProfilePage.lastNameValidation();
		onProfilePage.minHeightFieldValidation();
		onProfilePage.maxHeightFieldValidation();
		onProfilePage.minWeightFieldValidation();
		onProfilePage.maxWeightFieldValidation();
		
		Utils.Log.info("|Fields are checked");		
		onProfilePage.logout();
	}

}
