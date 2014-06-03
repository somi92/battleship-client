package protocol;

public class BattleShipPeer {

	public static final int INIT = 0;
	public static final int SYNCHRONIZED = 1;
	public static final int PLAYING = 2;
	public static final int DESTROYED = 3;
	public static final int BYE = 4;
	public static final int ERROR = 5;
	
	private int status;
	private int myIndex;
	
	private int SYNcounter;
	private int RNDcounter;
	
	private String usernamePeer1;
	private String usernamePeer2;
	
	private int myRandomNumber;
	private int peer1RndNubmer;
	private int peer2RndNumber;
	
	
	public BattleShipPeer() {
		this.status = BattleShipPeer.INIT;
		this.myIndex = 0;
		this.SYNcounter = 0;
		this.RNDcounter = 0;
	}
	
	public int parseProtocolMessage(String message) {
		
		if(message == null) {
			return BattleShipPeer.ERROR;
		}
		
		String[] messageParts = message.split("_");
		String pMethod = "";
		String pOptions = "";
		String pData = "";
		
		if(messageParts.length == 2) {
			
			pMethod = messageParts[0];
			pData = messageParts[1];
			
			if(pMethod.equals("SYN")) {
				SYNcounter++;
				if(SYNcounter == 1) {
					usernamePeer1 = messageParts[1];
					return status;
				} else if(SYNcounter == 2) {
					usernamePeer2 = messageParts[1];
					status = BattleShipPeer.SYNCHRONIZED;
					return status;
				}
			}	
		}
		
		if(messageParts.length == 3) {
			
			pMethod = messageParts[0];
			pData = messageParts[1];
			pOptions = messageParts[2];
			
			if(pMethod.equals("RND")) {
				RNDcounter++;
				if(RNDcounter == 1) {
					peer1RndNubmer = Integer.parseInt(pOptions);
					return status;
				} else if(RNDcounter == 2) {
					peer2RndNumber = Integer.parseInt(pOptions);
					boolean rndStatus = calculateIndexes();
					if(rndStatus) {
						status = BattleShipPeer.PLAYING;
					} else {
						status = BattleShipPeer.SYNCHRONIZED;
					}
					return status;
				}	
			}
		}
		
		return 0;
	}
	
	private boolean calculateIndexes() {
		if(myIndex == peer1RndNubmer || myIndex == peer2RndNumber || peer1RndNubmer == peer2RndNumber) {
			return false;
		}
		if(myIndex > peer1RndNubmer) {
			if(myIndex > peer2RndNumber) {
				myIndex = 1;
			} else {
				myIndex = 2;
			}
		} else {
			if(myIndex > peer2RndNumber) {
				myIndex = 2;
			}
			else {
				myIndex = 3;
			}
		}
		return true;
	}
}
