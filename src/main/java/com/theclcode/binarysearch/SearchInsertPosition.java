package com.theclcode.binarysearch;

public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums = {1, 3};

        for(int i = 0; i <= 4; i++) {
            System.out.format("Target: %s, Index: %s%n", i, searchInsert(nums, i));
        }
    }

    public static int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] < target) {
                start = middle + 1;
                ans = start;
            } else {
                ans = middle;
                end = middle - 1;
            }
        }
        return ans;
    }
}
