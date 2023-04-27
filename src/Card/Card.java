package Card;
import java.util.List;

import Enum.Rank;
import Enum.Suit;

public class Card implements Comparable<Card> {
    Rank rank;
    Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public String PrintCard(){
        return getRank().toString().toLowerCase() + " " + PrintSuit().toLowerCase();
    }
    
    public void afficherCarte() {
        String symbole;
            switch (suit) {
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
        if (rank.ordinal() < 9) {
            valeur = " " + (rank.ordinal() + 2); // 2 à 10
        } else {
            valeur = rank.name().substring(0, 1); // J, Q, K, A
        }
        String premiereLigne = "|" + valeur + "   | ";
        String deuxiemeLigne = "|  " + symbole + "  | ";
        String troisiemeLigne = "|___" + valeur + "| ";
        if (rank.ordinal() >= 9) {
            premiereLigne = "|" + valeur + "    | ";
            troisiemeLigne = "|____" + valeur + "| ";
        }
        if (rank.ordinal() == 8) {
            premiereLigne = "|" + valeur + "  | ";
            troisiemeLigne = "|__" + valeur + "| ";
        }
        System.out.println(" _____");
        System.out.println(premiereLigne + "\n" + deuxiemeLigne);
        System.out.print(troisiemeLigne + "\n\n");
    }
    
    

    public String PrintSuit(){
        switch (this.suit) {
            case HEARTS:
                return "\u0003";

            case DIAMONDS:
                return "\u0004";

            case CLUBS:
                return "\u0005";

            case SPADES:
                return "\u0006";
        
            default:
                break;
        }
        return null;
    }

    @Override
    public int compareTo(Card other) {
        return rank.compareTo(other.getRank());
    }
}
