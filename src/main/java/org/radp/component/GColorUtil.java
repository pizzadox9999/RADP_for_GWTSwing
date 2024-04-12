package org.radp.component;

import java.util.Random;

import de.exware.gwtswing.awt.GColor;

public final class GColorUtil {
	private static Random random = new Random(); 
	private GColorUtil() {
		
	}
	
	public static GColor nextRandomColor() {
		return new GColor(random.nextInt(255),
						  random.nextInt(255),
						  random.nextInt(255));
	}
}
