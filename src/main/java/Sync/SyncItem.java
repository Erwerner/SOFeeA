package Sync;

import javax.mail.Flags.Flag;

import de.sofeea.datastructure.FeedItem;

import javax.mail.Message;
import javax.mail.MessagingException;

public class SyncItem extends FeedItem {  
	private Boolean mPropRating = false;
	private Boolean mPropReadContent = false;
	private boolean mSyncedSuccessfull;
	private Message mMessage;

	public SyncItem(Integer pID, Message pMessage) {
		super(pID, null, "", "", "");
		mMessage = pMessage; 
	}  
	public Boolean getPropRating() {
		return mPropRating;
	}
	public void setPropRating( Boolean pRating) {
		mPropRating = pRating;
	}
	public Boolean getPropReadContent() {
		return mPropReadContent;
	}
	public void setPropReadContent(Boolean pPropContent) {
		mPropReadContent = pPropContent;
	}
	public void setSyncedSuccessfull() {
		mSyncedSuccessfull = true;
	}
	public Boolean isSyncedSuccessfull(){
		return mSyncedSuccessfull;
	}
	public Message getMessage(){
		return mMessage;
	} 
	public void setPropsFromFlags( ) throws MessagingException{  
		if (mMessage.isSet(Flag.FLAGGED)==true)
			// wichtig => E-Mail-Inhalt
			setPropReadContent( true);
		if (mMessage.isSet(Flag.SEEN)==false) 
			// ungelesen => interessant
			setPropRating( true ); 
	} 
}
