package Subscribe;

import Engine.AbstractLayerFactory;
import Engine.IParentLayer;

public class SubscriptionFactory extends AbstractLayerFactory {

	@Override
	protected IParentLayer createClientLayer() { 
		return new SubscribeClient();
	}

	@Override
	protected IParentLayer createBusinessLayer() { 
		return new SubscribeAppl();
	}

	@Override
	protected Object createDataAccessLayer() { 
		return new SubscribeDB();
	}
}
