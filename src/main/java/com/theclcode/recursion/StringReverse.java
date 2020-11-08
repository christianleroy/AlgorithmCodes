package com.theclcode.recursion;

public class StringReverse {
    public static void main(String[] args){
        char[] arr = "o come o come emmanuel".toCharArray();
        reverse(arr,0, arr.length-1);
        System.out.println(new String(arr));
    }

    public static void reverse(char[] arr, int start, int end){
        if(start < end){
            int middle = (start + end) / 2;
            reverse(arr, start, middle);
            reverse(arr, middle+1, end);
            reverseString(arr, start, middle, end);
        }
    }

    static void reverseString(char[] arr, int start, int middle, int end){
        char[] left = new char[(middle - start) + 1];
        char[] right = new char[(end+1) - (middle+1)];

        for(int i=0; i<left.length; i++){
            left[i] = arr[start+i];
        }
        for(int j=0; j<right.length; j++){
            right[j] = arr[middle+1+j];
        }

        for(int index=start; index<=end;){
            for(int j=0; j<right.length; j++){
                arr[index++] = right[j];
            }
            for(int i=0; i<left.length; i++){
                arr[index++] = left[i];
            }
        }
    }
}
