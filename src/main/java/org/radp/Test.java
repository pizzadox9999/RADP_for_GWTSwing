package org.radp;

import org.radp.application.SinglePageApplication;
import org.radp.application.impl.MaterialWindowSizeClassManager;
import org.radp.application.impl.navbar.MaterialNavbarManager;
import org.radp.laf.material.MaterialLookAndFeel;

import de.exware.gplatform.teavm.TeavmGPlatform;
import de.exware.gwtswing.swing.GUIManager;


class ChatApplication extends SinglePageApplication {
	public ChatApplication() {
		super();
		windowSizeClassManager = new MaterialWindowSizeClassManager();
		navbarManager = new MaterialNavbarManager();
	}
}

public class Test {
	public static void main(String[] args) {		
		TeavmGPlatform.init();
		GUIManager.setLookAndFeel(new MaterialLookAndFeel());
		
		ChatApplication chatApplication = new ChatApplication();
		chatApplication.start();
	}
}
