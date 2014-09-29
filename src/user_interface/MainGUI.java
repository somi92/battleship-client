package user_interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.JButton;

import application_logic.SeaFieldManager;
import main.Main;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Component;

public class MainGUI extends JFrame {
	public Main main = null;
	
	SeaFieldManager seaFieldManager = null;
	SeaFieldPanel seaFieldMy = null;
	JPanel seaFieldOpponent1 = null;
	JPanel seaFieldOpponent2 = null;
	
	int[][] logicMatrixMine = new int[10][10];
	int[][] logicMatrixOpponent1 = new int[10][10];
	int[][] logicMatrixOpponent2 = new int[10][10];
	
	
	private JPanel contentPane;
	private JPanel centralPanel;
	private JPanel downPanel;
	private JScrollPane scrollPane;
	private JTextPane textPane;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JPanel panelSeaFieldOpponent1;
	private JPanel panelSeaFieldMy;
	private JPanel panelSeaFieldOpponent2;
	private JPanel upperPanel;
	private JLabel labelOpponent1;
	private JLabel labelMe;
	private JLabel labelOpponent2;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainGUI frame = new MainGUI();
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
	public MainGUI(Main main) {
		this.main = main; 
		
		seaFieldManager = new SeaFieldManager();
		seaFieldMy = seaFieldManager.createSeaField();
		seaFieldOpponent1 = seaFieldManager.createSeaField();
		seaFieldOpponent2 = seaFieldManager.createSeaField();;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getCentralPanel(), BorderLayout.CENTER);
		contentPane.add(getDownPanel(), BorderLayout.SOUTH);
		contentPane.add(getUpperPanel(), BorderLayout.NORTH);
	}

	private JPanel getCentralPanel() {
		if (centralPanel == null) {
			centralPanel = new JPanel();
			centralPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow,center]"));
			centralPanel.add(getPanelSeaFieldOpponent1(), "cell 0 0,grow");
			centralPanel.add(getPanelSeaFieldMy(), "cell 1 0,grow");
			centralPanel.add(getPanelSeaFieldOpponent2(), "cell 2 0,grow");
			
		}
		return centralPanel;
	}
	private JPanel getDownPanel() {
		if (downPanel == null) {
			downPanel = new JPanel();
			downPanel.setPreferredSize(new Dimension(100, 100));
			downPanel.setLayout(new BorderLayout(0, 0));
			downPanel.add(getScrollPane(), BorderLayout.CENTER);
			downPanel.add(getPanel(), BorderLayout.EAST);
		}
		return downPanel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextPane());
		}
		return scrollPane;
	}
	private JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
		}
		return textPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new MigLayout("", "[89px]", "[23px][][]"));
			panel.add(getBtnNewButton(), "cell 0 0,alignx left,aligny top");
			panel.add(getBtnNewButton_1(), "cell 0 1");
			panel.add(getBtnNewButton_2(), "cell 0 2");
		}
		return panel;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("New button");
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("New button");
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("New button");
		}
		return btnNewButton_2;
	}
	private JPanel getPanelSeaFieldOpponent1() {
		if (panelSeaFieldOpponent1 == null) {
			panelSeaFieldOpponent1 = new JPanel();
			panelSeaFieldOpponent1.setAlignmentY(Component.TOP_ALIGNMENT);
			panelSeaFieldOpponent1.setAlignmentX(Component.LEFT_ALIGNMENT);
			panelSeaFieldOpponent1.setPreferredSize(new Dimension(130, 110));
			panelSeaFieldOpponent1.setLayout(new BorderLayout(0, 0));
			panelSeaFieldOpponent1.add(seaFieldOpponent1, BorderLayout.CENTER);
		}
		return panelSeaFieldOpponent1;
	}
	private JPanel getPanelSeaFieldMy() {
		if (panelSeaFieldMy == null) {
			panelSeaFieldMy = new JPanel();
			panelSeaFieldMy.setPreferredSize(new Dimension(130, 130));
			panelSeaFieldMy.setLayout(new BorderLayout(0, 0));
			panelSeaFieldMy.add(seaFieldMy,BorderLayout.CENTER);
		}
		return panelSeaFieldMy;
	}
	private JPanel getPanelSeaFieldOpponent2() {
		if (panelSeaFieldOpponent2 == null) {
			panelSeaFieldOpponent2 = new JPanel();
			panelSeaFieldOpponent2.setPreferredSize(new Dimension(130, 130));
			panelSeaFieldOpponent2.setLayout(new BorderLayout(0, 0));
			panelSeaFieldOpponent2.add(seaFieldOpponent2, BorderLayout.CENTER);
		}
		return panelSeaFieldOpponent2;
	}
	private JPanel getUpperPanel() {
		if (upperPanel == null) {
			upperPanel = new JPanel();
			upperPanel.setLayout(new GridLayout(1, 0, 0, 0));
			upperPanel.add(getLabelOpponent1());
			upperPanel.add(getLabelMe());
			upperPanel.add(getLabelOpponent2());
		}
		return upperPanel;
	}
	private JLabel getLabelOpponent1() {
		if (labelOpponent1 == null) {
			labelOpponent1 = new JLabel("Opponent1");
			labelOpponent1.setHorizontalTextPosition(SwingConstants.CENTER);
			labelOpponent1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return labelOpponent1;
	}
	private JLabel getLabelMe() {
		if (labelMe == null) {
			labelMe = new JLabel("Me");
			labelMe.setHorizontalTextPosition(SwingConstants.CENTER);
			labelMe.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return labelMe;
	}
	private JLabel getLabelOpponent2() {
		if (labelOpponent2 == null) {
			labelOpponent2 = new JLabel("Opponent2");
			labelOpponent2.setHorizontalTextPosition(SwingConstants.CENTER);
			labelOpponent2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return labelOpponent2;
	}
	
	
	public void popuniniMojePoljeBrodicima(){
		seaFieldMy.formirajMyFieldPane(logicMatrixMine);
		
	}
	
	
	
	
	
}
