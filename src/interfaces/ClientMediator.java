package interfaces;

import java.io.IOException;

public interface ClientMediator extends NetworkMediator {

	public void initializeServerCommunication(String mainServerIP, int mainSreverPort) throws IOException;
	public void initializePeersComunnication(String peer1Ip, int peer1Port, String peer2Ip, int peer2Port)throws IOException;
	public String sendToMainServer(String message) throws IOException;
	public void connectToPeers(String params);
	public void sendToPeers(String message);
}