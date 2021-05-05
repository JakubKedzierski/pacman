package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PacmanUI extends JFrame {
	Pacman game = null;
	BoardPanel boardPanel = null;

	public PacmanUI(Pacman pacman) {
		super("Pacman");
		this.game = pacman;
		setTitle("Pacman");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		boardPanel = new BoardPanel(game.getBoard(),pacman.getBoardWidth(),pacman.getBoardHeigth());
		this.getContentPane().setLocation(100, 100);
		this.getContentPane().add(boardPanel);
		
		boardPanel.setLocation(20, 100);
		
		setVisible(true);
	}

	public void drawBoard() {
		BoardField board[][] = game.getBoard();


	}

	public static void main(String[] args) {
		Pacman pacman = new Pacman();
		PacmanUI gameUI = new PacmanUI(pacman);
	}

}

class BoardPanel extends JPanel {
	private static final int RECT_WIDTH = 10;
	private static final int RECT_HEIGHT = RECT_WIDTH;
	private BoardField[][] board;
	private int boardWidth;
	private int boardHeight;
	
	public BoardPanel(BoardField[][] board,int boardWidth,int boardHeight) {
		this.board = board;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int offset = 40;
		super.paintComponent(g);
		
		for (int i = 0; i <boardWidth; i++) {
			for (int j = 0; j < boardHeight; j++) {
				if (board[i][j] == BoardField.EmptyField) {
					g.setColor(Color.WHITE);
					g.fillRect(j*20+offset, i*20+offset, RECT_WIDTH, RECT_HEIGHT);
				}
				if (board[i][j] == BoardField.Obstacle) {
					g.setColor(Color.RED);
					g.fillRect(j*20+offset, i*20+offset, RECT_WIDTH, RECT_HEIGHT);
				}
				if (board[i][j] == BoardField.Player) {
					g.setColor(Color.BLACK);
					g.fillRect(j*20+offset, i*20+offset, RECT_WIDTH, RECT_HEIGHT);
				}
				if (board[i][j] == BoardField.Pinky) {
					g.setColor(Color.GREEN);
					g.fillRect(j*20+offset, i*20+offset, RECT_WIDTH, RECT_HEIGHT);
				}
				if (board[i][j] == BoardField.Clyde) {
					g.setColor(Color.PINK);
					g.fillRect(j*20+offset, i*20+offset, RECT_WIDTH, RECT_HEIGHT);
				}
			}
			System.out.println("");
		}
	}
}
