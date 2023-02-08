package meddle.Entity;

public class Card {
    private int rank;
    private int suit;

    public Card(int suit, int rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }
}

