package RSS;

import Engine.AbstractLayerFactory;
import Engine.IParentLayer;

public class RSSFactory extends AbstractLayerFactory {

	@Override
	protected IParentLayer createClientLayer() { 
		return new RSSClient();
	}

	@Override
	protected IParentLayer createBusinessLayer() { 
		return new RSSAppl();
	}

	@Override
	protected Object createDataAccessLayer() { 
		return new RSSDB();
	}

}
