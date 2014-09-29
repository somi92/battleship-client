package interfaces;

public interface PeerEventListener {

	public void onSynchronized();
	public void onRnd(boolean myTurn, int myRND, int myIndex, String peer1Username, int peer1Index, String peer2Username, int peer2Index);
	public int onAttacked(int coorI, int coorJ);
	public void onAttackResponse(String username, int coorI, int coorJ, int status, boolean myTurn);
	public void onNext(String username, boolean myTurn);
	public void onBye();
	
}
