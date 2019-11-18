package com.theclcode.test;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static char[] stack;
    static int stackMarker=0;

    static void init(int limit){
        stack = new char[limit];
        stackMarker = 0;
    }

    public static void main(String[] args) {
        char[] arr = "abc".toCharArray();
        permute(arr, 0, arr.length-1);
//        init(arr.length);
//        combine(arr, 0, 2);
    }

    public static void combine(char[] arr, int start, int limit){
        if(limit == 0){
            System.out.println(new String(stack));
            return;
        }
        for(int i=start; i<=arr.length-limit; i++){
            stack[stackMarker++] = arr[i];
            combine(arr, i+1, limit-1);
            stack[--stackMarker] = '\0';
        }
    }

    public static void swap(char[] arr, int left, int right){
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void permute(char[] arr, int start, int end){
        if(start == end){
            System.out.println(new String(arr));
            return;
        }
        for(int i=start; i<=end; i++){
            swap(arr, start, i);
            permute(arr, start+1, end);
            swap(arr, start, i);
        }
    }


}
