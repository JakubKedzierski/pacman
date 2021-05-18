package pacman;

import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;

public class GameOver extends JFrame {
    
        private Ranking ranking = null;
        private BoardPanel dialogPanel = new BoardPanel();
        
        public boolean waitForClose = true;
        
	public GameOver(int points) {
		
            super("Pacman");
            
            setTitle("Koniec gry - " + String.valueOf(points) + "pkt.");
            setSize(320, 240);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            
            JLabel lblNewLabel_2 = new JLabel("Należy podać nazwę gracza:");
            lblNewLabel_2.setBounds(10, 10, 300, 36);
            dialogPanel.add(lblNewLabel_2);
            
            JTextField playerNameField = new JTextField("PrzykladowyNickGracza");
            playerNameField.setBounds(10, 80, 300, 50);
            dialogPanel.add(playerNameField);
            
            CloseButton closeButton = new CloseButton();
            closeButton.setText("Zapisz");
            closeButton.setBounds(10, 200, 200, 50);
            dialogPanel.add(closeButton);
            
            this.getContentPane().add(dialogPanel);
            this.setResizable(false);
            setVisible(true);
	}
        
        public String waitForName() {
           
            while(waitForClose) {
                
            }
            
            return "ab";
        }
}

class CloseButton extends JButton implements ActionListener{
 	
    @Override
    public void actionPerformed(ActionEvent arg0) {

    }
 }