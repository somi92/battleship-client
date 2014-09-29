package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import user_interface.MainGUI;
import user_interface.SetMyShipsFrame;
import user_interface.StartFrame;

public class Main implements Runnable{

	public static Main me = null;
	public static StartFrame startFrame = null;
	public static SetMyShipsFrame setMyShipsFrame = null;
	public static MainGUI mainGui = null;
	
	
		public static void main(String[] args) {
			
			Main main = new Main();
			main.run();
			
		}

		@Override
		public void run() {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						
					startFrame = new StartFrame(me);
					startFrame.setVisible(true);
					
					setMyShipsFrame = new SetMyShipsFrame(me);
					setMyShipsFrame.setVisible(false);
					
					mainGui = new MainGUI(me);
					mainGui.setVisible(false);
					
					
					
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}
		
		

		



		
	
	
}
