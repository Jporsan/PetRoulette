package json;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.format.DateFormat;

import model.Pet;
import model.Video;

public class Deserializer {

	private JSONParser jsonparser;
	private Pet pet;
	
	//JSONOBJECT RANDOM/NEXT
	//JSONObject data
	private static final String TAG_DATA="data";
	//JSONObject video
	private static final String TAG_VIDEO = "video";
	private static final String TAG_TITLE="title";
	private static final String TAG_ORDERING="ordering";
	private static final String TAG_PUBLISHED="published";
	private static final String TAG_VIDEOLINK="video_link";
	private static final String TAG_PETID="pet_id";
	private static final String TAG_ID="id";
	//JSONobject Pet
	private static final String TAG_PET = "pet";
	private static final String TAG_SPECIESID="species_id";
	private static final String TAG_DESCRIPTION="description";
	private static final String TAG_CREATEDDATETIME="created_datetime";
	private static final String TAG_ORGANIZATIONID="organization_id";
	private static final String TAG_DATEOFBIRTH="date_of_birth";
	private static final String TAG_RACEID="race_id";
	private static final String TAG_NAME="name";
	
	//JSONOBJECT DETAILS
	//pet
	private static final String TAG_SPECIESNAME="species_name";
	private static final String TAG_RACENAME="race_name";
	private static final String TAG_VIDEOS="videos";
	private static final String TAG_ORGANIZATION="organization";
	private static final String TAG_YOUTUBECHANNEL="youtube_channel";
	private static final String TAG_CONTACTNUMBER="contact_number";
	//TAG_NAME already exist
	private static final String TAG_ADDRESS="address";
	private static final String TAG_EMAIL="email";
	
	public Deserializer(){
		
		jsonparser=new JSONParser();
	}
	
	public void Deserialize(){
		
		JSONObject random=JSONParser.getRandom();
		JSONObject details=JSONParser.getDetails();
		int pet_id;
		String pet_name;
		String pet_race;
		String pet_species;
		String pet_description;
		String pet_birthDate;
		String pet_createdDate;
		int pet_nextCount;
		int shelter_id;
		String shelter_phoneNumber;
		String shelter_name;
		String shelter_address;
		String shelter_youtubeChannel;
		String shelter_email;
		String shelter_creationDate;
		Video pet_currentVideo;
		ArrayList<Video> pet_videoList = null;
		
		try {

			//Get the object needed, in the JSONObject
			JSONObject randomDataObj=random.getJSONObject(TAG_DATA);
			JSONObject randomVideoObj=randomDataObj.getJSONObject(TAG_VIDEO);
			JSONObject randomPetObj=randomVideoObj.getJSONObject(TAG_PET);
			
			JSONObject detailsDataObj=details.getJSONObject(TAG_DATA);
			JSONObject detailsPetObj=detailsDataObj.getJSONObject(TAG_PET);
			JSONObject detailsShelterObj=detailsPetObj.getJSONObject(TAG_ORGANIZATION);
			JSONArray videosArray=detailsPetObj.getJSONArray(TAG_VIDEOS);
			
			
			pet_currentVideo=new Video( randomVideoObj.getString(TAG_VIDEOLINK),randomVideoObj.getString(TAG_TITLE));
			pet_id =randomVideoObj.getInt(TAG_PETID);
			pet_name=randomPetObj.getString(TAG_NAME);
			pet_race=detailsPetObj.getString(TAG_RACENAME);
			pet_species=detailsPetObj.getString(TAG_SPECIESNAME);
			pet_description=detailsPetObj.getString(TAG_DESCRIPTION);
			pet_birthDate=detailsPetObj.getString(TAG_DATEOFBIRTH);
			pet_nextCount=0;
			shelter_id=randomPetObj.getInt(TAG_ORGANIZATIONID);
			shelter_phoneNumber=detailsShelterObj.getString(TAG_CONTACTNUMBER);
			shelter_address=detailsShelterObj.getString(TAG_ADDRESS);
			shelter_name=detailsShelterObj.getString(TAG_NAME);
			shelter_youtubeChannel=detailsShelterObj.getString(TAG_YOUTUBECHANNEL);
			shelter_email=detailsShelterObj.getString(TAG_EMAIL);
			shelter_creationDate=detailsShelterObj.getString(TAG_CREATEDDATETIME);
			pet_videoList=Deserializer.JSONArrayToArrayList(videosArray);
			
			this.pet=new Pet(pet_id, pet_name, pet_race, pet_species, pet_description, pet_birthDate, 
							pet_nextCount, shelter_id, shelter_phoneNumber, shelter_name, shelter_address, 
							shelter_youtubeChannel, shelter_email, shelter_creationDate, pet_currentVideo,
							pet_videoList);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public Pet Random(){
		
		this.jsonparser.Random();
		this.Deserialize();
		
		return pet;
	}
	
	public Pet Next(){
		this.jsonparser.Next();
		this.Deserialize();
		
		return pet;
	}
	
	
	/**
	 * 
	 * @param array
	 * @return Return an ArrayList of Video from an JSONArray 
	 */
	private static ArrayList<Video> JSONArrayToArrayList(JSONArray array){
		ArrayList<Video> temp=new ArrayList<Video>(0);
		try{
			for(int i = 0; i < array.length(); i++){
				JSONObject c = array.getJSONObject(i);
				// Storing each json item in variable
				Video video=new Video(c.getString(TAG_VIDEOLINK),c.getString(TAG_TITLE));
				temp.add(video);
			}
			return temp;
		} catch (JSONException e) {
		    e.printStackTrace();
		    return null;
		}
	}
}
