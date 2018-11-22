package Sync;

import Engine.AbstractLayerFactory;
import Engine.IParentLayer; 

public class SyncFactory extends AbstractLayerFactory {  
	protected IParentLayer createClientLayer() {  
		return new SyncClient( );
	}  
	protected IParentLayer createBusinessLayer() { 
		return new SyncAppl();
	}  
	protected Object createDataAccessLayer() { 
		return new SyncDB();
	} 
}
