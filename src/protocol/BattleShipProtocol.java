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
	
	public String getMyIP() {
		return myIP;
	}

	public void setMyIP(String myIP) {
		this.myIP = myIP;
	}

	public int getMyPort() {
		return myPort;
	}

	public void setMyPort(int myPort) {
		this.myPort = myPort;
	}

	public String getMyUserName() {
		return myUserName;
	}

	public void setMyUserName(String myUserName) {
		this.myUserName = myUserName;
	}

	public String getPeer1IP() {
		return peer1IP;
	}

	public void setPeer1IP(String peer1ip) {
		peer1IP = peer1ip;
	}

	public int getPeer1Port() {
		return peer1Port;
	}

	public void setPeer1Port(int peer1Port) {
		this.peer1Port = peer1Port;
	}

	public String getPeer1UserName() {
		return peer1UserName;
	}

	public void setPeer1UserName(String peer1UserName) {
		this.peer1UserName = peer1UserName;
	}

	public String getPeer2IP() {
		return peer2IP;
	}

	public void setPeer2IP(String peer2ip) {
		peer2IP = peer2ip;
	}

	public int getPeer2Port() {
		return peer2Port;
	}

	public void setPeer2Port(int peer2Port) {
		this.peer2Port = peer2Port;
	}

	public String getPeer2UserName() {
		return peer2UserName;
	}

	public void setPeer2UserName(String peer2UserName) {
		this.peer2UserName = peer2UserName;
	}

	public void initializeProtocol() {
		clientProtocol = new BattleShipClient(this);
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
