package org.radp.application;

import org.radp.RADP;
import org.radp.event.ResizeEvent;
import org.radp.event.ResizeEventListener;
import org.radp.laf.material.MaterialLookAndFeel;

import de.exware.gplatform.GPElement;
import de.exware.gplatform.GPlatform;
import de.exware.gplatform.teavm.TeavmGPlatform;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GToolkit;
import de.exware.gwtswing.swing.GPanel;
import de.exware.gwtswing.swing.GUIManager;
import de.exware.gwtswing.swing.GUtilities;

abstract public class Application {
	protected WindowSizeClasses windowSizeClass;
	protected WindowSizeClassManager windowSizeClassManager;
	
	protected View view;
	protected ViewManager viewManager;
	
	protected ResizeEventListener resizeEventListener;
	
	protected GDimension applicationSize = null;
	
	protected Application() {
		GUIManager.setLookAndFeel(new MaterialLookAndFeel());
		applicationSize = GToolkit.getDefaultToolkit().getScreenSize();
		
		RADP.init();
		resizeEventListener = new ResizeEventListener() {
			@Override
			public void resized(ResizeEvent resizeEvent) {
				applicationSize = resizeEvent.newDimension;
				updateWindowSizeClass();
				updateView();
				
				view.getComponent().setSize(applicationSize);
				view.getComponent().revalidate();
				
				view.resized(applicationSize);
			}
		};
		
		RADP.addResizeEventListener(resizeEventListener);
	}
	
	protected void updateWindowSizeClass() {
		windowSizeClass = windowSizeClassManager.determineWindowSizeClass(applicationSize);
	}
	
	protected void updateView() {
		View oldView = view;
		View newView = viewManager.determineView(windowSizeClass);
		
		if(oldView != newView) {
			if(oldView != null) {
				oldView.deactived();
				oldView.getComponent().getPeer().removeFromParent();
			}
			
			if(newView != null) {
				newView.activated();
				GUtilities.addToWidget(GPlatform.getDoc().getBody(), newView.getComponent());
			}
			
			view = newView;
		}
	}
	
	/**
	 * if this method is called everything is setup
	 */
	public void start() {
		applicationSize = GToolkit.getDefaultToolkit().getScreenSize();
		updateWindowSizeClass();
		updateView();
		
		view.getComponent().setSize(applicationSize);
		view.getComponent().setVisible(true);
		view.getComponent().validate();
	}
}
