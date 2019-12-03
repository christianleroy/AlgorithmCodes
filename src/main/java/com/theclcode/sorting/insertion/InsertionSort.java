package com.theclcode.sorting.insertion;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args){
        int[] arr = {15,13,1,2,5,13,12,10,9,7,9,8,4,4,3,2,0,1,7,-32, 32,-1, 4, 5,9, 10, 12, -12, -12, -11, 40};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr){
        for(int i=1; i<arr.length; i++){
            int key = arr[i];
            int prev = i-1;
            while(prev >= 0 && arr[prev] > key){
                arr[prev+1] = arr[prev];
                prev--;
            }
            arr[prev+1] = key;
        }
    }
}