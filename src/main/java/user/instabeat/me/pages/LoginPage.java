package user.instabeat.me.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import user.instabeat.me.dashboard.HomePage;
import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;
import user.instabeat.me.pagesMainFunctions.Utils;

public class LoginPage extends PagesMainFunctions{

	public LoginPage(WebDriver driver) {
		super(driver);
		createRandomUser();
	}

	public void verifyPageTitle() {
		String	expected = "Login | Instabeat";
		waitUntillElementWillBeVisible(LoginButton, 5);
		String	actual = driver.getTitle();
			Assert.assertEquals(expected, actual, "Expected title is not equals to actual title ");
			Utils.Log.info("|Actual Page is: "+actual);
		}
	
	public void verifyLoginPage() {
		waitUntillElementWillBeVisible(LoginButton, 5);
		Assert.assertTrue(LoginButton.isDisplayed(), "Login page with " + LoginButton + " is not visible");
	}

	public void isUserLoggedIn() {
		waitUntillElementWillBeVisible(HomeTab, 5);
		Assert.assertTrue(HomeTab.isDisplayed(), "Element " + HomeTab + " is not visible for logged in user!");
	}

	public void typeUserEmail() {
		clearField(EmailField);
		EmailField.sendKeys(userEmail);
	}

	public void typeWrongUserEmail() {
		EmailField.sendKeys(randomUser);
	}

	public void typeUserAfterRegister(String data) {
		EmailField.sendKeys(data);
	}

	public void typeUserPassword() {
		PasswordField.sendKeys(userPassword);
	}
	
	public void fullLogin(){
		EmailField.sendKeys(userEmail);
		PasswordField.sendKeys(userPassword);
	}
	
	public HomePage LoginButton() {
		LoginButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

	public ForgotPasswordPage clickOnForgotPasswordLink() {
//		click(By.xpath(parameters.ForgotPasswordLink));
		ForgotPasswordLink.click();
		return PageFactory.initElements(driver, ForgotPasswordPage.class);
	}

	public GetStartedPage clickOnGetStartedLink() {
		GetStartedLink.click();
		return PageFactory.initElements(driver, GetStartedPage.class);
	}
	
	public void checkAllLinksFromLoginPage(){
		GetAllLinksOnPage();	
//		linksTest();
	}

	public void checkErrorMessage() {
//		waitUntillElementWillBeVisible(ErrorMessages, 5);
		waitUntillMessageAppears(ErrorMessages,parameters.EMincorrectCredentials,5);
		Assert.assertEquals(parameters.EMincorrectCredentials, ErrorMessages.getText());
	}

	public void typeWrongUserPassword() {
		PasswordField.sendKeys("123");		
	}

	public void typeInvalidEmail() {
//		Utils.clearField(EmailField);
//		typeValuesForValidation(parameters.EMinvalidEmail, Utils.dataForEmailFieldInput, EmailField, LoginButton, ErrorMessages);
		EmailField.sendKeys(randomUser);
	}
	
	public HomePage sendSession() throws Exception{
		sendSessionFromDevice();
		return PageFactory.initElements(driver, HomePage.class);
	}
	
}
