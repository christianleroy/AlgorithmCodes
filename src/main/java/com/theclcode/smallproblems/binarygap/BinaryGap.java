package com.theclcode.smallproblems.binarygap;

import java.io.IOException;

public class BinaryGap {

    public static void main(String[] args) throws IOException {
        int[] input = {1, 5, 4, 2, 9, 1041, 32}; // 0, 1, 0, 0, 2, 5, 0

        for (int i : input) {
            System.out.println(countZeroes(i));
        }
    }

    public static int countZeroes(int N) {
        if (N <= 2) {
            return 0;
        }

        int zeroes = 0;
        int max = 0;
        boolean isFirst = true;
        for (int i = 31; i >= 0; i--) {
            int mask = 1 << i;
            if ((N & mask) != 0) {
                if (isFirst) {
                    isFirst = false;
                } else if (zeroes > max) {
                    max = zeroes;
                }
                zeroes = 0;
            } else {
                zeroes++;
            }
        }
        return max;
    }
}
