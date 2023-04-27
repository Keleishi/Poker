import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Card.Card;

public class Player {
    private String name;
    private List<Card> hand;
    private boolean folded;
    private int chips;

    private static List<Card> cardsOnTable;

    public Player(String name, int startingChips) {
        this.name = name;
        this.hand = new ArrayList<>();
        Player.cardsOnTable = new ArrayList<>();
        this.chips = startingChips;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Card> getHand() {
        return hand;
    }

    public static List<Card> getTable() {
        return cardsOnTable;
    }
    
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public static void addCardToTable(Card card) {
        cardsOnTable.add(card);
    }

    public static void clearTable() {
        cardsOnTable.clear();
    }
    
    public void clearHand() {
        hand.clear();
    }
    
    public boolean isFolded() {
        return folded;
    }

    public boolean isNotFolded() {
        folded = false;
        return folded;
    }
    
    public int getChips() {
        return chips;
    }
    
    public void setChips(int chips) {
        this.chips = chips;
    }

    public void reduceChips(int amount) {
        if (amount > chips) {
            throw new IllegalArgumentException("Cannot reduce chips by more than the player has");
        }
        chips -= amount;
    }

    public void addChips(int amount) {
        chips += amount;
    }
    
    
    public void printHand(){
        System.out.println("_______ " + this.name + " _______\n");
        printAllcard(hand);
    }

    public void printFirstCard(List<Card> cartes) {
        StringBuilder sbFirstLigne = new StringBuilder();
        StringBuilder sbPremiereLigne = new StringBuilder();
        StringBuilder sbDeuxiemeLigne = new StringBuilder();
        StringBuilder sbTroisiemeLigne = new StringBuilder();
    
        for (int i = 0; i < 2; i++) {
            Card carte = cartes.get(i);
            String symbole;
            switch (carte.getSuit()) {
                case CLUBS:
                    symbole = "\u0005"; // symbole pique
                    break;
                case DIAMONDS:
                    symbole = "\u0004"; // symbole carreau
                    break;
                case HEARTS:
                    symbole = "\u0003"; // symbole cœur
                    break;
                case SPADES:
                    symbole = "\u0006"; // symbole trèfle
                    break;
                default:
                    symbole = "";
                    break;
            }
            String valeur;
            if (carte.getRank().ordinal() < 9) {
                valeur = " " + (carte.getRank().ordinal() + 2); // 2 à 10
            } else {
                valeur = carte.getRank().name().substring(0, 1); // J, Q, K, A
            }
            String firstLigne = " _____";
            String premiereLigne = "|" + valeur + "   ";
            String deuxiemeLigne = "|  " + symbole + "  ";
            String troisiemeLigne = "|___" + valeur;
    
            if (carte.getRank().ordinal() >= 9) {
                premiereLigne = "|" + valeur + "    ";
                troisiemeLigne = "|____" + valeur;
            }
            if (carte.getRank().ordinal() == 8) {
                premiereLigne = "|" + valeur + "  ";
                troisiemeLigne = "|__" + valeur;
            }
            
            sbFirstLigne.append(firstLigne);
            sbPremiereLigne.append(premiereLigne);
            sbDeuxiemeLigne.append(deuxiemeLigne);
            sbTroisiemeLigne.append(troisiemeLigne);
    
            // Ajouter un espace entre chaque carte, sauf après la dernière carte
            if (i < 1) {
                sbFirstLigne.append("    ");
                sbPremiereLigne.append("|   ");
                sbDeuxiemeLigne.append("|   ");
                sbTroisiemeLigne.append("|   ");
            }
        }
    
        System.out.println("_______ " + this.name + " _______");
        
        System.out.println(sbFirstLigne + " ");
        System.out.println(sbPremiereLigne + "|");
        System.out.println(sbDeuxiemeLigne + "|");
        System.out.println(sbTroisiemeLigne + "|");

        System.out.println("________________________\n  ");

    }
    

    public static void printAllcard(List<Card> cartes) {
        StringBuilder sbFirstLigne = new StringBuilder();
        StringBuilder sbPremiereLigne = new StringBuilder();
        StringBuilder sbDeuxiemeLigne = new StringBuilder();
        StringBuilder sbTroisiemeLigne = new StringBuilder();
    
        for (int i = 0; i < cartes.size(); i++) {
            Card carte = cartes.get(i);
            String symbole;
            switch (carte.getSuit()) {
                case CLUBS:
                    symbole = "\u0005"; // symbole pique
                    break;
                case DIAMONDS:
                    symbole = "\u0004"; // symbole carreau
                    break;
                case HEARTS:
                    symbole = "\u0003"; // symbole cœur
                    break;
                case SPADES:
                    symbole = "\u0006"; // symbole trèfle
                    break;
                default:
                    symbole = "";
                    break;
            }
            String valeur;
            if (carte.getRank().ordinal() < 9) {
                valeur = " " + (carte.getRank().ordinal() + 2); // 2 à 10
            } else {
                valeur = carte.getRank().name().substring(0, 1); // J, Q, K, A
            }
            String firstLigne = " _____";
            String premiereLigne = "|" + valeur + "   ";
            String deuxiemeLigne = "|  " + symbole + "  ";
            String troisiemeLigne = "|___" + valeur;
    
            if (carte.getRank().ordinal() >= 9) {
                premiereLigne = "|" + valeur + "    ";
                troisiemeLigne = "|____" + valeur;
            }
            if (carte.getRank().ordinal() == 8) {
                premiereLigne = "|" + valeur + "  ";
                troisiemeLigne = "|__" + valeur;
            }
            
            sbFirstLigne.append(firstLigne);
            sbPremiereLigne.append(premiereLigne);
            sbDeuxiemeLigne.append(deuxiemeLigne);
            sbTroisiemeLigne.append(troisiemeLigne);
    
            // Ajouter un espace entre chaque carte, sauf après la dernière carte
            if (i < cartes.size() - 1) {
                sbFirstLigne.append("    ");
                sbPremiereLigne.append("|   ");
                sbDeuxiemeLigne.append("|   ");
                sbTroisiemeLigne.append("|   ");
            }
        }
    
        //System.out.println(" _____   _____   _____   _____   _____");
        System.out.println(sbFirstLigne + " ");
        System.out.println(sbPremiereLigne + "|");
        System.out.println(sbDeuxiemeLigne + "|");
        System.out.println(sbTroisiemeLigne + "|\n\n");
    }
    
    

    public void fold() {
        folded = true;
    }
    
    public int bet(int minimumBet, Scanner scanner) {
        System.out.println(name + ", your turn to bet!");
        System.out.println("Your current chips: " + chips);
        System.out.println("Minimum bet: " + minimumBet);
        System.out.print("Enter your bet amount: ");
    
        int betAmount = scanner.nextInt();
        while (betAmount < minimumBet || betAmount > chips) {
            System.out.println("Invalid bet amount. Please enter a value between " + minimumBet + " and " + chips);
            betAmount = scanner.nextInt();
        }
    
        chips -= betAmount;
        return betAmount;
    }
    

    public String toString(){
        return name;
    }

}