package com.theclcode.array.easy;

import java.util.Arrays;

// https://leetcode.com/problems/squares-of-a-sorted-array/
public class SquaresOfSortedArray {

    public static void main(String[] args) {
        int[] nums = {-7, -3, 2, 3, 11};
        System.out.println(Arrays.toString(sortedSquares(nums)));

    }

    public static int[] sortedSquares(int[] nums) {
        int[] newArr = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;
        while (index >= 0) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                newArr[index--] = nums[left] * nums[left];
                left++;
            } else {
                newArr[index--] = nums[right] * nums[right];
                right--;
            }
        }
        return newArr;
    }
}
