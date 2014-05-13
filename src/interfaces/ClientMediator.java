package interfaces;

import java.io.IOException;

public interface ClientMediator extends NetworkMediator {

	public void initializeClientMediator(String mainServerIP, int mainSreverPort) throws IOException;
	public String sendToMainServer(String message) throws IOException;
	public void connectToPeers(String params);
	public void sendToPeers(String message);
}
