package org.radp.component;

import de.exware.gwtswing.awt.event.GMouseAdapter;
import de.exware.gwtswing.awt.event.GMouseEvent;
import de.exware.gwtswing.swing.GButton;

public class MaterialLinkButton extends GButton {
	public MaterialLinkButton(String linkText, String browserHistoryString) {
		super(linkText);
		addMouseListener(new GMouseAdapter() {
			@Override
			public void mouseClicked(GMouseEvent evt) {
				//manipulate browser
				
			}
		});
	}
}
