package org.radp.layout.slick;

/*
 *	MIT License
 *
 *	Copyright (c) 2017 Josh Simonot
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *	
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *	
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GInsets;
import de.exware.gwtswing.awt.GLayoutManager;
import de.exware.gwtswing.swing.GComponent;

/**
 * Java Swing Layout Manager
 * 
 * Mix horizontal/vertical scaling components with fixed size components in a
 * single layout.
 * 
 * Lays components out in rows.
 * 
 * @author Josh
 *
 */
public class SlickLayout implements GLayoutManager {
	private List<List<GComponent>> componentListByRow = new ArrayList<>();
	private Map<GComponent, SlickConstraint> constraintMap = new HashMap<>();

	@Override
	public void addLayoutComponent(String name, GComponent comp) {
		// not supported
		// throw exceptions??
	}

	@Override
	public void addLayoutComponent(GComponent comp, Object constraints) {
		SlickConstraint constr = (SlickConstraint) constraints;
		int row = Math.max(constr.row, 0);

		while (componentListByRow.size() <= row) {
			componentListByRow.add(new ArrayList<>());
		}
		constraintMap.put(comp, constr);
		componentListByRow.get(row).add(comp);
	}

	@Override
	public void removeLayoutComponent(GComponent comp) {
		SlickConstraint constr = constraintMap.get(comp);
		List<GComponent> row = componentListByRow.get(constr.row);

		row.remove(comp);
		constraintMap.remove(comp);
	}

	@Override
	public void layoutContainer(GComponent container) {
		GInsets insets = container.getInsets();
		int containerWidth = container.getSize().width - (insets.left + insets.right);
		int containerHeight = container.getSize().height - (insets.top + insets.bottom);

		int[] rowHeights = getRowHeights();
		boolean[] isVerticallyPacked = findVerticallyPackedRows();

		int packedHeight = 0;
		int filledHeight = 0;
		float totVerticalFillWeight = 0;

		for (int row = 0; row < componentListByRow.size(); ++row) {
			if (isVerticallyPacked[row]) {
				packedHeight += rowHeights[row];
			} else {
				List<GComponent> components = componentListByRow.get(row);
				if (components.size() > 0) {
					totVerticalFillWeight += constraintMap.get(components.get(0)).getFillWeightVertical();
				}
			}
		}
		filledHeight = containerHeight - packedHeight;

		// used for y position of components
		int cumulativeRowHeights = insets.top;

		for (int row = 0; row < componentListByRow.size(); ++row) {
			int packedWidth = 0;
			int filledWidth = 0;
			float totHorizontalFillWeight = 0;

			List<GComponent> components = componentListByRow.get(row);

			for (GComponent comp : components) {
				if (!comp.isVisible())
					continue;

				SlickConstraint constr = constraintMap.get(comp);

				if (constr.hSizing == SlickConstraint.HorizontalPack) {
					packedWidth += comp.getPreferredSize().width;
				} else
					totHorizontalFillWeight += constr.getFillWeightHorizontal();
			}
			filledWidth = containerWidth - packedWidth;

			int x = insets.left;
			for (GComponent comp : components) {
				SlickConstraint constr = constraintMap.get(comp);

				int compWidth = 0;
				int compHeight = 0;
				int y;

				if (constr.hSizing == SlickConstraint.HorizontalFill) {
					float fraction = constr.getFillWeightHorizontal() / totHorizontalFillWeight;
					float trueWidth = fraction * filledWidth;
					compWidth = (int) trueWidth;

					// fix: a row was sometimes missing a single pixel because of rounding:
					if (compWidth < trueWidth) {
						compWidth += 1;
					}
				} else
					compWidth = comp.getPreferredSize().width;

				if (constr.vSizing == SlickConstraint.VerticalFill) {
					y = cumulativeRowHeights;

					if (!isVerticallyPacked[row]) {
						float fraction = constr.getFillWeightVertical() / totVerticalFillWeight;
						float trueHeight = fraction * filledHeight;
						compHeight = (int) trueHeight;

						// fix: the layout was sometimes a single pixel short because of rounding:
						if (compHeight < trueHeight) {
							compHeight += 1;
						}
					} else
						compHeight = rowHeights[row];
				} else {
					compHeight = comp.getPreferredSize().height;
					float vertAlignment = constr.getAlignmentY();
					y = cumulativeRowHeights + (int) (vertAlignment * (rowHeights[row] - compHeight));
				}

				comp.setBounds(x, y, compWidth, compHeight);
				x += compWidth;
			}
			if (isVerticallyPacked[row] || components.size() == 0) {
				cumulativeRowHeights += rowHeights[row];
			} else {
				cumulativeRowHeights += components.get(0).getHeight();
			}
		}

	}

	private boolean[] findVerticallyPackedRows() {
		boolean[] isVerticallyPacked = new boolean[componentListByRow.size()];

		for (int row = 0; row < componentListByRow.size(); ++row) {
			boolean vpacked = false;

			for (GComponent comp : componentListByRow.get(row)) {
				if (!comp.isVisible())
					continue;

				SlickConstraint constr = constraintMap.get(comp);
				if (constr.vSizing == SlickConstraint.VerticalPack) {
					vpacked = true;
					break;
				}
			}
			isVerticallyPacked[row] = vpacked;
		}
		return isVerticallyPacked;
	}

	private int[] getRowHeights() {
		int[] rowHeights = new int[componentListByRow.size()];

		for (int row = 0; row < componentListByRow.size(); ++row) {
			boolean fillheight = true;
			List<GComponent> components = componentListByRow.get(row);

			for (GComponent comp : components) {
				if (!comp.isVisible())
					continue;

				SlickConstraint constr = constraintMap.get(comp);
				if (constr.vSizing == SlickConstraint.VerticalPack) {
					rowHeights[row] = Math.max(comp.getPreferredSize().height, rowHeights[row]);
					fillheight = false;
				} else // use the largest height of vertFill IFF no vertPack exist
				{
					if (fillheight) {
						rowHeights[row] = Math.max(comp.getPreferredSize().height, rowHeights[row]);
					}
				}
			}
		}
		return rowHeights;
	}

	private int[] getRowWidths() {
		int[] rowWidths = new int[componentListByRow.size()];

		for (int row = 0; row < componentListByRow.size(); ++row) {
			List<GComponent> components = componentListByRow.get(row);
			for (GComponent comp : components) {
				if (!comp.isVisible())
					continue;
				rowWidths[row] += comp.getPreferredSize().width;
			}
		}
		return rowWidths;
	}

	@Override
	public GDimension minimumLayoutSize(GComponent container) {
		return preferredLayoutSize(container);
	}

	@Override
	public GDimension preferredLayoutSize(GComponent container) {
		GInsets insets = container.getInsets();
		int longestRow = max(getRowWidths()) + insets.left + insets.right;
		int sumRowHeights = sum(getRowHeights()) + insets.top + insets.bottom;
		return new GDimension(longestRow, sumRowHeights);
	}

	private int sum(int[] array) {
		int sum = 0;
		for (int i : array) {
			sum += i;
		}
		return sum;
	}

	private int max(int[] array) {
		int max = 0;
		for (int i : array) {
			if (i > max)
				max = i;
		}
		return max;
	}
}
