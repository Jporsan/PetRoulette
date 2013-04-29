package com.jeje.petroulette;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jeje.petroulette.OpenYouTubePlayerActivity;
import com.keyes.youtube.YouTubeUtility;

import model.Pet;
import json.Deserializer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends Activity {

	Button randomButton;
	Button nextButton;
	TextView shelter;
	TextView species;
	TextView name;
	TextView description;
	Pet pet;
	OpenYouTubePlayerActivity youtube;
	protected ProgressBar mProgressBar;
	protected TextView    mProgressMessage;
	protected VideoView   mVideoView;
	static Deserializer des=new Deserializer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setUpView();

		youtube=new OpenYouTubePlayerActivity(mProgressBar, mProgressMessage, mVideoView, this);
		
		random();
		/*
		randomButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				random();
				
				
			}
		});
       */
		nextButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				next();
				
				
			}
		});
		
	}

	public void random(){
		pet=des.Random();
		shelter.setText("Shelter: " + pet.getShelter_name());
		species.setText("species : "+pet.getPet_species() );
		//description.setText("video link : "+ pet.getPet_currentVideo().getVideo_url());
		description.setText("Description : "+pet.getPet_description());
		name.setText("Pet name : "+ pet.getPet_name());
		String url=pet.getPet_currentVideo().getVideo_url();
		// Linkify youtube URLs which are not already links.

		String id=getYoutubeIdWithUrl(url);
		youtube.playVideo(id);
		
	}
	
	public void next(){
		
		pet=des.Next();
		shelter.setText("Shelter: " + pet.getShelter_name());
		species.setText("species : "+pet.getPet_species() );
		//description.setText("video link : "+ pet.getPet_currentVideo().getVideo_url());
		description.setText("Description : "+pet.getPet_description());
		name.setText("Pet name : "+ pet.getPet_name());
		String url=pet.getPet_currentVideo().getVideo_url();
		// Linkify youtube URLs which are not already links.

		String id=getYoutubeIdWithUrl(url);
		youtube.playVideo(id);
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
	
	private void setUpView(){
		
		setContentView(R.layout.activity_main);
		
		//randomButton=(Button)findViewById(R.id.randomButton);
		nextButton=(Button)findViewById(R.id.nextButton);
		shelter=(TextView) findViewById(R.id.shelter);
		species=(TextView)findViewById(R.id.species);
		name=(TextView) findViewById(R.id.name);
		description=(TextView) findViewById(R.id.description);
		
	    mVideoView=(VideoView)findViewById(R.id.mVideoView);

	    mProgressBar=(ProgressBar) findViewById(R.id.mProgressBar);
	    mProgressBar.setIndeterminate(true);
	    mProgressBar.setVisibility(View.VISIBLE);
	    mProgressBar.setEnabled(true);
	    mProgressBar.setId(4);


	    mProgressMessage = (TextView) findViewById(R.id.mProgressMessage);
	    mProgressMessage.setId(5);
	    mProgressMessage.setTextColor(Color.LTGRAY);
	    mProgressMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
	    mProgressMessage.setText("...");

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
