package org.radp.test;

public class Message {
	public final String message;
	public final long timeStamp;
	public final String from;
	public final Comparable<Message> timeComparable;
	
	public Message(String message, long timestamp, String from) {
		this.message = message;
		this.timeStamp = timestamp;
		this.from = from;
		timeComparable = new Comparable<Message>() {
			@Override
			public int compareTo(Message o) {
				return Long.compare(timeStamp, o.timeStamp);
			}
		};
	}
}
