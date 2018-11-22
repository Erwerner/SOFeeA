package RSS;

import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Infrastructure.DataMapper;

public class RSSDB implements IRSSDB {

	public List<RSSSource> getActiveSubscriptions() throws SQLException {
		List<RSSSource> rSourceList = new ArrayList<RSSSource>();
		ResultSet lResultset; 
		DataMapper lDB = new DataMapper( );

		lResultset = lDB.executeSelect("SELECT * FROM func_get_subscriptions();");
		while (lResultset.next())
		{ 
			try {
				RSSSource lNewSource;
				lNewSource = new RSSSource(lResultset.getInt(1),lResultset.getString(2));
				rSourceList.add(lNewSource);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} 
		lResultset.close(); 
		return rSourceList;
	}

	public void insertXMLcontent(RSSSource iSource) throws SQLException { 
		DataMapper lDataMapper; 
		Integer lSourceID;
		String lXMLContent;
		
		lSourceID = iSource.getID();
		lXMLContent = iSource.getXMLContent();
		
		lDataMapper = new DataMapper( ); 
		lDataMapper.executeSQL("SELECT func_insert_source_xml( "+ lSourceID +", '"+ lXMLContent +"' );");
		lDataMapper.closeConnection(); 
	}

}
