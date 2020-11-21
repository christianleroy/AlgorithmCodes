package com.theclcode.problems.houserobber;

//Backtracking, recursion, complete search
public class HouseRobberBacktracking {

    private int max = 0;
    private int current = 0;

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
            System.out.println(new HouseRobberBacktracking().rob(houses[i]));
        }
    }

    public int rob(int[] nums) {
        traverse(0, nums);
        return max;
    }

    public void traverse(int index, int[] houses) {
        for (int i = index; i < houses.length; i++) {
            add(i, houses);
            traverse(i + 2, houses);
            reverse(i, houses);
        }
    }

    public void add(int i, int[] houses) {
        if (i < houses.length) {
            current += houses[i];
            if (current > max) {
                max = current;
            }
        }
    }

    public void reverse(int i, int[] houses) {
        if (i < houses.length) {
            current -= houses[i];
        }
    }
}
