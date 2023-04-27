import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Card.Card;
import Card.DeckOfCards;
import Hand.Hand;

public class PokerGame {
    private DeckOfCards deck;
    private List<Player> players;
    private int dealerIndex;
    private int currentPlayerIndex;
    private int pot;
    private int minBetAmount;

    public PokerGame(int numPlayers, int minBetAmount, int startingChips) {
        this.minBetAmount = minBetAmount;
        deck = new DeckOfCards();
        deck.shuffle();
        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1), startingChips));
        }
        dealerIndex = 0;
        currentPlayerIndex = (dealerIndex + 1) % numPlayers;
        pot = 0;
    }

    public void doBettingRound() {
        int betsToCall = 0;
        int currentBet = 0;
        boolean isFirstBetRound = true;
        boolean allBetsEqual = false;
        Scanner scanner = new Scanner(System.in);
        List<Player> playersInRound = new ArrayList<>();

        for (Player player : players) {
            if (!player.isFolded()) {
                playersInRound.add(player);
            }
        }

        int numPlayers = playersInRound.size();
        List<Integer> bets = new ArrayList<>(numPlayers);

    
        // initialize all bets to 0
        for (int i = 0; i < numPlayers; i++) {
            bets.add(0);
        }
    
        while ((!allBetsEqual || isFirstBetRound) && playersInRound.size() > 1) {
            allBetsEqual = true;
            isFirstBetRound = false;
            for (int i = 0; i < playersInRound.size(); i++) {
                Player currentPlayer = playersInRound.get(i);
                int playerBet = bets.get(i);
                int maxBet = currentBet - playerBet + betsToCall + minBetAmount;
                int playerChips = currentPlayer.getChips();
    
                if (playerChips <= 0) {
                    continue;
                }
    
                System.out.println(currentPlayer.getName() + ", it's your turn to bet. Current bet: " + currentBet + ", bets to call: " + betsToCall + ", your balance: " + playerChips + "\n");
    
                System.out.println("1. Fold");
                System.out.println("2. Call (" + betsToCall + ")");
                //if (maxBet > playerChips) {
                //    System.out.println("3. All-in (" + playerChips + ")");
                //} else {
                //    
                //}
                System.out.println("3. Raise (min. " + minBetAmount + ")");
                System.out.println("4. Show hand");
    
                int choice = scanner.nextInt();
                int betAmount = 0;
    
                switch (choice) {
                    case 1:
                        System.out.println(currentPlayer.getName() + " folds.\n");
                        currentPlayer.fold();
                        int index = playersInRound.indexOf(currentPlayer);
                        int bet = bets.get(index);
                        playersInRound.get(i).reduceChips(bet);
                        playersInRound.remove(currentPlayer);
                        bets.remove(i);
                        i--;
                        break;
                    case 2:
                        betAmount = betsToCall - playerBet;
                        System.out.println(currentPlayer.getName() + " calls (" + betAmount + ").\n");
                        bets.set(i, playerBet + betAmount);
                        break;
                    case 3:
                    /*
                        if (maxBet >= playerChips) {
                            System.out.println(currentPlayer.getName() + " is all-in (" + playerChips + ").\n");
                            betAmount = playerChips;
                        } else {
                            System.out.println(currentPlayer.getName() + " raises. Enter amount: ");
                            betAmount = scanner.nextInt();
                            while (betAmount < minBetAmount || betAmount > playerChips) {
                                System.out.println("Invalid amount. Please enter an amount between " + minBetAmount + " and " + playerChips + ": ");
                                betAmount = scanner.nextInt();
                            }
                        }*/

                        System.out.println(currentPlayer.getName() + " raises. Enter amount: ");
                        betAmount = scanner.nextInt();
                        while (betAmount < minBetAmount || betAmount > playerChips) {
                            System.out.println("Invalid amount. Please enter an amount between " + minBetAmount + " and " + playerChips + ": ");
                            betAmount = scanner.nextInt();}
                            
                            
                        currentBet += playerBet + betAmount;
                        betsToCall += playerBet + betAmount;

                        bets.set(i, betsToCall);
                        
                        break;
                    case 4:
                        currentPlayer.printFirstCard(currentPlayer.getHand());
                        i--;
                        continue;
                    default:
                        System.out.println("Invalid choice.");
                        i--;
                        break;
                }
                
                allBetsEqual = areEqual(bets);
                if (bets.get(0) != 0 && allBetsEqual) {
                    break;
                }
            
            }
        }
    
        for (int i = 0; i < playersInRound.size(); i++) {
            int bet = bets.get(i);
            Player player = playersInRound.get(i);
            player.reduceChips(bet);
            pot += bet;
        }
    
        System.out.println("Betting round is over. Pot: " + pot + "\n");
}

    private boolean areEqual(List<Integer> bets){
        if (bets == null || bets.size() == 0) {
            return false;
        }
        int firstBet = bets.get(0);
        for (int i = 1; i < bets.size(); i++) {
            if (bets.get(i) != firstBet) {
                return false;
            }
        }
        return true;
    }
    

    public void dealInitialCards() {
        for (Player player : players) {
            player.addCardToHand(deck.dealCard());
            player.addCardToHand(deck.dealCard());
            player.printHand();
        }

        System.out.println();
    }
    

    public void doFlop() {
        // Burn a card
        deck.dealCard();

        System.out.print("Flop ");
        // Deal the flop cards
        for (int i = 0; i < 3; i++) {
            Card card = deck.dealCard();
            Player.addCardToTable(card);
            for (Player player : players) {
                player.addCardToHand(card);
            }
        }
        
        printTable();
        // Start a new betting round
        doBettingRound();
    }
    

    public void doTurn() {
        // Burn a card
        deck.dealCard();
            
        // Deal the turn card
        Card card = deck.dealCard();

        System.out.println("Turn card: ");
        card.afficherCarte();;
        Player.addCardToTable(card);

        for (Player player : players) {
            player.addCardToHand(card);
        }

        printTable();
        // Start a new betting round
        doBettingRound();
    }
    

    public void doRiver() {
        // Burn a card
        deck.dealCard();
        
        // Deal the river card
        Card card = deck.dealCard();

        System.out.println("River card: ");
        card.afficherCarte();

        Player.addCardToTable(card);

        for (Player player : players) {
            player.addCardToHand(card);
        }

        printTable();
        // Start a new betting round
        doBettingRound();
    }
    

    public void evaluateHands() {
        List<Player> playersInHand = new ArrayList<>();
        for (Player player : players) {
            if (!player.isFolded()) {
                playersInHand.add(player);
            }
        }
        
        Hand bestHand = null;
        List<Player> winners = new ArrayList<>();
        
        for (Player player : playersInHand) {
            Hand hand = new Hand(player.getHand());
            
            if (bestHand == null || hand.compareTo(bestHand) > 0) {
                bestHand = hand;
                
                //System.out.println("Best Hand : " + hand.getHandRanking());
                winners.clear();
                winners.add(player);
            } else if (hand.compareTo(bestHand) == 0) {
                winners.add(player);
            }
        }
        
        int potPerWinner = pot / winners.size();
        if (bestHand.getHandRanking() != null) {
            System.out.println("Best Hand : " + bestHand.getHandRankingToString());
        }
        else{
            System.out.println();
        }

        for (Player winner : winners) {
            System.out.println("Winner : " + winner.toString() + ".");
            System.out.println("Won : " + potPerWinner + "$");
            winner.addChips(potPerWinner);
        }
    }
    

    private void printTable(){
        System.out.println("Table :");
        Player.printAllcard(Player.getTable());
    }

    private void printEndCards(){
        for (Player player : players) {
            player.printFirstCard(player.getHand());
        }

        printTable();
    }


    private void printPlayersHand(){
        for (Player player : players) {
            player.printHand();
        }
    }

    private void printSpace(int lign){
        for (int i = 0; i < lign; i++) {
            System.out.println();
        }
    }
    
    private void outOfChips(){
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player.getChips() <= 0) {
                System.out.println(player.getName() + " is out of chips and has been removed from the game.");
                players.remove(i);
                i--;
            }
        }
    }

    private void resetGameInfo(){
        deck = new DeckOfCards();
        deck.shuffle();
        dealerIndex ++;
        pot = 0;

        for (Player player : players) {
            player.isNotFolded();
            player.clearHand();
       }
        
        Player.clearTable();
    }

    private void end(){
        if (players.size() == 1) {
            System.out.println(players.get(0).getName() + " has won the game with " + players.get(0).getChips() + " chips!");
        }
        else{
            System.out.println("\nEnd of the game !\n");
            System.out.println("Remaining players:");
            for (Player player : players) {
                System.out.println("- " + player.getName() + " with " + player.getChips() + " chips!");
            }
        }

    }
    
    
    public void playGame() {
        dealInitialCards();
        doBettingRound();
        doFlop();
        doTurn();
        doRiver();

        //printPlayersHand();
        printEndCards();
        evaluateHands();
        //awardPot();
    }

    public void play() {
        boolean continuePlaying = true;
        Scanner scanner = new Scanner(System.in);
        int round = 1;
    
        while (continuePlaying) {
            System.out.println(" ______________________ \n");
            System.out.println("|      ROUND N°" + round + "       |");
            System.out.println(" ______________________ \n  ");

            
            dealInitialCards();
            doBettingRound();

            doFlop();
            doTurn();
            doRiver();
            
            printEndCards();
            evaluateHands();
            outOfChips();
    
            // Ask the players if they want to continue playing
            System.out.println("Do you want to continue playing? (Y/N)");
            String input = scanner.next().toLowerCase();
            while (!input.equals("y") && !input.equals("n")) {
                System.out.println("Invalid input. Please enter Y or N.");
                input = scanner.next().toLowerCase();
            }
    
            if (input.equals("n") || players.size() == 1) {
                continuePlaying = false;
            }

            System.out.println("End of Round n°" + round);
            round++;
            resetGameInfo();
            printSpace(20);
        }

        end();
    }
}