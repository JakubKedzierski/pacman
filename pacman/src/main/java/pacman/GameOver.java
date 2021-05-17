package pacman;

import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GameOver extends JFrame {
    
        private Ranking ranking = null;
        private BoardPanel dialogPanel = new BoardPanel();
        
        public boolean waitForClose = true;
        
	public GameOver(int points) {
		
            super("Pacman");
            
            setTitle("Koniec gry - " + String.valueOf(points) + "pkt.");
            setSize(320, 240);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            JLabel lblNewLabel_2 = new JLabel("Należy podać nazwę gracza:");
            lblNewLabel_2.setBounds(10, 10, 300, 36);
            dialogPanel.add(lblNewLabel_2);
            
            this.getContentPane().add(dialogPanel);
            this.setResizable(false);
            setVisible(true);
	}
        
        public String waitForName() {
           
            return "ab";
        }
}