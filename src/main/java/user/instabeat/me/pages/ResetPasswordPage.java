package user.instabeat.me.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;

public class ResetPasswordPage extends PagesMainFunctions{

	
	public ResetPasswordPage(WebDriver driver) {
		super(driver);
	}

	public void resetPasswordConfirmText (){
		waitUntillElementWillBeVisible(resetPasswordTitle, 5);
		Assert.assertTrue(resetPasswordTitle.getText().contains("Choose a new password"), "The title on Reset page is wrong or element was changed");
	}
	
	public void typeNewPassword() 
	{
		NewPasswordField.sendKeys(userPassword);
	}


	public void typeConfirmPassword() 
	{
		ConfirmNewPasswordField.sendKeys(userPassword);
	}


	public LoginPage afterResetPassword(){
		ResetPasswordButton.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public void ResetButton() 
	{
		WebElement resetButton = driver.findElement(By.xpath("//button[@type='submit']"));
		resetButton.click();
	}

}
