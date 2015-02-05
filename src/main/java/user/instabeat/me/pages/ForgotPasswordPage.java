package user.instabeat.me.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;
import user.instabeat.me.pagesMainFunctions.Utils;

public class ForgotPasswordPage extends PagesMainFunctions{

	public ForgotPasswordPage(WebDriver driver){
		super(driver);
		createRandomUser();
	}
	
	
	public void verifyPageTitle() {
		String	expected = "Login";
		String	actual = driver.getTitle();
			Assert.assertEquals(expected, actual);
			System.out.println("------------------>" + actual+" Page" + "<------------------");
		}
	
	public void typeExistingUserEmail ()
	{
		EmailField.sendKeys(userEmail);
//		values(By.id(parameters.EmailField), parameters.UserEmail);
	}
	
	public ForgotPasswordPageResults clickOnResetButton(){
		ResetPasswordButton.click();
//		click(By.xpath(parameters.ResetPasswordButton));
		return PageFactory.initElements(driver, ForgotPasswordPageResults.class);
	}


	public void emailFieldValidation() {
		typeValuesForValidation(parameters.EMinvalidEmail, Utils.dataForEmailFieldInput, EmailField, ResetPasswordButton, ResetPassErrorMessages);
//		EmailField.sendKeys(randomUser);
	}


	public void typeWrongUserEmail() {
		EmailField.sendKeys(randomUser);
		ResetPasswordButton.click();
//		Utils.delay(2000);
	}


	public void checkErrorMessage() throws InterruptedException {
		waitUntillMessageAppears(ResetPassErrorMessages, parameters.EMuserNotFound, 10);
		Assert.assertEquals(parameters.EMuserNotFound, ResetPassErrorMessages.getText());
	}
	
	
}
