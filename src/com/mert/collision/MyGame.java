package com.mert.collision;

import com.mert.collision.screens.LoadingScreen;
import com.mert.framework.AbstractGame;

public class MyGame extends AbstractGame {
	
	Assets assets;
	
	@Override
	public void onCreate() {
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void setUpAssets() {
		assets = new Assets();
		setAssets(assets);
		assets.loadAll();
	}

	@Override
	public void dispose() {
		super.dispose();
		assets.dispose();
	}

}
