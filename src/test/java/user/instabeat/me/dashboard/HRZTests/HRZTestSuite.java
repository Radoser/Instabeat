package user.instabeat.me.dashboard.HRZTests;

import org.testng.annotations.Test;

import user.instabeat.me.configTests.TestConfiguration;
import user.instabeat.me.dashboard.HeartRateZonesPage;
import user.instabeat.me.dashboard.HomePage;
import user.instabeat.me.pages.LoginPage;
import user.instabeat.me.pagesMainFunctions.Utils;

public class HRZTestSuite extends TestConfiguration{
	
	@Test(groups = {"Sanity"}, priority = 23, enabled = false)
	public void userCanUpdateHRZPage(){
			
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();
		HeartRateZonesPage onHeartRateZonesPage = onHomePage.clickOnHRZTab();
		
		/*Change heart rate using RHR field*/
		Utils.Log.info("|Change HR zones using RHR field");
		onHeartRateZonesPage.typeRHR();
		onHeartRateZonesPage.clickOnCalculateButton();
		onHeartRateZonesPage.clickOnUpdateButton();
		
		Utils.Log.info("|Check if HR updated");
		onHeartRateZonesPage.isSuccessMessagePresent();
		
		/*Change heart rate in boxes randomly*/
		Utils.Log.info("|Change HR zones using boxes with random data");
		onHeartRateZonesPage.typeHeartRateIntoBoxes();
		
		onHeartRateZonesPage.clickOnUpdateButton();
		Utils.Log.info("|Check if HR updated");
		onHeartRateZonesPage.isSuccessMessagePresent();
		
		Utils.Log.info("|Logging out...");
		onHeartRateZonesPage.logout();
	}
	
	@Test(priority = 24, enabled = false)
	public void HeartRateZonesValidation(){
			
		LoginPage onLoginPage = new LoginPage(driver);
		Utils.Log.info("|Logging in...");
		onLoginPage.fullLogin();
		
		HomePage onHomePage = onLoginPage.LoginButton();
		HeartRateZonesPage onHeartRateZonesPage = onHomePage.clickOnHRZTab();
		
		Utils.Log.info("|Checking HR zones using RHR field");
		onHeartRateZonesPage.RHRFieldValidation();
		
		Utils.Log.info("|Checking HR zones using HR fields");
		onHeartRateZonesPage.RHZFieldsValidation();
		
		Utils.Log.info("|Fields are checked");		
		onHeartRateZonesPage.logout();
	}

}
