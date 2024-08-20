package org.radp.laf.material;

import de.exware.gwtswing.awt.GToolkit;
import de.exware.gwtswing.awt.event.GAWTEvent;
import de.exware.gwtswing.awt.event.GAWTEventListener;
import de.exware.gwtswing.awt.event.GContainerEvent;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GLookAndFeel;
import de.exware.gwtswing.swing.GUIManager;
import de.exware.gwtswing.swing.plaf.GUIDefaults;

public class MaterialLookAndFeel extends GLookAndFeel {
	private GUIDefaults uiDefaults;
	private GAWTEventListener installLookAndFeelEventListener;
	
	public MaterialLookAndFeel() {
		uiDefaults = new MaterialDefaults();
	}

	@Override
	public GUIDefaults getDefaults() {
		return uiDefaults;
	}
	
	/**
	 * Sets skin values on to the MaterialUIDefaults.
	 * @param materialSkin
	 */
	public void setSkin(MaterialSkin materialSkin) {
		materialSkin.applySkin();
	}

	@Override
	public void initialize() {
		//because of the lightweight laf implementation a general solution that every
    	//component gets his laf is to add an component listener
		//this will update the ui every time a component is added to a container
		//of the component added to the container
		//but this implementation is insufficient for Material3
		//there is need for an update every time a UIDefault has
		//changed
    	installLookAndFeelEventListener = new GAWTEventListener() {
			@Override
			public void eventDispatched(GAWTEvent event) {
				if(event instanceof GContainerEvent) {
					GComponent component = ((GContainerEvent) event).getChild();
					component.updateUI();
				}
				
			}
		};
		
		GToolkit.getDefaultToolkit().addAWTEventListener(installLookAndFeelEventListener, GAWTEvent.CONTAINER_EVENT_MASK);
	}

	@Override
	public void uninitialize() {
		GToolkit.getDefaultToolkit().removeAWTEventListener(installLookAndFeelEventListener);
	}

	@Override
	public String getName() {
		return "Material LAF";
	}
}
