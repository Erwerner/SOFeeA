package RSS;

import java.net.MalformedURLException;

import de.sofeea.datastructure.FeedSource;

public class RSSSource extends FeedSource {
	public RSSSource(int pID, String pURL) throws MalformedURLException {
		super(pID, pURL); 
	}

	private String mXMLContent;
	public void setXMLContent(String pXMLContent) {
		mXMLContent = pXMLContent;
	}

	public String getXMLContent() { 
		return mXMLContent;
	}

}
