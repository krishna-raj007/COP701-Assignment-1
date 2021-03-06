package cop701.pastry;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;


import cop701.node.ClientWriter;

public class PastryWriter {
	
	private static final Logger logger = Logger.getLogger(ClientWriter.class.getName());
	
	public PastryWriter() {
	}

	public boolean forwardMessage(Message m) {
		Socket recipientSocket;
		ObjectOutputStream outputStream;
		
		try {
			recipientSocket = new Socket(m.getAddress().getIp(),m.getAddress().getPort());
			outputStream = new ObjectOutputStream(recipientSocket.getOutputStream());
			outputStream.writeObject(m);
			recipientSocket.close();
		}
		catch (IOException e) {
			logger.warning("PastryWriter has some issues while writing to socket, probably the host is down");
			System.out.println("PastryWriter failed from " + m.getSenderId() + " to " + m.getAddress());
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
