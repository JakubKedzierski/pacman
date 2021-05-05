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



}
