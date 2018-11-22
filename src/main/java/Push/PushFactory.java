package Push;

import Engine.AbstractLayerFactory;
import Engine.IParentLayer;

public class PushFactory extends AbstractLayerFactory {
	protected IParentLayer createClientLayer() { 
		return new PushClient( );
	}
	protected IParentLayer createBusinessLayer() { 
		return new PushAppl();
	}
	protected Object createDataAccessLayer() { 
		return new PushDB();
	}
}
