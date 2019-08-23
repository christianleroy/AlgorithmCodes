package com.theclcode.sorting.insertion;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args){
        int[] toSort = new int[]{5,3,1,4,2};
        for(int i=1; i<toSort.length; i++){
            int temp = toSort[i];
            int j=i-1;
            while(j >= 0 && temp < toSort[j]){
                toSort[i] = toSort[j];
                j--;
            }
            toSort[j+1] = temp;
        }
        System.out.println(Arrays.toString(toSort));
    }
}