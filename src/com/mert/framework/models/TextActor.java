package com.mert.framework.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mert.framework.AbstractTextActor;

public class TextActor extends AbstractTextActor  {

	public TextActor(BitmapFont font, Color color, String str, float posX,
			float posY, float width, float height, float scaleX, float scaleY) {
		super(font, color, str, posX, posY, width, height, scaleX, scaleY);
	}
	
}
