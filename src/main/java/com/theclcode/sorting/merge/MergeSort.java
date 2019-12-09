package com.theclcode.sorting.merge;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args){
        int[] arr = {15,13,1,2,5,13,12,10,9,7,9,8,4,4,3,2,0,1,7,-32, 32,-1, 4, 5,9, 10, 12, -12, -12, -11, 40};
        mergeSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] array, int start, int end){
        if(start < end){
            int middle = (start + end)/2;
            mergeSort(array, start, middle);
            mergeSort(array, middle+1, end);
            merge(array, start, middle, end);
        }
    }

    public static void merge(int[] array, int start, int middle, int end){
        int[] left = new int[(middle-start)+1];
        int[] right = new int[(end+1)-(middle+1)];

        for(int i=0; i<left.length; i++){
            left[i] = array[start+i];
        }
        for(int j=0; j<right.length; j++){
            right[j] = array[(middle+1)+j];
        }

        for(int index=start,i=0,j=0; index<=end; index++){
            if(j==right.length || (i<left.length && left[i]<=right[j])){
                array[index]=left[i];
                i++;
            } else {
                array[index]=right[j];
                j++;
            }
        }
    }
}
