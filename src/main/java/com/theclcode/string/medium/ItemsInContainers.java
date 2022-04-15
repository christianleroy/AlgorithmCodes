package com.theclcode.string.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemsInContainers {

    // Items in Containers
    // Count the * enclosed in | given the start and end indices
    public static void main(String[] args) {
        String s = "|***|**|****";
        System.out.println(compartmentCounterDp(s, Arrays.asList(5, 1, 1, 1), Arrays.asList(7, 5, 8, 7)));
    }

    public static List<Integer> compartmentCounterDp(String s, List<Integer> startIndices, List<Integer> endIndices) {
        int[] dp = new int[s.length()];
        dp[0] = 0;
        int curr = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                dp[i] = curr;
            } else {
                dp[i] = dp[i - 1];
                curr++;
            }
        }

        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < startIndices.size(); i++) {
            int opening = 0;
            int closing = 0;

            for (int x = startIndices.get(i) - 1, y = endIndices.get(i) - 1; y > x; ) {
                if (s.charAt(x) != '|') {
                    x++;
                }
                if (s.charAt(y) != '|') {
                    y--;
                }

                if (s.charAt(x) == '|' && s.charAt(y) == '|') {
                    opening = dp[x];
                    closing = dp[y];
                    break;
                }
            }
            answers.add(closing - opening);
        }
        return answers;
    }

}
