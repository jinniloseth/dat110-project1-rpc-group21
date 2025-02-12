package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = null;
		
		// TODO - START
		
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format
		
		rpcmsg = new byte[payload.length + 1];
		
		rpcmsg[0] = rpcid;
		
		System.arraycopy(payload, 0, rpcmsg, 1, payload.length);

		
		// TODO - END
		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = null;
		
		// TODO - START
		
		// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax
		
		payload = new byte[rpcmsg.length-1];
		
		System.arraycopy(rpcmsg, 1, payload, 0, payload.length);

		
		// TODO - END
		
		return payload;
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		// TODO - START 
	
		byte[] data = str.getBytes();
		int length = data.length;
		
		System.out.println("byte størrelse: " + length);
		System.out.println("faktisk lengde: " + str.length());
		
		byte[] encoded = new byte[length + 1];
		
		encoded[0] = (byte) length;
		
		System.arraycopy(data, 0, encoded, 1, length);
		
		// TODO - END
		
		return encoded;
	}

//	// convert byte array to a String
//	public static String unmarshallString(byte[] data) {
//		
//		// TODO - START 
//		
//		int length = data[0];
//	   
//		byte[] strBytes = new byte[length];
//		
//	    
//	    System.arraycopy(data, 1, strBytes, 0, length);  
//
//		// TODO - END
//	    
//	    return new String(strBytes); 
//	}
	
	public static String unmarshallString(byte[] data) {
	    if (data == null || data.length < 1) {
	        throw new IllegalArgumentException("Invalid string message: empty or null");
	    }

	    int length = data[0];
	    
	    System.out.println("DEBUG: Expected length: " + length + ", Actual data length: " + data.length);

	    if (length > data.length - 1) {
	        throw new IllegalArgumentException("Invalid string length: " + length + " for data size: " + data.length + " | Data: " + Arrays.toString(data));
	    }

	    byte[] strBytes = new byte[length];
	    System.arraycopy(data, 1, strBytes, 0, length);

	    return new String(strBytes);
	}


	
	
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = null;
		
		// TODO - START 
		
		encoded = new byte[1];
		
		encoded[0] = 0;
		
		// TODO - END
		
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		//TODO
		
	    System.out.println("Received void message: " + Arrays.toString(data)); // Debugging output

	    if (data == null || data.length != 1 || data[0] != 0) {
	        
	    }
	}


	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = null;
		
		// TODO - START 
		
		ByteBuffer buffer = ByteBuffer.allocate(x);
		
		buffer.putInt(x);
		
		encoded = buffer.array();
		
		// TODO - END
		
		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;
		
		// TODO - START 
	
		ByteBuffer buffer = ByteBuffer.wrap(data);
		
		decoded = buffer.getInt();
	
		
		// TODO - END
		
		return decoded;
		
	}
}
