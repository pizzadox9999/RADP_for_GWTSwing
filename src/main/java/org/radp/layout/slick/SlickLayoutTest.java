package org.radp.layout.slick;

import java.util.Random;

import de.exware.gwtswing.awt.GColor;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GRectangle;
import de.exware.gwtswing.swing.GFrame;
import de.exware.gwtswing.swing.GPanel;
import de.exware.gwtswing.swing.border.GBorderFactory;

public class SlickLayoutTest {

	public static boolean runTest() {
		boolean temp = true, pass = true;
		System.out.println("Test: SlickLayout");
		// ======================================
		pass &= (temp = layoutTest());
		System.out.println("      layoutTest: " + (temp ? "PASS" : "FAIL"));

		pass &= (temp = borderTest());
		System.out.println("      borderTest: " + (temp ? "PASS" : "FAIL"));
		// ======================================
		System.out.println("      > " + (pass ? "PASS" : "FAIL"));
		System.out.println();
		return pass;
	}

	/**
	 * 
	 * Tests
	 * 
	 */

	private static boolean borderTest() {
		GFrame frame = new GFrame();
		// frame.setDefaultCloseOperation(GFrame.EXIT_ON_CLOSE);

		GPanel contentPanel = new GPanel();
		contentPanel.setLayout(new SlickLayout());
		contentPanel.setBorder(GBorderFactory.createEmptyBorder(10, 10, 10, 10));

		GPanel comp1 = new RandomColorPanel(new GDimension(200, 200), "1");
		contentPanel.add(comp1, new SlickConstraint(0, SlickConstraint.HorizontalPack, SlickConstraint.VerticalPack));

		frame.add(contentPanel);
		frame.pack();
		// frame.setVisible(true);
		//frame.dispose();
		frame.show();

		boolean pass = true;

		// java.awt.Rectangle[x=62,y=0,width=236,height=259]
		GRectangle frameBounds = frame.getBounds();
		pass &= (frameBounds.width == 236) & (frameBounds.height == 259);

		// java.awt.Rectangle[x=0,y=0,width=220,height=220]
		GRectangle contentPanelBounds = contentPanel.getBounds();
		pass &= (contentPanelBounds.width == 220) & (contentPanelBounds.height == 220);
		pass &= (contentPanelBounds.x == 0) & (contentPanelBounds.y == 0);

		// java.awt.Rectangle[x=10,y=10,width=200,height=200]
		GRectangle comp1Bounds = comp1.getBounds();
		pass &= (comp1Bounds.width == 200) & (comp1Bounds.height == 200);
		pass &= (comp1Bounds.x == 10) & (comp1Bounds.y == 10);

		return pass;
	}

	private static boolean layoutTest() {
		GPanel comp1 = new RandomColorPanel(new GDimension(100, 36), "1");
		GPanel comp2 = new RandomColorPanel(new GDimension(100, 36), "2");
		GPanel comp3 = new RandomColorPanel(new GDimension(100, 36), "3");

		GPanel comp4 = new RandomColorPanel(new GDimension(100, 100), "4");
		GPanel comp5 = new RandomColorPanel(new GDimension(100, 100), "5");
		GPanel comp6 = new RandomColorPanel(new GDimension(100, 100), "6");

		GPanel comp7 = new RandomColorPanel(new GDimension(100, 175), "7");
		GPanel comp8 = new RandomColorPanel(new GDimension(600, 100), "8");
		GPanel comp9 = new RandomColorPanel(new GDimension(100, 138), "9");

		GPanel comp10 = new RandomColorPanel(new GDimension(100, 100), "10");
		GPanel comp11 = new RandomColorPanel(new GDimension(100, 100), "11");

		GPanel comp12 = new RandomColorPanel(new GDimension(100, 100), "12");
		GPanel comp13 = new RandomColorPanel(new GDimension(200, 100), "13");

		GFrame frame = new GFrame();
		frame.setTitle("Layout Test");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GFrame contentPanel = new GFrame();
		contentPanel.setLayout(new SlickLayout());

		frame.add(contentPanel);

		contentPanel.add(comp1, new SlickConstraint(0, SlickConstraint.HorizontalFill, SlickConstraint.VerticalPack));
		contentPanel.add(comp2, new SlickConstraint(5, SlickConstraint.HorizontalFill, SlickConstraint.VerticalPack));
		contentPanel.add(comp3, new SlickConstraint(5, SlickConstraint.HorizontalFill, SlickConstraint.VerticalPack));

		contentPanel.add(comp4, new SlickConstraint(1, SlickConstraint.HorizontalFill, SlickConstraint.VerticalFill));
		contentPanel.add(comp5, new SlickConstraint(1, SlickConstraint.HorizontalPack, SlickConstraint.VerticalFill));
		contentPanel.add(comp6, new SlickConstraint(1, SlickConstraint.HorizontalFill, SlickConstraint.VerticalPack));

		contentPanel.add(comp7, new SlickConstraint(2, SlickConstraint.HorizontalPack, SlickConstraint.VerticalPack));
		contentPanel.add(comp8, new SlickConstraint(2, SlickConstraint.HorizontalFill, SlickConstraint.VerticalPack)
				.setAlignmentY(0.5f));
		contentPanel.add(comp9, new SlickConstraint(2, SlickConstraint.HorizontalPack, SlickConstraint.VerticalPack)
				.setAlignmentY(1.0f));

		contentPanel.add(comp10, new SlickConstraint(4, SlickConstraint.HorizontalPack, SlickConstraint.VerticalFill));
		contentPanel.add(comp11, new SlickConstraint(4, SlickConstraint.HorizontalFill, SlickConstraint.VerticalFill));

		contentPanel.add(comp12, new SlickConstraint(3, SlickConstraint.HorizontalFill, SlickConstraint.VerticalFill));
		contentPanel.add(comp13, new SlickConstraint(3, SlickConstraint.HorizontalPack, SlickConstraint.VerticalFill));

		frame.pack();
		frame.show();

		// expected results:
		String c1 = "1[x=0,y=0,width=800,height=36]";
		String c2 = "2[x=0,y=511,width=400,height=36]";
		String c3 = "3[x=400,y=511,width=400,height=36]";
		String c4 = "4[x=0,y=36,width=350,height=100]";
		String c5 = "5[x=350,y=36,width=100,height=100]";
		String c6 = "6[x=450,y=36,width=350,height=100]";
		String c7 = "7[x=0,y=136,width=100,height=175]";
		String c8 = "8[x=100,y=173,width=600,height=100]";
		String c9 = "9[x=700,y=173,width=100,height=138]";
		String c10 = "10[x=0,y=411,width=100,height=100]";
		String c11 = "11[x=100,y=411,width=700,height=100]";
		String c12 = "12[x=0,y=311,width=600,height=100]";
		String c13 = "13[x=600,y=311,width=200,height=100]";

		boolean pass = true;
		pass &= c1.equals(comp1.toString());
		pass &= c2.equals(comp2.toString());
		pass &= c3.equals(comp3.toString());
		pass &= c4.equals(comp4.toString());
		pass &= c5.equals(comp5.toString());
		pass &= c6.equals(comp6.toString());
		pass &= c7.equals(comp7.toString());
		pass &= c8.equals(comp8.toString());
		pass &= c9.equals(comp9.toString());
		pass &= c10.equals(comp10.toString());
		pass &= c11.equals(comp11.toString());
		pass &= c12.equals(comp12.toString());
		pass &= c13.equals(comp13.toString());

		// frame.setVisible(true);
		//frame.dispose();
		return pass;
	}

	/**
	 * 
	 * Helpers
	 *
	 */

	@SuppressWarnings("serial")
	static class RandomColorPanel extends GPanel {
		static final Random random = new Random();

		private final String name;

		public RandomColorPanel(GDimension dims, String name) {
			final float saturation = (random.nextInt(2000) + 3000) / 10000f;
			final float luminance = 0.9f;
			final float hue = random.nextFloat();
			final GColor color = new GColor(random.nextInt(256), random.nextInt(256), random.nextInt(256));// GColor.getHSBColor(hue,
																											// saturation,
																											// luminance);

			this.name = name;
			this.setBackground(color);
			this.setPreferredSize(dims);
		}

		public String toString() {
			GRectangle bounds = this.getBounds();
			return name + "[x=" + bounds.x + ",y=" + bounds.y + ",width=" + bounds.width + ",height=" + bounds.height
					+ "]";
		}

		/*
		 * public void paintComponent(Graphics g) { super.paintComponent(g);
		 * 
		 * FontMetrics fm = g.getFontMetrics(); int strw = fm.stringWidth(name); int
		 * strh = fm.getHeight(); g.drawString(name, (getSize().width - strw) / 2,
		 * (getSize().height + strh) / 2); }
		 */
	}

}