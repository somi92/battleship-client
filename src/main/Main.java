package main;

import java.awt.EventQueue;

import user_interface.MainGUI;

public class Main {

	
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainGUI window = new MainGUI();
						window.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
	
	
}
