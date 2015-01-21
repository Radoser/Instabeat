package user.instabeat.me.forgotPasswordTests;

import org.testng.annotations.Test;

import user.instabeat.me.configTests.TestConfiguration;
import user.instabeat.me.pages.ForgotPasswordPage;
import user.instabeat.me.pages.ForgotPasswordPageResults;
import user.instabeat.me.pages.LoginPage;
import user.instabeat.me.pages.ResetPasswordPage;
import user.instabeat.me.pagesMainFunctions.Utils;

public class ForgotPasswordTestsSuite extends TestConfiguration{

	@Test(groups = {"Sanity"}, priority = 3)
	public void UserForgotPassword() throws Exception {
			
		LoginPage onLoginPage = new LoginPage(driver);
		ForgotPasswordPage onForgotPasswordPage = onLoginPage.clickOnForgotPasswordLink();
		
		/*Forgot password page --> type user email*/
		Utils.Log.info("|Filling in user email...");
		onForgotPasswordPage.typeExistingUserEmail();
		ForgotPasswordPageResults onForgotPasswordPageResults = onForgotPasswordPage.clickOnResetButton();
		
		Utils.Log.info("|Checking for proper user email");
		onForgotPasswordPageResults.checkUserEmail();

		/*Get confirm message from email*/
		Utils.Log.info("|Getting Reset link from IMAP server...");
		ResetPasswordPage onResetPasswordPage = onForgotPasswordPageResults.getConfirmationFromEmailIMAP();
		
		Utils.Log.info("|Check for proper page");
		onResetPasswordPage.resetPasswordConfirmText();
		
		/*Reset password page --> new password*/
		Utils.Log.info("|Filling in new password");
		onResetPasswordPage.typeNewPassword();
		onResetPasswordPage.typeConfirmPassword();
		onResetPasswordPage.afterResetPassword();

		onLoginPage.verifyLoginPage();
	}
	
}
