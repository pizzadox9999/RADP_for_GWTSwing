package org.radp.laf.material;

import de.exware.gwtswing.awt.GToolkit;
import de.exware.gwtswing.awt.event.GAWTEvent;
import de.exware.gwtswing.awt.event.GAWTEventListener;
import de.exware.gwtswing.awt.event.GContainerEvent;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GLookAndFeel;
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
	
	public void setSkin() {
		
	}

	@Override
	public void initialize() {
		//because of the lightweight laf implementation a general solution that every
    	//component gets his laf is to add an component listener
		//this will update the ui every time a component is added to a container
		//of the component added to the container
    	installLookAndFeelEventListener = new GAWTEventListener() {
			@Override
			public void eventDispatched(GAWTEvent event) {
				System.out.println("container event fired");
				//GComponent component = (GComponent) event.getSource();
				if(event instanceof GContainerEvent) {
					GComponent component = ((GContainerEvent) event).getChild();
					component.updateUI();
				}
				
			}
		};
		
		GToolkit.getDefaultToolkit().addAWTEventListener(installLookAndFeelEventListener, GAWTEvent.CONTAINER_EVENT_MASK);
		System.out.println("registered container event listener");
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
