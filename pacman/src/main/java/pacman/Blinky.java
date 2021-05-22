package pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blinky extends Sprite implements Ghost {

	private Player player;

	protected Blinky(int position_x, int position_y, List<BoardField>[][] board, Player player, PacmanView pacman) {
		super(position_x, position_y, board, pacman);
		this.player = player;
		sprite = BoardField.Blinky;
	}

	@Override
	public synchronized void move() {
		Move move = generateMove();

		if (move == Move.Stop) {
			Random rand = new Random();
			int dir = rand.nextInt(4);
			switch (dir) {
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
		}

		move(move, BoardField.Blinky);

	}

	@Override
	public Move generateMove() {
		Move move = Move.Stop;
		
		int verticalDistance = Math.abs(player.getPosition_x() - position_x);
		int horizontalDistance = Math.abs(player.getPosition_y() - position_y);
		
		if(verticalDistance < 8 || horizontalDistance < 8)
		if (horizontalDistance > verticalDistance) {
			if (player.getPosition_y() > position_y && checkMove(Move.Right)) {
				move = Move.Right;
			} else if (player.getPosition_y() < position_y && checkMove(Move.Left)) {
				move = Move.Left;
			}
		} else {
			if (player.getPosition_x() > position_x && checkMove(Move.Down)) {
				move = Move.Down;
			} else if (player.getPosition_x() < position_x && checkMove(Move.Up)) {
				move = Move.Up;
			}
		}
		
		return move;
	}

}
