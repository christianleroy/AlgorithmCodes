package com.theclcode.smallproblems.arrayreverse;

import java.util.Arrays;

public class ArrayReverse {
    public static void main(String[] args) {

        Integer[] arr = {10, 20, 30, 40, 50, 60};

        System.out.println(Arrays.toString(arr));
        reverse(arr);
        System.out.println(Arrays.toString(arr));

        String[] arr2 = {"algorithm", "logarithm"};
        System.out.println(Arrays.toString(arr2));
        reverse(arr2);
        System.out.println(Arrays.toString(arr2));


        String[] arr3 = {"a"};
        System.out.println(Arrays.toString(arr3));
        reverse(arr3);
        System.out.println(Arrays.toString(arr3));

    }

    static void reverse(Object[] arr){
        for(int i=0; i < (arr.length / 2); i++){
            Object temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
