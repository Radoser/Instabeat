package user.instabeat.me.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;
import user.instabeat.me.pagesMainFunctions.Utils;

public class GetStartedSecondStep extends PagesMainFunctions {

	public GetStartedSecondStep(WebDriver driver) {
		super(driver);
	}

	public void printPageTitle() {
		Utils.Log.info("|Actual Page is: " + driver.getTitle());
		
	}
	
	public void verifyGetInstabeatConnectText(){
		waitUntillElementWillBeVisible(SecondStepTitle, 5);
		Assert.assertTrue(SecondStepTitle.getText().contains("2. Software Download"));
	}
	
	public void downloadApp(){
		if (driver.findElement(By.linkText("WINDOWS")).isDisplayed()){
			click(By.xpath("//a[@href='/user/files/InstabeatConnect.exe']"));
		}else{
			driver.findElement(By.linkText("WINDOWS")).click();
			System.out.println("CLIKED BY LINK");
		}
	}

//	Access denied --> Parity =( in case if to execute file in C
//	File should be available in Project folder
	public void clickOnSave() throws IOException{
		Process proc = Runtime.getRuntime().exec(
				"D:\\Projects\\clickDownload.exe");
	}
	
	public GetStartedThirdStep loginByApp(String value) throws Exception
	{
		LoginApp(value);
		return PageFactory.initElements(driver, GetStartedThirdStep.class);
	}
}
