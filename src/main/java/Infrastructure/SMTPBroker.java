package Infrastructure;
 
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import de.sofeea.datastructure.AuthenticationData;  
 
public class SMTPBroker {
	private static final String cSMTPHost = "smtp.web.de";
	private static final Integer cSMTPPort = 587;
	 
    private Properties mSMTPProperties; 
    private Session mSession;
	private String mLogin;
	private String mPW;
	
	public void openConnection( AuthenticationData pAuthentication){ 
		mLogin = pAuthentication.getLogin();
		mPW = pAuthentication.getPassword();
		initProperties(); 
		openSession();
	}

	public void closeConnection(){
		closeSession();
	}
	
	private void initProperties() {
		mSMTPProperties = new Properties();
	    mSMTPProperties.put("mail.smtp.host", cSMTPHost); 
	    mSMTPProperties.put("mail.smtp.auth", "true"); 
	    mSMTPProperties.put("mail.transport.protocol", "smtps"); 
	    mSMTPProperties.put("mail.smtps.port", cSMTPPort);
	    mSMTPProperties.put("mail.smtp.starttls.enable", "true");
	}

	private void openSession() {
		SMTPMailAuthenticator lSMTPAuthenticator; 
		lSMTPAuthenticator = new SMTPMailAuthenticator(mLogin,mPW); 
        mSession = Session.getInstance(mSMTPProperties, lSMTPAuthenticator);
	}

    private void closeSession() { }
    
    class SMTPMailAuthenticator extends Authenticator { 
        private String user; 
        private String password;

        public SMTPMailAuthenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.user, this.password);
        }
    }

	public Message createMessage() { 
		return new MimeMessage(mSession);
	}

	public void sendMail(Message pMessage) throws MessagingException {
		Transport.send(pMessage);
	}
}
