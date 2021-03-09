package com.theclcode.smallproblems.numberwithoddoccurences;

import java.util.HashMap;
import java.util.Map;

public class NumberWithOddOccurences {
    public static void main(String[] args) {
        int[] input = { 9, 3, 9, 3, 9, 7, 9 }; // 0, 1, 0, 0, 2, 5, 0
        Map<Integer, Integer> contents = new HashMap<>();
        for (int i : input) {
            if (contents.containsKey(i)) {
                contents.remove(i);
            } else {
                contents.put(i, 1);
            }
        }
        int odd = -1;
        for (Map.Entry<Integer, Integer> entry : contents.entrySet()) {
            odd = entry.getKey();
        }
        System.out.println(odd);
    }
}
