package user.instabeat.me.homeTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import user.instabeat.me.configTests.TestConfiguration;
import user.instabeat.me.dashboard.HomePage;
import user.instabeat.me.dashboard.ProfilePage;
import user.instabeat.me.dashboard.ProfilePageSettings;
import user.instabeat.me.pages.LoginPage;
import user.instabeat.me.pagesMainFunctions.Utils;

public class HomePageTestSuite extends TestConfiguration{
	
	@Test(groups = {"Sanity"}, priority = 8)
	public void UserCanChooseExistSession() {
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();

		Utils.Log.info("|Check if user logged in");
		Assert.assertTrue(onHomePage.isHomePagePresent());
		onHomePage.isCalendarButtonPresent();

		Utils.Log.info("|Check if user has date with sessions...");
		onHomePage.cliclOnCalendarButton();
		onHomePage.isDateWithSessionsPresent();
		
		Utils.Log.info("|Choosing date with sessions...");
		onHomePage.showDatesWithSessions();
		
		//need to fix month when only one day with sessions ----------!!!!!
		onHomePage.chooseDateWithSessionRandomly();
		
		Utils.Log.info("|Choose session");
		onHomePage.clickOnContextMenu();
		onHomePage.clickOnSession();

		Utils.Log.info("|Logging out...");
		onHomePage.logout();
	}
	
	@Test(groups = {"Sanity"}, priority = 9)
	public void ValidateValuesInFooter() throws Exception{
				
		LoginPage onLoginPage = new LoginPage(driver);
		
		Utils.Log.info("|Sending session to the server...");
		onLoginPage.sendSession();
		
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		HomePage onHomePage = onLoginPage.LoginButton();
		
		Utils.Log.info("|Check if user logged in");
		onLoginPage.isUserLoggedIn();
		
		Utils.Log.info("|Checking if duration time is proper...");
		onHomePage.checkIfDurationTimeIsProper();
		
		Utils.Log.info("|Checking if total distance is proper...");
		onHomePage.chheckIfTotalDistanceIsProper();
		
		//====>T O D O<====
		onHomePage.checkIfAvaragePaceIsProper(); /*need access to total laps duration on the server side*/
		
		Utils.Log.info("|Logging out...");
		onHomePage.logout();
	}
	
	@Test(groups = {"Sanity"}, priority = 10, enabled = false)
	public void editActivityCheck(){
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();
		
		Utils.Log.info("|Check if user logged in");
		Assert.assertTrue(onHomePage.isHomePagePresent());
		
		/*........*/
	}
	
	@Test(groups = {"Sanity"}, priority = 11)
	public void checkDefaultActivity () throws Exception {
				
		LoginPage onLoginPage = new LoginPage(driver);
		
		Utils.Log.info("|Sending session to the server...");
		onLoginPage.sendSession();
		
		Thread.sleep(3000);
		
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();

		Utils.Log.info("|Check if user logged in");
		onLoginPage.isUserLoggedIn();
		
		onHomePage.getDefaultActivityFromHomePage();
				
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		onProfilePageSettings.getDefaultActivityFromSettingsPage();
		
		onProfilePageSettings.compareIfTheActivityIsProperAfterSessionDownload();
		
		onProfilePageSettings.changeDefaultActivity();
		onProfilePageSettings.clickOnMetricUpdate();
		onProfilePageSettings.clickOnHomeTab();
		
		onHomePage.clickOnPlusButton();
		onHomePage.deleteOneSession();
		
		Utils.Log.info("|Sending session to the server...");
		onLoginPage.sendSession();
		
		onHomePage.compareIfTheActivityIsProperAfterSessionDownload();
	}

}
