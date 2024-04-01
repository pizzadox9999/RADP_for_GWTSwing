package org.radp.test;

import org.radp.application.View;
import org.radp.application.ViewManager;
import org.radp.application.WindowSizeClasses;

public class MaterialViewManager implements ViewManager {
	private CompactMaterialView compactMaterialView;
	private ExpandedMaterialView expandedMaterialView;
	private MediumMaterialView mediumMaterialView;
	private LargeMaterialView largeMaterialView;
	private ExtraLargeMaterialView extraLargeView;
	
	
	public MaterialViewManager() {
		compactMaterialView = new CompactMaterialView();
		mediumMaterialView = new MediumMaterialView();
		expandedMaterialView = new ExpandedMaterialView();
		largeMaterialView = new LargeMaterialView();
		extraLargeView = new ExtraLargeMaterialView();
		
		compactMaterialView.init();
		mediumMaterialView.init();
		expandedMaterialView.init();
		largeMaterialView.init();
		extraLargeView.init();
	}
	
	@Override
	public View determineView(WindowSizeClasses windowSizeClass) {
		switch (windowSizeClass) {
		case COMPACT:
			return compactMaterialView;
			
		case MEDIUM:
			return mediumMaterialView;
			
		case EXPANED:
			return expandedMaterialView;
			
		case LARGE:
			return largeMaterialView;
			
		case EXTRA_LARGE:
			return extraLargeView;
		}
		
		throw new RuntimeException("No view");
	}

}
