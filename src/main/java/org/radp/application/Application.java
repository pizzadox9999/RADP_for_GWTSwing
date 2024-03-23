package org.radp.application;

import org.radp.RADP;
import org.radp.event.ResizeEvent;
import org.radp.event.ResizeEventListener;

import de.exware.gplatform.GPElement;
import de.exware.gplatform.GPlatform;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GToolkit;
import de.exware.gwtswing.swing.GPanel;
import de.exware.gwtswing.swing.GUtilities;

abstract public class Application extends GPanel {
	protected WindowSizeClasses windowSizeClass;
	protected WindowSizeClassManager windowSizeClassManager;
	
	protected View view;
	protected ViewManager viewManager;
	
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
				updateView();
				
				setSize(applicationSize);
				revalidate();
			}
		};
		
		RADP.addResizeEventListener(resizeEventListener);
	}
	
	protected void updateWindowSizeClass() {
		windowSizeClass = windowSizeClassManager.determineWindowSizeClass(applicationSize);
	}
	
	protected void updateView() {
		view = viewManager.determineView(windowSizeClass);
		view.update(applicationSize);
	}
	
	public void start() {
		start(GPlatform.getDoc().getBody());
	}
	
	/**
	 * if this method is called everything is setup
	 */
	public void start(GPElement element) {
		updateWindowSizeClass();
		updateView();
		
		
		setSize(applicationSize);
		//attach chat application to parent
		GUtilities.addToWidget(element, this);
		validate();
	}
	
	public void shutdown() {
		getPeer().removeFromParent();
	}
}
