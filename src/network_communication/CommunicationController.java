package network_communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import interfaces.ClientMediator;
import interfaces.NetworkMediator;
import interfaces.ServerSideMediator;

public class CommunicationController implements NetworkMediator, ClientMediator, ServerSideMediator {
	
	private Socket clientSocket;
	private BufferedReader clientInputStream;
	private DataOutputStream clientOutputStream;
	
	private Socket peer1Socket;
	private BufferedReader peer1InputStream;
	private DataOutputStream peer1OutputStream;
	private String peer1Username;
	
	private Socket peer2Socket;
	private BufferedReader peer2InputStream;
	private DataOutputStream peer2OutputStream;
	private String peer2Username;
	
	@Override
	public void setPeer1Username(String peer1Username) {
		// TODO Auto-generated method stub
		this.peer1Username = peer1Username;
	}

	@Override
	public void setPeer2Username(String peer2Username) {
		// TODO Auto-generated method stub
		this.peer2Username = peer2Username;
	}
	
	@Override
	public void send(String message) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String receive() throws IOException {
		// TODO Auto-generated method stub
		return clientInputStream.readLine();
	}
	
	@Override
	public void initializeServerCommunication(String mainServerIP, int mainServerPort) throws IOException {
		// TODO Auto-generated method stub
		clientSocket = new Socket(mainServerIP, mainServerPort);
		clientInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		clientOutputStream = new DataOutputStream(clientSocket.getOutputStream());
	}

	@Override
	public void initializePeersComunnication(String peer1Ip, int peer1Port,
			String peer2Ip, int peer2Port) throws IOException {
		// TODO Auto-generated method stub
		
		peer1Socket = new Socket(peer1Ip,peer1Port);
		peer1InputStream = new BufferedReader(new InputStreamReader(peer1Socket.getInputStream()));
		peer1OutputStream = new DataOutputStream(peer1Socket.getOutputStream());
//		this.peer1Username = peer1Ip;
		
		peer2Socket = new Socket(peer2Ip,peer2Port);
		peer2InputStream = new BufferedReader(new InputStreamReader(peer2Socket.getInputStream()));
		peer2OutputStream = new DataOutputStream(peer2Socket.getOutputStream());
//		this.peer2Username = peer2Ip;
	}
	
	@Override
	public String sendToMainServer(String message) throws IOException {
		// TODO Auto-generated method stub
		clientOutputStream.writeBytes(message) ;
		String response = clientInputStream.readLine();
		return response;
		
	}

	@Override
	public boolean sendToPeers(String message) {
		// TODO Auto-generated method stub
		
		
		try {
			peer1OutputStream.writeBytes(message);
		} catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Could not send to peer1, disconnected!");
//			e.printStackTrace();
		}

		try {
			peer2OutputStream.writeBytes(message);
		} catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Could not send to peer2, disconnected!");
//			e.printStackTrace();
		}

		String response1 = "";
		try {
			response1 = peer1InputStream.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(null, "Could not receive from peer1, disconnected!");
//			e.printStackTrace();
		}
		
		String response2 = "";
		try {
			response2 = peer2InputStream.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(null, "Could not receive from peer2, disconnected!");
//			e.printStackTrace();
		}

		
		if(response1 != null && response1.startsWith("OK") && response2 != null && response2.startsWith("OK")) {
			return true;
		} else {

			return false;
		}
	}

	@Override
	public boolean connectToPeers(String message) throws IOException {
		// TODO Auto-generated method stub
		// method for sending first message RND and SYN
		return false;
	}

	@Override
	public void serverResponse(String message) {
		// TODO Auto-generated method stub
		
	}
	
}
