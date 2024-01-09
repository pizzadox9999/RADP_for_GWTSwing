package org.radp;

import de.exware.gplatform.teavm.TeavmGPlatform;
import de.exware.gwtswing.awt.event.GActionEvent;
import de.exware.gwtswing.awt.event.GMouseAdapter;
import de.exware.gwtswing.awt.event.GMouseEvent;
import de.exware.gwtswing.swing.GAbstractAction;
import de.exware.gwtswing.swing.GDialog;
import de.exware.gwtswing.swing.GFrame;
import de.exware.gwtswing.swing.GOptionPane;
import de.exware.gwtswing.swing.GPopupMenu;
import de.exware.gwtswing.swing.GUtilities;

public class PopupMenuTest extends GFrame {
    public PopupMenuTest() {
        setName("Popup test");
        setSize(800, 600);        
        show();
        
        GPopupMenu popupMenu = new GPopupMenu();
        
        popupMenu.add(new GAbstractAction("test popupAction") {
            @Override
            public void actionPerformed(GActionEvent evt) {
                super.actionPerformed(evt);
                System.out.println("action performed");
            }
        });
        
        addMouseListener(new GMouseAdapter() {
            @Override
            public void mouseClicked(GMouseEvent evt) {
                super.mouseClicked(evt);
                if(evt.getButton() == GMouseEvent.BUTTON1) {
                  GDialog dialog = new GDialog(PopupMenuTest.this, "myModalDialog", true);
                  dialog.setSize(300, 300);
                  dialog.show();
                } else if(evt.getButton() == GMouseEvent.BUTTON3) {
                    popupMenu.show(PopupMenuTest.this, evt.getX(), evt.getY());
                }
                
                System.out.println("popupMenu.isShowing(): " + popupMenu.isShowing());
                System.out.println("popupMenu.isVisible(): " + popupMenu.isVisible());
            }
        });
    }
    
    
    public static void main(String[] args) {
        TeavmGPlatform.init();
        GUtilities.addToBody(new PopupMenuTest());
    }
}
