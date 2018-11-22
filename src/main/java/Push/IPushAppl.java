package Push;

import java.sql.SQLException;
import java.util.List;

public interface IPushAppl { 
	List<PushItem> getItemsForPush(Integer pUserID) throws SQLException;
	public void setItemsPushed(Integer pUserID, List<Integer> pItemIDList) throws SQLException;
}
