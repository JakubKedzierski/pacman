package pacman;

import java.util.Timer;

public abstract class Sprite implements Runnable {
	protected Timer timer;
	protected final int INITIAL_DELAY = 500;
	protected final int PERIOD_INTERVAL = 500;
	protected volatile BoardField[][] board;
	protected int position_x = 0;
	protected int position_y = 0;


}
