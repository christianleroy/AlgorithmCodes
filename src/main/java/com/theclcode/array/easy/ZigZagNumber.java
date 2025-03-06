package com.theclcode.array.easy;

import java.util.Arrays;

public class ZigZagNumber {

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 1};
        int[] arr = solution(numbers);
        System.out.println(Arrays.toString(arr));
    }

    // A number is ZigZag if a > b < c OR a < b > c
    static int[] solution(int[] numbers) {
        int[] arr = new int[numbers.length - 2];
        for (int i = 0, j = 0; i < numbers.length - 2; j++, i++) {
            if (numbers[i] > numbers[i + 1] && numbers[i + 1] < numbers[i + 2] || numbers[i] < numbers[i + 1] && numbers[i + 1] > numbers[i + 2]) {
                arr[j] = 1;
            } else {
                arr[j] = 0;
            }
        }
        return arr;
    }
}
