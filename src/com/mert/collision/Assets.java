package com.mert.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mert.framework.AbstractAssets;
import com.mert.framework.interfaces.IAssets;

public class Assets extends AbstractAssets implements IAssets {
	
	//Paths
	public static final String atlas = "data/pack.atlas";
	public static final String loading_icon = "data/icon.png";
	public static final String loadingBall1 = "data/loading_1.png";
	public static final String loadingBall2 = "data/loading_2.png";
	public static final String loadingBall3 = "data/loading_3.png";
	public static final String loadingBall4 = "data/loading_4.png";
	public static final String loadingBall5 = "data/loading_5.png";
	
	//Atlas Names
	public static final String atlas_background = "background";
	public static final String atlas_ball_blue = "ball_blue";
	public static final String atlas_ball_red = "ball_red";
	
	public static final String atlas_text_collision = "txt_collision";
	public static final String atlas_text_game_over = "txt_game_over";
	public static final String atlas_text_start = "txt_start";
	
	public static final String atlas_button_exit = "btn_exit";
	public static final String atlas_button_play = "btn_play";
	public static final String atlas_button_rate = "btn_rate";
	
	public static final String atlas_start_indicator = "ind_start";
	public static final String atlas_score_table = "score_table";
	
	//Font
	public static String font = "data/ocr_white.fnt";
	
	public  void loadAll() {
		getAssetManager().load(atlas, TextureAtlas.class);
		getAssetManager().load(font, BitmapFont.class);
	}
	
	public void dispose() {
		getAssetManager().dispose();
	}
	
}
