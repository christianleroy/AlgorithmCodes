package com.theclcode.problems.houserobber;

//Dynamic
public class HouseRobberDynamic {

    private Integer[] answerTable;
    private int max;

    public static void main(String[] args) {
        int[][] houses = {
                {1, 2, 3, 1},
                {2, 7, 9, 3, 1},
                {1, 0, 0, 5, 4},
                {3, 0, 1, 5, 4, 2, 6},
                {7, 1, 2, 5, 6, 12, 5, 0, 1, 1, 5, 9, 19},
                {6, 16, 0, 4, 5, 2, 1, 22, 13, 0, 23},
                {1, 2},
                {3, 0, 1, 4}
        };
        for(int i=0; i<houses.length; i++){
            System.out.println(new HouseRobberDynamic().robCopied(houses[i]));
        }
    }

    public int robCopied(int[] houses){
        if(houses.length == 0){
            return 0;
        } else if(houses.length == 1){
            return houses[0];
        } else if(houses.length == 2){
            return Math.max(houses[0], houses[1]);
        }
        int[] dp = new int[houses.length];
        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);

        for(int i=2; i<houses.length; i++){
            dp[i] = Math.max(houses[i] + dp[i-2], dp[i-1]);
        }
        return dp[houses.length - 1];
    }

    public int rob(int[] houses) {
        final int N = houses.length;
        this.answerTable = new Integer[houses.length];
        for (int i = N-1; i >= 0; i--) {
            int best = houses[i];
            int current = best;
            for (int j = i + 2; j < N; j++) {
                if (current + this.answerTable[j] > best) {
                    best = current + this.answerTable[j];
                }
            }
            this.answerTable[i] = best;
            if(best > this.max){
                this.max = best;
            }
        }
        return max;
    }

}
