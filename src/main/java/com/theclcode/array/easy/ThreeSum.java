package com.theclcode.array.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/3sum/description/
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
//        int[] nums = {0,0,0,0};
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int low = i + 1, high = nums.length - 1; low < high;) {
                    int sum = nums[i] + nums[low] + nums[high];
                    if(sum == 0) {
                        res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        do {
                            low++;
                        } while(nums[low] == nums[low-1] && low < high);
                    } else if(sum > 0) {
                        high--;
                    } else {
                        low++;
                    }
                }
            }
        }
        return res;
    }
}
