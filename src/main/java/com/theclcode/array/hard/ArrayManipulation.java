package com.theclcode.array.hard;

import java.util.Scanner;

//
public class ArrayManipulation {

    private static long arrayManipulation(int n, int[][] queries) {
        long[] arr = new long[n]; // Set up the range array
        for (int[] query : queries) {
            arr[query[0] - 1] += query[2]; // Add the value to the start range
            if (query[1] < n) {
                arr[query[1]] -= query[2]; // Diminish the value to the endIndex plus 1
            }
        }
        long sum = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // The length of the range
        int q = sc.nextInt(); // The number of queries
        int[][] queries = new int[q][3]; // startIdx, endIdx, value to add
        for(int i=0; i < q; i++){
            queries[i][0] = sc.nextInt(); // startIdx
            queries[i][1] = sc.nextInt(); // endIdx
            queries[i][2] = sc.nextInt(); // Add this value to range startIdx to endIdx
        }
        System.out.println(arrayManipulation(n, queries));
    }


}
