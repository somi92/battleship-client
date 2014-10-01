package application_logic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import user_interface.SetMyShipsFrame;

public class SetMyShipsManager {

	public SetMyShipsFrame workingFrame;
	public JButton[][] myButtonGameBoard = new JButton[10][10];
	
	public ImageIcon seaImg = new ImageIcon(getClass().getResource("/resources/sea.png"));
	public ImageIcon shipImage = new ImageIcon(getClass().getResource("/resources/brodic.jpg"));
	public ImageIcon xImg = new ImageIcon(getClass().getResource("/resources/x.png"));
	
	
	
	
	//mouse enter / exit coordinates
	public int iEnter;
	public int jEnter;
	public int jSuggested;
	public int iSuggested;	
//	public int iExit = -1;
//	public int jExit = -1;

	public int[][] logicMatrix = new int[10][10];
	
	public String [] suggestedButtonsNames = null;
	public LinkedList<String> existingBoats = new LinkedList<String>();

	public MyGameBoardMask gameBoardMask = new MyGameBoardMask();

	public SetMyShipsManager(SetMyShipsFrame workingFrame) {
	this.workingFrame = workingFrame;
}
/**
 * Pravi 2D niz dugmdi kojima su postavljeni eventListeneri. Niz je spreman da se postavi na potrebni panel.
 * @return
 */
	public JButton[][] initializeButtonsforGameBoard(){
		
		for(int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				myButtonGameBoard[i][j]= new JButton(seaImg);
				myButtonGameBoard[i][j].setName(i+""+j);
				myButtonGameBoard[i][j].setBorderPainted(false);
				myButtonGameBoard[i][j].setMaximumSize(new Dimension(30, 30));
				myButtonGameBoard[i][j].setMinimumSize(new Dimension(30, 30));
				
				JButton btn = myButtonGameBoard[i][j];
				
				enterActionListener(btn); //done
				clickActionListener(btn); //done
				exitActionListener(btn);  //done
				setToolTipEffect(btn);  // done
			}
		}
		
		return myButtonGameBoard;	
	}
	
// ENTER	
	public void enterActionListener(final JButton btn){
		btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				
				char iChar = btn.getName().charAt(0);
				char jChar = btn.getName().charAt(1);
				
				iEnter = Integer.parseInt(iChar+"");
				jEnter = Integer.parseInt(jChar+"");

				setSuggestedButtonsNames(suggestedIndexes(workingFrame.orijentation, workingFrame.shipSize));
				
				// PROVERA 
				System.out.println("Enter: " + +iEnter+ "" +jEnter);				
				String ispis="Dugmad za markiranje:  ";
				for (String btnName : suggestedButtonsNames) ispis = ispis + " " + btnName;
				System.out.println(ispis);			
				
				try {
					
					for(int i=0; i<suggestedButtonsNames.length;i++){

						iSuggested = Integer.parseInt(suggestedButtonsNames[i].charAt(0)+"");
						jSuggested = Integer.parseInt(suggestedButtonsNames[i].charAt(1)+"");
						
						//1) Kada predlozeno dugme izadje iz okvira polja, ono ili dobija negativnu kordinatu,
						// ili mu je jedna kordinata dvocifren broj. Samim tim njegovo ime ima duzinu 3.
						// Primer: "-11", "1-1", "010" , "109"
						//2) i 3) nepotrebni uslovi su stojali: " || iFirst<0 || jFirst<0 " 
						if(suggestedButtonsNames[i].length()==3) throw new Exception("Izasao si iz ogranicenja!");
						else myButtonGameBoard[iSuggested][jSuggested].setBorderPainted(true);
						
						setAllButtonsEnabled();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					
					setAllButtonsEnabled();
					myButtonGameBoard[iEnter][jEnter].setEnabled(false);										
	
					for (int m=0;m<10;m++)
						for (int n=0;n<10;n++){
							myButtonGameBoard[m][n].setBorderPainted(false);
						}
				}//kraj catch			
			}
		});
	}
	
// CLICK
	public void clickActionListener(final JButton btn){
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i=0;i<suggestedButtonsNames.length;i++)
					if(existingBoats.contains(suggestedButtonsNames[i])){
						//Neki od predlozenih polja za brod je na mestu gde vec postoji brod.
						System.out.println("Ne mozes postaviti brod na to polje!");
						return;
					}
				
				int sifraBroda = workingFrame.updateLabels();
				
				if((sifraBroda)!=-1){
					
					for(int i=0;i<10;i++)
						for(int j=0;j<10;j++){
							//samo polja koja treba da dobiju sliku broda imju border ofarban
							if(myButtonGameBoard[i][j].isBorderPainted()){
								existingBoats.add(myButtonGameBoard[i][j].getName());
								myButtonGameBoard[i][j].setIcon(shipImage);
								gameBoardMask.FillStartMatrix(i,j,sifraBroda);
							}
						}
//Provera
					//System.out.print("Kordinate postojecih brodica:");
					//for(int i=0;i<existingBoats.size();i++)
					//	System.out.print(" "+existingBoats.get(i));
					//System.out.println();
				}
				
				gameBoardMask.ispisi();
				// prosledjujem logicku matricu iz MyGameBoardMask u setMyShipFrame
				logicMatrix = gameBoardMask.gameBoard;
			}
		});
	}
		
// EXIT
	public void exitActionListener(final JButton btn){
		btn.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				
				for (int i=0;i<10;i++)
					for (int j=0;j<10;j++)
						myButtonGameBoard[i][j].setBorderPainted(false);	
			}
		});
	}
	
// TOOLTIP
	public void setToolTipEffect(JButton btn){	
		btn.setToolTipText(btn.getName());	
	}

/**
 * inicijalizuje niz odredjene duzine u zavisnosti od velicine brodica i
 * popunjava kordinatama dugmadi koja treba da se markiraju
 * @param horOrVer 
 * @param size
 * @return niz celih brojeva u formatu [size, iBtn1, jBtn2, iBtn2, jBtn2, ... ].
 * Gde kordinate pocinju od levog dugmeta ka desnom/gornjeg ka donjem.
 */
	public int[] suggestedIndexes(char orientation,int size){
		
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
			
			// [ 0  0  0  1  0  0  0 ]
			//			  my
			case 1:{			
				suggestIndexes[0]=size;
				suggestIndexes[1]=iEnter;
				suggestIndexes[2]=jEnter;
				return suggestIndexes;
			}
			
			// [ 0  0  1  1  0  0  0 ]
			//            my
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
		// ako ne postoji uneta velicina broda
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
			// ako ne postoji uneta velicina broda
			return null;
		}//kraj vertikalno			
	}// kraj metode
	

	
/**
 * int[] suggestIndexes --> String[] suggestedButtonsNames
 * Modifikuje niz dobijen iz suggestedIndexes(char orientation,int size) metode u niz 
 * popunjen samo imenima dugmadi koja treba da se markiraju zajedno sa onim na kome je mis. 
 * Finalni niz dodeljuje globalnoj promenjivi suggesetedButtonNames.
 * @param suggestedIndexes - inicijalizovan niz odredjene duzine
 * @return niz String vrednosti koje predstavljaju imena dugmadi koja treba da se markiraju.
 * u formatu ["btnName1, btnName2, ..."]
 */
	public void setSuggestedButtonsNames(int[] suggestedIndexes){
		
		String[] btnNames = new String[(suggestedIndexes.length-1)/2];
		int brojac = 0;
		
		for (int i=1;i<suggestedIndexes.length;i+=2) {
			btnNames[brojac] = suggestedIndexes[i]+""+suggestedIndexes[i+1];
			brojac++;
		}
		
//PROVERA
//		String ispis="Dugmad za markiranje:  ";
//		for (String nameForMark : btnNames) {
//			ispis = ispis + " " + nameForMark;
//		}
//		System.out.println(ispis);
		
		this.suggestedButtonsNames = btnNames;
	}
	
	
	public void setAllButtonsEnabled(){
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
				myButtonGameBoard[i][j].setEnabled(true);
	}
	
//	/**
//	 * Metoda se poziva nakon enterActionListenera, i pre nego sto postavi nove enter kordinate,
//	 * exit kordinatama dodeli vrednost prethodnih enter kordinata.
//	 */
//	public void setExitCordinates(){
//		iExit = iEnter;
//		jExit = jEnter;	
//	}
	
	
}
