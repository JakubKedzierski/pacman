package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PacmanUI extends JFrame implements KeyListener {
	
        Pacman game = null;
	BoardPanel boardPanel = null;
        
        JLabel lblpoints = null;
        JLabel lblranking = null;

	private Timer timer;
	private final int INITIAL_DELAY = 10;
	private final int PERIOD_INTERVAL = 10;
        private Ranking ranking = new Ranking();

	public PacmanUI(Pacman pacman) {
		
                super("Pacman");
		this.game = pacman;
		setTitle("Pacman");
		setSize(1024, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addKeyListener(this);
                
		boardPanel = new BoardPanel(game.getBoard(), pacman.getBoardWidth(), pacman.getBoardHeigth());
		this.getContentPane().setLocation(100, 100);
		this.getContentPane().add(boardPanel);
		boardPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ruch mo\u017Cesz wykona\u0107\r\n za pomoc\u0105 strza\u0142ek");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(596, 74, 357, 58);
		boardPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Spacja powoduje zatrzymanie ruchu");
		lblNewLabel_1.setBounds(621, 127, 300, 36);
		boardPanel.add(lblNewLabel_1);
                
                lblpoints = new JLabel();
		lblpoints.setBounds(641, 245, 217, 36);
		boardPanel.add(lblpoints);
                
                lblranking = new JLabel();
		lblranking.setBounds(641, 345, 300, 300);
		boardPanel.add(lblranking);

                ranking.init();
                lblranking.setText(ranking.prepareRanking());
                
		timer = new Timer();
		timer.scheduleAtFixedRate(new gameLoop(), INITIAL_DELAY, PERIOD_INTERVAL);
		setVisible(true);
	}

	private class gameLoop extends TimerTask {

		@Override
		public void run() {
			boardPanel.repaint();
                        lblpoints.setText("Punkty: " + String.valueOf(game.player.points));
                        
                        if(game.player.lives < 1) {
                            
                            timer.cancel();
                            
                            GameOver gameOver = new GameOver();
                            ranking.addRankField(gameOver.waitForName(), game.player.points);
                            
                        }
		}
	}

	public static void main(String[] args) {
            
            Pacman pacman = new Pacman();
            PacmanUI gameUI = new PacmanUI(pacman);
            pacman.play();
        }

	public void keyTyped(KeyEvent e) {
		movePacman(e);
	}

	public void keyPressed(KeyEvent e) {
		movePacman(e);
	}

	public void keyReleased(KeyEvent e) {
		movePacman(e);
	}

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

class BoardPanel extends JPanel {
	
        private static final int RECT_WIDTH = 25;
	private static final int RECT_HEIGHT = RECT_WIDTH;
        private static final int RECT_OFFSET = 26;
        
	private ArrayList<BoardField>[][] board;
	private int boardWidth;
	private int boardHeight;

	public BoardPanel(ArrayList<BoardField>[][]  board, int boardWidth, int boardHeight) {
		this.board = board;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
	}

    BoardPanel() {
        
    }

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
				
				if (board[i][j].contains(BoardField.Food)) {
					g.setColor(Color.WHITE);
					g.fillOval(j * RECT_OFFSET + offset, i * RECT_OFFSET + offset, RECT_WIDTH-5, RECT_HEIGHT-5);
				}
			}
			System.out.println("");
		}
	}
}
