package user.instabeat.me.dashboard;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;

public class HeartRateZonesPage extends PagesMainFunctions{
	

	public HeartRateZonesPage(WebDriver driver) {
		super(driver);
		createRandomNumbers(30,119);
	}

	public void typeRHR() {
	RHRField.sendKeys(randomNumbers);
	}
	
	public void clickOnCalculateButton(){
		CalculateHRButton.click();
	}
	
	public void clickOnUpdateButton(){
		UpdateButton.click();
	}
	
	/*public void isSuccessMessagePresent() {
		
		String message = driver.findElement(By.tagName("span")).getAttribute("class");
		Assert.assertTrue("There is no such as text", message.contains("Your zones have been configured! Plug-in your device now to sync"));
	}*/
	
	public void isSuccessMessagePresent() {
	Assert.assertTrue(verifyPageContent("Your zones have been configured! Plug-in your device now to sync"));
	}
	
	public void typeHeartRateIntoBoxes(){
		 WebElement a =  formOfHR;
		 List<WebElement> b = a.findElements(By.xpath("//*[@class='col-md-4 col-xs-4']/input[@name]"));
		 for (WebElement element: b) {
		      System.out.println(element.getAttribute("name"));//getText());
		      element.clear();
		      element.sendKeys(randomNumbers);
		      randomNumbers=Integer.toString(Integer.parseInt(randomNumbers)+5);
         }
	}

	public void RHZFieldsValidation() {
		WebElement a =  formOfHR;
		List<WebElement> b = a.findElements(By.xpath("//*[@class='col-md-4 col-xs-4']/input[@name]"));
		List<String> wrongValues = Arrays.asList("-1", "29", "251", "999999");		 
		
		for(WebElement element : b){
			element.clear();
		}
		
			for (WebElement element: b) {		
				for(String c : wrongValues){
					element.clear();
					element.sendKeys(c);
					UpdateButton.click();
					if(parameters.EMwrongDataInHRZBox.equals(DashboardErrorMessages.getText())){
						Assert.assertEquals(parameters.EMwrongDataInHRZBox, DashboardErrorMessages.getText());
					}else{
						Assert.assertEquals(parameters.EMascendingOrderRequire, DashboardErrorMessages.getText());
					}
				}
				element.clear();
				element.sendKeys(randomNumbers);
				randomNumbers=Integer.toString(Integer.parseInt(randomNumbers)+5);
			}
	}

	public void RHRFieldValidation() {
		List<String> a = Arrays.asList("-1","0001", "24");
		List<String> b = Arrays.asList("121", "222", "9999999");
		
		RHRField.sendKeys("0");
		CalculateHRButton.click();

		do{
			typeValuesForValidation(parameters.EMRHRIsLessThenRequire, a, RHRField, CalculateHRButton, DashboardErrorMessages);
			getTheValueFromFieldUsingJS(RHRField, "id");
		}while(parameters.EMRHRIsMoreThenRequire.equals(DashboardErrorMessages.getText()));
				typeValuesForValidation(parameters.EMRHRIsMoreThenRequire, b, RHRField, CalculateHRButton, DashboardErrorMessages);
	}


}
