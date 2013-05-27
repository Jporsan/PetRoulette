package com.jeje.petroulette;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import model.Appointment;
import fragment.DatePickerFragment;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AdoptActivity extends FragmentActivity implements OnDateSetListener {

	//Parameters
	EditText editName;
	EditText editEmail;
	EditText editPhone_number;
	EditText editDate;
	Appointment appointment;
	ApplicationController ac;
	final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adopt);
		editName= (EditText) findViewById(R.id.name);
		editEmail= (EditText) findViewById(R.id.email);
		editPhone_number=(EditText) findViewById(R.id.phone_number);
		editDate=(EditText) findViewById(R.id.date);
		appointment=new Appointment();

	}
	
	
	public void showDatePickerDialog(View v) {
	    DatePickerFragment newFragment = DatePickerFragment.newInstance(year, month, day);
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		
	 String date= String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+String.valueOf(dayOfMonth);	 
	 setYear(year);
	 setMonth(monthOfYear);
	 setDay(dayOfMonth);
	 appointment.setRequested_date(date);
	 editDate.setText(date);
	 
	}

	public void arrangeAppointment(View view){
		int check=0; // Used in order to check if every field is filled
		
		ac=(ApplicationController) getApplicationContext();
		appointment.setAppointment_pet(ac.getPet());
		if (editName.getText().toString().equals("")){
		    
			Toast.makeText(this, "You did not enter your name", Toast.LENGTH_SHORT).show();
			return;
		}
		else{
			appointment.setUser_name(editName.getText().toString());
			check++;
		}
		
		if (editEmail.getText().toString().equals("")){
			
			Toast.makeText(this, "You did not enter your email", Toast.LENGTH_SHORT).show();
			return;

		}
		else{
			appointment.setUser_email(editEmail.getText().toString());
			check++;
		}
		
		if (editPhone_number.getText().toString().equals("")){
			
			Toast.makeText(this, "You did not enter your phone number", Toast.LENGTH_SHORT).show();
			return;
		}
		else{
			appointment.setUser_phoneNumber(editPhone_number.getText().toString());
			check++;
		}
		
		if(editDate.getText().toString().equals("")){
			Toast.makeText(this, "You did not enter a date", Toast.LENGTH_SHORT).show();
			return;
		}
		else{
			
			appointment.setAppointment_creationDate(obtainTodayDate());
			Date time=new Date();
			Date date=new Date();
			
			time=convertStringtoDate(appointment.getAppointment_creationDate());
			date=convertStringtoDate(appointment.getRequested_date());
			
			if (time.getTime()>date.getTime()){
				
				Toast.makeText(this, "Please, select a date after today", Toast.LENGTH_SHORT).show();
				return;
				
			}
			check++;
		}
		
		if (check==4){
			//DO SOMETHING WITH appointment
			String result=appointment(appointment);
			Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
			finish();
		}
		
	}
	
	public String obtainTodayDate(){
		
        
        int _year = c.get(Calendar.YEAR);
        int _month = c.get(Calendar.MONTH);
        int _day = c.get(Calendar.DAY_OF_MONTH);
        String date= String.valueOf(_year)+"-"+String.valueOf(_month+1)+"-"+String.valueOf(_day);
		return date;
	}
	public String appointment(Appointment app){
		
		int result= ac.getDes().arrangeAppointment(app);
		switch(result){
		case 0: return "Appointment refused";
		case 1: return "Appointment accepted";
		case -1: return "Your request has failed, try again"; 
		}
		return null;
	}
	
	@SuppressLint("SimpleDateFormat")
	public Date convertStringtoDate(String date){
		 SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		 Date result_date= new Date();
		 
		 try {
			result_date=dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		 return result_date;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}
	
}
