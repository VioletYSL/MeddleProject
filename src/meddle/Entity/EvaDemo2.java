package meddle.Entity;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EvaDemo2 {
    public static void main(String[] args){
        List<Card> hand = new ArrayList<>();
        List<Card> table = new ArrayList<>();
        List<Card> allcards = new ArrayList<>();

        hand.add(new Card(3,5));
        hand.add(new Card(0,1));

        table.add(new Card(3,2));
        table.add(new Card(3,6));
        table.add(new Card(0,5));
        table.add(new Card(3,4));
        table.add(new Card(3,3));

        allcards.addAll(hand);
        allcards.addAll(table);

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
        if(isStraightFlush(allcards)) {
            System.out.println("同花順");
        }
    }
    //皇家同花順
    private static boolean isRoyalStraightFlush(List<Card> allcards){


        return true;
    }
    //同花順
    private static boolean isStraightFlush(List<Card> allcards) {
        Collections.sort(allcards, (o1, o2) -> {
            if (o1.getRank() != o2.getRank()) {
                return o1.getRank() - o2.getRank();
            } else {
                return o1.getSuit() - o2.getSuit();
            }
        });

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

        if (sameSuitCards.size() < 5) {
            return false;
        }

        for (int i = 0; i < sameSuitCards.size() - 4; i++) {
            boolean isStraight = true;
            for (int j = 0; j < 5; j++) {
                if (sameSuitCards.get(i + j).getRank() != sameSuitCards.get(i).getRank() + j) {
                    isStraight = false;
                    break;
                }
            }
            if (isStraight) {
                return true;
            }
        }

        return false;
    }

}
