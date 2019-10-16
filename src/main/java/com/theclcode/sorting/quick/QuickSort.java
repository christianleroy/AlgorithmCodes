package com.theclcode.sorting.quick;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {100,51,24,31,2,9,1,5,6,7,7,15,17,129,5,4,3,2,1,Integer.MAX_VALUE};
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array, int low, int high){
        if(low < high){
            int end = partition(array, low, high);
            quickSort(array, low, end);
            quickSort(array, end+1, high);
        }
    }

    public static int partition(int[] array, int low, int high){
        int pivot = array[low];
        int start = low;
        int end = high;
        while(start < end){
            do{
                start++;
            }while(start<high-1 && array[start]<=pivot);
            do{
                end--;
            }while(end>low && array[end]>pivot);

            if(start<end){
                swap(array, start, end);
            }
        }
        swap(array, low, end);
        return end;
    }

    public static void swap(int[] array, int left, int right){
        int temp = array[right];
        array[right] = array[left];
        array[left] = temp;
    }
}
