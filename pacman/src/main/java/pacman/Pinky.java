package pacman;

public class Pinky extends Sprite implements Ghost {
	
	
	public Pinky(int position_x, int position_y, BoardField[][] board) {
		this.board = board;
		this.position_x = position_x;
		this.position_y = position_y;
	}
	
	@Override
	public void move() {
		if (checkMove(Move.Right)) {
			board[position_x][position_y] = BoardField.EmptyField;
			position_y = position_y + 1;
			board[position_x][position_y] = BoardField.Pinky;
		}
	}

}
