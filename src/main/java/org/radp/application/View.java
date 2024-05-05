package org.radp.application;

import de.exware.gwtswing.swing.GComponent;

public class View extends GComponent{
	private boolean isActive = false;
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isActive() {
		return isActive;
	}
}
