package network_communication;

import java.io.IOException;

import interfaces.ClientMediator;

public class ClientThread implements Runnable {

	private ClientMediator mediator;
	
	public ClientThread(ClientMediator mediator) {
		this.mediator = mediator;
		try {
			this.mediator.initializeClientMediator();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void connectToMainServer() {
		
	}

}
