package user.instabeat.me.fullRegisterTests;


import org.testng.Assert;
import org.testng.annotations.Test;

import user.instabeat.me.configTests.TestConfiguration;
import user.instabeat.me.dashboard.HomePage;
import user.instabeat.me.dashboard.ProfilePage;
import user.instabeat.me.dashboard.ProfilePageSettings;
import user.instabeat.me.pages.GetSartedFirstStep;
import user.instabeat.me.pages.GetStartedPage;
import user.instabeat.me.pages.GetStartedSecondStep;
import user.instabeat.me.pages.GetStartedThirdStep;
import user.instabeat.me.pages.LoginPage;
import user.instabeat.me.pagesMainFunctions.Utils;

public class RegistrationTestSuite extends TestConfiguration{

	@Test(groups = "Sanity", priority = 7)
	public void UserCanRegister() throws Exception {
				
		LoginPage onLoginPage = new LoginPage(driver);
		GetStartedPage onGetStartedPage = onLoginPage.clickOnGetStartedLink();
		onGetStartedPage.verifyGetStartedPage();
		
		/*First step of register*/
		Utils.Log.info("|Filling in user data...");
		onGetStartedPage.typeUserValues();
		
		Utils.Log.info("|Chossing user's date of birth...");
		onGetStartedPage.chooseMonthOfBitrh();
		onGetStartedPage.chooseDayOfBitrh();
		onGetStartedPage.chooseYearOfBitrh();
		
		Utils.Log.info("|Chossing user's country...");
		onGetStartedPage.chooseCountry();
		
		onGetStartedPage.chooseMetricsRandomly();
		
		GetSartedFirstStep onGetSartedFirstStep = onGetStartedPage.clickOnSignUpButton();

		Utils.Log.info("|Checking user email");
		Assert.assertTrue(onGetSartedFirstStep.checkUserEmail(onGetStartedPage.randomUser));
		
		/*Second step of register*/
		Utils.Log.info("|Getting confirmation link from IMAP server...");
		
		/*Here we need to wait for proper email*/
		Thread.sleep(3000);
		
		GetStartedSecondStep onGetStartedSecondStep = onGetSartedFirstStep.getConfirmationLink();
		Utils.Log.info("|Check second step of register - Download page");
		onGetStartedSecondStep.verifyGetInstabeatConnectText();
		onGetStartedSecondStep.downloadApp();
//		onGetStartedSecondStep.clickOnSave();
		
		/*Login from App*/
		Utils.Log.info("|Logging from App using new created user...");
		GetStartedThirdStep onGetStartedThirdStep = onGetStartedSecondStep.loginByApp(onGetStartedPage.randomUser);
		Utils.Log.info("|Check for success login from App");
		onGetStartedThirdStep.verifyTextPresentOnPage();
		
		/*Third step of register*/
		Utils.Log.info("|Filling in RHR data");
		onGetStartedThirdStep.typeRHRValue();
		onGetStartedThirdStep.clickOnCalculateButton();
		Thread.sleep(1000);
		HomePage onHomePage = onGetStartedThirdStep.clickOnUpdateButton(); 
		Utils.Log.info("|Check if registration is successfully");
		onHomePage.isCongratsPresent();
		onHomePage.isHomePagePresent();
			
		Utils.Log.info("|Logging out...");
		onLoginPage.logout();
		
		/*Check if registered random user can login*/
		Utils.Log.info("|Logging by new created Random user");
		onLoginPage.typeUserAfterRegister(onGetStartedPage.randomUser);
		onLoginPage.typeUserPassword();
		onLoginPage.LoginButton();
		
		/*Delete random user*/
		Utils.Log.info("|Deleting created Random User...");
		ProfilePage onProfilePage = onHomePage.clickOnProfileTab();
		ProfilePageSettings onProfilePageSettings = onProfilePage.clickOnProfilePageSettings();
		onProfilePageSettings.clickOnDeleteAccountButton();
		onProfilePageSettings.clickOnCancelButton();
		onProfilePageSettings.clickOnDeleteAccountButton();
		onProfilePageSettings.confirmDeleteAccout();
	}
	
}
