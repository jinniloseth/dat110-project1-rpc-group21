package no.hvl.dat110.rpc;

import java.util.HashMap;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private MessageConnection connection;
	
	// hashmap to register RPC methods which are required to extend RPCRemoteImpl
	// the key in the hashmap is the RPC identifier of the method
	private HashMap<Byte,RPCRemoteImpl> services;
	
	public RPCServer(int port) {
		
		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Byte,RPCRemoteImpl>();
		
	}
	
	public void run() {
		
		// the stop RPC method is built into the server
		RPCRemoteImpl rpcstop = new RPCServerStopImpl(RPCCommon.RPIDSTOP,this);
		register(RPCCommon.RPIDSTOP, rpcstop);
		
		System.out.println("RPC SERVER RUN - Services: " + services.size());
			
		connection = msgserver.accept(); 
		
		System.out.println("RPC SERVER ACCEPTED");
		
		boolean stop = false;
		
		while (!stop) {
	    
		   byte rpcid = 0;
		   Message requestmsg, replymsg;
		   
		   // TODO - START
		   // - receive a Message containing an RPC request
		   // - extract the identifier for the RPC method to be invoked from the RPC request
		   // - lookup the method to be invoked
		   // - invoke the method
		   // - send back the message containing RPC reply
			
		   requestmsg = connection.receive();
		   
		   byte[] requestdata = requestmsg.getData();
		   
		   rpcid = requestdata[0];  

		   RPCRemoteImpl method = services.get(rpcid);

		   byte[] rpcreply;
		   
		   if (method == null) {
	            System.out.println("RPC method not found: " + rpcid);
	            rpcreply = new byte[0];
	        } else {
	        	
	        	//decapsulate
	        	byte[] params = RPCUtils.decapsulate(requestdata);
	        	
	        	//execute method
	        	byte[] returnData = method.invoke(params);

	        	//encapsulate
	        	rpcreply = RPCUtils.encapsulate(rpcid, returnData);
	        	
	        }

		   replymsg = new Message(rpcreply);
		   
	       connection.send(replymsg);
		   
		   // TODO - END

			// stop the server if it was stop methods that was called
		   if (rpcid == RPCCommon.RPIDSTOP) {
			   stop = true;
		   }
		}
	
	}
	
	// used by server side method implementations to register themselves in the RPC server
	public void register(byte rpcid, RPCRemoteImpl impl) {
		services.put(rpcid, impl);
	}
	
	public void stop() {

		if (connection != null) {
			connection.close();
		} else {
			System.out.println("RPCServer.stop - connection was null");
		}
		
		if (msgserver != null) {
			msgserver.stop();
		} else {
			System.out.println("RPCServer.stop - msgserver was null");
		}
		
	}
}
