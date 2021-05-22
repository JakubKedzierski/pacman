package pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import lombok.Getter;

/**
 * 
 * Glowna klasa odpowiedzialna za przebieg gry
 *
 */
public class Pacman implements PacmanView {
	@Getter
	private volatile List<BoardField>[][] board = null;
	@Getter
	private int boardWidth = BoardFactory.boardWidth;
	@Getter
	private int boardHeigth = BoardFactory.boardHeigth;
	@Getter
	private ArrayList<Sprite> sprites = null;
	@Getter
	private Player player = null;


	public Pacman() {

		board = BoardFactory.createBoard(2);
		BoardFactory.setDefaultPosition(board);
		
		sprites = new ArrayList<Sprite>();
		this.player = new Player(BoardFactory.playerDefaultPosition[0],BoardFactory.playerDefaultPosition[1],board,this);
		sprites.add(this.player);
		sprites.add(new Pinky(BoardFactory.pinkyDefaultPosition[0],BoardFactory.pinkyDefaultPosition[1],board,this,player));
		sprites.add(new Clyde(BoardFactory.clydeDefaultPosition[0],BoardFactory.clydeDefaultPosition[1],board,this));
		Blinky blinky = new Blinky(BoardFactory.blinkyDefaultPosition[0],BoardFactory.blinkyDefaultPosition[1],board,player,this);
		sprites.add(blinky);
		sprites.add(new Inky(BoardFactory.inkyDefaultPosition[0],BoardFactory.inkyDefaultPosition[1],board,this,blinky));

	}
	
	/**
	 * Rozpoczecie gry - start glownej petli
	 */
	public void play() {
		for(Sprite sprite:sprites) {
			new Thread(sprite).start();
		}
	}
	
	/**
	 * Po utracie zycia jesli gracz nadal ma jakies zycia nastepuje restart gry
	 */
	@Override
	public void restartGame() {
		for(Sprite sprite:sprites) {
			sprite.setDefaultPosition();
		}
		BoardFactory.setDefaultPosition(board);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Sprite sprite:sprites) {
			sprite.restart();
		}
	}


}