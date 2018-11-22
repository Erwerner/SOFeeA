package Subscribe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Infrastructure.DataMapper;
import de.sofeea.datastructure.FeedSource;

public class SubscribeDB implements ISubscribeDB{

	@Override
	public void updateAllSubscription(Integer pUserID, List<FeedSource> pSubscriptionList) throws SQLException  {
		DataMapper lDataMapper;
		List<String> lURLList   = new ArrayList<String>();
		List<String> lGroupList = new ArrayList<String>();
		

		for(FeedSource iSource : pSubscriptionList){
			lURLList.add("'"+iSource.getURL().toString()+"'");
			lGroupList.add("'"+iSource.getGroup()+"'"); 
		}
		

		lDataMapper = new DataMapper();
		try {
			lDataMapper.executeSQL("SELECT func_insert_subscription("+pUserID+","
																   	+"ARRAY"+lURLList+","
																	+"ARRAY"+lGroupList+");");
			lDataMapper.closeConnection();
		} catch (SQLException e) {
			lDataMapper.closeConnection();
			throw e;
		} 
	}

}
