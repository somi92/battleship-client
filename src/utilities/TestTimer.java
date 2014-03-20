package utilities;

import interfaces.TimerTimeout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class TestTimer implements TimerTimeout {

	private JFrame frame;
	private JButton btnNewButton;
	private MyTimer t;
	private JComboBox comboBox;
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;
	
	private MyTimer getTimer() {
		if(t == null) {
			t = new MyTimer();
			t.setFont(new Font("Dialog", Font.BOLD, 16));
		}
		return t;
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestTimer window = new TestTimer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestTimer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		t = new MyTimer(this, 30, true, true);
//		t = new MyTimer(this, 30, false, false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(getBtnNewButton(), BorderLayout.CENTER);
		frame.getContentPane().add(getTimer(), BorderLayout.SOUTH);
		frame.getContentPane().add(getComboBox(), BorderLayout.EAST);
		frame.getContentPane().add(getLblNewLabel(), BorderLayout.NORTH);
		frame.getContentPane().add(getBtnNewButton_1(), BorderLayout.WEST);
		
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Pokreni");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getTimer().startTimer();
				}
			});
		}
		return btnNewButton;
	}

	@Override
	public void timerTimeout() {
		// TODO Auto-generated method stub
		getLblNewLabel().setText("TIMEOUT!");
	}
	
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"jedan", "dva", "tri"}));
		}
		return comboBox;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
		}
		return lblNewLabel;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Zaustavi");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getTimer().stopTimer();
				}
			});
		}
		return btnNewButton_1;
	}
}
