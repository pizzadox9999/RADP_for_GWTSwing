package org.radp.application.impl;

import org.radp.application.WindowSizeClassManager;
import org.radp.application.WindowSizeClasses;

import de.exware.gwtswing.awt.GDimension;

public class MaterialWindowSizeClassManager implements WindowSizeClassManager {
	public static final int COMPACT = 576;
	public static final int MEDIUM = 768;
	public static final int EXPANED = 992;
	public static final int LARGE = 1200;
	public static final int EXTRA_LARGE = 1400;
	
	private GDimension lastWindowSize = null;
	private WindowSizeClasses lastWindowSizeClass = null;
	
	@Override
	public WindowSizeClasses determineWindowSizeClass(GDimension windowSize) {
		if(windowSize.equals(lastWindowSize)) {
			if(windowSize.width >= COMPACT && windowSize.width <= MEDIUM) {
				lastWindowSizeClass = WindowSizeClasses.COMPACT;
			} else if(windowSize.width >= MEDIUM && windowSize.width <= EXPANED)  {
				lastWindowSizeClass = WindowSizeClasses.MEDIUM;
			} else if(windowSize.width >= EXPANED && windowSize.width <= LARGE)  {
				lastWindowSizeClass = WindowSizeClasses.EXPANED;
			} else if(windowSize.width >= LARGE && windowSize.width <= EXTRA_LARGE)  {
				lastWindowSizeClass = WindowSizeClasses.LARGE;
			} else if(windowSize.width >= EXTRA_LARGE)  {
				lastWindowSizeClass = WindowSizeClasses.EXTRA_LARGE;
			}
			lastWindowSize = windowSize;
		}
		return lastWindowSizeClass;
	}

}
