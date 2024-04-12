package org.radp.event;

import java.util.List;

import de.exware.gplatform.event.GPEvent;
import de.exware.gplatform.event.GPTouch;

public class ConfigurableGPEvent implements GPEvent {
	public Type type = null;
	public boolean shiftKey = false;
	public boolean altKey = false;
	public boolean controlKey = false;
	public int clientX = 0;
	public int clientY = 0;
	public Button button = null;
	public int keyCode = 0;
	public int charCode = 0;

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void preventDefault() {
	}

	@Override
	public void stopPropagation() {
	}

	@Override
	public boolean getShiftKey() {
		return shiftKey;
	}

	@Override
	public boolean getAltKey() {
		return altKey;
	}

	@Override
	public boolean getCtrlKey() {
		return controlKey;
	}

	@Override
	public int getClientX() {
		return clientX;
	}

	@Override
	public int getClientY() {
		return clientY;
	}

	@Override
	public Button getButton() {
		return button;
	}

	@Override
	public int getKeyCode() {
		return keyCode;
	}

	@Override
	public int getCharCode() {
		return charCode;
	}

	@Override
	public int getMouseWheelVelocityY() {
		return 0;
	}

	@Override
	public List<GPTouch> getTouches() {
		return null;
	}

	@Override
	public List<GPTouch> getChangedTouches() {
		return null;
	}

}
