package application_logic;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import user_interface.GameFrame;

public class GameFrameManager {

	public JButton[][] buttonGameBoard = new JButton[10][10];
	public ImageIcon seaImage = new ImageIcon(getClass().getResource("/resources/sea.png"));
	public ImageIcon ShipImage = new ImageIcon(getClass().getResource("/resources/brodic.jpg"));

	public static int iEnter;
	public static int jEnter;
	
//public String [] suggesetedButtonNames=null;
//public LinkedList<String> existingBoats=new LinkedList<String>();

	public int x;
	public int y;

	public GameFrame gameFrame;

	public GameFrameManager(GameFrame gameFrame) {
	this.gameFrame = gameFrame;
}

	/**
		Ime dugmeta je "ij"
	 * konstuktor za matricu-GameBoard
	 */
	public JButton[][] generateGameBoard(){
		
		for(int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				
				buttonGameBoard[i][j]= new JButton(seaImage);
				buttonGameBoard[i][j].setName(i+""+j);
				buttonGameBoard[i][j].setBorderPainted(false);
				buttonGameBoard[i][j].setMaximumSize(new Dimension(30, 30));
				buttonGameBoard[i][j].setMinimumSize(new Dimension(30, 30));
				
				final JButton cell = buttonGameBoard[i][j];
				
//				clickActionListener(cell);
//				borderPaintActionListener(btn);

			}
		}
		
		return buttonGameBoard;
		
	}
	
	
	public void clickActionListener(final JButton btn){
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
	}
	
	public void borderPaintActionListener(final JButton btn){
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
			}
		});
	}	

	public void setToolTipEffect(final JButton btn){
		btn.setToolTipText(btn.getName());	
	}
	
	public void setAllButtonsEnabled(){
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++){
				buttonGameBoard[i][j].setEnabled(true);
			}
	}
	
	
}
