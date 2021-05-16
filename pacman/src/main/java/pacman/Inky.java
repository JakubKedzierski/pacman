package pacman;

import java.util.ArrayList;
import java.util.Random;

public class Inky extends Sprite implements Ghost {

	protected Inky(int position_x, int position_y, ArrayList<BoardField>[][] board) {
		super(position_x, position_y, board);
	}

	@Override
	public synchronized void move() {
		Random rand = new Random();
		int dir = rand.nextInt(4);
		Move move = Move.Stop;
		switch(dir) {
		case 0:
			move = Move.Down;
			break;
		case 1:
			move = Move.Up;
			break;
		case 2:
			move = Move.Left;
			break;
		case 3:
			move = Move.Right;
			break;
		}
		
		move(move,BoardField.Inky);
		
	}

}
