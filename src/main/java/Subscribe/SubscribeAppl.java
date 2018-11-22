package Subscribe;

import java.sql.SQLException;
import java.util.List;

import Engine.IParentLayer;
import de.sofeea.datastructure.FeedSource;

public class SubscribeAppl implements IParentLayer, ISubscribeAppl {
	ISubscribeDB mSubLayer;
	@Override
	public void injectSublayer(Object pSubLayer) {
		mSubLayer = (ISubscribeDB) pSubLayer;
	}
	@Override
	public void updateAllSubscription(Integer pUserID, List<FeedSource> pSubscriptionList) throws SQLException {
		mSubLayer.updateAllSubscription(pUserID, pSubscriptionList);
	}
}
