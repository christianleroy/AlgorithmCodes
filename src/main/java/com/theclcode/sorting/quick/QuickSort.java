package com.theclcode.sorting.quick;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {15, 13, 1, 2, 5, 13, 12, 10, 9, 7, 9, 8, 4, 4, 3, 2, 0, 1, 7, -32, 32, -1, 4, 5, 9,
                10, 12, -12, -12, -11, 40, 100, 109, -23, 41, 55, -2, 102, 110, 20, 200, 350, 200, -200, 4, 3, 2, 1, 301, 19, 18, 45, 366, 66, 66, -20, -19};
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int middle = partition(arr, start, end);
            quickSort(arr, start, middle - 1);
            quickSort(arr, middle + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i++, j);
            }
        }
        swap(arr, i, end);
        return i;
    }

    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
