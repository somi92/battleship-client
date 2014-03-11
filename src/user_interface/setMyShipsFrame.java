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

import application_logic.MyShipsManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class setMyShipsFrame extends JFrame {

	private JPanel contentPane;
	private JPanel centerPanel;
	private JPanel upPanel;
	private JRadioButton rdbtnOneCellShips;
	private JRadioButton rdbtnTwoCellShips;
	private JRadioButton rdbtnThreeCellsShips;
	private JRadioButton rdbtnForuCellShips;
	private JButton btnHorisontal;
	private JButton btnVertical;
	private JPanel leftPanel;
	public ButtonGroup group;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setMyShipsFrame frame = new setMyShipsFrame();
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
	public setMyShipsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getCenterPanel(), BorderLayout.CENTER);
		contentPane.add(getUpPanel(), BorderLayout.NORTH);
		contentPane.add(getLeftPanel(), BorderLayout.WEST);
		
		group = new ButtonGroup();
		group.add(getRdbtnOneCellShips());
		group.add(rdbtnTwoCellShips);
		group.add(rdbtnThreeCellsShips);
		group.add(rdbtnForuCellShips);
	}

	MyShipsManager shipManager = new MyShipsManager();
	JButton[][] buttonGameBoard = shipManager.generateGameBoard();
	
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
			rdbtnThreeCellsShips = new JRadioButton("Three cells ships");
		}
		return rdbtnThreeCellsShips;
	}
	private JRadioButton getRdbtnForuCellShips() {
		if (rdbtnForuCellShips == null) {
			rdbtnForuCellShips = new JRadioButton("Foru cell ships:");
		}
		return rdbtnForuCellShips;
	}
	private JButton getBtnHorisontal() {
		if (btnHorisontal == null) {
			btnHorisontal = new JButton("Horisontal");
		}
		return btnHorisontal;
	}
	private JButton getBtnVertical() {
		if (btnVertical == null) {
			btnVertical = new JButton("Vertical");
			btnVertical.setMinimumSize(new Dimension(81, 23));
			btnVertical.setMaximumSize(new Dimension(81, 23));
			btnVertical.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
		}
		return btnVertical;
	}
	private JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
			leftPanel.setLayout(new MigLayout("", "[]", "[][][][][][][][]"));
			leftPanel.add(getRdbtnOneCellShips(), "cell 0 1");
			leftPanel.add(getRdbtnTwoCellShips(), "cell 0 2");
			leftPanel.add(getRdbtnThreeCellsShips(), "cell 0 3");
			leftPanel.add(getRdbtnForuCellShips(), "cell 0 4");
			leftPanel.add(getBtnHorisontal(), "cell 0 6,alignx left");
			leftPanel.add(getBtnVertical(), "cell 0 7,alignx left");
		}
		return leftPanel;
	}
}
