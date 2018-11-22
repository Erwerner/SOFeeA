package Infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import de.sofeea.datastructure.FeedSource; 

public class RSSBroker {
	private FeedSource mSource;
    String mXMLContent = ""; 

	public RSSBroker(FeedSource pSource){
		mSource = pSource;
	}

	public void readXMLcontent() throws IOException, StringIndexOutOfBoundsException {
		URL lURL;
		URLConnection lConnection;
        BufferedReader lBufferReader;
        String lBufferLine; 

        lURL = mSource.getURL();
		lConnection = lURL.openConnection();

	    lBufferReader = new BufferedReader(new InputStreamReader(lConnection.getInputStream(), "UTF-8"));
	    while ((lBufferLine=lBufferReader.readLine())!=null) { 
	    	mXMLContent+=lBufferLine ;   
	    }
	    //Bereinigung des XML-Inhalts für PostgreSQL
	    mXMLContent = mXMLContent.replace(";", "");
	    mXMLContent = mXMLContent.replace("&", "");
	    mXMLContent = mXMLContent.replace("'", ""); 
	}

	public String getXMLContent() { 
		return mXMLContent;
	}
}
