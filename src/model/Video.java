package model;

public class Video {

/******************************************************************************************
 *                                  Variables
******************************************************************************************/
	private String video_url;
	private String video_title;
	
/******************************************************************************************
*                                 Methods
******************************************************************************************/
		
	
/******************************************************************************************
*                                  Constructors
******************************************************************************************/

	public Video(String video_url, String video_title) {
		super();
		this.video_url = video_url;
		this.setVideo_title(video_title);
	}
	

	
/******************************************************************************************
*                                  Getters and setters
******************************************************************************************/
	
	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}



	public String getVideo_title() {
		return video_title;
	}



	public void setVideo_title(String video_title) {
		this.video_title = video_title;
	}

}
