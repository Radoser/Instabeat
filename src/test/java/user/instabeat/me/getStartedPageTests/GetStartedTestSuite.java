package user.instabeat.me.getStartedPageTests;

import org.testng.annotations.Test;

import user.instabeat.me.configTests.TestConfiguration;
import user.instabeat.me.pages.GetStartedPage;
import user.instabeat.me.pages.LoginPage;
import user.instabeat.me.pagesMainFunctions.Utils;

public class GetStartedTestSuite extends TestConfiguration{
	
	@Test(priority = 4, enabled = true)
	public void GetStartedValidation(){
				
		LoginPage onLoginPage = new LoginPage(driver);
		GetStartedPage onGetStartedPage = onLoginPage.clickOnGetStartedLink();
		
		Utils.Log.info("|Checking all fields...");
		
		onGetStartedPage.minHeightFieldValidation();
		onGetStartedPage.maxHeightFieldValidation();
		onGetStartedPage.firstNameValidation();
		onGetStartedPage.lastNameValidation();
		onGetStartedPage.emailValidation();
		onGetStartedPage.passwordValidation();
		onGetStartedPage.confirmPasswordValidation();
		onGetStartedPage.minWeightFieldValidation();
		onGetStartedPage.maxWeightFieldValidation();
		onGetStartedPage.defaultPoolLengthValidation();
		onGetStartedPage.goToLoginPage();		
		
		Utils.Log.info("|All fields are checked");		
	}

}
