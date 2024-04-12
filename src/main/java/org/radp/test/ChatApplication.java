package org.radp.test;

import org.radp.application.SinglePageApplication;

public class ChatApplication extends SinglePageApplication {
	public static final String CHAT_SELECTION = "CHAT_SELECTION";
	
	@Override
	public void setupManager() {
		windowSizeClassManager = new MaterialWindowSizeClassManager();
		viewManager = new MaterialViewManager();
	}

	@Override
	public void setupComponentStore() {
		ComponentStore store = ComponentStore.getInstance();
		
		ChatSelectionArea chatSelection = new ChatSelectionArea();
		
		store.put(CHAT_SELECTION, chatSelection);
	}

	
}
