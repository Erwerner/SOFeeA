package RSS;

import java.sql.SQLException;

import Engine.IParentLayer;
import Engine.IUseCaseStart;
import de.sofeea.datastructure.User;

public class RSSClient implements IParentLayer, IUseCaseStart {  
	private IRSSAppl mSubLayer;

	public void setUser(User pUser) {  }

	public void run() {
		try {
			mSubLayer.start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void injectSublayer(Object pSubLayer) {
		mSubLayer = (IRSSAppl) pSubLayer;
	}

}
