package pacman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;

public abstract class Sprite implements Runnable {
	protected Timer timer;
	protected final int INITIAL_DELAY = 150;
	protected final int PERIOD_INTERVAL = 150;
	protected volatile ArrayList<BoardField>[][] board = null;
	@Getter
	protected int position_x = 0;
	@Getter
	protected int position_y = 0;
	protected int boardWidth = BoardFactory.boardWidth;
	protected int boardHeight = BoardFactory.boardHeigth;
	protected BoardField sprite;
	PacmanView pacman = null;
	
	protected int[] defaultPosition = new int[2];

	public void run() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new gameLoop(), INITIAL_DELAY, PERIOD_INTERVAL);
	}
	
	protected Sprite(int position_x, int position_y, ArrayList<BoardField>[][]  board,PacmanView pacman) {
		this.board = board;
		this.position_x = position_x;
		this.position_y = position_y;
		this.defaultPosition[0] = position_x;
		this.defaultPosition[1] = position_y;
		this.pacman = pacman;
	}

	protected class gameLoop extends TimerTask {

		@Override
		public void run() {
			move();
		}
	}

	protected synchronized boolean checkMove(Move move) {
		switch (move) {
		case Up:
			if (position_x - 1 > 0) {
				if (!board[position_x - 1][position_y].contains(BoardField.Obstacle)) {
					return true;
				}
			}
			break;

		case Down:
			if (position_x + 1 < boardWidth) {
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

			if (position_y + 1 < boardHeight) {
				if (!board[position_x][position_y + 1].contains(BoardField.Obstacle)) {
					return true;
				}
			}
			break;

		}
		return false;
	}
	
	public synchronized void setDefaultPosition() {
		timer.cancel();
		timer.purge();
		board[position_x][position_y].remove(sprite);
		if(!board[position_x][position_y].contains(BoardField.EmptyField)) {
			board[position_x][position_y].add(BoardField.EmptyField);
		}
		position_x = defaultPosition[0];
		position_y = defaultPosition[1];
	}
	
	public void restart() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new gameLoop(), INITIAL_DELAY, PERIOD_INTERVAL);
	}

	protected synchronized void move(Move move, BoardField sprite) {
		if (checkMove(move)) {
			switch (move) {
			case Right:
				board[position_x][position_y].remove(sprite);
				board[position_x][position_y].add(BoardField.EmptyField);
				position_y = position_y + 1;

				if (board[position_x][position_y].contains(BoardField.EmptyField)) {
					board[position_x][position_y].remove(BoardField.EmptyField);
				}

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
	
	public ArrayList<Move> getPossibleMoves(){
		ArrayList<Move> moves = new ArrayList<Move>();
		return moves;
	}

	protected abstract void move();

}
