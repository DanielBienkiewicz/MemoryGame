import java.util.Scanner;

public class Main {

    Scanner sc;

    public static void main(String[] args) {
        Main main = new Main();
        main.sc = new Scanner(System.in);
        main.menu();
        main.sc.close();

    }

    private void menu() {
        System.out.println("welcome to Memory Game");
        boolean rePlay = true;
        while (rePlay) {
            int[] diffParams = chooseDifficulty();

            runGame(diffParams);

            System.out.println("\nDo you wish to play again? Press  key (Y)es. Press any other key to quit");
            if (!"y".equalsIgnoreCase(sc.next())) {
                rePlay = false;
            }
        }
        System.out.println("Game over, bye");
    }

    private int[] chooseDifficulty() {
        int[] diff = null;
        while (diff == null) {
            System.out.println("Choose your difficulty level:");
            System.out.println("E - for easy, H - for Hard/difficult");
            String replayResponse = sc.next();
            if ("e".equalsIgnoreCase(replayResponse)) {
                diff = new int[]{4, 10};
            } else if ("h".equalsIgnoreCase(replayResponse)) {
                diff = new int[]{8, 15};
            } else {
                System.out.println("Bad key pressed. please press correct key");
            }
        }
        return diff;
    }

    private void runGame(int[] diffParams) {
        PlayBoard board = new PlayBoard(diffParams[0]);
        int left = diffParams[1];
        while (left > 0 && board.getUncovered() < diffParams[0]) {
            String coordinatesFirst = inputCoordinates(diffParams[0]);
            String first = board.uncoverWord(coordinatesFirst);
            board.printBoard(left);
            String coordinatesSecond = inputCoordinates(diffParams[0]);
            String second = board.uncoverWord(coordinatesSecond);
            board.printBoard(left);
            System.out.println("Enter any letter");
            sc.next();
           // System.out.println("\033[H\033[2J");
            if (!first.equals(second)) {
                board.coverWords(coordinatesFirst, coordinatesSecond);
            } else {
                board.addUncovered();
            }
            left--;
        }
    }

    private String inputCoordinates(int size) {
        boolean isValidCoordinate = false;
        String coord = "";
        while (!isValidCoordinate) {
            System.out.printf("Enter word coordinate: ");
            coord = sc.next().toUpperCase();
            if (size == 4 && coord.matches("[AB][1-4]") ||
                size == 8 && coord.matches("[AB][1-8]")) {
                isValidCoordinate = true;
            } else {
                System.out.println("Wrong coordinates!");
            }
        }
        return coord;
    }


}