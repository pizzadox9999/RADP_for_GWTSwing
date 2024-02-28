package org.radp.layout;

import org.radp.RADP;

import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GInsets;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GScrollPane;
import de.exware.gwtswing.swing.GSwingUtilities;

/**
 * FlowLayout subclass that fully supports wrapping of components.
 */
public class WrapGFlowLayout extends GFlowLayout {
	private GDimension preferredLayoutSize;

	/**
	 * Constructs a new <code>WrapLayout</code> with a left alignment and a default
	 * 5-unit horizontal and vertical gap.
	 */
	public WrapGFlowLayout() {
		super();
	}

	/**
	 * Constructs a new <code>FlowLayout</code> with the specified alignment and a
	 * default 5-unit horizontal and vertical gap. The value of the alignment
	 * argument must be one of <code>WrapLayout</code>, <code>WrapLayout</code>, or
	 * <code>WrapLayout</code>.
	 * 
	 * @param align the alignment value
	 */
	public WrapGFlowLayout(int align) {
		super(align);
	}

	/**
	 * Creates a new flow layout manager with the indicated alignment and the
	 * indicated horizontal and vertical gaps.
	 * <p>
	 * The value of the alignment argument must be one of <code>WrapLayout</code>,
	 * <code>WrapLayout</code>, or <code>WrapLayout</code>.
	 * 
	 * @param align the alignment value
	 * @param hgap  the horizontal gap between components
	 * @param vgap  the vertical gap between components
	 */
	public WrapGFlowLayout(int align, int hgap, int vgap) {
		super(align, hgap, vgap);
	}

	/**
	 * Returns the preferred dimensions for this layout given the <i>visible</i>
	 * components in the specified target container.
	 * 
	 * @param target the component which needs to be laid out
	 * @return the preferred dimensions to lay out the subcomponents of the
	 *         specified container
	 */
	@Override
	public GDimension preferredLayoutSize(GComponent target) {
		return layoutSize(target, true);
	}

	/**
	 * Returns the minimum dimensions needed to layout the <i>visible</i> components
	 * contained in the specified target container.
	 * 
	 * @param target the component which needs to be laid out
	 * @return the minimum dimensions to lay out the subcomponents of the specified
	 *         container
	 */
	@Override
	public GDimension minimumLayoutSize(GComponent target) {
		GDimension minimum = layoutSize(target, false);
		minimum.width -= (getHgap() + 1);
		return minimum;
	}

	/**
	 * Returns the minimum or preferred dimension needed to layout the target
	 * container.
	 *
	 * @param target    target to get layout size for
	 * @param preferred should preferred size be calculated
	 * @return the dimension to layout the target container
	 */
	private GDimension layoutSize(GComponent target, boolean preferred) {
		// Each row must fit with the width allocated to the containter.
		// When the container width = 0, the preferred width of the container
		// has not yet been calculated so lets ask for the maximum.

		int targetWidth = target.getSize().width;
		GComponent container = target;

		while (container.getSize().width == 0 && container.getParent() != null) {
			container = container.getParent();
		}

		targetWidth = container.getSize().width;

		if (targetWidth == 0)
			targetWidth = Integer.MAX_VALUE;

		int hgap = getHgap();
		int vgap = getVgap();
		GInsets insets = target.getInsets();
		int horizontalInsetsAndGap = insets.left + insets.right + (hgap * 2);
		int maxWidth = targetWidth - horizontalInsetsAndGap;

		// Fit components into the allowed width

		GDimension dim = new GDimension(0, 0);
		int rowWidth = 0;
		int rowHeight = 0;

		int nmembers = target.getComponentCount();

		for (int i = 0; i < nmembers; i++) {
			GComponent m = target.getComponent(i);

			if (m.isVisible()) {
				GDimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();

				// Can't add the component to current row. Start a new row.

				if (rowWidth + d.width > maxWidth) {
					addRow(dim, rowWidth, rowHeight);
					rowWidth = 0;
					rowHeight = 0;
				}

				// Add a horizontal gap for all components after the first

				if (rowWidth != 0) {
					rowWidth += hgap;
				}

				rowWidth += d.width;
				rowHeight = Math.max(rowHeight, d.height);
			}
		}

		addRow(dim, rowWidth, rowHeight);

		dim.width += horizontalInsetsAndGap;
		dim.height += insets.top + insets.bottom + vgap * 2;

		// When using a scroll pane or the DecoratedLookAndFeel we need to
		// make sure the preferred size is less than the size of the
		// target containter so shrinking the container size works
		// correctly. Removing the horizontal gap is an easy way to do this.
		
		GComponent scrollPane = RADP.getAncestorOfClass(GScrollPane.class, target);

		if (scrollPane != null) {
			dim.width -= (hgap + 1);
		}

		return dim;
	}

	/*
	 * A new row has been completed. Use the dimensions of this row to update the
	 * preferred size for the container.
	 *
	 * @param dim update the width and height when appropriate
	 * 
	 * @param rowWidth the width of the row to add
	 * 
	 * @param rowHeight the height of the row to add
	 */
	private void addRow(GDimension dim, int rowWidth, int rowHeight) {
		dim.width = Math.max(dim.width, rowWidth);

		if (dim.height > 0) {
			dim.height += getVgap();
		}

		dim.height += rowHeight;
	}
}