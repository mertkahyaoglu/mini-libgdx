package com.mert.framework.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mert.collision.Assets;
import com.mert.collision.screens.MenuScreen;
import com.mert.framework.AbstractGame;
import com.mert.framework.interfaces.IScreen;
import com.mert.framework.models.BasicActor;

public abstract class AbstractGameScreen extends AbstractScreen implements IScreen {
	
	private TextureAtlas atlas;
	private BasicActor backgroundActor;
	private boolean gameStarted;
	private float gameStartTime;
	
	public AbstractGameScreen(AbstractGame game) {
		super(game);
		Gdx.input.setCatchBackKey(true);
		gameStartTime = 0;
		atlas = getAssetManager().get(Assets.atlas);
		
		setUp();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if(gameStarted) gameStartTime += delta; 
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			  getGame().setScreen(new MenuScreen(getGame()));
		}
		render();
	}
	
	public void setBackground(String path) {
		TextureRegion region = atlas.findRegion(path);
		backgroundActor = new BasicActor(region, 0, 0, getWidth(), getHeight());
		getStage().addActor(backgroundActor);
	}
	
	
	public TextureAtlas getAtlas() {
		return atlas;
	}

	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}

	public float getGameStartTime() {
		return gameStartTime;
	}

	public void setGameStartTime(float gameStartTime) {
		this.gameStartTime = gameStartTime;
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		atlas.dispose();
	}

}
