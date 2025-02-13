package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.MessageUtils;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {
		
		// TODO - START
		
		// implement marshalling, call and unmarshalling for write RPC method

		byte[] data = RPCUtils.marshallString(message);
		
		byte[] dekapsulertVerdi = rpcclient.call((byte)Common.WRITE_RPCID,data);
		
		RPCUtils.unmarshallString(dekapsulertVerdi);
		
		// TODO - END
		
	}
}
