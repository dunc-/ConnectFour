import java.util.*;

public class ConnectFour {

    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	String[] names = getName(in);
    	char[][] board = fill();
    	int turn = 0;
    	boolean test = true;
    	while (test){
    		turn++;
    		printBoard(1, board);
    		String name = implementInput(turn, names, board, in);
    		test = checkForWin(turn, name, board);
    		if (turn == 42 && test){
    			System.out.println("This game is a draw");
    			test = false;
    		}
    	}
    	printBoard(2, board);
    	in.close();
    }

    // Gets the names of the players
    public static String[] getName(Scanner in){
    	String[] returnMe = new String[2];
    	System.out.print("Player 1 enter your name: ");
    	returnMe[0] = in.nextLine();
    	System.out.print("Player 2 enter your name: ");
    	returnMe[1] = in.nextLine();
    	return returnMe;
    }
    
    // Initializes and fills a 2D char array with "."
    public static char[][] fill(){
    	char[][] board = new char[6][7];
    	for (int row=0; row<6; row++){
    		for (int col=0; col<7; col++){
    			board[row][col] = '.';
    		}
    	}
    	return board;
    }
    
    // Prints out the board to the console
    public static void printBoard(int field, char[][] board){
    	System.out.println();
    	if (field == 1) System.out.println("Current Board");
    	else System.out.println("Final Board");
    	System.out.println("1 2 3 4 5 6 7 column numbers");
    	for (int row=0; row<6; row++){
    		for (int col=0; col<7; col++){
    			System.out.print(board[row][col] + " ");
    		}
    		System.out.println();
    	}
    }
    
    // Prints the directions for the players, as well as changes the board
    public static String implementInput(int turn, String[] s, char[][] board, Scanner in){
    	String putIn = s[0];
    	String color = "r's";
    	char coin = 'r';
    	if (turn%2 == 0){
    		putIn = s[1];
    		color = "b's";
    		coin = 'b';
    	}
    	System.out.println();
    	System.out.println(putIn + " it is your turn.");
    	System.out.println("Your pieces are the " + color);
    	int selection = check(putIn, in, board);
    	boolean check = true;
    	for (int row=5; row>=0; row--){
    		if (board[row][selection] == '.' && check){
    			board[row][selection] = coin;
    			check = false;
    		}
    	}
    	return putIn;
    }
    
    // Checks the input to see if it is valid
    public static int check(String putIn, Scanner in, char[][] board){
    	System.out.print(putIn + ", enter the column to drop your checker: ");
		while (!in.hasNextInt()){
			System.out.println(in.nextLine() + " is not an integer.");
    		System.out.print(putIn + ", enter the column to drop your checker: ");
		}
		int s = in.nextInt();
    	while (s<1 || s>7){
    		System.out.println(s + " is not a valid column.");
    		System.out.print(putIn + ", enter the column to drop your checker: ");
    		s = in.nextInt();
    	}
    	boolean col = checkCol(s, board);
    	while (!col){
    		System.out.println("Column " + s + " is full.");
    		System.out.print(putIn + ", enter the column to drop your checker: ");
    		s = in.nextInt();
    		col = checkCol(s, board);
    	}
    	return s-1;
    }
    
    // Checks to see if the column is full or not
    public static boolean checkCol(int s, char[][] board){
    	int count = 0;
    	for (int row=0; row<6; row++){
    		if (board[row][s-1] == 'r' || board[row][s-1] == 'b')
    			count++;
    	}
    	if (count == 6) return false;
    	return true;
    }
    
    // Checks to see if the player won the match
    public static boolean checkForWin(int turn, String name, char[][] board){
    	char coin = 'r';
    	if (turn%2 == 0) coin = 'b';
    	for (int row=0; row<6; row++){
        	for (int col=0; col<7; col++){
        		if (board[row][col] == coin){
        			if (row+3<=5){ // Straight Down
        				if (board[row+1][col] == coin && board[row+2][col] == coin && board[row+3][col] == coin){
        					System.out.println(name + " wins!!");
        					return false;
        				}
        				if (col-3>=0){ // South West
        					if (board[row+1][col-1] == coin && board[row+2][col-2] == coin && board[row+3][col-3] == coin){
        						System.out.println(name + " wins!!");
        						return false;
        					}
        				}
        				if (col+3<=6){ // South East
        					if (board[row+1][col+1] == coin && board[row+2][col+2] == coin && board[row+3][col+3] == coin){
        						System.out.println(name + " wins!!");
        						return false;
        					}
        				}
        			}
        			if (col-3>=0){ // Left/Right
        				if (board[row][col-1] == coin && board[row][col-2] == coin && board[row][col-3] == coin){
        					System.out.println(name + " wins!!");
    						return false;
        				}
        			}
        		}
        	}
    	}
    	return true;
    }
}
