package org.radp.test;

import de.exware.gwtswing.awt.GBorderLayout;
import de.exware.gwtswing.swing.GComponent;

/**
 * The base chat area provides an layout for an input, message and navigation
 * area.
 */
public class BaseChatArea extends GComponent {
	protected BaseNavigationArea baseNavigationArea;
	protected BaseMessageArea baseMessageArea;
	protected BaseInputArea baseInputArea;

	public BaseChatArea(BaseNavigationArea baseNavigationArea, BaseMessageArea baseMessageArea, BaseInputArea baseInputArea) {
		setLayout(new GBorderLayout());
		
		this.baseNavigationArea = baseNavigationArea;
		this.baseMessageArea = baseMessageArea;
		this.baseInputArea = baseInputArea;

		add(baseNavigationArea, GBorderLayout.NORTH);
		add(baseMessageArea, GBorderLayout.CENTER);
		add(baseInputArea, GBorderLayout.SOUTH);
	}
}
