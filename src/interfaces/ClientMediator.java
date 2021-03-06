package interfaces;

import java.io.IOException;

public interface ClientMediator extends NetworkMediator {

	public void initializeServerCommunication(String mainServerIP, int mainSreverPort) throws IOException;
	public void initializePeersComunnication(String peer1Ip, int peer1Port, String peer2Ip, int peer2Port) throws IOException;
	public String sendToMainServer(String message) throws IOException;
	public boolean connectToPeers(String params) throws IOException;
	public boolean sendToPeers(String message);
	public void sendChatToPeers(String message);
	public void setPeer1Username(String peer1Username);
	public void setPeer2Username(String peer2Username);
}
