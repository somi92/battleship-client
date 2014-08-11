package interfaces;

public interface PeerEventListener {

	public void onSynchronized();
	public void onAttacked();
	public void onAttackResponse(String username, int coorI, int coorJ, int status, boolean myTurn);
	public void onNext(String username, boolean myTurn);
	public void onBye();
}
