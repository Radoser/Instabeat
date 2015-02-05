package user.instabeat.me.loginTests;

import org.testng.annotations.Test;

import user.instabeat.me.configTests.RemoteTestConfiguration;
import user.instabeat.me.configTests.TestConfiguration;
import user.instabeat.me.pages.LoginPage;
import user.instabeat.me.pagesMainFunctions.Utils;

public class LoginPageTestSuite extends TestConfiguration /*RemoteTestConfiguration*/{
			
	@Test(groups = "Sanity", priority = 0, enabled = false)
	public void checkAllLinksOnWebPage() {
		
		LoginPage onLoginPage = new LoginPage(driver);
		onLoginPage.checkAllLinksFromLoginPage();		
	}

	@Test(priority = 1, groups = {"Sanity"})
	public  void UserCanLogin() {
		
		LoginPage onLoginPage = new LoginPage(driver);
		
		onLoginPage.verifyPageTitle();
		onLoginPage.verifyLoginPage();

		Utils.Log.info("|Typing user credentials...");
		onLoginPage.typeUserEmail();
		onLoginPage.typeUserPassword();
		onLoginPage.LoginButton();
		
		Utils.Log.info("|Checking if user successfully logged in");
		onLoginPage.isUserLoggedIn();

		Utils.Log.info("|Logging out...");
		onLoginPage.logout();
	}

	@Test(priority = 2, groups = {"Sanity"})
	public void UserCannotLogin() {
		
		LoginPage onLoginPage = new LoginPage(driver);
		
		onLoginPage.verifyPageTitle();
		
		Utils.Log.info("|Typing wrong user credentials...");
		onLoginPage.typeWrongUserEmail();
		onLoginPage.typeUserPassword();
		onLoginPage.LoginButton();

		
		Utils.Log.info("|Checking if error message appear");
		onLoginPage.checkErrorMessage();
	}

	@Test(priority = 3)
	public void LoginValidation(){
				
		LoginPage onLoginPage = new LoginPage(driver);
		
		Utils.Log.info("|Checking wrong user email");
		onLoginPage.typeWrongUserEmail();
		onLoginPage.typeUserPassword();
		onLoginPage.LoginButton();

		onLoginPage.checkErrorMessage();
		
		Utils.Log.info("|Checking wrong user password");
		onLoginPage.typeUserEmail();
		onLoginPage.typeWrongUserPassword();
		onLoginPage.LoginButton();
		onLoginPage.checkErrorMessage();
				
		Utils.Log.info("|Checking incorrect user email");
		onLoginPage.typeInvalidEmail();
	}
}
