package org.radp.laf.material;

import de.exware.gwtswing.swing.plaf.GUIDefaults;
import dynamiccolor.DynamicColor;
import dynamiccolor.DynamicScheme;

public class LightMaterialSkin extends BasicMaterialSkin {

	public LightMaterialSkin(GUIDefaults guiDefaults) {
		super(guiDefaults);
		
	}

	@Override
	public void applySkin() {
		super.applySkin(); //values from basic skin should be also used
		DynamicScheme scheme = new DynamicScheme(null, null, false, 0, null, null, null, null, null, null);
		
	}

}
