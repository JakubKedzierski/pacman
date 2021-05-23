package pacman;

import java.util.ArrayList;
import lombok.Getter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 
 * Pozycja w rankingu
 *
 */
class RankField {

	public String playerName;
	public int points;

	RankField() {
	}

	RankField(String name, int points) {

		this.playerName = name;
		this.points = points;
	}

	@Override
	public String toString() {

		return playerName + " " + String.valueOf(points) + "\n";
	}

}

/**
 * 
 * Ranking ktory zostaje wyswietlony po zakonczeniu gry
 *
 */
public class Ranking implements RankingInterface {

	@Getter
	private final ArrayList<RankField> rankList = new ArrayList<RankField>();

	public void addRankField(String name, int points) {

		rankList.add(new RankField(name, points));
		acceptChanges();
	}

	/**
	 * Sortowanie rankingu wedlug najlepszego wyniku
	 */
	private void sortRanking() {

		for (int i = 0; i < 9 && i < rankList.size() - 1; i++) {
			for (int j = 0; j <= i; j++) {

				if (rankList.get(j + 1).points > rankList.get(j).points) {

					RankField tmpRankField = rankList.get(j);
					rankList.set(j, rankList.get(j + 1));
					rankList.set(j + 1, tmpRankField);

				}
			}
		}
	}

	/**
	 * Zapisanie rankingu do pliku
	 */
	private void acceptChanges() {

		sortRanking();
		saveFile();
		init();
	}

	/** Zapis do pliku
	 * 
	 */
	private void saveFile() {

		File fileObject = new File("Ranking.sav");

		try {

			FileWriter writer = new FileWriter(fileObject, false);

			int i = 10;
			for (RankField rankfield : rankList) {

				if (i < 0)
					break;
				writer.write(rankfield.toString());
				i--;
			}

			writer.close();
		}

		catch (IOException e) {
			System.out.println("Błąd zapisu do pliku!");
		}
	}
	
	/**
	 * Tworzenie rankingu gdy w katalogu uzytkownika nie ma zadnego rankingu
	 */
	private void init() {

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
		} catch (IOException e) {
			System.out.println("Błąd odczytu!");
		}
	}


	/**
	 * Przygotowanie rankingu
	 * @return tabela z najlepszymi wynikami
	 */
	public JTable prepareRankingTable() {

                init();
            
		String[] columnNames = { "Miejsce", "Nazwa", "Punkty" };

		String[][] rowData = new String[11][3];

		rowData[0][0] = "Miejsce";
		rowData[0][1] = "Nazwa";
		rowData[0][2] = "Punkty";

		for (int i = 0; i < rankList.size() && i < 10; i++) {

			rowData[i + 1][0] = String.valueOf(i + 1);
			rowData[i + 1][1] = rankList.get(i).playerName;
			rowData[i + 1][2] = String.valueOf(rankList.get(i).points);
		}

		JTable rankTable = new JTable(rowData, columnNames) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		rankTable.setCellSelectionEnabled(false);
		rankTable.setFocusable(false);

		return rankTable;
	}

}