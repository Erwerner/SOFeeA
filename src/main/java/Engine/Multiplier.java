package Engine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Push.PushFactory;
import RSS.RSSFactory;
import Subscribe.SubscriptionFactory;
import Sync.SyncFactory;
import de.sofeea.datastructure.User;

public class Multiplier {
	IMultiplierDB mDBLayer;
	public Multiplier(){
		mDBLayer = new MultiplierDB();
	}
	public Iterator<IUseCaseStart>  getAllUseCases() throws SQLException {
		ArrayList<IUseCaseStart> rRequestList = new ArrayList<IUseCaseStart>();
		List<User> lUserList;
		
		// anwenderunabhängig; eine Instanz pro Schleifendurchlauf
		for(AbstractLayerFactory iFactory : getSystemFactorys()){ 
			IUseCaseStart lUserRequest; 
			
			iFactory.buildLayers();
			lUserRequest = iFactory.getUseCaseStart(); 
			rRequestList.add(lUserRequest);
		}
		
		// anwenderabhängig; für jedes User-Objekt instanziieren
		lUserList = mDBLayer.getAllUserInstances();
		for(AbstractLayerFactory iFactory : getUserFactorys()){ 
			for(User iUser : lUserList){
				IUseCaseStart lUserRequest; 
				
				iFactory.buildLayers();
				lUserRequest = iFactory.getUseCaseStart();
				lUserRequest.setUser(iUser);
				rRequestList.add(lUserRequest);
			}
		}
		return rRequestList.iterator();
	}
	private List<AbstractLayerFactory> getSystemFactorys() {
		List<AbstractLayerFactory> rFactoryList;
		rFactoryList= new ArrayList<AbstractLayerFactory>();

		rFactoryList.add(new RSSFactory()); 
		return rFactoryList;
	}
	private List<AbstractLayerFactory> getUserFactorys() {
		List<AbstractLayerFactory> rFactoryList;
		rFactoryList= new ArrayList<AbstractLayerFactory>();

		rFactoryList.add(new SubscriptionFactory());
		rFactoryList.add(new SyncFactory());
		rFactoryList.add(new PushFactory());
		return rFactoryList;
	} 
}