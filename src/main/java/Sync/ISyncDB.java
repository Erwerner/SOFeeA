package Sync;

import java.sql.SQLException;
import java.util.List;

public interface ISyncDB {
	public void syncItems( Integer pUserID, List<SyncItem> pSyncItemList) throws SQLException;
}
