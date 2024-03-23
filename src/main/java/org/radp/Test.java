package org.radp;

import org.radp.laf.material.MaterialLookAndFeel;
import org.radp.test.ChatApplication;

import de.exware.gplatform.teavm.TeavmGPlatform;
import de.exware.gwtswing.swing.GUIManager;


public class Test {
	public static void main(String[] args) {		
		TeavmGPlatform.init();
		GUIManager.setLookAndFeel(new MaterialLookAndFeel());
		
		ChatApplication chatApplication = new ChatApplication();
		chatApplication.start();
	}
}
