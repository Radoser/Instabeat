package user.instabeat.me.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import user.instabeat.me.pagesMainFunctions.Utils;


public class DriverEventHandler implements WebDriverEventListener{

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		String locator = arg0.toString().substring(arg0.toString().indexOf(">")+2,arg0.toString().lastIndexOf("]"));
		Utils.Log.info("|The element with locator '" + locator + "' was clicked");
	}

	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		
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

		String value = arg0.getAttribute("value");
		String locator = arg0.toString().substring(arg0.toString().indexOf(">")+2,arg0.toString().lastIndexOf("]"));
		if(!value.isEmpty()){
		Utils.Log.info("|Value '" + value + "' was typed in element with locator '" + locator + "'");
		}
		
	}

	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		
		Utils.Log.info("|Trying to click: '" + arg0 + "'");
/*		// Highlight Elements before clicking
//		for (int i = 0; i < 1; i++) {
		JavascriptExecutor js = (JavascriptExecutor) arg1;
		js.executeScript(
		
		"arguments[0].setAttribute('style', b);"
		+ "return test(arguments[0], arguments[1]);",
		arg0, "border: 2px solid red;");
//		}
		*/
//		Utils.Log.info(" |Clicked on: " + "[" + getElementDescriptorXPATH(arg1, arg0) + "]" + " element" + "; Element html tag: " + getElementDescriptorName(arg1, arg0));	
//		Utils.Log.info(" |Clicked on: " + "[" + getXpath(arg1, arg0) + "]" + " element" + "; Element html tag: " + getXpath(arg1, arg0));
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
		Utils.Log.info("|Navigated to: " + arg0);
		
	}

	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable arg0, WebDriver arg1) {
		Utils.Log.info("|WebDriver Exception throwed: " + arg0.getMessage());
		 File scrFile = ((TakesScreenshot) arg1).getScreenshotAs(OutputType.FILE);
		 SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		 String fileName = formater.format(Calendar.getInstance().getTime());
				 try {
				 FileUtils.copyFile(scrFile, new File("D:\\" + fileName + ".png"));
				 } catch (Exception e) {
				 System.out.println("|Unable to Save");
				 }
	}

	
	public String getElementDescriptorXPATH(WebDriver driver, WebElement element) {
		
		return (String) ((JavascriptExecutor) driver)
				.executeScript(
						"gPt=function(c){"
						+ "if(c.id){"
						+ "return'id(\"'+c.id+'\")'"
						+ "}"
						+ "if(c===document.body){"
						+ "return c.tagName"
						+ "}"
						+ "var a=0;"
						+ "var e=c.parentNode.childNodes;"
						+ "for(var b=0;b<e.length;b++){"
						+ "var d=e[b];"
						+ "if(d===c){"
						+ "return gPt(c.parentNode)+'/'+c.tagName + '['+(a+1)+']'"
						+ "}"
						+ "if(d.nodeType===1&&d.tagName===c.tagName){"
						+ "a++"
						+ "}}};"
//						+ "return gPt(arguments[0]).toLowerCase();"
						+ "return gPt(document.body.children[0].children[0]).toLowerCase();",
						element);
	}
	
	public String getXpath(WebDriver driver, WebElement element){
		return (String) ((JavascriptExecutor) driver)
				.executeScript(
				"getElementXPath = function(element){"
				+ "if (element && element.id){"
				+ "return '//*[@id=\"' + element.id + '\"]';"
				+ "}"
				+ "};"
				+ "return getElementXPath(arguments[0]);"
				, element);
	}
	
	public String getX(WebDriver driver, WebElement element){
		return ((String) ((JavascriptExecutor) driver)
				.executeScript(
						"getXElementTreeXPath = function( element ) {"
								+ "var paths = [];"
								+ "for ( ; element && element.nodeType == Node.ELEMENT_NODE; element = element.parentNode ) {"
								+ "var index = 0;"
								+ "for ( var sibling = element.previousSibling; sibling; sibling = sibling.previousSibling ) {"
								+ "if ( sibling.nodeType == Node.DOCUMENT_TYPE_NODE ) {"
								+ "continue;"
								+ "}"
								+ "if ( sibling.nodeName == element.nodeName ) {"
								+ "++index;"
								+ "}}"
								+ "var tagName = element.nodeName.toLowerCase();"
								+ "var pathIndex = \"[\" + (index+1) + \"]\";"
								+ "paths.unshift( tagName + pathIndex );"
								+ "}"
								+ "return paths.length ? \"/\" + paths.join( \"/\") : null;"
								+ "};"
								+ "return getXElementTreeXPath(arguments[0]);"
						, element));
	}
	
	public String getElementDescriptorName(WebDriver driver, WebElement element) {
		return element.getTagName() + " " + element.getText();
	}
	
}