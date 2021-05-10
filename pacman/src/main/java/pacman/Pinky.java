package pacman;

import java.util.ArrayList;

public class Pinky extends Sprite implements Ghost {
	
	
	public Pinky(int position_x, int position_y,ArrayList<BoardField>[][]  board) {
		this.board = board;
		this.position_x = position_x;
		this.position_y = position_y;
	}
	
	@Override
	public void move() {
		if (checkMove(Move.Right)) {
			board[position_x][position_y].remove(BoardField.Pinky);
			board[position_x][position_y].add(BoardField.EmptyField);
			position_y = position_y + 1;
			
			if(board[position_x][position_y].contains(BoardField.EmptyField)) 
				board[position_x][position_y].remove(BoardField.EmptyField);
			
			board[position_x][position_y].add(BoardField.Pinky);
		}
	}

}
