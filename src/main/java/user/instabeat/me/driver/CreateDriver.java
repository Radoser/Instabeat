package user.instabeat.me.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import user.instabeat.me.listeners.DriverEventHandler;


public class CreateDriver {
	
	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";
	private static final String IE = "ie";

	private static WebDriver driver;
	private static EventFiringWebDriver eventDriver;
	

	public static WebDriver getInstance(String browserName) throws Exception {
		if (eventDriver == null) {
		 if (driver == null) {
			
			if (CHROME.equals(browserName)) {
				setChromeDriver();	
				driver = new ChromeDriver();
				
			} else if (FIREFOX.equals(browserName)) {
				driver = new FirefoxDriver();
			} else if (IE.equals(browserName)){	
				setIEDriver();
				driver = new InternetExplorerDriver();
			}else
				throw new Exception(
						"Invalid browser property set in configuration file");		 	
			}
		 eventDriver = new EventFiringWebDriver(driver);
			eventDriver.register(new DriverEventHandler());
		}

		return eventDriver;
	}

	public static WebDriver getSetDriver() {
		if (eventDriver == null) {
			throw new RuntimeException("Driver is not set");
		}
		return eventDriver;
	}
	
	/**
	 * Kill driver instance.
	 * @throws Exception 
	 */
	public static void killDriverInstance() throws Exception {
		driver.quit();
		driver = null;
		eventDriver.quit();
		eventDriver = null;
	}

	/**
	 * Sets the chrome driver path for specific OS.
	 *
	 * @throws Exception the exception
	 */
	private static void setChromeDriver() throws Exception {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuffer chromeBinaryPath = new StringBuffer(
				"src/main/resources/drivers/");

		if (osName.startsWith("win")) {
			chromeBinaryPath.append("chrome-win/chromedriver.exe");
		} else if (osName.startsWith("lin")) {
			chromeBinaryPath.append("chrome-lin/chromedriver");
		} else if (osName.startsWith("mac")) {
			chromeBinaryPath.append("chrome-mac/chromedriver");
		} else
			throw new Exception("Your OS is invalid for webdriver tests");

		System.setProperty("webdriver.chrome.driver",
				chromeBinaryPath.toString());
	}

	/**
	 * Sets the ie driver path for specific OS.
	 *
	 * @throws Exception the exception
	 */
	private static void setIEDriver() throws Exception {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuffer ieBinaryPath = new StringBuffer("src/main/resources/drivers/");
		
		if(osName.startsWith("win")){
			ieBinaryPath.append("ie-driver/IEDriverServer2.44.exe");
		} else {
			throw new Exception("Your OS is invalid for webdriver tests");
		}
		
		System.setProperty("webdriver.ie.driver", ieBinaryPath.toString());
	}

	
}
