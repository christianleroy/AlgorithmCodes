package com.theclcode.window.medium;

//https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/submissions/
public class MinimumSwapsToGroupAllOnesTogether {
    public static void main(String[] args) {
        int[][] _data =
                {
                        {1,0,1,0,1,0,0,1,1,0,1},
                        {0,0,0,1,0},
                        {1,0,1,0,1},
                        {0}
                };

        for(int[] data : _data) {
            System.out.println(minSwaps(data));
        }
    }

    public static int minSwaps(int[] data) {
        int ones = 0;

        for (int i = 0; i < data.length; i++) {
            ones += data[i];
        }

        int left = 0;
        int right = 0;
        int count = 0;
        int maxOne = 0;


        while (right < data.length) {
            count += data[right++];
            if(right - left > ones) {
                count -= data[left++];
            }
            maxOne = Math.max(count, maxOne);
        }

        return ones-maxOne;
    }
}
