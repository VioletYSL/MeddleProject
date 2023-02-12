package meddle.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandEvaluator {
    private static final Map<Integer, String> RANK_NAMES = new HashMap<>();
     {
        RANK_NAMES.put(1, "A");
        RANK_NAMES.put(2, "2");
        RANK_NAMES.put(3, "3");
        RANK_NAMES.put(4, "4");
        RANK_NAMES.put(5, "5");
        RANK_NAMES.put(6, "6");
        RANK_NAMES.put(7, "7");
        RANK_NAMES.put(8, "8");
        RANK_NAMES.put(9, "9");
        RANK_NAMES.put(10, "T");
        RANK_NAMES.put(11, "J");
        RANK_NAMES.put(12, "Q");
        RANK_NAMES.put(13, "K");
    }
    private static final Map<Integer, String> SUIT_NAMES = new HashMap<>();
    static {
        SUIT_NAMES.put(0, "♠");
        SUIT_NAMES.put(1, "♥");
        SUIT_NAMES.put(2, "♦");
        SUIT_NAMES.put(3, "♣");
    }

    public static int evaluateHand(ArrayList<Card> hand ,ArrayList<Card> communityCards) {
        // Evaluate the hand here
        // 皇家同花順 =10,同花順 = 9,四條 = 8,葫蘆 = 7,同花=6,
        // TA順子=5,順子=4,三條=3,兩對=2,對子=1,高牌 =0
        List<Card> allcards = new ArrayList<>();
        allcards.addAll(hand);
        allcards.addAll(communityCards);

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
                    return 10;
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
                    return 9;
                }
                break;
            }while(isStraightFlush);
            return 6;
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
                    return 5;
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
                return  8;
            }else if(set ==2 | (set ==1 && pair >=1)){
                return  7;
            }else if (straight ==1) {
                return  4 ;

            }else if (set !=0){
                return 3;
            } else if (pair!=0) {
                if(pair>1){
                    return 2;
                }else
                    return 1;
            }
        }

        return 0;
    }


    private static String getRankName(int rank) {
        return RANK_NAMES.get(rank);
    }

    private static String getSuitName(int suit) {
        return SUIT_NAMES.get(suit);
    }
}
