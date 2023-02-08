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
            for (int j = 1; j <=13; j++) {
                cards.add(new Card(i, j));
            }
        }

// Shuffle the deck
        Collections.shuffle(cards);

// Pick 7 cards
        for (int i = 0; i < 7; i++) {
            hand.add(cards.get(i));
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

// Check if there are 5 or more of the same suit
        for (int count : suitCount) {
            if (count >= 5) {
                System.out.println("同花");
                break;
            }
        }
//        比較是否為四條,三條,
        int[] rankCount = new int[13];
        int pair =0;
        boolean hasThree = false;
        boolean hasTwo = false;
        for (Card card:hand) {
            int rank = card.getRank();
            rankCount[rank-1]++;
        }
        String type = "高牌";
        for (int count:rankCount) {
            if(count ==0){
                continue;
            }else if(count == 4){
                type= "4條";

            } else if (count==3) {
                hasThree = true;
              if (hasThree) {
                  type = "set";
              }
            } else if (count == 2) {
                pair +=1;
                hasTwo = true;
                if (hasThree && hasTwo) {
                    type ="This is a FULL-HOUSE.";

                } else if (hasTwo) {
                    if(pair>=2){
                        type ="2 pairs";
                    }else{
                        type ="pair";
                    }
                }


            }else if (!hasThree && !hasTwo){

            }







        }
        System.out.println(type);
    }

//          方法2 判斷排型

//        int Pukelevel = -1;
//        int flag = 0;
//        for (int i = 0; i < hand.size(); i++)
//        {
//            for (int j = i + 1; j < hand.size(); j++)
//            {
//                if (hand.get(i).getRank() == hand.get(j).getRank())
//                {
//                    flag++;
//                }
//            }
//        }
//        switch (flag)
//        {
//            case 6:
//                System.out.printf("四條");
//            case 4:
//                System.out.printf("葫蘆");
//            case 3:
//                System.out.printf("三條");
//            case 2:
//                System.out.printf("兩對");
//            case 1:
//                System.out.printf("對子");
//            case 0:
//                System.out.printf("高牌");
//        }

    }


