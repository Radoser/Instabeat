package user.instabeat.me.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

import user.instabeat.me.driver.CreateDriver;
import user.instabeat.me.pagesMainFunctions.Utils;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {
	 
	private static final String SCREENSHOT_FOLDER = "target/screenshots";
	private static final String SCREENSHOT_FORMAT = ".png";
	
	// This belongs to ISuiteListener and will execute before the Suite start
	 
	public void onStart(ISuite arg0) {
	 
//	Reporter.log(Utils.timeStamp() + " |About to begin executing Suite " + arg0.getName(), true);
	 Utils.Log.info(" |About to begin executing Suite " + arg0.getName());
	}
	 
	// This belongs to ISuiteListener and will execute, once the Suite is finished
	 
	 
	public void onFinish(ISuite arg0) {
	 
//	Reporter.log(Utils.timeStamp() + " |About to end executing Suite " + arg0.getName(), true);
		Utils.Log.info(" |About to end executing Suite " + arg0.getName());
		
	}
	 
	// This belongs to ITestListener and will execute before starting of Test set/batch
	 
	public void onStart(ITestContext arg0) {
	 
//	Reporter.log(Utils.timeStamp() + " |About to begin executing Test " + arg0.getName(), true);
	Utils.Log.info( "|About to begin executing Test " + arg0.getName());
	}
	 
	// This belongs to ITestListener and will execute, once the Test set/batch is finished
	 
	public void onFinish(ITestContext arg0) {
	 
//	Reporter.log(Utils.timeStamp() + " |Completed executing test " + arg0.getName(), true);
		Utils.Log.info(" |Completed executing test " + arg0.getName()); 
	 
	}
	 
	// This belongs to ITestListener and will execute only when the test is pass
	 
	public void onTestSuccess(ITestResult arg0) {
	 
	// This is calling the printTestResults method
		
	printTestResults(arg0);
	 
	}
	 
	// This belongs to ITestListener and will execute only on the event of fail test
	 
	public void onTestFailure(ITestResult arg0) {
	 
	// This is calling the printTestResults method
		
	printTestResults(arg0);
	 
	}
	 
	// This belongs to ITestListener and will execute before the main test start (@Test)
	 
	public void onTestStart(ITestResult arg0) {
	 
//		Reporter.log(Utils.timeStamp() + " |The execution of the main test starts now");
		Utils.Log.info(" |The execution of the main test starts now");
	}
	 
	// This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped
	 
	public void onTestSkipped(ITestResult arg0) {
		
	printTestResults(arg0);
	 
	}
	 
	// This is just a piece of shit, ignore this
	 
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	 
	}
	 
	// This is the method which will be executed in case of test pass or fail
	 
	// This will provide the information on the test
	 
	private void printTestResults(ITestResult result) {
	 
//		Reporter.log(Utils.timeStamp() + " |Test Method resides in " + result.getTestClass().getName(), true);
		 Utils.Log.info(" |Test Method resides in " + result.getTestClass().getName());
			if (result.getParameters().length != 0) {
				String params = null;
					for (Object parameter : result.getParameters()) {
						params += parameter.toString() + ",";
						}	 
//					Reporter.log(Utils.timeStamp() + " |Test Method had the following parameters : " + params, true);
					Utils.Log.info(" |Test Method had the following parameters : " + params);
			}
	 
			String status = null;
	 
			switch (result.getStatus()) {
				case ITestResult.SUCCESS:
					status = "~ Pass ~";
					break;
				case ITestResult.FAILURE:
					status = "~ Failed ~";
					break;
				case ITestResult.SKIP:
					status = "~ Skipped ~";
					}
	 
//			Reporter.log(Utils.timeStamp() + " |Test Status: " + status, true);
			Utils.Log.info(" |Test Status: " + status);
			takeScreenshot(result);
	 
	}
	 
	// This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test
	 
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
	 
		String textMsg = /*Utils.timeStamp() +*/ " |Began executing following method: " + returnMethodName(arg0.getTestMethod());
	 
//		Reporter.log(textMsg, true);
		Utils.Log.info(textMsg);
	 
		}
	 
	// This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test
	 
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
	 
		String textMsg = "|Completed executing following method: " + returnMethodName(arg0.getTestMethod());
	 
//		Reporter.log(textMsg, true);
		Utils.Log.info(textMsg);
	 
		}
	 
	// This will return method names to the calling function
	 
	private String returnMethodName(ITestNGMethod method) {
		return method.getRealClass().getSimpleName() + "." + method.getMethodName(); 
		}
	
	public void takeScreenshot(ITestResult result) {
		//creating screenshot folder for test
		String folder = SCREENSHOT_FOLDER + "/" + result.getName();
		File dir = new File(folder);
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		try {
			// Pause because sometimes webdriver takes previous page screenshot
			Thread.sleep(3000);
			// Taking webDriver screenshot
			File screenFile = ((TakesScreenshot) CreateDriver.getSetDriver()).getScreenshotAs(OutputType.FILE);
			// Setting screenshot file name 'testMethodName_01_12_14_14_11_09.png'
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String fileName = result.getName() + "_" + formater.format(Calendar.getInstance().getTime()) + SCREENSHOT_FORMAT;
			// Put screen file to appropriate folder
			FileUtils.copyFile(screenFile, new File(dir.getAbsoluteFile() + "/" + fileName));
			// Get cannonical screenshot path on disc and link it to reporter output with html code
			// <a href="E:\web.framework/target/screenshots/signInDisapearing/signInDisapearing_30_11_2014_02_48_34.png" 
			// target="_blank"><br><img 
			// src="file:///E:\web.framework/target/screenshots/signInDisapearing/signInDisapearing_30_11_2014_02_48_34.png" 
			// width="600" height="338" alt=""><br></a>
			File directory = new File(".");
			String cannonicalScreenshotsPath = directory.getCanonicalPath();
			Reporter.log("<a href=" + cannonicalScreenshotsPath + "/" + folder + "/" + fileName + " target='_blank' >"
					+ "<p><br/><img src=\"file:///" + cannonicalScreenshotsPath + "/"	+ folder + "/" + fileName
					+ "\" width=\"600\" height=\"338\" alt=\"\"/>" + "<br/></p></a>", true);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	}
