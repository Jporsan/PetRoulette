package json;

import java.net.HttpURLConnection;
import java.net.URL;

public class JSONRandom extends JSONParser implements Runnable {

    String jsonRandom ;
    String jsonDetails;
    static String urlRandom="http://78.193.45.72:20081/api/random/";
    static String urlDetails="http://78.193.45.72:20081/api/details/";
	
    public JSONRandom() {
		super();
		// TODO Auto-generated constructor stub
	}

	 public void run(){
	    	
	    	try {
				  URL _url = new URL(urlRandom);
				  HttpURLConnection con = (HttpURLConnection) _url
				    .openConnection();
				  
				  takeCookieWithConnection(con);
				  this.setIs(con.getInputStream());
				  this.setJsonRandom(readStream());			  


				  } catch (Exception e) {
				  e.printStackTrace();
				}
	    	try {
				  URL _url = new URL(urlDetails);
				  HttpURLConnection con = (HttpURLConnection) _url
				    .openConnection();

				  con.setRequestProperty("Cookie", cookie);
				  con.connect();
				  this.setIs(con.getInputStream());
				  this.setJsonDetails(readStream());
				  con.disconnect();
				  sem.release();
				  } catch (Exception e) {
				  e.printStackTrace();
				}
	    	
	    }
	 
	 
	 /**
	  * Method that gets the cookie needed
	  * @param con
	  */
	 public void takeCookieWithConnection(HttpURLConnection con){
		 
		  String headerName=null;
			 
		  for (int i=1; (headerName = con.getHeaderFieldKey(i))!=null; i++) {
		   	if (headerName.equals("Set-Cookie")) {                  
		  	cookie = con.getHeaderField(i);
		  	cookie = cookie.substring(0, cookie.indexOf(";"));
	        String cookieName = cookie.substring(0, cookie.indexOf("="));
	        String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
		  	System.out.println(cookie);
		  	System.out.println(cookieName+"="+cookieValue);
		   	}
		   	}
	 }

	public String getJsonRandom() {
		return jsonRandom;
	}

	public void setJsonRandom(String jsonRandom) {
		this.jsonRandom = jsonRandom;
	}

	public String getJsonDetails() {
		return jsonDetails;
	}

	public void setJsonDetails(String jsonDetails) {
		this.jsonDetails = jsonDetails;
	}
	public String getCookie(){
		return cookie;
	}
	
}
