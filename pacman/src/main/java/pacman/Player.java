package pacman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import jdk.jfr.Unsigned;

import lombok.Getter;
import lombok.Setter;

public class Player extends Sprite {

	@Getter
	int points = 0;
	private Move direction = Move.Stop;
	private Move previousMove = Move.Stop;
	@Getter
	int lives = 1;

	public Player(int position_x, int position_y, ArrayList<BoardField>[][] board) {
		super(position_x,position_y,board);
	}
	
	public void setDirection(Move move) {
		if(direction == Move.Right) previousMove = Move.Right;
		if(direction == Move.Left) previousMove = Move.Left;
		if(direction == Move.Up) previousMove = Move.Up;
		if(direction == Move.Down) previousMove = Move.Down;
		this.direction = move;
	}
	
	@Override
	public synchronized void move() {
		if(!checkMove(direction)) direction = previousMove;
		
                boolean a = board[position_x][position_y].contains(BoardField.Blinky);
		boolean b = board[position_x][position_y].contains(BoardField.Clyde);
		boolean c = board[position_x][position_y].contains(BoardField.Inky);
		boolean d = board[position_x][position_y].contains(BoardField.Pinky);

		if (a | b | c | d) {
			timer.purge();
			timer.cancel();
			lives--; 
		}
                
		move(direction, BoardField.Player);

		if (board[position_x][position_y].contains(BoardField.Food)) {
			points += 10;
			board[position_x][position_y].remove(BoardField.Food);
		}
	}

}
