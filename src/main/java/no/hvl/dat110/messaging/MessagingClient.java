package no.hvl.dat110.messaging;


import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessagingClient {

	// name/IP address of the messaging server
	private String server;

	// server port on which the messaging server is listening
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}

	public MessageConnection connect() {

	    // Client-side socket for the TCP connection
	    Socket clientSocket;
	    MessageConnection connection = null;

	    try {
	        // Establish TCP connection to the messaging server
	        clientSocket = new Socket(server, port);

	        // Create a MessageConnection object using the connected socket
	        connection = new MessageConnection(clientSocket);
	    } catch (IOException e) {
	        // Handle connection errors
	        System.err.println("Error connecting to the messaging server: " + e.getMessage());
	        e.printStackTrace();
	    }

	    // Return the established messaging connection (null if failed)
	    return connection;
	}

	
	
}
