package user.instabeat.me.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;

public class GetSartedFirstStep extends PagesMainFunctions {

	public GetSartedFirstStep(WebDriver driver) {
		super(driver);
	}

	public void printPageTitle() {
		System.out.println("------------------>GetStarted 1 Page<------------------");
	}
	
	public boolean checkUserEmail(String value) {
		return driver.findElement(By.id("usermail")).getText().contains(value);
	}

	public GetStartedSecondStep getConfirmationLink() throws Exception {
		GoToIMAPServer();
		return PageFactory.initElements(driver, GetStartedSecondStep.class);
	}
}
