package org.radp;

public final class CSSUnit {
	public static final CSSUnit PX = new CSSUnit("px");
	public static final CSSUnit PERCENT = new CSSUnit("%");
	
	private String unit;
	
	private CSSUnit(String unit) {
		this.unit = unit;
	}
	
	@Override
	public boolean equals(Object object) {
		return (object instanceof CSSUnit) && ((CSSUnit) object).unit == unit;
	}
	
	public String toString() {
		return unit;
	}
	
}
