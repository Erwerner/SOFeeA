package Push;

import de.sofeea.datastructure.FeedItem;
import de.sofeea.datastructure.FeedSource;

public class PushItem extends FeedItem {
	private String mPushViewDescription;
	private Boolean mStatusSuccess=false;
	public PushItem(Integer pID, FeedSource pChannel, String pTitle, 
					String pDescription, String pLink, String pViewDescr) {
		super(pID, pChannel, pTitle, pDescription,pLink);
		mPushViewDescription = pViewDescr;
	}  
	public String getPushViewDescription(){
		return mPushViewDescription;
	}
	public Boolean getStatusSuccess() {
		return mStatusSuccess;
	}
	public void setStatusSuccess() {
		this.mStatusSuccess = true;
	}
}
