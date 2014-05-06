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

	private ServerSocket serverSideSocket;
	private BufferedReader serverSideInputStream;
	private DataOutputStream serverSideOutputStream;
	
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
	public void initializeClientMediator() throws IOException {
		// TODO Auto-generated method stub
		clientSocket = new Socket();
		clientInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		clientOutputStream = new DataOutputStream(clientSocket.getOutputStream());
	}

	@Override
	public String sendToMainServer(String message) {
		// TODO Auto-generated method stub
		return message;
		
	}

	@Override
	public void sendToPeers(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectToPeers(String params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serverResponse(String message) {
		// TODO Auto-generated method stub
		
	}

	
}
