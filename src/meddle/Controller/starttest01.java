package meddle.Controller;

import meddle.Entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class starttest01 {
    private final int maxPlayers = 12;
    private ArrayList<Player> players = new ArrayList<>();
    private Player[] seats = new Player[maxPlayers];

    private ArrayList<Card> communityCards;
    private Deck deck;
    private PotCalculator PC;
    private int btnIndex =0;
    private Boolean hasend = false;

    // 添加玩家
    public void addPlayers(ArrayList<Player> players) {
        this.players = players;
        for (int i = 0; i < players.size(); i++) {
            seats[i] = players.get(i);
        }
        assignSeats();

    }

    // 分配座位
    private void assignSeats() {
//        int btnIndex = (int) (Math.random() * players.size());

        seats[btnIndex].setPosition("BTN");
        seats[(btnIndex + 1) % players.size()].setPosition("SB");
        seats[(btnIndex + 2) % players.size()].setPosition("BB");
        for (int i = 0; i < players.size(); i++) {
            if (seats[i].getPosition() == null) {
                seats[i].setPosition("UTG" + (i + 1));
            }
        }
    }

    // 玩家選擇行為
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
                PC.addToPot(PC.getBet());
                break;
            case 2:
                Scanner s2 = new Scanner(System.in);
                int n = s2.nextInt();
                PC.placeBet(n);
                currentPlayer.raise(PC.getBet());
                PC.addToPot(PC.getBet());


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
        deck = new Deck();
        deck.dealCard();
        communityCards = new ArrayList<>();
        PC = new PotCalculator();
        // 發牌
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addCard(deck.dealCard());
            }
        }
        int currentPlayerIndex = (players.indexOf(seats[2]) + 1 % maxPlayers);
        long playerCount = Arrays.stream(seats)
                .filter(Objects::nonNull)
                .count();
        //小盲大盲下注

        seats[1].raise(PC.getBet());
        PC.placeBet(1);
        PC.addToPot(PC.getBet());
        seats[2].raise(2 * PC.getBet());
        PC.placeBet(2);
        PC.addToPot(PC.getBet());
        // 翻牌前 由UTG 開始行動
        while (!hasend){Action();}
        //翻牌圈
        while (!hasend) {
            for (int j = 0; j < 3; j++) {
                communityCards.add(deck.dealCard());
            }
            Action2();
            communityCards.add(deck.dealCard());
            Action2();
            communityCards.add(deck.dealCard());
            Action2();
            Player winner = null;
            int winningHandValue = 0;
            for (Player player : players) {
                if (!player.hasFolded()) {
                    int playerHandValue = HandEvaluator.evaluateHand(player.getCards(), communityCards);
                    if (playerHandValue > winningHandValue) {
                        winner = player;
                        winningHandValue = playerHandValue;

                    }else{
                        // 當手牌價值一樣時,判斷牌的大小
                    }
                }
            }
            System.out.printf("最後贏家為: %s 手牌為: %s\n",winner.getName(),winningHandValue);
        }
    }

    public void Action() {
        int currentPlayerIndex = (players.indexOf(seats[2]) + 1) % maxPlayers;
        long playerCount = Arrays.stream(seats)
                .filter(Objects::nonNull)
                .count();
        do {
            if (!allButOneFolded()) {
                currentPlayerIndex = (currentPlayerIndex == playerCount) ? 0 : currentPlayerIndex;
                if (!players.get(currentPlayerIndex).hasFolded()) {
                    System.out.printf("玩家 %s 位置: %s 目前BET: %d  目前POT: %d \n", players.get(currentPlayerIndex).getName(), players.get(currentPlayerIndex).getPosition(), PC.getBet(), PC.getPot());
                    playerAction(currentPlayerIndex);
                }
                currentPlayerIndex = (currentPlayerIndex + 1) % maxPlayers;
            } else {
                end();
                break;
            }
        } while (!allPlayershasaction());
        for (Player p:players) {
            if(!p.hasFolded()){
                p.setHasAction(false);
            }
        }

    }
    public void Action2() {
        int currentPlayerIndex = (players.indexOf(seats[1])) % maxPlayers;
        long playerCount = Arrays.stream(seats)
                .filter(Objects::nonNull)
                .count();
        do {
            if (!allButOneFolded()) {
                currentPlayerIndex = (currentPlayerIndex == playerCount) ? 0 : currentPlayerIndex;
                if (!players.get(currentPlayerIndex).hasFolded()) {
                    System.out.printf("玩家 %s 位置: %s 目前BET: %d  目前POT: %d \n", players.get(currentPlayerIndex).getName(), players.get(currentPlayerIndex).getPosition(), PC.getBet(), PC.getPot());
                    playerAction(currentPlayerIndex);
                }
                currentPlayerIndex = (currentPlayerIndex + 1) % maxPlayers;
            } else {
                break;
            }
        } while (!allPlayershasaction());
        for (Player p:players) {
            if(!p.hasFolded()){
                p.setHasAction(false);
            }
        }

    }
    private void end(){
        hasend =true;
        for (Player pp:players) {
            if(!pp.hasFolded()) {
                System.out.println("winner id : "+ pp.getName());
                pp.addchips(PC.getPot());
            }
        }
        if(btnIndex <players.size()){
            btnIndex++;
        }else{
            btnIndex=0;
        }

    }
    private boolean hasend(){
        return hasend;
    }

    private boolean allButOneFolded() {
        int notFlod =0;
        for (Player vv:players) {
            if(vv.hasFolded() == true){
                notFlod += 1;
            }
        }
        if(notFlod!=players.size()-1){

            return false;
        }
        return true;
    }
    private boolean allPlayershasaction() {
        for (Player v:players) {

            if (!v.getHasAction()){
                return false;
            }
        }
        return true;
    }

}