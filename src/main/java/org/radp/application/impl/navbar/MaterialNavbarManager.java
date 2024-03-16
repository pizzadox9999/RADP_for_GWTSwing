package org.radp.application.impl.navbar;

import org.radp.application.Navbar;
import org.radp.application.NavbarManager;
import org.radp.application.WindowSizeClasses;

public class MaterialNavbarManager implements NavbarManager {

	@Override
	public Navbar determineNavbar(WindowSizeClasses windowSizeClass) {
		Navbar navbar = null;
		
		switch (windowSizeClass) {
		case COMPACT:
			navbar = new MaterialCompactNavbar();
			break;
		case MEDIUM:
			navbar = new MaterialMediumNavbar();
			break;
		case EXPANED:
			navbar = new MaterialExpanedNavbar();
			break;
		case LARGE:
		case EXTRA_LARGE:
			navbar = new MaterialLargeNavbar();
			break;
		}
		
		return navbar;
	}

}
