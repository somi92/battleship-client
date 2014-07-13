package network_communication;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import protocol.BattleShipClient;
import protocol.BattleShipPeer;

import interfaces.ClientMediator;

public class ClientThread implements Runnable {

	private BlockingQueue<String> messageQueue;
	private ClientMediator mediator;
	private BattleShipClient clientProtocol;
	private BattleShipPeer peersProtocol;
	
	private String mainServerIP;
	private int mainServerPort;
	
	private String username;
	
	public ClientThread(ClientMediator mediator) {
		this.mediator = mediator;
		this.clientProtocol = new BattleShipClient();
		this.peersProtocol = new BattleShipPeer();
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
	
	public void setUserName(String username) {
		this.username = username;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String messsage;
		try {
			while((messsage = messageQueue.take()) != null) {
				if(messsage.startsWith("CONN")) {
					String[] parts = messsage.split(" ");
					String[] ipAndPort = parts[1].split(":");
					String IP = ipAndPort[0];
					int port = Integer.parseInt(ipAndPort[1]);
//					clientProtocol.setMyPort(port);
					connectToMainServer(IP, port);
				} else {
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void connectToMainServer(String IP, int listeningPort) {
		// TODO Auto-generated method stub
		
		clientProtocol.setMyIP(IP);
		clientProtocol.setMyPort(listeningPort);
		clientProtocol.setUserName(username);
		
		String message = clientProtocol.mainServerConnectionMessage();
		try {
			
			String response = mediator.sendToMainServer(message);
			int responseCode = clientProtocol.parseProtocolMessage(response);
		
			switch (responseCode){

				case BattleShipClient.WAIT: { 
					//obavesti klijenta da se cekaju igraci za igru
					System.out.println("CEKANJE");
				}
				break;
				
				case BattleShipClient.START: { 
//					String[] peer1 = clientProtocol.getIPandPort1().split(":");
//					String[] peer2 = clientProtocol.getIPandPort2().split(":");
					
					String peer1Ip = clientProtocol.getIP1();
					int peer1Port = clientProtocol.getPort1();
					
					String peer2Ip = clientProtocol.getIP2();
					int peer2Port = clientProtocol.getPort2();
					
					mediator.initializePeersComunnication(peer1Ip, peer1Port, peer2Ip, peer2Port);
//					peersProtocol
//					mediator.connectToPeers(params);
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

//	public void receiveMessageFromPeers(String message) {
//		// TODO Auto-generated method stub
//		
//	}

}
