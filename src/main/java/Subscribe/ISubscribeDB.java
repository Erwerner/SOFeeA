package Subscribe;

import java.sql.SQLException;
import java.util.List;

import de.sofeea.datastructure.FeedSource; 

public interface ISubscribeDB {   
	public void updateAllSubscription(Integer pUserID, List<FeedSource> pSubscriptionList) throws SQLException; 
}
