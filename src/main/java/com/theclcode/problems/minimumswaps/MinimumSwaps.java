package com.theclcode.problems.minimumswaps;

import java.util.Arrays;

//array-manipulation, cycles
public class MinimumSwaps {

    public static void main(String[] args) {
        int[][] arrays = {{7, 1, 3, 2, 4, 5, 6}, {2, 3, 4, 1, 5}, {5, 4, 3, 2, 1},{5,3,2,1,4}};
        for (int[] arr : arrays) {
            minimumSwaps(arr);
        }
    }

    static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    static int minimumSwaps(int[] arr) {
        int swaps = 0;
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while ((j + 1) != arr[j]) {
                swap(arr, j, arr[j] - 1);
                swaps++;
                j = arr[j] - 1;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.format("Minimum swaps: %s%n", swaps);
        return swaps;
    }

}
