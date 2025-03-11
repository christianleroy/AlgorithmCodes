package com.theclcode.dynamicprogramming.medium;

// https://leetcode.com/problems/longest-increasing-subsequence/description/
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[][] arrs = {
                {10, 9, 2, 5, 3, 7, 101, 18}
                ,
                {4, 10, 4, 3, 8, 9}
                ,
                {7, 7, 7, 7, 7, 7}
                ,
                {0, 1, 0, 3, 2, 3}
                ,
                {1, 2, 2, 3, 3}
                ,
                {1, 3, 6, 7, 9, 4, 10, 5, 6}
                ,
                {8, 9, 10, 1, 2, 3, 4}
                ,
                {10, 9, 2, 5, 3, 4}
        }; // 4, 3, 1, 4, 3, 6, 4, 3

        for (int[] arr : arrs) {
            System.out.println(lengthOfLIS(arr));
        }
    }

    public static int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];
        int max = 1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] > max) {
                        max = dp[i];
                    }
                }
            }
        }

        return max;

    }
}
