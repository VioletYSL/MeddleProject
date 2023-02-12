package meddle.Entity;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EvaDemo2 {
    public static void main(String[] args){
        List<Card> cards = new ArrayList<>();
        List<Card> hand = new ArrayList<>();
        List<Card> table = new ArrayList<>();
        List<Card> allcards = new ArrayList<>();
        String type ="高牌";

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                cards.add(new Card(i, j));
            }
        }
//        Collections.shuffle(cards);
//        for (int i = 0; i < 2; i++) {
//            hand.add(cards.get(i));
//        }for (int i = 2; i < 7; i++) {
//            table.add(cards.get(i));
//        }
//        allcards.addAll(hand);
//        allcards.addAll(table);
        Card c1 =new Card(0,9);
        Card c2 =new Card(0,10);
        Card c3 =new Card(0,1);
        Card c4 =new Card(1,4);
        Card c5 =new Card(0,11);
        Card c6 =new Card(0,8);
        Card c7 =new Card(0,13);
        allcards.add(c1);allcards.add(c2);allcards.add(c3);allcards.add(c4);
        allcards.add(c5);allcards.add(c6);allcards.add(c7);

        Collections.sort(allcards, (o1, o2) -> {
            if (o1.getRank() != o2.getRank()) {
                return o1.getRank() - o2.getRank();
            } else {
                return o1.getSuit() - o2.getSuit();
            }
        });
        for (Card card : allcards) {
            System.out.println("Suit: " + card.getSuit() + ", Rank: " + card.getRank());
        }
        System.out.println(Eva(allcards));

    }
    //辨別器
    private static String  Eva(List<Card> allcards){

        int[] suitCount = new int[4];
        for (Card card : allcards) {
            suitCount[card.getSuit()]++;
        }

        int maxSuit = 0;
        for (int i = 1; i < 4; i++) {
            if (suitCount[i] > suitCount[maxSuit]) {
                maxSuit = i;
            }
        }

        List<Card> sameSuitCards = new ArrayList<>();
        for (Card card : allcards) {
            if (card.getSuit() == maxSuit) {
                sameSuitCards.add(card);
            }
        }
        int[] rankCount = new int[13];
        for (Card card:allcards) {
            int runk = card.getRank();
            rankCount[runk-1]++;
        }

        if (sameSuitCards.size() >= 5) {
            boolean isRSF = true;
            boolean isStraightFlush = true;


            do{
                if(sameSuitCards.get(0).getRank()== 1){
                    for (int k=1;k<5;k++){
                        if(sameSuitCards.get(k).getRank()!= 9+k){
                            isRSF =false;
                            break;
                        }
                    }
                    return "皇家";
                }
                isRSF =false;
            }while(isRSF);
            do{
                for (int i = 0; i < sameSuitCards.size() - 4; i++) {

                    for (int j = 0; j < 5; j++) {
                        if (sameSuitCards.get(i + j).getRank() == sameSuitCards.get(i).getRank() + j) {
                            break;
                        }else {
                            isStraightFlush = false;
                        }
                    }
                }
                if (isStraightFlush) {
                    return "同花順";
                }
            }while(isStraightFlush);
            return "同花";
        }else {
            boolean isTAStraight = true;
            boolean isStraight =true;
            do{// 创建一个数组，并将其初始化为 false
                boolean[] found = new boolean[] {false, false, false, false, false};
// 遍历 allcards，检查每张牌的点数是否等于 1、10、11、12、13
                for (Card c : allcards) {
                    int rank = c.getRank();
                    if (rank == 1) {
                        found[0] = true;
                    } else if (rank == 10) {
                        found[1] = true;
                    } else if (rank == 11) {
                        found[2] = true;
                    } else if (rank == 12) {
                        found[3] = true;
                    } else if (rank == 13) {
                        found[4] = true;
                    }
                }
// 遍历数组，检查是否所有元素都是 true
                for (boolean b : found) {
                    if (!b) {
                        isTAStraight = false;
                    }
                }
                if(isTAStraight){
                    return "TA順子";
                }
            }while(isTAStraight);
            int pair = 0;
            int set = 0;
            int king = 0;
            int straight = 0;
            int straightfiush =0;
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
                return  "四條";
            }else if(set ==2 | (set ==1 && pair >=1)){
                return  "FULL-HOUSE";
            }else if (straight ==1) {
                return  "順子"+maxrank ;

            }else if (set !=0){
                return "三條";
            } else if (pair!=0) {
                if(pair>1){
                    return "兩對";
                }else
                    return "對子";
            }
        }

        return "高牌";
    }


}
