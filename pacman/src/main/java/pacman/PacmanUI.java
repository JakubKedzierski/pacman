package pacman;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PacmanUI extends JFrame {
	
	public PacmanUI(){
		super("Pacman");
		setTitle("Pacman");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		PacmanUI game= new PacmanUI();
	}

}
