package org.radp.laf.material;

import de.exware.gplatform.GPStyle;
import de.exware.gwtswing.awt.GInsets;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.border.GBorder;

public class MaterialBorder implements GBorder {

	protected int borderRadius;
	protected int borderThickness;
	
	protected GInsets insets;
	
	public MaterialBorder() {
		this(2, 1);
	}
	
	public MaterialBorder(int borderRadius, int borderThickness) {
		this.borderRadius = borderRadius;
		this.borderThickness = borderThickness;
		
		addInsetsToBorder(borderThickness, borderThickness, borderThickness, borderThickness);
	}
	
	@Override
	public void install(GComponent component) {
		if(component == null) {
			return;
		}
		
		GPStyle peerStyle = component.getPeer().getStyle();
		peerStyle.setBorderColor("black");
		peerStyle.setBorderStyle("line");
		peerStyle.setBorderWidth(borderThickness);
		peerStyle.setProperty("borderRadius", borderRadius + "px");
	}
	
	protected void addInsetsToBorder(GInsets insets) {
		addInsetsToBorder(insets.top, insets.left, insets.bottom, insets.right);
	}
	
	protected void addInsetsToBorder(int top, int left, int bottom, int right) {
		if(insets == null) {
			insets = new GInsets(top, left, bottom, right);
		} else {
			insets = new GInsets(insets.top + top,
								 insets.left + left,
								 insets.bottom + bottom,
								 insets.right + right);
		}
	}

	@Override
	public GInsets getBorderInsets(GComponent c) {
		return insets;
	}

	@Override
	public void uninstall(GComponent component) {
		GPStyle peerStyle = component.getPeer().getStyle();
		peerStyle.clearProperty("borderColor");
		peerStyle.clearProperty("borderStyle");
		peerStyle.clearProperty("borderwidth");
		peerStyle.clearProperty("borderRadius");		
	}

}
