package interfaces;

import java.io.IOException;

public interface ClientMediator extends NetworkMediator {

	public void initializeClientMediator() throws IOException;
	public String sendToMainServer(String message);
	public void connectToPeers(String params);
	public void sendToPeers(String message);
}
