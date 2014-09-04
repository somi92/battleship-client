package utilities;

import interfaces.ClientEventListener;
import interfaces.PeerEventListener;

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
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		CommunicationController controller = new CommunicationController();
		ServerSideThread server = new ServerSideThread(queue, 9898, "Somi");
		ClientThread client = new ClientThread(controller, queue, "localhost", 9080);
		
		client.setClientEventListener(new ClientEventListener() {
			
			@Override
			public void onWait(String message) {
				// TODO Auto-generated method stub
//				System.out.println("onWait");
			}
			
			@Override
			public void onStart(String message) {
				// TODO Auto-generated method stub
//				System.out.println("on);
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
		});
		
		Thread s = new Thread(server);
		Thread c = new Thread(client);
		
		s.start();
		c.start();
		
		
	}

}
