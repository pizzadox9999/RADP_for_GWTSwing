package org.radp;

import org.radp.test.ChatApplication;

import de.exware.gplatform.teavm.TeavmGPlatform;


public class Test {
	public static void main(String[] args) {
		TeavmGPlatform.init();
		ChatApplication chatApplication = new ChatApplication();
		chatApplication.start();
	}
}
