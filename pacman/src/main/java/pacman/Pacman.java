package pacman;

import java.util.ArrayList;

import lombok.Getter;


public class Pacman {
	@Getter
	private BoardField[][] board = null;
	@Getter
	private int boardWidth = 20;
	@Getter
	private int boardHeigth = 20;
	@Getter
	private ArrayList<Sprite> sprites = null;

	public Pacman() {

		board = new BoardField[boardWidth][boardHeigth];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (i == 0) {
					board[i][j] = BoardField.Obstacle;
				} else if (i < 19) {
					if(j==0 || j==19)
						board[i][j] = BoardField.Obstacle;
					else
						board[i][j] = BoardField.EmptyField;
				} else {
					board[i][j] = BoardField.Obstacle;
				}
			}
		}
		
		board[10][10] = BoardField.Player;
		board[4][7] = BoardField.Pinky;
		board[4][13] = BoardField.Clyde;
		
		sprites = new ArrayList<Sprite>();
		sprites.add(new Player());
		sprites.add(new Pinky());
		
	}

	public void printDebugBoard() {
		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j <  boardHeigth; j++) {
				if (board[i][j] == BoardField.EmptyField) {
					System.out.print("0 ");
				}
				if (board[i][j] == BoardField.Obstacle) {
					System.out.print("1 ");
				}
				if (board[i][j] == BoardField.Player) {
					System.out.print("2 ");
				}
				if (board[i][j] == BoardField.Pinky) {
					System.out.print("3 ");
				}
				if (board[i][j] == BoardField.Clyde) {
					System.out.print("4 ");
				}
			}
			System.out.println("");
		}

	}
	
	public static void main(String[] args) {
		Pacman pacman = new Pacman();
		pacman.printDebugBoard();
	}

}
