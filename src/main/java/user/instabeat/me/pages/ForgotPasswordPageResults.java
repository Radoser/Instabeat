package user.instabeat.me.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;

public class ForgotPasswordPageResults extends PagesMainFunctions{

	public ForgotPasswordPageResults(WebDriver driver) {
		super(driver);
	}

	public void checkUserEmail() {
		waitUntillElementWillBeVisible(driver.findElement(By.tagName("span")), 5);
		Assert.assertTrue(driver.findElement(By.tagName("span")).getText().contains("fortestgl+1@gmail.com"));
	}

	public boolean verifyTextPresent(String text) {
		return driver.getPageSource().contains(text);
	}

	public ResetPasswordPage getConfirmationFromEmailIMAP() throws Exception {
		GoToIMAPServer();
		return PageFactory.initElements(driver, ResetPasswordPage.class);

	}
}
