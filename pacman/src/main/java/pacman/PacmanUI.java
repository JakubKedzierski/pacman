package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 * Klasa reprezentujaca graficzny intefejs uzytkownika, ktory jest odzielony od glownej czesci aplikacji i od logiki gry
 */
public class PacmanUI extends JFrame implements KeyListener {

	private Pacman game = null;
	private BoardPanel boardPanel = null;

	private JLabel lblpoints = null;
	// private JLabel lblranking = null;
	private JTable rankingTable = null;

	private Timer timer;
	private final int INITIAL_DELAY = 10;
	private final int PERIOD_INTERVAL = 10;
	private Ranking ranking = new Ranking();

	public PacmanUI(Pacman pacman) {

		super("Pacman");
		this.setResizable(false);
		this.game = pacman;
		setTitle("Pacman");
		setSize(1200, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addKeyListener(this);

		boardPanel = new BoardPanel(game.getBoard(), pacman.getBoardWidth(), pacman.getBoardHeigth());
		this.getContentPane().setLocation(100, 100);
		this.getContentPane().add(boardPanel);
		boardPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ruch mo\u017Cesz wykona\u0107\r\n za pomoc\u0105 strza\u0142ek");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(836, 74, 357, 58);
		boardPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Spacja powoduje zatrzymanie ruchu");
		lblNewLabel_1.setBounds(901, 127, 300, 36);
		boardPanel.add(lblNewLabel_1);

		lblpoints = new JLabel();
		lblpoints.setBounds(970, 200, 217, 36);
		boardPanel.add(lblpoints);

		ranking.init();
		rankingTable = ranking.prepareRankingTable();
		rankingTable.setBounds(900, 250, 250, 176);
		boardPanel.add(rankingTable);

		timer = new Timer();
		timer.scheduleAtFixedRate(new gameLoop(), INITIAL_DELAY, PERIOD_INTERVAL);
		setVisible(true);
	}
	
	/**
	 * Klasa aktualizujaca stan planszy
	 */
	private class gameLoop extends TimerTask {

		@Override
		public void run() {
			boardPanel.repaint();
			lblpoints.setText("Punkty: " + String.valueOf(game.getPlayer().getPoints()));

			if (game.getPlayer().getLives() < 1) {

				timer.cancel();
				timer.purge();

				GameOver gameOver = new GameOver(game.getPlayer().getPoints(), ranking);

				int i = 5;

			}
		}
	}

	public static void main(String[] args) {

		Pacman pacman = new Pacman();
		PacmanUI gameUI = new PacmanUI(pacman);
		pacman.play();
	}
	
	/**
	 * Ruch zadany od u¿ytkownika
	 */
	public void keyTyped(KeyEvent e) {
		movePacman(e);
	}

	/**
	 * Ruch zadany od u¿ytkownika
	 */
	public void keyPressed(KeyEvent e) {
		movePacman(e);
	}

	/**
	 * Ruch zadany od u¿ytkownika
	 */
	public void keyReleased(KeyEvent e) {
		movePacman(e);
	}
	
	/**
	 * Otrzymanie ruchu od uzytkownika
	 * @param e - nacisniety klawisz
	 */
	public void movePacman(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {

		case KeyEvent.VK_UP:
			game.getPlayer().setDirection(Move.Up);
			break;
		case KeyEvent.VK_DOWN:
			game.getPlayer().setDirection(Move.Down);
			break;
		case KeyEvent.VK_LEFT:
			game.getPlayer().setDirection(Move.Left);
			break;
		case KeyEvent.VK_RIGHT:
			game.getPlayer().setDirection(Move.Right);
			break;
		case KeyEvent.VK_SPACE:
			game.getPlayer().setDirection(Move.Stop);
			break;
		}
	}
}

/**
 * Klasa reprezentujaca graficzny wyglad planszy/labiryntu
 *
 */
class BoardPanel extends JPanel {

	private static final int RECT_WIDTH = 20;
	private static final int RECT_HEIGHT = RECT_WIDTH;
	private static final int RECT_OFFSET = 21;

	private List<BoardField>[][] board;
	private int boardWidth;
	private int boardHeight;

	public BoardPanel(List<BoardField>[][] board, int boardWidth, int boardHeight) {
		this.board = board;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
	}

	
	/**
	 * Rysowanie aktualnej tabeli
	 */
	@Override
	protected void paintComponent(Graphics g) {
		int offset = 40;
		super.paintComponent(g);

		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeight; j++) {
				if (board[i][j].contains(BoardField.EmptyField)) {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(j * RECT_OFFSET + offset, i * RECT_OFFSET + offset, RECT_WIDTH, RECT_HEIGHT);
				}
				if (board[i][j].contains(BoardField.Obstacle)) {
					g.setColor(Color.BLUE);
					g.fillRect(j * RECT_OFFSET + offset, i * RECT_OFFSET + offset, RECT_WIDTH, RECT_HEIGHT);
				}
				if (board[i][j].contains(BoardField.Food)) {
					g.setColor(Color.WHITE);
					g.fillOval(j * RECT_OFFSET + 3 + offset, i * RECT_OFFSET + 3 + offset, RECT_WIDTH - 9,
							RECT_HEIGHT - 9);
				}

				if (board[i][j].contains(BoardField.Player)) {
					g.setColor(Color.YELLOW);
					g.fillRect(j * RECT_OFFSET + offset, i * RECT_OFFSET + offset, RECT_WIDTH, RECT_HEIGHT);
				}
				if (board[i][j].contains(BoardField.Pinky)) {
					g.setColor(Color.GREEN);
					g.fillRect(j * RECT_OFFSET + offset, i * RECT_OFFSET + offset, RECT_WIDTH, RECT_HEIGHT);
				}
				if (board[i][j].contains(BoardField.Clyde)) {
					g.setColor(Color.PINK);
					g.fillRect(j * RECT_OFFSET + offset, i * RECT_OFFSET + offset, RECT_WIDTH, RECT_HEIGHT);
				}
				if (board[i][j].contains(BoardField.Blinky)) {
					g.setColor(Color.RED);
					g.fillRect(j * RECT_OFFSET + offset, i * RECT_OFFSET + offset, RECT_WIDTH, RECT_HEIGHT);
				}
				if (board[i][j].contains(BoardField.Inky)) {
					g.setColor(Color.MAGENTA);
					g.fillRect(j * RECT_OFFSET + offset, i * RECT_OFFSET + offset, RECT_WIDTH, RECT_HEIGHT);
				}

			}
		}
	}
}
