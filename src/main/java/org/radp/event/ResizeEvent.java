package org.radp.event;

import de.exware.gwtswing.awt.GDimension;

public class ResizeEvent {
    public final GDimension oldDimension;
    public final GDimension newDimension;
    public ResizeEvent(GDimension oldDimension, GDimension newDimension) {
        this.oldDimension = oldDimension;
        this.newDimension = newDimension;
    }
}