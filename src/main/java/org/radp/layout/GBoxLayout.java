/*
 * Copyright (c) 1997, 2019, Oracle and/or its affiliates. All rights reserved.
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
package org.radp.layout;

import org.radp.component.GComponentOrientation;
import org.radp.component.GComponentUtil;
import org.radp.component.GSizeRequirements;

import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GInsets;
import de.exware.gwtswing.awt.GLayoutManager;
import de.exware.gwtswing.swing.GComponent;

/**
 * A layout manager that allows multiple components to be laid out either
 * vertically or horizontally. The components will not wrap so, for
 * example, a vertical arrangement of components will stay vertically
 * arranged when the frame is resized.
 * <div style="float:right;text-align:center">
 *   <p><b>Example:</b>
 *   <p><img src="doc-files/BoxLayout-1.gif"
 *      alt="The following text describes this graphic."
 *      width="191" height="201">
 * </div>
 * <p>
 * Nesting multiple panels with different combinations of horizontal and
 * vertical gives an effect similar to GridBagLayout, without the
 * complexity. The diagram shows two panels arranged horizontally, each
 * of which contains 3 components arranged vertically.
 *
 * <p> The BoxLayout manager is constructed with an axis parameter that
 * specifies the type of layout that will be done. There are four choices:
 *
 * <blockquote><b>{@code X_AXIS}</b> - Components are laid out horizontally
 * from left to right.</blockquote>
 *
 * <blockquote><b>{@code Y_AXIS}</b> - Components are laid out vertically
 * from top to bottom.</blockquote>
 *
 * <blockquote><b>{@code LINE_AXIS}</b> - Components are laid out the way
 * words are laid out in a line, based on the container's
 * {@code ComponentOrientation} property. If the container's
 * {@code ComponentOrientation} is horizontal then components are laid out
 * horizontally, otherwise they are laid out vertically.  For horizontal
 * orientations, if the container's {@code ComponentOrientation} is left to
 * right then components are laid out left to right, otherwise they are laid
 * out right to left. For vertical orientations components are always laid out
 * from top to bottom.</blockquote>
 *
 * <blockquote><b>{@code PAGE_AXIS}</b> - Components are laid out the way
 * text lines are laid out on a page, based on the container's
 * {@code ComponentOrientation} property. If the container's
 * {@code ComponentOrientation} is horizontal then components are laid out
 * vertically, otherwise they are laid out horizontally.  For horizontal
 * orientations, if the container's {@code ComponentOrientation} is left to
 * right then components are laid out left to right, otherwise they are laid
 * out right to left.&nbsp; For vertical orientations components are always
 * laid out from top to bottom.</blockquote>
 * <p>
 * For all directions, components are arranged in the same order as they were
 * added to the container.
 * <p>
 * BoxLayout attempts to arrange components
 * at their preferred widths (for horizontal layout)
 * or heights (for vertical layout).
 * For a horizontal layout,
 * if not all the components are the same height,
 * BoxLayout attempts to make all the components
 * as high as the highest component.
 * If that's not possible for a particular component,
 * then BoxLayout aligns that component vertically,
 * according to the component's Y alignment.
 * By default, a component has a Y alignment of 0.5,
 * which means that the vertical center of the component
 * should have the same Y coordinate as
 * the vertical centers of other components with 0.5 Y alignment.
 * <p>
 * Similarly, for a vertical layout,
 * BoxLayout attempts to make all components in the column
 * as wide as the widest component.
 * If that fails, it aligns them horizontally
 * according to their X alignments.  For {@code PAGE_AXIS} layout,
 * horizontal alignment is done based on the leading edge of the component.
 * In other words, an X alignment value of 0.0 means the left edge of a
 * component if the container's {@code ComponentOrientation} is left to
 * right and it means the right edge of the component otherwise.
 * <p>
 * Instead of using BoxLayout directly, many programs use the Box class.
 * The Box class is a lightweight container that uses a BoxLayout.
 * It also provides handy methods to help you use BoxLayout well.
 * Adding components to multiple nested boxes is a powerful way to get
 * the arrangement you want.
 * <p>
 * For further information and examples see
 * <a
 href="https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html">How to Use BoxLayout</a>,
 * a section in <em>The Java Tutorial.</em>
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans
 * has been added to the {@code java.beans} package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @see Box
 * @see java.awt.ComponentOrientation
 * @see JComponent#getAlignmentX
 * @see JComponent#getAlignmentY
 *
 * @author   Timothy Prinzing
 * @since 1.2
 */
@SuppressWarnings("serial")
public class GBoxLayout implements GLayoutManager {

    /**
     * Specifies that components should be laid out left to right.
     */
    public static final int X_AXIS = 0;

    /**
     * Specifies that components should be laid out top to bottom.
     */
    public static final int Y_AXIS = 1;

    /**
     * Specifies that components should be laid out in the direction of
     * a line of text as determined by the target container's
     * {@code ComponentOrientation} property.
     */
    public static final int LINE_AXIS = 2;

    /**
     * Specifies that components should be laid out in the direction that
     * lines flow across a page as determined by the target container's
     * {@code ComponentOrientation} property.
     */
    public static final int PAGE_AXIS = 3;

    /**
     * Creates a layout manager that will lay out components along the
     * given axis.
     *
     * @param target  the container that needs to be laid out
     * @param axis  the axis to lay out components along. Can be one of:
     *              {@code BoxLayout.X_AXIS, BoxLayout.Y_AXIS,
     *              BoxLayout.LINE_AXIS} or {@code BoxLayout.PAGE_AXIS}
     *
     * @throws AWTError  if the value of {@code axis} is invalid
     */
    public GBoxLayout(GComponent target, int axis) {
        if (axis != X_AXIS && axis != Y_AXIS &&
            axis != LINE_AXIS && axis != PAGE_AXIS) {
            throw new Error("Invalid axis");
        }
        this.axis = axis;
        this.target = target;
    }

    /**
     * Returns the container that uses this layout manager.
     *
     * @return the container that uses this layout manager
     *
     * @since 1.6
     */
    public final GComponent getTarget() {
        return this.target;
    }

    /**
     * Returns the axis that was used to lay out components.
     * Returns one of:
     * {@code BoxLayout.X_AXIS, BoxLayout.Y_AXIS,
     * BoxLayout.LINE_AXIS} or {@code BoxLayout.PAGE_AXIS}
     *
     * @return the axis that was used to lay out components
     *
     * @since 1.6
     */
    public final int getAxis() {
        return this.axis;
    }

    /**
     * Indicates that a child has changed its layout related information,
     * and thus any cached calculations should be flushed.
     * <p>
     * This method is called by AWT when the invalidate method is called
     * on the Container.  Since the invalidate method may be called
     * asynchronously to the event thread, this method may be called
     * asynchronously.
     *
     * @param target  the affected container
     *
     * @throws AWTError  if the target isn't the container specified to the
     *                      BoxLayout constructor
     */
    public synchronized void invalidateLayout(GComponent target) {
        checkContainer(target);
        xChildren = null;
        yChildren = null;
        xTotal = null;
        yTotal = null;
    }

    /**
     * Not used by this class.
     *
     * @param name the name of the component
     * @param comp the component
     */
    public void addLayoutComponent(String name, GComponent comp) {
        invalidateLayout(comp.getParent());
    }

    /**
     * Not used by this class.
     *
     * @param comp the component
     */
    public void removeLayoutComponent(GComponent comp) {
        invalidateLayout(comp.getParent());
    }

    /**
     * Not used by this class.
     *
     * @param comp the component
     * @param constraints constraints
     */
    public void addLayoutComponent(GComponent comp, Object constraints) {
        invalidateLayout(comp.getParent());
    }

    /**
     * Returns the preferred dimensions for this layout, given the components
     * in the specified target container.
     *
     * @param target  the container that needs to be laid out
     * @return the dimensions &gt;= 0 &amp;&amp; &lt;= Integer.MAX_VALUE
     * @throws AWTError  if the target isn't the container specified to the
     *                      BoxLayout constructor
     * @see Container
     * @see #minimumLayoutSize
     * @see #maximumLayoutSize
     */
    public GDimension preferredLayoutSize(GComponent target) {
        GDimension size;
        synchronized(this) {
            checkContainer(target);
            checkRequests();
            size = new GDimension(xTotal.preferred, yTotal.preferred);
        }

        GInsets insets = target.getInsets();
        size.width = (int) Math.min((long) size.width + (long) insets.left + (long) insets.right, Integer.MAX_VALUE);
        size.height = (int) Math.min((long) size.height + (long) insets.top + (long) insets.bottom, Integer.MAX_VALUE);
        return size;
    }

    /**
     * Returns the minimum dimensions needed to lay out the components
     * contained in the specified target container.
     *
     * @param target  the container that needs to be laid out
     * @return the dimensions &gt;= 0 &amp;&amp; &lt;= Integer.MAX_VALUE
     * @throws AWTError  if the target isn't the container specified to the
     *                      BoxLayout constructor
     * @see #preferredLayoutSize
     * @see #maximumLayoutSize
     */
    public GDimension minimumLayoutSize(GComponent target) {
        GDimension size;
        synchronized(this) {
            checkContainer(target);
            checkRequests();
            size = new GDimension(xTotal.minimum, yTotal.minimum);
        }

        GInsets insets = target.getInsets();
        size.width = (int) Math.min((long) size.width + (long) insets.left + (long) insets.right, Integer.MAX_VALUE);
        size.height = (int) Math.min((long) size.height + (long) insets.top + (long) insets.bottom, Integer.MAX_VALUE);
        return size;
    }

    /**
     * Returns the maximum dimensions the target container can use
     * to lay out the components it contains.
     *
     * @param target  the container that needs to be laid out
     * @return the dimensions &gt;= 0 &amp;&amp; &lt;= Integer.MAX_VALUE
     * @throws AWTError  if the target isn't the container specified to the
     *                      BoxLayout constructor
     * @see #preferredLayoutSize
     * @see #minimumLayoutSize
     */
    public GDimension maximumLayoutSize(GComponent target) {
        GDimension size;
        synchronized(this) {
            checkContainer(target);
            checkRequests();
            size = new GDimension(xTotal.maximum, yTotal.maximum);
        }

        GInsets insets = target.getInsets();
        size.width = (int) Math.min((long) size.width + (long) insets.left + (long) insets.right, Integer.MAX_VALUE);
        size.height = (int) Math.min((long) size.height + (long) insets.top + (long) insets.bottom, Integer.MAX_VALUE);
        return size;
    }

    /**
     * Returns the alignment along the X axis for the container.
     * If the box is horizontal, the default
     * alignment will be returned. Otherwise, the alignment needed
     * to place the children along the X axis will be returned.
     *
     * @param target  the container
     * @return the alignment &gt;= 0.0f &amp;&amp; &lt;= 1.0f
     * @throws AWTError  if the target isn't the container specified to the
     *                      BoxLayout constructor
     */
    public synchronized float getLayoutAlignmentX(GComponent target) {
        checkContainer(target);
        checkRequests();
        return xTotal.alignment;
    }

    /**
     * Returns the alignment along the Y axis for the container.
     * If the box is vertical, the default
     * alignment will be returned. Otherwise, the alignment needed
     * to place the children along the Y axis will be returned.
     *
     * @param target  the container
     * @return the alignment &gt;= 0.0f &amp;&amp; &lt;= 1.0f
     * @throws AWTError  if the target isn't the container specified to the
     *                      BoxLayout constructor
     */
    public synchronized float getLayoutAlignmentY(GComponent target) {
        checkContainer(target);
        checkRequests();
        return yTotal.alignment;
    }

    /**
     * Called by the AWT <!-- XXX CHECK! --> when the specified container
     * needs to be laid out.
     *
     * @param target  the container to lay out
     *
     * @throws AWTError  if the target isn't the container specified to the
     *                      BoxLayout constructor
     */
    public void layoutContainer(GComponent target) {
        checkContainer(target);
        int nChildren = target.getComponentCount();
        int[] xOffsets = new int[nChildren];
        int[] xSpans = new int[nChildren];
        int[] yOffsets = new int[nChildren];
        int[] ySpans = new int[nChildren];

        GDimension alloc = target.getSize();
        GInsets in = target.getInsets();
        alloc.width -= in.left + in.right;
        alloc.height -= in.top + in.bottom;

        // Resolve axis to an absolute value (either X_AXIS or Y_AXIS)
        GComponentOrientation o = GComponentUtil.getComponentOrientation(target);
        int absoluteAxis = resolveAxis( axis, o );
        boolean ltr = (absoluteAxis != axis) ? o.isLeftToRight() : true;


        // determine the child placements
        synchronized(this) {
            checkRequests();

            if (absoluteAxis == X_AXIS) {
                GSizeRequirements.calculateTiledPositions(alloc.width, xTotal,
                                                         xChildren, xOffsets,
                                                         xSpans, ltr);
                GSizeRequirements.calculateAlignedPositions(alloc.height, yTotal,
                                                           yChildren, yOffsets,
                                                           ySpans);
            } else {
                GSizeRequirements.calculateAlignedPositions(alloc.width, xTotal,
                                                           xChildren, xOffsets,
                                                           xSpans, ltr);
                GSizeRequirements.calculateTiledPositions(alloc.height, yTotal,
                                                         yChildren, yOffsets,
                                                         ySpans);
            }
        }

        // flush changes to the container
        for (int i = 0; i < nChildren; i++) {
            GComponent c = target.getComponent(i);
            c.setBounds((int) Math.min((long) in.left + (long) xOffsets[i], Integer.MAX_VALUE),
                        (int) Math.min((long) in.top + (long) yOffsets[i], Integer.MAX_VALUE),
                        xSpans[i], ySpans[i]);

        }
    }

    void checkContainer(GComponent target) {
        if (this.target != target) {
            throw new Error("BoxLayout can't be shared");
        }
    }

    void checkRequests() {
        if (xChildren == null || yChildren == null) {
            // The requests have been invalidated... recalculate
            // the request information.
            int n = target.getComponentCount();
            xChildren = new GSizeRequirements[n];
            yChildren = new GSizeRequirements[n];
            for (int i = 0; i < n; i++) {
                GComponent c = target.getComponent(i);
                if (!c.isVisible()) {
                    xChildren[i] = new GSizeRequirements(0,0,0, GComponentUtil.getAlignmentX(c));
                    yChildren[i] = new GSizeRequirements(0,0,0, GComponentUtil.getAlignmentY(c));
                    continue;
                }
                GDimension min = c.getMinimumSize();
                GDimension typ = c.getPreferredSize();
                GDimension max = GComponentUtil.getMaximumSize(c);
                xChildren[i] = new GSizeRequirements(min.width, typ.width,
                                                    max.width,
                                                    GComponentUtil.getAlignmentX(c));
                yChildren[i] = new GSizeRequirements(min.height, typ.height,
                                                    max.height,
                                                    GComponentUtil.getAlignmentY(c));
            }
            
            // Resolve axis to an absolute value (either X_AXIS or Y_AXIS)
            int absoluteAxis = resolveAxis(axis, GComponentUtil.getComponentOrientation(target));

            if (absoluteAxis == X_AXIS) {
                xTotal = GSizeRequirements.getTiledSizeRequirements(xChildren);
                yTotal = GSizeRequirements.getAlignedSizeRequirements(yChildren);
            } else {
                xTotal = GSizeRequirements.getAlignedSizeRequirements(xChildren);
                yTotal = GSizeRequirements.getTiledSizeRequirements(yChildren);
            }
        }
    }

    /**
     * Given one of the 4 axis values, resolve it to an absolute axis.
     * The relative axis values, PAGE_AXIS and LINE_AXIS, are converted
     * to their absolute counterpart given the target's ComponentOrientation
     * value.  The absolute axes, X_AXIS and Y_AXIS, are returned unmodified.
     *
     * @param axis the axis to resolve
     * @param o the ComponentOrientation to resolve against
     * @return the resolved axis
     */
    private int resolveAxis(int axis, GComponentOrientation o) {
        int absoluteAxis;
        if( axis == LINE_AXIS ) {
            absoluteAxis = o.isHorizontal() ? X_AXIS : Y_AXIS;
        } else if( axis == PAGE_AXIS ) {
            absoluteAxis = o.isHorizontal() ? Y_AXIS : X_AXIS;
        } else {
            absoluteAxis = axis;
        }
        return absoluteAxis;
   }


    private int axis;
    private GComponent target;

    private transient GSizeRequirements[] xChildren;
    private transient GSizeRequirements[] yChildren;
    private transient GSizeRequirements xTotal;
    private transient GSizeRequirements yTotal;
}
