package org.radp.application;

public class WindowSizeClass {
	public final String id;
	
	public WindowSizeClass(String id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
            return true;
        }
		
        if(object instanceof WindowSizeClass) {
        	WindowSizeClass windowSizeClass = (WindowSizeClass) object;
        	return id.equals(windowSizeClass.id);
        }
		
		return false;
	}
}
