package network_communication;

import java.io.IOException;

import protocol.BattleShipClient;

import interfaces.ClientMediator;

public class ClientThread implements Runnable {

	private ClientMediator mediator;
	private BattleShipClient protocol;
	
	private String mainServerIP;
	private int mainServerPort;
	
	
	public ClientThread(ClientMediator mediator) {
		this.mediator = mediator;
		protocol = new BattleShipClient();
		try {
			this.mediator.initializeClientMediator(mainServerIP, mainServerPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getMainServerIP() {
		return mainServerIP;
	}

	public void setMainServerIP(String mainServerIP) {
		this.mainServerIP = mainServerIP;
	}

	public int getMainServerPort() {
		return mainServerPort;
	}

	public void setMainServerPort(int mainServerPort) {
		this.mainServerPort = mainServerPort;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void connectToMainServer() {
		String message = protocol.mainServerConnectionMessage();
		try {
			
		String response = mediator.sendToMainServer(message);
		int responseCode=protocol.parseProtocolMessage(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
