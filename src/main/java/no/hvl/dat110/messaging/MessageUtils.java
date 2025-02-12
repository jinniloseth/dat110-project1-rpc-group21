package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8081;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = null;
		byte[] data;
		
		// TODO - START
		
		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer
		
		data = message.getData();
		
		 if (data == null) {
		        throw new IllegalArgumentException("Message data cannot be null");
		 }
		
		segment = new byte[SEGMENTSIZE];
		
		segment[0] = (byte) message.getData().length;
		
		System.arraycopy(data, 0, segment, 1, data.length);
		
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {
		
		Message message = null;
	    
	    if (segment == null) {
	        throw new IllegalArgumentException("Segment cannot be null");
	    }

	    int length = segment[0];

	    byte[] payload = new byte[length];

	    System.arraycopy(segment, 1, payload, 0, length);
	    
	    message = new Message(payload);

	    return message;
	}
	



	
}
