package com.theclcode.string.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
public class MinimumDeletionsToMakeCharacterFrequenciesUnique {

    public static void main(String[] args) {
        String[] strings = {
                "ceabaacb", // 2
                "abbccc", // 0
                "aaabbbcc", // 2
                "aab", // 0
                "aaabbbccc", // 3
                "abc" // 2
        };
        for (String s : strings) {
            System.out.println(minDeletions(s) + ", " + minDeletionsTopSubmission(s));
        }
    }

    public static int minDeletions(String s) {
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        int moves = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < table.length; i++) {
            if (table[i] > 0) {
                map.put(table[i], map.getOrDefault(table[i], 0) + 1);
            }
        }

        for (int i = 0; i < table.length; i++) {
            int count = table[i];
            Integer nonUniqueCount = map.get(table[i]);

            if (nonUniqueCount != null && nonUniqueCount > 1) {
                int left = table[i] - 1;
                while (map.containsKey(left)) {
                    left--;
                }
                moves += count - left;
                map.put(table[i], nonUniqueCount - 1);
                if (left > 0) {
                    map.put(left, 1);
                }
            }
        }
        return moves;
    }

    public static int minDeletionsTopSubmission(String s) {
        int moves = 0;
        Set<Integer> set = new HashSet<>();
        int[] table = new int[26];

        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < table.length; i++) {
            if (table[i] > 0) {
                while (set.contains(table[i]) && table[i] > 0) {
                    table[i]--;
                    moves++;
                }
                set.add(table[i]);
            }
        }
        return moves;
    }
}
