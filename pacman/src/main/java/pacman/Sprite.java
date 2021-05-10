package pacman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;

public abstract class Sprite implements Runnable {
	protected Timer timer;
	protected final int INITIAL_DELAY = 300;
	protected final int PERIOD_INTERVAL = 300;
	protected volatile ArrayList<BoardField>[][] board;
	protected int position_x = 0;
	protected int position_y = 0;
	protected int boardWidth = 20;
	protected int boardHeight = 20;

	public void run() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new gameLoop(), INITIAL_DELAY, PERIOD_INTERVAL);
	}

	protected class gameLoop extends TimerTask {

		@Override
		public void run() {
			move();
		}
	}

	protected boolean checkMove(Move move) {
		switch (move) {
		case Up:
			if (position_x - 1 > 0) {
				if (!board[position_x - 1][position_y].contains(BoardField.Obstacle)) {
					return true;
				}
			}
			break;

		case Down:
			if (position_x + 1 < boardHeight) {
				if (!board[position_x + 1][position_y].contains(BoardField.Obstacle)) {
					return true;
				}
			}
			break;

		case Left:
			if (position_y - 1 > 0) {
				if (!board[position_x][position_y - 1].contains(BoardField.Obstacle)) {
					return true;
				}
			}
			break;

		case Right:
			if (position_y + 1 < boardWidth) {
				if (!board[position_x][position_y + 1].contains(BoardField.Obstacle)) {
					return true;
				}
			}
			break;

		}
		return false;
	}

	protected void move(Move move, BoardField sprite) {
		if (checkMove(move)) {
			switch (move) {
			case Right:
				board[position_x][position_y].remove(sprite);
				board[position_x][position_y].add(BoardField.EmptyField);
				position_y = position_y + 1;

				if (board[position_x][position_y].contains(BoardField.EmptyField))
					board[position_x][position_y].remove(BoardField.EmptyField);

				board[position_x][position_y].add(sprite);
				break;

			case Left:
				board[position_x][position_y].remove(sprite);
				board[position_x][position_y].add(BoardField.EmptyField);
				position_y = position_y - 1;

				if (board[position_x][position_y].contains(BoardField.EmptyField))
					board[position_x][position_y].remove(BoardField.EmptyField);

				board[position_x][position_y].add(sprite);
				break;

			case Down:
				board[position_x][position_y].remove(sprite);
				board[position_x][position_y].add(BoardField.EmptyField);
				position_x = position_x + 1;

				if (board[position_x][position_y].contains(BoardField.EmptyField))
					board[position_x][position_y].remove(BoardField.EmptyField);

				board[position_x][position_y].add(sprite);
				break;
				
			case Up:
				board[position_x][position_y].remove(sprite);
				board[position_x][position_y].add(BoardField.EmptyField);
				position_x = position_x - 1;

				if (board[position_x][position_y].contains(BoardField.EmptyField))
					board[position_x][position_y].remove(BoardField.EmptyField);

				board[position_x][position_y].add(sprite);
				break;
			}
		}

	}

	protected abstract void move();

}
