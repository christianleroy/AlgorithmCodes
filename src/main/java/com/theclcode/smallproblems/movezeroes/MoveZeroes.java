package com.theclcode.smallproblems.movezeroes;

import java.util.Arrays;

public class MoveZeroes {

    public static void main(String[] args) {

        int[][] arrs = {
                {0, 1},
                {1, 0},
                {0, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 1, 0, 1, 1},
                {0},
                {1},
                {1, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 3, 12},
                {1, 0, 1, 0, 1, 0, 0, 0, 3, 2, 9, 10, 5, 0, 0, 0}
        };

        for (int[] arr : arrs) {
            System.out.print(Arrays.toString(arr));
//            moveZeroes(arr);
            moveZeroesSimpler(arr);
            System.out.println(" => " + Arrays.toString(arr));
        }

    }

    private static void moveZeroes(int[] nums) {
        int z = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                z++;
            } else {
                nums[i - z] = nums[i];
                if (z > 0) {
                    nums[i] = 0;
                }
            }
        }
    }

    private static void moveZeroesSimpler(int[] nums) {
        for (int i = 0, z = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, z++, i);
            }
        }
    }

    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
