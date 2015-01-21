package user.instabeat.me.pagesMainFunctions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebElement;

public class Utils {
	
	public static Logger Log = Logger.getLogger(PagesMainFunctions.class.getName());

	public static void logFile(){
		DOMConfigurator.configure("src/main/resources/log4j.xml");
	}
	
	public static WebElement getRandomFromList(List<WebElement> list){
		int size = list.size();
		int item = new Random().nextInt(size); 
		int i = 0;
		for(WebElement obj : list)
		{
			if (i == item)
				return obj;
			i = i + 1;

		}
		return null;
	}

	public static List<String> dataForTextFieldsInput = Arrays.asList(
			"123",
			"!@#$%^&*()_+",
			"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "=", "+", "'", "='", "/", "\\", "?", ".",
			"QWE123",
			"123qwe",
			" ",
			" qwe",
			"qw er"
			);
	public static List <String> dataForDateFieldInput = Arrays.asList(
			"123",
			"!@#$%^&*()_+",
			"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "'", "='", "/", "\\", "?", ".",
			"QWE123", 
			"123qwe",
			" ",
			" qwe",
			"qw er",
			"05/32/200",
			"13/31/2000",
			"00/00/2000",
			"qw/12/2000",
			"01/qw/2000",
			"01/01/qwert",
			"01.01.2000",
			"01,01,01"
			);
	public static List<String> dataForEmailFieldInput = Arrays.asList(
			"#@%^%#$@#$@#.com",
			"@gmail.com",
			"test <test@gmail.com>",
			"test@gmail@gmail.com",
			/*" test@gmail.com",*/
			"test@gmail.com (test)",
			"test@gmail",
			/*"test@-gmail.com",*/
			"test@111.222.333.444",
			"test @gmail.com",
			"test@gmail. com"
			);

	public static List<String> dataForPassFieldInput = Arrays.asList(
			"1",
			"123",
			/*"12345678901234567",*/
			"1234567890123456789",
			"!@#$%^&*()_+-|?/.,:;"
			);
	public static List<String> dataForConfirmPassFieldInput = Arrays.asList(
			"123",
			"1234567"
			);

	public static List<String> dataForMinHeightFieldInput = Arrays.asList(
			"1",
			"99"
			);

	public static List<String> dataForMaxHeightFieldInput = Arrays.asList(
			"251",
			"999"
			);

	public static List<String> dataForMinWeightFieldInput = Arrays.asList(
			"1",
			"29"
			);

	public static List<String> dataForMaxWeightFieldInput = Arrays.asList(
			"301",
			"999"
			);
	
	public static List<String> dataForMaxWeightFieldInputLbs = Arrays.asList(
			"551",
			"999"
			);
	
	public static List<String> dataForDefaultPoolLenghtInput = Arrays.asList(
			"1q",
			"q1",
			"1 ",
			" 1",
			"1 1",
			"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "'", "='", "/", "\\", "?", ".",
			"!@#$%^&*()_+=-|/\\'",
			"1000000",
			"+15",
			"15'",
			" q"
			);

	public static String result;
	public static String conversion (String enterTheMetricField, String EnterTheValue){
		Utils.Log.info("|Converting values ... ");
		
		if (enterTheMetricField.equals("cm")){
			
			int intValue = Integer.parseInt(EnterTheValue);
			
			double foot = intValue/30.48;
			Utils.Log.info("|Ft: " + foot);

			double inch = (intValue / 2.54) - ((int)foot * 12);
			Utils.Log.info("|Inch: " + inch);

			int Ft = (int)foot;
			int In = (int)Math.round(inch);

			String result = Ft + " ft " + In + " inch";
			Utils.Log.info("|Result of converting is: " + result);

			return result;
			
			}else if(enterTheMetricField.equals("ft")){
				
				String[] partsOfString = EnterTheValue.split("\\D+");
				
				String footInString = partsOfString[0];
				System.out.println(footInString + " - this is first part of the double");
				
				String inchInString = partsOfString[1];
				System.out.println(inchInString + " - this is second part of the double");
				
				int intFtValue = Integer.parseInt(footInString); //<===Foot
				int intInchValue = Integer.parseInt(inchInString);//<===Inch

				double footConverted = intFtValue*30.48;
				Utils.Log.info("|Ft: " + footConverted);

				double inchconverted = intInchValue*2.54;
				Utils.Log.info("|Inch: " + inchconverted);
				
				double a = footConverted + inchconverted;
				
				int resultsInCm = (int)a;
				
				String result = Integer.toString(resultsInCm);
				Utils.Log.info("|Result of converting is: " + result + " cm");

				return result;
				
				}else if(enterTheMetricField.equals("kg")){
					int intValue = Integer.parseInt(EnterTheValue);
					
					double a = intValue *  2.2046226218;
					Utils.Log.info("|Lbs: " + a);
					
					int resultInLbs = (int)Math.round(a);
					
					String result = Integer.toString(resultInLbs);
					
					Utils.Log.info("|Result of converting is: " + result + " lbs");
					
					return result;
					
					}else if(enterTheMetricField.equals("lbs")){
						int intValue = Integer.parseInt(EnterTheValue);

						double b = intValue / 2.2046226218;
						Utils.Log.info("|Kg: " + b);
						
						int resultInLbs = (int)Math.round(b);
						
						String result = Integer.toString(resultInLbs);
						
						Utils.Log.info("|Result of converting is: " + result + " kg");
						
						return result;
						
						}else{
							Utils.Log.info("|Entered Metric was wrong!");
							}
		return result;
	}
	
}
