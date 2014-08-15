package network_communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.ClientMediator;
import interfaces.NetworkMediator;
import interfaces.ServerSideMediator;

public class CommunicationController implements NetworkMediator, ClientMediator, ServerSideMediator {

//	private ServerSocket serverSideSocket;
//	private BufferedReader serverSideInputStream;
//	private DataOutputStream serverSideOutputStream;
	
	private Socket clientSocket;
	private BufferedReader clientInputStream;
	private DataOutputStream clientOutputStream;
	
	private Socket peer1Socket;
	private BufferedReader peer1InputStream;
	private DataOutputStream peer1OutputStream;
	
	private Socket peer2Socket;
	private BufferedReader peer2InputStream;
	private DataOutputStream peer2OutputStream;
	
	@Override
	public void send(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receive(String message) {
		// TODO Auto-generated method stub
		
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
			String peer2Ip, int peer2Port) throws IOException{
		// TODO Auto-generated method stub
		
		peer1Socket = new Socket(peer1Ip,peer1Port);
		peer1InputStream= new BufferedReader(new InputStreamReader(peer1Socket.getInputStream()));
		peer1OutputStream= new DataOutputStream(peer1Socket.getOutputStream());
		
		peer2Socket=new Socket(peer1Ip,peer1Port);
		peer2InputStream= new BufferedReader(new InputStreamReader(peer2Socket.getInputStream()));
		peer2OutputStream= new DataOutputStream(peer2Socket.getOutputStream());
		
	}
	
	@Override
	public String sendToMainServer(String message) throws IOException {
		// TODO Auto-generated method stub
		clientOutputStream.writeBytes(message) ;
		String response = clientInputStream.readLine();
		return response;
		
	}

	@Override
	public void sendToPeers(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectToPeers(String message) {
		// TODO Auto-generated method stub
		// method for sending first message RND and SYN
	}

	@Override
	public void serverResponse(String message) {
		// TODO Auto-generated method stub
		
	}



	
}
