package com.theclcode.math.easy;

// https://leetcode.com/problems/missing-number/description

public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = {5, 1, 2, 6, 7, 9, 8, 0, 4};
        System.out.println(missingNumber(nums));
    }

    public static int missingNumber(int[] nums) {

        int sum = (nums.length * (nums.length + 1)) / 2;
        int actualSum = 0;
        for(int i = 0; i < nums.length; i++) {
            actualSum += nums[i];
        }

        return sum - actualSum;
    }
}
