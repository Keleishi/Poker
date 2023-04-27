package Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Enum.Rank;
import Enum.Suit;

public class DeckOfCards {
    private List<Card> cards;

    public DeckOfCards() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.remove(0);
    }

    public List<Card> getFlopCards() {
        List<Card> flopCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            flopCards.add(dealCard());
        }
        return flopCards;
    }

    public Card getTurnCard() {
        return dealCard();
    }

    public Card getRiverCard() {
        return dealCard();
    }
}
