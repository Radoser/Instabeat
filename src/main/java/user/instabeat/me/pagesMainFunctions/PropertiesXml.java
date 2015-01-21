package user.instabeat.me.pagesMainFunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class PropertiesXml {
	
	/**
	 * Load property from src/main/resources/properties.xml file.
	 *
	 * @param propertyName the property name
	 * @return the string value
	 */
	public static String loadProperty(String propertyName) {
		Properties props = new Properties();
		try {
			FileInputStream fis = new FileInputStream("src/main/resources/properties.xml");
			props.loadFromXML(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props.getProperty(propertyName);
	}

}
