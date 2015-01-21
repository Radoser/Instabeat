package user.instabeat.me.configTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import user.instabeat.me.driver.CreateDriver;
import user.instabeat.me.listeners.DriverEventHandler;
import user.instabeat.me.pagesMainFunctions.PropertiesXml;
import user.instabeat.me.pagesMainFunctions.Utils;


public class TestConfiguration /*extends CreateDriver*/{
	
	protected WebDriver driver;
	protected String staging;
	protected String production;
	
	@BeforeMethod (groups = "Sanity")
	@Parameters({"browserName"})
	public void before (String browserName) throws Exception {
		Utils.logFile();
		Utils.Log.info("*===========================================*");
		Utils.Log.info("*        Start running test...        *");
		Utils.Log.info("*===========================================*");
		
		staging = PropertiesXml.loadProperty("staging.url");
		production = PropertiesXml.loadProperty("production.url");
		
		driver = CreateDriver.getInstance(browserName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		driver.get(staging);
		
	}

	@AfterMethod (groups = "Sanity")
	public void after() throws Exception {
		Utils.Log.info("*===========================================*");
		Utils.Log.info("*       Finish running test...        *");
		Utils.Log.info("*===========================================*");
		Utils.Log.info("\n");
		
		if (driver != null) {
			CreateDriver.killDriverInstance();
		}
	}

}
