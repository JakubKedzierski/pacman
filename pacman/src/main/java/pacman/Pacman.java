package pacman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import lombok.Getter;

public class Pacman {
	@Getter
	private volatile ArrayList<BoardField>[][] board = null;
	@Getter
	private int boardWidth = BoardFactory.boardWidth;
	@Getter
	private int boardHeigth = BoardFactory.boardHeigth;
	@Getter
	private ArrayList<Sprite> sprites = null;
	@Getter
	private Player player = null;


	public Pacman() {

		board = BoardFactory.createBoard();
		BoardFactory.setDefaultPosition(board);
		
		sprites = new ArrayList<Sprite>();
		this.player = new Player(BoardFactory.playerDefaultPosition[0],BoardFactory.playerDefaultPosition[1],board);
		sprites.add(this.player);
		sprites.add(new Pinky(BoardFactory.pinkyDefaultPosition[0],BoardFactory.pinkyDefaultPosition[1],board));
		sprites.add(new Clyde(4,13,board));

	}
	
	public void play() {
		for(Sprite sprite:sprites) {
			new Thread(sprite).start();
		}
	}


}