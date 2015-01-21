package user.instabeat.me.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import user.instabeat.me.pagesMainFunctions.PagesMainFunctions;
import user.instabeat.me.pagesMainFunctions.Utils;

public class GetStartedPage extends PagesMainFunctions{
	
	public GetStartedPage(WebDriver driver) {
		super(driver);
		createRandomUser();
		createRandomNumbers(100, 250);
		createRandomValues(5);
		map.put(FirstNameField, "TestAuto");
		map.put(LastNameField, "User");
		map.put(EmailField, randomUser);
		map.put(PasswordField, userPassword);
		map.put(ConfirmNewPasswordField, userPassword);
		map.put(HeightField, randomNumbers);
		map.put(WeightField, randomNumbers);
		map.put(PoolLengthField, randomNumbers);
	}

	private Map<WebElement, String> map = new HashMap();

	public void typeUserValues() {
		for (WebElement key : map.keySet()) {
			values(key, map.get(key));
			Utils.Log.info("Entered value is: " + map.get(key));
		}
	}

	public void verifyGetStartedPage() {
		waitUntillElementWillBeVisible(FirstNameField, 5);
		Assert.assertTrue(FirstNameField.isDisplayed(), "Login page with " + FirstNameField + " is not visible");
	}
	
	public GetSartedFirstStep clickOnSignUpButton() {
		SignUpButton.click();
		return PageFactory.initElements(driver, GetSartedFirstStep.class);
	}

	public void randomUserValues() {
		FirstNameField.sendKeys(randomValues);
	}
	
	public void chooseMonthOfBitrh() {
		chooseRandomValuesFromDropDownList(MonthSelect);
	}
	
	public void chooseDayOfBitrh() {
		chooseRandomValuesFromDropDownList(DaySelect);
	}
	
	public void chooseYearOfBitrh() {
		chooseRandomValuesFromDropDownList(YearSelect);
	}
	
	public void chooseHeightMetric() {
		chooseRandomValuesFromDropDownList(HeightMetric);
	}
	
	public void chooseWeightMetric() {
		chooseRandomValuesFromDropDownList(WeightMetric);
	}
	
	public void choosePoolDistanceMetric() {
		chooseRandomValuesFromDropDownList(PoolDistanceMetric);
	}
	
	public void chooseCountry() {
		chooseRandomValuesFromDropDownList(ChooseCountry);
	}

	public void firstNameValidation() {
		typeValuesForValidation(parameters.EMonlyLettersFirstName, Utils.dataForTextFieldsInput, FirstNameField, SignUpButton, ErrorMessages);
		FirstNameField.sendKeys(randomValues);
	}
	
	public void lastNameValidation(){
		typeValuesForValidation(parameters.EMonlyLettersLastName, Utils.dataForTextFieldsInput, LastNameField, SignUpButton, ErrorMessages);
		LastNameField.sendKeys(randomValues);
	}
	
	public void emailValidation(){
		typeValuesForValidation(parameters.EMinvalidEmail, Utils.dataForEmailFieldInput, EmailField, SignUpButton, ErrorMessages);
		EmailField.sendKeys(randomUser);
	}
	
	public void passwordValidation(){
		typeValuesForValidation(parameters.EMinvalidPassword, Utils.dataForPassFieldInput, PasswordField, SignUpButton, ErrorMessages);
		PasswordField.sendKeys(userPassword);
	}
	
	public void confirmPasswordValidation(){
		typeValuesForValidation(parameters.EMpasswordNotMatch, Utils.dataForConfirmPassFieldInput, ConfirmNewPasswordField, SignUpButton, ErrorMessages);
		ConfirmNewPasswordField.sendKeys(userPassword);
	}
	
	/*need a rework*/
/*	public void dateOfBirthValidation(){
		typeValuesForValidation(parameters.EMwrongBirthdate, Utils.dataForDateFieldInput, DateOfBirthField, SignUpButton, ErrorMessages);
		DateOfBirthField.sendKeys("1/1/1990");
	}*/
	
	public void minHeightFieldValidation(){
		typeValuesForValidation(parameters.EMminimumNumberHeight, Utils.dataForMinHeightFieldInput, HeightFieldOnGo, SignUpButton, ErrorMessages);
	}
	
	public void maxHeightFieldValidation(){
		typeValuesForValidation(parameters.EMmaximumNumberHeight, Utils.dataForMaxHeightFieldInput, HeightField, SignUpButton, ErrorMessages);
		HeightField.sendKeys(randomNumbers);
	}
	
	public void minWeightFieldValidation(){
		typeValuesForValidation(parameters.EMminimumNumberWeight, Utils.dataForMinWeightFieldInput, WeightField, SignUpButton, ErrorMessages);
	}
	
	public void maxWeightFieldValidation(){
		typeValuesForValidation(parameters.EMmaximumNumberWeight, Utils.dataForMaxWeightFieldInput, WeightField, SignUpButton, ErrorMessages);
		WeightField.sendKeys(randomNumbers);
	}
	
	public void defaultPoolLengthValidation(){
		typeValuesForValidation(parameters.EMdefaultPoolLength, Utils.dataForDefaultPoolLenghtInput, PoolLengthField, SignUpButton, ErrorMessages);
		PoolLengthField.sendKeys(randomNumbers);
	}

	public void goToLoginPage() {
	LoginLink.click();		
	}
	
	public void chooseMetricsRandomly(){
		List<WebElement> a = driver.findElements(By.xpath("//*[@class = 'blue']//span"));
		for (WebElement b : a){
			b = a.get(new Random().nextInt(a.size()));
			b.click();
			Utils.Log.info("Chosen value: " + b.getText());
		}
		WebElement element = Utils.getRandomFromList(driver.findElement(By.xpath("//select[@name = 'pool_distance_metric']")).findElements(By.tagName("option")));
		element.click();
		Utils.Log.info("Chosen value: " + element.getText());		
	}

}
