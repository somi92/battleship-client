package utilities;

import interfaces.ClientEventListener;
import interfaces.PeerEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import network_communication.ClientThread;
import network_communication.CommunicationController;
import network_communication.ServerSideThread;

public class TestComm {

	BlockingQueue<String> queue;
	CommunicationController controller;
	ServerSideThread server;
	ClientThread client;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final TestComm comm = new TestComm();
		
		String username = "";
		System.out.print("Enter username: ");
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
		try {
			username = in1.readLine();
//			in1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comm.queue = new ArrayBlockingQueue<String>(10);
		comm.controller = new CommunicationController();
		comm.server = new ServerSideThread(comm.queue, 0, username);
		comm.client = new ClientThread(comm.controller, comm.queue, "localhost", 9080);
		
		comm.client.setClientEventListener(new ClientEventListener() {
			
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
		
		comm.client.setPeerEventListener(new PeerEventListener() {
			
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
				return comm.respond(coorI, coorJ);
			}
			
			@Override
			public void onAttackResponse(String username, int coorI, int coorJ,
					int status, boolean myTurn) {
				// TODO Auto-generated method stub
				System.out.println("User: "+username+" "+coorI+":"+coorJ+" status: "+status+" myTurn: "+myTurn);
				if(myTurn) {
					comm.shoot();
				}
			}

			@Override
			public void onRnd(boolean myTurn, int myRND, int myIndex) {
				// TODO Auto-generated method stub
				System.out.println("My turn: "+myTurn+" RND: "+myRND+" index: "+myIndex);
				if(myTurn) {
					comm.shoot();
				}
			}
		});
		
		Thread s = new Thread(comm.server);
		Thread c = new Thread(comm.client);
		
		s.start();
		c.start();
		
		
	}

	public void shoot() {
		System.out.println('\n');
		try {
			System.out.println("Enter target username,coorI,coorJ: ");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String target = in.readLine();
			String[] params = target.split(",");
			System.out.println(params[0]+" "+Integer.parseInt(params[1])+" "+Integer.parseInt(params[2]));
			client.sendSHT(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]));
			System.out.println();
//			in.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public int respond(int coorI, int coorJ) {
		System.out.println("\nSHIP_MISSED = 0");
		System.out.println("SHIP_HIT = 1");
		System.out.println("SHIP_SUNKED = 2");
		System.out.println("FLEET_DESTROYED = 3");
		System.out.println("Enter response: ");
		Scanner s = new Scanner(System.in);
		int response = s.nextInt();
//		s.close();
		return response;
	}
}
