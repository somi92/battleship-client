package interfaces;

public interface ClientEventObserver {

	public void connectToMainServer(int listeningPort);
	public void receiveMessageFromPeers(String message);
}
