package RSS;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import Engine.IParentLayer;
import Infrastructure.RSSBroker;

public class RSSAppl implements IParentLayer, IRSSAppl {
	IRSSDB mSubLayer;
	
	
	public void injectSublayer(Object pSubLayer) {
		mSubLayer = (IRSSDB) pSubLayer;
	}


	public void start() throws SQLException {
		Iterator<RSSSource> lSourceIterator;
	 
		lSourceIterator = mSubLayer.getActiveSubscriptions().iterator();
		while(lSourceIterator.hasNext()){ 
			RSSBroker lRSSBroker;  
			RSSSource lSource;
			
			lSource = lSourceIterator.next();
			
			lRSSBroker = new RSSBroker(lSource);
			try { 
				lRSSBroker.readXMLcontent( );
				lSource.setXMLContent(lRSSBroker.getXMLContent()); 
				mSubLayer.insertXMLcontent( lSource ); 
				lSourceIterator.remove();
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		} 

	}
 
}
