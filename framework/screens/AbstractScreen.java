package com.mert.framework.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mert.framework.AbstractGame;
import com.mert.framework.utils.Utils;

public abstract class AbstractScreen implements Screen {
	
	private float startTime;
	private float width, height, screenRatio;
	private Stage stage;
	private AbstractGame game;
	
	public AbstractScreen(AbstractGame game){
		super();
		this.game = game;
		startTime = 0;
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		screenRatio = height / width;
		
		stage = new Stage(width, height, false);
		
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		startTime += delta;
	
		stage.act(delta);
		stage.draw();
	}
	
	public float getStartTime() {
		return startTime;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public AbstractGame getGame() {
		return game;
	}

	public void setGame(AbstractGame game) {
		this.game = game;
	}
	
	public AssetManager getAssetManager(){
		return game.getAssets().getAssetManager();
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, false);
		Utils.log("Resizing", "");
	}

	@Override
	public void show() {
		Utils.log("Showed", "");
	}

	@Override
	public void hide() {
		Utils.log("Hid", "");
	}

	@Override
	public void pause() {
		Utils.log("Paused", "");
	}

	@Override
	public void resume() {
		Utils.log("Resumed", "");
	}

	@Override
	public void dispose() {
		Utils.log("Disposed", "");
	}

	public float getScreenRatio() {
		return screenRatio;
	}

	public void setScreenRatio(float screenRatio) {
		this.screenRatio = screenRatio;
	}

}
