package org.radp.test;

import static org.radp.laf.material.MaterialWindowSizeClasses.COMPACT;
import static org.radp.laf.material.MaterialWindowSizeClasses.EXPANED;
import static org.radp.laf.material.MaterialWindowSizeClasses.EXTRA_LARGE;
import static org.radp.laf.material.MaterialWindowSizeClasses.LARGE;
import static org.radp.laf.material.MaterialWindowSizeClasses.MEDIUM;

import org.radp.application.Application;
import org.radp.laf.material.MaterialWindowSizeClassManager;

import de.exware.gwtswing.swing.GComponent;

public class ChatApplication extends Application {
	private GComponent compactLayout;

	public ChatApplication() {
		windowSizeClassManager = new MaterialWindowSizeClassManager();
		compactLayout = new CompactChatLayout();
	}

	@Override
	protected void changeLayout() {
		if (windowSizeClass.equals(COMPACT)) {
			layout = compactLayout;
		} else if (windowSizeClass.equals(MEDIUM)) {

		} else if (windowSizeClass.equals(EXPANED)) {

		} else if (windowSizeClass.equals(LARGE)) {

		} else if (windowSizeClass.equals(EXTRA_LARGE)) {

		}
	}
}
