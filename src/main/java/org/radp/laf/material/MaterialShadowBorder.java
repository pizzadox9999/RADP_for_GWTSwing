package org.radp.laf.material;

import de.exware.gplatform.GPStyle;
import de.exware.gwtswing.awt.event.GMouseAdapter;
import de.exware.gwtswing.awt.event.GMouseEvent;
import de.exware.gwtswing.awt.event.GMouseListener;
import de.exware.gwtswing.swing.GComponent;

public class MaterialShadowBorder extends MaterialBorder {
	private static final String SHADOW = "0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24)";
	private static final String SHADOW_MOUSE_OVER = "0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22)";
	
	private GMouseListener mouseListener;
	
	public MaterialShadowBorder() {
		this(2);
	}
	
	public MaterialShadowBorder(int shadowIntensity) {
		this(shadowIntensity, 2, 1);
	}
	
	public MaterialShadowBorder(int shadowIntensity, int borderRadius, int borderThickness) {
		super(borderRadius, borderThickness);
	}
	
	@Override
	public void install(GComponent component) {
		super.install(component);
		
		GPStyle peerStyle = component.getPeer().getStyle();
		peerStyle.setProperty("boxShadow", SHADOW);
		peerStyle.setProperty("transition", "box-shadow 0.3s cubic-bezier(.25,.8,.25,1)");
		
		mouseListener = new GMouseAdapter() {
			@Override
			public void mouseEntered(GMouseEvent evt) {
				super.mouseEntered(evt);
				peerStyle.setProperty("boxShadow", SHADOW_MOUSE_OVER);
			}
			
			@Override
			public void mouseExited(GMouseEvent evt) {
				super.mouseExited(evt);
				peerStyle.setProperty("boxShadow", SHADOW);
			}
		};
		
		component.addMouseListener(mouseListener);
	}
	
	@Override
	public void uninstall(GComponent component) {
		super.uninstall(component);
		
		component.removeMouseListener(mouseListener);
		
		GPStyle peerStyle = component.getPeer().getStyle();
		peerStyle.clearProperty("boxShadow");
		peerStyle.clearProperty("transition");
	}

}
