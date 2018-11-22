package Infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import Exceptions.ExConnectionError;
import de.sofeea.datastructure.AuthenticationData; 
public class IMAPBroker {
	private Session mSession;
    private Store mStore;
    private Properties mProperties;
	private AuthenticationData mAuthentication;
    private List<Folder> mOpenFolderLsit = new ArrayList<Folder>();
	 
	public IMAPBroker() { }

	public void openConnection(AuthenticationData pAuthentication) 
				throws ExConnectionError{
		mAuthentication = pAuthentication;
		initProperties();
		openSession();
		openStore();
	}

	public void closeConnection() throws ExConnectionError{
		try {
			for(Folder iFolder:mOpenFolderLsit){
				iFolder.close(true);//expunge
			}
			mStore.close();
		} catch (MessagingException e) {
			throw new ExConnectionError();
		}
	}
	
	public List<Message> getMailsFromFolder(String pFolder) {
		Folder lFolder;
        Message[] lMessageList;
        List<Message> rMessageFromFolderList = new ArrayList<Message>();
		try {
			lFolder = openFolder(pFolder);
		    lMessageList = lFolder.getMessages();
		      
		    for(Message iMessage : lMessageList)
		    	rMessageFromFolderList.add(iMessage); 
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	    return rMessageFromFolderList;
	}

	private void initProperties() {
        mProperties= System.getProperties();
        mProperties.setProperty("mail.store.protocol", "imaps");
	}

	private void openSession() {
        mSession= Session.getDefaultInstance(mProperties, null);
	}

	private void openStore() throws ExConnectionError {
		try {
			mStore = mSession.getStore("imaps");
	        mStore.connect(	mAuthentication.getConnectionString(),
	        				mAuthentication.getLogin(), 
	        				mAuthentication.getPassword());
		} catch (MessagingException e) {
			throw new ExConnectionError();
		}
	}
	@SuppressWarnings("static-access")
	private Folder openFolder(String pFolder) throws MessagingException {
		Folder lFolder;  
		lFolder =  mStore.getFolder(pFolder);
		lFolder.open(lFolder.READ_WRITE); 
		mOpenFolderLsit.add(lFolder);
	
		return lFolder; 
	} 
}
