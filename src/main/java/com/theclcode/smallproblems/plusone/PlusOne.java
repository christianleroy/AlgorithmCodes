package com.theclcode.smallproblems.plusone;

import java.util.Arrays;

// https://leetcode.com/problems/plus-one/
public class PlusOne {

    public static void main(String[] args) {
        int[] nums = {9, 9, 9, 9, 9};
        System.out.println(Arrays.toString(plusOne(nums)));
    }

    public static int[] plusOne(int[] digits) {
        int lastIdx = -1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
                continue;
            }
            lastIdx = i;
            digits[i] += 1;
            break;
        }

        if (lastIdx == -1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for (int i = 1; i < digits.length; i++) {
                result[i] = digits[i];
            }
            return result;
        }

        return digits;

    }
}
