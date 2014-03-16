package application_logic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SetMyShipsManager {

	public JButton[][] myButtonGameBoard = new JButton[10][10];
	public ImageIcon papirImg = new ImageIcon(getClass().getResource("/resources/sea.png"));
	
	//mouse enter / exit coordinates
public int iEnter;
public int jEnter;
public int iExit;
public int jExit;
	
	
	public JButton[][] generateGameBoard(){
		
		for(int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				myButtonGameBoard[i][j]= new JButton(papirImg);
				myButtonGameBoard[i][j].setName(i+""+j);
				myButtonGameBoard[i][j].setBorderPainted(false);
//				buttonGameBoard[i][j].setMargin(new Insets(0, 0, 0, 0));
				myButtonGameBoard[i][j].setMaximumSize(new Dimension(30, 30));
				myButtonGameBoard[i][j].setMinimumSize(new Dimension(30, 30));
				
				final JButton btn = myButtonGameBoard[i][j];
				
				borderPaintActionListener(btn);
				enterActionListener(btn);
				exitActionListener(btn);
				setToolTipEffect(btn);
			}
		}
		
		return myButtonGameBoard;
		
	}
	
	public void borderPaintActionListener(final JButton btn){
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						btn.setBorderPainted(true);
				
			}
		});
	}
	
	
	public void enterActionListener(final JButton btn){
		btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				
				String name = btn.getName();
				char i = name.charAt(0);
				char j = name.charAt(1);
				
				iEnter = Integer.parseInt(i+"");
				jEnter = Integer.parseInt(j+"");
//				System.out.println("Enter: " + +iEnter+ " " +jEnter);
//				btn.setBorderPainted(true);
				
				
			}
		});
	}
	
	

	public void exitActionListener(final JButton btn){
		btn.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				
				String name = btn.getName();
				char i = name.charAt(0);
				char j = name.charAt(1);
				
				iExit = Integer.parseInt(i+"");
				jExit = Integer.parseInt(j+"");
//				System.out.println("Exit: "+iExit+ " " +jExit);
//				btn.setBorderPainted(false);
				
			}
		});
	}

	public void setToolTipEffect(final JButton btn){
		
		btn.setToolTipText(btn.getName());
		
	}

/**
 * vraca niz sa mogucim indeksima
 * @param i
 * @param j
 * @param horOrVer
 * @param size
 * @return [size, myI,myJ, ... ]
 */
	public int[] calculateButtonsForRecommand(int i, int j, String horOrVer,int size){
		
		int[] suggestIndexes=null;
		switch(size){
		case 1: {
			suggestIndexes=new int[3];
			break;
		}
		case 2:{
			suggestIndexes=new int[5];
			break;
		}
		case 3:{
			suggestIndexes=new int[7];
			break;
		}
		case 5:{
			suggestIndexes=new int[11];
			break;
		}
		default: return suggestIndexes;
				}
		
		
		
		if(horOrVer.equals("H")){// ako je horizontalno
			switch(size){
			case 1:{			
				suggestIndexes[0]=size;
				suggestIndexes[1]=iEnter;
				suggestIndexes[2]=jEnter;
				return suggestIndexes;
			}
			// [ 0  0  1  1  0  0  0 ]
			//           my
			case 2:{
				suggestIndexes[0]=size;
				suggestIndexes[1]=iEnter;
				suggestIndexes[2]=jEnter;
				suggestIndexes[3]=iEnter;
				suggestIndexes[4]=jEnter-1;
				return suggestIndexes;
			}
			// [ 0  0  1  1  1  0  0 ]
			//            my
			case 3:{
				suggestIndexes[0]=size;
				suggestIndexes[1]=iEnter;
				suggestIndexes[2]=jEnter-1;
				suggestIndexes[3]=iEnter;
				suggestIndexes[4]=jEnter;
				suggestIndexes[5]=iEnter;
				suggestIndexes[6]=jEnter+1;
				return suggestIndexes;
			}
			// [ 0  1  1  1  1  1  0  0]
			//            my
			case 5:{
				suggestIndexes[0]=size;
				suggestIndexes[1]=iEnter;
				suggestIndexes[2]=jEnter-2;
				suggestIndexes[3]=iEnter;
				suggestIndexes[4]=jEnter-1;
				suggestIndexes[5]=iEnter;
				suggestIndexes[6]=jEnter;
				suggestIndexes[7]=iEnter;
				suggestIndexes[8]=jEnter+1;
				suggestIndexes[9]=iEnter;
				suggestIndexes[10]=jEnter+2;
				return suggestIndexes;
			}
		}//kraj switch-a
			return null;
		}//kraj if
		else{ //ako je vertikalno
			switch(size){
				case 1:{			
					suggestIndexes[0]=size;
					suggestIndexes[1]=iEnter;
					suggestIndexes[2]=jEnter;
					return suggestIndexes;
				}
				case 2:{
					suggestIndexes[0]=size;
					suggestIndexes[1]=iEnter-1;
					suggestIndexes[2]=jEnter;
					suggestIndexes[3]=iEnter;
					suggestIndexes[4]=jEnter;
					return suggestIndexes;
				}
				case 3:{
					suggestIndexes[0]=size;
					suggestIndexes[1]=iEnter-1;
					suggestIndexes[2]=jEnter;
					suggestIndexes[3]=iEnter;
					suggestIndexes[4]=jEnter;
					suggestIndexes[5]=iEnter+1;
					suggestIndexes[6]=jEnter;
					return suggestIndexes;
				}
				case 5:{
					suggestIndexes[0]=size;
					suggestIndexes[1]=iEnter-2;
					suggestIndexes[2]=jEnter;
					suggestIndexes[3]=iEnter-1;
					suggestIndexes[4]=jEnter;
					suggestIndexes[5]=iEnter;
					suggestIndexes[6]=jEnter;
					suggestIndexes[7]=iEnter+1;
					suggestIndexes[8]=jEnter;
					suggestIndexes[9]=iEnter+2;
					suggestIndexes[10]=jEnter;
					return suggestIndexes;
				}
			}//kraj switch-a
			return null;
		}//kraj vertikalno			
	}// kraj metode
	

	/**
	 * Metoda vraca indeks buttona koji trazim
	 * @param i witch part of ship [ 0  0  X  1  1  0  0 ]  x=1
	 * @param suggestIndexes
	 * @return
	 */
	public int[] getButtonFromSuggestedButtons(int position, int[] suggestedIndexes){
	
		int[] indexOfButton = new int[2];
	
		switch(suggestedIndexes[0]){
			case 1: {
				indexOfButton[0] = suggestedIndexes[1];
				indexOfButton[1] = suggestedIndexes[2];
				break;
			}
			case 2:{
				if(position==1){
					indexOfButton[0] = suggestedIndexes[1];
					indexOfButton[1] = suggestedIndexes[2];
				}
				if(position==2){
					indexOfButton[0] = suggestedIndexes[3];
					indexOfButton[1] = suggestedIndexes[4];
				}
				break;
			}
			case 3:
				if(position==1){
					indexOfButton[0] = suggestedIndexes[1];
					indexOfButton[1] = suggestedIndexes[2];
				}
				if(position==2){
					indexOfButton[0] = suggestedIndexes[3];
					indexOfButton[1] = suggestedIndexes[4];
				}
				if(position==3){
					indexOfButton[0] = suggestedIndexes[5];
					indexOfButton[1] = suggestedIndexes[6];
				}
				break;	
			case 5: {
				if(position==1){
					indexOfButton[0] = suggestedIndexes[1];
					indexOfButton[1] = suggestedIndexes[2];
				}
				if(position==2){
					indexOfButton[0] = suggestedIndexes[3];
					indexOfButton[1] = suggestedIndexes[4];
				}
				if(position==3){
					indexOfButton[0] = suggestedIndexes[5];
					indexOfButton[1] = suggestedIndexes[6];
				}
				if(position==4){
					indexOfButton[0] = suggestedIndexes[7];
					indexOfButton[1] = suggestedIndexes[8];
				}
				if(position==5){
					indexOfButton[0] = suggestedIndexes[9];
					indexOfButton[1] = suggestedIndexes[10];
				}
				break;
			}
		}//kraj switch-a
		return indexOfButton;
	}//kraj metode


	public String[] lightTheSuggestedButtons(int[] suggestedIndexes){
		
		String[] btnNames = null;
		
		for (int i=1;i<suggestedIndexes.length;i++) {
			btnNames[i] = getButtonFromSuggestedButtons(i, suggestedIndexes)+"";
		}
		return btnNames;
	}



}
