package com.theclcode.dynamicprogramming.medium;

import java.util.Arrays;

// https://leetcode.com/problems/coin-change/description/
public class CoinChange {

    public static void main(String[] args) {
        int amount = 27;
        int[] coins = {5,2,7};
        Arrays.sort(coins);
        System.out.println(Arrays.toString(coins));
        System.out.println(coinsChange(amount, coins));
    }

    private static int coinsChange(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for(int j = 0; j < coins.length; j++) {
                if(coins[j] > i) {
                    continue;
                }
                int rem = i - coins[j];
                dp[i] = Math.min(dp[i], 1 + dp[rem]);
            }

        }
        return dp[amount] > amount ? -1 : dp[amount];

    }
}
