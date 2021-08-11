import java.util.Scanner;
import java.util.*; 
import java.io.*;

public class Connect4{

	public static int rows = 6;
	public static int columns = 7;
   public static char[][] board = new char[rows][columns]; 
   boolean gameOver = false; 
   char currPlayer='X'; // 'X' is the first to play.
      
    //Creating the game environment. 
	public Connect4(){
   System.out.println("X-O-X-O-X-X-O-X-X-O-O-X-O-O-X-X-O-X-O-X-O-X-O-X-X-O-X-X-O-X");
   System.out.println("X                   Let's Play Connect 4!                 X");
   System.out.println("X-O-X-O-X-X-O-X-X-O-O-X-O-O-X-X-O-X-O-X-O-X-O-X-X-O-X-X-O-X");

   //Constructor, displays a board of dashes.	
	//Creating the game environment.
	//Constructor, displays a board of dashes.
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				board[i][j] = '-';
			}
			}
      playGame();
		}
  public static void displayRules(){
      System.out.println("X-O-X-O-X-X-O-X-X-O-O-X-O-O-X-X-O-X-O-X-O-X-O-X-X-O-X-X-O-X");
      System.out.println("X                     Rules of Connect 4!                 X");
      System.out.println("X-O-X-O-X-X-O-X-X-O-O-X-O-O-X-X-O-X-O-X-O-X-O-X-X-O-X-X-O-X");
      System.out.println();
      System.out.println("* A two player game");
      System.out.println("* You and the other player are trying to get a Connect 4.");
      System.out.println("* A Connect 4 is a match of four either in the horizontial, \nvertical or diagonal direction.");
      System.out.println();
      System.out.println("In this implementation of Connect 4, you should keep the \nfollowing in mind:");
      System.out.println("* The first player is the 'X' and the second player is 'O'.");
      System.out.println("* Players switch after each turn.");
      System.out.println("* Each player will be prompted to enter which column they \nwould like to place \ntheir play.");
      System.out.println("* After the player selects their column, their play will \nbe placed in the \nnext available row in the column.");
      System.out.println("* Whoever makes a Connect 4 first wins and the game ends."); 
   }
		
	//Prints board to the console. 
	public void displayBoard(char[][] board){
		for (int row = 0; row < board.length; row++){
			for (int column = 0; column < board[0].length; column++){
				System.out.print(board[row][column]);
			}
			System.out.println();
		}
		System.out.println();
	}

	//Finds the lowest empty row to place the play. 
	public int findEmptyRow(int column){
		int rownum = 0;
		for(int i = 0; i < rows; i++){
			if(board[i][column-1] == '-'){
				rownum = i;
			}
		}
		return rownum;
	}

   //Counts the number of empty spots in the board. If there is no more empty spots, that means its a tie. 
   public int countEmptySpots(char[][]board){
   int emptySpot = 0; //holds the count of spaces in the board that are empty. 
   for(int row = 0; row < board.length; row++){
      for(int column = 0; column< board[0].length; column++){
      if(board[row][column] == '-'){
         emptySpot++;
      } 
   }
   }
   return emptySpot;
   }

	//Switches the players after each turn. 
	public char switchPlayers(char currPlayer){
		if(currPlayer =='X'){
			currPlayer = '0';
		}
		else{ 
			currPlayer = 'X';
		}
		return currPlayer;
	}

	
	// Checks to see if someone has a connect four in the vertical direction.
	public boolean verticalCheck(char[][] board, char player){
		for(int i = 0; i < board.length-3; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] == player && board[i+1][j] == player && board[i+2][j] == player && board[i+3][j] == player){
					return true;
				}
			}
		}
		return false;
	}


   	
	// Checks to see if someone has a connect four in the horizontal direction.
	public boolean horizontalCheck(char[][] board, char player){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length-3; j++){
				if(board[i][j] == player && board[i][j+1] == player && board[i][j+2] == player && board[i][j+3] == player){
					return true;
				}
			}
		}
		return false;
	}
	// Checks to see if there is a connect four diagonally, in a negative slope. 
	public boolean negativeDiagonals(char[][] board, char player){
		for(int i = 3; i < board.length; i++){
			for(int j = 0; j < board[0].length-3; j++){
				if(board[i][j] == player && board[i-1][j+1] == player && board[i-2][j+2] == player && board[i-3][j+3] == player){
					return true;
				}
			}
		}
		return false;
	}
	
	//Checks to see if there is a connect four diagonally, in a positive slope. 
	public boolean positiveDiagonals(char[][] board, char player){
		for(int i = 0; i < board.length-3; i++){
			for(int j = 0; j < board[0].length-3; j++){
				if(board[i][j] == player && board[i+1][j+1] == player && board[i+2][j+2] == player && board[i+3][j+3] == player){
					return true;
				}
			}
		}
		return false;
	}
	
	//Checks to see if someone had a row of four either vertically, horizontally, or diagonally. 
	public boolean checkWinner(char[][] board, char player){
		if(verticalCheck(board, player) == true || horizontalCheck(board, player) == true || negativeDiagonals(board, player) == true || positiveDiagonals(board, player) == true){
			String winner = String.format("Player: %s won", player);
			System.out.println(winner);
			displayBoard(board);
			return true;
		} 
      return false;
   }
	
	//Determines if the board is full and there is no winner. If so, then its a tie.
	public boolean checkTie(char[][] board, char player){

		if(countEmptySpots(board) == 0 && checkWinner(board, player) == false){
			System.out.println("It's a Tie!");
			displayBoard(board);
			return true;
		} 
		return false;
	}

	//Input validation ensuring that it's an integer between 1 and 7.
	public int takeInput(){
		Scanner columnName = new Scanner(System.in); 
      System.out.println("Enter the column number for your play:");
      int num = columnName.nextInt();
       // Modifies the board to reflect the player's play. 
        while(num < 1 || num > 7){
        	System.out.println("The column number is out of bounds. Please enter a new column number between 1-7"); // If the number is out of bounds, prompts user again for another number.
            num = columnName.nextInt();  
        }
      return num;
      }
	
	// Modifies the board to reflect the player's play. 
	public void dropDisc(char player, int row, int column){
		board[row][column-1] = player;
               
	}
	
	// This method runs the game.
   	public void playGame(){
		System.out.println("Welcome to Connect4! Let's start playing!");
		while(gameOver == false) {	
			displayBoard(board); // Displays board
			columns = takeInput(); //Takes in the user's input
			int userow = findEmptyRow(columns); // Searches within a column the next empty row.
			dropDisc(currPlayer, userow,columns); // Based on who's playing, where in the column there's an available spot, and the number of columns, the user's play is placed. 
			if(checkWinner(board, currPlayer) == true || checkTie(board, currPlayer) == true){
				gameOver = true;
			}
			currPlayer = switchPlayers(currPlayer); // Switches the players.
					}
	}

	public static void main(String[] args){
      System.out.println("X-O-X-O-X-X-O-X-X-O-O-X-O-O-X-X-O-X-O-X-O-X-O-X-X-O-X-X-O-X");
      System.out.println("X                     Welcome to Connect 4!               X");
      System.out.println("X-O-X-O-X-X-O-X-X-O-O-X-O-O-X-X-O-X-O-X-O-X-O-X-X-O-X-X-O-X");
      System.out.println();
      Scanner option = new Scanner(System.in);
      int chosenOption = 0;
      while(chosenOption != 1 || chosenOption !=2){
      System.out.println();
      System.out.print("Select an Option by Typing the Number Associated with Your \nSelection:");
      System.out.println();
      System.out.println("1 Instructions");
      System.out.println("2 Play Game");
      System.out.println("3 Exit");
      chosenOption = option.nextInt();
      if(chosenOption == 1){
         displayRules();
      }else if(chosenOption == 2){
         Connect4 game = new Connect4();
      }else if(chosenOption == 3){
      System.exit(0);
      }     
      }
      }
		//Connect4 game = new Connect4(); // Creates the object.
		//game.playGame(); // Calls the method that runs the game.
      } 
		
	
