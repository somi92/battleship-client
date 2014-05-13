package application_logic;

public class MyGameBoardMask {

private int[][] gameBoard=new int [10][10];	

public int [] [] logicStartMatrix= new int [10] [10];

public static final int PRAZAN=0; // nema broda na tom polju
public static final int POSTAVLJEN=1;
public static final int POGODJEN=2;
public static final int POTOPLJEN=3;
public static final int PROMASEN=4;
private int shipTypeOneSum=2;
private int shipTypeTwoSum=4;
private int shipTypeThree1Sum=9;
private int shipTypeThree2Sum=12;
private int shipTypeFiveSum=25;

public void FillStartMatrix(int k, int l, int tip){
	
	
	
			gameBoard[k][l]=tip;
		
}
public int takeAHit(int m, int n){
	
	int field=gameBoard[m][n];
	switch(field){
	case 1: {
		shipTypeOneSum=shipTypeOneSum-1;
		gameBoard[m][n]=-field;
	}
	break;
	case 2:{
		shipTypeTwoSum=shipTypeTwoSum-2;
		gameBoard[m][n]=-field;
	}
		break;
	case 3: {
		shipTypeThree1Sum-=3;
		gameBoard[m][n]=-field;
	}
	break;
	case 4: {
		shipTypeThree2Sum-=4;
		gameBoard[m][n]=-field;
	
	}
	case 5: {
		shipTypeFiveSum-=5;
		gameBoard[m][n]=-field;
	
	}
	break;

	}
	return -field;
}



public void ispisi(){

	for (int[] i : gameBoard) {
		for (int j : i) {
			System.out.print(j);
		}
		System.out.println();
	}
}

	public MyGameBoardMask(){
		for(int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				gameBoard[i][j]= 0;
			}
		}
	}
	
	/**
	 * Methos fill my game board fith ships i setted.
	 * @param ships - Int[][] 
	 * @return boolean
	 */
	public boolean fillMyGameboard(int[][] ships){
		
		for(int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				if(gameBoard[i][j]!=0){
					return false;
				}	
			}
		}
		
		gameBoard = ships;
		return true;
	}
	
	/**
	 * Method that modifies cell in my game board.
	 * @param i - index of row
	 * @param j - index of columns
	 * @param stateOfCell
	 * @return boolean
	 */
	public boolean modifyCell(int i, int j, int stateOfCell){
		
		if(gameBoard[i][j]==0 || gameBoard[i][j]==2){
			if( (i<10 && i>=0) && (j<10 && j>=0) && (stateOfCell>=0 && stateOfCell<4) ){
				gameBoard[i][j] = stateOfCell;
				return true;
			}
		}
		
		return false;
	}

	
	
	
	
}
