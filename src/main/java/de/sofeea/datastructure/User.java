package de.sofeea.datastructure;
 
public class User {
	private Integer mUserID;
	private AuthenticationData mAuthentication;
	
	public User(Integer pUserID){
		mUserID = pUserID; 
	} 
	public Integer getUserID(){
		return mUserID;
	}
	public AuthenticationData getAuthentication() { 
		return mAuthentication;
	}
	public void setAuthentication(AuthenticationData pAuthentication){
		mAuthentication = pAuthentication;
	}
}
