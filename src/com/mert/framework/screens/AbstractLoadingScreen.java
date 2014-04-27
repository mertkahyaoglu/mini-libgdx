package com.mert.framework.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mert.framework.AbstractGame;
import com.mert.framework.interfaces.IScreen;

public abstract class AbstractLoadingScreen extends AbstractScreen implements IScreen {
	
	private SpriteBatch batch;
	
	public AbstractLoadingScreen(AbstractGame game){
		super(game);
		batch = new SpriteBatch();
		//
		setUp();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		//
		render();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public float getLoadingProgress() {
		return getAssetManager().getProgress();
	}
	
	public float getLoadingTime(){
		return getStartTime();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}

}
