package user_interface;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * 
 * SeaField that extends JPanel represents game board made of 100 JButtons.
 * @author Stefan
 *
 */
public class SeaField extends JPanel {

	private JButton[][] seaFieldMatrix;
	
	public SeaField() {
		setLayout(null);
		
		seaFieldMatrix = createSeaField();
		
		for(int row=0; row<10; row++)
			for(int col=0; col<10; col++)	
				//add(seaFieldMatrix[row][col],BorderLayout.CENTER);	
				add(seaFieldMatrix[row][col]);
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
						cellName = "cell"+row+col;
						JButton btn = new JButton(cellName);
						btn.setName(cellName);
						btn.setBorder(null);
						btn.setBounds(((col*25)+10),((row*25)+25), 25, 25);
						
						matrix[row][col] = btn;
					}
				
				return matrix;
			}

}
