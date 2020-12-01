package com.theclcode.problems.arraymanipulation;

import java.util.Scanner;

public class ArrayManipulation {

    private static long arrayManipulation(int n, int[][] queries) {
        long[] arr = new long[n];
        for (int[] query : queries) {
            arr[query[0] - 1] += query[2];
            if (query[1] < n) {
                arr[query[1]] -= query[2];
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

        int n = sc.nextInt();
        int q = sc.nextInt();
        int[][] queries = new int[q][3];
        for(int i=0; i < q; i++){
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
            queries[i][2] = sc.nextInt();
        }
        System.out.println(arrayManipulation(n, queries));
    }


}
