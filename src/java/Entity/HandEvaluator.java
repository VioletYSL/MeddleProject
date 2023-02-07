package java.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HandEvaluator {
    private static final Map<Integer, String> RANK_NAMES = new HashMap<>();
    static {
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

        return 0 ;
    }

    private static String getRankName(int rank) {
        return RANK_NAMES.get(rank);
    }

    private static String getSuitName(int suit) {
        return SUIT_NAMES.get(suit);
    }
}
