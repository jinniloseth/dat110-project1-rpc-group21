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
		
		byte[] encoded;
		
		// TODO - START 
		
		encoded = new byte[str.length()];
		
		for(int i = 0; i < str.length(); i++) {
			encoded[i] = (byte) str.charAt(i);
		}
		
		// TODO - END
		
		return encoded;
	}


	public static String unmarshallString(byte[] data) {
		String decoded;
		
	    decoded = new String(Arrays.copyOfRange(data, 0, data.length));

	    // Convert the byte array into a string
	    return decoded;
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
