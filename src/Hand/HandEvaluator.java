package Hand;
import java.util.*;

import Card.Card;
import Enum.HandRanking;
import Enum.Rank;
import Enum.Suit;

public class HandEvaluator {
    public HandRanking evaluate(Hand hand) {
        List<Card> allCards = hand.getCards();
        Collections.sort(allCards);
        
        boolean isFlush = isFlush(allCards);
        boolean isStraight = isStraight(allCards);
        
        if (isFlush && isStraight) {
            if (allCards.get(4).getRank() == Rank.ACE) {
                //System.out.println("ROYAL_FLUSH");
                return HandRanking.ROYAL_FLUSH;
            } else {
                //System.out.println("STRAIGHT_FLUSH");
                return HandRanking.STRAIGHT_FLUSH;
            }
        }
        
        if (hasOfAKind(allCards, 4)) {
            //System.out.println("FOUR_OF_A_KIND");
            return HandRanking.FOUR_OF_A_KIND;
        }
        
        if (hasFullHouse(allCards)) {
            //System.out.println("FULL_HOUSE");

            return HandRanking.FULL_HOUSE;
        }
        
        if (isFlush) {
            //System.out.println("FLUSH");
            return HandRanking.FLUSH;
        }
        
        if (isStraight) {
            //System.out.println("STRAIGHT");
            return HandRanking.STRAIGHT;
        }
        
        if (hasOfAKind(allCards, 3)) {
            //System.out.println("THREE_OF_A_KIND");
            return HandRanking.THREE_OF_A_KIND;
        }
        
        if (hasTwoPairs(allCards)) {
            //System.out.println("TWO_PAIR");
            return HandRanking.TWO_PAIR;
        }
        
        if (hasOfAKind(allCards, 2)) {
            //System.out.println("ONE_PAIR");
            return HandRanking.ONE_PAIR;
        }

        //System.out.println("HIGH_CARD");
        return HandRanking.HIGH_CARD;
    }
    
    
    private boolean isFlush(List<Card> cards) {
        Suit suit = cards.get(0).getSuit();
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getSuit() != suit) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isStraight(List<Card> cards) {
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank().ordinal() != cards.get(i-1).getRank().ordinal() + 1) {
                return false;
            }
        }
        return true;
    }
    
    private boolean hasOfAKind(List<Card> cards, int num) {
        int count = 1;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank() == cards.get(i-1).getRank()) {
                count++;
                if (count == num) {
                    return true;
                }
            } else {
                count = 1;
            }
        }
        return false;
    }
    
    private boolean hasFullHouse(List<Card> cards) {
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;
        int count = 1;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank() == cards.get(i-1).getRank()) {
                count++;
                if (count == 3) {
                    hasThreeOfAKind = true;
                } else if (count == 2) {
                    hasPair = true;
                }
            } else {
                count = 1;
            }
        }
        return hasThreeOfAKind && hasPair;
    }
    
    private boolean hasTwoPairs(List<Card> cards) {
        int numPairs = 0;
        int count = 1;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank() == cards.get(i-1).getRank()) {
                count++;
                if (count == 2) {
                    numPairs++;
                    count = 1;
                }
            } 
            else {
                count = 1;
            }
        }
        return numPairs == 2;
    }
                    
}
