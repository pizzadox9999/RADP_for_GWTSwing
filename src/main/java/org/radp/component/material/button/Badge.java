package org.radp.component.material.button;

import de.exware.gwtswing.awt.GColor;

public class Badge {
	protected GColor color;
	protected int number;
	
	public Badge(int number, GColor color) {
		this.color = color;
	}
	
	public GColor getColor() {
		return color;
	}
	
	public int getNumber() {
		return number;
	}
}
