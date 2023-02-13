package meddle.Controller;
import meddle.Entity.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Table {
    private final int maxPlayers = 12;
    private ArrayList<Player> players = new ArrayList<>() ;
    private Player[] seats = new Player[maxPlayers];

    private ArrayList<Card> communityCards;
    private Deck deck;
    private PotCalculator PC ;


    // 添加玩家到座位陣列
//    public void addPlayer(Player player) {
//        if (players.size() < maxPlayers) {
//            players.add(player);
//        }
//    }
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

    // 遊戲流程
    public void startGame() {
        // Deal two cards to each player
        deck = new Deck();
        deck.shuffle();
        communityCards = new ArrayList<>();
        PC = new PotCalculator();


        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addCard(deck.dealCard());
            }
        }

        int currentPlayerIndex = (players.indexOf(seats[2]) + 1) % maxPlayers;
        long playerCount = Arrays.stream(seats)
                .filter(Objects::nonNull)
                .count();
        seats[1].raise(PC.getBet());
        PC.placeBet(1);
        PC.addToPot(PC.getBet());
        seats[2].raise(2* PC.getBet());
        PC.placeBet(2);
        PC.addToPot(PC.getBet());

        do {
            if(!allButOneFolded()) {
                currentPlayerIndex = (currentPlayerIndex == playerCount) ? 0 : currentPlayerIndex;
                if (!players.get(currentPlayerIndex).hasFolded()) {
                    System.out.printf("玩家 %s 位置: %s 目前BET: %d  目前POT: %d \n", players.get(currentPlayerIndex).getName(), players.get(currentPlayerIndex).getPosition(), PC.getBet(), PC.getPot());
                    ArrayList<Card> cc = players.get(currentPlayerIndex).getCards();
                    for (Card c:cc) {
                        System.out.printf("手牌為 :  %s %s\n",c.getSuit(),c.getRank());
                    }
                    playerAction(currentPlayerIndex);
                }
                currentPlayerIndex = (currentPlayerIndex + 1) % maxPlayers;
            }else{
                break;
            }
        } while (!allPlayershasaction());

        if (!allButOneFolded()){
            PC.resetBet();
            System.out.println("翻牌");
            for(int v=0;v<3;v++){
                communityCards.add(deck.dealCard());
            }
            for(int i=0;i<3;i++) {
                for (Card deckcards:communityCards) {
                    System.out.printf("%s  %s \n",deckcards.getSuit(),deckcards.getRank());
                }
                currentPlayerIndex = (players.indexOf(seats[1]) ) % maxPlayers;
                do{
                    currentPlayerIndex = (currentPlayerIndex == playerCount) ? 0 : currentPlayerIndex;
                    if (!players.get(currentPlayerIndex).hasFolded()) {
                        System.out.printf("玩家 %s 位置: %s 目前BET: %d  目前POT: %d \n" ,players.get(currentPlayerIndex).getName(),players.get(currentPlayerIndex).getPosition(),PC.getBet(),PC.getPot());
                        playerAction(currentPlayerIndex);
                    }
                    currentPlayerIndex = (currentPlayerIndex + 1) % maxPlayers;


                }while (!allPlayershasaction()||allButOneFolded());
                System.out.println(i);
                System.out.println("------------------");
            }

        }else {
            end();
        }





    }

    public void battle(){
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

    // 是否所有玩家都過牌
    private boolean allPlayershasaction() {
        for (Player v:players) {

            if (!v.getHasAction()){
                return false;
            }
        }
        return true;
    }

    // 是否只剩下一個沒有棄牌的玩家
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
    private void end(){
        for (Player pp:players) {
            if(!pp.hasFolded()) {
                System.out.println("winner id : "+ pp.getName());
            }
        }
    }
}
