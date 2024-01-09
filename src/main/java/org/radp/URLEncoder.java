package org.radp;

public class URLEncoder {
	private StringBuilder encodedData = new StringBuilder();
	private boolean first = true;
	
	public URLEncoder() {}
	
	public URLEncoder add(String parameter, String data) {
		if(first) {
			encodedData.append(parameter + "=" + data);
			first = false;
		} else {
			encodedData.append("&" + parameter + "=" + data);
		}
		return this;
	}
	
	public String build() {
		return encodedData.toString();
	}
	
	public static URLEncoder getInstance() {
		return new URLEncoder();
	}
}