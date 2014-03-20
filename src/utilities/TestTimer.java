package utilities;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub

		final Timer t = new Timer();
		
		final AudioClip clip1 = Applet.newAudioClip(new URL("file:./src/resources/pulse.wav"));
		final AudioClip clip2 = Applet.newAudioClip(new URL("file:./src/resources/horn.wav"));
		
		t.schedule(new TimerTask() {
			
			private int counter = 30;
			
			public void run() {
				if (this.counter==-1) {
					t.cancel();
					return;
				}
				System.out.print(" "+this.counter);
				if(this.counter<11 && this.counter != 0) {
					try {
						clip1.play();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				if(this.counter==0) {
					clip2.play();
				}
				this.counter--;
			}
			
		}, new Date(), 1000);
		
	}

}
