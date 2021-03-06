package protocol;

import java.util.Random;

import utilities.BattleShipStatus;

public class BattleShipPeer {

	public static final int INIT = 0;
	public static final int SYNCHRONIZED = 1;
	public static final int PLAYING = 2;
	public static final int IDLE = 3;
	public static final int ATTACKED = 4;
	public static final int DESTROYED = 5;
	public static final int EXCLUDED = 6;
	public static final int BYE = 7;
	public static final int ERROR = 8;
	
	private int state;
	private int myIndex;
	private int peer1Index;
	private int peer2Index;
	private int currentIndex;
	
	private boolean fleetDestroyed;
	
	private int SYNcounter;
	private int RNDcounter;
	
	private BattleShipProtocol parent;
	
	private int myRandomNumber;
	private int peer1RndNumber;
	private int peer2RndNumber;
	
	private int coorIAttacked;
	private int coorJAttacked;
	
	public BattleShipPeer() {
		this.state = BattleShipPeer.INIT;
		this.myIndex = 0;
		this.peer1Index = 0;
		this.peer2Index = 0;
		this.currentIndex = 0;
		this.fleetDestroyed = false;
		this.SYNcounter = 0;
		this.RNDcounter = 0;
		this.coorIAttacked = -1;
		this.coorJAttacked = -1;
	}
	
	public BattleShipPeer(BattleShipProtocol parent) {
		this();
		this.parent = parent;
	}
	
	int getMyIndex() {
		return this.myIndex;
	}
	
	int getCurrentIndex() {
		return this.currentIndex;
	}
	
	public int parseProtocolMessage(String message) {
		
		if(message == null) {
			return BattleShipPeer.ERROR;
		}
		
		String[] messageParts = message.split("_");
		String pMethod = "";
		String pData = "";
		String pUser = "";
		String pCoords = "";
		
		if(messageParts.length == 2) {
			
			pMethod = messageParts[0];
			pUser = messageParts[1];
			
			if(pMethod.equals("SYN")) {
				SYNcounter++;
				if(SYNcounter == 1) {
					parent.setPeer1UserName(messageParts[1]);
					return state;
				} else if(SYNcounter == 2) {
					parent.setPeer2UserName(messageParts[1]);
					state = BattleShipPeer.SYNCHRONIZED;
					parent.peerListener.onSynchronized();
					return state;
				}
			}
			
			if(pMethod.equals("BYE")) {
				// implement BYE
			}
		}
		
		if(messageParts.length == 3) {
			
			pMethod = messageParts[0];
			pUser = messageParts[1];
			pData = messageParts[2];
			
			if(pMethod.equals("RND")) {
				RNDcounter++;
				if(RNDcounter == 1) {
					
					if(parent.getPeer1UserName().equals(pUser)) {
						peer1RndNumber = Integer.parseInt(pData);
					}
					if(parent.getPeer2UserName().equals(pUser)) {
						peer2RndNumber = Integer.parseInt(pData);
					}
						
					state = BattleShipPeer.IDLE;
					return state;
					
				} else if(RNDcounter == 2) {
					
					if(parent.getPeer1UserName().equals(pUser)) {
						peer1RndNumber = Integer.parseInt(pData);
					}
					if(parent.getPeer2UserName().equals(pUser)) {
						peer2RndNumber = Integer.parseInt(pData);
					}
						
					boolean rndStatus = calculateIndexes();
					if(rndStatus) {
						state = BattleShipPeer.PLAYING;
						boolean myTurn = (myIndex == currentIndex ? true : false); 
						parent.peerListener.onRnd(myTurn, myRandomNumber, myIndex, parent.getPeer1UserName(), peer1Index, parent.getPeer2UserName(), peer2Index);
					} else {
						state = BattleShipPeer.SYNCHRONIZED;
					}
					return state;
				}	
			}
			
			if(pMethod.equals("SHT")) {
				
				String targetUsername = pUser;
				if(targetUsername.equals(parent.getMyUserName())) {
					state = BattleShipPeer.ATTACKED;
					String[] coors = pData.split(":");
					int i = Integer.parseInt(coors[0]);
					int j = Integer.parseInt(coors[1]);
					coorIAttacked = i;
					coorJAttacked = j;
					parent.status = parent.peerListener.onAttacked(i, j);
					
					if(parent.status == BattleShipStatus.FLEET_DESTROYED) {
						state = BattleShipPeer.DESTROYED;
						fleetDestroyed = true;
					}
					
					return state;
				} else {
					state = BattleShipPeer.PLAYING;
				}
				return state;
			}
			
			if(pMethod.equals("NXT")) {
				String username = pUser;
				int nextIndex = Integer.parseInt(pData);
				boolean myTurn = false;
				currentIndex = nextIndex;
				myTurn = (currentIndex == myIndex ? true : false);
				parent.peerListener.onNext(username, myTurn);
				state = BattleShipPeer.PLAYING;
				return state;
			}
		}
		
		if(messageParts.length == 4) {
			pMethod = messageParts[0];
			pUser = messageParts[1];
			pCoords = messageParts[2];
			pData = messageParts[3];
			
			if(pMethod.equals("RSP")) {
				String username = pUser;
				int coorI = Integer.parseInt(pCoords.split(":")[0]);
				int coorJ = Integer.parseInt(pCoords.split(":")[1]);
				int status = Integer.parseInt(pCoords.split(":")[2]);
				int nextIndex = Integer.parseInt(pData);
				boolean myTurn = false;
				currentIndex = nextIndex;
				myTurn = (currentIndex == myIndex ? true : false);
				if(fleetDestroyed && myTurn) {
					myTurn = false;
					state = BattleShipPeer.EXCLUDED;
				} else {
					state = BattleShipPeer.PLAYING;
				}
				parent.peerListener.onAttackResponse(username, coorI, coorJ, status, myTurn);
				
//				if(fleetDestroyed == true && myTurn == true) {
//					state = BattleShipPeer.EXCLUDED;
//				} else {
//					state  = BattleShipPeer.PLAYING;
//				}
				
//				state  = BattleShipPeer.PLAYING;
				return state;
			}
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
		System.out.println("Current index is: "+currentIndex);
		return "RSP_"+parent.getMyUserName()+"_"+coorIAttacked+":"+coorJAttacked+":"+status+"_"+nextIndex(status)+'\n';
	}
	
	public String nextMessage() {
		state = BattleShipPeer.DESTROYED;
		return "NXT_"+parent.getMyUserName()+"_"+nextIndex(BattleShipStatus.SHIP_MISSED)+'\n';
	}
	
	public String nextMessage(int index) {
		return "NXT_"+parent.getMyUserName()+"_"+index+'\n';
	}
	
	public String finishMessage() {
		// delete this method
		state = BattleShipPeer.BYE;
		return "FIN"+'\n';
	}
	
	public String byeMessage() {
		state = BattleShipPeer.BYE;
		return "BYE"+'\n';
	}
	
	private boolean calculateIndexes() {
		if(myRandomNumber == peer1RndNumber || myRandomNumber == peer2RndNumber || peer1RndNumber == peer2RndNumber) {
			return false;
		}
		if(myRandomNumber > peer1RndNumber) {
			if(myRandomNumber > peer2RndNumber) {
				
				myIndex = 1;
				if(peer1RndNumber>peer2RndNumber) {
					peer1Index = 2;
					peer2Index = 3;
				} else {
					peer2Index = 2;
					peer1Index = 3;
				}
				
			} else {
				
				peer2Index = 1;
				myIndex = 2;
				peer1Index = 3;
				
			}
		} else {
			
			if(myRandomNumber > peer2RndNumber) {
				
				peer1Index = 1;
				myIndex = 2;
				peer2Index = 3;
				
			} else {
				
				myIndex = 3;
				if(peer1RndNumber>peer2RndNumber) {
					peer1Index = 1;
					peer2Index = 2;
				} else {
					peer2Index = 1;
					peer1Index = 2;
				}
			}
		}
		currentIndex = 1;
		return true;
	}
	
	private int nextIndex(int status) {
		if(status == BattleShipStatus.SHIP_MISSED) {
			if(currentIndex == 3) {
				currentIndex = 1;
				return currentIndex;
			} else {
				currentIndex = currentIndex + 1;
				return currentIndex;
			}
		}
		if(status == BattleShipStatus.FLEET_DESTROYED) {
			fleetDestroyed = true;
			this.state = BattleShipPeer.DESTROYED;
		}
		return currentIndex;
	}
	
	private int generateRndNumber() {
		Random rnd = new Random();
		myRandomNumber = rnd.nextInt(100)+1; 
		return myRandomNumber;
	}
}
