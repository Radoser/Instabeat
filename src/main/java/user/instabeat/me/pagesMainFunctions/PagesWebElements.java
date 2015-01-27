package user.instabeat.me.pagesMainFunctions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

//import user.instabeat.me.configTests.TestConfiguration;

public class PagesWebElements /*extends TestConfiguration*/{
	
	protected WebDriver driver;
	
	public PagesWebElements(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;	
	}

	
	@FindBy(xpath = "//div[@onclick='logOut()']")
	WebElement LogoutLink;

	/* ----------------------------------------------------------------------  Login Page */
	@FindBy(id = "email")
	public static WebElement EmailField;

	@FindBy(id = "passwordInput")
	public WebElement PasswordField;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement LoginButton;

	@FindBy(xpath = "//a[@href='/go']")
	public WebElement GetStartedLink;

	@FindBy(xpath = "//a[@href='/user/restore']")
	public WebElement ForgotPasswordLink;

	/* ----------------------------------------------------------------------  Reset Password Page */
	@FindBy(id = "passwordInput")
	public WebElement NewPasswordField;

	@FindBy(id = "password_conf")
	public WebElement ConfirmNewPasswordField;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement ResetPasswordButton;
	
	@FindBy(xpath = "//*[@class = 'col-md-12 col-xs-12 ibt-title']")
	public WebElement resetPasswordTitle;

	/* ----------------------------------------------------------------------  Get Started Page */
	@FindBy(xpath = "//button[@class = 'ibt-button subbut']")
	public WebElement SignUpButton;

	@FindBy(id = "firstName")
	public WebElement FirstNameField;

	@FindBy(id = "lastName")
	public WebElement LastNameField;

	@FindBy(id = "birthdate")
	public WebElement DateOfBirthField;

	@FindBy(id = "height")
	public WebElement HeightField;
	
	@FindBy(id = "height")
	public WebElement HeightFieldOnGo;
	
	@FindBy(id = "weight")
	public WebElement WeightField;

	@FindBy(id = "month-select")
	public WebElement MonthSelect;
	
	@FindBy(id = "day-select")
	public WebElement DaySelect;
	
	@FindBy(id = "year-select")
	public WebElement YearSelect;
	
	@FindBy(id = "metric_height")
	public WebElement HeightMetric;
	
	@FindBy(id = "metric_weight")
	public WebElement WeightMetric;
	
	@FindBy(id = "pool_distance_metric")
	public WebElement PoolDistanceMetric;
	
	@FindBy(id = "timezone")
	public WebElement ChooseCountry;
	
	@FindBy(id = "pool_distance")
	public WebElement PoolLengthField;
	
	@FindBy(xpath = "//*[@class = 'text-thin']")
	public WebElement SWDownloadPageTitle;
	
	@FindBy(xpath = "//*[@class = 'col-md-4 col-xs-4 conf-tab conf-selected']")
	public WebElement FirstStepTitle;
	
	@FindBy(xpath = "//*[@class = 'col-md-4 col-xs-4 conf-tab conf-selected']")
	public WebElement SecondStepTitle;
	
	@FindBy(xpath = "//*[@class = 'col-md-4 col-xs-4 conf-tab conf-selected']")
	public WebElement ThirdStepTitle;
	
	/* ---------------------------------------------------------------------- Heart Rate Zones Page */
	@FindBy(id = "MHR")
	public WebElement RHRField;

	@FindBy(id = "calcButton")
	public WebElement CalculateHRButton;

	@FindBy(xpath = "//*[@class='col-md-12 col-xs-12']/input[@value='Update']")
	public WebElement UpdateButton;

	/* ----------------------------------------------------------------------  Home Page */
	@FindBy(xpath = "//div[@class='float-right calendar']")
	public WebElement CalendarButton;

	@FindBy(xpath = "//span[@class='cal-day']")
	public WebElement CalendarButtonDate;

	@FindBy(xpath = "//span[@class='cal-month']")
	public WebElement CalendarButtonMonth;

	@FindBy(how = How.CSS, using = ".day")//(xpath = "//*[@class = 'day ']")
	public WebElement DateContainsSessions;
	
	@FindBy (xpath = "//*[@class = 'datepicker-days']//th[@class = 'switch']")
	public WebElement ActiveMonth;
	
	@FindBys({@FindBy(xpath = "//*[@class = 'datepicker-days']//td[@class = 'day ']")})
	public List<WebElement> DatesWithSessions;

	@FindBy(how = How.CSS, using = ".day.active")
	public WebElement ActiveDateWithSession;

	@FindBy(how = How.CSS, using = ".day.old")
	public WebElement oldDay;

	@FindBy(xpath = ".//*[@id='cong-text']")
	public WebElement CongratsMessage;

	@FindBy(xpath = ".//*[@id='s2id_parentNode']/a")
	public WebElement ContextMenu;

	@FindBy(xpath = "//*[@class='top-content'][text()='Home']")
	public WebElement HomeTab;

	@FindBy(xpath = "//*[text()='+']")
	public WebElement PlusButton;

	@FindBy(xpath = "//*[@class='col-md-4 col-xs-4 face']")
	public WebElement FBShareGraphButton;

	@FindBy(xpath = "//*[@onclick = 'alertToTwitter()']")
	public WebElement TwitterShareButton;
	
	@FindBy(xpath = "//div[4][@class = 'session-container']")
	public WebElement SessionDuration;

	@FindBy(xpath = "//*[@id = 'duration']")
	public WebElement SessionDurationInFooter;
	
	@FindBy(xpath = "//*[@class = 'pool-length']")
	public WebElement PoolLength;
	
	@FindBy(xpath = "//*[@id = 'laps']")
	public WebElement LapsAmount;
	
	@FindBy(xpath = "//*[@id = 'totalDist']")
	public WebElement SessionTotalDistance;
	
	@FindBy(xpath = "//*[@class = 'pool-edit']")
	public WebElement EditActivityButton;
	
	@FindBy(css = ".close_ic")
	public WebElement DeleteOneSessionButton;
	
	/* ----------------------------------------------------------------------  Profile Page */
	@FindBy(xpath = "//*[text()='Profile']")
	public WebElement ProfileTab;

	@FindBy(xpath = "//*[@class='name ibt-title']")
	public WebElement UserNameTitle;

	@FindBy(xpath = "html/body/div[4]/div/div[4]/div[2]/div[2]/form/div[2]/div[4]/button")
	public WebElement ProfileUpdateButton;

	@FindBy(xpath = "//span[@class = 'suc-text']")
	public WebElement MessageAboutUpdateProfile;

	@FindBy(xpath = "//span[@class = 'glyphicon glyphicon-exclamation-sign']")
	public WebElement ExclamationMark;

	@FindBy(xpath = "//select[@name='f_level']")
	public WebElement FitnessLevel;

	@FindBy(id = "file")
	public WebElement UploadPictureButton;

	@FindBy(xpath = "//div[@onclick=\"goTo('.settings')\"]")
	public WebElement ProfileSettingsLink;
	
	@FindBy(xpath = "//div[@onclick=\"goTo('.profile')\"]")
	public WebElement ProfileLinkOnSettingsPage;

	@FindBy(xpath = "//input[@name='oldpassword']")
	public WebElement OldPasswordField;
	
	@FindBy(xpath = "//select[@id='timezone2']")
	public WebElement UTCzones;
	
	@FindBy(xpath = "//select[@id = 'month-select'][@class = 'form-control ibt-input select2-offscreen']")
	public WebElement MonthSelectOnProfile;
	
	@FindBy(xpath = "//select[@id = 'day-select'][@class = 'form-control ibt-input select2-offscreen']")
	public WebElement DaySelectOnProfile;
	
	@FindBy(xpath = "//select[@id = 'year-select'][@class = 'form-control ibt-input select2-offscreen']")
	public WebElement YearSelectOnProfile;
	
	@FindBy(id = "heightCm")
	public WebElement HeightCmField;
	
	@FindBy(id = "heightFt")
	public WebElement HeightFtField;

	@FindBy(id = "heightIn")
	public WebElement HeightInchField;
	
	@FindBy(xpath = "//*[@id = 'weight-kg']//input[@id = 'weight']")
	public WebElement WeightKgField;
	
	@FindBy(xpath = "//*[@id = 'weight-lb']//input[@id = 'weight']")
	public WebElement WeightLbsField;
	
	@FindBy(xpath = "//*[substring-after(@style, '/load')]")
	public WebElement LoadingGIFImage;
	
	@FindBy(xpath = "//*[substring-after(@style, '/avatar')]")
	public WebElement LoadedImage;
	
	@FindBy(xpath = "//div[@onclick=\"$('#file').click()\"]")
	public WebElement LoadImageButton;

	/* ----------------------------------------------------------------------  Profile Settings */
	@FindBy(id = "newpassword")
	public WebElement NewUserProfilePasswordField;

	@FindBy(id = "confirm")
	public WebElement ConfirmNewUserProfilePasswordField;

	@FindBy(id = "PassUpdate")
	public WebElement PasswordUpdateButton;

	@FindBy(xpath = "//*[@class = 'col-md-12 col-xs-12']/button[@type = 'submit'][text() = 'Update']")
	public WebElement UpdateUserProfileButton;

	@FindBy(xpath = "//input[@value='Erase data']")
	public WebElement SessionsEraseButton;

	@FindBy(xpath = "//input[@value='Delete account']")
	public WebElement AccountDeleteButton;
	
	@FindBy(css = "input[name = 'metric_height']:not(:checked) + span")
	public WebElement NotActiveHeightUnit;
	
	@FindBy(css = "input[name = 'metric_weight']:not(:checked) + span")
	public WebElement NotActiveWeightUnit;
	
	@FindBy(css = "input[name = 'metric_activity']:not(:checked) + span")
	public WebElement NotActiveDefaultActivity;

	@FindBy(css = "input[name = 'metric_activity']:checked + span")
	public WebElement ActiveDefaultActivity;
	
	@FindBy(css = "input[name = 'metric_distance']:not(:checked) + span")
	public WebElement NotActiveDistanceUnit;

	/*@FindBy(xpath = "//*[@class='col-md-6 col-xs-6']/input[@value='OK']")
	public WebElement OkButtonForDelete;*/

	@FindBy(xpath = "//input[@value='OK']")
	public WebElement OkButtonForDelete;

	@FindBy(xpath = "//*[@class='col-md-6 col-xs-6']/input[@value='Cancel']")
	public WebElement CancelButton;

	@FindBy(id = "alertText")
	public WebElement EraseTextWindow;

	@FindBy(id = "alertText")
	public WebElement DeleteTextWindow;

	@FindBy(xpath = "//input[@class='ibt-button f-disc']")
	public WebElement ConnectToFBButton;

	@FindBy(xpath = "//input[@class='ibt-button t-disc']")
	public WebElement ConnectToTwitterButton;
	
	@FindBy(id = "MetricUpdate")
	public WebElement MetricUpdateButton;
	
	@FindBy(xpath = "//span[text() = 'cm']")
	public WebElement HeightUnitCm;

	/* ---------------------------------------------------------------------- Heart Rate Zones Page */
	@FindBy(xpath = "//*[@class='top-content'][text()='Heart rate zone']")
	public WebElement HRZTab;

	@FindBy(xpath = "//span[@class= 'suc-text'][text()='Your zones have been configured! Plug-in your device now to sync']")
	public WebElement sucMessage;

	@FindBy(xpath = "//form[@name='heartrate']")
	public WebElement formOfHR;

	/* ---------------------------------------------------------------------- Facebook window*/
	@FindBy(id = "email")
	public WebElement FBEmailField;

	@FindBy(id = "pass")
	public WebElement FBPasswordField;

	@FindBy(name = "login")
	public WebElement FBLoginButton;

	@FindBy(xpath = "//button[@name='__CONFIRM__']")
	public WebElement FBOKButton;

	@FindBy(id = "username_or_email")
	public WebElement TwitterEmailField;

	@FindBy(id = "password")
	public WebElement TwitterPasswordField;

	@FindBy(id = "allow")
	public WebElement TwitterAuthorizeButton;

	@FindBy(xpath = "//*[@class='col-md-6 col-xs-6 error-message']")
	public WebElement ErrorMessages;

	@FindBy(xpath = "//*[@class = 'error-message'][text()]")
	public WebElement DashboardErrorMessages;

	@FindBy(xpath = "//*[@class='col-md-5 col-xs-5 error-message']")
	public WebElement ResetPassErrorMessages;

	@FindBy(xpath = "//*[@class = 'float-right header-button']/a[@href = '/user/']")
	public WebElement LoginLink;
	
	@FindBy(xpath = "//span[@class = 'tooltipspan'][@style = 'white-space: nowrap;']")
	public WebElement greenNotification;
	
}

	