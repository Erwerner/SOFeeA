package Push;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;

import Engine.IParentLayer;
import Engine.IUseCaseStart;
import Infrastructure.SMTPBroker;
import de.sofeea.datastructure.FeedSource;
import de.sofeea.datastructure.User;

public class PushClient implements IParentLayer, IUseCaseStart { 
	private IPushAppl mSubLayer;
	private User mUser; 
	SMTPBroker mSMTPBroker; 

	protected PushClient(){}
	
	public void injectSublayer(Object pSubLayer) {
		mSubLayer = (IPushAppl) pSubLayer;
	}
	public void setUser(User pUser) {
		mUser = pUser;
	} 

	public void run( ) {
		List<PushItem> lItemList; 
		List<Integer> lItemIDList = new ArrayList<Integer>();
		
		try {
			lItemList = mSubLayer.getItemsForPush(mUser.getUserID());

			mSMTPBroker = new SMTPBroker();
			mSMTPBroker.openConnection(mUser.getAuthentication());
			try { 
				for(PushItem iItem : lItemList){
					Message lMessage = createMail( iItem );  
					
					mSMTPBroker.sendMail(lMessage);
					lItemIDList.add(iItem.getID()); 
				}
			} catch(SendFailedException e){
				// Server überlastet (zu viele E-Mails) 
				System.out.println("Mail-Server überlastet.");
				System.out.println("Unerledigte E-Mails werden erneut gesendet.");
			} catch (MessagingException e ) {
				// Mail Error
				e.printStackTrace();
			}
			
			mSubLayer.setItemsPushed(mUser.getUserID(), lItemIDList);
			mSMTPBroker.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private Message createMail( PushItem pItem) throws MessagingException {  
		Message rMessage;		
		FeedSource lSource;
		String lLogin;
		String lSubject;
		String lText;
		InternetAddress lSenderAddress;
		InternetAddress[] lReceiverAddress; 
		
		rMessage = mSMTPBroker.createMessage();
			
		lLogin = mUser.getAuthentication().getLogin();

		lSenderAddress = new InternetAddress( lLogin );
		lReceiverAddress = InternetAddress.parse( lLogin ); 
		rMessage.setFrom(lSenderAddress); 
		rMessage.setRecipients(Message.RecipientType.TO, lReceiverAddress);
		rMessage.setSentDate(new Date( ));

		lSource = pItem.getSource();
		
		lSubject = new String(); 
		lSubject+= pItem.getTitle();
		lSubject+="; ";
		lSubject+="["+pItem.getID()+"]"; 
		lSubject+= " <" + lSource.getGroup()+ ">";
		rMessage.setSubject(lSubject);
		
		lText= new String();
		lText+=pItem.getDescription();
		lText+=";\n";
		lText+="\n";
		lText+=pItem.getLink();
		lText+=";\n";
		lText+="\n";
		lText+="Dieser Beitrag wurde aus folgendem Grund gesendet:\n";
		lText+=pItem.getPushViewDescription();
		lText+=";\n";
		lText+="\n";
		lText+= lSource.getTitle(); 
		lText+=";\n";
		lText+= lSource.getURL(); 
		lText+=";\n";
		lText+= lSource.getDescription(); 
		lText+=";\n";
		rMessage.setText(lText);
		
		return rMessage; 
	}
}
