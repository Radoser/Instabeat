package user.instabeat.me.pagesMainFunctions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class ApplicationLogin {
	
	protected static String stagingAppUrl = PropertiesXml.loadProperty("stagingApp.url");
	protected static String productionAppUrl = PropertiesXml.loadProperty("productionApp.url");
	
	public static JSONObject AppLogin(String value) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		/* GET method */
		/*
		 * String contentGet = Request.Get("http://user.instabeat.me/user")
		 * .connectTimeout(1000) .socketTimeout(1000)
		 * .execute().returnContent().asString();
		 * System.out.println(contentGet);
		 */

		/* POST method Login */
		/*
		 * String contentPost =
		 * Request.Post("http://ibeat.pub.globallogic.com/user/login")
		 * .bodyForm(Form.form().add("email",
		 * "testusergl+3@ukr.net").add("password", "123456").build())
		 * .execute().returnContent().asString();
		 * System.out.println(contentPost);
		 */

		HttpPost httpPost = new HttpPost(stagingAppUrl);

		httpPost.addHeader("User-Agent",
				"instabeat-desktop/MAJOR.MINOR.{osx, win32, win64}.OSRELATED");
		httpPost.addHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.addHeader("instabeat-api-id",
				"fa7686b57cf116d0a2102ad531356005");
		httpPost.addHeader("Connection", "Keep-Alive");
		httpPost.addHeader("Accept-Encoding", "gzip, deflate");
		httpPost.addHeader("Accept-Language", "en-US,*");

		List<NameValuePair> credentials = new ArrayList<NameValuePair>();

		credentials.add(new BasicNameValuePair("user[email]", value));
//		System.out.println("username is " + value);
		Utils.Log.info("|username is " + value);
		credentials.add(new BasicNameValuePair("user[password]", "123456"));
//		credentials.add(new BasicNameValuePair("user[device-id]",
//				"2d10323430333135003b0036")); //25 symbols
		httpPost.setEntity(new UrlEncodedFormEntity(credentials));

		CloseableHttpResponse response2 = httpclient.execute(httpPost);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response2.getEntity().getContent())));

		String output;
		String buffer = "";
//		System.out.println("Output from Server .... \n");
		Utils.Log.info("|Output from Server...");
		while ((output = br.readLine()) != null) {
			buffer += output;
		}

//		System.out.println(buffer);
		Utils.Log.info("|"+buffer);
		
		JSONObject jObject = new JSONObject(buffer);
		JSONObject data = jObject.getJSONObject("user");
		String username = data.getString("user");
		String usertoken = data.getString("token");

//		System.out.println(username);
		Utils.Log.info("|UserID is: "+username);
//		System.out.println(usertoken);
		Utils.Log.info("|User token is: "+usertoken);
		
		response2.close();
		httpclient.close();

		return data;

	}

}
