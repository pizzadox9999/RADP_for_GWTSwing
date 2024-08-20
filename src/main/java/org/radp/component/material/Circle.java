package org.radp.component.material;

import de.exware.gwtswing.awt.GColor;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.border.GBorder;
import de.exware.gwtswing.swing.border.GBorderFactory;
import de.exware.gwtswing.swing.border.GEmptyBorder;

public class Circle extends GComponent {
	private RoundBorder border;
	private GBorder baseBorder;
	private int radius;
	
	public Circle(int radius) {
		baseBorder = GBorderFactory.createLineBorder(GColor.BLACK, 1);
		setRadius(radius);
	}
	
	public void setRadius(int radius) {
		if(border != null) {
			border.uninstall(this);
		}
		
		this.radius = radius;
		
		border = new RoundBorder(radius, baseBorder);
		
		border.install(this);
	}
	
	@Override
	public GDimension getPreferredSize() {
		return super.getPreferredSize();
	}
	
	@Override
	public GDimension getMinimumSize() {
		return super.getMinimumSize();
	}
	
	public int getRadius() {
		return radius;
	}
	
	public static GComponent createCircle(int radius) {
		Circle circle = new Circle(radius);
		circle.setSize(radius * 2, radius * 2);
		return circle;
	}
}
