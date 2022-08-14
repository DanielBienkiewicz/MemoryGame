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

            System.out.println("Do you wish to play again? Press  key (Y)es. Press any other key to quit");
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

    }

}