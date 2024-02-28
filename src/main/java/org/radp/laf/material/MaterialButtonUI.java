package org.radp.laf.material;

import org.radp.RADP;

import de.exware.gplatform.GPStyle;
import de.exware.gwtswing.awt.GColor;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.event.GMouseAdapter;
import de.exware.gwtswing.awt.event.GMouseEvent;
import de.exware.gwtswing.awt.event.GMouseListener;
import de.exware.gwtswing.swing.GButton;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GUtilities;
import de.exware.gwtswing.swing.border.GBorder;
import de.exware.gwtswing.swing.plaf.ComponentUI;

public class MaterialButtonUI extends ComponentUI {
	public static final String MATERIAL_MODE = "MATERIAL_MODE";
	public static final String MATERIAL_CONTAINED = "MATERIAL_CONTAINED";
	public static final String MATERIAL_OUTLINED = "MATERIAL_OUTLINED";
	public static final String MATERIAL_TEXT = "MATERIAL_TEXT";

	// this ui needs a new instance for every button
	// to support a different color on each button
	static public ComponentUI createUI(GComponent component) {
		return new MaterialButtonUI();
	}

	protected MaterialButtonUI() {
	}

	private GMouseListener mouseListener;

	@Override
	public void installUI(GComponent component) {
		String mode = (String) component.getClientProperty(MATERIAL_MODE);
		
		if(mode == null) {
			mode = MATERIAL_OUTLINED;
		}
		
		GBorder border = null;
		switch (mode) {
		case MATERIAL_CONTAINED:
			border = new MaterialShadowBorder(3, 2, 1);
			break;

		case MATERIAL_OUTLINED:
			border = new MaterialBorder(2, 1);
			break;

		case MATERIAL_TEXT:
			border = new MaterialBorder(2, 0);
			break;

		default:
			border = new MaterialBorder(2, 1);
		}

		GColor backgroundColor = component.getBackground();
		GColor backgroundColorMouseOver = GColor.brighter(backgroundColor, 10);
		GColor backgroundColorActive = GColor.brighter(backgroundColorMouseOver, 10);

		component.setBorder(border);

		mouseListener = new GMouseAdapter() {
			@Override
			public void mouseExited(GMouseEvent evt) {
				super.mouseExited(evt);
				// reset button
				component.setBackground(backgroundColor);
			}

			@Override
			public void mouseEntered(GMouseEvent evt) {
				super.mouseEntered(evt);
				// elevate button
				component.setBackground(backgroundColorMouseOver);
			}

			@Override
			public void mousePressed(GMouseEvent evt) {
				super.mousePressed(evt);
				// mouse active button
				component.setBackground(backgroundColorActive);
			}

			@Override
			public void mouseReleased(GMouseEvent evt) {
				super.mouseReleased(evt);
				component.setBackground(backgroundColorMouseOver);
			}
		};

		component.addMouseListener(mouseListener);

		// assume it is a button and
		// take text and upper case it
		GButton button = (GButton) component;
		button.setText(button.getText().toUpperCase());

		GPStyle peerStyle = component.getPeer().getStyle();
		peerStyle.setDisplay("flex");
		peerStyle.setProperty("justifyContent", "center");
		peerStyle.setProperty("alignItems", "center");

		GDimension textDimension = RADP.getTextDimension(button.getText());

		component.setPreferredSize(new GDimension(textDimension.width + 30, textDimension.height + 30));
	}

	@Override
	public void uninstallUI(GComponent component) {
		component.removeMouseListener(mouseListener);

	}

}
