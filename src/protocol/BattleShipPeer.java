package protocol;

import java.util.Random;

import utilities.BattleShipStatus;

public class BattleShipPeer {

	public static final int INIT = 0;
	public static final int SYNCHRONIZED = 1;
	public static final int PLAYING = 2;
	public static final int DESTROYED = 3;
	public static final int BYE = 4;
	public static final int ERROR = 5;
	
	private int state;
	private int myIndex;
	
	private int SYNcounter;
	private int RNDcounter;
	
	private BattleShipProtocol parent;
	
//	private String usernamePeer1;
//	private String usernamePeer2;
	
	private int myRandomNumber;
	private int peer1RndNubmer;
	private int peer2RndNumber;
	
	
	public BattleShipPeer() {
		this.state = BattleShipPeer.INIT;
		this.myIndex = 0;
		this.SYNcounter = 0;
		this.RNDcounter = 0;
	}
	
	public BattleShipPeer(BattleShipProtocol parent) {
		this();
		this.parent = parent;
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
					parent.setPeer1UserName(messageParts[1]);
//					usernamePeer1 = messageParts[1];
					return state;
				} else if(SYNcounter == 2) {
					parent.setPeer2UserName(messageParts[1]);
//					usernamePeer2 = messageParts[1];
					state = BattleShipPeer.SYNCHRONIZED;
					return state;
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
					return state;
				} else if(RNDcounter == 2) {
					peer2RndNumber = Integer.parseInt(pOptions);
					boolean rndStatus = calculateIndexes();
					if(rndStatus) {
						state = BattleShipPeer.PLAYING;
					} else {
						state = BattleShipPeer.SYNCHRONIZED;
					}
					return state;
				}	
			}
			
			if(pMethod.equals("SHT")) {
				// call callback method to trigger event and pass target username and coordinates
			}
			
			if(pMethod.equals("RSP")) {
				// call callback method to trigger event for game update
			}
			
//			if(pMethod.equals("DST")) {
//				// call callback method to trigger event for game update, exclude from the game
//			}
//			
//			if(pMethod.equals("FIN")) {
//				
//			}
//			
//			if(pMethod.equals("BYE")) {
//				
//			}
		}
		
		return 0;
	}
	
	public String synMessage() {
		state = BattleShipPeer.INIT;
		return "SYN_"+parent.getMyUserName()+'\n';
	}
	
	public String rndMessage() {
		return "RND_"+parent.getMyUserName()+"_"+generateRndNumber()+'\n';
	}
	
	public String shootMessage(String targetUserName, int coorI, int coorJ) {
		state = BattleShipPeer.PLAYING;
		return "SHT_"+targetUserName+"_"+coorI+":"+coorJ+'\n';
	}
	
	// this method will be automatically called from facade after the event callback finishes 
	public String responseMessage(int status) {
		return "RSP_"+parent.getMyUserName()+"_"+status+"_"/*calculate next index*/;
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
	
	private int nextIndex(int status) {
		if(status == BattleShipStatus.FLEET_DESTROYED) {
			if(myIndex == 3) {
				return 1;
			} else {
				return ++myIndex;
			}
			
		}
		return 0;
	}
	
	private int generateRndNumber() {
		Random rnd = new Random();
		myRandomNumber = rnd.nextInt(100)+1; 
		return myRandomNumber;
	}
}
