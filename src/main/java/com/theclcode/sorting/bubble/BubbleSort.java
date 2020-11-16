package com.theclcode.sorting.bubble;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {15, 13, 1, 2, 5, 13, 12, 10, 9, 7, 9, 8, 4, 4, 3, 2, 0, 1, 7, -32, 32, -1, 4, 5, 9, 10, 12, -12, -12, -11, 40};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void bubbleSortIncorrect(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
