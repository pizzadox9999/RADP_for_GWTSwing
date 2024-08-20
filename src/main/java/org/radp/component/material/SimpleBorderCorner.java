package org.radp.component.material;

import org.radp.CSSUnit;

public class SimpleBorderCorner implements BorderCorner {
	
	public final int value;
	public final CSSUnit unit;
	
	public SimpleBorderCorner(int value, CSSUnit unit) {
		this.value = value;
		this.unit = unit;
	}
	
	@Override
	public String getValue() {
		return new StringBuilder()
				.append(value)
				.append(unit)
				.toString();
	}
	
}
