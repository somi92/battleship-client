package application_logic;

import protocol.BattleShipClient;
import utilities.BattleShipStatus;

public class MyGameBoardMask {

	public int[][] gameBoard = new int [10][10];	
	//public int[][] logicStartMatrix = new int[10][10];

	public static final int PRAZAN=0; // nema broda na tom polju
	public static final int POSTAVLJEN=1;
	public static final int POGODJEN=2;
	public static final int POTOPLJEN=3;
	public static final int PROMASEN=4;
	private int shipTypeOneSum=1;
	private int shipTypeTwoSum=4;
	private int shipTypeThree1Sum=9;
	private int shipTypeThree2Sum=12;
	private int shipTypeFiveSum=25;
	private int totalSum=51;
	public MyGameBoardMask(){
		for(int i=0;i<10;i++)
			for (int j=0;j<10;j++)
				gameBoard[i][j]= 0;
	}
	
	public void FillStartMatrix(int m, int n, int tip){
		gameBoard[m][n]=tip;
	}
	
	public int takeAHit(int m, int n){
		int field=gameBoard[m][n];
		
		switch(field){
		
		
		case 0:
			
			return BattleShipStatus.SHIP_MISSED;
		case 1: {
			shipTypeOneSum=shipTypeOneSum-1;
			gameBoard[m][n]=-field;
			totalSum-=field;
			if(totalSum==0)
				return BattleShipStatus.FLEET_DESTROYED;
			return BattleShipStatus.SHIP_SUNKED;
			
		}
		
		case 2:{
			shipTypeTwoSum=shipTypeTwoSum-2;
			gameBoard[m][n]=-field;
			totalSum-=field;
			if(totalSum==0)
				return BattleShipStatus.FLEET_DESTROYED;
			if(shipTypeTwoSum==0)
				return BattleShipStatus.SHIP_SUNKED;
			else
				return BattleShipStatus.SHIP_HIT;
					
		}
		
		case 3: {
			shipTypeThree1Sum=shipTypeThree1Sum - 3;
			gameBoard[m][n]=-field;
			totalSum-=field;
			if(totalSum==0)
				return BattleShipStatus.FLEET_DESTROYED;
			if(shipTypeThree1Sum==0)
				return BattleShipStatus.SHIP_SUNKED;
			else
				return BattleShipStatus.SHIP_HIT;
			
		}
		
		case 4: {
			shipTypeThree2Sum=shipTypeThree2Sum-4;
			gameBoard[m][n]=-field;
			totalSum-=field;
			if(totalSum==0)
				return BattleShipStatus.FLEET_DESTROYED;
			if(shipTypeThree2Sum==0)
				return BattleShipStatus.SHIP_SUNKED;
			else
				return BattleShipStatus.SHIP_HIT;
		}
		case 5: {
			shipTypeFiveSum=shipTypeFiveSum-5;
			gameBoard[m][n]=-field;
			totalSum-=field;
			if(totalSum==0)
				return BattleShipStatus.FLEET_DESTROYED;
			if(shipTypeFiveSum==0)
				return BattleShipStatus.SHIP_SUNKED;
			else
				return BattleShipStatus.SHIP_HIT;
		}
		
	}
		
		
	return -field;
}


/**
 * Ispis logicke matrice
 */
public void ispisi(){
	System.out.println("Logicka matrica: "); 
	for (int[] i : gameBoard) {
		for (int j : i) System.out.print(j);
		System.out.println(); //novi red
	}
}

	
	/**
	 * Methos fill my game board fith ships i setted.
	 * @param ships - Int[][] 
	 * @return boolean
	 */
	public boolean fillMyGameboard(int[][] ships){
		
		for(int i=0;i<10;i++)
			for (int j=0;j<10;j++)
				if(gameBoard[i][j]!=0) 	return false;
		
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
