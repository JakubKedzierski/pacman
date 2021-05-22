package pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * Duszek zablakany - podaza swoimi sciezkami, nie goni pacmana a jedynie zyje swoim zyciem
 *
 */
public class Clyde extends Sprite implements Ghost {

	private boolean defaultCornerFlag = false;

	public Clyde(int position_x, int position_y, List<BoardField>[][] board, PacmanView pacman) {
		super(position_x, position_y, board, pacman);
		sprite = BoardField.Clyde;
	}

	@Override
	public void move() {

		Move move = Move.Up;
		if (!defaultCornerFlag) {
			move = goToDefaultCorner();
		} else {
			move = generateMove();
		}

		move(move, BoardField.Clyde);

	}

	public Move goToDefaultCorner() {
		Move move = Move.Up;

		if (position_x < 5) {
			defaultCornerFlag = true;
		}

		return move;
	}

	@Override
	public Move generateMove() {
		Move move = Move.Up;
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
		return move;
	}

}
