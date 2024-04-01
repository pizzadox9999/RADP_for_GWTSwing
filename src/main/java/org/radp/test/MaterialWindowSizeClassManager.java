package org.radp.test;

import org.radp.application.WindowSizeClassManager;
import org.radp.application.WindowSizeClasses;

import de.exware.gwtswing.awt.GDimension;

public class MaterialWindowSizeClassManager implements WindowSizeClassManager {
	public static final int COMPACT = 600;
	public static final int MEDIUM = 840;
	public static final int EXPANED = 1200;
	public static final int LARGE = 1600;
	
	@Override
	public WindowSizeClasses determineWindowSizeClass(GDimension windowSize) {
		WindowSizeClasses windowSizeClass = null;
		
		int width = windowSize.width;
		
		if(width < COMPACT) {
			windowSizeClass = WindowSizeClasses.COMPACT;
		} else if(width >= COMPACT && width < MEDIUM) {
			windowSizeClass = WindowSizeClasses.MEDIUM;
		} else if(width >= MEDIUM && width < EXPANED)  {
			windowSizeClass = WindowSizeClasses.EXPANED;
		} else if(width >= EXPANED && width < LARGE)  {
			windowSizeClass = WindowSizeClasses.LARGE;
		} else if(width >= LARGE)  {
			windowSizeClass = WindowSizeClasses.EXTRA_LARGE;
		}
		
		return windowSizeClass;
	}

}
