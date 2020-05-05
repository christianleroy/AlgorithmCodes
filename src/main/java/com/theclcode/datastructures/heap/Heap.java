package com.theclcode.datastructures.heap;

import java.util.Arrays;

//Unfinished
public class Heap {

    public static void main(String[] args) {
        int[] newArr = {13, 2, 1, 5, 3, 14, 6, 13, 18, 4, 5};
        for(int i=0; i<newArr.length; i++){
            insert(newArr, i);
        }
        System.out.println(Arrays.toString(newArr));

    }

    static void insert(int[] arr, int index){
        int parent = ((index+1)/2)-1;
        while(parent >= 0 && arr[index] > arr[parent]){
            swap(arr, index, parent);
            index = parent;
            parent = ((index+1)/2)-1;
        }
    }

    //@TODO
    static void delete(int[] arr, int lastIndex){
        swap(arr, 0, lastIndex--);
        int index = 0;
        int left = 1;
        int right = 2;
        while(left < lastIndex || right < lastIndex){
            if(left < lastIndex && right < lastIndex){
                if(arr[left] >= arr[right] && arr[left] > arr[index]){
                    swap(arr, index, left);
                    index = left;
                } else {
                    swap(arr, index, right);
                    index = right;
                }
            } else {
                if(left < lastIndex && arr[left] > arr[index]){
                    swap(arr, index, left);
                    index = left;
                } else if(right < lastIndex && arr[right] > arr[index]){
                    swap(arr, index, right);
                    index = right;
                }
            }
            left = index+1;
            right = index+2;
        }
    }

    static void replace(int[] arr, int index){
        if(index < arr.length){
            int value = arr[index];
            int left = arr[index+1];
            int right = arr[index+2];

            if(value <= left || value <= right){
                if(left >= right){
                    swap(arr, index, index+1);
                    replace(arr, index+1);
                } else {
                    swap(arr, index, index+2);
                    replace(arr, index+2);
                }
            }
        }
    }

    static void swap(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
