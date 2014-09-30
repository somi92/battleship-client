package user_interface;

import interfaces.ClientEventListener;
import interfaces.PeerEventListener;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import main.Main;
import net.miginfocom.swing.MigLayout;
import network_communication.ClientThread;
import network_communication.CommunicationController;
import network_communication.ServerSideThread;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


import application_logic.SetMyShipsManager;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SetMyShipsFrame extends JFrame {

	Main main = null;
	
	public SetMyShipsFrame me = this;
	SetMyShipsManager myShipsManager = new SetMyShipsManager(me);
	
	JButton[][] buttonGameBoard = myShipsManager.initializeButtonsforGameBoard();
	
	String myUserName = "";
	String mainServerIP = "192.168.1.181";
	
	
	
	
	private JPanel contentPane;
	private JPanel centerPanel;
	private JPanel upPanel;
	private JRadioButton rdbtnOneCellShips;
	private JRadioButton rdbtnTwoCellShips;
	private JRadioButton rdbtnThreeCellsShips;
	private JRadioButton rdbtnFiveCellShips;
	private JPanel leftPanel;
	public ButtonGroup groupShips;
	public ButtonGroup groupHV;
	
	public int shipSize = 1;
	public char orijentation = 'H';
	

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SetMyShipsFrame frame = new SetMyShipsFrame(null);
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
	public SetMyShipsFrame(Main main) {
		this.main = main;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getCenterPanel(), BorderLayout.CENTER);
		contentPane.add(getUpPanel(), BorderLayout.NORTH);
		contentPane.add(getLeftPanel(), BorderLayout.WEST);
		
		groupShips = new ButtonGroup();
		groupShips.add(getRdbtnOneCellShips());
		groupShips.add(rdbtnTwoCellShips);
		groupShips.add(rdbtnThreeCellsShips);
		groupShips.add(rdbtnFiveCellShips);
		
		groupHV = new ButtonGroup();
		groupHV.add(rdbtnHorisontal);
		groupHV.add(rdbtnVertical);
	}

	
	private JRadioButton rdbtnHorisontal;
	private JRadioButton rdbtnVertical;
	private JLabel lblLinija;
	private JLabel lblOneCellsShip;
	private JLabel lblTwoCellsShip;
	private JLabel lblThreeCellsShip;
	private JLabel lblFrourCellsShip;
	private JButton btnDone;
	private JButton btnCancle;
	private JLabel label;
	
	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
//			centerPanel.setLayout(new BorderLayout(0, 0)); //<-
			//centerPanel.setLayout(new MigLayout("", "[left][38.00][]", "[][][]"));
			centerPanel.setLayout(new MigLayout("gap 0px 0px", "[][][]", "[][][][][][]"));
			for(int i=0;i<10;i++){
				for (int j=0;j<10;j++){
					buttonGameBoard[i][j].setSize(40, 40);
					centerPanel.add(buttonGameBoard[i][j], "cell "+j+" "+i+"");
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
	private JRadioButton getRdbtnOneCellShips() {
		if (rdbtnOneCellShips == null) {
			rdbtnOneCellShips = new JRadioButton("One cell ships:");
			rdbtnOneCellShips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
				refreshShipSize();

				}
			});
			rdbtnOneCellShips.setSelected(true);
		}
		return rdbtnOneCellShips;
	}
	private JRadioButton getRdbtnTwoCellShips() {
		if (rdbtnTwoCellShips == null) {
			rdbtnTwoCellShips = new JRadioButton("Two cell ships:");
			rdbtnTwoCellShips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
				refreshShipSize();
				
				}
			});
		}
		return rdbtnTwoCellShips;
	}
	private JRadioButton getRdbtnThreeCellsShips() {
		if (rdbtnThreeCellsShips == null) {
			rdbtnThreeCellsShips = new JRadioButton("Three cells ships:");
			rdbtnThreeCellsShips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					refreshShipSize();
					
				}
			});
		}
		return rdbtnThreeCellsShips;
	}
	private JRadioButton getRdbtnFiveCellShips() {
		if (rdbtnFiveCellShips == null) {
			rdbtnFiveCellShips = new JRadioButton("Five cell ships:");
			rdbtnFiveCellShips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
				refreshShipSize();
				
				}
			});
		}
		return rdbtnFiveCellShips;
	}
	private JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
			leftPanel.setLayout(new MigLayout("", "[][]", "[][][][][][][][][][][][]"));
			leftPanel.add(getRdbtnOneCellShips(), "cell 0 1");
			leftPanel.add(getLblOneCellsShip(), "cell 1 1");
			leftPanel.add(getRdbtnTwoCellShips(), "cell 0 2");
			leftPanel.add(getLblTwoCellsShip(), "cell 1 2");
			leftPanel.add(getRdbtnThreeCellsShips(), "cell 0 3");
			leftPanel.add(getLblThreeCellsShip(), "cell 1 3");
			leftPanel.add(getRdbtnFiveCellShips(), "cell 0 4");
			leftPanel.add(getLblFrourCellsShip(), "cell 1 4");
			leftPanel.add(getLblLinija(), "cell 0 5,growx,aligny center");
			leftPanel.add(getRdbtnHorisontal(), "cell 0 7");
			leftPanel.add(getRdbtnVertical(), "cell 0 8");
			leftPanel.add(getLabel(), "cell 0 9,growx");
			leftPanel.add(getBtnDone(), "cell 0 10,growx");
			leftPanel.add(getBtnCancle(), "cell 0 11,growx");
		}
		return leftPanel;
	}
	private JRadioButton getRdbtnHorisontal() {
		if (rdbtnHorisontal == null) {
			rdbtnHorisontal = new JRadioButton("Horisontal");
			rdbtnHorisontal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
				refreshOrjientation();
				
				}
			});
			rdbtnHorisontal.setSelected(true);
		}
		return rdbtnHorisontal;
	}
	private JRadioButton getRdbtnVertical() {
		if (rdbtnVertical == null) {
			rdbtnVertical = new JRadioButton("Vertical");
			rdbtnVertical.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				refreshOrjientation();
				
				}
			});
		}
		return rdbtnVertical;
	}
	private JLabel getLblLinija() {
		if (lblLinija == null) {
			lblLinija = new JLabel("-----------------");
			lblLinija.setHorizontalTextPosition(SwingConstants.CENTER);
			lblLinija.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblLinija;
	}
	private JLabel getLblOneCellsShip() {
		if (lblOneCellsShip == null) {
			lblOneCellsShip = new JLabel("1");
			
		}
		return lblOneCellsShip;
	}
	private JLabel getLblTwoCellsShip() {
		if (lblTwoCellsShip == null) {
			lblTwoCellsShip = new JLabel("1");
		}
		return lblTwoCellsShip;
	}
	private JLabel getLblThreeCellsShip() {
		if (lblThreeCellsShip == null) {
			lblThreeCellsShip = new JLabel("2");
		}
		return lblThreeCellsShip;
	}
	private JLabel getLblFrourCellsShip() {
		if (lblFrourCellsShip == null) {
			lblFrourCellsShip = new JLabel("1");
		}
		return lblFrourCellsShip;
	}
	
	/**
	 * metoda postavlja vrednost int shipSize u zavisnosti koji RadioButton je checked.
	 */
	public void refreshShipSize(){
		
		if(rdbtnOneCellShips.isSelected()) shipSize=1;
		if(rdbtnTwoCellShips.isSelected()) shipSize=2;
		if(rdbtnThreeCellsShips.isSelected()) shipSize=3;
		if(rdbtnFiveCellShips.isSelected()) shipSize=5;
	}
	
	public void refreshOrjientation(){
		
		if(rdbtnHorisontal.isSelected()) orijentation='H';
		if(rdbtnVertical.isSelected()) orijentation='V';
		
	}
	
/**
 * Metoda proveri koji radioButton je selektovan (velicina broda) i vodi racuna o boriju postavljenih brodova.
 * Koristi je setMyShipsManager
 * @return int index - sifru broda 
 */
	public int updateLabels(){
		int indeks = 0;
		JRadioButton randomJB = null;
		int moreShips = 0;
		JLabel randomL = null;
		
		if(rdbtnOneCellShips.isSelected()){
			indeks=1;
			randomJB=rdbtnOneCellShips;
			randomL=lblOneCellsShip;
		}
		
		if(rdbtnTwoCellShips.isSelected()){
			indeks=2;
			randomJB=rdbtnTwoCellShips;
			randomL=lblTwoCellsShip;;
		}
		
		if(rdbtnThreeCellsShips.isSelected()){
			randomJB=rdbtnThreeCellsShips;
			randomL=lblThreeCellsShip;

			if(randomL.getText().equals("2")) indeks=3;
			else indeks=4;//kada se drugi put bira brod tipa 3 celije

		}
		
		if(rdbtnFiveCellShips.isSelected()){
			indeks=5;
			randomJB=rdbtnFiveCellShips;
			randomL=lblFrourCellsShip;
		}
		
		moreShips=Integer.parseInt(randomL.getText());
		
		if(moreShips > 0){
			moreShips--;
			randomL.setText(""+moreShips);
			if(moreShips==0){
				randomJB.setEnabled(false);
				randomJB.setSelected(false);
				}
			return indeks;
		}else{
			randomJB.setSelected(false);
			randomJB.setEnabled(false);
			return -1;}
	}	
	
//------------------------------------------------	
	
	
	private JButton getBtnDone() {
		if (btnDone == null) {
			btnDone = new JButton("DONE");
			btnDone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
// prebacujem logicalMatrix iz setMyShipsManager u setMyShipFrame
					 
					main.mainGui.logicMatrixMine = myShipsManager.logicMatrix;
					main.mainGui.popuniniMojePoljeBrodicima();
					
					//USLOVI DA LI SU SVI BRODICI POSTAVLJENI
					main.mainGui.labelMe.setText(myUserName);
					
					sveKrene();
				
					main.mainGui.setVisible(true);
					setVisible(false);
				}
			});
			btnDone.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnDone;
	}
	private JButton getBtnCancle() {
		if (btnCancle == null) {
			btnCancle = new JButton("CANCLE");
			btnCancle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					main.startFrame.setVisible(true);
					setVisible(false);
					
				}
			});
			btnCancle.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnCancle;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("-----------------");
			label.setHorizontalTextPosition(SwingConstants.CENTER);
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label;
		}
	
	
	//
	BlockingQueue<String> queue;
	CommunicationController controler;
	ServerSideThread myServer;
	ClientThread myClient;
	
	

	public void setUserAndServerIP(String userName, String serverIP) {
		this.myUserName = userName;
		this.mainServerIP = serverIP;
	}
	
	
	public void sveKrene(){

		queue = new ArrayBlockingQueue<String>(10);
		controler = new CommunicationController();
		myServer = new ServerSideThread(queue,0,myUserName);
		myClient = new ClientThread(controler, queue, "192.168.1.181", 9080);
		
		myClient.setClientEventListener(new ClientEventListener() {
			
			@Override
			public void onWait(String message) {  
		    	  EventQueue.invokeLater(new Runnable() {
				      public void run() {
				    	  main.mainGui.textPane.setText("Prijavljeni ste na glavni server ali se ceka jos igraca.\n" + main.mainGui.textPane.getText());
				       }
				 });		
			}
			
			@Override
			public void onStart(String message) {
				EventQueue.invokeLater(new Runnable() {
				      public void run() {
				    	  main.mainGui.textPane.setText("Postoji dovoljan broj igraca na serveru.\n" + main.mainGui.textPane.getText());
				       }
				 });
				
			}
			
			@Override
			public void onBye(String message) {
				EventQueue.invokeLater(new Runnable() {
				      public void run() {
				    	  main.mainGui.textPane.setText("Sisao si sa servera.\n" + main.mainGui.textPane.getText());
				       }
				 });
				
			}
		});
		
		myClient.setPeerEventListener(new PeerEventListener() {
			
			@Override
			public void onSynchronized() {
				EventQueue.invokeLater(new Runnable() {
				      public void run() {  
						    	  main.mainGui.textPane.setText("Sinhronizovani !\n" + main.mainGui.textPane.getText());  
				      }
				 });
				
			}
			
			@Override
			public void onRnd(final boolean myTurn, int myRND, int myIndex,
					final String peer1Username, int peer1Index, final String peer2Username,
					int peer2Index) {
						
				 EventQueue.invokeLater(new Runnable() {
				      public void run() {
						main.mainGui.labelOpponent1.setText(peer1Username);
						main.mainGui.labelOpponent2.setText(peer2Username);
						
						//PRATI KO JE NA REDu
						
						
						if(myTurn){
					main.mainGui.seaFieldOpponent1.setEnableToAll(true);
							main.mainGui.seaFieldOpponent2.setEnableToAll(true);
							main.mainGui.textPane.setText("Ti si na potezu.\n" + main.mainGui.textPane.getText());  
						} 
						
				       }
				 });
				
		
			}
			
			@Override
			public void onNext(final String username, final boolean myTurn) {
				 EventQueue.invokeLater(new Runnable() {
				      public void run() {
						main.mainGui.textPane.setText("Igrac "+ username +" je propustio potez ili je diskonektovan.\n" + main.mainGui.textPane.getText());  
						if(myTurn){
//						main.mainGui.seaFieldOpponent1.setEnableToAll(true);
	//					main.mainGui.seaFieldOpponent2.setEnableToAll(true);
							main.mainGui.textPane.setText("Ti si na potezu.\n" + main.mainGui.textPane.getText());  
						} 
				      }
				 });
				
			}
			
			@Override
			public void onBye() {
				EventQueue.invokeLater(new Runnable() {
				      public void run() {
						main.mainGui.textPane.setText("Kraj igre. ??? \n" + main.mainGui.textPane.getText());  
				       }
				 });
			}
			
			//Mene gadja NEKO, proverim moju logigicku matricu i vratim status poteza pogledati interface BattleShipStatus
			@Override
			public int onAttacked(int coorI, int coorJ) {
				
				int sifra;
				sifra = myShipsManager.gameBoardMask.takeAHit(coorI, coorJ);
				main.mainGui.azurirajMojaPolja(sifra,coorI,coorJ);
				
				return sifra;
			}
			
			// onaj ko je gadjan, njegove kordinate i status
			//ako je myTurn tru znaci otkljucavam sva polja
			@Override
			public void onAttackResponse(String username, int coorI, int coorJ,
					int status, boolean myTurn) {
				
				if(!username.equals(myUserName)){
					if(username.equals(main.mainGui.labelOpponent1.getText())){
						main.mainGui.azurirajOpponentsPolja(status,coorI,coorJ,1);
					}
					else{//opponent 2 je
						if(username.equals(main.mainGui.labelOpponent2.getText()))
							main.mainGui.azurirajOpponentsPolja(status,coorI,coorJ,2);
					}	
				}	
				
				///
				
				
				
				
				main.mainGui.textPane.setText("Igrac "+ username +" je gadjan.\n" + main.mainGui.textPane.getText());  
				
				if(myTurn){
					main.mainGui.seaFieldOpponent1.setEnableToAll(true);
					main.mainGui.seaFieldOpponent2.setEnableToAll(true);
					main.mainGui.textPane.setText("Ti si na potezu.\n" + main.mainGui.textPane.getText() );  
				} 
				
			}
		});
		
		
		Thread server = new Thread(myServer);
		Thread client = new Thread(myClient);
		
		server.start();
		client.start();

	}
	
	
}

