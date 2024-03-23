package org.radp.test;

import org.radp.application.SinglePageApplication;
import org.radp.application.impl.MaterialWindowSizeClassManager;

public class ChatApplication extends SinglePageApplication {
	public ChatApplication() {
		super();
		windowSizeClassManager = new MaterialWindowSizeClassManager();
		
		
	}
}
