package pacman;

import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;

public abstract class Sprite implements Runnable {
	protected Timer timer;
	protected final int INITIAL_DELAY = 500;
	protected final int PERIOD_INTERVAL = 500;
	protected volatile BoardField[][] board;
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
		case Down:
			if (position_x - 1 > 0) {
				if (board[position_x - 1][position_y] != BoardField.Obstacle) {
					return true;
				}
			}
			break;

		case Up:
			if (position_x + 1 < boardHeight) {
				if (board[position_x + 1][position_y] != BoardField.Obstacle) {
					return true;
				}
			}
			break;

		case Left:
			if (position_y - 1 > 0) {
				if (board[position_x][position_y - 1] != BoardField.Obstacle) {
					return true;
				}
			}
			break;

		case Right:
			if (position_y + 1 < boardWidth) {
				if (board[position_x][position_y + 1] != BoardField.Obstacle) {
					return true;
				}
			}
			break;

		}
		return false;
	}

	protected abstract void move();

}
