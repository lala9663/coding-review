/*
https://leetcode.com/problems/hand-of-straights/description/

아이디어
TreeMap 카운트 활용

자료구조
TreeMap

시간복잡도
O(N log N + N * M) -> N = hand 길이 , M = group 크기
 -> 최악 = O(10^4 * 10^4) -> O(10^8) -> 1초
*/

import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        TreeMap<Integer, Integer> cardCountMap = new TreeMap<>();

        for (int num : hand) {
            cardCountMap.put(num, cardCountMap.getOrDefault(num, 0) + 1);
        }

        while (cardCountMap.size() > 0) {
            Map.Entry<Integer, Integer> entry = cardCountMap.pollFirstEntry();

            int cardNum = entry.getKey();
            int cardCount = entry.getValue();

            for (int i = 1; i < groupSize; i++) {
                if (!cardCountMap.containsKey(cardNum + i)) return false;
                if (cardCountMap.get(cardNum + i) < cardCount) return false;

                int nextCardCount = cardCountMap.get(cardNum + i) - cardCount;
                if (nextCardCount <= 0)
                    cardCountMap.remove(cardNum + i);
                else
                    cardCountMap.put(cardNum + i, nextCardCount);
            }
        }

        return true;
    }
}