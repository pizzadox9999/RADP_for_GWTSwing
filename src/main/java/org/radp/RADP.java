package org.radp;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.radp.event.ResizeEvent;
import org.radp.event.ResizeEventListener;
import org.radp.layout.VerticalFlowLayout;
import org.teavm.jso.JSBody;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.EventListener;

import de.exware.gplatform.GPElement;
import de.exware.gplatform.GPlatform;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GLayoutManager;
import de.exware.gwtswing.awt.GToolkit;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GLabel;
import de.exware.gwtswing.swing.GPanel;
import de.exware.gwtswing.swing.GUtilities;

/**
 * Application sets up the views and controllers
 */
public abstract class RADP {
	public static final int TEXT_FIELD_LENGTH = 30;
	private static GDimension size;
	private static ArrayList<ResizeEventListener> resizeEventListeners = new ArrayList<>();

	public static void init() {
		// setup resizing events
		size = GToolkit.getDefaultToolkit().getScreenSize();
		// setup resizeEventListeners
		Window.current().addEventListener("resize", new EventListener<Event>() {
			@Override
			public void handleEvent(Event evt) {
				GDimension newSize = GToolkit.getDefaultToolkit().getScreenSize();
				ResizeEvent resizeEvent = new ResizeEvent(size, newSize);
				for (ResizeEventListener resizeEventListener : resizeEventListeners) {
					resizeEventListener.resized(resizeEvent);
				}
				size = newSize;
			}
		});
	}

	public static GComponent createSpacer() {
		return createSpacer(1);
	}

	public static GComponent createSpacer(int length) {
		class Spacer extends GLabel {
			public Spacer() {
				StringBuilder space = new StringBuilder();
				for (int i = 0; i < length; i++) {
					space.append("&nbsp;");
				}
				setText(space.toString());
			}
		}
		return new Spacer();
	}

	public static GPanel wrapComponentsInPanel(GComponent... component) {
		return wrapComponentsInPanel(new VerticalFlowLayout(), component);
	}
	
	public static GPanel wrapComponentsInPanel(GLayoutManager layoutManager, GComponent... component) {
		GPanel panel = new GPanel(layoutManager);
		for (GComponent c : component) {
			panel.add(c);
		}
		return panel;
	}

	public static String getLanguage() {
		String lang = native_getBrowserLanguage();
		if (lang.contains("-")) {
			lang = lang.toLowerCase().substring(0, 2);
		}
		return lang;
	}

	public static String getHostAdress() {
		String hostAdress = native_getHostAdress();
		return hostAdress;
	}

	public static void addResizeEventListener(ResizeEventListener resizeEventListener) {
		resizeEventListeners.add(resizeEventListener);
	}

	public static void removeResizeEventListener(ResizeEventListener resizeEventListener) {
		resizeEventListeners.remove(resizeEventListener);
	}

	public static GDimension getTextDimension(String text) {
		GPElement textElement = GPlatform.getDoc().createDivElement();
		textElement.setInnerHTML(text);
		GUtilities.getMeasureElement().appendChild(textElement);
		GDimension dimension = new GDimension(textElement.getOffsetWidth(), textElement.getOffsetHeight());
		GUtilities.getMeasureElement().removeChild(textElement);
		return dimension;
	}

	private static String localizedDateFormatPattern = null;

	public static String getLocalizedDateFormatPattern() {
		if (localizedDateFormatPattern == null) {
			switch (getLanguage()) {
			case "de":
				localizedDateFormatPattern = "dd.MM.yyyy HH:mm:ss";
				break;
			case "en":
				localizedDateFormatPattern = "yyyy.MM.dd G 'at' HH:mm:ss z";
				break;
			}
		}
		return localizedDateFormatPattern;
	}

	public static GComponent getAncestorOfClass(Class<?> c, GComponent comp) {
		if (comp == null || c == null)
			return null;

		GComponent parent = comp.getParent();
		while (parent != null && !(c.isInstance(parent)))
			parent = parent.getParent();
		return parent;
	}

	public static <T> T checkNullWithDefault(Object object, T onNull) {
		if (object == null) {
			return onNull;
		} else {
			return (T) object;
		}
	}

	public static void walkComponentTree(GComponent c, Consumer<GComponent> consumer) {
		if (c != null) {
			consumer.accept(c);
			for (GComponent component : c.getComponents()) {
				walkComponentTree(component, consumer);
			}
		}
	}
	
	public static void executeAsynchron(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.start();
	}
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void sleep(int seconds) {
		sleep(((long) seconds * 1000));
	}

	/******** NATIVE ********/
	@JSBody(params = {}, script = "return navigator.language || navigator.userLanguage;")
	private static native String native_getBrowserLanguage();

	@JSBody(params = {}, script = "return location.host;")
	private static native String native_getHostAdress();
}