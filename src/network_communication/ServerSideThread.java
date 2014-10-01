package network_communication;

import interfaces.ChatEventListener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

public class ServerSideThread implements Runnable {
	
	private BlockingQueue<String> messageQueue;
	private int listeningPort;
	private String myUsername;
	private ServerSocket serverSide;
	private ExecutorService serverSideExecutor;
	
	private ChatEventListener chatListener; 
	
//	private String mainServerIP;
	
	public ServerSideThread() {

	}
	
	public ServerSideThread(BlockingQueue<String> messageQueue) {
		this();
		this.messageQueue = messageQueue;
	}

	public ServerSideThread(BlockingQueue<String> messageQueue, int listeningPort, String myUsername) {
		this(messageQueue);
		this.listeningPort = listeningPort;
		this.myUsername = myUsername;
	}
	
	private void setListeningPort(int listeningPort) {
		this.listeningPort = listeningPort;
	}
	
	public int getMyListeningPort() {
		return listeningPort;
	}
	
	public void setMyUsername(String myUsername) {
		this.myUsername = myUsername;
	}
	
	public String getMyUsername() {
		return myUsername;
	}
	
	public void setChatEventListener(ChatEventListener chatListener) {
		this.chatListener = chatListener;
	}
	
//	public void setMainServerIP(String mainServerIP) {
//		this.mainServerIP = mainServerIP;
//	}
	
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
		startServerListener(listeningPort);
		serverSideExecutor = Executors.newFixedThreadPool(2);
		
		// this thread needs to signal the client thread to start and pass it the listening port
		try {
			messageQueue.put("CONN "+"localhost"+":"+listeningPort+":"+myUsername+'\n');
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(true) {
			try {
				serverSideExecutor.execute(new ReceiverThread(serverSide.accept()));
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
				return;
			}
		}
	}
	
	/********************** Inner class - receiver thread ***********************************/
	
	public class ReceiverThread implements Runnable {
		
		private Socket communicationSocket;
		BufferedReader clientInput;
		DataOutputStream clientOutput;
		
		public ReceiverThread(Socket communicationSocket) {
			this.communicationSocket = communicationSocket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				
				String clientMessage = "";
				clientInput = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
				clientOutput = new DataOutputStream(communicationSocket.getOutputStream());
				
				while(true) {
					clientMessage = clientInput.readLine();
					if(clientMessage != null) {
						
						if(clientMessage.startsWith("CHT")) {
							String[] parts = clientMessage.split("_");
							chatListener.onChatMessageReceived(parts[1], parts[2]);
						} else {
							messageQueue.put(clientMessage);
						}
						
						clientOutput.writeBytes("OK"+'\n');
					}
				}
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					clientInput.close();
					clientOutput.close();
					communicationSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		
	}
	/*********************************************************/
}
