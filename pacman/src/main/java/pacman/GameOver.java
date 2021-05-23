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

 /**
  * Klasa odpowiedzialna za wyswietlenie informacji ko�cz�cych gr�
  *
  */
public class GameOver extends JFrame {
    
        private Ranking ranking = null;
        private JPanel dialogPanel = new JPanel();
        JTextField playerNameField = null;
        JButton saveButton = null;
        
        public boolean waitForClose;
        
	public GameOver(final int points, final RankingInterface ranking) {
		
            super("Pacman");
            
            setTitle("Koniec gry - " + String.valueOf(points) + "pkt.");
            setSize(320, 240);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            
            JLabel lblNewLabel_2 = new JLabel("Naleleży podać nazwę gracza:");
            lblNewLabel_2.setBounds(10, 10, 300, 36);
            dialogPanel.add(lblNewLabel_2);
            
            playerNameField = new JTextField("Przykładowy Nick użytkownika");
            playerNameField.setBounds(10, 80, 300, 50);
            dialogPanel.add(playerNameField);
            
            saveButton = new JButton();
            saveButton.setText("Zapisz");
            saveButton.setBounds(10, 200, 200, 50);
            
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   
                    ranking.addRankField(playerNameField.getText(), points);
                    dispose();
                }
             } );
            
            dialogPanel.add(saveButton);
            
            this.getContentPane().add(dialogPanel);
            this.setResizable(false);
            setVisible(true);
            
            waitForClose = true;
	}
        
}