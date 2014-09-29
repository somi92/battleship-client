package user_interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Main;
import net.miginfocom.swing.MigLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JCheckBox;

import application_logic.SetMyShipsManager;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SetMyShipsFrame extends JFrame {

	Main main = null;
	
	public SetMyShipsFrame me = this;
	SetMyShipsManager myShipsManager = new SetMyShipsManager(me);
	
	JButton[][] buttonGameBoard = myShipsManager.initializeButtonsforGameBoard();

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
//					SetMyShipsFrame frame = new SetMyShipsFrame();
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
	private JButton getBtnDone() {
		if (btnDone == null) {
			btnDone = new JButton("DONE");
			btnDone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					//USLOVI DA LI SU SVI BRODICI POSTAVLJENI
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
}

