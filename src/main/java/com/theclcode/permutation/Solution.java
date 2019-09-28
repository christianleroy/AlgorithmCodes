package com.theclcode.permutation;

import java.util.Arrays;

public class Solution {
    static char[] stack;
    static int stackMarker=0;

    public static void main(String[] args){
        char[] arr = {'A', 'B', 'C', 'D'};
//        permute(arr, 0, arr.length-1);
        init(arr);
        combine(arr, 0, 4);
    }

    static void swap(char[] arr, int left, int right){
        char temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
    }

    static void permute(char[] arr, int start, int end){
        if(start==end){
            System.out.println(Arrays.toString(arr));
        } else {
            for(int i=start; i<=end; i++){
                swap(arr, start, i);
                permute(arr, start+1, end);
                swap(arr, start, i);
            }
        }
    }

    static void init(char[] arr){
        stack = new char[arr.length];
    }

    static void combine(char[] arr, int start, int limit){
        int length = arr.length;
        if(limit==0){
            System.out.println(new String(stack));
            return;
        }

        for(int i=start; i<=length-limit; i++){
            push(arr[i]);
            combine(arr, i+1, limit-1);
            pop();
        }
    }

    static void pop(){
        stack[--stackMarker]='\0';
    }

    static void push(char character){
        stack[stackMarker++] = character;
    }
}
