package utilities;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Timer extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6457684504590405968L;
	private JLabel lblTime;

	/**
	 * Create the panel.
	 */
	public Timer() {
		setLayout(new BorderLayout(0, 0));
		add(getLblTime(), BorderLayout.CENTER);

	}

	private JLabel getLblTime() {
		if (lblTime == null) {
			lblTime = new JLabel("Timer");
			lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblTime;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
