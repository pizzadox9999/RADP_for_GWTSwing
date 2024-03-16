package org.radp.application;

import org.radp.RADP;
import org.radp.event.ResizeEvent;
import org.radp.event.ResizeEventListener;

import de.exware.gplatform.GPElement;
import de.exware.gplatform.GPlatform;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GToolkit;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GFrame;
import de.exware.gwtswing.swing.GPanel;
import de.exware.gwtswing.swing.GUtilities;

abstract public class Application extends GPanel {
	protected WindowSizeClasses windowSizeClasse;
	protected WindowSizeClassManager windowSizeClassManager;
	
	protected Navbar navbar;
	protected NavbarManager navbarManager;
	
	protected ResizeEventListener resizeEventListener;
	
	protected GDimension applicationSize = null;
	
	protected Application() {
		applicationSize = GToolkit.getDefaultToolkit().getScreenSize();
		
		RADP.init();
		resizeEventListener = new ResizeEventListener() {
			@Override
			public void resized(ResizeEvent resizeEvent) {
				applicationSize = resizeEvent.newDimension;
				updateWindowSizeClass();
				updateNavbar();
				
				setSize(applicationSize);
				revalidate();
			}
		};
		
		RADP.addResizeEventListener(resizeEventListener);
	}
	
	protected void updateWindowSizeClass() {
		windowSizeClasse = windowSizeClassManager.determineWindowSizeClass(applicationSize);
	}
	
	protected void updateNavbar() {
		navbar = navbarManager.determineNavbar(windowSizeClasse);
	}
	
	public void start() {
		start(GPlatform.getDoc().getBody());
	}
	
	/**
	 * if this method is called everything is setup
	 */
	public void start(GPElement element) {
		updateWindowSizeClass();
		updateNavbar();
		
		
		setSize(applicationSize);
		//attach chat application to parent
		GUtilities.addToWidget(element, this);
		validate();
	}
	
	public void shutdown() {
		getPeer().removeFromParent();
	}
}
