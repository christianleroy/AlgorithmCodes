package com.theclcode.searching.binarysearch;

public class BinarySearch {
    public static final String LOG_FORMAT = "Item %s is found at index %s\n";

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 7, 8, 9, 11, 17, 32, 34, 50, 51, 99, 109, 119, 200, 203, 245};

        for (int i = 0; i < arr.length; i++) {
            System.out.format(LOG_FORMAT, arr[i], binarySearch(arr, 0, arr.length - 1, arr[i]));
        }
        System.out.format(LOG_FORMAT, -5, binarySearch(arr, 0, arr.length - 1, -5));
        System.out.format(LOG_FORMAT, -245, binarySearch(arr, 0, arr.length - 1, -245));
        System.out.format(LOG_FORMAT, 360, binarySearch(arr, 0, arr.length - 1, 360));
    }

    static int binarySearch(int[] arr, int start, int end, int item) {
        if (start == end) {
            return arr[start] == item ? start : -1;
        }
        int middle = (start + end) / 2;
        if (arr[middle] == item) {
            return middle;
        } else if (item < arr[middle]) {
            return binarySearch(arr, start, middle, item);
        } else {
            return binarySearch(arr, middle + 1, end, item);
        }
    }


    public static void mainSimplified(){
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(bst(arr, 0, 4, 1));
        System.out.println(bst(arr, 0, 4, 2));
        System.out.println(bst(arr, 0, 4, 3));
        System.out.println(bst(arr, 0, 4, 4));
        System.out.println(bst(arr, 0, 4, 5));
        System.out.println(bst(arr, 0, 4, 50));
    }

    //Simplified
    public static int bst(int[] arr, int start, int end, int item){
        if(start < end){
            int middle = (start + end) / 2;
            if(item == arr[middle]){
                return middle;
            } else {
                if(item < arr[middle]){
                    return bst(arr, start, middle - 1, item);
                } else {
                    return bst(arr, middle + 1, end, item);
                }
            }
        }
        return arr[start] == item ? start : -1;
    }
}
