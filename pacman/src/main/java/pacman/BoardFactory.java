package pacman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardFactory {

	static int boardWidth = 15;
	static int boardHeigth = 35;
	public static int[] playerDefaultPosition = { 13, 18 };
	public static int[] blinkyDefaultPosition = { 7, 18 };
	public static int[] inkyDefaultPosition = { 8, 18 };
	public static int[] clydeDefaultPosition = { 8, 19 };
	public static int[] pinkyDefaultPosition = { 8, 17 };

	@SuppressWarnings("unchecked")
	public static List<BoardField>[][] createBoard(int boardChoice) {

		List<BoardField>[][] board = new List[boardWidth][boardHeigth];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = Collections.synchronizedList(new ArrayList<BoardField>());
			}
		}
		for (int i = 0; i < boardWidth; i++) {
			board[i][0].add(BoardField.Obstacle);
			board[i][boardHeigth - 1].add(BoardField.Obstacle);
		}

		for (int i = 0; i < boardHeigth; i++) {
			board[0][i].add(BoardField.Obstacle);
			board[boardWidth - 1][i].add(BoardField.Obstacle);
		}
		switch (boardChoice) {
		case 1:
			createBoard1(board);
			break;
		case 2:
			createBoard2(board);
			break;
		case 3:
			createBoard3(board);
			break;
		default:
			throw new IllegalArgumentException("Illegal board choice.");
		}

		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeigth; j++) {
				if (board[i][j].isEmpty()) {
					board[i][j].add(BoardField.EmptyField);
					board[i][j].add(BoardField.Food);
				}
			}
		}

		board[13][17].remove(BoardField.Food);
		board[12][17].remove(BoardField.Food);
		board[13][18].remove(BoardField.Food);
		board[12][18].remove(BoardField.Food);
		board[13][19].remove(BoardField.Food);
		board[12][19].remove(BoardField.Food);
		board[7][17].remove(BoardField.Food);
		board[8][17].remove(BoardField.Food);
		board[7][18].remove(BoardField.Food);
		board[8][18].remove(BoardField.Food);
		board[7][19].remove(BoardField.Food);
		board[8][19].remove(BoardField.Food);
		return board;
	}

	public static void setDefaultPosition(List<BoardField>[][] board) {
		board[playerDefaultPosition[0]][playerDefaultPosition[1]].clear();
		board[playerDefaultPosition[0]][playerDefaultPosition[1]].add(BoardField.Player);

		board[pinkyDefaultPosition[0]][pinkyDefaultPosition[1]].clear();
		board[pinkyDefaultPosition[0]][pinkyDefaultPosition[1]].add(BoardField.Pinky);
		board[clydeDefaultPosition[0]][clydeDefaultPosition[1]].clear();
		board[clydeDefaultPosition[0]][clydeDefaultPosition[1]].add(BoardField.Clyde);
		board[blinkyDefaultPosition[0]][blinkyDefaultPosition[1]].clear();
		board[blinkyDefaultPosition[0]][blinkyDefaultPosition[1]].add(BoardField.Blinky);
		board[inkyDefaultPosition[0]][inkyDefaultPosition[1]].clear();
		board[inkyDefaultPosition[0]][inkyDefaultPosition[1]].add(BoardField.Inky);
	}

	private static void createBoard1(List<BoardField>[][] board) {
		int[] row2 = { 2, 3, 5, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 30, 31, 32 };
		for (int cell : row2) {
			board[2][cell].add(BoardField.Obstacle);
		}
		int[] row3 = { 2, 3, 5, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31,
				32 };
		for (int cell : row3) {
			board[4][cell].add(BoardField.Obstacle);
		}
		int[] row4 = { 2, 3, 5, 10, 12, 13, 14, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31, 32 };
		for (int cell : row4) {
			board[5][cell].add(BoardField.Obstacle);
		}
		int[] row5 = { 1, 2, 3, 4, 5, 6, 16, 20, 28, 29, 30, 31, 32 };
		for (int cell : row5) {
			board[7][cell].add(BoardField.Obstacle);
		}

		int[] row6 = { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 13, 14, 16, 20, 22, 23, 24, 26, 28, 29, 30, 31, 32 };
		for (int cell : row6) {
			board[8][cell].add(BoardField.Obstacle);
		}
		int[] row7 = { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 20, 22, 23, 24, 26, 28, 29, 30, 31, 32 };
		for (int cell : row7) {
			board[9][cell].add(BoardField.Obstacle);
		}
		int[] row8 = { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 13, 14, 22, 23, 24, 26, 28, 29, 30, 31, 32 };
		for (int cell : row8) {
			board[10][cell].add(BoardField.Obstacle);
		}

		int[] row9 = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31, 32 };
		for (int cell : row9) {
			board[12][cell].add(BoardField.Obstacle);
		}
		board[13][16].add(BoardField.Obstacle);
		board[13][20].add(BoardField.Obstacle);
	}
	
	private static void createBoard2(List<BoardField>[][] board) {
		int[] row2 = { 2, 3, 5, 7, 8, 9, 10, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 24, 25, 30, 31, 32 };
		for (int cell : row2) {
			board[2][cell].add(BoardField.Obstacle);
		}
		board[3][2].add(BoardField.Obstacle);board[3][3].add(BoardField.Obstacle);board[3][4].add(BoardField.Obstacle);
		int[] row3 = { 2, 3,4, 5, 7, 8, 9, 10, 12, 13, 14, 15, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31,
				32 };
		for (int cell : row3) {
			board[4][cell].add(BoardField.Obstacle);
		}
		int[] row4 = { 2, 3,4, 5, 10, 12, 13, 14, 15, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31, 32 };
		for (int cell : row4) {
			board[5][cell].add(BoardField.Obstacle);
		}
		int[] row5 = { 2, 3, 5, 6,8, 9, 10, 16, 20, 28, 29, 30, 31, 32 };
		for (int cell : row5) {
			board[7][cell].add(BoardField.Obstacle);
		}

		int[] row6 = { 2, 3,  5, 6, 8, 9, 10, 12, 13, 14, 16, 20, 22, 23, 24,25, 26,27, 28, 29, 30, 31, 32 };
		for (int cell : row6) {
			board[8][cell].add(BoardField.Obstacle);
		}
		int[] row7 = { 2, 3, 5, 6, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 20, 22, 23,24,25,26,30, 31, 32 };
		for (int cell : row7) {
			board[9][cell].add(BoardField.Obstacle);
		}
		int[] row8 = { 2, 3, 5, 6, 8, 9, 10, 12, 13, 14, 22, 23, 24, 26, 28, 29, 30, 31, 32 };
		for (int cell : row8) {
			board[10][cell].add(BoardField.Obstacle);
		}

		int[] row9 = {1, 2, 3, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 20, 21,22,23,24, 26, 27, 28, 30, 31, 32,33 };
		for (int cell : row9) {
			board[12][cell].add(BoardField.Obstacle);
		}
		board[13][16].add(BoardField.Obstacle);
		board[13][20].add(BoardField.Obstacle);
	}
	
	private static void createBoard3(List<BoardField>[][] board) {
		int[] row2 = { 2, 3, 5, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 30, 31, 32 };
		for (int cell : row2) {
			board[2][cell].add(BoardField.Obstacle);
		}
		int[] row3 = { 2, 3, 5, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31,
				32 };
		for (int cell : row3) {
			board[4][cell].add(BoardField.Obstacle);
		}
		int[] row4 = { 2, 3, 5, 10, 12, 13, 14, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31, 32 };
		for (int cell : row4) {
			board[5][cell].add(BoardField.Obstacle);
		}
		int[] row5 = { 1, 2, 3, 4, 5, 6, 16, 20, 28, 29, 30, 31, 32 };
		for (int cell : row5) {
			board[7][cell].add(BoardField.Obstacle);
		}

		int[] row6 = { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 13, 14, 16, 20, 22, 23, 24, 26, 28, 29, 30, 31, 32 };
		for (int cell : row6) {
			board[8][cell].add(BoardField.Obstacle);
		}
		int[] row7 = { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 20, 22, 23, 24, 26, 28, 29, 30, 31, 32 };
		for (int cell : row7) {
			board[9][cell].add(BoardField.Obstacle);
		}
		int[] row8 = { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 13, 14, 22, 23, 24, 26, 28, 29, 30, 31, 32 };
		for (int cell : row8) {
			board[10][cell].add(BoardField.Obstacle);
		}

		int[] row9 = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31, 32 };
		for (int cell : row9) {
			board[12][cell].add(BoardField.Obstacle);
		}
		board[13][16].add(BoardField.Obstacle);
		board[13][20].add(BoardField.Obstacle);
	}

}
