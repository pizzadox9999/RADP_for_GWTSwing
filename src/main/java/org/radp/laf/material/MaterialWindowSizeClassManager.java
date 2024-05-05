package org.radp.laf.material;

import org.radp.application.WindowSizeClass;
import org.radp.application.WindowSizeClassManager;

import de.exware.gwtswing.awt.GDimension;

public class MaterialWindowSizeClassManager implements WindowSizeClassManager {
	public static final int COMPACT = 600;
	public static final int MEDIUM = 840;
	public static final int EXPANED = 1200;
	public static final int LARGE = 1600;
	
	@Override
	public WindowSizeClass determineWindowSizeClass(GDimension windowSize) {
		WindowSizeClass windowSizeClass = null;
		
		int width = windowSize.width;
		
		if(width < COMPACT) {
			windowSizeClass = MaterialWindowSizeClasses.COMPACT;
		} else if(width >= COMPACT && width < MEDIUM) {
			windowSizeClass = MaterialWindowSizeClasses.MEDIUM;
		} else if(width >= MEDIUM && width < EXPANED)  {
			windowSizeClass = MaterialWindowSizeClasses.EXPANED;
		} else if(width >= EXPANED && width < LARGE)  {
			windowSizeClass = MaterialWindowSizeClasses.LARGE;
		} else if(width >= LARGE)  {
			windowSizeClass = MaterialWindowSizeClasses.EXTRA_LARGE;
		}
		
		return windowSizeClass;
	}

}
