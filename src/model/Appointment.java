package model;


import android.text.format.*;

public class Appointment {

/******************************************************************************************
*                                  Variables
******************************************************************************************/
	private Pet appointment_pet;
	private String user_name;
	private String user_email;
	private String user_phoneNumber;
	private DateFormat appointment_creationDate;
	private DateFormat requested_date;
	
/******************************************************************************************
*                                 Methods
******************************************************************************************/	
	
/******************************************************************************************
*                                  Constructors
******************************************************************************************/	
	public Appointment(Pet appointment_pet, String user_name,
			String user_email, String user_phoneNumber, DateFormat requested_date) {
		super();
		this.appointment_pet = appointment_pet;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_phoneNumber = user_phoneNumber;
		this.appointment_creationDate= null;
		this.requested_date=requested_date;
	}

	
/******************************************************************************************
*                                  Getters and setters
******************************************************************************************/	
	
public Pet getAppointment_pet() {
	return appointment_pet;
}

public void setAppointment_pet(Pet appointment_pet) {
	this.appointment_pet = appointment_pet;
}

public String getUser_name() {
	return user_name;
}

public void setUser_name(String user_name) {
	this.user_name = user_name;
}

public String getUser_email() {
	return user_email;
}

public void setUser_email(String user_email) {
	this.user_email = user_email;
}

public String getUser_phoneNumber() {
	return user_phoneNumber;
}

public void setUser_phoneNumber(String user_phoneNumber) {
	this.user_phoneNumber = user_phoneNumber;
}

public DateFormat getAppointment_creationDate() {
	return appointment_creationDate;
}

public void setAppointment_creationDate(DateFormat appointment_creationDate) {
	this.appointment_creationDate = appointment_creationDate;
}

public DateFormat getRequested_date() {
	return requested_date;
}

public void setRequested_date(DateFormat requested_date) {
	this.requested_date = requested_date;
}
	

}
