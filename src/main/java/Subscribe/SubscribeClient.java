package Subscribe;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

import Engine.IParentLayer;
import Engine.IUseCaseStart;
import Exceptions.ExConnectionError;
import Infrastructure.IMAPBroker;
import de.sofeea.datastructure.FeedSource;
import de.sofeea.datastructure.User;

public class SubscribeClient implements IParentLayer, IUseCaseStart {

	private User mUser;
	private ISubscribeAppl mSubLayer;
	private static final String cFolderNameSubscription = "Subscription";
	IMAPBroker mIMAPBroker;

	@Override
	public void setUser(User pUser) {
		mUser = pUser;
	}
	@Override
	public void injectSublayer(Object pSubLayer) {
		mSubLayer = (ISubscribeAppl) pSubLayer;
	}

	@Override
	public void run() {  
		List<Message> lMessageList; 
		List<FeedSource> lSourceList;

		mIMAPBroker = new IMAPBroker();
		try {
			mIMAPBroker.openConnection(mUser.getAuthentication());
			lMessageList = mIMAPBroker.getMailsFromFolder(cFolderNameSubscription);
			lSourceList = buildFeedSourceFromMail(lMessageList); 
			 
			mSubLayer.updateAllSubscription(mUser.getUserID(), lSourceList);
			mIMAPBroker.closeConnection();
		} catch (ExConnectionError e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<FeedSource> buildFeedSourceFromMail(List<Message> pMessageList) {
		List<FeedSource> rSourceList = new ArrayList<FeedSource>();
		
		for(Message iMessage : pMessageList){
			FeedSource lSource;
			Integer lSourceIDDummy = -1;
			String lURL;
			String lEmailText;

			try {
				lURL = iMessage.getSubject();
				lEmailText = iMessage.getContent().toString(); 
				
				lSource = new FeedSource(lSourceIDDummy, lURL);
				lSource.setGroup(lEmailText);
				rSourceList.add(lSource);
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return rSourceList;
	}
}
