package org.radp.test;

import java.util.ArrayList;

public class SimpleEventListenerSupport {
	private ArrayList<SimpleEventListener> simpleEventListeners = new ArrayList<>();
	
	public void addSimpleEventListener(SimpleEventListener simpleEventListener) {
		simpleEventListeners.add(simpleEventListener);
	}
	
	public void fireSimpleEventListeners() {
		for(SimpleEventListener simpleEventListener : simpleEventListeners) {
			simpleEventListener.onEvent();
		}
	}
}
