package com.mert.collision.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mert.collision.Assets;
import com.mert.framework.AbstractGame;
import com.mert.framework.models.BasicActor;
import com.mert.framework.screens.AbstractMenuScreen;

public class MenuScreen extends AbstractMenuScreen {
	
	private Image txt_collision;
	private Image btn_play;
	private Image btn_exit;
	private Image btn_rate;
	private Preferences prefs;
	private TextureRegion btnRateRegion;
	private final static String appName = "";
	
	public MenuScreen(AbstractGame game) {
		super(game);
		prefs = Gdx.app.getPreferences("gamePrefs");
	}

	@Override
	public void setUp() {
		
		//Background
		setBackground(Assets.atlas_background);
		
		//Collision Text Settings
		TextureRegion txtRegion = getAtlas().findRegion(Assets.atlas_text_collision);
		txt_collision = new Image(txtRegion);
		txt_collision.setPosition((getWidth() - txtRegion.getRegionWidth()) / 2, getHeight()* 0.65f);
		txt_collision.getColor().a = 0;
		txt_collision.addAction(Actions.fadeIn(0.8f, Interpolation.exp10In));
		getStage().addActor(txt_collision);
		//
		
		//Play Button Settings
		TextureRegion btnPlayRegion = getAtlas().findRegion(Assets.atlas_button_play);
		final float regPW = btnPlayRegion.getRegionWidth();
		float regPH = btnPlayRegion.getRegionHeight();
		float desPX = (getWidth()- regPW) / 2;
		float desPY = getHeight() * 0.25f;
		btn_play = new Image(btnPlayRegion);
		btn_play.setPosition(desPX, desPY);
		btn_play.getColor().a = 0;
		btn_play.addAction(Actions.sequence(Actions.delay(0.5f), Actions.fadeIn(0.8f, Interpolation.exp10In)));
		
		btn_play.addListener(new ClickListener(){

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				txt_collision.addAction(Actions.fadeOut(0.3f, Interpolation.exp10Out));
				btn_play.addAction(Actions.moveTo(getWidth(), btn_play.getY(), 0.3f));
				btn_exit.addAction(Actions.moveTo(getWidth(), btn_exit.getY(), 0.3f));
				btn_rate.addAction(Actions.sequence(Actions.moveTo(getWidth(), btn_rate.getY(), 0.3f), 
													Actions.run(new Runnable() {
					
					@Override
					public void run() {
						getGame().setScreen(new GameScreen(getGame()));
					}
					
				})));
				
			}
			
		});
		getStage().addActor(btn_play);
		//
		
		//Exit Button Settings
		TextureRegion btnExitRegion = getAtlas().findRegion(Assets.atlas_button_exit);
		float regEW = btnExitRegion.getRegionWidth();
		float regEH = btnExitRegion.getRegionHeight();
		float desEX = getWidth() - regEW *1.2f;
		float desEY = getHeight() - regEH;
		btn_exit = new Image(btnExitRegion);
		btn_exit.setPosition(getWidth(), desEY);
		btn_exit.addAction(Actions.sequence(Actions.delay(1), Actions.moveTo(desEX, desEY, 0.6f)));
		
		btn_exit.addListener(new ClickListener(){

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				Gdx.app.exit();
				
			}
			
		});
		getStage().addActor(btn_exit);
		//
		
		//Rate Button Settings
		btnRateRegion = getAtlas().findRegion(Assets.atlas_button_rate);
		float regRW = btnRateRegion.getRegionWidth();
		float regRH = btnRateRegion.getRegionHeight();
		float desRX = getWidth() - regRW * 2.4f;
		float desRY = getHeight() - regRH;
		btn_rate = new Image(btnRateRegion);
		btn_rate.setPosition(getWidth(), desRY);
		btn_rate.addAction(Actions.sequence(Actions.delay(1), Actions.moveTo(desRX, desRY, 0.6f)));
		
		btn_rate.addListener(new ClickListener(){

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {	
				Gdx.net.openURI("https://play.google.com/store/apps/details?id=" + appName);
			}
			
		});
		getStage().addActor(btn_rate);
		//
		
	}

	@Override
	public void render() {
	}
	
}
