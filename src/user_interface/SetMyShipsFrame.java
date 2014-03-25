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
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JCheckBox;

import application_logic.SetMyShipsManager;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SetMyShipsFrame extends JFrame {

	private JPanel contentPane;
	private JPanel centerPanel;
	private JPanel upPanel;
	private JRadioButton rdbtnOneCellShips;
	private JRadioButton rdbtnTwoCellShips;
	private JRadioButton rdbtnThreeCellsShips;
	private JRadioButton rdbtnForuCellShips;
	private JPanel leftPanel;
	public ButtonGroup groupShips;
	public ButtonGroup groupHV;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetMyShipsFrame frame = new SetMyShipsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SetMyShipsFrame() {
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
		groupShips.add(rdbtnForuCellShips);
		
		groupHV = new ButtonGroup();
		groupHV.add(rdbtnHorisontal);
		groupHV.add(rdbtnVertical);
	}

	SetMyShipsManager shipManager = new SetMyShipsManager();
	JButton[][] buttonGameBoard = shipManager.generateGameBoard();
	
	private JRadioButton rdbtnHorisontal;
	private JRadioButton rdbtnVertical;
	private JLabel lblLinija;
	private JLabel lblOneCellsShip;
	private JLabel lblTwoCellsShip;
	private JLabel lblThreeCellsShip;
	private JLabel lblFrourCellsShip;
	
	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
			//centerPanel.setLayout(new MigLayout("", "[left][38.00][]", "[][][]"));
			centerPanel.setLayout(new MigLayout("gap 0px 0px", "[][]", "[][]"));
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
					
				}
			});
			rdbtnOneCellShips.setSelected(true);
		}
		return rdbtnOneCellShips;
	}
	private JRadioButton getRdbtnTwoCellShips() {
		if (rdbtnTwoCellShips == null) {
			rdbtnTwoCellShips = new JRadioButton("Two cell ships:");
		}
		return rdbtnTwoCellShips;
	}
	private JRadioButton getRdbtnThreeCellsShips() {
		if (rdbtnThreeCellsShips == null) {
			rdbtnThreeCellsShips = new JRadioButton("Three cells ships:");
		}
		return rdbtnThreeCellsShips;
	}
	private JRadioButton getRdbtnForuCellShips() {
		if (rdbtnForuCellShips == null) {
			rdbtnForuCellShips = new JRadioButton("Foru cell ships:");
		}
		return rdbtnForuCellShips;
	}
	private JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
			leftPanel.setLayout(new MigLayout("", "[][]", "[][][][][][][][][][]"));
			leftPanel.add(getRdbtnOneCellShips(), "cell 0 1");
			leftPanel.add(getLblOneCellsShip(), "cell 1 1");
			leftPanel.add(getRdbtnTwoCellShips(), "cell 0 2");
			leftPanel.add(getLblTwoCellsShip(), "cell 1 2");
			leftPanel.add(getRdbtnThreeCellsShips(), "cell 0 3");
			leftPanel.add(getLblThreeCellsShip(), "cell 1 3");
			leftPanel.add(getRdbtnForuCellShips(), "cell 0 4");
			leftPanel.add(getLblFrourCellsShip(), "cell 1 4");
			leftPanel.add(getLblLinija(), "cell 0 5,growx,aligny center");
			leftPanel.add(getRdbtnHorisontal(), "cell 0 7");
			leftPanel.add(getRdbtnVertical(), "cell 0 8");
		}
		return leftPanel;
	}
	private JRadioButton getRdbtnHorisontal() {
		if (rdbtnHorisontal == null) {
			rdbtnHorisontal = new JRadioButton("Horisontal");
			rdbtnHorisontal.setSelected(true);
		}
		return rdbtnHorisontal;
	}
	private JRadioButton getRdbtnVertical() {
		if (rdbtnVertical == null) {
			rdbtnVertical = new JRadioButton("Vertical");
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
			lblOneCellsShip = new JLabel("5");
		}
		return lblOneCellsShip;
	}
	private JLabel getLblTwoCellsShip() {
		if (lblTwoCellsShip == null) {
			lblTwoCellsShip = new JLabel("3");
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
}
