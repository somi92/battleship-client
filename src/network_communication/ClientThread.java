package network_communication;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import protocol.BattleShipClient;
import protocol.BattleShipPeer;
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
	
	private boolean error;
	
//	private String username;
	
	public ClientThread(ClientMediator mediator, BlockingQueue<String> messageQueue, String mainServerIP, int mainServerPort) {
		this.mediator = mediator;
		this.messageQueue = messageQueue;
		this.protocol = new BattleShipProtocol();
		this.mainServerIP = mainServerIP;
		this.mainServerPort = mainServerPort;
		this.error = false;
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
	
//	public void setUserName(String username) {
//		this.username = username;
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message;
		try {
			while((message = messageQueue.take()) != null) {
				if(message.startsWith("CONN")) {
					String[] parts = message.split(" ");
					String[] ipAndPort = parts[1].split(":");
					String IP = ipAndPort[0];
					int port = Integer.parseInt(ipAndPort[1]);
					String username = ipAndPort[2];
//					clientProtocol.setMyPort(port);
					protocol.setMyIP(IP);
					protocol.setMyPort(port);
					protocol.setMyUserName(username);
					System.out.println(port+" "+username);
					connectToMainServer(IP, port);
				} else {
					// call method to parse and handle the message passed by server-side thread
					
					switch(handleMessage(message)) {
					
						case BattleShipPeer.SYNCHRONIZED:
							mediator.setPeer1Username(protocol.getPeer1UserName());
							mediator.setPeer2Username(protocol.getPeer2UserName());
							sendRnd();
						break;
						
						case BattleShipPeer.IDLE:
							
						break;
						
						case BattleShipPeer.PLAYING:
							// nothing
						break;
							
						case BattleShipPeer.ATTACKED:
							int attackerIndex = protocol.getCurrentIndex();
							String rsp = sendRSP();
							String[] parts = rsp.split("_");
							String[] coords = parts[2].split(":");
							int coorI = Integer.parseInt(coords[0]);
							int coorJ = Integer.parseInt(coords[1]);
							int status = Integer.parseInt(coords[2]);
							boolean myTurn = (protocol.getCurrentIndex() == protocol.getMyIndex() ? true : false);
							System.out.println("ERROR: "+error);
							if(error && (attackerIndex != protocol.getCurrentIndex()) && !myTurn ) {
								sendNXT(protocol.getMyIndex());
								myTurn = true;
								protocol.setCurrentIndex(protocol.getMyIndex());
							}
							System.out.println("Current index after RSP call: "+protocol.getCurrentIndex());
							protocol.getPeerEventListener().onAttackResponse(protocol.getMyUserName(), coorI, coorJ, status, myTurn);
						break;
							
						case BattleShipPeer.DESTROYED:
							sendRSP();
//							String rsp1 = sendRSP();
//							String[] parts1 = rsp1.split("_");
//							String[] coords1 = parts1[2].split(":");
//							int coorI1 = Integer.parseInt(coords1[0]);
//							int coorJ1 = Integer.parseInt(coords1[1]);
//							int status1 = Integer.parseInt(coords1[2]);
							boolean myTurn1 = (protocol.getCurrentIndex() == protocol.getMyIndex() ? true : false);
//							protocol.getPeerEventListener().onAttackResponse(protocol.getMyUserName(), coorI1, coorJ1, status1, myTurn1);
							
							if(myTurn1) {
								sendNXT();
							}
						break;
						
						case BattleShipPeer.EXCLUDED:
							sendNXT();
						break;
						
						case BattleShipPeer.BYE:
							
						break;
						
						case BattleShipPeer.ERROR:
							
						break;
					}
					
					// if response SYNCHRONIZED send RND
					// if response PLAYING do nothing
					// if response ATTACKED send RSP
					// if response DESTROYED
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void connectToMainServer(String IP, int listeningPort) {
		// TODO Auto-generated method stub

		boolean wait = true;
		
//		protocol.setMyIP(IP);
//		protocol.setMyPort(listeningPort);
//		protocol.setMyUserName(username);
		
		String message = protocol.getMainServerConnectionMessage();
		System.out.println(message);
		
		try {
			
			String response = mediator.sendToMainServer(message);

			int responseCode = protocol.parseMainServerMessage(response);
			
			while(wait) {
				
				wait = false;

				switch (responseCode){

					case BattleShipClient.WAIT: { 
						//obavesti klijenta da se cekaju igraci za igru, event listener
						protocol.getClientEventListener().onWait("Server je prihvatio zahtev. Cekaju se igraci...");
						
						response = mediator.receive();
						responseCode = protocol.parseMainServerMessage(response);
						
						wait = true;
					}
					break;
					
					case BattleShipClient.START: { 
						
						String peer1Ip = protocol.getPeer1IP();
						int peer1Port = protocol.getPeer1Port();

						String peer2Ip = protocol.getPeer2IP();
						int peer2Port = protocol.getPeer2Port();
						
						System.out.println("Peers "+peer1Ip+peer1Port+" "+peer2Ip+peer2Port);
						
						mediator.initializePeersComunnication(peer1Ip, peer1Port, peer2Ip, peer2Port);
						protocol.getClientEventListener().onStart("Povezivanje sa igracima...");
						
						boolean isOK  = mediator.sendToPeers(protocol.getSynMessage());
						
						if(!isOK) {
							// error occured, handle here
							System.out.println("ERROR!");
							break;
						}
//						sendRnd();
//					 	ovdje ce ici event listener za pocetak igre
//						peersProtocol
//						mediator.connectToPeers(params);
					}
					break;
					
					case BattleShipClient.BYE: {
						// call methods to close resources in mediator and trigger event in protocol
					}
					break;
					
					case BattleShipClient.ERROR: { 
						// call methods to close resources in mediator and trigger event in protocol
					}
					break;
			
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// methods for sending messages
	
	private void sendRnd() {
		String message = protocol.getRndMessage();
		boolean isOK = mediator.sendToPeers(message);
		if(!isOK) {
				
		}
	}
	
	private String sendRSP() {
		String message = protocol.getRspMessage();
		boolean isOK = mediator.sendToPeers(message);
		if(!isOK) {
//			if(error = false) {
				error = true;
//			}
		}
		return message;
	}
	
	public boolean sendSHT(String targetUserName, int coorI, int coorJ) {
		String message = protocol.getShtMessage(targetUserName, coorI, coorJ);
		boolean isOK = mediator.sendToPeers(message);
		if(!isOK) {
			System.out.println("Send not OK!");
			return false;
		}
		return true;
	}

	private void sendNXT() {
		String message = protocol.getNextMessage();
		System.out.println(message);
		boolean isOK = mediator.sendToPeers(message);
		if(!isOK) {
			System.out.println("Next not OK!");
		}
	}
	
	private void sendNXT(int index) {
		String message = protocol.getNextMessage(index);
		System.out.println(message);
		boolean isOK = mediator.sendToPeers(message);
		if(!isOK) {
			System.out.println("Next not OK!");
		}
	}
	
	// listener setters
	public void setClientEventListener(ClientEventListener listener) {
		protocol.setClientListener(listener);
	}
	
	public void setPeerEventListener(PeerEventListener listener) {
		protocol.setPeerListener(listener);
	}
	
	private int handleMessage(String message) {
		int response  = protocol.parsePeerMessage(message);
		return response;
	}
}
