package user.instabeat.me.pagesMainFunctions;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class PostSessions {
	
	public static void postSession () throws Exception{
		 
		JSONObject response = ApplicationLogin.AppLogin("fortestgl+1@gmail.com");
		String username = response.getString("user");
		String usertoken = response.getString("token");
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {
	        	
//	        	HttpPost httppost = new HttpPost("http://user.instabeat.me/user/unpack");
	        	HttpPost httppost = new HttpPost("http://staging-web-664817575.us-west-2.elb.amazonaws.com/user/unpack");
	        	
	            httppost.addHeader("User-Agent", "instabeat-desktop/MAJOR.MINOR.{osx, win32, win64}.OSRELATED");
	            httppost.addHeader("MIME-Version", "1.0");
	            httppost.addHeader("Connection", "Keep-Alive");
	            httppost.addHeader("Accept-Encoding", "gzip, deflate");
	            httppost.addHeader("Accept-Language", "en-US,*");
	                       
	            FileBody bin = new FileBody(new File("D:\\Java\\Projects\\session.bin"));
//	            FileBody bin = new FileBody(new File("D:\\JMeter\\session.bin"));
	            
	            HttpEntity reqEntity = MultipartEntityBuilder.create()
	            		.addTextBody("user[userId]", username)
	            		.addTextBody("user[token]", usertoken)
	            		.addTextBody("user[deviceId]", "0d47323430333135003d003a")
	            		.addPart("sessions", bin).build();
            
	            httppost.setEntity(reqEntity);

	            Utils.Log.info("|Executing request: " + httppost.getRequestLine());
	            CloseableHttpResponse response2 = httpclient.execute(httppost);
	            try {
	            	Utils.Log.info("|Status of request is: " + response2.getStatusLine());
	            	Utils.Log.info("|Response is: " + response2.toString());
	                HttpEntity resEntity = response2.getEntity();
	                if (resEntity != null) {
	                	Utils.Log.info("|Response content length: " + resEntity.getContentLength());
	                }
	                EntityUtils.consume(resEntity);
	            } finally {
	                response2.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	    }

}
