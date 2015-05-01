package com.mert.collision.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mert.collision.Assets;
import com.mert.framework.AbstractGame;
import com.mert.framework.models.TextActor;
import com.mert.framework.screens.AbstractGameOverScreen;

public class GameoverScreen extends AbstractGameOverScreen{

	private Image txt_gameover;
	private Image btn_play;
	private Image btn_exit;
	private Image table_score;
	private TextActor score_game;
	private TextActor score_best;
	private Preferences prefs;

	public GameoverScreen(AbstractGame game) {
		super(game);
	}

	@Override
	public void setUp() {
		setBackground(Assets.atlas_background);
		prefs = Gdx.app.getPreferences("gamePrefs");

		//Play Button Settings
		TextureRegion btnPlayRegion = getAtlas().findRegion(Assets.atlas_button_play);
		final float regPW = btnPlayRegion.getRegionWidth();
		float regPH = btnPlayRegion.getRegionHeight();
		float desPX = (getWidth()- regPW) / 2;
		float desPY = (getHeight()- regPH) / 2;
		btn_play = new Image(btnPlayRegion);
		btn_play.setPosition(desPX, desPY);
		btn_play.getColor().a = 0;
		btn_play.setTouchable(Touchable.disabled);
		btn_play.addListener(new ClickListener(){

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				table_score.addAction(Actions.moveTo(table_score.getX(), getHeight(), 0.3f));
				score_game.addAction(Actions.moveTo(score_game.getX(), getHeight() + table_score.getHeight(), 0.3f));
				score_best.addAction(Actions.moveTo(score_game.getX(), getHeight() + table_score.getHeight(), 0.3f));
				btn_play.addAction(Actions.moveTo(getWidth(), btn_play.getY(), 0.3f));
				btn_exit.addAction(Actions.sequence(Actions.moveTo(getWidth(), btn_exit.getY(), 0.3f),
						Actions.run(new Runnable() {

					@Override
					public void run() {
						getGame().setScreen(new GameScreen(getGame()));
					}

				})));

			}

		});
		getStage().addActor(btn_play);

		//Exit Button Settings
		TextureRegion btnExitRegion = getAtlas().findRegion(Assets.atlas_button_exit);
		final float regEW = btnExitRegion.getRegionWidth();
		float regEH = btnExitRegion.getRegionHeight();
		float desEX = getWidth() - regEW;
		float desEY = getHeight() - regEH;
		btn_exit = new Image(btnExitRegion);
		btn_exit.setPosition(getWidth(), desEY);
		btn_exit.setTouchable(Touchable.disabled);
		btn_exit.addListener(new ClickListener(){

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				Gdx.app.exit();

			}

		});
		getStage().addActor(btn_exit);

		//Score Table
		TextureRegion tblScoreRegion = getAtlas().findRegion(Assets.atlas_score_table);
		table_score = new Image(tblScoreRegion);
		table_score.setPosition(0 , getHeight());
		getStage().addActor(table_score);
		//

		score_game = new TextActor(getAssetManager().get(Assets.font, BitmapFont.class), Color.CYAN, Integer.toString(prefs.getInteger("score", 0)) , table_score.getX() + table_score.getWidth() / 2 - 20f, getHeight() + table_score.getHeight() * 0.75f,
				48f, 48f, 1f, 1f);
		getStage().addActor(score_game);

		score_best = new TextActor(getAssetManager().get(Assets.font, BitmapFont.class), Color.RED, Integer.toString(prefs.getInteger("best", 0)) , table_score.getX() + table_score.getWidth() / 2 - 20f, getHeight() + table_score.getHeight() * 0.25f,
				48f, 48f, 1f, 1f);
		getStage().addActor(score_best);


		//Game Over Text
		TextureRegion txtRegion = getAtlas().findRegion(Assets.atlas_text_game_over);
		txt_gameover = new Image(txtRegion);
		txt_gameover.setPosition((getWidth() - txtRegion.getRegionWidth()) / 2, (getHeight() - txt_gameover.getHeight()) * 0.5f);
		txt_gameover.getColor().a = 0;
		txt_gameover.addAction(Actions.sequence(Actions.fadeIn(0.8f, Interpolation.exp10In), Actions.delay(2f),
				Actions.fadeOut(0.5f), Actions.run(new Runnable() {

					@Override
					public void run() {
						btn_play.addAction(Actions.fadeIn(0.5f));
						btn_exit.addAction(Actions.moveBy(-regEW, 0, 0.6f));
						table_score.addAction(Actions.moveBy(0, -table_score.getHeight(), 0.6f));
						score_game.addAction(Actions.moveBy(0, -table_score.getHeight(), 0.6f));
						score_best.addAction(Actions.moveBy(0, -table_score.getHeight(), 0.6f));
						btn_play.setTouchable(Touchable.enabled);
						btn_exit.setTouchable(Touchable.enabled);
						txt_gameover.remove();
					}

				})));

		getStage().addActor(txt_gameover);	
	}

	@Override
	public void render() {
	}

}
