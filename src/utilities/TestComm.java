package utilities;

import interfaces.ClientEventListener;
import interfaces.PeerEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import network_communication.ClientThread;
import network_communication.CommunicationController;
import network_communication.ServerSideThread;

public class TestComm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String username = "";
		System.out.print("Enter username: ");
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
		try {
			username = in1.readLine();
			in1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		CommunicationController controller = new CommunicationController();
		ServerSideThread server = new ServerSideThread(queue, 0, username);
		ClientThread client = new ClientThread(controller, queue, "localhost", 9080);
		
		client.setClientEventListener(new ClientEventListener() {
			
			@Override
			public void onWait(String message) {
				// TODO Auto-generated method stub
				System.out.println(message);
			}
			
			@Override
			public void onStart(String message) {
				// TODO Auto-generated method stub
				System.out.println(message);
			}
			
			@Override
			public void onBye(String message) {
				// TODO Auto-generated method stub
				
			}
		});
		
		client.setPeerEventListener(new PeerEventListener() {
			
			@Override
			public void onSynchronized() {
				// TODO Auto-generated method stub
				System.out.println("onSynchronized");
				
			}
			
			@Override
			public void onNext(String username, boolean myTurn) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onBye() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int onAttacked(int coorI, int coorJ) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void onAttackResponse(String username, int coorI, int coorJ,
					int status, boolean myTurn) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onRnd(boolean myTurn, int myRND, int myIndex) {
				// TODO Auto-generated method stub
				System.out.println("My turn: "+myTurn+" RND: "+myRND+" index: "+myIndex);

			}
		});
		
		Thread s = new Thread(server);
		Thread c = new Thread(client);
		
		s.start();
		c.start();
		
		
	}

}
