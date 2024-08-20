package org.radp.component.material;

import org.radp.CSSUnit;

import de.exware.gplatform.GPStyle;
import de.exware.gwtswing.awt.GInsets;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.border.GBorder;

public class RoundBorder implements GBorder {
		
	private BorderCorner topLeft;
	private BorderCorner topRight;
	private BorderCorner bottomLeft;
	private BorderCorner bottomRight;
	private GBorder border;
	
	public RoundBorder(int radius, GBorder border) {
		this(radius, CSSUnit.PX, border);
	}
	
	public RoundBorder(int radius, CSSUnit unit, GBorder border) {
		this(new SimpleBorderCorner(radius, unit), new SimpleBorderCorner(radius, unit), new SimpleBorderCorner(radius, unit), new SimpleBorderCorner(radius, unit), border);
	}
	
	public RoundBorder(BorderCorner topLeft, BorderCorner topRight, BorderCorner bottomLeft, BorderCorner bottomRight, GBorder border) {
		if(border == null) {
			throw new RuntimeException("No border.");
		}
		
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
		this.border = border;
	}
	
	@Override
	public void install(GComponent component) {
		border.install(component);
		GPStyle style = component.getPeer().getStyle();
		style.setProperty("borderTopLeftRadius", topLeft.getValue());
		style.setProperty("borderTopRightRadius", topRight.getValue());
		style.setProperty("borderBottomLeftRadius", bottomLeft.getValue());
		style.setProperty("borderBottomRightRadius", bottomRight.getValue());
	}

	@Override
	public GInsets getBorderInsets(GComponent c) {
		return border.getBorderInsets(c);
	}

	@Override
	public void uninstall(GComponent component) {
		border.uninstall(component);
		GPStyle style = component.getPeer().getStyle();
		style.clearProperty("borderTopLeftRadius");
		style.clearProperty("borderTopRightRadius");
		style.clearProperty("borderBottomLeftRadius");
		style.clearProperty("borderBottomRightRadius");
	}
	
	public BorderCorner getTopLeftCorner() {
		return topLeft;
	}
	
	public BorderCorner getTopRightCorner() {
		return topRight;
	}
	
	public BorderCorner getBottomLeftCorner() {
		return bottomLeft;
	}
	
	public BorderCorner getBottomRightCorner() {
		return bottomRight;
	}
	
}
