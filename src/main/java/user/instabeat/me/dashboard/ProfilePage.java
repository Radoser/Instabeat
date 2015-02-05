package user.instabeat.me.dashboard;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.natives.SysUtil;
import org.sikuli.script.FindFailed;
import org.testng.Assert;

import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;
import user.instabeat.me.pagesMainFunctions.Utils;

public class ProfilePage extends PagesMainFunctions{
	
	public ProfilePage(WebDriver driver) {
		super(driver);
		createRandomValues(3);
		createRandomNumbers(120, 200);
	}
	
	public String heightFt;
	public String heightIn;
	public String heightValue;
	public String FtAndInchResult;
	public String weightValueInKg;
	public String weightValueInLbs;

	public void updateFirstNameField() {
		FirstNameField.clear();
		FirstNameField.sendKeys(randomValues);
	}

	public void updateLastNameField() {
		LastNameField.clear();
		LastNameField.sendKeys(randomValues);
	}

	public void updateBirthdateField() {
		chooseRandomValuesFromDropDownList(MonthSelectOnProfile);
		chooseRandomValuesFromDropDownList(DaySelectOnProfile);
		chooseRandomValuesFromDropDownList(YearSelectOnProfile);
	}
	
	public void updateCountryField() {
		chooseRandomValuesFromDropDownList(UTCzones);
	}
	
	public void updateHeightfield(){
		if (HeightCmField.isDisplayed()){
			clearField(HeightCmField);
			HeightCmField.sendKeys(randomNumbers);
		}else if(HeightFtField.isDisplayed()){
			clearField(HeightFtField);
			HeightFtField.sendKeys("5");
			clearField(HeightInchField);
			HeightInchField.sendKeys("5");			
		}else {
			Utils.Log.info("Cannot find the field");
		}
	}
	
	public void updateWeightField() {
		if(WeightKgField.isDisplayed()){
			clearField(WeightKgField);
			WeightKgField.sendKeys(randomNumbers);
		}else if(WeightLbsField.isDisplayed()){
			clearField(WeightLbsField);
			WeightLbsField.sendKeys(randomNumbers);
		}
	}

	public void clickOnUpdateButton() {
		ProfileUpdateButton.click();
	}

	public void changeFitnessLevel() {
//		FitnessLevel.click();
		WebElement element = Utils.getRandomFromList(FitnessLevel.findElements(By.tagName("option")));
		element.click();
	}

	public void checkMessageAboutUpdate() {
		waitUntillMessageAppears(greenNotification, parameters.SMProfileUpdate, 5);
		Assert.assertTrue(verificationOfElementsOnPages(greenNotification), "Green notification box is not displayed after Profile update");
	}

	public boolean checkIconToSyncDevice() {
		return verificationOfElementsOnPages(ExclamationMark);
	}

	public void isUserTitleNameEqualsUserName() {
		Assert.assertTrue(UserNameTitle.getText().contains(
				FirstNameField.getText()));
	}

	public void updateUserPicture() throws IOException, FindFailed, InterruptedException {
//		driver.findElement(By.xpath("//div[@onclick=\"$('#file').click()\"]")).click();
//		Process proc = Runtime.getRuntime().exec("D:\\Java\\eclipse-kepler\\eclipse\\a.exe"); ---- autoIT script
		Assert.assertTrue(pictureExist("avatar.jpg"), "Picture was not properly loaded. Probably the screen image and screenshot  didn't match");
	}
	
	public ProfilePageSettings clickOnProfilePageSettings(){
		ProfileSettingsLink.click();
		return PageFactory.initElements(driver, ProfilePageSettings.class);
	}

	public void firstNameValidation() {
//		clearField(FirstNameField);
		typeValuesForValidation(parameters.EMonlyLettersFirstName, Utils.dataForTextFieldsInput, FirstNameField, UpdateUserProfileButton, DashboardErrorMessages);
		FirstNameField.sendKeys(randomValues);		
	}

	public void lastNameValidation() {
		typeValuesForValidation(parameters.EMonlyLettersLastName, Utils.dataForTextFieldsInput, LastNameField, UpdateUserProfileButton, DashboardErrorMessages);
		LastNameField.sendKeys(randomValues);		
	}

/*	public void dateOfBirthValidation() {
		typeValuesForValidation(parameters.EMwrongBirthdate, Utils.dataForDateFieldInput, DateOfBirthField, UpdateUserProfileButton, DashboardErrorMessages);
		DateOfBirthField.sendKeys("1/1/1990");		
	}*/

	public void getMetricConfigFromHeightField(){
		
	}
	
	public void minHeightFieldValidation() {
		if(HeightCmField.isDisplayed()){
		typeValuesForValidation(parameters.EMminimumNumberHeight, Utils.dataForMinHeightFieldInput, HeightCmField, UpdateUserProfileButton, DashboardErrorMessages);

			}
		
		else{
			Utils.Log.info("The field is set to Feet, nothing to check");
		}
	}

	public void maxHeightFieldValidation() {
		typeValuesForValidation(parameters.EMmaximumNumberHeight, Utils.dataForMaxHeightFieldInput, HeightCmField, UpdateUserProfileButton, DashboardErrorMessages);
		HeightCmField.sendKeys(randomNumbers);	
	}

	
	public void minWeightFieldValidation(){
		if (WeightKgField.isDisplayed()){
			typeValuesForValidation(parameters.EMminimumNumberWeight, Utils.dataForMinWeightFieldInput, WeightKgField, UpdateUserProfileButton, DashboardErrorMessages);
		} else if (WeightLbsField.isDisplayed()){
			typeValuesForValidation(parameters.EMminimumNumberWeight, Utils.dataForMinWeightFieldInput, WeightLbsField, UpdateUserProfileButton, DashboardErrorMessages);
		}
	}

	
	public void maxWeightFieldValidation(){
		if (WeightKgField.isDisplayed()){
			typeValuesForValidation(parameters.EMmaximumNumberWeight, Utils.dataForMaxWeightFieldInput, WeightKgField, UpdateUserProfileButton, DashboardErrorMessages);
		} else if (WeightLbsField.isDisplayed()){
			typeValuesForValidation(parameters.EMmaximumNumberWeightLbs, Utils.dataForMaxWeightFieldInputLbs, WeightLbsField, UpdateUserProfileButton, DashboardErrorMessages);
		}
	}

	public HomePage reloading() {
		Utils.Log.info("|Reloading the page...");
		driver.navigate().refresh();
		return PageFactory.initElements(driver, HomePage.class);		
	}
	
	public void checkChangesInHeightField() {
		try{
		Assert.assertEquals(heightValue, getTheValueFromFields(HeightField));
		}catch (Exception e){
			Assert.assertEquals(getValuesFromHeightField(), getValuesFromHeightFieldFtAndInch());
		}
	}
		
	public String getTheValueFromHeightField() {		
		heightValue = getTheValueFromFields(HeightCmField);
		System.out.println(getTheTextFromElementUsingJS("value"));
		System.out.println(heightValue);
		Utils.Log.info("|The value from Height field is: " + heightValue);
		return heightValue;
	}
	
	public String getValuesFromHeightField() {
		try {				
			return getTheValueFromHeightField();
			}catch (Exception e) {
				return getValuesFromHeightFieldFtAndInch();
			}
	}
	
	public String test(){
		if(driver.findElement(By.xpath("//*[@class = 'ibt-metric'][text() = 'cm']")).getText().equals("cm")){
			return getTheValueFromHeightField();
		}else if(driver.findElement(By.xpath("//*[@class = 'ibt-metric'][text() = 'ft']")).getText().equals("ft")) {
			System.out.println("field is in FT");
			return getValuesFromHeightFieldFtAndInch();
		}else {
			
			return "Oooooppppsssss"; 
		}
	}
	
	public String getValuesFromHeightFieldFtAndInch() {
		
		heightFt = getTheValueFromFields(HeightFtField);
		Utils.Log.info("|The value from Height field in Ft is: " + heightFt);
		
		heightIn = getTheValueFromFields(HeightInchField);
		Utils.Log.info("|The value from Height field in In is: " + heightIn);
		
		FtAndInchResult = heightFt + " ft " + heightIn + " inch";
		Utils.Log.info("|The result value from Height Feet and Inch fields are: " + FtAndInchResult);
		
		return FtAndInchResult;
	}
		
	public void checkConvertedValues() {
		try{
			if(HeightField.isDisplayed()){
				Utils.Log.info("|Comparing the values that was given from field");
				Utils.Log.info("|Converting Cm into Feets and Inches...");
				
				Assert.assertEquals(Utils.conversion(driver.findElement(By.id("height-span")).getText(), heightValue), FtAndInchResult);
			}
		}catch(Exception e) {
			Utils.Log.info("|Comparing the values that was given from field");
			Utils.Log.info("|Converting Feets and Inches into Cm...");
			
			Assert.assertEquals(Utils.conversion(driver.findElement(By.xpath("//*[@class = 'ibt-metric'][text() = 'ft']")).getText(), FtAndInchResult), heightValue);
		}	
	}

	public String getTheValueFromWeightField() {
		
		if (driver.findElement(By.id("weight-span")).getText().equals("kg")){
			waitUntillElementWillBeVisible(WeightKgField, 5);
			Utils.Log.info("|The kg metric is active");
			weightValueInKg = getTheValueFromFields(WeightKgField);
			Utils.Log.info("|The value from Weight field is: " + weightValueInKg);	
			return weightValueInKg;
		 
		}else {
			Utils.Log.info("|The lbs metric is active");
			waitUntillElementWillBeVisible(WeightLbsField, 5);
			weightValueInLbs = getTheValueFromFields(WeightLbsField);
				Utils.Log.info("|The value from Weight field is: " + weightValueInLbs);
				return weightValueInLbs;
			}
		}

	public void checkChangesInWeightField() {
		
		if(driver.findElement(By.id("weight-span")).getText().equals("kg")){
			waitUntillElementWillBeVisible(WeightKgField, 5);
			Assert.assertEquals(weightValueInKg, getTheValueFromFields(WeightKgField));
		}else{
			waitUntillElementWillBeVisible(WeightLbsField, 5);
			Assert.assertEquals(weightValueInLbs, getTheValueFromFields(WeightLbsField));
		}
	}
	
	public void checkChangesInWeightAfterConvert(){
		
		if(driver.findElement(By.id("weight-span")).getText().equals("kg")){
			Utils.Log.info("|Converting Kg into Lbs...");
			Assert.assertEquals(Utils.conversion(driver.findElement(By.id("weight-span")).getText(), weightValueInKg), weightValueInLbs, "The values are not equals after conversion ");
			}else{
				Utils.Log.info("|Converting Lbs into Kg...");
				Assert.assertEquals(Utils.conversion(driver.findElement(By.id("weight-span")).getText(), weightValueInLbs), weightValueInKg, "The values are not equals after conversion ");
			}
	}

}
