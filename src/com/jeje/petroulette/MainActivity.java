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

	Button nextButton;
	Button viewMoreButton;
	TextView shelter;
	TextView species;
	TextView name;
	TextView description;
	TextView nextCount;
	Pet pet;
	OpenYouTubePlayerActivity youtube;
	protected ProgressBar mProgressBar;
	protected TextView    mProgressMessage;
	protected VideoView   mVideoView;
	static Deserializer des=new Deserializer();
	ApplicationController ac;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setUpView();

		youtube=new OpenYouTubePlayerActivity(mProgressBar, mProgressMessage, mVideoView, this);
		ac=(ApplicationController) getApplicationContext();
		random();

		
	}

	public void viewMore(View view){
		
		Intent intent = new Intent(this,ViewMoreActivity.class);
		startActivityForResult(intent, 1);
		
	}
	public void random(){
		pet=des.Random();
		ac.setPet(pet);
		nextCount.setText("This pet has been nexted : " + String.valueOf( pet.getPet_nextCount()));
		String url=pet.getPet_currentVideo().getVideo_url();
		// Linkify youtube URLs which are not already links.

		String id=getYoutubeIdWithUrl(url);
		youtube.playVideo(id);
		
	}
	
	public void next(View view){
		
		pet=des.Next();
		ac.setPet(pet);
		nextCount.setText("This pet has been nexted : " + String.valueOf( pet.getPet_nextCount()));

		String url=pet.getPet_currentVideo().getVideo_url();
		
		// Linkify youtube URLs which are not already links.

		String id=getYoutubeIdWithUrl(url);
		youtube.playVideo(id);
		nextButton.setClickable(true);
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
	/**
	 * SetUp the view 
	 */
	private void setUpView(){
		
		setContentView(R.layout.activity_main);
		
		nextButton=(Button)findViewById(R.id.nextButton);
		viewMoreButton=(Button) findViewById(R.id.viewMoreButton);
		nextCount=(TextView) findViewById(R.id.nextCount);
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
	protected void onActivityResult(int requestCode, int resultCode,Intent data){
		if(requestCode==1){
			  if (requestCode == 1) {

				     if(resultCode == RESULT_OK){      
				         int result=data.getIntExtra("result",-1);
				         if(result>=0){
				        	 
				        	String url=pet.getPet_videoList().get(result).getVideo_url();
				     		String id=getYoutubeIdWithUrl(url);
				     		pet.setPet_currentVideo(pet.getPet_videoList().get(result));
				     		youtube.playVideo(id);
				         }
				     }
				     if (resultCode == RESULT_CANCELED) {    
				         //Write your code if there's no result
				     }
				  }
		
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
}
