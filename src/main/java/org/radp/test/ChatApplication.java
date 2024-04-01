package org.radp.test;

import org.radp.application.SinglePageApplication;

import de.exware.gwtswing.swing.GPanel;

class NameInformationPanel extends GPanel {
	public NameInformationPanel() {
		
	}
}

public class ChatApplication extends SinglePageApplication {
	public ChatApplication() {
		super();
		windowSizeClassManager = new MaterialWindowSizeClassManager();
		viewManager = new MaterialViewManager();
		
	}
}
