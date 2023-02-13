package meddle.Controller;



import meddle.Entity.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TexasHoldem {
    private final int maxPlayers = 12;
    private ArrayList<Player> players = new ArrayList<>() ;
    private Player[] seats = new Player[maxPlayers];

    private ArrayList<Card> communityCards;
    private Deck deck;
    private PotCalculator PC =new PotCalculator();

    public void addPlayers(ArrayList<Player> players) {
        this.players = players;
        for (int i = 0; i < players.size(); i++) {
            seats[i] = players.get(i);
        }
        assignSeats();

    }
    private void assignSeats() {
//        int btnIndex = (int) (Math.random() * players.size());
        int btnIndex = 0;
        seats[btnIndex].setPosition("BTN");
        seats[(btnIndex + 1) % players.size()].setPosition("SB");
        seats[(btnIndex + 2) % players.size()].setPosition("BB");
        for (int i = 0; i < players.size(); i++) {
            if (seats[i].getPosition()==null) {
                seats[i].setPosition("UTG" + (i + 1));
            }
        }

    }
    public void playerAction(int playerIndex) {
//        System.out.println(count);
        Player currentPlayer = seats[playerIndex];
        Scanner s1 = new Scanner(System.in);
        System.out.println("1.call 2.raise 3.fold 4.check :");
        int choose = s1.nextInt();
        int action = currentPlayer.chooseAction(choose);
        switch (action) {
            case 1:
                currentPlayer.call(PC.getBet());
                break;
            case 2:
                currentPlayer.raise(PC.getBet());
                break;
            case 3:
                currentPlayer.fold();
                break;
            case 4:
                currentPlayer.check();
                break;
            default:
                break;
        }
    }



        public void startGame() {
            // Deal two cards to each player
            deck = new Deck();
            deck.shuffle();
            communityCards = new ArrayList<>();
            PC = new PotCalculator();

            int bet =10;
            int pot=0;
            //sb 下注10 bb下注20
            seats[2].raise(PC.getBet());
            //發2張牌給每位玩家
            for (int i = 0; i < 2; i++) {
                for (Player player : players) {
                    player.addCard(deck.dealCard());
                }
            }


            // Flop: deal three community cards
            for (int i = 0; i < 3; i++) {
                communityCards.add(deck.dealCard());
            }

            // Turn: deal one more community card
            communityCards.add(deck.dealCard());

            // River: deal one final community card
            communityCards.add(deck.dealCard());

            // Players take turns betting



            // Determine the winner
            Player winner = null;
            int winningHandValue = 0;
            for (Player player : players) {
                if (!player.hasFolded()) {
                    int playerHandValue = HandEvaluator.evaluateHand(player.getCards(), communityCards);
                    if (playerHandValue > winningHandValue) {
                        winner = player;
                        winningHandValue = playerHandValue;
                    } else {


                    }
                }
            }
        }

}
