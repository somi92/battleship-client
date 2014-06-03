package network_communication;

import interfaces.ClientEventObserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

public class ServerSideThread implements Runnable {
	
	private int listeningPort;
	private ServerSocket serverSide;
	private ExecutorService serverSideExecutor;
	
	
	private LinkedList<ClientEventObserver> observers;
	private ClientEventObserver clientObserver;
	
	public ServerSideThread() {
		this.observers = new LinkedList<ClientEventObserver>();
	}
	
	public ServerSideThread(ClientEventObserver obsv) {
		this();
		clientObserver = obsv;
	}
	
	public void setClientEventObserver(ClientEventObserver clientObserver) {
		this.clientObserver = clientObserver;
	}

	private void setListeningPort(int listeningPort) {
		this.listeningPort = listeningPort;
	}
	
	private void startServerListener(int listeningPort) {
		try {
			serverSide = new ServerSocket(listeningPort);
			if(listeningPort == 0) {
				setListeningPort(serverSide.getLocalPort());
			} else {
				setListeningPort(listeningPort);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Aplikacija ne moze da rezervise izabrani port za osluskivanje! Port je verovatno zauzet. Izaberite novi broj porta i pokusajte ponovo.", "Greska!", JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Aplikacija ne moze da rezervise izabrani port za osluskivanje! Broj porta mora biti u rasponu 0-65535, ukljucno. Izaberite novi broj porta i pokusajte ponovo.", "Greska!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		startServerListener(this.listeningPort);
		serverSideExecutor = Executors.newFixedThreadPool(2);
		
		while(true) {
			try {
				serverSideExecutor.execute(new ReceiverThread(serverSide.accept(), this));
			} catch (Exception e) {
				// TODO: handle exception
				return;
			}
		}
	}
	
	public void startMainServerConnection() {
		clientObserver.connectToMainServer(listeningPort);
	}
	
	public synchronized void notifyClientObserver(String message) {
		clientObserver.receiveMessageFromPeers(message);
	}

}
