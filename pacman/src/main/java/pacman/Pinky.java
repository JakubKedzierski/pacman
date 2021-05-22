package pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  Duszek probujacy odciac droge graczowi (porusza sie w przeciwnym kierunku do ruchu gracza)
 */
public class Pinky extends Sprite implements Ghost {
	private boolean defaultCornerFlag = false;
	private Player player;

	public Pinky(int position_x, int position_y, List<BoardField>[][] board, PacmanView pacman, Player player) {
		super(position_x, position_y, board, pacman);
		sprite = BoardField.Pinky;
		this.player = player;
	}

	@Override
	public synchronized void move() {
		Move move = Move.Stop;
		if (!defaultCornerFlag) {
			move = goToDefaultCorner();
		} else {
			move = generateMove();
		}

		if (move == Move.Stop || !checkMove(move)) {
			while (!checkMove(move)) {

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
		}

		move(move, BoardField.Pinky);
	}

	public Move goToDefaultCorner() {
		Move move = Move.Right;

		if (position_x > 23) {
			defaultCornerFlag = true;
		}

		return move;
	}

	@Override
	public Move generateMove() {
		Move move = Move.Stop;
		switch (player.getDirection()) {
		case Up:
			move = Move.Down;
			break;
		case Down:
			move = Move.Up;
			break;
		case Right:
			move = Move.Left;
			break;
		case Left:
			move = Move.Right;
			break;
		}

		return move;

	}

}
