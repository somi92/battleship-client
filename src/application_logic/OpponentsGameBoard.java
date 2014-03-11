package application_logic;

public class OpponentsGameBoard {
	
	private int[][] gameBoard;
	

	public OpponentsGameBoard(){
		for(int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				gameBoard[i][j]= 0;
			}
		}
	}
	
	
	/**
	 * Method that modifies cell in opponents game board.
	 * @param i - index of row
	 * @param j - index of columns
	 * @param stateOfCell
	 * @return boolean
	 */
	public boolean modifyCell(int i, int j, int stateOfCell){
		
		if(gameBoard[i][j]==0 || gameBoard[i][j]==1){
			if( (i<10 && i>=0) && (j<10 && j>=0) && (stateOfCell>=0 && stateOfCell<4) ){
				gameBoard[i][j] = stateOfCell;
				return true;
			}
		}
		
		return false;
	}	
		
		
		
		
		
		
	
	
	
	

}
