package utilities;

import interfaces.TimerTimeout;

import javax.swing.JPanel;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MyTimer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6457684504590405968L;
	private JLabel lblTime;
	
	private TimerTimeout event;
	private Timer timer;
	private int seconds;
	private boolean sound;
	private boolean visual;
	
	private boolean running;
	
	private AudioClip countdown;
	private AudioClip timeoutHorn;

	/**
	 * Create the panel.
	 */
	public MyTimer() {
		setLayout(new BorderLayout(0, 0));
		add(getLblTime(), BorderLayout.CENTER);
		this.running = false;
		getLblTime().setText("0");
		try {
			this.countdown = Applet.newAudioClip(new URL("file:./src/resources/pulse.wav"));
			this.timeoutHorn = Applet.newAudioClip(new URL("file:./src/resources/horn.wav"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MyTimer(TimerTimeout event, int seconds, boolean sound, boolean visual) {
		this();
		this.event = event;
		this.seconds = seconds;
		this.sound = sound;
		this.visual = visual;
		getLblTime().setText(this.seconds+"");
	}

	private TimerTimeout getEvent() {
		return this.event;
	}
	
	private Timer getTimer() {
		return this.timer;
	}
	
	private AudioClip getCountdown() {
		return this.countdown;
	}
	
	private AudioClip getTimeoutHorn() {
		return this.timeoutHorn;
	}
	
	public int getSeconds() {
		return this.seconds;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public boolean isVisual() {
		return visual;
	}

	public void setVisual(boolean visual) {
		this.visual = visual;
	}

	private JLabel getLblTime() {
		if (lblTime == null) {
			lblTime = new JLabel("");
			lblTime.setFont(new Font("Dialog", Font.BOLD, 20));
			lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblTime;
	}
	
	public void startTimer() {
		if (!this.running) {
			
			this.timer = new Timer();
			this.running = true;
			getLblTime().setForeground(null);
			
			this.timer.schedule(new TimerTask() {
				int counter = getSeconds();
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (counter == -1) {
							getTimer().cancel();
							return;
						}
						if(counter < 11 && counter != 0) {
							if(isSound()) {
								getCountdown().play();
							}
							if(isVisual()) {
								getLblTime().setForeground(Color.RED);
							}
						}
						getLblTime().setText(counter+"");
						if(counter == 0) {
							if(isSound()) {
								getTimeoutHorn().play();
							}
							getEvent().timerTimeout();
							running = false;
						}
						counter--;
					}
				}, new Date(), 1000);
		}
	}

	public void stopTimer() {
		this.running = false;
		if(getTimer() == null) {
			return;
		}
		getTimer().cancel();
	}
	
}
