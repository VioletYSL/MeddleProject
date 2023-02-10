package meddle.Entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EvaDemo {

    public static void main (String[] args) {
        List<Card> cards = new ArrayList<>();
        List<Card> hand = new ArrayList<>();

// Create the deck of cards
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                cards.add(new Card(i, j));
            }
        }

// Shuffle the deck
        Collections.shuffle(cards);

// Pick 7 cards
        for (int i = 0; i < 2; i++) {
            hand.add(cards.get(i));
        }for (int i = 0; i < 5; i++) {
            cards.add(cards.get(i));
        }



        // Print the hand
        for (Card card : hand) {
            System.out.println("Suit: " + card.getSuit() + ", Rank: " + card.getRank());
        }

// Count the number of each suit
        int[] suitCount = new int[4];
        for (Card card : hand) {
            int suit = card.getSuit();
            suitCount[suit]++;
        }
        int suit = 0;
// Check if there are 5 or more of the same suit
        for (int count : suitCount) {
            if (count >= 5) {
                suit += 1;
            }
        }
//        比較是否為四條,三條,
        int[] rankCount = new int[13];
        int pair = 0;
        int set = 0;
        int king = 0;
        int straight = 0;
        int straightfiush =0;

        boolean hasThree = false;
        boolean hasTwo = false;
        String type = "高牌";
        int tcount = 0;
        for (Card card : hand) {   //取得每張牌的張數
            int rank = card.getRank();
            rankCount[rank - 1]++;
        }
        for (int count : rankCount) {  //統計排型
            if (count == 0) {
                continue;
            } else if (count == 4) {
                king += 1;
            } else if (count == 3) {
                set += 1;
            } else if (count == 2) {
                pair += 1;
            }
        }
        int maxrank = 0;
        for (int j = 12; j > 3; j--) {       //判斷順子
            int count = 0;
            if (rankCount[j] >= 1) {
                for (int k = 1; k < 5; k++) {
                    if (rankCount[j-k] >= 1) {
                        count += 1;
                    }else break;
                }
                if (count >= 4) {
                    straight += 1;
                    maxrank =j+1;
                    break;
                }
            }
        }

         if(king ==1){
            type ="四條";
        }else if(set ==2 | (set ==1 && pair >=1)){
            type ="FULL-HOUSE";
        }else if (straight ==1) {
            type ="順子";
            System.out.printf("maxrank: %s\n",maxrank);
        }else if (set !=0){
            type = "三條";
        } else if (pair!=0) {
            if(pair>1){
                type = "兩對";
            }else
            type = "對子";
        }

//        System.out.printf("king = %s,set = %s,pair = %s\n",king,set,pair);

        System.out.println(type);
    }

}






