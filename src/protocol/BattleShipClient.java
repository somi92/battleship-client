package protocol;

public class BattleShipClient {

	public static final int INIT = 0;
	public static final int WAIT = 1;
	public static final int ERROR = 2;
	public static final int START = 3;
	public static final int BYE = 4;
	
	private BattleShipProtocol parent;
	
	private int state;
	
	public BattleShipClient() {
		setState(BattleShipClient.INIT);
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
				
				String[] data1 = IPandPort1.split(":");
				parent.setPeer1IP(data1[0]);
				parent.setPeer1Port(Integer.parseInt(data1[1]));
				
				String[] data2 = IPandPort2.split(":");
				parent.setPeer2IP(data2[0]);
				parent.setPeer2Port(Integer.parseInt(data2[1]));
				
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
