package org.radp.component.material;

import org.radp.CSSUnit;

public class ComplexBorderCorner implements BorderCorner {
	
	public final int arcStart;
	public final int arcEnd;
	
	public final CSSUnit unitStart;
	public final CSSUnit unitEnd;
	
	public ComplexBorderCorner(int arcStart, int arcEnd, CSSUnit unitStart, CSSUnit unitEnd) {
		this.arcStart = arcStart;
		this.arcEnd = arcEnd;
		this.unitStart = unitStart;
		this.unitEnd = unitEnd;
	}


	@Override
	public String getValue() {
		return new StringBuilder()
				.append(arcStart)
				.append(unitStart)
				.append(" ")
				.append(arcEnd)
				.append(unitEnd)
				.toString();
	}
	
}
