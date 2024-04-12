package org.radp.test;

import java.util.HashMap;

import de.exware.gwtswing.swing.GComponent;

final public class ComponentStore extends HashMap<String, GComponent> {
	private ComponentStore() {}
	
	private static ComponentStore componentStore = null;
	
	public static ComponentStore getInstance() {
		if(componentStore == null) {
			componentStore = new ComponentStore();
		}
		
		return componentStore;
	}
}
