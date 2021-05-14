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
                
                boolean a = board[position_x][position_y].contains(BoardField.Blinky);
                boolean b = board[position_x][position_y].contains(BoardField.Clyde);
                boolean c = board[position_x][position_y].contains(BoardField.Inky);
                boolean d = board[position_x][position_y].contains(BoardField.Pinky);
                
                if(a | b | c | d) {
                    System.exit(0);
                }
                
		if(board[position_x][position_y].contains(BoardField.Food)) {
			points += 10;
			board[position_x][position_y].remove(BoardField.Food);
		}
	}

}
