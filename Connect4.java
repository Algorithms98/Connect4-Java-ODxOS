import java.util.Scanner;
import java.util.*; 

public class Connect4{

	public static int rows = 6;
	public static int columns = 7;
   public static char[][] board = new char[rows][columns]; 
   char currPlayer = 'O';
   boolean gameOver;

	public Connect4(){
      // what would be needed here if the constructor has no parameters?
		gameOver = false;

		for(int i = 0; i < rows; i++){
			for(int j = 0;j < columns; j++){
				board[i][j] = '-';
			}
			}
		}

	//prints board to the screen
	public void displayBoard(char[][] board){
		//System.out.println("---------------");
		for (int row = 0; row < board.length; row++){
			//System.out.print("|");
			for (int column = 0; column < board[0].length; column++){
				System.out.print(board[row][column]);
			//	System.out.print("|");
			}
			System.out.println();
			//System.out.println("---------------");
		}
		System.out.println();
	}

	public int findEmptyRow(int column){
		int rownum = -1;
		for( int i = 0; i < rows; i++){
			if(board[i][column-1] == '-'){
				rownum = i;
			}
		}
		return rownum;

	}

	//returns all the columns with openings
	public List<Integer> validColumns(char[][] board){

		List<Integer> openslots = new ArrayList<Integer>();

		for(int i = 0; i < columns; i++){
			if(board[rows - 1][i] == '-'){
				openslots.add(i);
			}
		}

		return openslots;
	} 

	public char switchPlayers(char currPlayer){

		if(currPlayer == 'X'){
			currPlayer = 'O';
		}
		else{
			currPlayer = 'X';
		}

		return currPlayer;
	}

	public boolean verticalCheck(char[][] board, char player){

		for(int i = 0; i < columns; i++){
			for(int j = 0; j < rows-3; i++){
				if(board[j][i] == player || board[j+1][i] == player || board[j+2][i] == player || board[j+3][i] == player){
					return true;
				}
			}
		}

		return false;

	}

	public boolean horizontalCheck(char[][] board, char player){

		for(int i =0; i < columns-3; i++){
			for(int j = 0; j < rows; j++){
				if(board[j][i] == player || board[j][i+1] == player || board[j][i+2] == player || board[j][i+3] == player){
					return true;
				}
			}
		}
		return false;

	}

	public boolean negativeDiagonals(char[][] board, char player){

		for(int i = 0; i < columns-3; i++){
			for(int j = 3; j < rows; j++){
				if(board[j][i] == player || board[j-1][i+1] == player || board[j-2][i+2] == player || board[j-3][i+3] == player){
					return true;
				}
			}
		}
		return false;

	}

	public boolean positiveDiagonals(char[][] board, char player){

		for(int i = 0; i < columns-3;i++){
			for(int j = 0; j < rows-3;j++){
				if(board[j][i] == player || board[j+1][i+1] == player || board[j+2][i+2] == player || board[j+3][i+3] == player){
					return true;
				}
			}
		}
		return false;

	}

	public boolean checkWinner(char[][] board, char player){
		if(verticalCheck(board, player) == true || horizontalCheck(board, player) == true || negativeDiagonals(board, player) == true || positiveDiagonals(board, player) == true){
			String winner = String.format("Player: %s won", player);
			System.out.println(winner);
			displayBoard(board);
			return true;
		}
		return false;

	}

	public boolean checkTie(char[][] board, char player){

		if(validColumns(board).size() == 0 && checkWinner(board, player) == false){
			System.out.println("It's a Tie!");
			displayBoard(board);
			return true;
		}
		return false;
	}

	//needs proper input validation ensuring that it's an integer between, 1 and 7
	public int takeInput(){
		Scanner columname = new Scanner(System.in); 

		System.out.print("Enter the column number for your disc: ");
		int num = columname.nextInt(); 

		columname.close();

		return num;
	}

	public void dropDisc(char player, int row, int column){
		board[row][column-1] = player;
               
	}

	public void playGame(){
		System.out.println("Welcome to Connect4! Let's start playing!");	

		while(gameOver == false){

			char player = switchPlayers(currPlayer);
			displayBoard(board);
			int col = takeInput();
			int userow = findEmptyRow(col);
			dropDisc(player, userow,col );
			if(checkWinner(board, player) || checkTie(board, player) ){
				gameOver = true; 
			}

		}

	}


	public static void main(String[] args){

		Connect4 game = new Connect4();
		game.playGame();
      
      	} 
}
