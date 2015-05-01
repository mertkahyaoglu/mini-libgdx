package com.mert.framework.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mert.collision.Assets;
import com.mert.framework.AbstractGame;
import com.mert.framework.interfaces.IScreen;
import com.mert.framework.models.BasicActor;

public abstract class AbstractMenuScreen extends AbstractScreen implements IScreen {

	private TextureAtlas atlas;
	private BasicActor backgroundActor;

	public AbstractMenuScreen(AbstractGame game) {
		super(game);
		Gdx.input.setCatchBackKey(false);
		atlas = getAssetManager().get(Assets.atlas);
		//
		setUp();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		//
		render();
	}

	public void setBackground(String path) {
		TextureRegion region = atlas.findRegion(path);
		backgroundActor = new BasicActor(region, 0, 0, getWidth(), getHeight());
		getStage().addActor(backgroundActor);
	}

	public TextureAtlas getAtlas() {
		return this.atlas;
	}

	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}

	@Override
	public void dispose() {
		super.dispose();
		atlas.dispose();
	}

}
