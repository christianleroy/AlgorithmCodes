package com.theclcode.problems.editdistance;

//Levenshtein Distance
public class EditDistance {

    public static void main(String[] args) {

        String[][] words = {
                {"ros", "horse"},
                {"hyundai", "honda"},
                {"r", "rrr"},
                {"kitten", "sitting"},
                {"", "abcde"},
                {"xyz", ""},
                {"", ""},
                {"a", "ab"},
                {"aa", "aab"},
                {"kitt", "sit"}
        };

        for (String[] pair : words) {
            System.out.println(minDistance(pair[0], pair[1]));
        }
    }

    public static int minDistance(String word1, String word2) {

        if (word1.length() == 0 || word2.length() == 0) {
            return word1.length() == 0 ? word2.length() : word1.length();
        }

        int[][] dp = new int[word1.length()][word2.length()];

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                int prevBest = -1;

                if (i > 0 && j > 0) {
                    prevBest = dp[i - 1][j - 1];
                } else {
                    if (j > 0) {
                        prevBest = prevBest == -1 ? dp[i][j - 1] : Math.min(prevBest, dp[i][j - 1]);
                    }
                    if (i > 0) {
                        prevBest = prevBest == -1 ? dp[i - 1][j] : Math.min(prevBest, dp[i - 1][j]);
                    }
                }

                int val;
                if (word1.charAt(i) != word2.charAt(j)) {
                    val = 1 + (prevBest == -1 ? 0 : prevBest);
                } else {
                    if (i > 0 && j > 0) {
                        val = Math.max(dp[i - 1][j - 1], Math.abs(i - j));
                    } else {
                        val = Math.max(prevBest == -1 ? 0 : prevBest, Math.abs(i - j));
                    }
                }
                dp[i][j] = val;
            }
        }

        return dp[word1.length() - 1][word2.length() - 1];
    }

}
