package Sync;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags.Flag;
import javax.mail.Message;
import javax.mail.MessagingException;

import Engine.IParentLayer;
import Engine.IUseCaseStart;
import Exceptions.ExConnectionError;
import Infrastructure.IMAPBroker;
import de.sofeea.datastructure.User;

public class SyncClient implements IUseCaseStart, IParentLayer { 
	private static final String c_FoldernameTrash = "Papierkorb"; 
	private List<Message> mUnsycedMailList; 
	private IMAPBroker mIMAPBroker;
	private ISyncAppl mSubLayer; 
	private List<SyncItem> mSyncItemList = new ArrayList<SyncItem>();
	private User mUser;
	
	protected SyncClient( ){
		mIMAPBroker = new IMAPBroker(); 
	}
	@Override
	public void injectSublayer(Object pSubLayer) {
		mSubLayer = (ISyncAppl) pSubLayer;
	}
	@Override
	public void setUser(User pUser) {
		mUser = pUser;
	} 

	@Override
	public void run( ) {  
		try {
			mIMAPBroker.openConnection(mUser.getAuthentication());
			mUnsycedMailList = mIMAPBroker.getMailsFromFolder(c_FoldernameTrash);
			buildSyncItems();  

			if(mSyncItemList.isEmpty() == false){
				mSubLayer.syncItems(mUser.getUserID(), mSyncItemList);  
				setDeleteFlagForSyncedItems();
			}
			mIMAPBroker.closeConnection();
		} catch (ExConnectionError e) { 
			e.printStackTrace();
		} 
	} 
	
	private void buildSyncItems() { 
		for(Message iMessage : mUnsycedMailList){ 
			try {  
				String lSubject;
				String lSubstring;
				Integer lItemID;
				SyncItem lNewItem;
				
				lSubject = iMessage.getSubject();
				lSubstring = lSubject.substring(lSubject.indexOf("[")+1,lSubject.indexOf("]")); 
				lItemID = Integer.parseInt(lSubstring);  

				lNewItem =new SyncItem(lItemID,iMessage);
				lNewItem.setPropsFromFlags( );
				
				mSyncItemList.add(lNewItem); 
			} catch(NumberFormatException e){
				// keine Zahl in eckigen Klammern
			}catch (StringIndexOutOfBoundsException e) {
				//Keine eckigen Klammern im Betreff
			} catch (MessagingException e) { 
				e.printStackTrace();
			} 
		} 
	} 
	
	private void setDeleteFlagForSyncedItems() {
		for(SyncItem iItem: mSyncItemList){
			try {
				if(iItem.isSyncedSuccessfull())
					iItem.getMessage().setFlag(Flag.DELETED, true);
			} catch (MessagingException e) { }
		}
	} 
}
