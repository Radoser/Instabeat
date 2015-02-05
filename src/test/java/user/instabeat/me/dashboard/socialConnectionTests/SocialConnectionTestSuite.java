package user.instabeat.me.dashboard.socialConnectionTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import user.instabeat.me.configTests.TestConfiguration;
import user.instabeat.me.dashboard.HomePage;
import user.instabeat.me.dashboard.ProfilePage;
import user.instabeat.me.dashboard.ProfilePageSettings;
import user.instabeat.me.pages.LoginPage;
import user.instabeat.me.pagesMainFunctions.Utils;

public class SocialConnectionTestSuite extends TestConfiguration{

	@Test(groups = {"Sanity"}, priority = 25, enabled = false)
	public void userCanConnectToFB(){	
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();
		
		Utils.Log.info("|Check if user logged in");
		Assert.assertTrue(onHomePage.isHomePagePresent());
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		Utils.Log.info("|Connecting to FB...");
		onProfilePageSettings.FaceBookConnect();
		
		onProfilePageSettings.checkIfFBConnectionSuc();
		
		Utils.Log.info("|Logging out...");
		onProfilePageSettings.logout();
		Utils.Log.info("<<-----Finishing running test-----< \n---------------------------------------------------");
	}
	
	@Test(groups = {"Sanity"}, priority = 26, enabled = false)
	public void UserCanShareGraphFB(){
		
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();
		
		Utils.Log.info("|Check if user logged in");
		Assert.assertTrue(onHomePage.isHomePagePresent());
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		Utils.Log.info("|Connecting to FB...");
		onProfilePageSettings.FaceBookConnect();
		
		onProfilePageSettings.clickOnHomeTab();
				
		Utils.Log.info("|Sharing data to FB...");
		onHomePage.clickOnPlusButton();
		onHomePage.clickOnFBShareButton();
		onHomePage.checkIfFBShareWindowOpened();
		
		
		onHomePage.cancelShareDataFB();
		onHomePage.clickOnFBShareButton();
		onHomePage.confirmShareDataFB();
		
		Utils.Log.info("|Logging out...");
		onHomePage.logout();
		Utils.Log.info("<<-----Finishing running test-----< \n---------------------------------------------------");
		
	}
	
	@Test(groups = {"Sanity"}, priority = 27, enabled = false)
	public void UserCanConnectToTwitter(){
				
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();

		Utils.Log.info("|Check if user logged in");
		Assert.assertTrue(onHomePage.isHomePagePresent());
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		Utils.Log.info("|Connecting to Twitter...");
		onProfilePageSettings.TwitterConnect();
		
		Utils.Log.info("|Checking if connection is success");
		onHomePage.clickOnProfileTab();
		onProfilePage.clickOnProfilePageSettings();
		
		onProfilePageSettings.checkIfTwitterConnectionSuc();
		
		Utils.Log.info("|Logging out...");
		onHomePage.logout();
		Utils.Log.info("<<-----Finishing running test-----< \n---------------------------------------------------");
	}
	
	@Test(groups = {"Sanity"}, priority = 28, enabled = false)
	public void UserCanShareGraphTwitter(){
		
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();

		Utils.Log.info("|Check if user logged in");
		Assert.assertTrue(onHomePage.isHomePagePresent());
		
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		
		Utils.Log.info("|Connecting to Twitter...");
		onProfilePageSettings.TwitterConnect();
		
		onProfilePageSettings.clickOnHomeTab();
		
		Utils.Log.info("|Sharing data to Twitter...");
		onHomePage.clickOnPlusButton();
		onHomePage.clickOnTwitterShareButton();
		onHomePage.checkIfTwitterShareWindowOpened();
		
		onHomePage.cancelShareDataFB();
		onHomePage.clickOnTwitterShareButton();
		onHomePage.confirmShareDataFB();
		
		onHomePage.checkIfShareIsSucced();
		
		Utils.Log.info("|Logging out...");
		onHomePage.logout();
		Utils.Log.info("<<-----Finishing running test-----< \n---------------------------------------------------");
	}
	
}
