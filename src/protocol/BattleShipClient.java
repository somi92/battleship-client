package protocol;

public class BattleShipClient {

	public static final int INIT = 0;
	public static final int WAIT = 1;
	public static final int ERROR = 2;
	public static final int START = 3;
	public static final int BYE = 4;
	
	private BattleShipProtocol parent;
	
	private int state;
//	private String userName;
//	private String myIP;
//	private int myPort;
	
//	private String IPandPort1;
//	private String IPandPort2;
	
//	private String IP1;
//	private int port1;
//	private String IP2;
//	private int port2;
	
	public BattleShipClient() {
		setState(BattleShipClient.INIT);
//		IPandPort1 = null;
//		IPandPort2 = null;
	}
	
	public BattleShipClient(BattleShipProtocol parent) {
		this();
		this.parent = parent;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getMyIP() {
//		return myIP;
//	}
//
//	public void setMyIP(String iP) {
//		myIP = iP;
//	}
//
//	public int getMyPort() {
//		return myPort;
//	}
//
//	public void setMyPort(int port) {
//		this.myPort = port;
//	}
	
//	public String getIP1() {
//		return IP1;
//	}
//
//	public void setIP1(String iP1) {
//		IP1 = iP1;
//	}
//
//	public int getPort1() {
//		return port1;
//	}
//
//	public void setPort1(int port1) {
//		this.port1 = port1;
//	}
//
//	public String getIP2() {
//		return IP2;
//	}
//
//	public void setIP2(String iP2) {
//		IP2 = iP2;
//	}
//
//	public int getPort2() {
//		return port2;
//	}
//
//	public void setPort2(int port2) {
//		this.port2 = port2;
//	}

//	public String getIPandPort1() {
//		return this.IPandPort1;
//	}
//	
//	public String getIPandPort2() {
//		return this.IPandPort2;
//	}
	
	public int parseProtocolMessage(String message) {
		
		if(message == null) {
			return BattleShipClient.ERROR;
		}
		
		String[] messageParts = message.split("_");
		
		if(messageParts.length == 1) {
			
			String protocolMethod = messageParts[0];
			
			if(protocolMethod.equals("WAIT")) {
				setState(BattleShipClient.WAIT);
				return BattleShipClient.WAIT;
			}
			
			if(protocolMethod.equals("BYE")) {
				setState(BattleShipClient.BYE);
				return BattleShipClient.BYE;
			}
			
			setState(BattleShipClient.ERROR);
			return BattleShipClient.ERROR;
		}
		
		if(messageParts.length == 3) {
			
			String protocolMethod = messageParts[0];
			
			String IPandPort1 = messageParts[1];
			String IPandPort2 = messageParts[2];
			
			if(protocolMethod.equals("PEERS") && IPandPort1.contains(":") && IPandPort2.contains(":")) {
//				this.IPandPort1 = IPandPort1;
//				this.IPandPort2 = IPandPort2;
				
				String[] data1 = IPandPort1.split(":");
				parent.setPeer1IP(data1[0]);
				parent.setPeer1Port(Integer.parseInt(data1[1]));
//				IP1 = data1[0];
//				port1 = Integer.parseInt(data1[1]);
				
				String[] data2 = IPandPort2.split(":");
				parent.setPeer2IP(data2[0]);
				parent.setPeer2Port(Integer.parseInt(data2[1]));
//				IP2 = data2[0];
//				port2 = Integer.parseInt(data2[1]);
				
				setState(START);
				return BattleShipClient.START;
			}
		}
		
		return BattleShipClient.ERROR;
	}

	public String mainServerConnectionMessage() {
		setState(BattleShipClient.WAIT);
		return "HELLO_"+parent.getMyIP()+":"+parent.getMyPort()+"_"+parent.getMyUserName()+'\n';
	}
	
	public String repeatConnectionMessage() {
		setState(BattleShipClient.WAIT);
		return "REPEAT"+'\n';
	}
	
	public String startMessage() {
		setState(BattleShipClient.BYE);
		return "START"+'\n';
	}
}
