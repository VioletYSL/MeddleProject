package java.Entity;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                cards.add(new Card(i, j));
            }
        }
    }

    public void shuffle() {
        // Implement a shuffle algorithm here
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        // Return the top card from the deck and remove it from the list
        Card d1 = cards.get(0);
        cards.remove(0);
        return d1;
    }
}

