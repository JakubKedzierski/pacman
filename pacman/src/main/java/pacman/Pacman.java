package pacman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import lombok.Getter;

public class Pacman {
	@Getter
	private volatile ArrayList<BoardField>[][] board = null;
	@Getter
	private int boardWidth = 20;
	@Getter
	private int boardHeigth = 20;
	@Getter
	private ArrayList<Sprite> sprites = null;
	@Getter
	private Player player = null;


	public Pacman() {

		board = new ArrayList[boardWidth][boardHeigth];
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j] =  new ArrayList<BoardField>();
			}
		}
		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeigth; j++) {
				if (i == 0) {
					board[i][j].add(BoardField.Obstacle);
				} else if (i < 19) {
					if (j == 0 || j == 19)
						board[i][j].add(BoardField.Obstacle);
					else
						board[i][j].add(BoardField.EmptyField);
				} else {
					board[i][j].add(BoardField.Obstacle);
				}
			}
		}
		
		for(int i=2;i<9;i++) {
			board[6][i].remove(BoardField.EmptyField);
			board[6][i].add(BoardField.Obstacle);
		}
		
		for(int i=2;i<9;i++) {
			board[8][i].remove(BoardField.EmptyField);
			board[8][i].add(BoardField.Obstacle);
		}

		board[10][10].add(BoardField.Player);
		board[4][7].add(BoardField.Pinky);
		board[4][13].add(BoardField.Clyde);

		sprites = new ArrayList<Sprite>();
		this.player = new Player(10,10,board);
		sprites.add(this.player);
		sprites.add(new Pinky(4,7,board));

	}
	
	public void play() {
		for(Sprite sprite:sprites) {
			new Thread(sprite).start();
		}
	}





}
