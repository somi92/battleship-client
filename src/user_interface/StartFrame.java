package user_interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.Main;

import java.awt.Component;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartFrame extends JFrame {
	Main main = null;
	
	public ImageIcon cover = new ImageIcon(getClass().getResource("/resources/cover.jpg"));
	
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnGameRules;
	private JButton btnStart;
	private JLabel lblA;
	private JButton btnNewButton;
	private JTextField textFieldServersIP;
	private JButton btnGameRules_1;
	private JLabel lblPotapanjeBrodova;
	private JTextField textFieldUserName;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StartFrame frame = new StartFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
		
	/**
	 * Create the frame.
	 */
	public StartFrame(Main main) {
		this.main = main;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getTextFieldServersIP());
		contentPane.add(getBtnGameRules_1());
		contentPane.add(getTextFieldUserName());
		contentPane.add(getLblPotapanjeBrodova());
		contentPane.add(getLblA());
		//this.setContentPane(new JLabel(cover));

		
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("WELCOME");
			lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(0, 0, 3, 14);
		}
		return lblNewLabel;
	}
	private JButton getBtnGameRules() {
		if (btnGameRules == null) {
			btnGameRules = new JButton("Game rules");
			btnGameRules.setBounds(93, 150, 89, 23);
		}
		return btnGameRules;
	}
	private JButton getBtnStart() {
		if (btnStart == null) {
			btnStart = new JButton("Start");
			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnStart.setBounds(93, 116, 89, 23);
		}
		return btnStart;
	}
	private JLabel getLblA() {
		if (lblA == null) {
			lblA = new JLabel("");
			lblA.setHorizontalTextPosition(SwingConstants.CENTER);
			lblA.setHorizontalAlignment(SwingConstants.CENTER);
			lblA.setAlignmentY(Component.TOP_ALIGNMENT);
			lblA.setBounds(0, 0, 507, 398);
			lblA.setIcon(new ImageIcon(StartFrame.class.getResource("/resources/cover.jpg")));
		}
		return lblA;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("START");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
									
						goToSetMyShipsFrame();
					 									
				}
			});
			btnNewButton.setBounds(33, 90, 118, 23);
		}
		return btnNewButton;
	}
	private JTextField getTextFieldServersIP() {
		if (textFieldServersIP == null) {
			textFieldServersIP = new JTextField();
			textFieldServersIP.setText("192.168.1.181");
			textFieldServersIP.setBounds(33, 158, 118, 23);
			textFieldServersIP.setColumns(10);
		}
		return textFieldServersIP;
	}
	private JButton getBtnGameRules_1() {
		if (btnGameRules_1 == null) {
			btnGameRules_1 = new JButton("Game rules");
			btnGameRules_1.setBounds(33, 124, 118, 23);
		}
		return btnGameRules_1;
	}
	private JLabel getLblPotapanjeBrodova() {
		if (lblPotapanjeBrodova == null) {
			lblPotapanjeBrodova = new JLabel("POTAPANJE BRODOVA");
			lblPotapanjeBrodova.setFont(new Font("Stencil", Font.BOLD, 18));
			lblPotapanjeBrodova.setHorizontalTextPosition(SwingConstants.CENTER);
			lblPotapanjeBrodova.setHorizontalAlignment(SwingConstants.LEFT);
			lblPotapanjeBrodova.setBounds(33, 24, 223, 20);
		}
		return lblPotapanjeBrodova;
	}
	private JTextField getTextFieldUserName() {
		if (textFieldUserName == null) {
			textFieldUserName = new JTextField();
			textFieldUserName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					textFieldUserName.setText("");
				}
			});
			textFieldUserName.setText("User name:");
			textFieldUserName.setBounds(33, 59, 118, 23);
			textFieldUserName.setColumns(10);
		}
		return textFieldUserName;
	}
	
	public void goToSetMyShipsFrame(){
		
		main.setMyShipsFrame.setUserAndServerIP(textFieldUserName.getText(), textFieldServersIP.getText());
		main.setMyShipsFrame.setVisible(true);
		setVisible(false);
		
		
	}
}
