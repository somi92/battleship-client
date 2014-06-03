package network_communication;

import java.io.IOException;

import protocol.BattleShipClient;
import protocol.BattleShipPeer;

import interfaces.ClientMediator;
import interfaces.ClientEventObserver;

public class ClientThread implements Runnable, ClientEventObserver {

	private ClientMediator mediator;
	private BattleShipClient clientProtocol;
	private BattleShipPeer peersProtocol;
	
	private String mainServerIP;
	private int mainServerPort;
	
	
	public ClientThread(ClientMediator mediator, BattleShipPeer peersProtocol) {
		this.mediator = mediator;
		this.clientProtocol = new BattleShipClient();
		this.peersProtocol = peersProtocol;
		try {
			this.mediator.initializeServerCommunication(mainServerIP, mainServerPort);
//			this.mediator.initializePeersComunnication(peer1Ip, peer1Port, peer2Ip, peer2Port)
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
	
	@Override
	public void connectToMainServer(int listeningPort) {
		// TODO Auto-generated method stub
		clientProtocol.setMyPort(listeningPort);
		String message = clientProtocol.mainServerConnectionMessage();
		try {
			
			String response = mediator.sendToMainServer(message);
			int responseCode = clientProtocol.parseProtocolMessage(response);
		
			switch (responseCode){

				case BattleShipClient.WAIT: { 
					//obavesti klijenta da se cekaju igraci za igru
				}
				break;
				
				case BattleShipClient.START: { 
					String[] peer1 = clientProtocol.getIPandPort1().split(":");
					String[] peer2 = clientProtocol.getIPandPort2().split(":");
					
					String peer1Ip = peer1[0];
					int peer1Port = Integer.parseInt(peer1[1]);
					
					String peer2Ip = peer2[0];
					int peer2Port = Integer.parseInt(peer2[1]);
					
					mediator.initializePeersComunnication(peer1Ip, peer1Port, peer2Ip, peer2Port);
				}
				break;
				
				case BattleShipClient.BYE: {
				
				}
				break;
				
				case BattleShipClient.ERROR: { 
				
				}
				break;
		
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void receiveMessageFromPeers(String message) {
		// TODO Auto-generated method stub
		
	}

}
