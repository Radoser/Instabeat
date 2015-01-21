package user.instabeat.me.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import user.instabeat.me.pagesMainFunctions.Utils;


public class DriverEventHandler implements WebDriverEventListener{

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		
	}

	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
//		Utils.Log.info(" |The element " + "[" + getElementDescriptorName(arg2, arg1) + "]" + " is found by: " + arg0);		
	}

	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		Utils.Log.info(" |Clicked on: " + "[" + getElementDescriptorXPATH(arg1, arg0) + "]" + " element" + "; Element html tag: " + getElementDescriptorName(arg1, arg0));		
	}

	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
	}

	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		Utils.Log.info( " |Navigated to: " + arg0);
		
	}

	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable arg0, WebDriver arg1) {
		Utils.Log.info("WebDriver Exception throwed: " + arg0.getMessage());
		
	}

	public String getElementDescriptorXPATH(WebDriver driver, WebElement element) {
		return (String) ((JavascriptExecutor) driver)
				.executeScript(
						"gPt=function(c){if(c.id!=='')"
								+ "{return'id(\"'+c.id+'\")'}"
								+ "if(c===document.body){return c.tagName}"
								+ "var a=0;var e=c.parentNode.childNodes;"
								+ "for(var b=0;b<e.length;b++){"
								+ "var d=e[b];if(d===c){"
								+ "return gPt(c.parentNode)+'/'+c.tagName+"
								+ "'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName)"
								+ "{a++}}};return gPt(arguments[0]).toLowerCase();",
						element);
	}
	
	public String getElementDescriptorName(WebDriver driver, WebElement element) {
		return element.getTagName() + " " + element.getText();
	}
	
}