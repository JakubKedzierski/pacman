package pacman;

import java.util.Timer;
import java.util.TimerTask;


public class Player implements Sprite {
	
	int points = 0;
	int position_x = 0;
	int position_y = 0;
	private volatile BoardField[][] board;
	
	private Timer timer;
	private final int INITIAL_DELAY = 500;
	private final int PERIOD_INTERVAL = 500;
	
	public Player(int position_x,int position_y, BoardField[][] board) {
		this.position_x = position_x;
		this.position_y = position_y;
		this.board = board;
	}

	public void run() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new gameLoop(), INITIAL_DELAY, PERIOD_INTERVAL);
	}

	
	private class gameLoop extends TimerTask {

		@Override
		public void run() {
			board[position_x][position_y] = BoardField.EmptyField;
			position_x = position_x +1;
			board[position_x][position_y] = BoardField.Player;
			
		}
	}



}
