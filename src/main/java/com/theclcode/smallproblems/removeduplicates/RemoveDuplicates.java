package com.theclcode.smallproblems.removeduplicates;

import java.util.Arrays;
import java.util.LinkedList;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] arr = {1,2, 2, 3, 3, 3, 4, 5, 5, 7, 9, 10, 10, 11, 13, 13, 14};
        System.out.println(removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }

    public static int removeDuplicates(int[] nums) {
        int lastDupIdx = -1;
        int curr = 101; // Assuming input will never contain numbers beyond -100 to 100
        int unique = 0;
        for(int i = 0; i < nums.length; i++) {
            if(curr != nums[i]) {
                curr = nums[i];
                if(lastDupIdx != -1) {
                    swap(nums, i, lastDupIdx);
                    lastDupIdx++;
                }
                unique++;
            } else {
                if(lastDupIdx == -1) {
                    lastDupIdx = i;
                }
            }
        }
        return unique;
    }

    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static int removeDuplicatesSlow(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        int unique = 0;
        for(int i : nums) {
            if(list.isEmpty() || list.getLast() != i) {
                list.add(i);
                unique++;
            }
        }
        System.out.println(list);
        int i = 0;
        while(!list.isEmpty()) {
            nums[i] = list.removeFirst();
            i++;
        }

        for(; i < nums.length; i++) {
            nums[i] = 101;
        }

        return unique;
    }
}
