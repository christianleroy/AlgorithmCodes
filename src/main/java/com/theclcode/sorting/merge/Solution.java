package com.theclcode.sorting.merge;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args){
        int[] inputArray = {4,5,6,7,1,2,3};
        mergeSort(inputArray, 0, inputArray.length-1);
        System.out.println(Arrays.toString(inputArray));
    }

    public static void mergeSort(int[] array, int start, int end){
        if(start<end){
            int nextEnd = (start + end)/2;
            mergeSort(array, start, nextEnd);
            mergeSort(array, nextEnd+1, end);
            merge(array, start, nextEnd, end);
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
