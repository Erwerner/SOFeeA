package de.sofeea.datastructure;

public class AuthenticationData {
	private String mLogin;
	private String mPassword;
	private String mConnectionString;
	
	public AuthenticationData(String pLogin, String pPassword, String pConnectionString){ 
		mLogin = pLogin;
		mPassword = pPassword;
		mConnectionString = pConnectionString;
	}
	public String getLogin() {
		return mLogin;
	} 
	public String getPassword() {
		return mPassword;
	} 
	public String getConnectionString() {
		return mConnectionString;
	} 
}
