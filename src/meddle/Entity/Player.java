package meddle.Entity;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String  name;
    private ArrayList<Card> cards;
    private int chips;
    private boolean isFolded;

    private Boolean  hasAction;

    public int getPlayerBet() {
        return playerBet;
    }

    public void setPlayerBet(int playerBet) {
        this.playerBet = playerBet;
    }
    public void resetBet(){
        this.playerBet=0;
    }
    private int playerBet;

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
        chips = 1000;
        isFolded = false;
        hasAction = false;

    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    public void addchips(int pot){
        chips += pot;
    }
    public int getChips(){return chips;}

    public void placeBet(int bet) {
        chips -= bet;
    }

    public void fold() {
        setHasAction(true);
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

    public void call(int bet) {
    chips = chips - bet;
    this.setPlayerBet(getPlayerBet()+bet);
    setHasAction(true);
    }

    public void raise(int bet) {
    chips =chips -bet;
    this.setPlayerBet(getPlayerBet()+bet);
    setHasAction(false);
    }

    public void check() {
        setHasAction(true);

    }

    public Boolean getHasAction() {
        return hasAction;
    }

    public void setHasAction(Boolean hasAction) {
        this.hasAction = hasAction;
    }

    public void reset(){
        isFolded = false;
        cards = new ArrayList<>();
        hasAction = false;
    }
}
