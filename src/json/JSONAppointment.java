package json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class JSONAppointment extends JSONParser implements Runnable {

    String jsonresult;
    String name;
    String email;
    String contact_number;
    String time;
    String date;
    
    static String urlAppointement="http://78.193.45.72:20081/api/appointment/";
    private static String APP_TAG="JSONAPPOINTMENT->";
	public JSONAppointment() {
		// TODO Auto-generated constructor stub
	}
	
	public JSONAppointment(String name, String email, String contact_number,
			String time, String date) {
		super();
		this.name = name;
		this.email = email;
		this.contact_number = contact_number;
		this.time = time;
		this.date = date;
	}

	public void postHttpResponse() {
		
	    Log.d(APP_TAG, "Going to make a post request");
	    //StringBuilder response = new StringBuilder();
	    try {
	    	
	    	//Constructing the request
	        HttpPost post = new HttpPost("http://78.193.45.72:20081/api/appointment/");
	      
	        post.addHeader("Cookie",cookie);
	        
	        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
	        params.add(new BasicNameValuePair("name",name));
	        params.add(new BasicNameValuePair("email",email));
	        params.add(new BasicNameValuePair("contact_number",contact_number));
	        params.add(new BasicNameValuePair("time",time));
	        params.add(new BasicNameValuePair("date",date));

	        post.setEntity(new UrlEncodedFormEntity(params));
	        //send the request
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpResponse httpResponse = httpClient.execute(post);
	        
	        
	        if (httpResponse.getStatusLine().getStatusCode() == 200) {
	            Log.d(APP_TAG, "HTTP POST succeeded");
	            HttpEntity messageEntity = httpResponse.getEntity();
	            this.setIs(messageEntity.getContent());

	           setJsonresult(readStream());
	           System.out.println(getJsonresult());
	        } else {
	            Log.e(APP_TAG, "HTTP POST status code is not 200");
	        }
	    } catch (Exception e) {
	        Log.e(APP_TAG, e.getMessage());
	    }
	    Log.d(APP_TAG, "Done with HTTP posting");
	    //return response.toString();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		postHttpResponse();
		sem.release();
	}

	public String getJsonresult() {
		return jsonresult;
	}

	public void setJsonresult(String jsonresult) {
		this.jsonresult = jsonresult;
	}

}
