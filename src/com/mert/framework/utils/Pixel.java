package com.mert.framework.utils;

import com.badlogic.gdx.math.Vector2;

public class Pixel {

	public static float toMeter(float pixels) {
        return (float)LengthConversions.Pixel2SIf(pixels);
    }

    public static Vector2 toMeter(Vector2 vecPixel) {
        return new Vector2(Pixel.toMeter(vecPixel.x), Pixel.toMeter(vecPixel.y));
    }
	
}
