package com.mert.framework;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mert.framework.interfaces.IActor;

public abstract class AbstractGameActor extends Actor implements IActor {

	private TextureRegion region;
	private Rectangle bounds;
	public AbstractGameActor(TextureRegion region, float x, float y, float width, float height, float scaleX, float scaleY) {
		this.region = region;
		setPosition(x, y);
		setBounds(x, y, width, height);
		setSize(width, height);
		setOrigin(width/2, height/2);
		setScale(scaleX,scaleY);
		bounds = new Rectangle(x, y, width, height);
		//
		setUp();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		update();
	}

	public Vector2 getPosition() {
		return new Vector2(getX(),getY());
	}

	public void setPosition(float x, float y){
		setX(x);
		setY(y);
	}

	public TextureRegion getRegion() {
		return this.region;
	}

	public void setRegion(TextureRegion region) {
		this.region = region;
	}

	public Rectangle getBounds() {
		return this.bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

}
