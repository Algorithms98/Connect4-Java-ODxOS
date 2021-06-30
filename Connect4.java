import java.util.Scanner;
import java.util.*; 

public class Connect4{

	private int rows = 6;
	private int columns = 7;
	char[][] board = new char[rows][columns]; 

	public Connect4(){

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				board[i][j] = ' ';
			}
		}
	}

	public int findEmptyRow(int column){
		int rownum = -1;
		for( int i = 0; i < rows; i++){
			if(board[i][column] == ' '){
				rownum = i;
			}
		}
		return rownum;

	}

	//returns all the columns with openings
	public int[] validColumns(char[][] board){

		List<Integer> openslots = new ArrayList<Integer>();



	}

	public void dropDisc(player, row, column){
		board[row][column] = player;
	}

	public char switchPlayers(char currPlayer){

		if(currPlayer == 'X'){
			currPlayer = 'O';
		}
		else{
			currPlayer = '0';
		}

		return currPlayer;
	}

	public bool verticalCheck(char[][] board, char player){

		return False;

	}

	public bool horizontalCheck(char[][] board, char player){
		return False;

	}

	public bool negativeDiagonals(char[][] board, char player){
		return False;

	}

	public bool positiveDiagonals(char[][] board, char player){
		return False;

	}

	public bool checkWinner(char[][] board, char player){
		return False;

	}

	public bool checkTie(char[][] board, char player){

	}




	public static void main(String[] args){

	} 
}