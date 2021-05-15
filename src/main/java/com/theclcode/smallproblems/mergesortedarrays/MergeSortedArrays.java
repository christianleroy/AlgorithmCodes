package com.theclcode.smallproblems.mergesortedarrays;

import java.util.Arrays;

public class MergeSortedArrays {

    public static void main(String[] args) {
        //[1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};

        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int index = (m + n) - 1, i = m - 1, j = n - 1; index >= 0; index--) {
            if (j == -1 || (i >= 0 && nums1[i] >= nums2[j])) {
                nums1[index] = nums1[i];
                i--;
            } else {
                nums1[index] = nums2[j];
                j--;
            }
        }
    }
}
