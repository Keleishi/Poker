import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startGame();
    }

    private static void startGame(){
        Scanner scanner = new Scanner(System.in);

        printStart();
        System.out.println("Welcome to Poker Game!\n");
        System.out.print("Number of players (ex:4): ");
        int numPlayers = scanner.nextInt();
        System.out.println();
        System.out.print("Minimum bet amount (ex:5): ");
        int minBetAmount = scanner.nextInt();
        System.out.println();
        System.out.print("Starting chips (ex:200): ");
        int startingChips = scanner.nextInt();
        System.out.println();

        PokerGame game = new PokerGame(numPlayers,minBetAmount, startingChips);
        game.play();

    }
    
    private static void printStart(){
        System.out.println("\n.------..------..------..------..------.");
        System.out.println("|P.--. ||O.--. ||K.--. ||E.--. ||R.--. |");
        System.out.println("| :/\\: || :/\\: || :/\\: || (\\/) || :(): |");
        System.out.println("| (__) || :\\/: || :\\/: || :\\/: || ()() |");
        System.out.println("| '--'P|| '--'O|| '--'K|| '--'E|| '--'R|");
        System.out.println("`------'`------'`------'`------'`------'\n");
    }
}
