package pacman;

import java.util.ArrayList;

public class BoardFactory {

	static int boardWidth = 15;
	static int boardHeigth = 35;
	
	@SuppressWarnings("unchecked")
	public static ArrayList<BoardField>[][] createBoard(){
			
		ArrayList<BoardField>[][] board = new ArrayList[boardWidth][boardHeigth];
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j] =  new ArrayList<BoardField>();
			}
		}
		for (int i = 0; i < boardWidth; i++) {
			board[i][0].add(BoardField.Obstacle);
			board[i][boardHeigth-1].add(BoardField.Obstacle);
		}
		
		for (int i = 0; i < boardHeigth; i++) {
			board[0][i].add(BoardField.Obstacle);
			board[boardWidth-1][i].add(BoardField.Obstacle);
		}
		
		int[] row2 = {2,3,5,7,8,9,10,12,13,14,15,16,17,18,19,20,21,22,23,24,25,30,31,32};
		for(int cell:row2) {
			board[2][cell].add(BoardField.Obstacle);
		}
		int[] row3 = {2,3,5,7,8,9,10,12,13,14,15,16,17,18,20,21,22,23,24,25,26,27,28,30,31,32};
		for(int cell:row3) {
			board[4][cell].add(BoardField.Obstacle);
		}
		int[] row4 = {2,3,5,10,12,13,14,15,16,17,18,20,21,22,23,24,25,26,27,28,30,31,32};
		for(int cell:row4) {
			board[5][cell].add(BoardField.Obstacle);
		}
		int[] row5= {1,2,3,4,5,6,16,20,28,29,30,31,32};
		for(int cell:row5) {
			board[7][cell].add(BoardField.Obstacle);
		}
		
		int[] row6= {1,2,3,4,5,6,8,9,10,12,13,14,16,20,22,23,24,26,28,29,30,31,32};
		for(int cell:row6) {
			board[8][cell].add(BoardField.Obstacle);
		}
		int[] row7= {1,2,3,4,5,6,8,9,10,12,13,14,16,17,18,19,20,22,23,24,26,28,29,30,31,32};
		for(int cell:row7) {
			board[9][cell].add(BoardField.Obstacle);
		}
		int[] row8= {1,2,3,4,5,6,8,9,10,12,13,14,22,23,24,26,28,29,30,31,32};
		for(int cell:row8) {
			board[10][cell].add(BoardField.Obstacle);
		}
		
		int[] row9= {2,3,4,5,6,7,8,9,10,12,13,14,15,16,20,21,22,23,24,25,26,27,28,30,31,32};
		for(int cell:row9) {
			board[12][cell].add(BoardField.Obstacle);
		}
		board[13][16].add(BoardField.Obstacle);
		board[13][20].add(BoardField.Obstacle);

		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeigth; j++) {
				if (board[i][j].isEmpty()) {
					board[i][j].add(BoardField.EmptyField);
					board[i][j].add(BoardField.Food);
				}
			}
		}
		
		board[13][17].remove(BoardField.Food);board[12][17].remove(BoardField.Food);
		board[13][18].remove(BoardField.Food);board[12][18].remove(BoardField.Food);
		board[13][19].remove(BoardField.Food);board[12][19].remove(BoardField.Food);
		board[7][17].remove(BoardField.Food);board[8][17].remove(BoardField.Food);
		board[7][18].remove(BoardField.Food);board[8][18].remove(BoardField.Food);
		board[7][19].remove(BoardField.Food);board[8][19].remove(BoardField.Food);
		return board;
	}	
	
	public static void setDefaultPosition(ArrayList<BoardField>[][] board) {
		board[13][18].clear();
		board[13][18].add(BoardField.Player);
		
		board[7][18].clear();
		board[7][18].add(BoardField.Pinky);
		board[8][18].clear();
		board[8][18].add(BoardField.Clyde);
		board[8][19].clear();
		board[8][19].add(BoardField.Blinky);
		board[8][17].clear();
		board[8][17].add(BoardField.Inky);
	}
}
