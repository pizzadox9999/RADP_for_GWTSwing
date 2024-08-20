package org.radp;

import org.radp.component.GBox;
import org.radp.component.material.Circle;

import de.exware.gplatform.teavm.TeavmGPlatform;
import de.exware.gwtswing.awt.GColor;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GFrame;
import de.exware.gwtswing.swing.GSwingUtilities;

public class Test {
	public static void main(String[] args) {
		TeavmGPlatform.init();
		GSwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				test();
			}
		});
	}
	
	static void test() {
		GFrame frame = new GFrame();
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.show();
		
		GComponent box1 = GBox.createRigidArea(new GDimension(50, 50));
		box1.setSize(50, 50);
		box1.setBackground(GColor.RED);
		box1.setLocation(100, 100);
		GComponent box2 = GBox.createRigidArea(new GDimension(50, 50));
		box2.setSize(50, 50);
		box2.setBackground(GColor.BLUE);
		box2.setLocation(120, 120);
		
		frame.add(box1);
		frame.add(box2);
		
		GComponent circle = Circle.createCircle(300);
		frame.add(circle);
	}
}
