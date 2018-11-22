package Push;

import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Infrastructure.DataMapper;
import de.sofeea.datastructure.FeedSource;

public class PushDB implements IPushDB {

	protected PushDB(){}
	
	public void startPushViews(Integer pUserID) throws SQLException {
		DataMapper lDataMapper = new DataMapper();
		lDataMapper.executeSQL("SELECT func_create_push("+pUserID+");");

		lDataMapper.closeConnection();
	}
	public List<PushItem> getItemsForPush(Integer pUserID) throws SQLException {
		List<PushItem> rItemList = new ArrayList<PushItem>();
		DataMapper lDataMapper;
		ResultSet lResultSet;

		lDataMapper = new DataMapper();  
		lResultSet = lDataMapper.executeSelect(
					 "SELECT * FROM func_get_items_for_push("+pUserID+");");
		
		while(lResultSet.next()){ 
			Integer lItemID 	= lResultSet.getInt(2);
			Integer lSourceid 	= lResultSet.getInt(3);
			String lURL 		= lResultSet.getString(4);
			String lItemTitle	= lResultSet.getString(5);
			String lItemDescr	= lResultSet.getString(6);
			String lItemLink	= lResultSet.getString(7);
			String lViewDescr	= lResultSet.getString(8);
			String lSourceGroup	= lResultSet.getString(9);
			String lSourceDescr	= lResultSet.getString(10);
			String lSourceTitle	= lResultSet.getString(11);
			  
			try {
				FeedSource lFeedSource;
				PushItem lItem;
				
				lFeedSource = new FeedSource(lSourceid, lURL);
				lFeedSource.setGroup(lSourceGroup);
				lFeedSource.setDescription(lSourceDescr);
				lFeedSource.setTitle(lSourceTitle);
				lItem = new PushItem(lItemID, 
									 lFeedSource, 
									 lItemTitle, 
									 lItemDescr, 
									 lItemLink, 
									 lViewDescr);
				
				rItemList.add(lItem);
			} catch (MalformedURLException e) { 
				e.printStackTrace();
			}
		} 

		lDataMapper.closeConnection();
		return rItemList;
	}

	public void setItemsPushed(Integer pUserID, List<Integer> pItemIDList) 
				throws SQLException {
		DataMapper lDataMapper = new DataMapper();
		lDataMapper.executeSQL(
			"SELECT func_set_items_pushed("+pUserID+",ARRAY"+pItemIDList+");");
		lDataMapper.closeConnection();
	}
}
