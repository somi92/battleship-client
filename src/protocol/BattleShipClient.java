package protocol;

public class BattleShipClient {

	public static final int INIT = 0;
	public static final int WAIT = 1;
	public static final int ERROR = 2;
	public static final int START = 3;
	public static final int BYE = 4;
	
	private int state;
	private String userName;
	private String myIP;
	private int myPort;
	
	private String IPandPort1;
	private String IPandPort2;
	
	public BattleShipClient() {
		setState(BattleShipClient.INIT);
		IPandPort1 = null;
		IPandPort2 = null;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMyIP() {
		return myIP;
	}

	public void setMyIP(String iP) {
		myIP = iP;
	}

	public int getMyPort() {
		return myPort;
	}

	public void setMyPort(int port) {
		this.myPort = port;
	}
	
	public String getIPandPort1() {
		return this.IPandPort1;
	}
	
	public String getIPandPort2() {
		return this.IPandPort2;
	}
	
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
				this.IPandPort1 = IPandPort1;
				this.IPandPort2 = IPandPort2;
				setState(START);
				return BattleShipClient.START;
			}
		}
		
		return BattleShipClient.ERROR;
	}

	public String mainServerConnectionMessage() {
		setState(BattleShipClient.WAIT);
		return "HELLO_"+getMyIP()+":"+getMyPort()+"_"+getUserName()+'\n';
	}
	
	public String repeatConnectionMessage() {
		setState(BattleShipClient.WAIT);
		return "REPEAT";
	}
	
	public String startMessage() {
		setState(BattleShipClient.BYE);
		return "START";
	}
}
