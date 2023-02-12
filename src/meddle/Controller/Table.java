package meddle.Controller;
import meddle.Entity.Card;
import meddle.Entity.Deck;
import meddle.Entity.HandEvaluator;
import meddle.Entity.Player;

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
                int bet =0;
                currentPlayer.call();
                break;
            case 2:
                currentPlayer.raise();
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


        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addCard(deck.dealCard());
            }
        }

        int currentPlayerIndex = (players.indexOf(seats[2]) + 1) % maxPlayers;
        long playerCount = Arrays.stream(seats)
                .filter(Objects::nonNull)
                .count();
        for (int j=0;j<players.size();j++){
            currentPlayerIndex = (currentPlayerIndex == playerCount) ? 0 : currentPlayerIndex;
            System.out.printf("玩家 %s  %s\n" ,players.get(currentPlayerIndex).getName(),players.get(currentPlayerIndex).getPosition());
            playerAction(currentPlayerIndex);
            currentPlayerIndex = (currentPlayerIndex + 1) % maxPlayers;
        }
        System.out.println("翻牌");
        for(int v=0;v<3;v++){
            communityCards.add(deck.dealCard());
        }

        for(int i=0;i<3;i++) {
            for (Card deckcards:communityCards) {
                System.out.printf("%s  %s \n",deckcards.getSuit(),deckcards.getRank());
            }

            while(allButOneFolded()); {
                    currentPlayerIndex = (currentPlayerIndex == playerCount) ? 0 : currentPlayerIndex;
                    if (!players.get(currentPlayerIndex).hasFolded()) {
                        System.out.printf("玩家 %s\n" ,players.get(currentPlayerIndex).getName());
                        playerAction(currentPlayerIndex);
                    }
                    currentPlayerIndex = (currentPlayerIndex + 1) % maxPlayers;


            }
            System.out.println(i);
            System.out.println("------------------");
        }
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
    private boolean allPlayersChecked() {
        for (Player v:players) {

            if (v.getHasAction().equals("noaction") || !v.getHasAction().equals("check")){
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
        if(notFlod!=1){
            return false;
        }
        return true;
    }
}
