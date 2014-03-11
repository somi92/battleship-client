package application_logic;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyShipsManager {

	JButton[][] buttonGameBoard = new JButton[10][10];
	ImageIcon papirImg = new ImageIcon(getClass().getResource("/resources/sea.png"));
	
	
	public JButton[][] generateGameBoard(){
		
		for(int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				buttonGameBoard[i][j]= new JButton(papirImg);
				buttonGameBoard[i][j].setBorderPainted(false);
				buttonGameBoard[i][j].setMargin(new Insets(0, 0, 0, 0));
				buttonGameBoard[i][j].setMaximumSize(new Dimension(30, 30));
				buttonGameBoard[i][j].setMinimumSize(new Dimension(30, 30));
				
				final JButton btn = buttonGameBoard[i][j];
				
				buttonGameBoard[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						btn.setBorderPainted(true);	
						
					}
				});
				
			}
		}
		
		return buttonGameBoard;
		
	}
	
}
