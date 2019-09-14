package com.theclcode.test;

import java.util.*;

public class Solution {
    static int stackMarker;
    static char[] stack;

    public static void main(String[] args) {
        char[] str = {'a','b','c'};
//        permute(str, 0, str.length-1);

        stackMarker=0;
        stack=new char[2];
        combine(str, 0, 2);


    }

    public static void permute(char[] str, int start, int end){
        if(start==end){
            System.out.println(new String(str));
            return;
        }

        for(int i=start; i<str.length; i++){
            swap(str, start, i);
            permute(str, start+1, end);
            swap(str, start, i);
        }
    }

    private static void swap(char[] str, int left, int right) {
        char temp = str[right];
        str[right] = str[left];
        str[left] = temp;
    }

    public static void combine(char[] str, int start, int limit){
        if(limit==0){
            System.out.println(new String(stack));
            return;
        }

        for(int i=start; i<=str.length-limit; i++){
            stack[stackMarker++]=str[i];
            combine(str, i+1, limit-1);
            stack[--stackMarker]='\0';
        }
    }
}
