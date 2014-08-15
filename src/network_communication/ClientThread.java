package network_communication;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import protocol.BattleShipClient;
import protocol.BattleShipProtocol;

import interfaces.ClientEventListener;
import interfaces.ClientMediator;
import interfaces.PeerEventListener;

public class ClientThread implements Runnable {

	private BlockingQueue<String> messageQueue;
	private ClientMediator mediator;
	private BattleShipProtocol protocol;
	
	private String mainServerIP;
	private int mainServerPort;
	
	private String username;
	
	public ClientThread(ClientMediator mediator) {
		this.mediator = mediator;
		this.protocol = new BattleShipProtocol();
		try {
			this.mediator.initializeServerCommunication(mainServerIP, mainServerPort);
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
					String username = ipAndPort[2];
//					clientProtocol.setMyPort(port);
					protocol.setMyIP(IP);
					protocol.setMyPort(port);
					protocol.setMyUserName(username);
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

		protocol.setMyIP(IP);
		protocol.setMyPort(listeningPort);
		protocol.setMyUserName(username);
		
		String message = protocol.getMainServerConnectionMessage();
		
		try {
			
			String response = mediator.sendToMainServer(message);

			int responseCode = protocol.parseMainServerMessage(response);
		
			switch (responseCode){

				case BattleShipClient.WAIT: { 
					//obavesti klijenta da se cekaju igraci za igru, event listener
					protocol.getClientEventListener().onWait("Server je prihvatio zahtev. Cekaju se igraci...");
				}
				break;
				
				case BattleShipClient.START: { 
					
					String peer1Ip = protocol.getPeer1IP();
					int peer1Port = protocol.getPeer1Port();
					

					String peer2Ip = protocol.getPeer2IP();
					int peer2Port = protocol.getPeer2Port();
					
					mediator.initializePeersComunnication(peer1Ip, peer1Port, peer2Ip, peer2Port);
					
//				 	ovdje ce ici event listener za pocetak igre
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

	// listener setters
	public void setClientEventListener(ClientEventListener listener) {
		protocol.setClientListener(listener);
	}
	
	public void setPeerEventListener(PeerEventListener listener) {
		protocol.setPeerListener(listener);
	}
}
