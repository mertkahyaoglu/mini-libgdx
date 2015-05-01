package com.mert.framework;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractTextActor extends Actor{

	private BitmapFont font;
	private Color color;
	private String str;

	public AbstractTextActor(BitmapFont font, Color color, String str, float posX, float posY, float width, float height, float scaleX, float scaleY){
		this.font = font;
		this.color = color;
		this.str = str;
		setBounds(posX, posY, width, height);
		setOrigin(width/2, height/2);
		setPosition(posX, posY);
		setSize(width, height);
		font.setScale(scaleX, scaleY);
		setScale(scaleX,scaleY);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		font.setColor(color);
		font.draw(batch, str, getX(), getY());
	}

	public BitmapFont getFont() {
		return this.font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
