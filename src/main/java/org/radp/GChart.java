package org.radp;

import java.io.IOException;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.json.JSON;

import de.exware.gplatform.GPlatform;
import de.exware.gplatform.element.GPCanvasElement;
import de.exware.gplatform.internal.Ajax;
import de.exware.gplatform.teavm.TeavmGPElement;
import de.exware.gwtswing.swing.GComponent;

public class GChart extends GComponent {	
	private JSObject peer;
	private GPCanvasElement canvasElement;
	
	public GChart() {
		canvasElement =  GPlatform.getDoc().createCanvasElement();
		getPeer().appendChild(canvasElement);
		
		new Thread(new Runnable() {
		    String json = "nodata";
            @Override
            public void run() {
                try {
                    json = Ajax.POST("http://127.0.0.1:8080/MemberStatisticsService/api/statistic/chart/userpopulation", "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("jsondata: " + json);
                
                JSObject sample = JSON.parse(json);
                peer = native_createPeer(((TeavmGPElement) canvasElement).getNativeElement(), sample);
            }
        }).start();
	}
	
	
	/********* NATIVE ***********/
	@JSBody(params = {"canvas", "settings"}, script = "new Chart(canvas, settings);")
	private static native JSObject native_createPeer(JSObject canvas, JSObject settings);
}