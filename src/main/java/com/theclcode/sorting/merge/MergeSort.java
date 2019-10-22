package com.theclcode.sorting.merge;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args){
        int[] inputArray = {15,13,1,2,5,13,12,10,9,7,9,8,4,4,3,2,0,1,7};
        mergeSort(inputArray, 0, inputArray.length-1);
        System.out.println(Arrays.toString(inputArray));
    }

    public static void mergeSort(int[] array, int start, int end){
        if(start < end){
            int mid = (start + end)/2;
            mergeSort(array, start, mid);
            mergeSort(array, mid+1, end);
            merge(array, start, mid, end);
        }
    }

    public static void merge(int[] array, int start, int mid, int end){
        int[] left = new int[(mid-start)+1];
        int[] right = new int[(end+1)-(mid+1)];

        for(int i=0; i<left.length; i++){
            left[i] = array[start+i];
        }
        for(int j=0; j<right.length; j++){
            right[j] = array[(mid+1)+j];
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
