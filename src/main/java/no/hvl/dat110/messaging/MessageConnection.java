package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		byte[] data = MessageUtils.encapsulate(message);;

		// TODO - START
		// encapsulate the data contained in the Message and write to the output stream

		try {
			outStream.write(data); // Send encapsulated data over TCP
			outStream.flush(); // Ensure data is sent immediately

		} catch (IOException e) {
			System.err.println("Error sending message: " + e.getMessage());
			e.printStackTrace();
		}

		// TODO - END

	}

	public Message receive() {

		Message message = null;
		byte[] data;

		// TODO - START
		// read a segment from the input stream and decapsulate data into a Message

	    try {
	        data = new byte[MessageUtils.SEGMENTSIZE]; // Buffer to store received segment
	        
	        inStream.readFully(data); // Read exactly SEGMENTSIZE bytes from the stream
	        
	        message = MessageUtils.decapsulate(data); // Convert bytes back into a Message object

	    } catch (IOException e) {
	        System.err.println("Error receiving message: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return message;

		// TODO - END


	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}