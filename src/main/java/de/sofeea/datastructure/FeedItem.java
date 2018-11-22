package de.sofeea.datastructure;

public class FeedItem {
	private String mTitle;
	private String mDescription;
	private String mLink; 
	private Integer mID;
	private FeedSource mSource;
	
	public FeedItem(Integer pID, FeedSource pSource, String pTitle, 
					String pDescription, String pLink){
		mID = pID;
		mDescription = pDescription;
		mLink = pLink;
		mTitle = pTitle;
		mSource = pSource;
	} 
	public String getTitle() {
		return mTitle;
	} 
	public String getDescription() {
		return mDescription;
	} 
	public String getLink() {
		return mLink;
	} 
	public Integer getID() {
		return mID;
	} 
	public FeedSource getSource() {
		return mSource;
	} 
}
