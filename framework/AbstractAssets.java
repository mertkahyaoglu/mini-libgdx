package com.mert.framework;

import com.badlogic.gdx.assets.AssetManager;

public abstract class AbstractAssets {

	private AssetManager assetManager;

	public AbstractAssets() {
		assetManager = new AssetManager();
	}

	public AssetManager getAssetManager() {
		return this.assetManager;
	}

	public void setManager(AssetManager manager) {
		this.assetManager = manager;
	}

}
