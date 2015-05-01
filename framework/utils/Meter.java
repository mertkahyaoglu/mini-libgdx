package com.mert.framework.utils;

public class Meter {

	public static final float METERS_PER_PIXEL = (float) LengthConversions.SI_PIXEL;

    public static float toPixel(float meter) {
        return (float)LengthConversions.SI2Pixelf(meter);
    }
	
}
