package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessagingServer {

	// server-side socket for accepting incoming TCP connections
	private ServerSocket welcomeSocket;

	public MessagingServer(int port) {
	    try {
	        System.out.println("Attempting to start server on port " + port);
	        this.welcomeSocket = new ServerSocket(port);
	        System.out.println("Server started successfully on port " + port);
	    } catch (IOException ex) {
	        System.err.println("Messaging server failed to start: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	}


//	// accept an incoming connection from a client
//	public MessageConnection accept() {
//
//		MessageConnection connection = null;
//
//		// TODO - START
//		// accept TCP connection on welcome socket and create messaging connection to be returned
//
//		if (true)
//			throw new UnsupportedOperationException(TODO.method());
//		
//		// TODO - END
//		
//		return connection;
//
//	}
	
	public MessageConnection accept() {
		if (welcomeSocket == null) {
	        throw new IllegalStateException("Server socket not initialized. Ensure MessagingServer was started correctly.");
	    }

	    try {
	        Socket clientSocket = welcomeSocket.accept();
	        return new MessageConnection(clientSocket);
	    } catch (IOException e) {
	        throw new RuntimeException("Error accepting client connection: " + e.getMessage(), e);
	    }
    }

	public void stop() {

		if (welcomeSocket != null) {

			try {
				welcomeSocket.close();
			} catch (IOException ex) {

				System.out.println("Messaging server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
