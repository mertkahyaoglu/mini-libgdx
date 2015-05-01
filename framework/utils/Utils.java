package com.mert.framework.utils;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;

public class Utils {
	
	public static Random random = new Random();
	public static FPSLogger fps = new FPSLogger();
	public static void log(String tag, String message) {
		Gdx.app.log(tag, message);
	}
	
	public static void log(String tag, int message) {
		Gdx.app.log(tag, Integer.toString(message));
	}
	
	public static void log(String tag, float message) {
		Gdx.app.log(tag, Float.toString(message));
	}
	
	public static void log(String tag, double message) {
		Gdx.app.log(tag, Double.toString(message));
	}

	public static void log(String tag, boolean fingerOver) {
		Gdx.app.log(tag, Boolean.toString(fingerOver));
	}
	
	public static int randomInt(int from, int to) {
		return random.nextInt(to + 1) + from;
	}
	
	public static float randomFloat(float from, float to) {
		int fromInt = (int)from;
		int toInt = (int) to;
		
		return (float)(random.nextInt(toInt + 1) + fromInt);
	}
	
	public static int randomIntN(int fromNegative, int toPositive) {
		
		int from = fromNegative * -1;
		int minusPart = random.nextInt(from / 3) + from;
		int plusPart = random.nextInt(toPositive / 3) + toPositive;
		
		if(random.nextFloat() - 0.5f > 0)
			return plusPart;
		else
			return -minusPart;
		
	}
	
	public static void showFPS(){
		fps.log();
	}
}


