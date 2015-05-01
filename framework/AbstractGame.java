package com.mert.framework;

import com.badlogic.gdx.Game;
import com.mert.framework.interfaces.IGame;

public abstract class AbstractGame extends Game implements IGame {

	private AbstractAssets assets;

	@Override
	public void create() {
		setUpAssets();
		onCreate();
	}


	public AbstractAssets getAssets() {
		return this.assets;
	}

	public void setAssets(AbstractAssets assets) {
		this.assets = assets;
	}

}
