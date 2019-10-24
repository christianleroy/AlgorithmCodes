package com.theclcode.sorting.merge;

import java.util.Arrays;

public class MultiplePointMergeSort {
    public static void main(String[] args) {
        int[][] inputArray ={{1,5},{1,2,},{0,6},{3,5},{3,1},{4,1},{3,0}};
        mergeSort(inputArray, 0, inputArray.length-1);
        for(int i=0; i<inputArray.length; i++){
            System.out.println(Arrays.toString(inputArray[i]));
        }
    }

    private static void mergeSort(int[][] arr, int start, int end){
        if(start < end){
            int middle = (start + end) / 2;
            mergeSort(arr, start, middle);
            mergeSort(arr, middle+1, end);
            merge(arr, start, middle, end);
        }
    }

    private static void merge(int[][] arr, int start, int middle, int end){
        int[][] left = new int[(middle - start) + 1][];
        int[][] right = new int[(end+1) - (middle+1)][];

        for(int i=0; i<left.length; i++){
            left[i] = arr[start+i];
        }

        for(int j=0; j<right.length; j++){
            right[j] = arr[(middle+1)+j];
        }

        for(int index=start, i=0, j=0; index<=end; index++){
            if(j==right.length || (i<left.length && isLesser(left[i], right[j]))){
                arr[index] = left[i];
                i++;
            } else {
                arr[index] = right[j];
                j++;
            }
        }
    }

    private static boolean isLesser(int[] left, int[] right){
        if(left[0]<=right[0]){
            if(left[0]==right[0]){
                return left[1]<right[1];
            } else {
                return true;
            }
        }
        return false;
    }
}
