package Engine;

public abstract class AbstractLayerFactory {
	private IParentLayer mClientLayer;
	private IParentLayer mBusinessLayer;
	private Object 		 mDataAccessLayer;
	
	public void buildLayers(){
		mDataAccessLayer = createDataAccessLayer();
		mBusinessLayer   = createBusinessLayer();
		mClientLayer     = createClientLayer();
		
		injectSubLayers();
	}
	private void injectSubLayers(){
		mBusinessLayer.injectSublayer(mDataAccessLayer);
		mClientLayer.injectSublayer(mBusinessLayer);
	}
	public IUseCaseStart getUseCaseStart(){
		return (IUseCaseStart) mClientLayer;
	}
	protected abstract IParentLayer createClientLayer();
	protected abstract IParentLayer createBusinessLayer();
	protected abstract Object 		createDataAccessLayer();
}
