package com.theclcode.sorting.merge;

import java.util.Arrays;

//Unfinished
public class Solution {

    public static void main(String[] args){
        int[] inputArray = {4,5,6,7,1,2,3};
        merge(inputArray, 0, 3, 6);
    }

    public static void merge(int[] array, int start, int middle, int end){
        System.out.println(Arrays.toString(array));
        int[] left = new int[((middle+1)-(start+1))+1];
        int[] right = new int[(end+1)-(middle+1)];

        for(int i=0; i<left.length; i++){
            left[i] = array[start+i];
        }
        for(int j=0; j<right.length; j++){
            right[j] = array[(middle+1)+j];
        }

        for(int index=start,i=0,j=0; index<=end; index++){
            if(i<left.length && left[i]<=right[j]){
                array[index]=left[i];
                i++;
            } else {
                array[index]=right[j];
                j++;
            }
        }

        System.out.println(Arrays.toString(array));


    }
}
