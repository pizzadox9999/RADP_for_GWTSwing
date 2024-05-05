package org.radp.application;

import org.radp.RADP;
import org.radp.event.ResizeEvent;
import org.radp.event.ResizeEventListener;
import org.radp.laf.material.MaterialLookAndFeel;

import de.exware.gplatform.timer.AbstractGPTimerTask;
import de.exware.gplatform.timer.GPTimer;
import de.exware.gwtswing.awt.GToolkit;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GUIManager;
import de.exware.gwtswing.swing.GUtilities;

/**
 * Simple base class for an GWTSwing application.
 */
abstract public class Application {
	protected WindowSizeClass windowSizeClass;
	protected WindowSizeClassManager windowSizeClassManager;

	protected GComponent layout;

	protected ResizeEventListener resizeEventListener;
	
	protected GPTimer timer = GPTimer.createInstance();
	
	Thread thread = null;
	
	Object lock = new Object();
	
	protected Application() {
		GUIManager.setLookAndFeel(new MaterialLookAndFeel());

		RADP.init();
		resizeEventListener = new ResizeEventListener() {
			@Override
			public void resized(ResizeEvent resizeEvent) {
				if(thread == null) {
					thread = new Thread(new Runnable() {
						@Override
						public void run() {
							RADP.sleep(3);
							updateWindowSizeClass();
							if (layout != null) {
								layout.setSize(resizeEvent.newDimension);
								layout.revalidate();
							}
						}
					});
					thread.start();
				} else {
					//terminate thread
					thread.interrupt();
				}
			}
		};

		RADP.addResizeEventListener(resizeEventListener);
	}

	protected void updateWindowSizeClass() {
		WindowSizeClass oldWindowSizeClass = windowSizeClass;
		WindowSizeClass newWindowSizeClass = windowSizeClassManager
				.determineWindowSizeClass(GToolkit.getDefaultToolkit().getScreenSize());
		if (oldWindowSizeClass != newWindowSizeClass) {
			windowSizeClass = newWindowSizeClass;
			if(layout != null) {
				layout.setVisible(false);
				layout.getPeer().removeFromParent();
			}
			changeLayout();
			if(layout != null) {
				GUtilities.addToBody(layout);
				layout.setVisible(true);
			}
		}
	}

	/**
	 * changeLayout is called, when the window size class has changed, to accommodate
	 * for the new window size class, so that the user can have a pleasant layout.
	 */
	abstract protected void changeLayout();

	/**
	 * if this method is called everything is setup
	 */
	public void start() {
		// do some safty checks
		if (windowSizeClassManager == null) {
			throw new Error("No WindowSizeClassManager is set! Exiting application.");
		}

		// application setup
		updateWindowSizeClass();

		// start application by updating view and validating it.
		if (layout != null) {
			layout.setSize(GToolkit.getDefaultToolkit().getScreenSize());
			layout.validate();
		}
	}
}
