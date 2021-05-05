package pacman;

import java.util.Timer;
import java.util.TimerTask;


public class Player extends Sprite {
	
	int points = 0;	
	
	public Player(int position_x,int position_y, BoardField[][] board) {
		this.board = board;
		this.position_x = position_x;
		this.position_y = position_y;
	}

	public void run() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new gameLoop(), INITIAL_DELAY, PERIOD_INTERVAL);
	}

	
	private class gameLoop extends TimerTask {

		@Override
		public void run() {
			board[position_x][position_y] = BoardField.EmptyField;
			position_y = position_y +1;
			board[position_x][position_y] = BoardField.Player;
			
		}
	}



}
