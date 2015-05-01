package com.mert.framework;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractBasicActor extends Actor {

	private TextureRegion textureRegion = null;
	private Texture texture = null;
	public AbstractBasicActor(TextureRegion textureRegion, float posX, float posY,float width, float height){
		super();
		this.textureRegion = textureRegion;
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
		setOrigin(width/2, height/2);
	}

	public AbstractBasicActor(Texture texture, float posX, float posY, float width, float height){
		super();
		this.texture = texture;
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
		setOrigin(width/2, height/2);
	}



	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		// For fade out/in effect
		batch.setColor(this.getColor().r, this.getColor().g, this.getColor().b,
				parentAlpha);
		if(textureRegion != null)
			batch.draw(textureRegion, getX(), getY(),
				getOriginX(), getOriginY(),  getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		if(texture != null)
			batch.draw(texture, getX(), getY(), getHeight(), getHeight());
	}

	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

	public void setTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}

	public Texture getTexture() {
		return this.texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

}
