package pacman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import lombok.Setter;

public class Player extends Sprite {

	@Getter
	int points = 0;
	@Setter
	private Move direction = Move.Stop;
	@Getter
	int lives = 1;

	public Player(int position_x, int position_y, ArrayList<BoardField>[][] board) {
		super(position_x,position_y,board);
	}
	
	@Override
	public synchronized void move() {

		move(direction, BoardField.Player);
		
		boolean a = board[position_x][position_y].contains(BoardField.Blinky);
		boolean b = board[position_x][position_y].contains(BoardField.Clyde);
		boolean c = board[position_x][position_y].contains(BoardField.Inky);
		boolean d = board[position_x][position_y].contains(BoardField.Pinky);

		if (a | b | c | d) {
			lives--;
		}

		if (board[position_x][position_y].contains(BoardField.Food)) {
			points += 10;
			board[position_x][position_y].remove(BoardField.Food);
		}
	}

}
