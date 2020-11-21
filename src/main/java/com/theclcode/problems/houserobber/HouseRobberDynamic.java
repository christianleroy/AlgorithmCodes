package com.theclcode.problems.houserobber;

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
                {1, 2}
        };
        for(int i=0; i<houses.length; i++){
            System.out.println(new HouseRobberDynamic().rob(houses[i]));
        }
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
