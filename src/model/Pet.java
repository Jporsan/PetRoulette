package model;

import java.util.*;

public class Pet {

/******************************************************************************************
 *                                  Variables
 ******************************************************************************************/

	private int pet_id;
	private String pet_name;
	private String pet_race;
	private String pet_species;
	private String pet_description;
	private String pet_birthDate;
	private String pet_createdDate;
	private int pet_nextCount;
	private int shelter_id;
	private String shelter_phoneNumber;
	private String shelter_name;
	private String shelter_address;
	private String shelter_youtubeChannel;
	private String shelter_email;
	private String shelter_creationDate;
	private Video pet_currentVideo;
	private ArrayList<Video> pet_videoList;
	
	
/******************************************************************************************
 *                                 Methods
 ******************************************************************************************/
	
	
	public void setDetails(int shelter_id, String shelter_phoneNumber, String shelter_name,
			String shelter_address, String shelter_email,
			String shelter_creationDate, ArrayList<Video> pet_videoList) {

		setShelter_id(shelter_id);
		setShelter_phoneNumber(shelter_phoneNumber);
		setShelter_name(shelter_name);
		setShelter_address(shelter_address);
		setShelter_email(shelter_email);
		setShelter_creationDate(shelter_creationDate);
		setPet_videoList(pet_videoList);
		
	}

	
	
	
/******************************************************************************************
 *                                  Constructors
 ******************************************************************************************/
	
	public Pet(int pet_id, String pet_name, String pet_race, String pet_species,
		String pet_description, String pet_birthDate,
		String pet_createdDate, int pet_nextCount,Video video) {
	super();
	this.pet_id = pet_id;
	this.pet_name = pet_name;
	this.pet_race = pet_race;
	this.pet_species = pet_species;
	this.pet_description = pet_description;
	this.pet_birthDate = pet_birthDate;
	this.pet_createdDate = pet_createdDate;
	this.pet_nextCount = pet_nextCount;
	this.pet_currentVideo=video;
	this.pet_videoList=new ArrayList<Video>();
	
}
/**
 * 
 * @param pet_id
 * @param pet_name
 * @param pet_race
 * @param pet_species
 * @param pet_description
 * @param pet_birthDate
 * @param pet_createdDate
 * @param pet_nextCount
 * @param shelter_id
 * @param shelter_phoneNumber
 * @param shelter_name
 * @param shelter_address
 * @param shelter_youtubeChannel
 * @param shelter_email
 * @param shelter_creationDate
 * @param pet_currentVideo
 * @param pet_videoList
 */
	public Pet(int pet_id, String pet_name, String pet_race, String pet_species,
		String pet_description, String pet_birthDate,
		int pet_nextCount, int shelter_id, String shelter_phoneNumber,
		String shelter_name, String shelter_address,
		String shelter_youtubeChannel, String shelter_email,
		String shelter_creationDate, Video pet_currentVideo,
		ArrayList<Video> pet_videoList) {
	super();
	this.pet_id = pet_id;
	this.pet_name = pet_name;
	this.pet_race = pet_race;
	this.pet_species = pet_species;
	this.pet_description = pet_description;
	this.pet_birthDate = pet_birthDate;
	this.pet_createdDate = null;
	this.pet_nextCount = pet_nextCount;
	this.shelter_id = shelter_id;
	this.shelter_phoneNumber = shelter_phoneNumber;
	this.shelter_name = shelter_name;
	this.shelter_address = shelter_address;
	this.shelter_youtubeChannel = shelter_youtubeChannel;
	this.shelter_email = shelter_email;
	this.shelter_creationDate = shelter_creationDate;
	this.pet_currentVideo = pet_currentVideo;
	this.pet_videoList = pet_videoList;
}




	/******************************************************************************************
	 *                                  Getters and setters
	 ******************************************************************************************/
	
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public String getPet_race() {
		return pet_race;
	}
	public void setPet_race(String pet_race) {
		this.pet_race = pet_race;
	}
	public String getPet_species() {
		return pet_species;
	}
	public void setPet_species(String pet_species) {
		this.pet_species = pet_species;
	}
	public String getPet_birthDate() {
		return pet_birthDate;
	}
	public void setPet_birthDate(String pet_birthDate) {
		this.pet_birthDate = pet_birthDate;
	}

	public String getPet_createdDate() {
		return pet_createdDate;
	}


	public void setPet_createdDate(String pet_createdDate) {
		this.pet_createdDate = pet_createdDate;
	}


	public int getPet_nextCount() {
		return pet_nextCount;
	}


	public void setPet_nextCount(int pet_nextCount) {
		this.pet_nextCount = pet_nextCount;
	}


	public int getShelter_id() {
		return shelter_id;
	}
	public void setShelter_id(int shelter_id) {
		this.shelter_id = shelter_id;
	}
	public String getShelter_phoneNumber() {
		return shelter_phoneNumber;
	}
	public void setShelter_phoneNumber(String shelter_phoneNumber) {
		this.shelter_phoneNumber = shelter_phoneNumber;
	}
	public String getShelter_name() {
		return shelter_name;
	}
	public void setShelter_name(String shelter_name) {
		this.shelter_name = shelter_name;
	}
	public String getShelter_address() {
		return shelter_address;
	}
	public void setShelter_address(String shelter_address) {
		this.shelter_address = shelter_address;
	}
	public String getShelter_email() {
		return shelter_email;
	}
	public void setShelter_email(String shelter_email) {
		this.shelter_email = shelter_email;
	}
	public String getShelter_creationDate() {
		return shelter_creationDate;
	}
	public void setShelter_creationDate(String shelter_creationDate) {
		this.shelter_creationDate = shelter_creationDate;
	}
	public Video getPet_currentVideo() {
		return pet_currentVideo;
	}


	public void setPet_currentVideo(Video pet_currentVideo) {
		this.pet_currentVideo = pet_currentVideo;
	}


	public ArrayList<Video> getPet_videoList() {
		return pet_videoList;
	}


	public void setPet_videoList(ArrayList<Video> pet_videoList) {
		this.pet_videoList = pet_videoList;
	}
	public String getPet_description() {
		return pet_description;
	}
	public void setPet_description(String pet_description) {
		this.pet_description = pet_description;
	}




	public String getShelter_youtubeChannel() {
		return shelter_youtubeChannel;
	}




	public void setShelter_youtubeChannel(String shelter_youtubeChannel) {
		this.shelter_youtubeChannel = shelter_youtubeChannel;
	}
	
}
