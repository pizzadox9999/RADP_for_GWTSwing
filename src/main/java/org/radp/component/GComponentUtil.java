package org.radp.component;

import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.swing.GComponent;
import static org.radp.RADP.checkNullWithDefault;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class GComponentUtil {
	public static final String COMPONENT_ORIENTATION = "COMPONENT_ORIENTATION";
	public static final String MAXIMUM_SIZE = "MAXIMUM_SIZE";
	public static final String ALIGNMENT_Y = "ALIGNMENT_Y";
	public static final String ALIGNMENT_X = "ALIGNMENT_X";

	private GComponentUtil() {
	}

	public static final GComponentOrientation getComponentOrientation(GComponent component) {
		return checkNullWithDefault(component.getClientProperty(COMPONENT_ORIENTATION),
				GComponentOrientation.LEFT_TO_RIGHT);
	}

	public static final GDimension getMaximumSize(GComponent component) {
		Object clientPropertyMaxiumSize = component.getClientProperty(MAXIMUM_SIZE);
		GDimension maximumSize = new GDimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
		
		if(clientPropertyMaxiumSize != null) {
			if(clientPropertyMaxiumSize instanceof Function) {
				maximumSize = ((Function<GComponent, GDimension>) clientPropertyMaxiumSize).apply(component);
			} else if(clientPropertyMaxiumSize instanceof GDimension) {
				maximumSize = (GDimension) clientPropertyMaxiumSize;
			}
		}
		
		return maximumSize;
	}
	
	public static final void setMaximumSize(GComponent component, GDimension maximumSize) {
		component.putClientProperty(MAXIMUM_SIZE, maximumSize);
	}
	
	public static final void setMaximumSize(GComponent component, Function<GComponent, GDimension> function) {
		component.putClientProperty(MAXIMUM_SIZE, function);
	}

	public static final float getAlignmentY(GComponent component) {
		return checkNullWithDefault(component.getClientProperty(ALIGNMENT_Y), .5f);
	}

	public static final float getAlignmentX(GComponent component) {
		return checkNullWithDefault(component.getClientProperty(ALIGNMENT_X), .5f);
	}
}
