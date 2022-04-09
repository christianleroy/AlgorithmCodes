package com.theclcode.smallproblems.counthillsandvalleys;

// https://leetcode.com/problems/count-hills-and-valleys-in-an-array/
public class CountHillsAndValleys {

    public static void main(String[] args) {
        int[] nums = {6,6,5,5,4,1};
        System.out.println(countHillValley(nums));
    }

    public static int countHillValley(int[] nums) {
        if(nums.length < 3) {
            return 0;
        }

        int indexAndValleys = 0;
        for(int left = 0, middle = 1, right = 2; right < nums.length; right++) {
            if(nums[middle] != nums[right]) {
                if(nums[middle] < nums[left] && nums[middle] < nums[right]) {
                    indexAndValleys++;
                } else if(nums[middle] > nums[left] && nums[middle] > nums[right]) {
                    indexAndValleys++;
                }
                left = middle;
                middle = right;
            }
        }
        return indexAndValleys;

    }
}
