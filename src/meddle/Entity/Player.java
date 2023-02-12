package meddle.Entity;

import java.util.ArrayList;

public class Player {
    private String  name;
    private ArrayList<Card> cards;
    private int chips;
    private boolean isFolded;
    private Boolean isCheck;



    private String  hasAction;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    private String  position;
    public Player(String name) {
        this.name = name;
        cards = new ArrayList<>();
        chips = 500;
        isFolded = false;
        hasAction = "notaction";

    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void placeBet(int bet) {
        chips -= bet;
    }

    public void fold() {
        isFolded = true;
    }

    public boolean hasFolded() {
        return isFolded;
    }

    public String getName() {
        return name;
    }

    public int chooseAction(int choose){

        return choose;
    }

    public void call() {



        setHasAction("raise");
    }

    public void raise() {





        setHasAction("raise");
    }

    public void check() {
        setHasAction("check");

    }

    public String getHasAction() {
        return hasAction;
    }

    public void setHasAction(String hasAction) {
        this.hasAction = hasAction;
    }
}
