package org.radp.test;

import de.exware.gwtswing.awt.GBorderLayout;
import de.exware.gwtswing.swing.GComponent;

class CompactChatSelectionArea extends BaseChatSelectionArea {

}

class CompactNavigationArea extends BaseNavigationArea {
	private SimpleEventListenerSupport backButtonEventListeners = new SimpleEventListenerSupport();

	public CompactNavigationArea() {

	}

	protected void fireSimpleEventListeners() {
		backButtonEventListeners.fireSimpleEventListeners();
	}

	public void addBackButtonEventListener(SimpleEventListener simpleEventListener) {
		backButtonEventListeners.addSimpleEventListener(simpleEventListener);
	}
}

class CompactMessageArea extends BaseMessageArea {

}

class CompactInputArea extends BaseInputArea {

}

class CompactChatArea extends BaseChatArea {
	private SimpleEventListenerSupport leaveChatAreaEventListeners = new SimpleEventListenerSupport();

	public CompactChatArea() {
		super(new CompactNavigationArea(), new CompactMessageArea(), new CompactInputArea());

	}

	public void addLeaveChatAreaEventListener(SimpleEventListener leaveChatAreaEventListener) {
		leaveChatAreaEventListeners.addSimpleEventListener(leaveChatAreaEventListener);
	}
}

public class CompactChatLayout extends GComponent {
	private CompactChatSelectionArea compactChatSelectionArea;
	private CompactChatArea compactChatArea;

	public CompactChatLayout() {
		setLayout(new GBorderLayout());

		compactChatSelectionArea = new CompactChatSelectionArea();
		compactChatArea = new CompactChatArea();

		compactChatSelectionArea.addChatSelectedEventListener(new SimpleEventListener() {
			@Override
			public void onEvent() {
				removeAll();
				// TODO: load the chat to display
				
				add(compactChatArea, GBorderLayout.CENTER);
			}
		});

		compactChatArea.addLeaveChatAreaEventListener(new SimpleEventListener() {
			@Override
			public void onEvent() {
				removeAll();
				add(compactChatSelectionArea, GBorderLayout.CENTER);
			}
		});

		add(compactChatSelectionArea, GBorderLayout.CENTER);
	}
	
}
