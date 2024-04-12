package org.radp.layout;

/**
 * An interface used to indicate if a component is scaled within a ScaleLayout.
 *
 * <p>
 * Copyright (c) Xoetrope Ltd., 2002-2007
 * </p>
 * <p>
 * License: see License.txt
 * </p>
 * $Revision: 1.0 $
 */
public interface Scaleable {
	/**
	 * Indicates whether the component should be scaled. If false is returned the
	 * component will not be resized by the ScaleLayout
	 * 
	 * @return false if the component should not be scaled otherwise true
	 */
	public boolean isScalable();
}