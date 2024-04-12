package org.radp.test;

import org.radp.application.View;
import org.radp.layout.VerticalFlowLayout;

import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GLabel;
import de.exware.gwtswing.swing.GPanel;

public class LargeMaterialView implements View {
	private GComponent component;

	@Override
	public void init() {
		component = new GPanel(new VerticalFlowLayout());
		component.add(new GLabel("large material view"));
		
	}

	@Override
	public void activated() {

	}

	@Override
	public void deactived() {

	}

	@Override
	public void resized(GDimension windowSize) {

	}

	@Override
	public GComponent getComponent() {
		return component;
	}

}
