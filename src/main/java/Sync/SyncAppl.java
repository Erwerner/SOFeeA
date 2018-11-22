package Sync;

import java.sql.SQLException;
import java.util.List;

import Engine.IParentLayer;

public class SyncAppl implements ISyncAppl, IParentLayer { 
	private ISyncDB mSubLayer;
	
	protected SyncAppl(){}
	
	@Override
	public void injectSublayer(Object pSubLayer) {
		mSubLayer = (ISyncDB) pSubLayer;
	}
	@Override
	public void syncItems(Integer pUserID, List<SyncItem> pSyncItemList) {
		try {
			mSubLayer.syncItems(pUserID, pSyncItemList);
			for(SyncItem iItem : pSyncItemList)iItem.setSyncedSuccessfull();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	} 
}
