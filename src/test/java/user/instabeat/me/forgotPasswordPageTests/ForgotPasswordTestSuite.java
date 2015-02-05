package user.instabeat.me.forgotPasswordPageTests;


import org.testng.annotations.Test;

import user.instabeat.me.configTests.TestConfiguration;
import user.instabeat.me.pages.ForgotPasswordPage;
import user.instabeat.me.pages.LoginPage;
import user.instabeat.me.pagesMainFunctions.Utils;

public class ForgotPasswordTestSuite extends TestConfiguration{

	@Test(priority = 5)
	public void ResetPasswordValidation() throws InterruptedException{
				
		LoginPage onLoginPage = new LoginPage(this.driver);
		ForgotPasswordPage onForgotPasswordPage =  onLoginPage.clickOnForgotPasswordLink();
		
		Utils.Log.info("|Checking not existing user email");
		onForgotPasswordPage.typeWrongUserEmail();
		onForgotPasswordPage.checkErrorMessage();

		Utils.Log.info("|Checking incorrect user email");
		onForgotPasswordPage.emailFieldValidation();
		onForgotPasswordPage.LoginLink.click();
	}
	
}
