package org.radp.test;

import java.util.function.Consumer;

import org.radp.RADP;
import org.radp.application.View;
import org.radp.layout.VerticalFlowLayout;

import de.exware.gwtswing.awt.GBorderLayout;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GLabel;
import de.exware.gwtswing.swing.GPanel;
import de.exware.gwtswing.swing.GSwingUtilities;
import de.exware.gwtswing.swing.GUtilities;

public class CompactMaterialView implements View {
	private ComponentStore store = ComponentStore.getInstance();
	private GComponent component;
	
	@Override
	public void init() {
		component = new GPanel(new GBorderLayout());
	}

	@Override
	public void activated() {
		ChatSelectionArea chatSelection = (ChatSelectionArea) store.get(ChatApplication.CHAT_SELECTION);
		chatSelection.setSize(component.getSize());
		
		component.add(chatSelection, GBorderLayout.CENTER);
		//component.revalidate();
		
		RADP.walkComponentTree(component, new Consumer<GComponent>() {
			@Override
			public void accept(GComponent t) {
				t.validate();
			}
		});
		
		System.out.println("layout should be validated and relayout");
	}

	@Override
	public void deactived() {
		component.removeAll();
	}

	@Override
	public void resized(GDimension windowSize) {
		
	}

	@Override
	public GComponent getComponent() {
		return component;
	}

}
