package com.mert.collision.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mert.collision.Assets;
import com.mert.framework.AbstractGame;
import com.mert.framework.screens.AbstractLoadingScreen;

public class LoadingScreen extends AbstractLoadingScreen{
	
	Image iconImage;
	Texture iconTexture;
	
	public LoadingScreen(AbstractGame game) {
		super(game);
	}
	
	@Override
	public void setUp() {

		iconTexture = new Texture(Assets.loading_icon);
		iconTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		iconImage = new Image(iconTexture);
		iconImage.setPosition((getWidth() - iconImage.getWidth()) / 2, (getHeight() - iconImage.getHeight()) / 2);
		iconImage.getColor().a = 0;
		iconImage.addAction(Actions.fadeIn(2.5f));
		getStage().addActor(iconImage);

	}

	@Override
	public void render() {		
		if(getAssetManager().update() && getStartTime() > 4f) {
			getGame().setScreen(new MenuScreen(getGame()));
		}	
	}

	@Override
	public void dispose() {
		super.dispose();
		iconTexture.dispose();
	}

}
