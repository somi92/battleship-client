package protocol;

/**
 * Facade class for protocols
 * */
public class BattleShipProtocol {
	
	private BattleShipClient clientProtocol;
	private BattleShipPeer peerProtocol;
	
	private String myIP;
	private int myPort;
	private String myUserName;
	
	private String peer1IP;
	private int peer1Port;
	private String peer1UserName;
	
	private String peer2IP;
	private int peer2Port;
	private String peer2UserName;
	
	public BattleShipProtocol() {
		
	}
	
	public void initializeProtocol() {
		clientProtocol = new BattleShipClient();
		peerProtocol = new BattleShipPeer();
	}
	
	/*** delegate method calls for client protocol ***/
	public int parseMainServerMessage(String message) {
		return clientProtocol.parseProtocolMessage(message);
	}
	
	public String getMainServerConnectionMessage() {
		return clientProtocol.mainServerConnectionMessage();
	}
	
	public String getMainServerStartMessage() {
		return clientProtocol.startMessage();
	}
	
	public String getMainServerRepeatMessage() {
		return clientProtocol.repeatConnectionMessage();
	}
	
	
	
	/*** delegate method calls for peer protocol***/
	public int parsePeerMessage(String message) {
		return peerProtocol.parseProtocolMessage(message);
	}
}
