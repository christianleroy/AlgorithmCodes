package com.theclcode.array.easy;

import java.util.Arrays;

// https://leetcode.com/problems/remove-element/description/
public class RemoveElement {
    public static void main(String[] args) {
        int[] arr = {0,1,2,2,3,0,4,2};
        System.out.println(removeElement(arr, 2));
        System.out.println(Arrays.toString(arr));

    }

    public static int removeElement(int[] nums, int val) {
        int k = 0;
        for(int i = 0, j = 1; i < nums.length; i++, j++) {
            if(nums[i] == val) {
                while(j < nums.length && nums[j] == val) {
                    j++;
                }
                if(j < nums.length) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    k++;
                }
            } else {
                k++;
            }
        }
        return k;
    }

//        Better
//        int index = 0;
//        for(int i = 0; i < nums.length; i++) {
//            if(nums[i] != val){
//                nums[index++] = nums[i];
//            }
//        }
//        return index;
}
