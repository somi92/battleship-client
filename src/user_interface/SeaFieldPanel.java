package user_interface;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * 
 * SeaField that extends JPanel represents game board made of 100 JButtons.
 * Ready to be added on content pane.
 *
 */
public class SeaFieldPanel extends JPanel {

	public ImageIcon seaImg = new ImageIcon(getClass().getResource("/resources/sea.png"));
	public ImageIcon shipImg = new ImageIcon(getClass().getResource("/resources/brodic.jpg"));
	
	
	
	private JButton[][] seaButtonMatrix;
	
	
// 1. konstruktor
	public SeaFieldPanel() {
		setLayout(null);
		
		seaButtonMatrix = createSeaField();
		
		for(int row=0; row<10; row++)
			for(int col=0; col<10; col++)	
				//add(seaFieldMatrix[row][col],BorderLayout.CENTER);	
				add(seaButtonMatrix[row][col]);
	}
	

	/**
	 * @return 2D array of length 100, and all buttons inside are initialized and
	 * named in format of their index ["rowNumber"+"colNumber"]. Example: ["00", "01", "02", ... "99"]
	 */
		private JButton[][] createSeaField(){
						
				String cellName = "";
				JButton[][] matrix = new JButton[10][10];

				for(int row=0; row<10; row++)
					for(int col=0; col<10; col++){				
						cellName = ""+row+col;
						JButton btn = new JButton(seaImg);						
						btn.setName(cellName);
						btn.setBorder(null);
						btn.setBounds(((col*25)+10),((row*25)+25), 25, 25);
						
						matrix[row][col] = btn;
					}
				
				return matrix;
			}
		
		public void formirajMyFieldPane(int[][] logicMatrix){
			
			for(int i=0;i<10;i++)
				for(int j=0;j<10;j++)				
					if(logicMatrix[i][j] != 0) seaButtonMatrix[i][j].setIcon(shipImg);
			
		}
		
}
