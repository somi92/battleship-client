package network_communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiverThread implements Runnable {
	
	private Socket communicationSocket;
	private ServerSideThread parent;
	
	public ReceiverThread(Socket communicationSocket, ServerSideThread parent) {
		this.communicationSocket = communicationSocket;
		this.parent = parent;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			String clientMessage = "";
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
			
			while(true) {
				clientMessage = clientInput.readLine();
				parent.notifyClientObserver(clientMessage);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
}
