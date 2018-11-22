package Push;

import java.sql.SQLException;
import java.util.List;

import Engine.IParentLayer;

public class PushAppl implements IPushAppl, IParentLayer { 
	private IPushDB mSubLayer;

	protected PushAppl(){}
	
	public void injectSublayer(Object pSubLayer) {
		mSubLayer = (IPushDB) pSubLayer;
	}
	public List<PushItem> getItemsForPush(Integer pUserID) throws SQLException { 
		mSubLayer.startPushViews( pUserID );
		return mSubLayer.getItemsForPush(pUserID);
	} 

	public void setItemsPushed(Integer pUserID,List<Integer> pItemIDList) 
				throws SQLException {
		if(pItemIDList.isEmpty() == false)
			mSubLayer.setItemsPushed(pUserID,pItemIDList);
	}
}
