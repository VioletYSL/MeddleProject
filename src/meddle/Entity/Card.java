package meddle.Entity;

public class Card {
    private int rank;
    private int suit;
    private String imagePath;
    public Card(int suit, int rank,String imagePath) {
        this.rank = rank;
        this.suit = suit;
        this.imagePath = imagePath;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

