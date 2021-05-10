package pacman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import lombok.Setter;

public class Player extends Sprite {

	@Getter
	private int points = 0;
	@Setter 
	private Move direction = Move.Stop;

	public Player(int position_x, int position_y, ArrayList<BoardField>[][]  board) {
		this.board = board;
		this.position_x = position_x;
		this.position_y = position_y;
	}

	public void move() {
		move(direction, BoardField.Player);
	}

}
