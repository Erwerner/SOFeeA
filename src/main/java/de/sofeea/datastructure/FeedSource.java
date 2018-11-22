package de.sofeea.datastructure;
 
import java.net.MalformedURLException;
import java.net.URL;

public class FeedSource {
	private URL mURL;
	private int mID;
	private String mGroup;
	private String mTitle;
	private String mDescription;

	public FeedSource(int pID, String pURL ) throws MalformedURLException{
		mURL = new URL(pURL);
		mID = pID;
	}

	public URL getURL() {
		return mURL;
	}
	public int getID() {
		return mID;
	}
	public String getGroup() {
		return mGroup;
	}
	public void setGroup(String pGroup) {
		this.mGroup = pGroup;
	}
	public void setDescription(String pDescription){
		mDescription = pDescription;
	}
	public void setTitle(String pTitle){
		mTitle = pTitle;
	}
	public String getTitle() { 
		return mTitle;
	}

	public String getDescription() { 
		return mDescription;
	}


}