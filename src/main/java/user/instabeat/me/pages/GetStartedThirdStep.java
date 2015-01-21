package user.instabeat.me.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import user.instabeat.me.dashboard.HomePage;
import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;
import user.instabeat.me.pagesMainFunctions.Utils;

public class GetStartedThirdStep extends PagesMainFunctions {

	public GetStartedThirdStep(WebDriver driver) {
		super(driver);
		createRandomNumbers(25, 120);
	}

	public void printPageTitle() {
		Utils.Log.info("|Actual Page is: "+driver.getTitle());
	}

	public void verifyTextPresentOnPage() {
		waitUntillElementWillBeVisible(ThirdStepTitle, 5);
		Assert.assertTrue(ThirdStepTitle.getText().contains("3. Setup"));
	}

	public void typeRHRValue() {
		RHRField.sendKeys(randomNumbers);
	}

	public void clickOnCalculateButton() {
		CalculateHRButton.click();
	}
	
	public HomePage clickOnUpdateButton(){
		UpdateButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}
}
