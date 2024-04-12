package org.radp.layout;

import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GFont;
import de.exware.gwtswing.awt.GLayoutManager;
import de.exware.gwtswing.swing.GComponent;

/**
 * A scaling layout manager. Scales the components in proportion to their
 * original layout size relative to the original container size
 *
 * <p>
 * Copyright (c) Xoetrope Ltd., 2002-2007
 * </p>
 * <p>
 * License: see License.txt
 * </p>
 * $Revision: 1.2 $
 */
public class ScaleLayout implements GLayoutManager {
	private GDimension lastSize;

	private double scaleX;
	private double scaleY;

	private boolean scaleAll;
	private boolean scaleFonts;

	public ScaleLayout() {
		scaleAll = true;
	}

	/**
	 * Creates a new ScaleLayout
	 * 
	 * @param designWidth  the original design width for the container
	 * @param designHeight the original design height for the container
	 */
	public ScaleLayout(int designWidth, int designHeight) {
		scaleAll = true;
		lastSize = new GDimension(designWidth, designHeight);
	}

	/**
	 * Creates a new ScaleLayout
	 * 
	 * @param designWidth  the original design width for the container
	 * @param designHeight the original design height for the container
	 * @param scales       true to scale all the children including containers and
	 *                     the container's children.<br>
	 *                     false to scale only the container that owns this layout
	 *                     and any child container with a null layout.
	 */
	public ScaleLayout(int designWidth, int designHeight, boolean scales) {
		scaleAll = true;
		lastSize = new GDimension(designWidth, designHeight);
	}

	/**
	 * If the layout manager uses a per-component string, adds the component
	 * <code>comp</code> to the layout, associating it with the string specified by
	 * <code>name</code>.
	 * 
	 * @param name the string to be associated with the component
	 * @param comp the component to be added
	 */
	public void addLayoutComponent(String name, GComponent comp) {
	}

	/**
	 * Lays out the specified container.
	 * 
	 * @param parent the container to be laid out
	 */
	public void layoutContainer(GComponent parent) {
		double width = parent.getSize().width;
		if (lastSize == null)
			lastSize = parent.getSize();

		if (width > 0) {
			double height = parent.getSize().height;
			scaleX = width / lastSize.width;
			scaleY = height / lastSize.height;
			int noComps = parent.getComponentCount();
			for (int i = 0; i < noComps; i++)
				iterateComps(parent.getComponent(i));

			lastSize = parent.getSize();
		}
	}

	/**
	 * Calculates the minimum size dimensions for the specified container, given the
	 * components it contains.
	 * 
	 * @param parent the component to be laid out
	 * @see #preferredLayoutSize
	 */
	public GDimension minimumLayoutSize(GComponent parent) {
		return new GDimension(Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	/**
	 * Calculates the preferred size dimensions for the specified container, given
	 * the components it contains.
	 * 
	 * @param parent the container to be laid out
	 * 
	 * @see #minimumLayoutSize
	 */
	public GDimension preferredLayoutSize(GComponent parent) {
		if(parent.getLayout() != this) {
			return parent.getPreferredSize();
		} else {
			int width = 0;
			int height = 0;
			for(GComponent c : parent.getComponents()) {
				GDimension d = c.getPreferredSize();
				width = width + d.width;
				height = height + d.height;
			}
			
			return new GDimension(width, height);
		}
	}

	/**
	 * Removes the specified component from the layout.
	 * 
	 * @param comp the component to be removed
	 */
	public void removeLayoutComponent(GComponent comp) {
	}

	/**
	 * Modify this component and its children, if any
	 * 
	 * @param comp the component to be modified
	 */
	protected void iterateComps(GComponent comp) {
		scaleComponent(comp);
		if (comp instanceof GComponent) {
			GComponent cont = comp;
			if (scaleAll || (cont.getLayout() == null)) {
				int numComps = ((GComponent) comp).getComponentCount();
				for (int i = 0; i < numComps; i++)
					iterateComps(((GComponent) comp).getComponent(i));
			}
		}
	}

	/**
	 * Scale the component
	 * 
	 * @param comp the component to be modified
	 */
	public void scaleComponent(GComponent comp) {
		boolean isScaleable = true;
		if (comp instanceof Scaleable)
			isScaleable = ((Scaleable) comp).isScalable();

		GDimension size = comp.getSize();
		int compWidth = size.width;
		int compHeight = size.height;
		int compX = (int) comp.getLocation().getX();
		int compY = (int) comp.getLocation().getY();

		int newWidth, newHeight;
		if (isScaleable) {
			newWidth = (int) ((compWidth * scaleX) + 0.5);
			newHeight = (int) ((compHeight * scaleY) + 0.5);
		} else {
			newWidth = compWidth;
			newHeight = compHeight;
		}

		comp.setBounds((int) ((compX * scaleX) + 0.5), (int) ((compY * scaleY) + 0.5), newWidth, newHeight);

		if (scaleFonts) {
			GFont oldFont = comp.getFont();
			GFont newFont = new GFont(oldFont.getFamily(), oldFont.getStyle(), compHeight * (int)oldFont.getSize2D());
			comp.setFont(newFont);
		}
	}

	/**
	 * Sets the state of the scale all flag. Does not layout the container.
	 * 
	 * @param scales true to scale all the children including containers and the
	 *               container's children.<br>
	 *               false to scale only the container that owns this layout and any
	 *               child container with a null layout.
	 */
	public void setScaleAll(boolean scales) {
		scaleAll = scales;
	}

	/**
	 * Sets the state of the scale all flag. Does not layout the container.
	 * 
	 * @return true if all the children including containers and the container's
	 *         children are scaled, otherwise false when only the container that
	 *         owns this layout and any child container with a null layout are
	 *         scaled.
	 */
	public boolean getScaleAll() {
		return scaleAll;
	}

	/**
	 * Sets the state of the scale fonts flag.
	 * 
	 * @param scales true to scale all the fonts of a rescaled container<br>
	 *               false to leave the font unchanged.
	 */
	public void setScaleFonts(boolean scales) {
		scaleFonts = scales;
	}

	/**
	 * Sets the state of the scale fonts flag. Does not layout the container.
	 * 
	 * @return true if fonts in the children are scaled, false when no changes are
	 *         made to the fonts.
	 */
	public boolean getScaleFonts() {
		return scaleFonts;
	}

	@Override
	public void addLayoutComponent(GComponent comp, Object constraints) {	}
}
