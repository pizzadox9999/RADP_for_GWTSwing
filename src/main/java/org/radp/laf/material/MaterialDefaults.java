package org.radp.laf.material;

import de.exware.gwtswing.swing.GButton;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.plaf.ComponentUI;
import de.exware.gwtswing.swing.plaf.GUIDefaults;

public class MaterialDefaults extends GUIDefaults {
	
	@Override
	public ComponentUI getUI(GComponent component) {
		if(component instanceof GButton) {
			return MaterialButtonUI.createUI(component);
		}
		
		return null;
	}

}
