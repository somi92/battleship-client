package user_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.ImageIcon;
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

import utilities.BattleShipStatus;

import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {
	public Main main = null;
	
	//kada mene neko gadja
	int sifra = -1;
	
	public ImageIcon seaMissImg = new ImageIcon(getClass().getResource("/resources/miss.png"));
	public ImageIcon bombImg = new ImageIcon(getClass().getResource("/resources/bomb.png"));
	
	String[] redosledIgranja = new String[3];
	
	SeaFieldManager seaFieldManager = null;
	SeaFieldPanel seaFieldMy = null;
	SeaFieldPanel seaFieldOpponent1 = null;
	SeaFieldPanel seaFieldOpponent2 = null;
	
	int[][] logicMatrixMine = new int[10][10];
	int[][] logicMatrixOpponent1 = new int[10][10];
	int[][] logicMatrixOpponent2 = new int[10][10];
	
	
	private JPanel contentPane;
	private JPanel centralPanel;
	private JPanel downPanel;
	private JScrollPane scrollPane;
	public JTextPane textPane;
	private JPanel panel;
	private JPanel panelSeaFieldOpponent1;
	private JPanel panelSeaFieldMy;
	private JPanel panelSeaFieldOpponent2;
	private JPanel upperPanel;
	public JLabel labelOpponent1;
	public JLabel labelMe;
	public JLabel labelOpponent2;

	public String myUserName="";
	//na potezu ko je 0-2
	int naPotezu = 0;
	private JButton btnSendChatMessage;
	private JScrollPane scrollPane_1;
	public JTextPane textPaneChat;
	private JTextField textChatField;
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
		
		seaFieldManager = new SeaFieldManager(this);
		seaFieldMy = seaFieldManager.createSeaField(true,0);
		seaFieldOpponent1 = seaFieldManager.createSeaField(false,1);
		seaFieldOpponent2 = seaFieldManager.createSeaField(false,2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 560);
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
			downPanel.setPreferredSize(new Dimension(100, 200));
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
			textPane.setSize(new Dimension(10, 20));
			textPane.setPreferredSize(new Dimension(6, 15));
			textPane.setEditable(false);
		}
		return textPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(400, 15));
			panel.setLayout(new MigLayout("", "[89px,grow,fill]", "[23px,grow][][]"));
			panel.add(getScrollPane_1(), "cell 0 0,grow");
			panel.add(getTextField(), "cell 0 1,growx");
			panel.add(getBtnSendChatMessage(), "cell 0 2");
		}
		return panel;
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

	public void pozoviMetoduProtokolaSendSHT(int opponent, int iEnter, int jEnter) {
		
		//PROVERI DA NE GADJAM ISTA POLJA
		
		seaFieldOpponent1.setEnableToAll(false);
		seaFieldOpponent2.setEnableToAll(false);
		String userName;
		if(opponent==1) userName=labelOpponent1.getText();
		else userName=labelOpponent2.getText();
		
		main.setMyShipsFrame.myClient.sendSHT(userName, iEnter, jEnter);
		System.out.println("Gadjao sam "+userName+iEnter+jEnter);
		
	}


	public void azurirajMojaPolja(int sifra, int i, int j) {
		
		//ili pogodio ili potovio
		if(sifra!=BattleShipStatus.SHIP_MISSED)
			seaFieldMy.seaButtonMatrix[i][j].setIcon(bombImg);
		else seaFieldMy.seaButtonMatrix[i][j].setIcon(seaMissImg);

	}

	public void azurirajOpponentsPolja(int status, int i, int j,int opponent) {
		
		if(opponent==1){
			if(status==BattleShipStatus.SHIP_MISSED){
				seaFieldOpponent1.seaButtonMatrix[i][j].setIcon(seaMissImg);
				textPane.setText("Igrac "+ labelOpponent1.getText() +" je promasen !\n" + main.mainGui.textPane.getText());  

			}
			
			if(status==BattleShipStatus.SHIP_HIT || status==BattleShipStatus.SHIP_SUNKED || status==BattleShipStatus.FLEET_DESTROYED){
			 seaFieldOpponent1.seaButtonMatrix[i][j].setIcon(bombImg);	
				textPane.setText("Igrac "+ labelOpponent1.getText() +" je pogodjen !\n" + main.mainGui.textPane.getText());  

			}
			
			
			if(status==BattleShipStatus.FLEET_DESTROYED){
				seaFieldOpponent1.setEnableToAll(false);
				textPane.setText("Igrac "+ labelOpponent1.getText() +" je UNISTEN !\n" + main.mainGui.textPane.getText());  
			}
		}
		else{// opponent 2
			if(status==BattleShipStatus.SHIP_MISSED){
				seaFieldOpponent2.seaButtonMatrix[i][j].setIcon(seaMissImg);
				textPane.setText("Igrac "+ labelOpponent2.getText() +" je promasen !\n" + main.mainGui.textPane.getText());  

			}
			
			if(status==BattleShipStatus.SHIP_HIT || status==BattleShipStatus.SHIP_SUNKED || status==BattleShipStatus.FLEET_DESTROYED){
			 seaFieldOpponent2.seaButtonMatrix[i][j].setIcon(bombImg);	
				textPane.setText("Igrac "+ labelOpponent2.getText() +" je pogodjen !\n" + main.mainGui.textPane.getText());  
			}
					
			if(status==BattleShipStatus.FLEET_DESTROYED){
				seaFieldOpponent2.setEnableToAll(false);
				textPane.setText("Igrac "+ labelOpponent2.getText() +" je UNISTEN !\n" + main.mainGui.textPane.getText());  
			//boolean postaje true
			}
			
		}
		
		
		
		
		
		
		
	}

	public void praviRedosledIgranja(String peer1Username,String peer2Username, int index1, int index2,int myIndex) {
		
		 redosledIgranja[myIndex-1] = myUserName;
		 redosledIgranja[index1-1] = peer1Username;
		 redosledIgranja[index2-1] = peer2Username;
		 		 
	}
	

	
//	public void koJeNaPotezu(){		
//		if(naPotezu==2) naPotezu=0;
//		else naPotezu++;
//		
//		zatamniLabele();
//	}
	
//	public void zatamniLabele(){
//		if(redosledIgranja[naPotezu].equals(labelMe.getText())) {  vratiSveLabele();}//podebljaj mene a druge vrati  
//		if(redosledIgranja[naPotezu].equals(labelOpponent1.getText())) {  vratiSveLabele();  labelOpponent1.setBorder(new LineBorder(new Color(0, 0, 0), 5));}
//		if(redosledIgranja[naPotezu].equals(labelOpponent2.getText())) {   vratiSveLabele();  labelOpponent2.setBorder(new LineBorder(new Color(0, 0, 0), 5));}
//	}
	
//	public void vratiSveLabele(){
//		labelMe.setBorder(new LineBorder(new Color(0, 0, 0), 0));
//		labelOpponent1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
//		labelOpponent2.setBorder(new LineBorder(new Color(0, 0, 0), 0));
//	}
	
	private JButton getBtnSendChatMessage() {
		if (btnSendChatMessage == null) {
			btnSendChatMessage = new JButton("Send message");
			btnSendChatMessage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!main.mainGui.textChatField.getText().equals("") && main.mainGui.textChatField.getText() != null) {
						main.setMyShipsFrame.myClient.sendCHT(main.mainGui.textChatField.getText());
						main.mainGui.textPaneChat.setText(labelMe.getText()+">: "+main.mainGui.textChatField.getText()+'\n'+main.mainGui.textPaneChat.getText());
						textChatField.setText("");
					}
				}
			});
		}
		return btnSendChatMessage;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTextPane_1());
		}
		return scrollPane_1;
	}
	private JTextPane getTextPane_1() {
		if (textPaneChat == null) {
			textPaneChat = new JTextPane();
			textPaneChat.setEditable(false);
		}
		return textPaneChat;
	}
	private JTextField getTextField() {
		if (textChatField == null) {
			textChatField = new JTextField();
			textChatField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(!main.mainGui.textChatField.getText().equals("") && main.mainGui.textChatField.getText() != null) {
						main.setMyShipsFrame.myClient.sendCHT(main.mainGui.textChatField.getText());
						main.mainGui.textPaneChat.setText(labelMe.getText()+">: "+main.mainGui.textChatField.getText()+'\n'+main.mainGui.textPaneChat.getText());
						textChatField.setText("");
					}
				}
			});
			textChatField.setColumns(10);
		}
		return textChatField;
	}
}
