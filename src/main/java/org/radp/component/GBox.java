/*
 * Copyright (c) 1997, 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.radp.component;

import org.radp.layout.GBoxLayout;

import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GLayoutManager;
import de.exware.gwtswing.swing.GComponent;

/**
 * A lightweight container that uses a BoxLayout object as its layout manager.
 * Box provides several class methods that are useful for containers using
 * BoxLayout -- even non-Box containers.
 *
 * <p>
 * The <code>Box</code> class can create several kinds of invisible components
 * that affect layout: glue, struts, and rigid areas. If all the components your
 * <code>Box</code> contains have a fixed size, you might want to use a glue
 * component (returned by <code>createGlue</code>) to control the components'
 * positions. If you need a fixed amount of space between two components, try
 * using a strut (<code>createHorizontalStrut</code> or
 * <code>createVerticalStrut</code>). If you need an invisible component that
 * always takes up the same amount of space, get it by invoking
 * <code>createRigidArea</code>.
 * <p>
 * If you are implementing a <code>BoxLayout</code> you can find further
 * information and examples in
 * <a href="https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html">How
 * to Use BoxLayout</a>, a section in <em>The Java Tutorial.</em>
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans has been added to the <code>java.beans</code> package. Please see
 * {@link java.beans.XMLEncoder}.
 *
 * @see BoxLayout
 *
 * @author Timothy Prinzing
 * @since 1.2
 */

public class GBox extends GComponent {

	/**
	 * Creates a <code>Box</code> that displays its components along the specified
	 * axis.
	 *
	 * @param axis can be {@link BoxLayout#X_AXIS}, {@link BoxLayout#Y_AXIS},
	 *             {@link BoxLayout#LINE_AXIS} or {@link BoxLayout#PAGE_AXIS}.
	 * @throws AWTError if the <code>axis</code> is invalid
	 * @see #createHorizontalBox
	 * @see #createVerticalBox
	 */
	public GBox(int axis) {
		super();
		super.setLayout(new GBoxLayout(this, axis));
	}

	/**
	 * Creates a <code>Box</code> that displays its components from left to right.
	 * If you want a <code>Box</code> that respects the component orientation you
	 * should create the <code>Box</code> using the constructor and pass in
	 * <code>BoxLayout.LINE_AXIS</code>, eg:
	 * 
	 * <pre>
	 * Box lineBox = new Box(BoxLayout.LINE_AXIS);
	 * </pre>
	 *
	 * @return the box
	 */
	public static GBox createHorizontalBox() {
		return new GBox(GBoxLayout.X_AXIS);
	}

	/**
	 * Creates a <code>Box</code> that displays its components from top to bottom.
	 * If you want a <code>Box</code> that respects the component orientation you
	 * should create the <code>Box</code> using the constructor and pass in
	 * <code>BoxLayout.PAGE_AXIS</code>, eg:
	 * 
	 * <pre>
	 * Box lineBox = new Box(BoxLayout.PAGE_AXIS);
	 * </pre>
	 *
	 * @return the box
	 */
	public static GBox createVerticalBox() {
		return new GBox(GBoxLayout.Y_AXIS);
	}

	/**
	 * Creates an invisible component that's always the specified size. <!-- WHEN
	 * WOULD YOU USE THIS AS OPPOSED TO A STRUT? -->
	 *
	 * @param d the dimensions of the invisible component
	 * @return the component
	 * @see #createGlue
	 * @see #createHorizontalStrut
	 * @see #createVerticalStrut
	 */
	public static GComponent createRigidArea(GDimension d) {
		return new GFiller(d, d, d);
	}

	/**
	 * Creates an invisible, fixed-width component. In a horizontal box, you
	 * typically use this method to force a certain amount of space between two
	 * components. In a vertical box, you might use this method to force the box to
	 * be at least the specified width. The invisible component has no height unless
	 * excess space is available, in which case it takes its share of available
	 * space, just like any other component that has no maximum height.
	 *
	 * @param width the width of the invisible component, in pixels &gt;= 0
	 * @return the component
	 * @see #createVerticalStrut
	 * @see #createGlue
	 * @see #createRigidArea
	 */
	public static GComponent createHorizontalStrut(int width) {
		return new GFiller(new GDimension(width, 0), new GDimension(width, 0), new GDimension(width, Short.MAX_VALUE));
	}

	/**
	 * Creates an invisible, fixed-height component. In a vertical box, you
	 * typically use this method to force a certain amount of space between two
	 * components. In a horizontal box, you might use this method to force the box
	 * to be at least the specified height. The invisible component has no width
	 * unless excess space is available, in which case it takes its share of
	 * available space, just like any other component that has no maximum width.
	 *
	 * @param height the height of the invisible component, in pixels &gt;= 0
	 * @return the component
	 * @see #createHorizontalStrut
	 * @see #createGlue
	 * @see #createRigidArea
	 */
	public static GComponent createVerticalStrut(int height) {
		return new GFiller(new GDimension(0, height), new GDimension(0, height), new GDimension(Short.MAX_VALUE, height));
	}

	/**
	 * Creates an invisible "glue" component that can be useful in a Box whose
	 * visible components have a maximum width (for a horizontal box) or height (for
	 * a vertical box). You can think of the glue component as being a gooey
	 * substance that expands as much as necessary to fill the space between its
	 * neighboring components.
	 *
	 * <p>
	 *
	 * For example, suppose you have a horizontal box that contains two fixed-size
	 * components. If the box gets extra space, the fixed-size components won't
	 * become larger, so where does the extra space go? Without glue, the extra
	 * space goes to the right of the second component. If you put glue between the
	 * fixed-size components, then the extra space goes there. If you put glue
	 * before the first fixed-size component, the extra space goes there, and the
	 * fixed-size components are shoved against the right edge of the box. If you
	 * put glue before the first fixed-size component and after the second
	 * fixed-size component, the fixed-size components are centered in the box.
	 *
	 * <p>
	 *
	 * To use glue, call <code>Box.createGlue</code> and add the returned component
	 * to a container. The glue component has no minimum or preferred size, so it
	 * takes no space unless excess space is available. If excess space is
	 * available, then the glue component takes its share of available horizontal or
	 * vertical space, just like any other component that has no maximum width or
	 * height.
	 *
	 * @return the component
	 */
	public static GComponent createGlue() {
		return new GFiller(new GDimension(0, 0), new GDimension(0, 0), new GDimension(Short.MAX_VALUE, Short.MAX_VALUE));
	}

	/**
	 * Creates a horizontal glue component.
	 *
	 * @return the component
	 */
	public static GComponent createHorizontalGlue() {
		return new GFiller(new GDimension(0, 0), new GDimension(0, 0), new GDimension(Short.MAX_VALUE, 0));
	}

	/**
	 * Creates a vertical glue component.
	 *
	 * @return the component
	 */
	public static GComponent createVerticalGlue() {
		return new GFiller(new GDimension(0, 0), new GDimension(0, 0), new GDimension(0, Short.MAX_VALUE));
	}

	/**
	 * Throws an AWTError, since a Box can use only a BoxLayout.
	 *
	 * @param l the layout manager to use
	 */
	public void setLayout(GLayoutManager l) {
		throw new Error("Illegal request");
	}

	/**
	 * An implementation of a lightweight component that participates in layout but
	 * has no view.
	 * <p>
	 * <strong>Warning:</strong> Serialized objects of this class will not be
	 * compatible with future Swing releases. The current serialization support is
	 * appropriate for short term storage or RMI between applications running the
	 * same version of Swing. As of 1.4, support for long term storage of all
	 * JavaBeans has been added to the <code>java.beans</code> package. Please see
	 * {@link java.beans.XMLEncoder}.
	 */
	public static class GFiller extends GComponent {
		private GDimension minimumSize;
		private GDimension maximumSize;
		
		/**
		 * Constructor to create shape with the given size ranges.
		 *
		 * @param min  Minimum size
		 * @param pref Preferred size
		 * @param max  Maximum size
		 */
		public GFiller(GDimension min, GDimension pref, GDimension max) {
			minimumSize = min;
			setPreferredSize(pref);
			GComponentUtil.setMaximumSize(GFiller.this, max); //setMaximumSize(max);
			//setFocusable(false);
		}

		/**
		 * Change the size requests for this shape. An invalidate() is propagated upward
		 * as a result so that layout will eventually happen with using the new sizes.
		 *
		 * @param min  Value to return for getMinimumSize
		 * @param pref Value to return for getPreferredSize
		 * @param max  Value to return for getMaximumSize
		 */
		public void changeShape(GDimension min, GDimension pref, GDimension max) {
			minimumSize = min;
			setPreferredSize(pref);
			GComponentUtil.setMaximumSize(GFiller.this, max); //setMaximumSize(max);
			revalidate();
		}
		
		@Override
		public GDimension getMinimumSize() {
			return minimumSize;
		}

	}

}
