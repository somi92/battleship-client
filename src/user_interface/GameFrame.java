package user_interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import application_logic.GameFrameManager;

public class GameFrame extends JFrame {

	private JPanel contentPane;
	private JPanel centerPanel;
	private JPanel upPanel;
	public ButtonGroup groupShips;
	public ButtonGroup groupHV;
	public int shipSize=1;
	public char orijentation='H';
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame gameFrame = new GameFrame();
					gameFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getCenterPanel(), BorderLayout.CENTER);
		contentPane.add(getUpPanel(), BorderLayout.NORTH);

	}

	public GameFrame me = this;
	
	GameFrameManager gameFrameManager = new GameFrameManager(me);
	
	JButton[][] buttonGameBoard1 = gameFrameManager.generateGameBoard();
	JButton[][] buttonGameBoard2 = gameFrameManager.generateGameBoard();
	JButton[][] buttonGameBoard3 = gameFrameManager.generateGameBoard();
	
	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
			centerPanel.setLayout(new MigLayout("gap 0px 0px", "[30]", "[30]"));
			//centerPanel.setLayout(new MigLayout("", "[left][38.00][]", "[][][]"));
			//centerPanel.setLayout(new MigLayout("gap 0px 0px", "[][][]", "[][][][][][]"));
			for(int i=0;i<30;i++){
				for (int j=0;j<10 && i<10;j++){
					buttonGameBoard1[i][j].setSize(40, 40);
					centerPanel.add(buttonGameBoard1[i][j], "cell "+j+" "+i+"");
				}
				for (int j=0;j<10 && i>=10 && i<20;j++){
					buttonGameBoard2[i-10][j].setSize(40, 40);
					centerPanel.add(buttonGameBoard2[i-10][j], "cell "+j+" "+(i-10)+" ");
				}
				for (int j=0;j<10 && i>=20;j++){
					buttonGameBoard3[i-20][j].setSize(40, 40);
					centerPanel.add(buttonGameBoard3[i-20][j], "cell "+j+" "+(i-20)+" ");
				}
		}
			
		
		}
		return centerPanel;
	}
	private JPanel getUpPanel() {
		if (upPanel == null) {
			upPanel = new JPanel();
		}
		return upPanel;
	}
	
}

