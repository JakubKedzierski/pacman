package pacman;

import java.util.ArrayList;
import lombok.Getter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.Scanner;


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

public class Ranking {
    
    @Getter
    private static ArrayList<RankField> rankList = new ArrayList<RankField>();
    
    public static void addRankField(String name, int points) {
        
        rankList.add(new RankField(name, points));
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
        
        File fileObject = new File("Ranking.sav");
        rankList.clear();
        
        try {
            
            Scanner scanner = new Scanner(fileObject);
            
            while (scanner.hasNextLine()) {
                
                RankField rankfield = new RankField();
                
                rankfield.playerName = scanner.next();
                rankfield.points = scanner.nextInt();
            }
            scanner.close();
        }
        catch (IOException e) {
            System.out.println("Błąd odczytu!");
        }
    }
}