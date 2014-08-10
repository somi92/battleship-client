package interfaces;

public interface ClientEventListener {

	// method fired when wait message is received from main server, implement wait message
	public void onWait(String message);
	
	// method fired when peer message is received from main server, implement start logic
	public void onStart();
	
	// method fired when bye message is received
	public void onBye();
}
