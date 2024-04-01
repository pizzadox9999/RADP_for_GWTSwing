package org.radp.application;

import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.swing.GComponent;

public interface View {
	
	public void init();
	
	public void activated();
	
	public void deactived();
	
	public void resized(GDimension windowSize);

	public GComponent getComponent();
}
