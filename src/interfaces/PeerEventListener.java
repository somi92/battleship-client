package interfaces;

public interface PeerEventListener {

	public void onSynchronized();
	public void onRnd(boolean myTurn, int myRND, int myIndex);
	public int onAttacked(int coorI, int coorJ);
	public void onAttackResponse(String username, int coorI, int coorJ, int status, boolean myTurn);
	public void onNext(String username, boolean myTurn);
	public void onBye();
}
