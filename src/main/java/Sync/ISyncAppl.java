package Sync;

import java.util.List;

public interface ISyncAppl { 
	public void syncItems(Integer pUserID, List<SyncItem> pSyncItemList);
}
