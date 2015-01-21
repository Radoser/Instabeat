package user.instabeat.me.pagesMainFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParametersManager {

	public String EMmaximumNumberWeightLbs;
	public String SMprofileSettingsUpdate;
	public String EMmaximumNumberHeightFt;
	public String EMmaximumNumberHeightIn;
	public String EMascendingOrderRequire;
	public String EMonlyLettersFirstName;
	public String EMshortLengthFirstName;
	public String EMincorrectCredentials;
	public String EMnewPasswordRequiered;
	public String EMwrongCurrentPassword;
	public String EMRHRIsLessThenRequire;
	public String EMRHRIsMoreThenRequire;
	public String EMshortLengthLastName;
	public String EMminimumNumberHeight;
	public String EMminimumNumberWeight;
	public String EMonlyLettersLastName;
	public String EMmaximumNumberHeight;
	public String EMmaximumNumberWeight;
	public String EMoldPasswordRequired;
	public String EMdefaultPoolLength;
	public String EMrequiredFirstName;
	public String EMwrongDataInHRZBox;
	public String EMrequiredLastName;
	public String EMpasswordRequired;
	public String EMpasswordNotMatch;
	public String EMinvalidPassword;
	public String EMconfirmRequired;
	public String EMwrongBirthdate;	
	public String EMprovideHeight;
	public String EMprovideWeight;
	public String SMProfileUpdate;
	public String EMinvalidEmail;
	public String EMuserNotFound;
	public String SMchangedPassword;
		
	public void getPropertyFields() {

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("src/main/resources/errorMessages.properties");
			prop.load(input);
					
		
			/*Error messages*/
			EMmaximumNumberWeightLbs = prop.get("maximumNumberWeightLbs").toString();
			EMmaximumNumberHeightFt = prop.get("maximumNumberHeightFt").toString();
			EMmaximumNumberHeightIn = prop.get("maximumNumberHeightIn").toString();
			EMascendingOrderRequire = prop.get("ascendingOrderRequire").toString();
			EMshortLengthFirstName = prop.get("shortLengthFirstName").toString();
			EMonlyLettersFirstName = prop.get("onlyLettersFirstName").toString();
			EMincorrectCredentials = prop.get("incorrectCredentials").toString();
			EMnewPasswordRequiered = prop.get("newPasswordRequiered").toString();
			EMwrongCurrentPassword = prop.get("wrongCurrentPassword").toString();
			EMRHRIsLessThenRequire = prop.get("RHRIsLessThenRequire").toString();
			EMRHRIsMoreThenRequire = prop.get("RHRIsMoreThenRequire").toString();
			EMshortLengthLastName = prop.get("shortLengthLastName").toString();
			EMonlyLettersLastName = prop.get("onlyLettersLastName").toString();
			EMminimumNumberHeight = prop.get("minimumNumberHeight").toString();
			EMminimumNumberWeight = prop.get("minimumNumberWeight").toString();
			EMmaximumNumberHeight = prop.get("maximumNumberHeight").toString();
			EMmaximumNumberWeight = prop.get("maximumNumberWeight").toString();			
			EMoldPasswordRequired = prop.get("oldPasswordRequired").toString();			
			EMrequiredFirstName = prop.get("requiredFirstName").toString();
			EMwrongDataInHRZBox = prop.get("wrongDataInHRZBox").toString();
			EMdefaultPoolLength = prop.get("defaultPoolLength").toString();
			EMpasswordNotMatch = prop.get("passwordNotMatch").toString();
			EMpasswordRequired = prop.get("passwordRequired").toString();
			EMrequiredLastName = prop.get("requiredLastName").toString();
			EMinvalidPassword = prop.get("invalidPassword").toString();
			EMconfirmRequired = prop.get("confirmRequired").toString();
			EMwrongBirthdate = prop.get("wrongBirthdate").toString();
			EMprovideHeight = prop.get("provideHeight").toString();
			EMprovideWeight = prop.get("provideWeight").toString();
			EMinvalidEmail = prop.get("invalidEmail").toString();
			EMuserNotFound = prop.get("userNotFound").toString();

			/*Green notifications*/
			SMProfileUpdate = prop.get("profileUpdate").toString();
			SMprofileSettingsUpdate = prop.get("profileSettingsUpdate").toString();
			SMchangedPassword = prop.get("changedPassword").toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

