package com.jeje.petroulette;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Pet;
import model.Video;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ViewMoreActivity extends Activity {

	private Pet pet;
	TextView petname;
	TextView description;
	TextView species;
	TextView race;
	TextView birthdate;
	TextView shelter_name;
	TextView shelter_phonenumber;
	TextView shelter_email;
	TextView shelter_address;
	TextView shelter_youtubeChannel;
	Button currentvideo;
	LinearLayout others;
	ProgressBar mProgressBar;
	MainActivity main;
	ApplicationController ac;
	OnClickListener listener;// listener used for all the images
	Intent returnIntent;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.viewmore_layout);

		
		ac=(ApplicationController) getApplicationContext();
		pet=ac.getPet();
		setUpView();
		
	}
	
	public void setUpView(){

		setContentView(R.layout.viewmore_layout);
		setUpListener();
		others=(LinearLayout) findViewById(R.id.Others);
		this.setUpTextViews();
		this.setUpButtons();
	}
	
	public void setUpListener(){
		listener=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				returnIntent=new Intent();
				returnIntent.putExtra("result", arg0.getId());
				
				setResult(RESULT_OK,returnIntent);
				finish();
			}
		};
		
	}
	
	public void setUpButtons(){
	
		ArrayList<Video> videos=pet.getPet_videoList();
		
		for (int i =0;i<videos.size();i++){
			
			String videoId=getYoutubeIdWithUrl(videos.get(i).getVideo_url());
			ImageView img=new ImageView(this);
			TextView title=new TextView(this);
			title.setId(i);
			title.setText(videos.get(i).getVideo_title());
			title.setGravity(Gravity.CENTER);
			others.addView(title);
			img.setId(i);
			img.setOnClickListener(listener);
			new DownloadImageTask(img).execute("http://img.youtube.com/vi/"+videoId+"/0.jpg");
			others.addView(img);
		}
	}
	
	public String getYoutubeIdWithUrl(String url){
		
		String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

	    Pattern compiledPattern = Pattern.compile(pattern);
	    Matcher matcher = compiledPattern.matcher(url);

	    if(matcher.find()){
	        return matcher.group();
	    }
		return null;
	}
	
	public void setUpTextViews(){
		
		petname=(TextView) findViewById(R.id.pet_name);
		petname.setText(pet.getPet_name());
		description=(TextView) findViewById(R.id.description);
		description.setText(pet.getPet_description());
		species=(TextView) findViewById(R.id.Species);
		species.setText(pet.getPet_species());
		race=(TextView) findViewById(R.id.Race);
		race.setText(pet.getPet_race());
		birthdate=(TextView) findViewById(R.id.Birthdate);
		birthdate.setText(pet.getPet_birthDate());		
		shelter_name=(TextView) findViewById(R.id.shelter_name);
		shelter_name.setText(pet.getShelter_name());
		shelter_phonenumber=(TextView) findViewById(R.id.shelter_phonenumber);
		shelter_phonenumber.setText(pet.getShelter_phoneNumber());
		shelter_email=(TextView) findViewById(R.id.shelter_email);
		shelter_email.setText(pet.getShelter_email());
		shelter_address=(TextView) findViewById(R.id.shelter_address);
		shelter_address.setText(pet.getShelter_address());
		shelter_youtubeChannel=(TextView) findViewById(R.id.shelter_youtubeChannel);

	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		  ImageView bmImage;

		  public DownloadImageTask(ImageView bmImage) {
		      this.bmImage = bmImage;
		  }

		  protected Bitmap doInBackground(String... urls) {
		      String urldisplay = urls[0];
		      Bitmap mIcon11 = null;
		      try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		      } catch (Exception e) {
		          Log.e("Error", e.getMessage());
		          e.printStackTrace();
		      }
		      return mIcon11;
		  }

		  protected void onPostExecute(Bitmap result) {
		      bmImage.setImageBitmap(result);
		  }
		}
}
