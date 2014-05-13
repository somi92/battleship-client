package application_logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.AllPermission;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import user_interface.SetMyShipsFrame;

public class SetMyShipsManager {

	public JButton[][] myButtonGameBoard = new JButton[10][10];
	public ImageIcon papirImg = new ImageIcon(getClass().getResource("/resources/sea.png"));
	public ImageIcon ShipImage = new ImageIcon(getClass().getResource("/resources/brodic.jpg"));

	//mouse enter / exit coordinates
	public static int[] suggestedIndexes;
	public static int iEnter;
public static int jEnter;
public int iExit;
public int jExit;

public String [] suggesetedButtonNames=null;
public LinkedList<String> existingBoats=new LinkedList<String>();

MyGameBoardMask gb1= new MyGameBoardMask();
public int x;
public int y;

public SetMyShipsFrame frame;


	
	/**
	 * @wbp.parser.entryPoint
	 */
	public SetMyShipsManager(SetMyShipsFrame setMyShipsFrame) {
	this.frame = setMyShipsFrame;
}

	/**
	 * @wbp.parser.entryPoint
	 */
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
				
				clickActionListener(btn);
//				borderPaintActionListener(btn);
				enterActionListener(btn);
//				enterActionListener(btn);
				exitActionListener(btn);
//				setToolTipEffect(btn);
			}
		}
		
		return myButtonGameBoard;
		
	}
	public void clickActionListener(final JButton btn){
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i=0;i<suggesetedButtonNames.length;i++){
					if(existingBoats.contains(suggesetedButtonNames[i])){
						return;
					}
				
				}
				int tipBroda=frame.updateLabels();
				if((tipBroda)>0){
					
					
				for(int i=0;i<10;i++)
					for(int j=0;j<10;j++){
						if(myButtonGameBoard[i][j].isBorderPainted())
						{
							existingBoats.add(myButtonGameBoard[i][j].getName());
							myButtonGameBoard[i][j].setIcon(ShipImage);
							gb1.FillStartMatrix(i,j,tipBroda);
						}
					}
				for(int i=0;i<existingBoats.size();i++){
					System.out.print(" "+existingBoats.get(i));
				}
				
			}
				gb1.ispisi();}
		});
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
				char iChar = name.charAt(0);
				char jChar = name.charAt(1);
				
				iEnter = Integer.parseInt(iChar+"");
				jEnter = Integer.parseInt(jChar+"");
				System.out.println("Enter: " + +iEnter+ " " +jEnter);
//				btn.setBorderPainted(true);

//				for(int i=0 ;i<btnNames.length;i++)
//					if(btn.getName().equals(btnNames[i]))
//						btn.setBorderPainted(true);
				
				
				String[] btnNames = lightTheSuggestedButtons(suggestedIndexes(iEnter, jEnter, frame.orijentation, frame.shipSize));
				
				
//				for (String btnName : btnNames) {
//					System.out.println("iEnter : jEnter = " + iEnter +" : "+ jEnter);
//					System.out.println("5/  btn name: " + btnName);
//				}
				
				try {
					for(int i=0; i<btnNames.length;i++){

							char iChar0 = btnNames[0].charAt(0);
							char jChar0 = btnNames[0].charAt(1);
							
							int a = Integer.parseInt(iChar0+"");
							int b = Integer.parseInt(jChar0+"");
							

							
							char iChar1 = btnNames[i].charAt(0);
							char jChar1 = btnNames[i].charAt(1);
							x = Integer.parseInt(iChar1+"");
							y = Integer.parseInt(jChar1+"");
							
							if(btnNames[i].length()==3 || a<0 || b<0 ){
								
							
								throw new Exception();
								}
							else{
								myButtonGameBoard[x][y].setBorderPainted(true);
							}
								setAllButtonsEnabled();
						}


						} catch (Exception e) {
							
							System.out.println("Izasao si iz ogranicenja.");
							setAllButtonsEnabled();
							myButtonGameBoard[iEnter][jEnter].setEnabled(false);

							


							
							for (int m=0;m<10;m++)
								for (int n=0;n<10;n++){
									
									myButtonGameBoard[m][n].setBorderPainted(false);
								}
						}			
				}
			
		});
	}
	
	

	public void exitActionListener(final JButton btn){
		btn.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				
				for (int i=0;i<10;i++)
					for (int j=0;j<10;j++)
						myButtonGameBoard[i][j].setBorderPainted(false);	
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
	public int[] suggestedIndexes(int i, int j, char orientation,int size){
		
		int[] suggestIndexes=null;
		
		switch(size){
		case 1: {
			suggestIndexes=new int[3];
		}
		break;
		case 2:{
			suggestIndexes=new int[5];
		}
		break;
		case 3:{
			suggestIndexes=new int[7];
		}
		break;
		case 5:{
			suggestIndexes=new int[11];
		}
		break;
		default: return suggestIndexes;
				}
		
		
		
		if(orientation=='H'){// ako je horizontalno
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
//	public int[] getButtonFromSuggestedButtons(int position, int[] suggestedIndexes){
//	
//		int[] indexOfButton = new int[2];
//	
//		switch(suggestedIndexes[0]){
//			case 1: {
//				indexOfButton[0] = suggestedIndexes[1];
//				indexOfButton[1] = suggestedIndexes[2];
//				break;
//			}
//			case 2:{
//				if(position==1){
//					indexOfButton[0] = suggestedIndexes[1];
//					indexOfButton[1] = suggestedIndexes[2];
//				}
//				if(position==2){
//					indexOfButton[0] = suggestedIndexes[3];
//					indexOfButton[1] = suggestedIndexes[4];
//				}
//				break;
//			}
//			case 3:
//				if(position==1){
//					indexOfButton[0] = suggestedIndexes[1];
//					indexOfButton[1] = suggestedIndexes[2];
//				}
//				if(position==2){
//					indexOfButton[0] = suggestedIndexes[3];
//					indexOfButton[1] = suggestedIndexes[4];
//				}
//				if(position==3){
//					indexOfButton[0] = suggestedIndexes[5];
//					indexOfButton[1] = suggestedIndexes[6];
//				}
//				break;	
//			case 5: {
//				if(position==1){
//					indexOfButton[0] = suggestedIndexes[1];
//					indexOfButton[1] = suggestedIndexes[2];
//				}
//				if(position==2){
//					indexOfButton[0] = suggestedIndexes[3];
//					indexOfButton[1] = suggestedIndexes[4];
//				}
//				if(position==3){
//					indexOfButton[0] = suggestedIndexes[5];
//					indexOfButton[1] = suggestedIndexes[6];
//				}
//				if(position==4){
//					indexOfButton[0] = suggestedIndexes[7];
//					indexOfButton[1] = suggestedIndexes[8];
//				}
//				if(position==5){
//					indexOfButton[0] = suggestedIndexes[9];
//					indexOfButton[1] = suggestedIndexes[10];
//				}
//				break;
//			}
//		}//kraj switch-a
//		return indexOfButton;
//	}//kraj metode

/**
 * Vraca niz imena dugmadi koja treba da se markiraju
 * @param suggestedIndexes
 * @return
 */
	public String[] lightTheSuggestedButtons(int[] suggestedIndexes){
		
//		try {
//			System.out.println("3/");
//			for (int i=0;i<suggestedIndexes.length;i++) {
//				System.out.println("["+i+"]" + " = "+suggestedIndexes[i]);
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
		String[] btnNames = new String[(suggestedIndexes.length-1)/2];
		int brojac = 0;
		
		for (int i=1;i<suggestedIndexes.length;i+=2) {

			btnNames[brojac] = suggestedIndexes[i]+""+suggestedIndexes[i+1];

//			System.out.println("4/  From suggested array: "+btnNames[brojac]);
			brojac++;
		}
		
		String ispis="Dugmad za markiranje:  ";
		for (String nameForMark : btnNames) {
			ispis = ispis + " " + nameForMark;
		}
		System.out.println(ispis);
		this.suggesetedButtonNames=btnNames;
		return btnNames;
	}
	public void setAllButtonsEnabled(){
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++){
				myButtonGameBoard[i][j].setEnabled(true);
	}

	}
	
	
}
