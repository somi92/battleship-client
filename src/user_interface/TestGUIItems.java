package user_interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import application_logic.SeaFieldManager;

import java.awt.Dimension;

public class TestGUIItems {

	SeaField seaField;
	
	private JFrame frame;
	private JPanel centarPanel;
	private JButton btnUp;
	private JPanel leftPanel;
	private JPanel downPanel;
	private JButton btnRight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUIItems window = new TestGUIItems();
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
	public TestGUIItems() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		seaField = new SeaFieldManager().createSeaField();
		seaField.setPreferredSize(new Dimension(250, 250));
		seaField.setLayout(new BorderLayout(0, 0));
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(getCentarPanel(), BorderLayout.CENTER);
		frame.getContentPane().add(getBtnUp(), BorderLayout.NORTH);
		frame.getContentPane().add(getLeftPanel(), BorderLayout.WEST);
		frame.getContentPane().add(getDownPanel(), BorderLayout.SOUTH);
		frame.getContentPane().add(getBtnRight(), BorderLayout.EAST);
	}

	private JPanel getCentarPanel() {
		if (centarPanel == null) {
			centarPanel = new JPanel();
			centarPanel.setLayout(new BorderLayout(0, 0));
			centarPanel.add(seaField, BorderLayout.CENTER);
			
		}
		return centarPanel;
	}
	private JButton getBtnUp() {
		if (btnUp == null) {
			btnUp = new JButton("upper button");
		}
		return btnUp;
	}
	private JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
		}
		return leftPanel;
	}
	private JPanel getDownPanel() {
		if (downPanel == null) {
			downPanel = new JPanel();
		}
		return downPanel;
	}
	private JButton getBtnRight() {
		if (btnRight == null) {
			btnRight = new JButton("right button");
			btnRight.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return btnRight;
	}
}
