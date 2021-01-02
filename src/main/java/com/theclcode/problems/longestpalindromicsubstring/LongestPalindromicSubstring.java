package com.theclcode.problems.longestpalindromicsubstring;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "abbaxxxxx";
        boolean[][] dp = new boolean[s.length()][s.length()];

        int max = 1;
        int maxI = 0, maxJ = 0;

        for (int bound = 0; bound < s.length(); bound++) {
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + bound; j < s.length(); ) {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (bound >= 2) {
                            if (dp[i + 1][j - 1]) {
                                if (j - i + 1 > max) {
                                    max = j - i + 1;
                                    maxI = i;
                                    maxJ = j;
                                }
                                dp[i][j] = true;
                            }
                        } else {
                            if (j - i + 1 > max) {
                                max = j - i + 1;
                                maxI = i;
                                maxJ = j;
                            }
                            dp[i][j] = true;
                        }

                    }
                    break;
                }
            }
        }

        System.out.println(max);
        System.out.println(s.substring(maxI, maxJ + 1));

    }
}
