package com.theclcode.sorting.insertion;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args){
        int[] arr = {15,13,1,2,5,13,12,10,9,7,9,8,4,4,3,2,0,1,7,-32};
        for(int i=1; i<arr.length; i++){
            int key = arr[i];
            int j=i-1;
            while(j >= 0 && arr[j] > key){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        System.out.println(Arrays.toString(arr));
    }
}