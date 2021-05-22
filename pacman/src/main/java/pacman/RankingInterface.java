package pacman;


/**
 * 
 * Interfejs pozwalajacy na przekazywanie informacji o stanie gry do klasy rankingowej.
 * Interfejs pozwala na utrzymanie zasad SOLIDa
 *
 */
public interface RankingInterface {
    
    public void addRankField(String name, int points);
}
