package no.hvl.dat110.rpc;

import java.util.Arrays;

import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.MessagingClient;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;

	public RPCClient(String server, int port) {

		msgclient = new MessagingClient(server, port);
	}

	public void connect() {

		// TODO - START
		// connect using the RPC client
		this.connection = msgclient.connect();

		// TODO - END
	}

	public void disconnect() {

		// TODO - START
		// disconnect by closing the underlying messaging connection

		if (connection != null) {
			connection.close();
			connection = null; // Prevent reuse of a closed connection
		} else {
			System.out.println("RPCClient.disconnect - connection was already null");
		}
		// TODO - END
	}

	/*
	 * Make a remote call om the method on the RPC server by sending an RPC request
	 * message and receive an RPC reply message
	 * 
	 * rpcid is the identifier on the server side of the method to be called param
	 * is the marshalled parameter of the method to be called
	 */


	public byte[] call(byte rpcid, byte[] param) {
		byte[] returnval = null;

		// TODO - START

		/*
		 * 
		 * The rpcid and param must be encapsulated according to the RPC message format
		 * 
		 * The return value from the RPC call must be decapsulated according to the RPC
		 * message format
		 * 
		 */

		if (connection == null) {
			throw new IllegalStateException("RPCClient: Connection is not established. Call connect() first.");
		}

		byte[] request = RPCUtils.encapsulate(rpcid, param);

		connection.send(new Message(request));

		Message reply = connection.receive();

		returnval = RPCUtils.decapsulate(reply.getData());

		// TODO - END

		return returnval;
	}

}
