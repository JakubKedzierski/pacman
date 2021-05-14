package pacman;

import java.util.ArrayList;
import lombok.Getter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class RankField {
    
    public String playerName;
    public int points;
    
    public String toString() {
        
        return playerName + " " + String.valueOf(points) + "\n";
    }
}

public class Ranking {
    
    @Getter
    private static ArrayList<RankField> rankList = new ArrayList<RankField>();
    
    public static void addRankField(String name, int points) {
        
        RankField rankfield = new RankField();
        rankfield.playerName = name;
        rankfield.points = points;
        
        System.out.println("foefne");
        
        rankList.add(rankfield);
    }
    
    public static void saveFile() {
        
        File fileObject = new File("Ranking.sav");

        
        try {
            
            FileWriter writer = new FileWriter(fileObject, false);
            
            for(RankField rankfield : rankList) {
                writer.write(rankfield.toString());
            }
            
            writer.close();
        }
        
        catch (IOException e) {
            System.out.println("Błąd zapisu do pliku!");
        }
    }
    
    public static void readFile() {
        
        
    }
    
}
