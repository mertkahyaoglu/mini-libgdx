package com.mert.framework.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mert.framework.AbstractBasicActor;

public class BasicActor extends AbstractBasicActor {

	public BasicActor(Texture texture, float posX, float posY, float width, float height) {
		super(texture, posX, posY, width, height);
	}
	
	public BasicActor(TextureRegion texture, float posX, float posY, float width, float height) {
		super(texture, posX, posY, width, height);
	}
}
