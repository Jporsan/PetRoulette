package com.jeje.petroulette;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jeje.petroulette.OpenYouTubePlayerActivity;
import com.keyes.youtube.YouTubeUtility;

import model.Pet;
import json.Deserializer;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
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
	
	ApplicationController ac;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		setUpView();

		youtube=new OpenYouTubePlayerActivity(mProgressBar, mProgressMessage, mVideoView, this);
		ac=(ApplicationController) getApplicationContext();
		random();

		
	}

	/**
	 * Start the ViewMoreActivity
	 * @param view
	 */
	public void viewMore(View view){
		
		Intent intent = new Intent(this,ViewMoreActivity.class);
		startActivityForResult(intent, 1);
		
	}
	/**
	 * Start the AdoptActivity
	 * @param view
	 */
	public void adopt(View view){
		
		Intent intent=new Intent(this,AdoptActivity.class);
		startActivity(intent);
	}
	

	public void donate(View view){
		
		Intent intent=new Intent(this,DonateActivity.class);
		startActivity(intent);
	}
	/**
	 * Perform the random to get the first pet and also to change the pet a the end of the current video
	 * 
	 */
	public void random(){
		mProgressBar.bringToFront();
		mProgressBar.setVisibility(View.VISIBLE);
		pet=ac.getDes().Random();
		ac.setPet(pet);//Set the pet in the ApplicationController so all the activity can have access to the pet
		
		nextCount.setText("This pet has been nexted : " + String.valueOf( pet.getPet_nextCount())+" times.");
		String url=pet.getPet_currentVideo().getVideo_url();
		// Linkify youtube URLs which are not already links.
		String id=getYoutubeIdWithUrl(url);
		youtube.playVideo(id);
		
	}
	/**
	 * 
	 * @param view
	 */
	public void next(View view){
		
		mVideoView.pause();
		mProgressBar.bringToFront();
		mProgressBar.setVisibility(View.VISIBLE);
		
		pet=ac.getDes().Next();
		ac.setPet(pet);
		nextCount.setText("This pet has been nexted : " + String.valueOf( pet.getPet_nextCount())+" times.");
		String url=pet.getPet_currentVideo().getVideo_url();
		// Linkify youtube URLs which are not already links.
		String id=getYoutubeIdWithUrl(url);
		youtube.playVideo(id);
		nextButton.setClickable(true);
	}

	/**
	 * Function that extract the video id from a youtube url
	 * @param url
	 * @return 
	 */
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
				     		String youtubeId=getYoutubeIdWithUrl(url);
				     		pet.setPet_currentVideo(pet.getPet_videoList().get(result));
				     		youtube.playVideo(youtubeId);
				         }
				     }
				     if (resultCode == RESULT_CANCELED) {    
				         //Write your code if there's no result
				    	 
				     }
				  }
		
		}
	}
	
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if(pet!=null){
		String url=pet.getPet_currentVideo().getVideo_url();
		// Linkify youtube URLs which are not already links.
		String id=getYoutubeIdWithUrl(url);
		youtube.playVideo(id);
		}
	}

	
}
