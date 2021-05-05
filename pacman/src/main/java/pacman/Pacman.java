package pacman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import lombok.Getter;

public class Pacman {
	@Getter
	private volatile BoardField[][] board = null;
	@Getter
	private int boardWidth = 20;
	@Getter
	private int boardHeigth = 20;
	@Getter
	private ArrayList<Sprite> sprites = null;


	public Pacman() {

		board = new BoardField[boardWidth][boardHeigth];
		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeigth; j++) {
				if (i == 0) {
					board[i][j] = BoardField.Obstacle;
				} else if (i < 19) {
					if (j == 0 || j == 19)
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
		sprites.add(new Player(10,10,board));
		sprites.add(new Pinky(4,7,board));

	}
	
	public void play() {
		for(Sprite sprite:sprites) {
			new Thread(sprite).start();
		}
	}





}
