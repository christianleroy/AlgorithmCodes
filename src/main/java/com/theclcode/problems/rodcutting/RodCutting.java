package com.theclcode.problems.rodcutting;

public class RodCutting {

    public static void main(String[] args) {
        int[] prices = new int[]{0, 2, 3, 5};
        int length = 3;
        System.out.println(cutRod(prices, length));
    }

    public static int cutRod(int[] prices, int max) {
        int[] resultTable = new int[++max];
        resultTable[0] = 0;
        for (int i = 1; i < max; i++) {
            int result = 0;
            for (int j = 1; j <= i; j++) {
                result = prices[j] + resultTable[i - j] > result ? prices[j] + resultTable[i - j] : result;
            }
            resultTable[i] = result;
        }
        return resultTable[max - 1];
    }
}
