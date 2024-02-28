package org.radp;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import org.radp.event.ResizeEvent;
import org.radp.event.ResizeEventListener;
import org.radp.laf.material.MaterialButtonUI;
import org.radp.laf.material.MaterialColor;
import org.radp.laf.material.MaterialIcons;
import org.radp.laf.material.MaterialLookAndFeel;
import org.radp.layout.GFlowLayout;
import org.radp.layout.WrapGFlowLayout;
import org.radp.layout.slick.SlickConstraint;
import org.radp.layout.slick.SlickLayout;

import de.exware.gplatform.teavm.TeavmGPlatform;
import de.exware.gwtswing.animation.FadeInAnimation;
import de.exware.gwtswing.animation.Animation.TriggerEvent;
import de.exware.gwtswing.awt.GColor;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GToolkit;
import de.exware.gwtswing.awt.event.GActionEvent;
import de.exware.gwtswing.awt.event.GActionListener;
import de.exware.gwtswing.swing.GButton;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GFrame;
import de.exware.gwtswing.swing.GImageIcon;
import de.exware.gwtswing.swing.GPanel;
import de.exware.gwtswing.swing.GUIManager;
import de.exware.gwtswing.swing.GUtilities;

public class Test {
	public static void main(String[] args) {		
		TeavmGPlatform.init();
		RADP.init();
		
		GUIManager.setLookAndFeel(new MaterialLookAndFeel());
		
		GFrame frame = new GFrame();
		
		System.out.println("is frame assignable from component: " + frame.getClass().isAssignableFrom(GComponent.class));
		
		RADP.addResizeEventListener(new ResizeEventListener() {
			@Override
			public void resized(ResizeEvent resizeEvent) {
				frame.setSize(resizeEvent.newDimension);
				frame.revalidate();
			}
		});
		
		frame.setSize(GToolkit.getDefaultToolkit().getScreenSize());
		frame.setLayout(new SlickLayout());
		
		GPanel headerPanel = new GPanel(new GFlowLayout(GFlowLayout.LEFT));
		headerPanel.setPreferredSize(new GDimension(50, 50));
		headerPanel.setBackground(GColor.BLUE);
		
		GButton navButton = new GButton(new GImageIcon(MaterialIcons.BURGER_MENU_SVG, 50, 50));
		navButton.putClientProperty(MaterialButtonUI.MATERIAL_MODE, MaterialButtonUI.MATERIAL_TEXT);
		navButton.setBackground(new GColor(0, 0, 0, 0));
		navButton.getPeer().getStyle().setPadding(5);
		headerPanel.add(navButton);
		
		
		GPanel navPanel = new GPanel();
		navPanel.setPreferredSize(new GDimension(70, 70));
		navPanel.setBackground(GColor.YELLOW);
		navPanel.getPeer().getStyle().setProperty("transition", "width 1s ease-in-out");
		
		GPanel contentPanel = new GPanel();
		contentPanel.setLayout(new WrapGFlowLayout(GFlowLayout.CENTER));
		contentPanel.setPreferredSize(new GDimension(70, 70));
		contentPanel.setBackground(GColor.WHITE);
		contentPanel.getPeer().getStyle().setProperty("overflowY", "auto");
		
		Random random = new Random();
		GPanel box1 = new GPanel();
		box1.setPreferredSize(new GDimension(100, 100));
		box1.setBackground(new GColor(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		GPanel box2 = new GPanel();
		box2.setPreferredSize(new GDimension(100, 100));
		box2.setBackground(new GColor(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		GPanel box3 = new GPanel();
		box3.setPreferredSize(new GDimension(100, 100));
		box3.setBackground(new GColor(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		GPanel box4 = new GPanel();
		box4.setPreferredSize(new GDimension(100, 100));
		box4.setBackground(new GColor(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		
		FadeInAnimation fadeInAnimation = new FadeInAnimation(TriggerEvent.VISIBILITY, 1f);
		fadeInAnimation.install(box4);
		
		AtomicBoolean isShowing = new AtomicBoolean(true);
		GButton button = new GButton("testButton");
		button.putClientProperty(MaterialButtonUI.MATERIAL_MODE, MaterialButtonUI.MATERIAL_OUTLINED);
		button.setBackground(MaterialColor.BLUE_DEEP_OCEAN);
		button.setForeground(GColor.WHITE);
		button.addActionListener(new GActionListener() {
			@Override
			public void actionPerformed(GActionEvent evt) {
				isShowing.set(!isShowing.get());
				box4.setVisible(isShowing.get());
				
				if(!isShowing.get()) {
					navPanel.setPreferredSize(new GDimension(0, 70));
				} else {
					navPanel.setPreferredSize(new GDimension(70, 70));
				}
				
				frame.revalidate();
			}
		});
		
		contentPanel.add(box1);
		contentPanel.add(box2);
		contentPanel.add(box3);
		contentPanel.add(box4);
		
		for(int i=0; i < 30; i++) {
			GPanel box = new GPanel();
			box.setPreferredSize(new GDimension(100, 100));
			box.setBackground(new GColor(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
			contentPanel.add(box);
		}
		
		contentPanel.add(button);
		
		frame.add(headerPanel, new SlickConstraint(0, SlickConstraint.HorizontalFill, SlickConstraint.VerticalPack));
		frame.add(navPanel, new SlickConstraint(1, SlickConstraint.HorizontalPack, SlickConstraint.VerticalFill));
		frame.add(contentPanel, new SlickConstraint(1, SlickConstraint.HorizontalFill, SlickConstraint.VerticalFill));
		
		
		frame.show();
		
		
		//GSwingUtilities.updateComponentTreeUI(frame);
		
		frame.validate();
	}
}
