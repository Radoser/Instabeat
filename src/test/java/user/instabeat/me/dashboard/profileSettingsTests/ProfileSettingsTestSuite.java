package user.instabeat.me.dashboard.profileSettingsTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import user.instabeat.me.configTests.TestConfiguration;
import user.instabeat.me.dashboard.HomePage;
import user.instabeat.me.dashboard.ProfilePage;
import user.instabeat.me.dashboard.ProfilePageSettings;
import user.instabeat.me.pages.LoginPage;
import user.instabeat.me.pagesMainFunctions.Utils;

public class ProfileSettingsTestSuite extends TestConfiguration{
	
	@Test(groups = {"Sanity"}, priority = 7)
	public void userCanChangePassword(){
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();

		Utils.Log.info("|Check if user logged in");
		onHomePage.isHomePagePresent();
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		Utils.Log.info("|Changing user's password...");
		onProfilePageSettings.typeOldPassword();
		onProfilePageSettings.typeNewPassword();
		onProfilePageSettings.typeConfirmNewPassword();
		
		onProfilePageSettings.clickOnChangePasswordButton();

		onProfilePageSettings.checkIfPasswordSucChanged();
		Utils.Log.info("|Password was successfully changed");
		
		Utils.Log.info("|Logging out...");
		onProfilePageSettings.logout();
	}
	
	@Test(groups = {"Sanity"}, priority = 8)
	public void userCanUpdateProfileSettings(){
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();

		Utils.Log.info("|Check if user logged in");
		onHomePage.isHomePagePresent();
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		Utils.Log.info("|Updating profile settings");
		onProfilePageSettings.changeSettingsRandomly();
		
		onProfilePageSettings.clickOnMetricUpdate();
		
		Utils.Log.info("|Logging out...");
		onProfilePageSettings.logout();
	}
	
	@Test(priority = 9, enabled = false)
	public void userCanDeleteAllSessions(){
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();

		Utils.Log.info("|Check if user logged in");
		Assert.assertTrue(onHomePage.isHomePagePresent());
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		Utils.Log.info("|Deleting all sessions...");
		onProfilePageSettings.clickOnEraseDataButton();
		onProfilePageSettings.checkIfEraseWindowOpened();
		onProfilePageSettings.clickOnCancelButton();
		onProfilePageSettings.clickOnEraseDataButton();
		onProfilePageSettings.confirmEraseAllSessions();
		
		Utils.Log.info("|Check if sessions exist");
		onHomePage.isDateWithSessionsPresent();
		
		Utils.Log.info("|Logging out...");
		onHomePage.logout();
	}
	
	@Test(priority = 5)
	public void ProfileSettingsValidation(){
				
		LoginPage onLoginPage = new LoginPage(driver);
		
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		HomePage onHomePage = onLoginPage.LoginButton();
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		Utils.Log.info("|Checking password fields with random data");
		onProfilePageSettings.passwordsFieldsValidation();
		
		Utils.Log.info("|Checking password fields in a different order");
		onProfilePageSettings.passwordsFieldsValidationForDifferentCases();
		
		Utils.Log.info("|Fields are checked");		
		onProfilePageSettings.logout();
	}

}
