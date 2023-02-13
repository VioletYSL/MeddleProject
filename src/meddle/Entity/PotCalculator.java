package meddle.Entity;

public class PotCalculator {
    private int pot;
    private int bet;

    public PotCalculator() {
        this.pot = 0;
        this.bet = 10;
    }

    public void addToPot(int amount) {
        this.pot += amount;
    }

    public void placeBet(int n) {
        this.bet = n*bet;
    }

    public void resetBet() {
        this.bet = 0;
    }

    public int getPot() {
        return pot;
    }

    public int getBet() {
        return bet;
    }

    public void updatePot(int remainingPlayers) {
        if (remainingPlayers <= 1) {
            addToPot(bet);
            resetBet();
        }
    }
}

