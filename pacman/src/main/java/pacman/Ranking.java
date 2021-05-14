package pacman;

import java.util.ArrayList;
import lombok.Getter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.Scanner;

/*------------------------------------------------------------------------------------*/

class RankField {
    
    public String playerName;
    public int points;
    
    RankField() {}
    
    RankField(String name, int points) {
        
        this.playerName = name;
        this.points = points;
    }
    
    public String toString() {
        
        return playerName + " " + String.valueOf(points) + "\n";
    }
}

/*------------------------------------------------------------------------------------*/

public class Ranking {
    
    @Getter
    private final ArrayList<RankField> rankList = new ArrayList<RankField>();
    
    public void addRankField(String name, int points) {
        
        rankList.add(new RankField(name, points));
        saveFile();
    }
    
    /*------------------------------------------------------------------------------------*/
    
    public void saveFile() {
        
        File fileObject = new File("Ranking.sav");

        
        try {
            
            FileWriter writer = new FileWriter(fileObject, false);
            
            int i = 10;
            for(RankField rankfield : rankList) {
                
                if(i < 0) break;
                writer.write(rankfield.toString());
                i--;
            }
            
            writer.close();
        }
        
        catch (IOException e) {
            System.out.println("Błąd zapisu do pliku!");
        }
    }
    
    /*------------------------------------------------------------------------------------*/
    
    public void init() {
        
        File fileObject = new File("Ranking.sav");
        rankList.clear();
        
        try {
            
            Scanner scanner = new Scanner(fileObject);
            
            while (scanner.hasNext()) {
                
                RankField rankfield = new RankField();
                
                rankfield.playerName = scanner.next();
                rankfield.points = scanner.nextInt();
                
                rankList.add(rankfield);
            }
            scanner.close();
        }
        catch (IOException e) {
            System.out.println("Błąd odczytu!");
        }
    }
    
    /*------------------------------------------------------------------------------------*/
    
    public String prepareRanking() {
        
        String text = "<html>Ranking:<br/>";
        
        for(int i = 0; i < rankList.size() && i < 10; i++) {
            
            text += String.valueOf(i+1) + ". ";
            text += rankList.get(i).toString();
            text += "<br/>";
        }
        text += "</html>";
        
        return text;
    } 
}