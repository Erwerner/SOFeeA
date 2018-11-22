package Sync;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Infrastructure.DataMapper;

public class SyncDB implements ISyncDB {

	protected SyncDB(){}
	
	@Override
	public void syncItems(Integer pUserID, List<SyncItem> pSyncItemList) 
				throws SQLException {
		DataMapper lDataMapper;
		List<Integer> lSyncedItemIDList = new ArrayList<Integer>();
		List<Boolean> lProp1List = new ArrayList<Boolean>();
		List<Boolean> lProp2List = new ArrayList<Boolean>();

		lDataMapper = new DataMapper();
		try {
			for(SyncItem iItem : pSyncItemList){
				lSyncedItemIDList.add(iItem.getID());
				lProp1List.add(iItem.getPropRating());
				lProp2List.add(iItem.getPropReadContent()); 
			}

			lDataMapper.executeSQL(
					"SELECT func_set_items_synced(  "+pUserID+","
					+"ARRAY"+lSyncedItemIDList+","
					+"ARRAY"+lProp1List+","
					+"ARRAY"+lProp2List+");");
			lDataMapper.closeConnection();
		} catch (SQLException e) {
			lDataMapper.closeConnection();
			throw e;
		}
	}
}
