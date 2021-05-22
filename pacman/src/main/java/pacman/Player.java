package pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import jdk.jfr.Unsigned;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Klasa reprezentujaca gracza
 *
 */
public class Player extends Sprite {

	@Getter
	int points = 0;
	@Getter
	private Move direction = Move.Stop;
	private Move previousMove = Move.Stop;
	@Getter
	int lives = 3;
	
	

	public Player(int position_x, int position_y, List<BoardField>[][] board, PacmanView pacman) {
		super(position_x, position_y, board, pacman);
		sprite = BoardField.Player;
	}
	
	/**
	 * Ustawienie ruchu oraz poprzedniego ruchu tak aby uczucie poruszania sie po planszy bylo bardziej przyjazne dla uzytkownika
	 * @param move - ruch do wykonania
	 */
	public void setDirection(Move move) {
		if (direction == Move.Right)
			previousMove = Move.Right;
		if (direction == Move.Left)
			previousMove = Move.Left;
		if (direction == Move.Up)
			previousMove = Move.Up;
		if (direction == Move.Down)
			previousMove = Move.Down;
		this.direction = move;
	}
	

	/**
	 * Wykonanie ruchu - ruch jest generowany poprzez timer z klasy bazowej - sprite
	 */
	@Override
	public synchronized void move() {
		if (!checkMove(direction))
			direction = previousMove;

		boolean a = board[position_x][position_y].contains(BoardField.Blinky);
		boolean b = board[position_x][position_y].contains(BoardField.Clyde);
		boolean c = board[position_x][position_y].contains(BoardField.Inky);
		boolean d = board[position_x][position_y].contains(BoardField.Pinky);

		if (a | b | c | d) {
			lives--;
			if(lives >=0) {
				pacman.restartGame();
				return;
			}
		}

		move(direction, BoardField.Player);

		if (board[position_x][position_y].contains(BoardField.Food)) {
			points += 10;
			board[position_x][position_y].remove(BoardField.Food);
		}
		
		a = board[position_x][position_y].contains(BoardField.Blinky);
		b = board[position_x][position_y].contains(BoardField.Clyde);
		c = board[position_x][position_y].contains(BoardField.Inky);
		d = board[position_x][position_y].contains(BoardField.Pinky);

		if (a | b | c | d) {
			lives--;
			if(lives >=0) {
				pacman.restartGame();
			}
		}
	}

}
