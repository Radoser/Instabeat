package user.instabeat.me.driver;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteDriver {

	
	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";
	private static final String SAFARI = "safari";
	private static final String IE = "ie";
	
	protected static RemoteWebDriver driver;
	protected static DesiredCapabilities capabilities  = new DesiredCapabilities();
	

	public static RemoteWebDriver getInstance2(String browserName) throws Exception {
				
			if (OSDetector().equals("Windows")){
				if (CHROME.equals(browserName)) {
					System.out.println("-----------------XXXPPPP");
					capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
					capabilities.setBrowserName(CHROME);
					capabilities.setPlatform(Platform.XP);
					
					driver = new RemoteWebDriver(new URL("http://172.24.223.128:5556/wd/hub"), capabilities);
					
				}else if(FIREFOX.equals(browserName)){
					
					capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
					capabilities.setBrowserName(FIREFOX);
					capabilities.setPlatform(Platform.XP);
					
					driver = new RemoteWebDriver(new URL("http://172.24.223.128:5556/wd/hub"), capabilities);
					
				}else if(IE.equals(browserName)){
					
					capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
					capabilities.setBrowserName(IE);
					capabilities.setPlatform(Platform.XP);
					
					driver = new RemoteWebDriver(new URL("http://172.24.223.128:5556/wd/hub"), capabilities);
					
				}else {
					System.out.println("OOOOOOOOOOOOOOOOOOOOOOPPPPPSSSSS");
				}
		} else if (OSDetector().equals("Mac")){
			if (CHROME.equals(browserName)) {
				System.out.println("-----------------MAAAACC");
				capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
				capabilities.setBrowserName(CHROME);
				capabilities.setPlatform(Platform.MAC);
				
				driver = new RemoteWebDriver(new URL("http://172.24.223.129:5557/wd/hub"), capabilities);
				
			}else if(FIREFOX.equals(browserName)){
				
				capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
				capabilities.setBrowserName(FIREFOX);
				capabilities.setPlatform(Platform.MAC);
				
				driver = new RemoteWebDriver(new URL("http://172.24.223.129:5557/wd/hub"), capabilities);
				
			}else if (SAFARI.equals(browserName)){
				
				capabilities.setBrowserName(DesiredCapabilities.safari().getBrowserName());
				capabilities.setBrowserName(SAFARI);
				capabilities.setPlatform(Platform.MAC);
				
				driver = new RemoteWebDriver(new URL("http://172.24.223.129:5557/wd/hub"), capabilities);
				
			} else {
				System.out.println("OOOOOOOOOOOOOOOPPPPPPPPPPPPPPPPPPPPPSSSS 2222");
			}
		}
	
		
		return driver;
	}
	
	public static String OSDetector () {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			
			return "Windows";
		} else if (os.contains("nux") || os.contains("nix")) {
			return "Linux";
		}else if (os.contains("mac")) {
			return "Mac";
		}else if (os.contains("sunos")) {
			return "Solaris";
		}else {
			return "Other";
		}
	}
	
	/*public static RemoteWebDriver getInstance(String browserName) throws Exception {

		 if (driver == null) {
			
			 if (CHROME.equals(browserName)) {
				 
				capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
				driver = new RemoteWebDriver(new URL("http://172.24.223.129:5557/wd/hub"), capabilities);
				System.out.println("Chrome is running on: " +  System.getProperty("os.name").toLowerCase());
				
			} else if (FIREFOX.equals(browserName)) {
				capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
				driver = new RemoteWebDriver(new URL("http://172.24.223.128:5556/wd/hub"), capabilities);
			}else
				throw new Exception(
						"Invalid browser property set in configuration file");		 	
			}

		return driver;
	}*/
	
	public static WebDriver getSetDriver() {
		if (driver == null) {
			throw new RuntimeException("Driver is not set");
		}
		return driver;
	}
	
	public static void killDriverInstance() throws Exception {
		driver.quit();
		driver = null;
		
	}
		
		
}
