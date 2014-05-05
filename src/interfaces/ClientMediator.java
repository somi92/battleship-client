package interfaces;

public interface ClientMediator extends NetworkMediator {

	public void sendToMainServer(String message);
	public void connectToPeers(String params);
	public void sendToPeers(String message);
}
