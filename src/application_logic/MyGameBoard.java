package application_logic;

public class MyGameBoard {

private int[][] gameBoard;
	

	public MyGameBoard(){
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
