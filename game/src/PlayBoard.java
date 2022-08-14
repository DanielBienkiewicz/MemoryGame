import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PlayBoard {

    int size;
    ArrayList<String> words = new ArrayList<>();
    List<String> rowA;

    List<String> rowB = new ArrayList();
    boolean[] hidden;


    public PlayBoard(int diffParam) {
        size = diffParam;
        hidden = new boolean[size * 2];
        Arrays.fill(hidden, true);

        loadWords();
        Collections.shuffle(words);
        rowA = new ArrayList<>(words.subList(0, size));
        rowB.addAll(rowA);
        Collections.shuffle(rowB);
    }

    private void loadWords() {
        Scanner s = null;
        try {
            s = new Scanner(new File("./game/resources/Words.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNext()) {
            words.add(s.next());
        }
        s.close();
    }

    public void printBoard(int left) {
        System.out.println("\nLevel: " + (size == 4 ? "easy" : "hard"));
        System.out.println("Guess chances: " + left + "\n");
        System.out.printf("  %15s %15s %15s %15s\nA", "1", "2", "3", "4");
        for (int i = 0; i < size; i++) {
            System.out.printf(" %15s", (hidden[i] ? "X" :  rowA.get(i)));
            if (i > 0 && i % 3 == 0) System.out.println();
        }
        for (int i = 0; i < size; i++) {
            if  (i == 0) System.out.print("B");
            System.out.printf(" %15s", (hidden[i + 4] ? "X" :  rowB.get(i)));
            if (i > 0 && i % 3 == 0) System.out.println();
        }


    }


    public void coverWords(String... coordinates) {
        hidden[(coordinates[0].charAt(0) - 'A') + (coordinates[0].charAt(1) - '0')] = true;
        hidden[(coordinates[1].charAt(0) - 'A') + (coordinates[1].charAt(1) - '0')] = true;
    }

    public String uncoverWord(String coordinates) {
        hidden[(coordinates.charAt(0) - 'A') + (coordinates.charAt(1) - '0')] = false;
        int column = Integer.parseInt(coordinates.substring(1)) - 1;
        return coordinates.charAt(0) == 'A' ? rowA.get(column) : rowB.get(column) ;
    }
}
