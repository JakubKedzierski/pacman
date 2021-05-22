package pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * Duszek nasladujacy ruchy innego duszka - w przypadku naszej gry nasladuje pinkiego
 *
 */
public class Inky extends Sprite implements Ghost {
	private boolean defaultCornerFlag = false;
	private Sprite ghostToPursue;
	
	protected Inky(int position_x, int position_y, List<BoardField>[][] board, PacmanView pacman,Sprite ghostToPursue) {
		super(position_x, position_y, board, pacman);
		sprite = BoardField.Inky;
		this.ghostToPursue = ghostToPursue;
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

		move(move, BoardField.Inky);

	}
	
	/**
	 * Ruch do domyslnego rogu
	 * @return odpowiedni ruch
	 */
	public Move goToDefaultCorner() {
		Move move = Move.Down;

		if (position_x > 10) {
			defaultCornerFlag = true;
		}
		if(position_x <= 7) {
			move = Move.Left;
		}

		return move;
	}
	
	/**
	 * Inky nasladuje ruchy przekazanego sprite
	 */
	@Override
	public Move generateMove() {
		Random rand = new Random();
		int randDir = rand.nextInt(2);
		randDir = 1;
		Move move = Move.Stop;
		if(randDir == 1) {
			move = ghostToPursue.getDir();
		}
		return move;

	}

}
