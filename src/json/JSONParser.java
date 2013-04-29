package json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class JSONParser {

	//***************************************************************
				//JSON OBJECT
	//***************************************************************
    static JSONObject random= null;
    static JSONObject details= null;
    //static JSONObject next= null;
	//***************************************************************
	//Parameters
    //***************************************************************
    static String cookie;
    InputStream is = null;
    static Semaphore sem=new Semaphore(0);
 
    // constructor
	 
	public JSONParser() {
		super();
	}
	/**
	 * 
	 */
    public void Random() {
        
        
    	JSONRandom ran= new JSONRandom();
    	
    	Thread t =new Thread(ran);
        
    	t.start();
        
    	try {
			sem.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	        // try parse the string to a JSON object
    	        try {
    	            random = new JSONObject(ran.getJsonRandom());
    	            details= new JSONObject(ran.getJsonDetails());
    	        } catch (JSONException e) {
    	            Log.e("JSON Parser", "Error parsing data " + e.toString());
    	        }
    	 
    	        // return JSON String
    	   
    	    }
	/**
	 * 
	 */
    public void Next() {
        
        
    	JSONNext nex= new JSONNext();
    	
    	Thread t =new Thread(nex);
        
    	t.start();
        
    	try {
			sem.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	        // try parse the string to a JSON object
    	        try {
    	            random = new JSONObject(nex.getJsonNext());
    	            details= new JSONObject(nex.getJsonDetails());
    	        } catch (JSONException e) {
    	            Log.e("JSON Parser", "Error parsing data " + e.toString());
    	        }
    	   
    	    }
  
/**
 * Function called in order to transform the InputStream "is" in a string
 * @return 
 */
protected String readStream() {
	  BufferedReader reader = null;
	  try {
	    reader = new BufferedReader(new InputStreamReader(is));
	    String line = "";
	    StringBuilder sb = new StringBuilder();
	    while ((line = reader.readLine()) != null) {
	      System.out.println(line);
	      sb.append(line + "\n");
	    }
	    return sb.toString();
	  } catch (IOException e) {
		  e.printStackTrace();
		  return null;
	  } finally {
	    if (reader != null) {
	      try {
	        reader.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	        }
	    }
	  }
	  
	}


public InputStream getIs() {
	return is;
}


public void setIs(InputStream is) {
	this.is = is;
}

public static JSONObject getRandom(){
	return random;
}

public static JSONObject getDetails(){
	return details;
}
}