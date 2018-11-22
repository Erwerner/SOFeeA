package Push;

import java.sql.SQLException;
import java.util.List;

public interface IPushDB { 
	List<PushItem> getItemsForPush(Integer pUserID) throws SQLException;
	void setItemsPushed(Integer pUserID, List<Integer> pItemIDList) throws SQLException;
	void startPushViews(Integer pUserID) throws SQLException; 
}
