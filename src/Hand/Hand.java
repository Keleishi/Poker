package Hand;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Card.Card;
import Enum.HandRanking;

public class Hand implements Comparable<Hand> {
    private List<Card> cards;
    private HandRanking handRanking;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }    

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public HandRanking getHandRanking() {
        return handRanking;
    }
    

    public void setHandRanking(HandRanking handRanking) {
        this.handRanking = handRanking;
    }
    
    public String getHandRankingToString(){
        switch (handRanking) {
            case HIGH_CARD:
                return "High Card";
            case ONE_PAIR:
                return "One Pair";
            case TWO_PAIR:
                return "Two Pair";
            case THREE_OF_A_KIND:
                return "Three of a kind";
            case STRAIGHT:
                return "Straight";
            case FLUSH:
                return "Flush";
            case FULL_HOUSE:
                return "Full House";
            case FOUR_OF_A_KIND:
                return "Four of a kind";
            case STRAIGHT_FLUSH:
                return "Straight Flush";
            case ROYAL_FLUSH:
                return "Royal Flush";
            default:
                break;
        }
        return null;
    }
    

    public void sort() {
        Collections.sort(cards);
    }

    public void addCardsOnTable(List<Card> cardsOnTable) {
        cards.addAll(cardsOnTable);
    }
    
    public void addCardOnTable(Card cardOnTable) {
        cards.add(cardOnTable);
    }

    @Override
    public int compareTo(Hand other) {
        HandEvaluator evaluator = new HandEvaluator();
        HandRanking thisRanking = evaluator.evaluate(this);
        HandRanking otherRanking = evaluator.evaluate(other);

        this.setHandRanking(thisRanking);
        
        // Si les rangs des mains sont différents, on compare les rangs
        if (thisRanking != otherRanking) {
            return thisRanking.compareTo(otherRanking);
        } else {
            // Si les rangs des mains sont les mêmes, on compare les cartes individuellement
            List<Card> thisCards = this.getCards();
            List<Card> otherCards = other.getCards();
            
            for (int i = thisCards.size() - 1; i >= 0; i--) {
                int comparison = thisCards.get(i).getRank().compareTo(otherCards.get(i).getRank());
                
                if (comparison != 0) {
                    return comparison;
                }
            }
            
        // Si toutes les cartes ont la même valeur, les mains sont équivalentes
        return 0;
        }
    }
}
    
